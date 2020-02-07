import { Component, OnInit, ViewChild } from '@angular/core';
import { TableAppComponent } from '@app/table-app/table-app.component';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { HttpClientService } from '@app/_services/httpClient.service';
import { AlertService } from '@app/_services';
import { first } from 'rxjs/operators';
import { isUndefined } from 'util';

@Component({
  selector: 'app-managepage',
  templateUrl: './managepage.component.html',
  styleUrls: ['./managepage.component.css']
})
export class ManagepageComponent implements OnInit {
  public static self: ManagepageComponent;
  managePageid = '';
  mainpath = 'managepages';
  managePageForm: FormGroup;
  loading = false;
  submitted = false;
  returnUrl: string;
  setOption = 'Grid';
  operation = 'Add';
  mainHead: string = "ManagePage";
  @ViewChild(TableAppComponent, { static: false })
  private tableComponent: TableAppComponent;


  datatable = {
    title: 'Manage Page',
    cols: [
      { title: 'ID', data: 'count' },
      { title: 'Page Name', data: 'pageName' },
      {
        title: 'Action', render: function (data: any, type: any, full: any) {
          return ManagepageComponent.self.createEditDeleteButton(full);
        }
      }
    ],
    rows: [],
    custominput: false,
    customcols: []
  };

  constructor(private formBuilder: FormBuilder,
    private httpclientService: HttpClientService,
    private alertService: AlertService) { }

  createEditDeleteButton(fullData) {
    let mainId = fullData['id'];
    let editBtn = '<i (click)="onEdit($event);" class="fa fa-edit primary editcustom" value="'
      + mainId + '" style="margin-right: 14px;color: #ed5c0e;  font-size: 20px;font - weight: 500;cursor: pointer;" ></i>';
    let deleteBtn = '<i (click)="onDelete($event)" class="fa fa-trash deletecustom" value="' +
      mainId + '" style="color: #ff001f;  font-size: 20px;font - weight: 500;cursor: pointer;" ></i>';
    return editBtn + '&nbsp;' + deleteBtn;
  }
  changeMenu(flag) {
    this.setOption = flag;
  }

  ngOnInit() {
    ManagepageComponent.self = this;
    this.getAllData();
    this.fillForm('');
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
        debugger;
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
  get f() { return this.managePageForm.controls; }
  onSubmit() {
    debugger;
    this.submitted = true;
    // stop here if form is invalid
    if (this.managePageForm.invalid) {
      return;
    }
    this.loading = true;
    let flag = this.managePageid == '' ? 'save' : 'update';
    let saveUpdatePath = this.managePageid == '' ? this.mainpath : this.mainpath + '/' + this.managePageid;
    this.httpclientService.saveOrUpdate(this.makeJsonForSaveOrUpdate(this.managePageForm.value, flag), saveUpdatePath, flag)
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
  makeJsonForSaveOrUpdate(formObject, flag) {
    let finaljson = {}
    if (flag == 'save') {
      finaljson['pageName'] = formObject.pageName;
    } else {
      finaljson['pageName'] = formObject.pageName;
      finaljson['id'] = formObject.id;
      finaljson['version'] = formObject.version;
    }
    return finaljson;
  }
  fillForm(json) {
    this.managePageid = this.checkVal(json['id']);
    this.managePageForm = this.formBuilder.group({
      id: [this.checkVal(json['id'])],
      pageName: [this.checkVal(json['pageName']), Validators.required],
      version: [0]
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
