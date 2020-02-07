import { Component, OnInit, ViewChild } from '@angular/core';
import { isUndefined, isNullOrUndefined } from 'util';
import { TableAppComponent } from '@app/table-app/table-app.component';
import { FormBuilder } from '@angular/forms';
import { AuthenticationService, AlertService } from '@app/_services';
import { CommonUtility } from '@app/_services/common.service';
import { HttpClientService } from '@app/_services/httpClient.service';
import { first } from 'rxjs/operators';

@Component({
  selector: 'app-auditlog',
  templateUrl: './auditlog.component.html',
  styleUrls: ['./auditlog.component.css']
})
export class AuditlogComponent implements OnInit {

  public static self: AuditlogComponent;

  @ViewChild(TableAppComponent, { static: false })
  private tableComponent: TableAppComponent;
  loading = false;
  submitted = false;
  returnUrl: string;
  setOption = 'Grid';
  operation = 'Add';
  mainHead: string = "Audit Log";
  datatable = {
    title: 'Audit Log',
    cols: [
      { title: 'ID', data: 'id' },
      { title: 'Login User Name', data: 'loginUserName.userName' },
      { title: 'Ip Address', data: 'ipAddress' },
      { title: 'Browswer', data: 'browser' },
      { title: 'Action Type', data: 'actionType' },
      { title: 'Module Name', data: 'moduleName' },
      { title: 'Status', data: 'status' }
    ],
    rows: [],
    custominput: false,
    customcols: []
  };

  moduleLabel = 'Module Name';
  moduleLabelClass = 'lblgrey18bold';
  drpModulevalueCustomSearch = null;
  drpLoginUservalueCustomSearch = null;
  moduleid = 'ModuleName';
  moduleOptions = ['Risk_Incident', 'MIS', 'Risk_Register', 'Asset_Register', 'Document', 'Manage_Role', 'Manage_User', 'Manage_Permission'
    , 'Category', 'CCY', 'Residual_Risk_Likelihood', 'Risk_Status', 'Event_Type', 'Event_Status', 'Risk_Category_Level_1', 'Risk_Category_Level_2',
    'Residual_Impact_Assessment', 'Inherent_Risk_Likelihood', 'Inherent_Impact_Assessment'];
  preparedmoduleOptions = ['RISKINCIDENTS', 'MIS', 'RISKREGISTERS', 'ASSETREGISTERS', 'DOCUMENTS', 'MANAGEROLES', 'MANAGEUSERS', 'MANAGEPERMISSIONS'
    , 'CATEGORIES', 'CCIES', 'RESIDUALRISKLIKELIHOODS', 'RISKSTATUSES', 'EVENTTYPES', 'EVENTSTATUSES', 'RISKCATEGORYLEVELONES', 'RISKCATEGORYLEVELTWOES',
    'RESIDUALIMPACTASSESSMENTS', 'INHERENTRISKLIKELIHOODS', 'INHERENTIMPACTASSESSMENTS'];

  loginUserLabel = 'Login User Name';
  loginUserid = 'loginUserName';
  loginUserOptions = [];
  preparedloginUserOptions = [];





  constructor(private formBuilder: FormBuilder,
    private authenticationService: AuthenticationService,
    private commonUtility: CommonUtility,
    private httpclientService: HttpClientService,
    private alertService: AlertService) { }

  mainCustomSearchJson = { 'moduleName': 0, 'statusId': 0, 'riskOwnername': "" };



  fnCustomSearchSet(e, flag) {
    debugger;
    if (flag == 'moduleCustomSearch') {
      if (isUndefined(e) || isNullOrUndefined(e)) {
        this.mainCustomSearchJson['moduleName'] = 0;
        this.drpModulevalueCustomSearch = null;
      } else {
        this.mainCustomSearchJson['moduleName'] = e.id;
        this.drpModulevalueCustomSearch = e;
      }
    }

    if (flag == 'loginUserCustomSearch') {
      if (isUndefined(e) || isNullOrUndefined(e.description)) {
        this.mainCustomSearchJson['loginUserName'] = { id: null };
        this.drpLoginUservalueCustomSearch = null;
      } else {
        this.mainCustomSearchJson['loginUserName'] = { id: e.id };
        this.drpLoginUservalueCustomSearch = e;
      }
    }

    // if (flag == 'ownerCustomSearch') {

    // if (isUndefined(e) || isNullOrUndefined(e.target.value)) {
    // this.mainCustomSearchJson['riskOwnername'] = "";
    // } else {
    // this.mainCustomSearchJson['riskOwnername'] = e.target.value;
    // }

    // }
  }
  fromTime: string = null;
  toTime: string = null;
  fnSearch() {
    debugger;
    let json = this.prepareJsonForSearch();
    let result = this.checkJsonIsNullOrNot(json);
    console.log(json);
    let newjson = {};


    if (result) {
      this.httpclientService.post(json, 'auditlogsbins/search/findByModuleNameOrLoginUserNameIdOrCreationDateBetween').pipe(first()).subscribe(
        data => {
          debugger;
          this.modifyJsonCount(data);
          let rowArray;
          rowArray = data;
          this.datatable.rows = rowArray;
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
      this.getAuditGridData();
    }

  }
  fnDateFormatter(event) {
    if (event) {
      const dt = new Date(event);
      return dt.getFullYear() + '-' + (dt.getMonth() + 1) + '-' + dt.getDate() + ' ' + dt.getHours() + ':' + dt.getMinutes() + ':' + dt.getSeconds();
    } else {
      return '';
    }
  }
  checkJsonIsNullOrNot(json) {
    let reuslt = true;
    if (json['moduleName'] == 0 && json['loginUserName'] == 0
      && json['fromTime'] == 0 && json['toTime'] == 0) {
      reuslt = false;
    }
    return reuslt;
  }
  prepareJsonForSearch() {
    debugger;
    let json = {};

    if (isNullOrUndefined(this.fromTime)) {
      json['fromTime'] = 0;
    } else {
      json['fromTime'] = this.fnDateFormatter(this.fromTime);
    }
    if (isNullOrUndefined(this.toTime)) {
      json['toTime'] = 0;
    } else {
      json['toTime'] = this.fnDateFormatter(this.toTime);
    }
    if (this.drpModulevalueCustomSearch.length == 0) {
      json['moduleName'] = 0;
    } else {
      json['moduleName'] = this.drpModulevalueCustomSearch;
    }
    if (isNullOrUndefined(this.drpLoginUservalueCustomSearch)) {
      json['loginUserName'] = 0;
    } else {
      json['loginUserName'] = this.drpLoginUservalueCustomSearch.id;
    }
    return json;
  }
  preparecoldataforExample() {
    let finalarry: any[] = [];
    for (var j = 0; j < 10; j++) {
      let mainarry = {};
      for (var i = 0; i < this.datatable.cols.length; i++) {
        const mainvalue = this.datatable.cols[i];
        let tempstring;

        if (mainvalue.data == 'browser') {
          let tempVal = ['Chrome', 'Firefox', 'Bing', 'Internet Explorer', 'Chrome', 'Chrome', 'Firefox', 'Bing', 'Internet Explorer', 'Chrome'];
          tempstring = tempVal[j];
        } else if (mainvalue.data == 'actionType') {
          let tempVal = ['Save', 'Edit', 'Delete', 'Update', 'Save', 'Save', 'Edit', 'Delete', 'Update', 'Save'];
          tempstring = tempVal[j];
        } else if (mainvalue.data == 'moduleName') {
          let tempVal = ['Risk Register', 'Risk Incident', 'Asset Register', 'Document', 'Manage User', 'Risk Register', 'Risk Incident', 'Asset Register', 'Document', 'Manage User'];
          tempstring = tempVal[j];
        } else if (mainvalue.data == 'Status') {
          let tempVal = ['In progress', 'Complete', 'Failed', 'Complete', 'Complete', 'In progress', 'Complete', 'Failed', 'Complete', 'Complete'];
          tempstring = tempVal[j];
        } else if (mainvalue.data == 'ipAddress') {
          let tempVal = ['192.168.0.1', '192.168.0.85', '192.168.0.60', '192.168.0.89', '192.168.0.55', '192.168.0.1', '192.168.0.85', '192.168.0.60', '192.168.0.89', '192.168.0.55'];
          tempstring = tempVal[j];
        } else if (mainvalue.data == 'id') {
          tempstring = j + 1;
        } else {
          tempstring = mainvalue.title + ' ' + (j + 1);
        }
        mainarry[mainvalue.data] = tempstring;
      }
      finalarry.push(mainarry);
    }
    return finalarry;
  }

  ngOnInit() {
    AuditlogComponent.self = this;
    this.getAuditGridData();
    let drpdownPaths = ['manageusers|id|userName'];
    this.prepareDrpDownData(drpdownPaths);

    // this.preparecoldataforExample();


  }
  prepareDrpDownData(drpdownPaths) {
    debugger;
    drpdownPaths.forEach(element => {
      debugger;
      this.getSetDrpDownData(element);
    });
  }
  getSetDrpDownData(path) {
    debugger;
    let maindrpdowndata = [];
    this.httpclientService.get(path.split('|')[0]).pipe(first()).subscribe(
      data => {
        debugger;
        maindrpdowndata = data['content'];
        if (path.split('|')[0] == 'manageusers') {
          debugger;
          this.preparedloginUserOptions = this.makeCustomDrpdownWithval(maindrpdowndata, path);
        }
      }
    );
  }
  makeCustomDrpdownWithval(options, keyName) {
    debugger;
    let optionsMain = [];
    options.forEach(element => {
      let json = {};
      json['id'] = element[keyName.split('|')[1]];
      json['description'] = element[keyName.split('|')[2]];
      optionsMain.push(json);
    });
    return optionsMain;
  }
  getAuditGridData() {
    debugger;
    this.httpclientService.get("auditlogsbins").pipe(first()).subscribe(
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

}