import { Component, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthenticationService, AlertService, UserService } from '@app/_services';
import { first } from 'rxjs/operators';
import { HttpClientService } from '@app/_services/httpClient.service';
import { TableAppComponent } from '@app/table-app/table-app.component';
import { isUndefined } from 'util';
import { CommonUtility } from '@app/_services/common.service';

@Component({
  selector: 'app-ccy',
  templateUrl: './ccy.component.html',
  styleUrls: ['./ccy.component.css']
})
export class CcyComponent implements OnInit {
  public static self: CcyComponent;
  mainpath = 'ccies';
  ccyForm: FormGroup;
  loading = false;
  submitted = false;
  returnUrl: string;
  setOption = 'Grid';
  operation = 'Add';
  id = '';
  canSave = false;
  @ViewChild(TableAppComponent, { static: false })
  private tableComponent: TableAppComponent;
  datatable = {
    title: 'CCY',
    cols: [
      { title: 'ID', data: 'count' },
      { title: 'CCY', data: 'ccyName' },
      { title: 'CCY Description', data: 'ccyDescription' },
      {
        title: 'Action', render: function (data: any, type: any, full: any) {
          return CcyComponent.self.createEditDeleteButton(full);
        }
      }
    ],
    rows: [],
    custominput: false,
    customcols: []
  };

  exportAsXLSX():void {
    this.commonUtility.exportExcel(this.ccyForm.value, 'CCY');
 }
 printAsPdf():void {
	this.commonUtility.generatePdf(this.ccyForm.value, 'CCY', this.commonUtility.printdownloadpdfflag);
  }

  constructor(private formBuilder: FormBuilder,
    private httpclientService: HttpClientService,
    private commonUtility: CommonUtility,
    private alertService: AlertService) {
  }

  createEditDeleteButton(fullData) {
    let mainId = fullData['id'];
    let editBtn = '';
    let deleteBtn = '';
    let userPermission = JSON.parse(localStorage.getItem('currentUser'))['permissions'];
    let mainpermissionJson = {};
    userPermission.forEach(element => {
      if (element.pageName == 'CCY') {
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
  fillForm(json) {
    this.id = this.commonUtility.checkVal(json['id']);
    this.ccyForm = this.formBuilder.group({
      id: [this.commonUtility.checkVal(json['id'])],
      ccyName: [this.commonUtility.checkVal(json['ccyName']), Validators.required],
      ccyDescription: [this.commonUtility.checkVal(json['ccyDescription']), Validators.required]

    });
  }

  ngOnInit() {
    this.canSave = this.httpclientService.cheackCanSavePermission('CCY');
    this.getAllData();
    CcyComponent.self = this;
    this.fillForm('');
    this.ccyForm = this.formBuilder.group({
      ccyName: ['', Validators.required],
      ccyDescription: ['', Validators.required]
    });
  }

  // convenience getter for easy access to form fields
  get f() { return this.ccyForm.controls; }

  onSubmit() {
    this.submitted = true;
    // stop here if form is invalid
    if (this.ccyForm.invalid) {
      return;
    }
    this.loading = true;

    let flag = this.id == '' ? 'save' : 'update';
    let saveUpdatePath = this.id == '' ? this.mainpath : this.mainpath + '/' + this.id;

    this.httpclientService.saveOrUpdate(this.ccyForm.value, saveUpdatePath, flag)
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
