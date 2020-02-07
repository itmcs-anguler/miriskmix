import { Component, OnInit, ViewChild } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { AlertService } from '@app/_services';
import { HttpClientService } from '@app/_services/httpClient.service';
import { TableAppComponent } from '@app/table-app/table-app.component';
import { isUndefined } from 'util';
import { first } from 'rxjs/operators';
import { CommonUtility } from '@app/_services/common.service';

@Component({
  selector: 'app-risk-category-level-one',
  templateUrl: './risk-category-level-one.component.html',
  styleUrls: ['./risk-category-level-one.component.css']
})
export class RiskCategoryLevelOneComponent implements OnInit {
  public static self: RiskCategoryLevelOneComponent;
  riskCategoryLevelOneForm: FormGroup;
  loading = false;
  submitted = false;
  returnUrl: string;
  setOption = 'Grid';
  operation = 'Add';
  mainpath = 'riskcategorylevelones';
  id = '';
  @ViewChild(TableAppComponent, { static: false })
  private tableComponent: TableAppComponent;
  datatable = {
    title: 'riskCategoryLevelOne',
    cols: [
      { title: 'ID', data: 'count' },
      { title: 'Risk Category Level One', data: 'riskCategoryLevelOneName' },
      { title: 'Risk Category Level One Description', data: 'riskCategoryLevelOneDescription' },
      {
        title: 'Action', render: function (data: any, type: any, full: any) {
          return RiskCategoryLevelOneComponent.self.createEditDeleteButton(full);
        }
      }
    ],
    rows: [],
    custominput: false,
    customcols: []
  };
  constructor(private formBuilder: FormBuilder,
    private httpclientService: HttpClientService,
    private commonUtility: CommonUtility,
    private alertService: AlertService) {
  }

  canSave = false;
  createEditDeleteButton(fullData) {
    let mainId = fullData['id'];
    let editBtn = '';
    let deleteBtn = '';
    let userPermission = JSON.parse(localStorage.getItem('currentUser'))['permissions'];
    let mainpermissionJson = {};
    userPermission.forEach(element => {
      if (element.pageName == 'Risk_Category_Level_1') {
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

  exportAsXLSX():void {
    this.commonUtility.exportExcel(this.riskCategoryLevelOneForm.value, 'Risk_Category_Level_1');
 }

 printAsPdf():void {
	this.commonUtility.generatePdf(this.riskCategoryLevelOneForm.value, 'Risk_Category_Level_1', this.commonUtility.printdownloadpdfflag);
  }

  changeMenu(flag) {
    this.setOption = flag;
  }

  formSubmitload() {
    this.loading = true;
    setTimeout(function () {
      this.loading = false;
      this.changeMenu('Grid');
    }, 3000);
  }
  fnDelete(id) {
    this.httpclientService.delete(this.mainpath + '/' + id).pipe(first()).subscribe(
      data => {
        this.alertService.deleteSuccessMsg();
        this.getAllData();
      },
      error => {
        this.alertService.error(error, false);
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
  modifyJsonCount(jsonArr) {
    let count = 0;
    jsonArr.forEach(element => {
      count++;
      element['count'] = count;
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
  fillForm(json) {
    this.id = this.commonUtility.checkVal(json['id']);
    this.riskCategoryLevelOneForm = this.formBuilder.group({
      id: [this.commonUtility.checkVal(json['id'])],
      riskCategoryLevelOneName: [this.commonUtility.checkVal(json['riskCategoryLevelOneName']), Validators.required],
      riskCategoryLevelOneDescription: [this.commonUtility.checkVal(json['riskCategoryLevelOneDescription']), Validators.required]
    });
  }
  ngOnInit() {
    this.canSave = this.httpclientService.cheackCanSavePermission('Risk_Category_Level_1');
    this.getAllData();
    this.fillForm('');
    RiskCategoryLevelOneComponent.self = this;
    this.riskCategoryLevelOneForm = this.formBuilder.group({
      riskCategoryLevelOneName: ['', Validators.required],
      riskCategoryLevelOneDescription: ['', Validators.required]
    });
  }

  // convenience getter for easy access to form fields
  get f() { return this.riskCategoryLevelOneForm.controls; }



  onSubmit() {
    this.submitted = true;
    // stop here if form is invalid
    if (this.riskCategoryLevelOneForm.invalid) {
      return;
    }
    this.loading = true;

    let flag = this.id == '' ? 'save' : 'update';
    let saveUpdatePath = this.id == '' ? this.mainpath : this.mainpath + '/' + this.id;

    this.httpclientService.saveOrUpdate(this.riskCategoryLevelOneForm.value, saveUpdatePath, flag)
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

}
