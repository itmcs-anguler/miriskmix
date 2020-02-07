import { Component, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthenticationService, AlertService, UserService } from '@app/_services';
import { first } from 'rxjs/operators';
import { HttpClientService } from '@app/_services/httpClient.service';
import { CommonUtility } from '@app/_services/common.service';
import { TableAppComponent } from '@app/table-app/table-app.component';
import { isUndefined, isNullOrUndefined } from 'util';

@Component({
  selector: 'app-residual-impact-assessment',
  templateUrl: './residual-impact-assessment.component.html',
  styleUrls: ['./residual-impact-assessment.component.css']
})
export class ResidualImpactAssessmentComponent implements OnInit {

  public static self: ResidualImpactAssessmentComponent;
  residualImpactAssessmentForm: FormGroup;
  validator = {};
  loading = false;
  submitted = false;
  returnUrl: string;
  setOption = 'Grid';
  operation = 'Add';
  mainpath = 'residualimpactassessments';
  id = '';
  @ViewChild(TableAppComponent, { static: false })
  private tableComponent: TableAppComponent;
  datatable = {
    title: 'residualImpactAssessment',
    cols: [
      { title: 'ID', data: 'count' },
      { title: 'Residual Impact Assessment Name', data: 'residualImpactAssessmentName' },
      { title: 'Residual Impact Assessment Level', data: 'residualImpactAssessmentLevel' },
      { title: 'Residual Impact Assessment Description', data: 'residualImpactAssessmentDescription' },

      {
        title: 'Action', render: function (data: any, type: any, full: any) {
          return ResidualImpactAssessmentComponent.self.createEditDeleteButton(full);
        }
      }
    ],
    rows: [],
    custominput: false,
    customcols: []
  };

  makeDropdownValue(val) {
    let mainJson = {};
    mainJson['id'] = val;
    mainJson['description'] = val;
    return mainJson;
  }
  canSave = false;
  selectedLevel;
  residualLevelLabel = ' Residual Impact Assessment  Level';
  residualLevelClass = 'lblgrey18bold';
  preparedLevelOptions = [
    {
      'id': '1',
      'description': '1'
    }, {
      'id': '2',
      'description': '2'
    }, {
      'id': '3',
      'description': '3'
    }, {
      'id': '4',
      'description': '4'
    }, {
      'id': '5',
      'description': '5'
    },
  ];

  exportAsXLSX(): void {
    this.commonUtility.exportExcel(this.residualImpactAssessmentForm.value, 'Residual_Impact_Assessment');
  }

  printAsPdf(): void {
    this.commonUtility.generatePdf(this.residualImpactAssessmentForm.value, 'Residual_Impact_Assessment', this.commonUtility.printdownloadpdfflag);
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
      if (element.pageName == 'Residual_Impact_Assessment') {
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
    this.resetValidation();
    this.httpclientService.get(this.mainpath + '/' + id).pipe(first()).subscribe(
      data => {
        this.fillForm(data);
        this.selectedLevel = { 'id': data['residualImpactAssessmentLevel'], 'description': data['residualImpactAssessmentLevel'] };
        this.changeMenu('Add');
        this.fnSetVal(this.selectedLevel, 'residualImpactAssessmentLevel', 'residualImpactAssessmentLevel');
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

  fnSetVal(e, flag, validatorname) {
    debugger;


    if (!isNullOrUndefined(e)) {
      this.residualImpactAssessmentForm.controls[flag].setValue(e.id);
      this.selectedLevel = e;
    } else {
      this.residualImpactAssessmentForm.controls[flag].setValue(null);
      this.selectedLevel = null;
    }
    console.log(this.residualImpactAssessmentForm)
    this.validation(validatorname, e);
  }
  fillForm(json) {
    this.id = this.commonUtility.checkVal(json['id']);
    this.selectedLevel = this.commonUtility.checkVal(json['residualImpactAssessmentLevel']);
    this.residualImpactAssessmentForm = this.formBuilder.group({
      id: [this.commonUtility.checkVal(json['id'])],
      residualImpactAssessmentName: [this.commonUtility.checkVal(json['residualImpactAssessmentName']), Validators.required],
      residualImpactAssessmentDescription: [this.commonUtility.checkVal(json['residualImpactAssessmentDescription']), Validators.required],
      residualImpactAssessmentLevel: [this.commonUtility.checkVal(json['residualImpactAssessmentLevel'])]
    });
    //this.selectedLevel = this.makeDropdownValue(this.commonUtility.checkVal(json['residualImpactAssessmentLevel']));
  }
  resetValidation() {
    let jsonIndexName = [];
    $.each(this.validator, function (index, object) {
      debugger;
      if (object['status']) {
        jsonIndexName.push(index);
      }
    });
    for (const iterator of jsonIndexName) {
      this.validator[iterator]['status'] = false;
    }
  }
  ngOnInit() {
    this.canSave = this.httpclientService.cheackCanSavePermission('Residual_Impact_Assessment');
    this.getAllData();
    ResidualImpactAssessmentComponent.self = this;
    this.residualImpactAssessmentForm = this.formBuilder.group({
      residualImpactAssessmentName: ['', Validators.required],
      residualImpactAssessmentLevel: [''],
      residualImpactAssessmentDescription: ['', Validators.required]
    });
    this.validator = {
      residualImpactAssessmentLevel: {
        status: false,
        message: 'Residual Impact Assessment Level is required',
      }
    }
  }

  // convenience getter for easy access to form fields
  get f() { return this.residualImpactAssessmentForm.controls; }
  validation(validatorname, e) {
    debugger;
    if (isNullOrUndefined(e)) {
      this.validator[validatorname]['status'] = true;
    } else {
      this.validator[validatorname]['status'] = false;
    }
    console.log(this.validator);
  }

  onSubmit() {
    this.submitted = true;
    let validatingpass = true;
    let indexArray = [];
    console.log(this.residualImpactAssessmentForm);
    $.each(this.validator, function (index, object) {
      debugger;
      console.log(ResidualImpactAssessmentComponent.self.residualImpactAssessmentForm.controls[index]['value']);
      if (ResidualImpactAssessmentComponent.self.residualImpactAssessmentForm.controls[index]['value'] == ""
        || isNullOrUndefined(ResidualImpactAssessmentComponent.self.residualImpactAssessmentForm.controls[index]['value'])) {
        indexArray.push(index);
        validatingpass = false;
      }
    });
    for (const iterator of indexArray) {
      this.validator[iterator]['status'] = true;
    }
    this.submitted = true;
    // stop here if form is invalid
    if (this.residualImpactAssessmentForm.invalid) {
      return;
    }
    if (validatingpass) {
      this.loading = true;

      let flag = this.id == '' ? 'save' : 'update';
      let saveUpdatePath = this.id == '' ? this.mainpath : this.mainpath + '/' + this.id;

      this.httpclientService.saveOrUpdate(this.residualImpactAssessmentForm.value, saveUpdatePath, flag)
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
}
