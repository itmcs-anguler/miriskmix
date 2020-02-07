import { Component, OnInit, ViewChild } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { CommonUtility } from '@app/_services/common.service';
import { HttpClientService } from '@app/_services/httpClient.service';
import { AlertService } from '@app/_services';
import { first } from 'rxjs/operators';
import { isUndefined } from 'util';
import { TableAppComponent } from '@app/table-app/table-app.component';

@Component({
  selector: 'app-gross-impact',
  templateUrl: './gross-impact.component.html',
  styleUrls: ['./gross-impact.component.css']
})
export class GrossImpactComponent implements OnInit {
  public static self: GrossImpactComponent;
  setOption = 'Incident';
  grossImpactForm: FormGroup;
  grossImpactLoading = false;
  grossImpactSubmitted = false;
  grossImpactId = '';
  mainpathGrossImpact = 'grossimpacts';
  riskIncidentId;
  grossImpactFormShow;
  drpCCYvalue = null;
  CCYOptions = [];
  CCYLabel = 'CCY';
  CCYid = 'CCCYName';
  grossImpact = 1;
  loading = false;
  @ViewChild(TableAppComponent, { static: false })
  private tableComponentGrossImpact: TableAppComponent;
  constructor(
    private formBuilder: FormBuilder,
    private commonUtility: CommonUtility,
    private httpclientService: HttpClientService,
    private alertService: AlertService
  ) { }

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
          return GrossImpactComponent.self.createEditDeleteButtonGrossImpact(full);
        }
      }
    ],
    rows: [],
    custominput: false,
    customcols: []
  };

  exportAsXLSX(): void {
    this.commonUtility.exportExcel(this.grossImpactForm.value, 'Gross_Impact');
  }

  printAsPdf(): void {
    this.commonUtility.generatePdf(this.grossImpactForm.value, 'Gross_Impact', this.commonUtility.printdownloadpdfflag);
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
      editBtn = '<i control="grossImpact" class="fa fa-edit primary editcustom" value="' + mainId + '" style="margin-right: 14px;color: #ed5c0e;  font-size: 20px;font - weight: 500;cursor: pointer;" ></i>';
    }
    if (mainpermissionJson['canDelete']) {
      deleteBtn = '<i control="grossImpact" class="fa fa-trash deletecustom" value="' + mainId + '" style="color: #ff001f;  font-size: 20px;font - weight: 500;cursor: pointer;" ></i>';
    }
    if (!mainpermissionJson['canEdit'] && !mainpermissionJson['canDelete']) {
      editBtn = '<span style="color:red"><b>No rights Avaliable</b></span>'
    }
    return editBtn + '&nbsp;' + deleteBtn;
  }
  // createEditDeleteButtonGrossImpact(fullData) {
  //   let mainId = fullData['id'];
  //   let editBtn = '<i control="grossImpact" class="fa fa-edit primary editcustom" value="' + mainId + '" style="margin-right: 14px;color: #ed5c0e;  font-size: 20px;font - weight: 500;cursor: pointer;" ></i>';
  //   let deleteBtn = '<i control="grossImpact" class="fa fa-trash deletecustom" value="' + mainId + '" style="color: #ff001f;  font-size: 20px;font - weight: 500;cursor: pointer;" ></i>';
  //   return editBtn + '&nbsp;' + deleteBtn;
  // }

  changeMenuGrossImpact() {
    this.grossImpactFormShow = 1;
    this.grossImpact = 0;
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


  modifyJsonCount(jsonArr) {
    let count = 0;
    jsonArr.forEach(element => {
      count++;
      element['count'] = count;
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



  fnSetValGrossDp(e, flag) {
    var json = {};
    json['id'] = e.id;
    json['description'] = e.description;
    this.grossImpactForm.controls[flag].setValue(json);
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
      uniqueId: [this.commonUtility.checkVal(json['uniqueId'])],
      riskIncident: [this.commonUtility.checkVal(json['riskIncident'])]
    });

    if (this.commonUtility.checkVal(json)) {
      this.refilllDropdownvalue(json);
    } else {
      this.drpCCYvalue = null;

    }
  }

  jsonMap(json) {
    let ccyId = json['ccy']['id'];
    let updated = { "id": ccyId };
    json['ccy'] = updated;
    return json;
  }
  onSubmitGrossImpact() {
    console.log(this.grossImpactForm.value);
    console.log('Updated ');
    console.log(this.jsonMap(this.grossImpactForm.value));
    this.grossImpactSubmitted = true;
    // stop here if form is invalid
    if (this.grossImpactForm.invalid) {
      return;
    }

    let flag = this.grossImpactId == '' ? 'save' : 'update';
    if (flag == 'save') {
      this.grossImpactForm.controls['uniqueId'].setValue(this.commonUtility.genRandom());
    }
    this.grossImpactForm.controls['riskIncident'].setValue({ id: this.riskIncidentId });
    console.log('==>>>');
    console.log(this.grossImpactForm.value);
    let saveUpdatePath = this.grossImpactId == '' ? this.mainpathGrossImpact : this.mainpathGrossImpact + '/' + this.grossImpactId;
    this.grossImpactLoading = true;

    this.httpclientService.saveOrUpdate(this.jsonMap(this.grossImpactForm.value), saveUpdatePath, flag)
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
  cancelMenuGrossImpact() {
    this.grossImpactFormShow = 0;
    this.grossImpact = 1;

  }



  getAllDataGrossImpact() {
    this.httpclientService.get(this.mainpathGrossImpact + '/search/findbyRiskIncident/' + JSON.stringify(this.mainCustomSearchJson)).subscribe(
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
  get g() { return this.grossImpactForm.controls; }
  mainCustomSearchJson
  ngOnInit() {
    this.canSave = this.httpclientService.cheackCanSavePermission('Risk_Incident');
    this.riskIncidentId = sessionStorage.getItem('fkRiskIncident');
    this.mainCustomSearchJson = { 'riskIncidentId': this.riskIncidentId };
    this.getAllDataGrossImpact();
    let drpdownPaths = ['ccies|id|ccyName'];
    this.prepareDrpDownData(drpdownPaths);
    GrossImpactComponent.self = this;
    this.grossImpactForm = this.formBuilder.group({
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
