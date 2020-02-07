import { Component, OnInit, ViewChild, Self } from '@angular/core';
import { UserService, AlertService } from '@app/_services';
import { FormBuilder, Validators, FormGroup, FormControl } from '@angular/forms';
import { first } from 'rxjs/operators';
import { HttpClientService } from '@app/_services/httpClient.service';
import { isUndefined, isNull, isNullOrUndefined } from 'util';
import { TableAppComponent } from '@app/table-app/table-app.component';
import { CommonUtility } from '@app/_services/common.service';

@Component({
  selector: 'app-risk-register',
  templateUrl: './risk-register.component.html',
  styleUrls: ['./risk-register.component.css']
})
export class RiskRegisterComponent implements OnInit {
  public static self: RiskRegisterComponent;
  public static riskRegisterOption = 'search';
  classReference = RiskRegisterComponent;

  riskRegisterForm: FormGroup;
  riskregisterid = '';
  mainpath = 'riskregisters';
  loading = false;
  submitted = false;
  returnUrl: string;
  setOption = 'Grid';
  operation = 'Add';
  datatable = {
    title: 'Risk Register',
    cols: [
      { title: 'ID', data: 'count' },
      // { title: 'ID', data: 'id' },
      { title: 'Category', data: 'category.categoryName' },
      { title: 'Inherent Risk Likelihood', data: 'inheritRiskLikelihood.inherentRiskLikelihoodName' },
      { title: 'Risk Assessment Score', data: 'inheritRiskAssessmentScore' },
      { title: 'Risk Owner', data: 'riskOwner' },
      { title: 'Residual Risk Likelihood', data: 'residualRiskLikelihood.residualRiskLikelihoodName' },
      { title: 'Current Controls', data: 'currentControls' },
      { title: 'Further Action', data: 'furthurAction' },
      { title: 'Risk Status', data: 'riskStatus.riskStatusName' },
      {
        title: 'Action', render: function (data: any, type: any, full: any) {
          return RiskRegisterComponent.self.createEditDeleteButton(full);
        }
      }
    ],
    rows: [],
    custominput: false,
    customcols: []
  };
  mainCustomSearchJson = { 'categoryId': -1, 'statusId': -1, 'riskOwnername': "-1" };
  @ViewChild(TableAppComponent, { static: false })
  private tableComponent: TableAppComponent;

  categoryLabel = 'Risk Category Name';
  categoryLabelClass = 'lblgrey18bold';
  categoryid = 'categoryid';
  selectedvalue = null;
  drpresidualImpactAssessmentvalue = null;
  drpresidualRiskLikelihoodvalue = null;
  drpinherentImpactAssessmentvalue = null;
  drpinheritRiskLikelihoodvalue = null;
  drpcatgoryvalue = null;
  drpcatgoryvalueCustomSearch = null;
  drpriskStatusvalue = null;
  drpriskStatusCustomSearch = null;


  riskOwnerLabel = 'Risk Owner Name';
  riskOwnerLabelClass = 'lblgrey18bold';
  riskOwnerid = 'riskownerid';

  riskStatusLabel = 'Risk Status';
  riskStatusLabelClass = 'lblgrey18bold';
  riskStatusid = 'riskstatusid';

  inherentRiskLikelihood = 'Inherent Risk Likelihood';
  inherentRiskLikelihoodid = 'inherentRiskLikelihoodid';

  inherentImpactAssessment = 'Inherent Impact Assessment';
  inherentImpactAssessmentid = 'inherentImpactAssessmentid';

  residualRiskLikelihood = 'Residual Risk Likelihood';
  residualRiskLikelihoodid = 'residualRiskLikelihoodid';

  residualImpactAssessment = 'Residual Impact Assessment';
  residualImpactAssessmentid = 'residualImpactAssessmentid';

  preparedcategoryOptions = [];
  preparedriskOwnerOptions = [];
  preparedriskStatusOptions = [];
  preparedRiskLikelihoodOptions = [];
  preparedImpactAssessmentOptions = [];
  preparedInherentRiskLikelihoodOptions = [];
  preparedInherentImpactAssessmentOptions = [];
  datableShowHide = false;
  getSessionId;
  fnDatableShowHide() {
    this.datableShowHide = true;
  }
  canSave = false;
  createEditDeleteButton(fullData) {
    let mainId = fullData['id'];
    let editBtn = '';
    let deleteBtn = '';
    let userPermission = JSON.parse(localStorage.getItem('currentUser'))['permissions'];
    let mainpermissionJson = {};
    userPermission.forEach(element => {
      if (element.pageName == 'Risk_Register') {
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
  constructor(private formBuilder: FormBuilder,
    private alertService: AlertService,
    private commonUtility: CommonUtility,
    private httpclientService: HttpClientService) { }

  changeMenu(flag) {
    this.setOption = flag;
    RiskRegisterComponent.riskRegisterOption = 'search';
    if (flag == 'Add') {
      this.fillForm('');
      RiskRegisterComponent.riskRegisterOption = 'add';
    }
  }

  exportAsXLSX(): void {
    this.commonUtility.exportExcel(this.riskRegisterForm.value, 'Risk_Register');
  }

  printAsPdf(): void {
    var json = this.riskRegisterForm.value;
    this.commonUtility.generatePdf(json, 'Risk_Register', this.commonUtility.printdownloadpdfflag);
  }

  ngOnInit() {
    this.canSave = this.httpclientService.cheackCanSavePermission('Risk_Register');
    this.getSessionId = sessionStorage.getItem('riskRegisterId');
    if (this.getSessionId) {
      this.fnEdit(this.getSessionId);
    }
    sessionStorage.removeItem('riskRegisterId');
    //this.inheritRiskAssessmentScore = 5;
    //this.getAllData();
    this.fillForm('');

    let drpdownPaths = ['categories|id|categoryName', 'riskstatuses|id|riskStatusName',
      'residualrisklikelihoods|id|residualRiskLikelihoodName|residualRiskLikelihoodLevel',
      'residualimpactassessments|id|residualImpactAssessmentName|residualImpactAssessmentLevel',
      'inherentrisklikelihoods|id|inherentRiskLikelihoodName|inherentRiskLikelihoodlevel',
      'inherentimpactassessments|id|inherentImpactAssessmentName|inherentImpactAssessmentLevel'
    ];
    this.prepareDrpDownData(drpdownPaths);
    RiskRegisterComponent.self = this;
  }
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
      if (!isUndefined(keyName.split('|')[3])) {
        json['value'] = element[keyName.split('|')[3]];
      }
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
        if (path.split('|')[0] == 'categories') {
          this.preparedcategoryOptions = this.makeCustomDrpdownWithval(maindrpdowndata, path);
        } else if (path.split('|')[0] == 'riskstatuses') {
          this.preparedriskStatusOptions = this.makeCustomDrpdownWithval(maindrpdowndata, path);
        } else if (path.split('|')[0] == 'residualrisklikelihoods') {
          this.preparedRiskLikelihoodOptions = this.makeCustomDrpdownWithval(maindrpdowndata, path);
        } else if (path.split('|')[0] == 'residualimpactassessments') {
          this.preparedImpactAssessmentOptions = this.makeCustomDrpdownWithval(maindrpdowndata, path);
        } else if (path.split('|')[0] == 'inherentrisklikelihoods') {
          this.preparedInherentRiskLikelihoodOptions = this.makeCustomDrpdownWithval(maindrpdowndata, path);
        } else if (path.split('|')[0] == 'inherentimpactassessments') {
          this.preparedInherentImpactAssessmentOptions = this.makeCustomDrpdownWithval(maindrpdowndata, path);
        }

      });
  }
  getAllData() {
    this.httpclientService.get(this.mainpath).pipe(first()).subscribe(
      data => {
        this.fnDatableShowHide();
        this.changeMenu('Grid');
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
    this.riskregisterid = this.checkVal(json['id']);
    this.riskRegisterForm = this.formBuilder.group({
      id: [this.checkVal(this.riskregisterid)],
      category: [this.checkVal(this.selectedvalue), Validators.required],
      riskDescription: [this.checkVal(json['riskDescription']), Validators.required],
      inheritRiskLikelihood: [this.checkVal(json['inheritRiskLikelihood']), Validators.required],
      inheritImpactAssessment: [this.checkVal(json['inheritImpactAssessment']), Validators.required],
      inheritRiskAssessmentScore: [this.checkVal(json['inheritRiskAssessmentScore']), Validators.required],
      currentControls: [this.checkVal(json['currentControls']), Validators.required],
      riskOwner: [this.checkVal(json['riskOwner']), Validators.required],
      furthurAction: [this.checkVal(json['furthurAction']), Validators.required],
      residualRiskLikelihood: [this.checkVal(json['ResidualRiskLikelihood']), Validators.required],
      residualImpactAssessment: [this.checkVal(json['residualImpactAssessment']), Validators.required],
      residualRiskAssessmentScore: [this.checkVal(json['residualRiskAssessmentScore']), Validators.required],
      riskStatus: [this.checkVal(json['riskStatus']), Validators.required],
      uniqueId: [this.checkVal(json['uniqueId'])]
    });
    if (this.checkVal(json) != '') {
      this.refilllDropdownvalue(json);
    } else {
      this.drpresidualImpactAssessmentvalue = null;
      this.drpresidualRiskLikelihoodvalue = null;
      this.drpinherentImpactAssessmentvalue = null;
      this.drpinheritRiskLikelihoodvalue = null;
      this.drpcatgoryvalue = null;
      this.drpriskStatusvalue = null;
    }

  }
  checkVal(value) {
    if (value != '' && value != null && value != undefined) {
      return value;
    } else {
      return '';
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
    this.changeMenu('Add');
    this.httpclientService.get(this.mainpath + '/' + id).pipe(first()).subscribe(
      data => {
        this.fillForm(data);
      },
      error => {
        this.alertService.error(error, false);
        this.loading = false;
      });
  }

  refilllDropdownvalue(json) {
    if (json.hasOwnProperty('category')) {
      this.drpcatgoryvalue = this.makeDropdownValue(json, 'category');
    }
    if (json.hasOwnProperty('residualRiskLikelihood')) {
      this.drpresidualRiskLikelihoodvalue = this.makeDropdownValue(json, 'residualRiskLikelihood');
    }
    if (json.hasOwnProperty('residualImpactAssessment')) {
      this.drpresidualImpactAssessmentvalue = this.makeDropdownValue(json, 'residualImpactAssessment');
    }
    if (json.hasOwnProperty('riskStatus')) {
      this.drpriskStatusvalue = this.makeDropdownValue(json, 'riskStatus');
    }
    if (json.hasOwnProperty('inheritRiskLikelihood')) {
      this.drpinheritRiskLikelihoodvalue = this.makeDropdownValue(json, 'inheritRiskLikelihood');
    }
    if (json.hasOwnProperty('inheritImpactAssessment')) {
      this.drpinherentImpactAssessmentvalue = this.makeDropdownValue(json, 'inheritImpactAssessment');
    }
  }
  inheritRiskAssessmentScore = 0;
  inheritImpactAssessment = 0;
  inheritRiskLikelihood = 0;

  residualRiskScore = 0;
  residualImpact = 0;
  residualLikelihood = 0;

  fnCustomSearchSet(e, flag) {
    if (flag == 'categoryCustomSearch') {
      if (isUndefined(e) || isNullOrUndefined(e.description)) {
        this.mainCustomSearchJson['categoryId'] = -1;
        this.drpcatgoryvalueCustomSearch = null;
      } else {
        this.mainCustomSearchJson['categoryId'] = e.id;
        this.drpcatgoryvalueCustomSearch = e;
      }
    }

    if (flag == 'statusCustomSearch') {
      if (isUndefined(e) || isNullOrUndefined(e.description)) {
        this.mainCustomSearchJson['statusId'] = -1;
        this.drpriskStatusCustomSearch = null;
      } else {
        this.mainCustomSearchJson['statusId'] = e.id;
        this.drpriskStatusCustomSearch = e;
      }
    }

    if (flag == 'ownerCustomSearch') {
      if (isUndefined(e) || isNullOrUndefined(e.target.value)) {
        this.mainCustomSearchJson['riskOwnername'] = "-1";
      } else {
        this.mainCustomSearchJson['riskOwnername'] = e.target.value;
      }
    }
  }

  fnSetVal(e, flag) {
    //Auto Fill Inherent Risk Assessment Score   
    if (!isUndefined(e) && flag.indexOf('CustomSearch') == -1) {
      var json = {};
      json['id'] = e.id;
      json['description'] = e.description;
      this.riskRegisterForm.controls[flag].setValue(json);
    }
    if (flag == 'inheritRiskLikelihood') {
      if (isUndefined(e) || isNaN(e.value)) {
        this.inheritRiskLikelihood = 0;
      } else {
        this.inheritRiskLikelihood = Number(e.value);
      }
    }

    if (flag == 'inheritImpactAssessment') {
      if (isUndefined(e) || isNaN(e.value)) {
        this.inheritImpactAssessment = 0;
      } else {
        this.inheritImpactAssessment = Number(e.value);
      }
    }
    this.inheritRiskAssessmentScore = Number(this.inheritRiskLikelihood) * Number(this.inheritImpactAssessment);

    //Auto Fill Residual Risk Assessment Score
    if (flag == 'residualRiskLikelihood') {
      if (isUndefined(e) || isNaN(e.value)) {
        this.residualImpact = 0;
      } else {
        this.residualImpact = Number(e.value);
      }
    }

    if (flag == 'residualImpactAssessment') {
      if (isUndefined(e) || isNaN(e.value)) {
        this.residualLikelihood = 0;
      } else {
        this.residualLikelihood = Number(e.value);
      }
    }
    this.residualRiskScore = Number(this.residualImpact) * Number(this.residualLikelihood);

  }
  makeDropdownValue(json, key) {
    json = json[key];
    key = key.replace('inherit', 'inherent');
    let mainJson = {};
    mainJson['id'] = json['id'];
    mainJson['description'] = json[key + 'Name'];
    return mainJson;
  }
  get f() {
    return this.riskRegisterForm.controls;
  }

  onSubmit() {
    this.submitted = true;
    // stop here if form is invalid
    if (this.riskRegisterForm.invalid) {
      return;
    }
    RiskRegisterComponent.self.loading = true;
    let flag = this.riskregisterid == '' ? 'save' : 'update';
    if (flag == 'save') {
      this.riskRegisterForm.controls['uniqueId'].setValue(this.commonUtility.genRandom());
    }
    let saveUpdatePath = this.riskregisterid == '' ? this.mainpath : this.mainpath + '/' + this.riskregisterid;
    this.httpclientService.saveOrUpdate(this.riskRegisterForm.value, saveUpdatePath, flag)
      .pipe(first())
      .subscribe(
        data => {
          RiskRegisterComponent.self.loading = false;
          this.submitted = false;
          this.alertService.successSaveUpdate('');
          this.fillForm('');
          RiskRegisterComponent.self.setOption = 'Grid';
          this.getAllData();
        },
        error => {
          this.submitted = false;
          this.alertService.error(error);
          RiskRegisterComponent.self.loading = false;
        });
  }

  fnSearchCategoryStatusOwner() {
    if (!this.isCustomSearchNull()) {
      this.fnDatableShowHide();
      this.httpclientService.get(this.mainpath + '/search/categoryIdOrRiskStatusIdOrRiskOwner/' + JSON.stringify(this.mainCustomSearchJson)).pipe(first()).subscribe(
        data => {
          this.modifyJsonCount(data);
          let responsecontent = data;
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

  isCustomSearchNull() {
    let status = false;
    if (this.mainCustomSearchJson['categoryId'] == -1 && this.mainCustomSearchJson['statusId'] == -1 &&
      this.mainCustomSearchJson['riskOwnername'] == "-1") {
      status = true;
    }
    return status;
  }

  fnResetSearchCategoryStatusOwner() {
    this.mainCustomSearchJson = { 'categoryId': -1, 'statusId': -1, 'riskOwnername': "-1" };
    this.drpcatgoryvalueCustomSearch = null;
    this.drpriskStatusCustomSearch = null;
    $('#ownerCustomSearch').val('');
    this.fnSearchCategoryStatusOwner();
  }

}
