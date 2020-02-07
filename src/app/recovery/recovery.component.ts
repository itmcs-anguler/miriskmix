import { Component, OnInit, ViewChild } from '@angular/core';
import { TableAppComponent } from '@app/table-app/table-app.component';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { CommonUtility } from '@app/_services/common.service';
import { HttpClientService } from '@app/_services/httpClient.service';
import { AlertService } from '@app/_services';
import { isUndefined } from 'util';
import { first } from 'rxjs/operators';

@Component({
  selector: 'app-recovery',
  templateUrl: './recovery.component.html',
  styleUrls: ['./recovery.component.css']
})
export class RecoveryComponent implements OnInit {
  public static self: RecoveryComponent;
  recoveriesForm: FormGroup;
  recoveriesShowHide;
  recoveries = 1;
  drpCCYvalue = null;

  CCYOptions = [];
  CCYLabel = 'CCY';
  CCYid = 'CCCYName';
  loading = false;
  recoveryId = '';

  riskIncidentId;
  mainpathRecovery = 'recoveries';

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
          return RecoveryComponent.self.createEditDeleteButtonGrossImpact(full);
        }
      }
    ],
    rows: [],
    custominput: false,
    customcols: []
  };

  constructor(private formBuilder: FormBuilder,
    private commonUtility: CommonUtility,
    private httpclientService: HttpClientService,
    private alertService: AlertService) { }

  @ViewChild(TableAppComponent, { static: false })
  private tableComponentRecovery: TableAppComponent;
  makecustomJson;

  exportAsXLSX(): void {
    this.commonUtility.exportExcel(this.recoveriesForm.value, 'Recoveries_Impact');
  }

  printAsPdf(): void {
    this.commonUtility.generatePdf(this.recoveriesForm.value, 'Recoveries_Impact', this.commonUtility.printdownloadpdfflag);
  }

  canSave = false;
  createEditDeleteButtonGrossImpact(fullData) {
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
      editBtn = '<i control="recovery" class="fa fa-edit primary editcustom" value="' + mainId + '" style="margin-right: 14px;color: #ed5c0e;  font-size: 20px;font - weight: 500;cursor: pointer;" ></i>';
    }
    if (mainpermissionJson['canDelete']) {
      deleteBtn = '<i control="recovery" class="fa fa-trash deletecustom" value="' + mainId + '" style="color: #ff001f;  font-size: 20px;font - weight: 500;cursor: pointer;" ></i>';
    }
    if (!mainpermissionJson['canEdit'] && !mainpermissionJson['canDelete']) {
      editBtn = '<span style="color:red"><b>No rights Avaliable</b></span>'
    }
    return editBtn + '&nbsp;' + deleteBtn;
  }
  // createEditDeleteButtonRecovery(fullData) {
  //   let mainId = fullData['id'];
  //   let editBtn = '<i control="recovery" class="fa fa-edit primary editcustom" value="' + mainId + '" style="margin-right: 14px;color: #ed5c0e;  font-size: 20px;font - weight: 500;cursor: pointer;" ></i>';
  //   let deleteBtn = '<i control="recovery" class="fa fa-trash deletecustom" value="' + mainId + '" style="color: #ff001f;  font-size: 20px;font - weight: 500;cursor: pointer;" ></i>';
  //   return editBtn + '&nbsp;' + deleteBtn;
  // }
  changeMenuRecoveryImpact() {
    this.recoveriesShowHide = 1;
    if (this.recoveries = 0) {
      this.recoveries = 1;
    } else {
      this.recoveries = 0;
    }
  }
  
  modifyJsonCount(jsonArr) {
    let count = 0;
    jsonArr.forEach(element => {
      count++;
      element['count'] = count;
    });
  }

  refilllDropdownvalue(json) {
    if (json.hasOwnProperty('ccy')) {
      this.drpCCYvalue = this.makeDropdownValue(json, 'ccy');
    }
  }
  makeDropdownValue(json, key) {

    json = json[key];
    let mainJson = {};
    mainJson['id'] = json['id'];
    mainJson['description'] = json[key + 'Name'];
    return mainJson;
  }

  prepareDrpDownData(drpdownPaths) {
    drpdownPaths.forEach(element => {
      this.getSetDrpDownData(element);
    });
  }
  getSetDrpDownData(path) {
    let maindrpdowndata = [];
    this.httpclientService.get(path.split('|')[0]).pipe(first()).subscribe(
      data => {
        maindrpdowndata = data['content'];
        if (path.split('|')[0] == 'ccies') {
          this.CCYOptions = this.makeCustomDrpdownWithval(maindrpdowndata, path);
        }
      }
    );
  }
  cancelMenurecoveries() {
    this.recoveriesShowHide = 0;
    this.recoveries = 1;

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
  get r() { return this.recoveriesForm.controls; }

  fnSetValGrossDp(e, flag) {
    this.recoveriesForm.controls[flag].setValue(e.id);
  }
  updatedate = [];
  filterDatabyId(data) {
    console.log('filterDatabyId==============>');
    data.forEach(element => {
      if (element['riskIncident']['id'] == this.riskIncidentId) {
        this.updatedate.push(element);
        console.log(element);
      }
    });
    return this.updatedate;
  }


  getAllDataRecoveries() {
    this.httpclientService.get(this.mainpathRecovery + '/search/findbyRiskincidentId/' + JSON.stringify(this.makecustomJson)).subscribe(
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
        this.loading = false;
      });
  }
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
      riskIncident: [this.commonUtility.checkVal(json['riskIncident'])]
    });

    if (this.commonUtility.checkVal(json)) {
      this.refilllDropdownvalue(json);
    } else {
      this.drpCCYvalue = null;

    }
  }
  fnSetValRecovery(e, flag) {
    var json = {};
    json['id'] = e.id;
    json['description'] = e.description;
    this.recoveriesForm.controls[flag].setValue(json);
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

  jsonMap(json) {
    let ccyId = json['ccy']['id'];
    let updated = { "id": ccyId };
    json['ccy'] = updated;
    return json;
  }

  recoveriesLoading = false;
  recoveriesSubmitted = false;
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
      this.recoveriesForm.controls['riskIncident'].setValue({ id: this.riskIncidentId });
    }
    let saveUpdatePath = this.recoveryId == '' ? this.mainpathRecovery : this.mainpathRecovery + '/' + this.recoveryId;

    this.httpclientService.saveOrUpdate(this.jsonMap(this.recoveriesForm.value), saveUpdatePath, flag)
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

  ngOnInit() {
    this.canSave = this.httpclientService.cheackCanSavePermission('Risk_Incident');
    this.riskIncidentId = sessionStorage.getItem('fkRiskIncident');
    this.makecustomJson = { 'riskIncidentId': this.riskIncidentId };
    console.log('Fk Risk Incident : ' + sessionStorage.getItem('fkRiskIncident'));
    let drpdownPaths = ['ccies|id|ccyName'];
    this.prepareDrpDownData(drpdownPaths);
    this.getAllDataRecoveries();
    RecoveryComponent.self = this;
    this.recoveriesForm = this.formBuilder.group({
      ccy: ['', Validators.required],
      amount: ['', Validators.required],
      glReference: ['', Validators.required],
      impactDate: ['', Validators.required],
      description: ['', Validators.required],
      uniqueId: [''],
      riskIncident: [this.riskIncidentId]
    });
  }

}
