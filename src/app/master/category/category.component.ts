import { Component, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AlertService, AuthenticationService, UserService } from '@app/_services';
import { HttpClientService } from '@app/_services/httpClient.service';
import { first } from 'rxjs/operators';
import { TableAppComponent } from '@app/table-app/table-app.component';
import { isUndefined } from 'util';
import { CommonUtility } from '@app/_services/common.service';
@Component({
  selector: 'app-category',
  templateUrl: './category.component.html',
  styleUrls: ['./category.component.css']
})
export class CategoryComponent implements OnInit { 
  public static self: CategoryComponent;
  categoryid = '';
  mainpath = 'categories';
  categoryForm: FormGroup;
  loading = false;
  submitted = false;
  returnUrl: string;
  setOption = 'Grid';
  operation = 'Add';
  mainHead: string = "Category";
  canSave = false;
  @ViewChild(TableAppComponent, { static: false })
  private tableComponent: TableAppComponent;
  datatable = {
    title: 'Category',
    cols: [
      { title: 'ID', data: 'count' },
      { title: 'Category Name', data: 'categoryName' },
      { title: 'Category Description', data: 'categoryDescription' },
      {
        title: 'Action', render: function (data: any, type: any, full: any) {
          return CategoryComponent.self.createEditDeleteButton(full);
        }
      }
    ],
    rows: [],
    custominput: false,
    customcols:[]
  };

 exportAsXLSX():void {
    this.commonUtility.exportExcel(this.categoryForm.value, this.mainHead);
 }
 
 printAsPdf():void {
    this.commonUtility.generatePdf(this.categoryForm.value, this.mainHead, this.commonUtility.printdownloadpdfflag);
}

  constructor(private formBuilder: FormBuilder,    
    private authenticationService: AuthenticationService,
    private commonUtility: CommonUtility,
    private httpclientService : HttpClientService,
    private alertService: AlertService) {
  }

  
  createEditDeleteButton(fullData) {
    let mainId = fullData['id'];
    let editBtn = '';
    let deleteBtn = '';
    let userPermission = JSON.parse(localStorage.getItem('currentUser'))['permissions'];
    let mainpermissionJson = {};
    userPermission.forEach(element => {
      if (element.pageName == 'Category'){
        mainpermissionJson = element;
      }      
    });
    this.canSave = mainpermissionJson['canSave'];
    if (mainpermissionJson['canEdit']) {
      editBtn = '<i control="categories" class="fa fa-edit primary editcustom" value="' + mainId + '" style="margin-right: 14px;color: #ed5c0e;  font-size: 20px;font - weight: 500;cursor: pointer;" ></i>';
    }
    if (mainpermissionJson['canDelete']) {
      deleteBtn = '<i control="categories" class="fa fa-trash deletecustom" value="' + mainId + '" style="color: #ff001f;  font-size: 20px;font - weight: 500;cursor: pointer;" ></i>';
    }
    if (!mainpermissionJson['canEdit'] && !mainpermissionJson['canDelete']) {
      editBtn = '<span style="color:red"><b>No rights Avaliable</b></span>'
    }
    return editBtn + '&nbsp;' + deleteBtn;
  }
  
  changeMenu(flag) {
    this.setOption = flag;
  }

  ngOnInit() {
    this.canSave = this.httpclientService.cheackCanSavePermission('Category');
    CategoryComponent.self = this;
    this.getAllData();
    this.fillForm('');  
  }

  fnDelete(id) { 
    this.httpclientService.delete(this.mainpath+'/'+id).pipe(first()).subscribe(
      data => {
        this.alertService.deleteSuccessMsg();
        this.getAllData();
      },
      error => {
        this.alertService.error(error,false);
        this.loading = false;
      });
  }

  fnEdit(id) {
    this.httpclientService.get(this.mainpath + '/' + id).pipe(first()).subscribe(
      data => {
        this.fillForm(data);
        this.changeMenu('Add');
      },
      error => {
        this.alertService.error(error, false);
        this.loading = false;
      });
  }

  getAllData() {    
    this.httpclientService.get(this.mainpath).pipe(first()).subscribe(
      data => {    
        this.modifyJsonCount(data['content']);
        let responsecontent = data['content'];
        this.datatable.rows = responsecontent;
        if (isUndefined(this.tableComponent)) {
          this.tableComponent = new TableAppComponent();
        }
        this.tableComponent.customDatatableobj = this.datatable;
        this.tableComponent.rerender();
      },
      error => {
        this.alertService.error(error);
        this.loading = false;
      });
  }

  modifyJsonCount(jsonArr) {
    let count = 0;
    jsonArr.forEach(element => {
      count++;
      element['count'] = count;
    });
  }

  // convenience getter for easy access to form fields
  get f() { return this.categoryForm.controls; }

  onSubmit() {
    this.submitted = true;
    // stop here if form is invalid
    if (this.categoryForm.invalid) {
      return;
    }
    this.loading = true;

    let flag = this.categoryid == '' ? 'save' : 'update';
    let saveUpdatePath = this.categoryid == '' ? this.mainpath : this.mainpath + '/' + this.categoryid;
    this.httpclientService.saveOrUpdate(this.categoryForm.value, saveUpdatePath,flag)
      .pipe(first())
      .subscribe(
        data => {
          this.loading = false;
          this.submitted = false;
          this.fillForm('');
          this.alertService.successSaveUpdate('');
          this.getAllData();
          this.setOption = 'Grid';
        },
        error => {
          this.submitted = false;
          this.alertService.error(error);
          this.loading = false;
        });  
  }

  fillForm(json) {
    this.categoryid = this.checkVal(json['id']);
    this.categoryForm = this.formBuilder.group({
      id: [this.checkVal(json['id'])],
      categoryName: [this.checkVal(json['categoryName']), Validators.required],
      categoryDescription: [this.checkVal(json['categoryDescription']), Validators.required]
    });
  }

  checkVal(value) {
    if (value != '' && value != null && value != undefined) {
      return value;
    } else {
      return '';
    }
  }
 

}
