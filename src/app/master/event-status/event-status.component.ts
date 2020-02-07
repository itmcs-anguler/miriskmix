import { Component, OnInit, ViewChild } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { AlertService } from '@app/_services';
import { HttpClientService } from '@app/_services/httpClient.service';
import { CommonUtility } from '@app/_services/common.service';
import { TableAppComponent } from '@app/table-app/table-app.component';
import { first } from 'rxjs/operators';
import { isUndefined } from 'util';

@Component({
  selector: 'app-event-status',
  templateUrl: './event-status.component.html',
  styleUrls: ['./event-status.component.css']
})
export class EventStatusComponent implements OnInit {
  public static self: EventStatusComponent;
  eventStatusForm: FormGroup;
  loading = false;
  submitted = false;
  returnUrl: string;
  setOption = 'Grid';
  operation = 'Add';
  mainpath = 'eventstatuses';
  id = '';
  canSave = false;
  @ViewChild(TableAppComponent, { static: false })
  private tableComponent: TableAppComponent;
  datatable = {
    title: 'eventStatus',
    cols: [
      { title: 'ID', data: 'count' },
      { title: 'Event Status', data: 'eventStatusName' },
      { title: 'Event Status Description', data: 'eventStatusDescription' },
      {
        title: 'Action', render: function (data: any, type: any, full: any) {
          return EventStatusComponent.self.createEditDeleteButton(full);
        }
      }
    ],
    rows: [],
    custominput: false,
    customcols: []
  };

  exportAsXLSX():void {
    this.commonUtility.exportExcel(this.eventStatusForm.value, 'Event_Status');
 }

 printAsPdf():void {
	this.commonUtility.generatePdf(this.eventStatusForm.value, 'Event_Status', this.commonUtility.printdownloadpdfflag);
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
      if (element.pageName == 'Event_Status') {
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
    this.eventStatusForm = this.formBuilder.group({
      id: [this.commonUtility.checkVal(json['id'])],
      eventStatusName: [this.commonUtility.checkVal(json['eventStatusName']), Validators.required],
      eventStatusDescription: [this.commonUtility.checkVal(json['eventStatusDescription']), Validators.required]

    });
  }
  ngOnInit() {
    this.canSave = this.httpclientService.cheackCanSavePermission('Event_Status');
    this.getAllData();
    EventStatusComponent.self = this;    
    this.eventStatusForm = this.formBuilder.group({
      eventStatusName: ['', Validators.required],
      eventStatusDescription: ['', Validators.required]
    });
  }

  get f() { return this.eventStatusForm.controls; }


  onSubmit() {
    this.submitted = true;
    // stop here if form is invalid
    if (this.eventStatusForm.invalid) {
      return;
    }
    this.loading = true;

    let flag = this.id == '' ? 'save' : 'update';
    let saveUpdatePath = this.id == '' ? this.mainpath : this.mainpath + '/' + this.id;

    this.httpclientService.saveOrUpdate(this.eventStatusForm.value, saveUpdatePath, flag)
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
