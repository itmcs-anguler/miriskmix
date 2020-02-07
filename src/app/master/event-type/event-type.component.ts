import { Component, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

import { AlertService, UserService } from '@app/_services';
import { first } from 'rxjs/operators';
import { HttpClientService } from '@app/_services/httpClient.service';
import { TableAppComponent } from '@app/table-app/table-app.component';
import { CommonUtility } from '@app/_services/common.service';
import { isUndefined } from 'util';
@Component({
  selector: 'app-event-type',
  templateUrl: './event-type.component.html',
  styleUrls: ['./event-type.component.css']
})
export class EventTypeComponent implements OnInit {
  public static self: EventTypeComponent;
  eventTypeForm: FormGroup;
  loading = false;
  submitted = false;
  returnUrl: string;
  setOption = 'Grid';
  operation = 'Add';
  mainpath = 'eventtypes';
  canSave = false;
  id = '';
  @ViewChild(TableAppComponent, { static: false })
  private tableComponent: TableAppComponent;
  datatable = {
    title: 'eventType',
    cols: [
      { title: 'ID', data: 'count' },
      { title: 'Event Type', data: 'eventTypeName' },
      { title: 'Event Type Description', data: 'eventTypeDescription' },
      {
        title: 'Action', render: function (data: any, type: any, full: any) {
          return EventTypeComponent.self.createEditDeleteButton(full);
        }
      }
    ],
    rows: [],
    custominput: false,
    customcols: []
  };
  constructor(private formBuilder: FormBuilder,
    private userService: UserService,
    private commonUtility: CommonUtility,
    private httpclientService: HttpClientService,
    private alertService: AlertService) {
  }

  exportAsXLSX():void {
    this.commonUtility.exportExcel(this.eventTypeForm.value, 'Event_Type');
 }

 printAsPdf():void {
	this.commonUtility.generatePdf(this.eventTypeForm.value, 'Event_Type', this.commonUtility.printdownloadpdfflag);
  }

  createEditDeleteButton(fullData) {
    let mainId = fullData['id'];
    let editBtn = '';
    let deleteBtn = '';
    let userPermission = JSON.parse(localStorage.getItem('currentUser'))['permissions'];
    let mainpermissionJson = {};
    userPermission.forEach(element => {
      if (element.pageName == 'Event_Type') {
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
        console.log(data['content'])
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
    this.eventTypeForm = this.formBuilder.group({
      id: [this.commonUtility.checkVal(json['id'])],
      eventTypeName: [this.commonUtility.checkVal(json['eventTypeName']), Validators.required],
      eventTypeDescription: [this.commonUtility.checkVal(json['eventTypeDescription']), Validators.required]

    });
  }
  ngOnInit() {
    this.canSave = this.httpclientService.cheackCanSavePermission('Event_Type');
    this.getAllData();
    this.fillForm('');
    EventTypeComponent.self = this;
    this.eventTypeForm = this.formBuilder.group({
      eventTypeName: ['', Validators.required],
      eventTypeDescription: ['', Validators.required]
    });
  }

  // convenience getter for easy access to form fields
  get f() { return this.eventTypeForm.controls; }


  onSubmit() {
    this.submitted = true;
    // stop here if form is invalid
    if (this.eventTypeForm.invalid) {
      return;
    }
    this.loading = true;

    let flag = this.id == '' ? 'save' : 'update';
    let saveUpdatePath = this.id == '' ? this.mainpath : this.mainpath + '/' + this.id;

    this.httpclientService.saveOrUpdate(this.eventTypeForm.value, saveUpdatePath, flag)
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
