import { Component, OnInit, ViewChild } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { TableAppComponent } from '@app/table-app/table-app.component';
import { CommonUtility } from '@app/_services/common.service';
import { HttpClientService } from '@app/_services/httpClient.service';
import { AlertService } from '@app/_services';
import { isUndefined, isNullOrUndefined } from 'util';
import { first } from 'rxjs/operators';
import { RiskIncidentMenuComponent } from '@app/risk-incident-menu/risk-incident-menu.component';

@Component({
  selector: 'app-risk-incident',
  templateUrl: './risk-incident.component.html',
  styleUrls: ['./risk-incident.component.css']
})
export class RiskIncidentComponent implements OnInit {
  public static self: RiskIncidentComponent;
  public static incidentOption = 'search';
  public classReference = RiskIncidentComponent;

  datatableHideShow;
  riskIncidentForm: FormGroup;

  loading = false;
  submitted = false;
  mainpath = 'riskincidents';
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

  eventTypeOptions = [];
  eventTypeid = 'eventTypeName';
  drpEventTypevalue = null;
  eventTypeLabel = 'Event Type';

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


  IncidentShowHide;
  setOption = 'Incident';
  riskIncidentId = '';
  datatable = {
    title: 'Risk Incident',
    cols: [
      { title: 'ID', data: 'id' },
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
          return RiskIncidentComponent.self.createEditDeleteButton(full);
        }
      }
    ],
    rows: [],
    custominput: false,
    customcols: []
  };


  @ViewChild(TableAppComponent, { static: false })
  private tableComponent: TableAppComponent;
  constructor(private formBuilder: FormBuilder,
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
        }
      }
    );
  }

  changeIncidentMenu() {
    this.IncidentShowHide = 1;
    this.setOption = 'none';
  }
  fnReset() {
    this.occurrenceDate = '-1';
    $('#incidentTitleId').val('');
    $('#ownerId').val('');
    RiskIncidentMenuComponent.showOtherOption = false;
    sessionStorage.removeItem('fkRiskIncident');
    //sessionStorage.setItem('datatableHideShow', 'hide');
    //this.datatableHideShow = sessionStorage.getItem('datatableHideShow');
    this.getAllData();
  }

  fnDatatableHideShow() {
    sessionStorage.setItem('datatableHideShow', 'show');
    this.datatableHideShow = sessionStorage.getItem('datatableHideShow');
  }
  cancelIncidentMenu() {
    RiskIncidentComponent.incidentOption = 'search';
    this.IncidentShowHide = 0;
    this.setOption = 'Incident';
  }

  exportAsXLSX(): void {
    let createdjsonForexport = this.riskIncidentForm.value;
    if (this.riskIncidentForm.value.id != null) {
      this.fetchAlldataRiskIncident(this.riskIncidentForm.value.id);
    } else {
      this.commonUtility.exportExcel(this.riskIncidentForm.value, 'Risk_Incident');
    }
  }


  isKeyFK(jsonObj, key) {
    let mainJsonObj = jsonObj[key];
    if (typeof mainJsonObj == 'object') { 
      if (key == 'riskCategoryOne') {
        return mainJsonObj['riskCategoryLevelOneDescription'];
      } else if (key == 'riskCategoryTwo') {
        return mainJsonObj['riskCategoryLevelTwoDescription'];
      } else {
        return mainJsonObj[key + 'Description'];
      }  
    } else {
      return mainJsonObj;
    }
  }

  findFkAndreplace(element) {
    let mainJson = {};
    for (let key in element) { 
      mainJson[key] = this.isKeyFK(element, key);
    }
    return mainJson;
  }

  fetchAlldataRiskIncident(id) {
    let mainData = [];
    this.httpclientService.get(this.mainpath + '/search/fetchAlldataRiskIncident/' + id).pipe(first()).subscribe(
      data => {
        if (data != []) {
          var jsonArr = [];
          data.forEach(element => {
            var json = this.findFkAndreplace(element.riskIncident);
            delete element['riskIncident'];
            for (let key in element) {
              json[key] = this.isKeyFK(element,key);
            }
            jsonArr.push(json);
          });         
          this.commonUtility.exportExcel(jsonArr, 'Risk_Incident');
        } else {
          this.commonUtility.exportExcel(this.riskIncidentForm.value, 'Risk_Incident');
        }
      },
      error => {
        this.loading = false;
        return mainData;
      });
  }

  printAsPdf(): void {
    this.commonUtility.generatePdf(this.riskIncidentForm.value, 'Risk_Incident', this.commonUtility.printdownloadpdfflag);
  }

  canSave = false;
  createEditDeleteButton(fullData) {
    let mainId = fullData['id'];
    let editBtn = '';
    let deleteBtn = '';
    let userPermission = JSON.parse(localStorage.getItem('currentUser'))['permissions'];
    let mainpermissionJson = {};
    userPermission.forEach(element => {
      if (element.pageName == 'Risk_Incident') {
        mainpermissionJson = element;
      }
    });
    this.canSave = mainpermissionJson['canSave'];
    if (mainpermissionJson['canEdit']) {
      editBtn = '<i control="riskevent" class="fa fa-edit primary editcustom" value="' + mainId + '" style="margin-right: 14px;color: #ed5c0e;  font-size: 20px;font - weight: 500;cursor: pointer;" ></i>';
    }
    if (mainpermissionJson['canDelete']) {
      deleteBtn = '<i control="riskevent" class="fa fa-trash deletecustom" value="' + mainId + '" style="color: #ff001f;  font-size: 20px;font - weight: 500;cursor: pointer;" ></i>';
    }
    if (!mainpermissionJson['canEdit'] && !mainpermissionJson['canDelete']) {
      editBtn = '<span style="color:red"><b>No rights Avaliable</b></span>'
    }
    return editBtn + '&nbsp;' + deleteBtn;
  }

  // createEditDeleteButton(fullData) {
  //   let mainId = fullData['id'];
  //   let editBtn = '<i control="riskevent" class="fa fa-edit primary editcustom" value="' + mainId + '" style="margin-right: 14px;color: #ed5c0e;  font-size: 20px;font - weight: 500;cursor: pointer;" ></i>';
  //   let deleteBtn = '<i control="riskevent" class="fa fa-trash deletecustom" value="' + mainId + '" style="color: #ff001f;  font-size: 20px;font - weight: 500;cursor: pointer;" ></i>';
  //   return editBtn + '&nbsp;' + deleteBtn;
  // }

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

  fnCustomSearchSet(e, flag) {
    debugger
    if (flag == 'incidentTitle') {
      if (isUndefined(e) || isNullOrUndefined(e.target.value)) {
        this.mainCustomSearchJson['incidentTitle'] = '-1';
      } else {
        this.mainCustomSearchJson['incidentTitle'] = e.target.value;
      }
    }

    if (flag == 'occurrence') {
      console.log('In occurrence');
      if (isUndefined(e) || isNullOrUndefined(e.target.value)) {
        this.mainCustomSearchJson['occurrence'] = '-1';
      } else {
        const val = (String)(e.target.value);
        const updatedVal = val.substr(1, 10);
        console.log('Occurrence Date :: ' + val);
        this.mainCustomSearchJson['occurrence'] = updatedVal;
      }
    }

    if (flag == 'owner') {
      if (isUndefined(e) || isNullOrUndefined(e.target.value)) {
        this.mainCustomSearchJson['owner'] = '-1';
      } else {
        this.mainCustomSearchJson['owner'] = e.target.value;
      }
    }
  }
  mainCustomSearchJson = { 'incidentTitle': '-1', 'occurrence': '-1', 'owner': '-1' };

  isNUllSearchValue() {
    if (this.mainCustomSearchJson['incidentTitle'] == '-1' && this.mainCustomSearchJson['occurrence'] == '-1' && this.mainCustomSearchJson['owner'] == '-1') {

      return true;
    } else {
      return false;
    }

  }
  setvalueBeforeCheck() {
    if (this.mainCustomSearchJson['incidentTitle'] == '') {
      this.mainCustomSearchJson['incidentTitle'] = "-1";
    }
    if (this.mainCustomSearchJson['occurrence'] == '') {
      this.mainCustomSearchJson['occurrence'] = '-1';
    }
    if (this.mainCustomSearchJson['owner'] == '') {
      this.mainCustomSearchJson['owner'] = '-1';
    }
  }
  fnDateFormatter(event) {
    debugger;
    if (event) {
      const dt = new Date(event);
      let month;
      let date;
      if (dt.getMonth() + 1 <= 9) {
        month = '0' + (dt.getMonth() + 1);
      } else {
        month = (dt.getMonth() + 1);
      }
      if (dt.getDate() + 1 <= 9) {
        date = '0' + (dt.getDate());
      } else {
        date = (dt.getDate());
      }
      return dt.getFullYear() + '-' + month + '-' + date;
    } else {
      return '-1';
    }
  }

  //mainCustomSearchJson = { 'incidentTitle': 'ss', 'occurrence': '2020-01-15T18:30:00.000Z', 'owner': 'sss' };

  fnSearch() {

    this.fnDatatableHideShow();
    this.setvalueBeforeCheck();
    this.mainCustomSearchJson['occurrence'] = this.fnDateFormatter(this.occurrenceDate);
    console.log('---------------');
    console.log(JSON.stringify(this.mainCustomSearchJson));
    console.log('---------------');
    if (!this.isNUllSearchValue()) {
      console.log('fnSearch');
      console.log(JSON.stringify(this.mainCustomSearchJson));
      this.httpclientService.get(this.mainpath + '/search/customSearch/' + JSON.stringify(this.mainCustomSearchJson)).subscribe(
        data => {

          this.modifyJsonCount(data);
          console.log('Return Data');
          console.log('Search Methods');
          let responsecontent = data;
          console.log(responsecontent);

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
    } else {
      this.getAllData();
    }
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
        sessionStorage.setItem('flag', 'edit');
        RiskIncidentComponent.incidentOption = 'add';
        this.refilllDropdownvalue(data);
        this.IncidentShowHide = 1;
        this.setOption = 'none';
        RiskIncidentMenuComponent.showOtherOption = true;
      },
      error => {
        this.alertService.error(error, false);
        this.loading = false;
      });
  }


  fillForm(json) {
    this.riskIncidentId = this.commonUtility.checkVal(json['id']);
    this.drpInternalorExternalvalue = this.commonUtility.checkVal(json['internalorExternal']);
    this.drpReportabletoRegulatorvalue = this.commonUtility.checkVal(json['reportabletoRegulator']);
    this.drpLocationvalue = this.commonUtility.checkVal(json['location']);
    sessionStorage.setItem('fkRiskIncident', this.riskIncidentId);

    this.riskIncidentForm = this.formBuilder.group({
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
    if (json.hasOwnProperty('eventType')) {
      this.drpEventTypevalue = this.makeDropdownValue(json, 'eventType');
    }
    if (json.hasOwnProperty('eventStatus')) {
      this.drpEventStatusvalue = this.makeDropdownValue(json, 'eventStatus');
    }
    if (json.hasOwnProperty('riskCategoryOne')) {
      this.drpRiskCategoryOnevalue = this.makeDropdownValue(json, 'riskCategoryOne');
    }
    if (json.hasOwnProperty('riskCategoryTwo')) {
      this.drpRiskCategoryTwovalue = this.makeDropdownValue(json, 'riskCategoryTwo');
    }
  }
  occurrenceDate = '';
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
  fnSetVal(e, flag) {
    if (e.id) {
      var json = {};
      json['id'] = e.id;
      json['description'] = e.description;
      this.riskIncidentForm.controls[flag].setValue(json);
    }
  }
  ngOnInit() {

    if (sessionStorage.getItem('flag') == 'edit' && this.classReference.incidentOption == 'add') {
      this.fnEdit(sessionStorage.getItem('fkRiskIncident'));
    }
    this.datatableHideShow = sessionStorage.getItem('datatableHideShow');
    if (this.datatableHideShow == 'show') {
      this.getAllData();
    }
    this.canSave = this.httpclientService.cheackCanSavePermission('Risk_Incident');

    let drpdownPaths = ['eventtypes|id|eventTypeName', 'eventstatuses|id|eventStatusName',
      'riskcategorylevelones|id|riskCategoryLevelOneName', 'riskcategoryleveltwoes|id|riskCategoryLevelTwoName', 'ccies|id|ccyName'];
    this.prepareDrpDownData(drpdownPaths);
    RiskIncidentComponent.self = this;

    this.riskIncidentForm = this.formBuilder.group({
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
  }

  get f() { return this.riskIncidentForm.controls; }
  jsonMap(json) {
    json['internalorExternal'] = json['internalorExternal']['id'];
    json['reportabletoRegulator'] = json['reportabletoRegulator']['id'];
    json['eventStatus'] = { "id": json['eventStatus']['id'] };
    json['eventType'] = { "id": json['eventType']['id'] };
    json['riskCategoryOne'] = { "id": json['riskCategoryOne']['id'] };
    json['riskCategoryTwo'] = { "id": json['riskCategoryTwo']['id'] };
    json['internalorExternal'] = json['internalorExternal']['id'];
    json['reportabletoRegulator'] = json['reportabletoRegulator']['id'];    
    return json;
  }

  onSubmit() {
    this.submitted = true;
    //stop here if form is invalid
    if (this.riskIncidentForm.invalid) {
      return;
    }
    this.loading = true;
    let flag = this.riskIncidentId == '' ? 'save' : 'update';
    if (flag == 'save') {
      this.riskIncidentForm.controls['uniqueId'].setValue(this.commonUtility.genRandom());
    }

    let saveUpdatePath = this.riskIncidentId == '' ? this.mainpath : this.mainpath + '/' + this.riskIncidentId;
    this.httpclientService.saveOrUpdate(this.jsonMap(this.riskIncidentForm.value), saveUpdatePath, flag)
      .pipe(first())
      .subscribe(
        data => {
          console.log('===>> save or update =>>');
          sessionStorage.setItem('fkRiskIncident', data['id']);
          this.cancelIncidentMenu();
          this.loading = false;
          this.alertService.successSaveUpdate('');
          this.fillForm('');

          this.getAllData();
          this.datatableHideShow = 'show';
          RiskIncidentComponent.self.setOption = 'Incident';
          RiskIncidentComponent.incidentOption = 'search';
          RiskIncidentMenuComponent.showOtherOption = false;
          sessionStorage.setItem('flag', 'save');
        },
        error => {
          this.alertService.error(error);
          this.loading = false;
          this.cancelIncidentMenu();
        });
  }
}
