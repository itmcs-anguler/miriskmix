import { Component, OnInit, ViewChild } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { TableAppComponent } from '@app/table-app/table-app.component';
import { HttpClientService } from '@app/_services/httpClient.service';
import { CommonUtility } from '@app/_services/common.service';
import { AlertService } from '@app/_services';
import { first } from 'rxjs/operators';
import { isUndefined, isNullOrUndefined } from 'util';

@Component({
  selector: 'app-inherent-risk-likelihood',
  templateUrl: './inherent-risk-likelihood.component.html',
  styleUrls: ['./inherent-risk-likelihood.component.css']
})
export class InherentRiskLikelihoodComponent implements OnInit {
  validator = {};
  public static self: InherentRiskLikelihoodComponent;
  inheritLikelihoodForm: FormGroup;
  loading = false;
  submitted = false;
  returnUrl: string;
  setOption = 'Grid';
  operation = 'Add';
  mainpath = 'inherentrisklikelihoods';
  id = '';
  canSave = false;
  @ViewChild(TableAppComponent, { static: false })
  private tableComponent: TableAppComponent;
  datatable = {
    title: ' Inherent  Risk Likelihood',
    cols: [
      { title: 'ID', data: 'count' },
      { title: 'Inherent Risk Likelihood', data: 'inherentRiskLikelihoodName' },
      { title: 'Inherent Risk Likelihood Level', data: 'inherentRiskLikelihoodlevel' },
      { title: 'Inherent Risk Likelihood Description', data: 'inherentRiskLikelihoodDescription' },
      {
        title: 'Action', render: function (data: any, type: any, full: any) {
          return InherentRiskLikelihoodComponent.self.createEditDeleteButton(full);
        }
      }
    ],
    rows: [],
    custominput: false,
    customcols: []
  };

  constructor(private formBuilder: FormBuilder,
    private httpclientService: HttpClientService,
    private commonUtility: CommonUtility,
    private alertService: AlertService) {
  }
  selectedLevel;
  inherentLevelLabel = 'Risk Likelihood Level';
  inherentLevelClass = 'lblgrey18bold';
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

  inherentLevelid = 'inherentRiskLikelihoodName';

  exportAsXLSX(): void {
    this.commonUtility.exportExcel(this.inheritLikelihoodForm.value, 'Inherent_Risk_Likelihood');
  }

  printAsPdf(): void {
    this.commonUtility.generatePdf(this.inheritLikelihoodForm.value, 'Inherent_Risk_Likelihood', this.commonUtility.printdownloadpdfflag);
  }

  createEditDeleteButton(fullData) {
    let mainId = fullData['id'];
    let editBtn = '';
    let deleteBtn = '';
    let userPermission = JSON.parse(localStorage.getItem('currentUser'))['permissions'];
    let mainpermissionJson = {};
    userPermission.forEach(element => {
      if (element.pageName == 'Inherent_Risk_Likelihood') {
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
        this.selectedLevel = { 'id': data['inherentRiskLikelihoodlevel'], 'description': data['inherentRiskLikelihoodlevel'] };
        this.changeMenu('Add');
        this.fnSetVal(this.selectedLevel, 'inherentRiskLikelihoodlevel', 'inherentRiskLikelihoodlevel');
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
  fillForm(json) {
    this.id = this.commonUtility.checkVal(json['id']);
    this.selectedLevel = this.commonUtility.checkVal(json['inherentRiskLikelihoodlevel']);
    this.inheritLikelihoodForm = this.formBuilder.group({
      id: [this.commonUtility.checkVal(json['id'])],
      inherentRiskLikelihoodName: [this.commonUtility.checkVal(json['inherentRiskLikelihoodName']), Validators.required],
      inherentRiskLikelihoodlevel: [this.commonUtility.checkVal(json['inherentRiskLikelihoodlevel'])],
      inherentRiskLikelihoodDescription: [this.commonUtility.checkVal(json['inherentRiskLikelihoodDescription']), Validators.required],
    });
  }

  ngOnInit() {
    this.canSave = this.httpclientService.cheackCanSavePermission('Inherent_Risk_Likelihood');
    this.getAllData();
    InherentRiskLikelihoodComponent.self = this;
    this.inheritLikelihoodForm = this.formBuilder.group({
      inherentRiskLikelihoodName: ['', Validators.required],
      inherentRiskLikelihoodlevel: [''],
      inherentRiskLikelihoodDescription: ['', Validators.required]
    });
    this.validator = {
      inherentRiskLikelihoodlevel: {
        status: false,
        message: 'Inherent Risk Likelihood Level is required',
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
  get f() { return this.inheritLikelihoodForm.controls; }

  fnSetVal(e, flag, validatorname) {
    debugger;
    if (!isNullOrUndefined(e)) {
      this.inheritLikelihoodForm.controls[flag].setValue(e.id);
      this.selectedLevel = e;
    } else {
      this.inheritLikelihoodForm.controls[flag].setValue(null);
      this.selectedLevel = null;
    }
    console.log(this.inheritLikelihoodForm)
    this.validation(validatorname, e);
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
  onSubmit() {
    this.submitted = true;
    let validatingpass = true;
    let indexArray = [];
    console.log(this.inheritLikelihoodForm);
    $.each(this.validator, function (index, object) {
      debugger;
      console.log(InherentRiskLikelihoodComponent.self.inheritLikelihoodForm.controls[index]['value']);
      if (InherentRiskLikelihoodComponent.self.inheritLikelihoodForm.controls[index]['value'] == ""
        || isNullOrUndefined(InherentRiskLikelihoodComponent.self.inheritLikelihoodForm.controls[index]['value'])) {
        indexArray.push(index);
        validatingpass = false;
      }
    });
    for (const iterator of indexArray) {
      this.validator[iterator]['status'] = true;
    }




    this.submitted = true;
    if (this.inheritLikelihoodForm.invalid) {
      return;
    }

    if (validatingpass) {


      this.loading = true;

      let flag = this.id == '' ? 'save' : 'update';
      let saveUpdatePath = this.id == '' ? this.mainpath : this.mainpath + '/' + this.id;

      this.httpclientService.saveOrUpdate(this.inheritLikelihoodForm.value, saveUpdatePath, flag)
        .pipe(first())
        .subscribe(
          data => {
            this.loading = false;
            this.submitted = false;
            //this.fillForm('');
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
