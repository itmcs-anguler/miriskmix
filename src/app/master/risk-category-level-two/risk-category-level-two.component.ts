import { Component, OnInit, ViewChild } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { AlertService } from '@app/_services';
import { HttpClientService } from '@app/_services/httpClient.service';
import { CommonUtility } from '@app/_services/common.service';
import { TableAppComponent } from '@app/table-app/table-app.component';
import { isUndefined } from 'util';
import { first } from 'rxjs/operators';

@Component({
  selector: 'app-risk-category-level-two',
  templateUrl: './risk-category-level-two.component.html',
  styleUrls: ['./risk-category-level-two.component.css']
})
export class RiskCategoryLevelTwoComponent implements OnInit {
  public static self: RiskCategoryLevelTwoComponent;
  riskCategoryLevelTwoForm: FormGroup;
  loading = false;
  submitted = false;
  returnUrl: string;
  setOption = 'Grid';
  operation = 'Add';
  mainpath = 'riskcategoryleveltwoes';
  id = '';
  @ViewChild(TableAppComponent, { static: false })
  private tableComponent: TableAppComponent;

  datatable = {
    title: 'riskCategoryLevelTwo',
    cols: [
      { title: 'ID', data: 'count' },
      { title: 'Risk Category Level Two', data: 'riskCategoryLevelTwoName' },
      { title: 'Risk Category Level Two Description', data: 'riskCategoryLevelTwoDescription' },
      {
        title: 'Action', render: function (data: any, type: any, full: any) {
          return RiskCategoryLevelTwoComponent.self.createEditDeleteButton(full);
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

  exportAsXLSX():void {
    this.commonUtility.exportExcel(this.riskCategoryLevelTwoForm.value, 'Risk_Category_Level_2');
 }

 printAsPdf():void {
	this.commonUtility.generatePdf(this.riskCategoryLevelTwoForm.value, 'Risk_Category_Level_2', this.commonUtility.printdownloadpdfflag);
  }

  canSave = false;
  createEditDeleteButton(fullData) {
    let mainId = fullData['id'];
    let editBtn = '';
    let deleteBtn = '';
    let userPermission = JSON.parse(localStorage.getItem('currentUser'))['permissions'];
    let mainpermissionJson = {};
    userPermission.forEach(element => {
      if (element.pageName == 'Risk_Category_Level_2') {
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
    this.riskCategoryLevelTwoForm = this.formBuilder.group({
      id: [this.commonUtility.checkVal(json['id'])],
      riskCategoryLevelTwoName: [this.commonUtility.checkVal(json['riskCategoryLevelTwoName']), Validators.required],
      riskCategoryLevelTwoDescription: [this.commonUtility.checkVal(json['riskCategoryLevelTwoDescription']), Validators.required]

    });
  }


  ngOnInit() {
    this.canSave = this.httpclientService.cheackCanSavePermission('Risk_Category_Level_2');
    this.getAllData();
    RiskCategoryLevelTwoComponent.self = this;
    this.riskCategoryLevelTwoForm = this.formBuilder.group({
      riskCategoryLevelTwoName: ['', Validators.required],
      riskCategoryLevelTwoDescription: ['', Validators.required]
    });
  }

  // convenience getter for easy access to form fields
  get f() { return this.riskCategoryLevelTwoForm.controls; }



  onSubmit() {
    this.submitted = true;
    // stop here if form is invalid
    if (this.riskCategoryLevelTwoForm.invalid) {
      return;
    }
    this.loading = true;

    let flag = this.id == '' ? 'save' : 'update';
    let saveUpdatePath = this.id == '' ? this.mainpath : this.mainpath + '/' + this.id;

    this.httpclientService.saveOrUpdate(this.riskCategoryLevelTwoForm.value, saveUpdatePath, flag)
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
