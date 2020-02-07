import { Component, OnInit, ViewChild, AfterViewInit } from '@angular/core';
import * as $ from 'jquery';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { UserService, AlertService } from '@app/_services';
import { first } from 'rxjs/operators';
import { TableAppComponent } from '@app/table-app/table-app.component';
import { HttpClientService } from '@app/_services/httpClient.service';
import { isUndefined } from 'util';
import { CommonUtility } from '@app/_services/common.service';

@Component({
  selector: 'app-risk-event',
  templateUrl: './risk-event.component.html',
  styleUrls: ['./risk-event.component.css']
})

export class RiskEventComponent implements OnInit, AfterViewInit {

  public static self: RiskEventComponent;
  riskEventForm: FormGroup;

  loading = false;
  submitted = false;

  rootCauseForm: FormGroup;
  loadingRootCause = false;
  rootCauseSubmitted = false;

  IncidentShowHide;
  grossImpactFormShow;
  recoveriesShowHide;

  setOption = 'Incident';
  grossImpact = 1;
  recoveries = 1;
  @ViewChild(TableAppComponent, { static: false })
  private tableComponent: TableAppComponent;

  @ViewChild(TableAppComponent, { static: false })
  private tableComponentGrossImpact: TableAppComponent;

  @ViewChild(TableAppComponent, { static: false })
  private tableComponentRecovery: TableAppComponent;

  @ViewChild(TableAppComponent, { static: false })
  private tableComponentDocument: TableAppComponent;

  incidentTitleLabel = 'Incident Title Name';
  incidentTitleLabelClass = 'lblgrey18bold';
  incidentTitleid = 'IncidentTitleName';
  incidentTitleOptions = ['Incident Title Name 1', 'Incident Title Name 2', 'Incident Title Name 3', 'Incident Title Name 4', 'Incident Title Name 5'];
  preparedincidentTitleOptions = this.makeCustomDrpdownWithvalSimple(this.incidentTitleOptions);

  drpInternalorExternalvalue = null;
  internalorExternalLabel = 'Internal or External Name';
  internalorExternalLabelClass = 'lblgrey18bold';
  internalorExternalid = 'IncidentTitleName';
  internalorExternalOptions = [{
    'id': 'External',
    'description': 'External'
  }, {
    'id': 'Internal',
    'description': 'Internal'
  }];
  // preparedinternalorExternalOptions = this.makeCustomDrpdownWithval(this.internalorExternalOptions);

  eventTypeOptions = [];
  eventTypeid = 'eventTypeName';
  drpEventTypevalue = null;
  eventTypeLabel = 'Event Type';

  drpCCYvalue = null;
  CCYOptions = [];
  CCYLabel = 'CCY';
  CCYid = 'CCCYName';

  drpRiskCategoryOnevalue = null;
  riskCategoryOneLabel = 'Risk Category One';
  riskCategoryOneId = 'riskCategoryOneName';
  riskCategoryOneOptions = [];

  drpRiskCategoryTwovalue = null;
  riskCategoryTwoLabel = 'Risk Category Two';
  riskCategoryTwoId = 'riskCategoryTwoName';
  riskCategoryTwoOptions = [];

  eventStatusOptions = [];
  eventStatusid = 'eventStatusName';
  drpEventStatusvalue = null;
  eventStatusLabel = 'Event Status';

  drpReportabletoRegulatorvalue = null;
  reportabletoRegulatorId = 'reportabletoRegulatorName';
  reportabletoRegulatorLabel = 'Reportable to Regulator';
  reportabletoRegulatorOptions =
    [{
      'id': 'YES',
      'description': 'YES'
    }, {
      'id': 'NO',
      'description': 'NO'
    }];

  locationOptions =
    [{
      'id': 'Broda',
      'description': 'Broda'
    }, {
      'id': 'Ahmedabad',
      'description': 'Ahmedabad'
    }];
  locationId = 'locationName';
  locationLabel = 'Location';
  drpLocationvalue = null;

  ownerLabel = 'Owner Name';
  ownerLabelClass = 'lblgrey18bold';
  ownerid = 'IncidentTitleName';
  ownerOptions = ['Owner Name 1', 'Owner Name 2', 'Owner Name 3', 'Owner Name 4', 'Owner Name 5'];
  preparedownerOptions = this.makeCustomDrpdownWithvalSimple(this.ownerOptions);


  ccyLabel = 'CCY Name';
  ccyLabelClass = 'lblgrey18bold';
  ccyid = 'IncidentTitleName';
  ccyOptions = ['CCY 1', 'CCY 2', 'CCY 3', 'CCY 4', 'CCY 5'];
  preparedccyOptions = this.makeCustomDrpdownWithvalSimple(this.ccyOptions);

  amountLabel = 'Amount Name';
  amountLabelClass = 'lblgrey18bold';
  amountid = 'IncidentTitleName';
  amountOptions = ['Amount 1', 'Amount 2', 'Amount 3', 'Amount 4', 'Amount 5'];
  preparedamountOptions = this.makeCustomDrpdownWithvalSimple(this.amountOptions);
  fillFormAdditionDocument(json) {
    this.additionalDocumentId = this.commonUtility.checkVal(json['id']);
    this.additionalForm = this.formBuilder.group({
      id: [this.commonUtility.checkVal(this.additionalDocumentId)],
      fileName: [this.commonUtility.checkVal(json['fileName']), Validators.required],
      documentTitle: [this.commonUtility.checkVal(json['documentTitle']), Validators.required],
      documentOwner: [this.commonUtility.checkVal(json['documentOwner']), Validators.required],
      attachment: [this.commonUtility.checkVal(json['attachment'])],
      uniqueId: [this.commonUtility.checkVal(json['uniqueId'])]
    });
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

        this.refilllDropdownvalue(data);
        this.IncidentShowHide = 1;
        this.setOption = 'none';
      },
      error => {
        this.alertService.error(error, false);
        this.loading = false;
      });
  }

  fnDeleteAdditionDocument(id) {
    this.httpclientService.delete(this.mainPathAdditional + '/' + id).pipe(first()).subscribe(
      data => {
        this.alertService.deleteSuccessMsg();
        this.getAllData();
      },
      error => {
        this.alertService.error(error, false);
        this.loading = false;
      });
  }

  fnEditAdditionDocument(id) {
    console.log('fnEditAdditionDocument ::: === >>>>> ');
    this.httpclientService.get(this.mainPathAdditional + '/' + id).pipe(first()).subscribe(
      data => {
        this.fillFormAdditionDocument(data);
        this.changeDocument();
      },
      error => {
        this.alertService.error(error, false);
        this.loading = false;
      });
  }
  fnDeleteGrossImpact(id) {
    this.httpclientService.delete(this.mainpathGrossImpact + '/' + id).pipe(first()).subscribe(
      data => {
        this.alertService.deleteSuccessMsg();
        this.getAllDataGrossImpact();
      },
      error => {
        this.alertService.error(error, false);
        this.loading = false;
      });
  }

  fnEditGrossImpact(id) {
    this.httpclientService.get(this.mainpathGrossImpact + '/' + id).pipe(first()).subscribe(
      data => {
        this.fillFormGrossImpact(data);
        this.refilllDropdownvalue(data);
        this.grossImpactFormShow = 1;
        this.grossImpact = 0;
      },
      error => {
        this.alertService.error(error, false);
        this.loading = false;
      });
  }

  fnDeleteRecovery(id) {
    this.httpclientService.delete(this.mainpathRecovery + '/' + id).pipe(first()).subscribe(
      data => {
        this.alertService.deleteSuccessMsg();
        this.getAllDataRecoveries();
      },
      error => {
        this.alertService.error(error, false);
        this.loading = false;
      });
  }

  fnEditRecovery(id) {
    this.httpclientService.get(this.mainpathRecovery + '/' + id).pipe(first()).subscribe(
      data => {
        this.fillFormRecovery(data);
        this.refilllDropdownvalue(data);
        this.recoveries = 0;
        this.recoveriesShowHide = 1;
      },
      error => {
        this.alertService.error(error, false);
        this.loading = false;
      });
  }

  makeCustomDrpdownWithvalSimple(options) {
    let optionsMain = [];
    for (var i = 0; i < options.length; i++) {
      let json = {};
      json['id'] = i;
      json['description'] = options[i];
      optionsMain.push(json);
    }
    return optionsMain;
  }

  createEditDeleteButton(fullData) {
    let mainId = fullData['id'];
    let editBtn = '<i control="riskevent" class="fa fa-edit primary editcustom" value="' + mainId + '" style="margin-right: 14px;color: #ed5c0e;  font-size: 20px;font - weight: 500;cursor: pointer;" ></i>';
    let deleteBtn = '<i control="riskevent" class="fa fa-trash deletecustom" value="' + mainId + '" style="color: #ff001f;  font-size: 20px;font - weight: 500;cursor: pointer;" ></i>';
    return editBtn + '&nbsp;' + deleteBtn;
  }

  createEditDeleteButtonGrossImpact(fullData) {
    let mainId = fullData['id'];
    let editBtn = '<i control="grossImpact" class="fa fa-edit primary editcustom" value="' + mainId + '" style="margin-right: 14px;color: #ed5c0e;  font-size: 20px;font - weight: 500;cursor: pointer;" ></i>';
    let deleteBtn = '<i control="grossImpact" class="fa fa-trash deletecustom" value="' + mainId + '" style="color: #ff001f;  font-size: 20px;font - weight: 500;cursor: pointer;" ></i>';
    return editBtn + '&nbsp;' + deleteBtn;
  }
  createEditDeleteButtonRecovery(fullData) {
    let mainId = fullData['id'];
    let editBtn = '<i control="recovery" class="fa fa-edit primary editcustom" value="' + mainId + '" style="margin-right: 14px;color: #ed5c0e;  font-size: 20px;font - weight: 500;cursor: pointer;" ></i>';
    let deleteBtn = '<i control="recovery" class="fa fa-trash deletecustom" value="' + mainId + '" style="color: #ff001f;  font-size: 20px;font - weight: 500;cursor: pointer;" ></i>';
    return editBtn + '&nbsp;' + deleteBtn;
  }

  getOption(id) {
    this.setOption = id;
  }

  changeDocument() {
    this.setOption = 'show';
  }
  cancelDocument() {
    this.setOption = 'Document';
  }

  changeMenuGrossImpact() {
    this.grossImpactFormShow = 1;
    this.grossImpact = 0;
  }

  cancelMenuGrossImpact() {
    this.grossImpactFormShow = 0;
    this.grossImpact = 1;
  }

  changeMenuRecoveryImpact() {
    this.recoveriesShowHide = 1;
    if (this.recoveries = 0) {
      this.recoveries = 1;
    } else {
      this.recoveries = 0;
    }

  }
  cancelMenurecoveries() {
    this.recoveriesShowHide = 0;
    this.recoveries = 1;

  }
  changeIncidentMenu() {
    this.IncidentShowHide = 1;
    this.setOption = 'none';
  }
  cancelIncidentMenu() {
    this.IncidentShowHide = 0;
    this.setOption = 'Incident';
  }


  datatable = {
    title: 'Risk Incident',
    cols: [
      { title: 'ID', data: 'uniqueId' },
      { title: 'Incident Title', data: 'incidentTitle' },
      { title: 'Internal or External', data: 'internalorExternal' },
      { title: 'Owner', data: 'owner' },
      { title: 'Description', data: 'descriptionIncident' },
      { title: 'Event Occurrence Date', data: 'occurrence' },
      { title: 'Date of Detection', data: 'detection' },
      { title: 'Incident Close Date', data: 'incidentClose' },
      { title: 'Event Type', data: 'eventType.eventTypeName' },
      {
        title: 'Action', render: function (data: any, type: any, full: any) {
          return RiskEventComponent.self.createEditDeleteButton(full);
        }
      }
    ],
    rows: [],
    custominput: false,
    customcols: []
  };

  datatableGrossImpact = {
    title: 'Gross Impact',
    cols: [
      { title: 'ID', data: 'uniqueId' },
      { title: 'CCY', data: 'ccy.ccyName' },
      { title: 'Amount', data: 'amount' },
      { title: 'GL Reference', data: 'glReference' },
      { title: 'Impact Date', data: 'impactDate' },
      { title: 'Description', data: 'description' },
      {
        title: 'Action', render: function (data: any, type: any, full: any) {
          return RiskEventComponent.self.createEditDeleteButtonGrossImpact(full);
        }
      }
    ],
    rows: [],
    custominput: false,
    customcols: []
  };

  datatableRecovery = {
    title: 'Recovery',
    cols: [
      { title: 'ID', data: 'uniqueId' },
      { title: 'CCY', data: 'ccy.ccyName' },
      { title: 'Amount', data: 'amount' },
      { title: 'GL Reference', data: 'glReference' },
      { title: 'Impact Date', data: 'impactDate' },
      { title: 'Description', data: 'description' },
      {
        title: 'Action', render: function (data: any, type: any, full: any) {
          return RiskEventComponent.self.createEditDeleteButtonRecovery(full);
        }
      }
    ],
    rows: [],
    custominput: false,
    customcols: []
  };



  datatableDocument = {
    title: 'Document',
    cols: [
      { title: 'ID', data: 'uniqueId' },
      { title: 'File Name', data: 'fileName' },
      { title: 'Document Title', data: 'documentTitle' },
      { title: 'Document Owner', data: 'documentOwner' },
      {
        title: 'Action', render: function (data: any, type: any, full: any) {
          return RiskEventComponent.self.createEditDeleteButton(full);
        }
      }
    ],
    rows: [],
    custominput: false,
    customcols: []
  };

  grossImpactForm: FormGroup;
  grossImpactLoading = false;
  grossImpactSubmitted = false;

  fnSetVal(e, flag) {
    if (e.id) {
      this.riskEventForm.controls[flag].setValue(e.id);
    }
  }
  fnSetValRecovery(e, flag) {
    this.recoveriesForm.controls[flag].setValue(e.id);
  }
  fnSetValGrossDp(e, flag) {
    this.grossImpactForm.controls[flag].setValue(e.id);
  }
  get g() { return this.grossImpactForm.controls; }

  grossImpactId = '';
  mainpathGrossImpact = 'grossimpacts';

  getAllDataGrossImpact() {
    this.httpclientService.get(this.mainpathGrossImpact).pipe(first()).subscribe(
      data => {
        this.modifyJsonCount(data['content']);
        for (let index = 0; index < data['content'].length; index++) {
          data['content'][index]['impactDate'] = this.commonUtility.fnDateFormatter(data['content'][index]['impactDate']);
        }

        let responsecontent = data['content'];
        this.datatableGrossImpact.rows = responsecontent;
        if (isUndefined(this.tableComponentGrossImpact)) {
          this.tableComponentGrossImpact = new TableAppComponent();
        }
        this.tableComponentGrossImpact.customDatatableobj = this.datatableGrossImpact;
        this.tableComponentGrossImpact.rerender();
      },
      error => {
        this.alertService.error(error);
        this.grossImpactLoading = false;
      });
  }


  getAllDataRecoveries() {
    this.httpclientService.get(this.mainpathRecovery).pipe(first()).subscribe(
      data => {
        this.modifyJsonCount(data['content']);
        for (let index = 0; index < data['content'].length; index++) {
          data['content'][index]['impactDate'] = this.commonUtility.fnDateFormatter(data['content'][index]['impactDate']);
        }
        let responsecontent = data['content'];
        this.datatableRecovery.rows = responsecontent;
        if (isUndefined(this.tableComponentRecovery)) {
          this.tableComponentRecovery = new TableAppComponent();
        }
        this.tableComponentRecovery.customDatatableobj = this.datatableRecovery;
        this.tableComponentRecovery.rerender();
      },
      error => {
        this.alertService.error(error);
        this.recoveriesLoading = false;
      });
  }


  resetRootCauseForm() {
    $('#rootCause').val('');
  }
  onSubmitRootCause() {
    console.log('On Submit Root Cause == > ');
    this.rootCauseSubmitted = true;
    // stop here if form is invalid
    if (this.rootCauseForm.invalid) {
      return;
    }
    this.loading = true;
    this.httpclientService.post(this.rootCauseForm.value, 'rootcauses')
      .pipe(first())
      .subscribe(
        data => {
          this.rootCauseSubmitted = false;
          this.loading = false;
          this.alertService.successSaveUpdate('Successfully Saved');
          console.log('Data Saved Successfully');
          this.resetRootCauseForm();
        },
        error => {
          this.rootCauseSubmitted = false;
          this.alertService.error(error);
          this.loading = false;
        });

  }

  onSubmitGrossImpact() {
    this.grossImpactSubmitted = true;
    // stop here if form is invalid
    if (this.grossImpactForm.invalid) {
      return;
    }

    let flag = this.grossImpactId == '' ? 'save' : 'update';
    if (flag == 'save') {
      this.grossImpactForm.controls['uniqueId'].setValue(this.commonUtility.genRandom());
    }
    let saveUpdatePath = this.grossImpactId == '' ? this.mainpathGrossImpact : this.mainpathGrossImpact + '/' + this.grossImpactId;
    this.grossImpactLoading = true;

    this.httpclientService.saveOrUpdate(this.grossImpactForm.value, saveUpdatePath, flag)
      .pipe(first())
      .subscribe(
        data => {
          this.grossImpactLoading = false;
          this.alertService.successSaveUpdate('');
          this.fillFormGrossImpact('');
          this.getAllDataGrossImpact();
          this.cancelMenuGrossImpact();
        },
        error => {
          this.alertService.error(error);
          this.grossImpactLoading = false;
          this.cancelMenuGrossImpact();
        });
  }


  riskIncidentId = '';
  mainpath = 'riskincidents';
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
        for (let index = 0; index < data['content'].length; index++) {
          data['content'][index]['occurrence'] = this.commonUtility.fnDateFormatter(data['content'][index]['occurrence']);
          data['content'][index]['detection'] = this.commonUtility.fnDateFormatter(data['content'][index]['detection']);
          data['content'][index]['incidentClose'] = this.commonUtility.fnDateFormatter(data['content'][index]['incidentClose']);
        }
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

  getAllDataAdditionDocumentDetails() {
    this.httpclientService.get(this.mainPathAdditional).pipe(first()).subscribe(
      data => {
        this.modifyJsonCount(data['content']);
        let responsecontent = data['content'];
        this.datatableDocument.rows = responsecontent;
        if (isUndefined(this.tableComponentDocument)) {
          this.tableComponentDocument = new TableAppComponent();
        }
        this.tableComponentDocument.customDatatableobj = this.datatableDocument;
        this.tableComponentDocument.rerender();
      },
      error => {
        this.alertService.error(error);
        this.loading = false;
      });
  }

  fillForm(json) {
    console.log('Json Data :: =  >> ');
    console.log(json);
    this.riskIncidentId = this.commonUtility.checkVal(json['id']);

    this.drpInternalorExternalvalue = this.commonUtility.checkVal(json['internalorExternal']);
    this.drpReportabletoRegulatorvalue = this.commonUtility.checkVal(json['reportabletoRegulator']);
    this.drpLocationvalue = this.commonUtility.checkVal(json['location']);
    //this.drpRiskCategoryOnevalue = this.commonUtility.checkVal(json['riskCategoryOne']['riskCategoryLevelOneName']);
    //this.drpRiskCategoryOnevalue = this.commonUtility.checkVal(json['riskCategoryOne']['id']);
    //this.drpRiskCategoryTwovalue = this.commonUtility.checkVal(json['riskCategoryTwo']['riskCategoryLevelTwoName']);
    // console.log('DropDown ');
    // console.log('drpInternalorExternalvalue :: ' + this.drpInternalorExternalvalue);
    // console.log('drpReportabletoRegulatorvalue :: ' + this.drpReportabletoRegulatorvalue);
    // console.log('drpLocationvalue' + this.drpLocationvalue);
    // console.log('riskCategoryOne :: ' + this.drpRiskCategoryOnevalue);
    // console.log('riskCategoryTwo :: ' + this.drpRiskCategoryTwovalue);


    this.riskEventForm = this.formBuilder.group({
      id: [this.commonUtility.checkVal(this.riskIncidentId)],
      incidentTitle: [this.commonUtility.checkVal(json['incidentTitle'])],
      internalorExternal: [this.commonUtility.checkVal(json['internalorExternal'])],
      owner: [this.commonUtility.checkVal(json['owner'])],
      descriptionIncident: [this.commonUtility.checkVal(json['descriptionIncident'])],
      occurrence: [this.commonUtility.checkVal(json['occurrence'])],
      detection: [this.commonUtility.checkVal(json['detection'])],
      incidentClose: [this.commonUtility.checkVal(json['incidentClose'])],
      eventType: [this.commonUtility.checkVal(json['eventType'])],
      incidentReported: [this.commonUtility.checkVal(json['incidentReported'])],
      reportabletoRegulator: [this.commonUtility.checkVal(json['reportabletoRegulator'])],
      numberofPeople: [this.commonUtility.checkVal(json['numberofPeople'])],
      location: [this.commonUtility.checkVal(json['location'])],
      eventStatus: [this.commonUtility.checkVal(json['eventStatus'])],
      riskCategoryOne: [this.commonUtility.checkVal(json['riskCategoryOne'])],
      riskCategoryTwo: [this.commonUtility.checkVal(json['riskCategoryTwo'])],
      remediationPlan: [this.commonUtility.checkVal(json['remediationPlan'])],
      remediationActionOwner: [this.commonUtility.checkVal(json['remediationActionOwner'])],
      notes: [this.commonUtility.checkVal(json['notes'])],
      uniqueId: [this.commonUtility.checkVal(json['uniqueId'])]
    });

    if (this.commonUtility.checkVal(json)) {
      this.refilllDropdownvalue(json);
    } else {
      this.drpEventTypevalue = null;
      this.drpEventStatusvalue = null;
      this.drpRiskCategoryOnevalue = null;
      this.drpRiskCategoryTwovalue = null;
    }
  }

  makeDropdownValueForSimple(key, value) {
    let mainJson = {};
    mainJson['id'] = value;
    mainJson['description'] = value;
    return mainJson;
  }

  makeDropdownValue(json, key) {

    json = json[key];
    let mainJson = {};
    mainJson['id'] = json['id'];
    if (key == 'riskCategoryOne') {
      mainJson['description'] = json['riskCategoryLevelOneName'];
    }
    else if (key == 'riskCategoryTwo') {
      mainJson['description'] = json['riskCategoryLevelTwoName'];
    }
    else {
      mainJson['description'] = json[key + 'Name'];
    }

    return mainJson;
  }

  refilllDropdownvalue(json) {
    console.log('in Refill == > ');
    if (json.hasOwnProperty('eventType')) {
      this.drpEventTypevalue = this.makeDropdownValue(json, 'eventType');
    }
    if (json.hasOwnProperty('eventStatus')) {
      this.drpEventStatusvalue = this.makeDropdownValue(json, 'eventStatus');
      console.log('Event Status :: ');
      console.log(this.makeDropdownValue(json, 'eventStatus'));
    }
    if (json.hasOwnProperty('riskCategoryOne')) {
      this.drpRiskCategoryOnevalue = this.makeDropdownValue(json, 'riskCategoryOne');
      console.log('riskCategoryOne :: ');
      console.log(this.makeDropdownValue(json, 'riskCategoryOne'));
    }
    if (json.hasOwnProperty('riskCategoryTwo')) {
      this.drpRiskCategoryTwovalue = this.makeDropdownValue(json, 'riskCategoryTwo');
    }
    if (json.hasOwnProperty('ccy')) {
      this.drpCCYvalue = this.makeDropdownValue(json, 'ccy');
    }
  }

  get f() { return this.riskEventForm.controls; }
  onSubmit() {

    this.submitted = true;
    // stop here if form is invalid
    // if (this.riskEventForm.invalid) {
    //   return;
    // }
    this.loading = true;
    let flag = this.riskIncidentId == '' ? 'save' : 'update';
    if (flag == 'save') {
      this.riskEventForm.controls['uniqueId'].setValue(this.commonUtility.genRandom());
    }
    let saveUpdatePath = this.riskIncidentId == '' ? this.mainpath : this.mainpath + '/' + this.riskIncidentId;
    console.log('On Submit ' + saveUpdatePath);

    console.log(this.riskEventForm.value);

    this.httpclientService.saveOrUpdate(this.riskEventForm.value, saveUpdatePath, flag)
      .pipe(first())
      .subscribe(
        data => {
          this.cancelIncidentMenu();
          this.loading = false;
          this.alertService.successSaveUpdate('');
          this.fillForm('');
          this.getAllData();
          RiskEventComponent.self.setOption = 'Incident';
        },
        error => {
          this.alertService.error(error);
          this.loading = false;
          this.cancelIncidentMenu();
        });
  }

  recoveriesForm: FormGroup;
  recoveriesLoading = false;
  recoveriesSubmitted = false;

  recoveryId = '';

  get r() { return this.recoveriesForm.controls; }

  mainpathRecovery = 'recoveries';

  onSubmitRecoveries() {
    console.log('on Submit Recoveries');
    console.log(this.recoveriesForm.value);
    this.recoveriesSubmitted = true;
    // stop here if form is invalid
    if (this.recoveriesForm.invalid) {
      return;
    }
    this.recoveriesLoading = true;
    let flag = this.recoveryId == '' ? 'save' : 'update';
    if (flag == 'save') {
      this.recoveriesForm.controls['uniqueId'].setValue(this.commonUtility.genRandom());
    }
    let saveUpdatePath = this.recoveryId == '' ? this.mainpathRecovery : this.mainpathRecovery + '/' + this.recoveryId;

    this.httpclientService.saveOrUpdate(this.recoveriesForm.value, saveUpdatePath, flag)
      .pipe(first())
      .subscribe(
        data => {
          this.recoveriesLoading = false;
          this.alertService.successSaveUpdate('');
          this.fillFormRecovery('');
          this.getAllDataRecoveries();
          this.cancelMenurecoveries();
        },
        error => {
          this.alertService.error(error);
          this.recoveriesLoading = false;
          this.cancelMenurecoveries();
        });
  }

  additionalForm: FormGroup;
  additionalLoading = false;
  additionalSubmitted = false;

  get a() { return this.additionalForm.controls; }
  mainPathAdditional = 'additionaldetailss';
  additionalDocumentId = '';

  onSubmitAdditional() {
    this.additionalSubmitted = true;
    // stop here if form is invalid
    if (this.additionalForm.invalid) {
      return;
    }
    this.additionalLoading = true;
    let flag = this.additionalDocumentId == '' ? 'save' : 'update';
    if (flag == 'save') {
      this.additionalForm.controls['uniqueId'].setValue(this.commonUtility.genRandom());
    }
    let saveUpdatePath = this.additionalDocumentId == '' ? this.mainPathAdditional : this.mainPathAdditional + '/' + this.additionalDocumentId;
    this.httpclientService.saveOrUpdate(this.additionalForm.value, saveUpdatePath, flag)
      .pipe(first())
      .subscribe(
        data => {
          this.additionalLoading = false;
          this.alertService.successSaveUpdate('');
          this.getAllDataAdditionDocumentDetails();
          this.cancelDocument();
        },
        error => {
          this.alertService.error(error);
          this.additionalLoading = false;
          this.cancelDocument();
        });
  }

  navClick() {
    $('ul').on('click', 'a', function () {
      $('li a.active').removeClass('active');
      $(this).addClass('active');
    });
  }

  constructor(private formBuilder: FormBuilder,
    private userService: UserService,
    private commonUtility: CommonUtility,
    private httpclientService: HttpClientService,
    private alertService: AlertService) { }

  prepareDrpDownData(drpdownPaths) {
    drpdownPaths.forEach(element => {
      this.getSetDrpDownData(element);
    });
  }
  makeCustomDrpdownWithval(options, keyName) {
    let optionsMain = [];
    options.forEach(element => {
      let json = {};
      json['id'] = element[keyName.split('|')[1]];
      json['description'] = element[keyName.split('|')[2]];
      optionsMain.push(json);
    });
    return optionsMain;
  }
  getSetDrpDownData(path) {
    let maindrpdowndata = [];
    this.httpclientService.get(path.split('|')[0]).pipe(first()).subscribe(
      data => {
        maindrpdowndata = data['content'];
        if (path.split('|')[0] == 'eventtypes') {
          this.eventTypeOptions = this.makeCustomDrpdownWithval(maindrpdowndata, path);
        } else if (path.split('|')[0] == 'eventstatuses') {
          this.eventStatusOptions = this.makeCustomDrpdownWithval(maindrpdowndata, path);
        } else if (path.split('|')[0] == 'riskcategorylevelones') {
          this.riskCategoryOneOptions = this.makeCustomDrpdownWithval(maindrpdowndata, path);
        } else if (path.split('|')[0] == 'riskcategoryleveltwoes') {
          this.riskCategoryTwoOptions = this.makeCustomDrpdownWithval(maindrpdowndata, path);
        } else if (path.split('|')[0] == 'ccies') {
          this.CCYOptions = this.makeCustomDrpdownWithval(maindrpdowndata, path);
        }
      }
    );
  }
  fillFormGrossImpact(json) {
    this.grossImpactId = this.commonUtility.checkVal(json['id']);
    this.grossImpactForm = this.formBuilder.group({
      id: [this.commonUtility.checkVal(this.grossImpactId)],
      ccy: [this.commonUtility.checkVal(json['ccy']), Validators.required],
      amount: [this.commonUtility.checkVal(json['amount']), Validators.required],
      glReference: [this.commonUtility.checkVal(json['glReference']), Validators.required],
      impactDate: [this.commonUtility.checkVal(json['impactDate']), Validators.required],
      description: [this.commonUtility.checkVal(json['description']), Validators.required],
      uniqueId: [this.commonUtility.checkVal(json['uniqueId'])]
    });

    if (this.commonUtility.checkVal(json)) {
      this.refilllDropdownvalue(json);
    } else {
      this.drpCCYvalue = null;

    }
  }
  get root() { return this.rootCauseForm.controls; }
  fillFormRecovery(json) {
    this.recoveryId = this.commonUtility.checkVal(json['id']);
    this.recoveriesForm = this.formBuilder.group({
      id: [this.commonUtility.checkVal(this.recoveryId)],
      ccy: [this.commonUtility.checkVal(json['ccy']), Validators.required],
      amount: [this.commonUtility.checkVal(json['amount']), Validators.required],
      glReference: [this.commonUtility.checkVal(json['glReference']), Validators.required],
      impactDate: [this.commonUtility.checkVal(json['impactDate']), Validators.required],
      description: [this.commonUtility.checkVal(json['description']), Validators.required],
      uniqueId: [this.commonUtility.checkVal(json['uniqueId'])],
    });

    if (this.commonUtility.checkVal(json)) {
      this.refilllDropdownvalue(json);
    } else {
      this.drpCCYvalue = null;

    }
  }

  ngAfterViewInit() {
    this.getAllDataGrossImpact();

    this.getAllDataAdditionDocumentDetails();

  }

  ngOnInit() {
    this.getAllData();

    let drpdownPaths = ['eventtypes|id|eventTypeName', 'eventstatuses|id|eventStatusName',
      'riskcategorylevelones|id|riskCategoryLevelOneName', 'riskcategoryleveltwoes|id|riskCategoryLevelTwoName', 'ccies|id|ccyName'];
    this.prepareDrpDownData(drpdownPaths);
    RiskEventComponent.self = this;



    this.additionalForm = this.formBuilder.group({
      fileName: ['', Validators.required],
      documentTitle: ['', Validators.required],
      documentOwner: ['', Validators.required],
      attachment: ['', Validators.required],
      uniqueId: [''],
    });

    this.rootCauseForm = this.formBuilder.group({
      rootCauseName: ['', Validators.required]
    });



    this.grossImpactForm = this.formBuilder.group({
      ccy: ['', Validators.required],
      amount: ['', Validators.required],
      glReference: ['', Validators.required],
      impactDate: ['', Validators.required],
      description: ['', Validators.required],
      uniqueId: ['']
    });

    this.recoveriesForm = this.formBuilder.group({
      ccy: ['', Validators.required],
      amount: ['', Validators.required],
      glReference: ['', Validators.required],
      impactDate: ['', Validators.required],
      description: ['', Validators.required],
      uniqueId: ['']
    });

    this.riskEventForm = this.formBuilder.group({
      incidentTitle: ['', Validators.required],
      internalorExternal: ['', Validators.required],
      owner: ['', Validators.required],
      descriptionIncident: ['', Validators.required],
      occurrence: ['', Validators.required],
      detection: ['', Validators.required],
      incidentClose: ['', Validators.required],
      eventType: ['', Validators.required],
      incidentReported: ['', Validators.required],
      reportabletoRegulator: ['', Validators.required],
      numberofPeople: ['', Validators.required],
      location: ['', Validators.required],
      eventStatus: ['', Validators.required],
      riskCategoryOne: ['', Validators.required],
      riskCategoryTwo: ['', Validators.required],
      remediationPlan: ['', Validators.required],
      remediationActionOwner: ['', Validators.required],
      notes: ['', Validators.required],
      uniqueId: ['']
    });

    this.getAllDataRecoveries();
  }



}
