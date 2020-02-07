import { Component, OnInit, ViewChild } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { TableAppComponent } from '@app/table-app/table-app.component';
import { HttpClientService } from '@app/_services/httpClient.service';
import { CommonUtility } from '@app/_services/common.service';
import { AlertService } from '@app/_services';
import { first } from 'rxjs/operators';
import { isUndefined, isNullOrUndefined } from 'util';

@Component({
  selector: 'app-inherent-impact-assessment',
  templateUrl: './inherent-impact-assessment.component.html',
  styleUrls: ['./inherent-impact-assessment.component.css']
})
export class InherentImpactAssessmentComponent implements OnInit {
  public static self: InherentImpactAssessmentComponent;
  validator = {};
  inherentImpactAssessmentForm: FormGroup;
  loading = false;
  submitted = false;
  returnUrl: string;
  setOption = 'Grid';
  operation = 'Add';
  mainpath = 'inherentimpactassessments';
  id = '';
  canSave = false;
  @ViewChild(TableAppComponent, { static: false })
  private tableComponent: TableAppComponent;
  datatable = {
    title: 'InherentImpactAssessment',
    cols: [
      { title: 'ID', data: 'count' },
      { title: 'Inherent Impact Assessment Name', data: 'inherentImpactAssessmentName' },
      { title: 'Inherent Impact Assessment Level', data: 'inherentImpactAssessmentLevel' },
      { title: 'Inherent Impact Assessment Description', data: 'inherentImpactAssessmentDescription' },

      {
        title: 'Action', render: function (data: any, type: any, full: any) {
          return InherentImpactAssessmentComponent.self.createEditDeleteButton(full);
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


  selectedLevel;
  inherentLevelLabel = ' Inherent Impact Assessment  Level';
  inherentLevelClass = 'lblgrey18bold';
  inherentLevelid = 'inherentLevelid';

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

  constructor(private formBuilder: FormBuilder,
    private httpclientService: HttpClientService,
    private commonUtility: CommonUtility,
    private alertService: AlertService) {
  }

  exportAsXLSX(): void {
    this.commonUtility.exportExcel(this.inherentImpactAssessmentForm.value, 'Inherent_Impact_Assessment');
  }
  printAsPdf(): void {
    this.commonUtility.generatePdf(this.inherentImpactAssessmentForm.value, 'Inherent_Impact_Assessment', this.commonUtility.printdownloadpdfflag);
  }

  createEditDeleteButton(fullData) {
    let mainId = fullData['id'];
    let editBtn = '';
    let deleteBtn = '';
    let userPermission = JSON.parse(localStorage.getItem('currentUser'))['permissions'];
    let mainpermissionJson = {};
    userPermission.forEach(element => {
      if (element.pageName == 'Inherent_Impact_Assessment') {
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
        this.selectedLevel = { 'id': data['inherentImpactAssessmentLevel'], 'description': data['inherentImpactAssessmentLevel'] };
        this.changeMenu('Add');
        this.fnSetVal(this.selectedLevel, 'inherentImpactAssessmentLevel', 'inherentImpactAssessmentLevel');
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
      this.inherentImpactAssessmentForm.controls[flag].setValue(e.id);
      this.selectedLevel = e;
    } else {
      this.inherentImpactAssessmentForm.controls[flag].setValue(null);
      this.selectedLevel = null;
    }
    console.log(this.inherentImpactAssessmentForm)
    this.validation(validatorname, e);
  }
  fillForm(json) {
    this.id = this.commonUtility.checkVal(json['id']);
    this.selectedLevel = this.commonUtility.checkVal(json['inherentImpactAssessmentLevel']);
    this.inherentImpactAssessmentForm = this.formBuilder.group({
      id: [this.commonUtility.checkVal(json['id'])],
      inherentImpactAssessmentName: [this.commonUtility.checkVal(json['inherentImpactAssessmentName']), Validators.required],
      inherentImpactAssessmentDescription: [this.commonUtility.checkVal(json['inherentImpactAssessmentDescription']), Validators.required],
      inherentImpactAssessmentLevel: [this.commonUtility.checkVal(json['inherentImpactAssessmentLevel'])]
    });
  }
  ngOnInit() {
    this.canSave = this.httpclientService.cheackCanSavePermission('Inherent_Impact_Assessment');
    this.getAllData();
    InherentImpactAssessmentComponent.self = this;
    this.inherentImpactAssessmentForm = this.formBuilder.group({
      inherentImpactAssessmentName: ['', Validators.required],
      inherentImpactAssessmentLevel: [''],
      inherentImpactAssessmentDescription: ['', Validators.required]
    });
    this.validator = {
      inherentImpactAssessmentLevel: {
        status: false,
        message: 'Inherent Impact Assessment Level is required',
      }
    }
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

  // convenience getter for easy access to form fields
  get f() { return this.inherentImpactAssessmentForm.controls; }


  onSubmit() {
    this.submitted = true;
    let validatingpass = true;
    let indexArray = [];
    console.log(this.inherentImpactAssessmentForm);
    $.each(this.validator, function (index, object) {
      debugger;
      console.log(InherentImpactAssessmentComponent.self.inherentImpactAssessmentForm.controls[index]['value']);
      if (InherentImpactAssessmentComponent.self.inherentImpactAssessmentForm.controls[index]['value'] == ""
        || isNullOrUndefined(InherentImpactAssessmentComponent.self.inherentImpactAssessmentForm.controls[index]['value'])) {
        indexArray.push(index);
        validatingpass = false;
      }
    });
    for (const iterator of indexArray) {
      this.validator[iterator]['status'] = true;
    }




    this.submitted = true;
    // stop here if form is invalid
    if (this.inherentImpactAssessmentForm.invalid) {
      return;
    }
    if (validatingpass) {


      this.loading = true;

      let flag = this.id == '' ? 'save' : 'update';
      let saveUpdatePath = this.id == '' ? this.mainpath : this.mainpath + '/' + this.id;

      this.httpclientService.saveOrUpdate(this.inherentImpactAssessmentForm.value, saveUpdatePath, flag)
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
  validation(validatorname, e) {
    debugger;
    if (isNullOrUndefined(e)) {
      this.validator[validatorname]['status'] = true;
    } else {
      this.validator[validatorname]['status'] = false;
    }
    console.log(this.validator);
  }

}
