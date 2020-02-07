import { Component, OnInit, ViewChild } from '@angular/core';
import { AlertService } from '@app/_services';
import { Router } from '@angular/router';
import { FormBuilder, Validators, FormGroup } from '@angular/forms';
import { HttpClientService } from '@app/_services/httpClient.service';
import { TableAppComponent } from '@app/table-app/table-app.component';
import { CommonUtility } from '@app/_services/common.service';
import { isUndefined, isNullOrUndefined } from 'util';
import { first } from 'rxjs/operators';

@Component({
  selector: 'app-residual-risk-likelihood',
  templateUrl: './residual-risk-likelihood.component.html',
  styleUrls: ['./residual-risk-likelihood.component.css']
})
export class ResidualRiskLikelihoodComponent implements OnInit {
  public static self: ResidualRiskLikelihoodComponent;
  likelihoodForm: FormGroup;
  loading = false;
  submitted = false;
  returnUrl: string;
  setOption = 'Grid';
  operation = 'Add';
  mainpath = 'residualrisklikelihoods';
  id = '';
  validator = {};
  @ViewChild(TableAppComponent, { static: false })
  private tableComponent: TableAppComponent;
  datatable = {
    title: 'Residual Risk Likelihood',
    cols: [
      { title: 'ID', data: 'id' },
      { title: 'Residual Risk Likelihood', data: 'residualRiskLikelihoodName' },
      { title: 'Residual Risk Likelihood Level', data: 'residualRiskLikelihoodLevel' },
      { title: 'Residual Risk Likelihood Description', data: 'residualRiskLikelihoodDescription' },
      {
        title: 'Action', render: function (data: any, type: any, full: any) {
          return ResidualRiskLikelihoodComponent.self.createEditDeleteButton(full);
        }
      }
    ],
    rows: [],
    custominput: false,
    customcols: []
  };
  canSave = false;

  constructor(private formBuilder: FormBuilder,
    private router: Router,
    private httpclientService: HttpClientService,
    private commonUtility: CommonUtility,
    private alertService: AlertService) {
  }
  selectedLevel;
  residualLevelLabel = 'Risk Likelihood Level';
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

  residualLevelid = 'residualRiskLikelihoodName';

  exportAsXLSX(): void {
    this.commonUtility.exportExcel(this.likelihoodForm.value, 'Residual_Risk_Likelihood');
  }

  printAsPdf(): void {
    this.commonUtility.generatePdf(this.likelihoodForm.value, 'Residual_Risk_Likelihood', this.commonUtility.printdownloadpdfflag);
  }

  createEditDeleteButton(fullData) {
    let mainId = fullData['id'];
    let editBtn = '';
    let deleteBtn = '';
    let userPermission = JSON.parse(localStorage.getItem('currentUser'))['permissions'];
    let mainpermissionJson = {};
    userPermission.forEach(element => {
      if (element.pageName == 'Residual_Risk_Likelihood') {
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
        this.selectedLevel = { 'id': data['residualRiskLikelihoodLevel'], 'description': data['residualRiskLikelihoodLevel'] };
        this.changeMenu('Add');
        this.fnSetVal(this.selectedLevel, 'residualRiskLikelihoodLevel', 'residualRiskLikelihoodLevel');
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
    this.selectedLevel = this.commonUtility.checkVal(json['residualRiskLikelihoodLevel']);
    this.likelihoodForm = this.formBuilder.group({
      id: [this.commonUtility.checkVal(json['id'])],
      residualRiskLikelihoodName: [this.commonUtility.checkVal(json['residualRiskLikelihoodName']), Validators.required],
      residualRiskLikelihoodLevel: [this.commonUtility.checkVal(json['residualRiskLikelihoodLevel'])],
      residualRiskLikelihoodDescription: [this.commonUtility.checkVal(json['residualRiskLikelihoodDescription']), Validators.required],
    });
  }

  ngOnInit() {
    this.canSave = this.httpclientService.cheackCanSavePermission('Residual_Risk_Likelihood');
    this.getAllData();
    ResidualRiskLikelihoodComponent.self = this;
    this.likelihoodForm = this.formBuilder.group({
      residualRiskLikelihoodName: ['', Validators.required],
      residualRiskLikelihoodLevel: [''],
      residualRiskLikelihoodDescription: ['', Validators.required]
    });
    this.validator = {
      residualRiskLikelihoodLevel: {
        status: false,
        message: 'Residual Risk Likehood Level is required',
      }
    }
  }

  resetValidation() {
    let jsonIndexName = [];
    $.each(this.validator, function (index, object) {

      if (object['status']) {
        jsonIndexName.push(index);
      }
    });
    for (const iterator of jsonIndexName) {
      this.validator[iterator]['status'] = false;
    }
  }

  // convenience getter for easy access to form fields
  get f() { return this.likelihoodForm.controls; }
  validation(validatorname, e) {

    if (isNullOrUndefined(e)) {
      this.validator[validatorname]['status'] = true;
    } else {
      this.validator[validatorname]['status'] = false;
    }
    console.log(this.validator);
  }
  fnSetVal(e, flag, validatorname) {



    if (!isNullOrUndefined(e)) {
      this.likelihoodForm.controls[flag].setValue(e.id);
      this.selectedLevel = e;
    } else {
      this.likelihoodForm.controls[flag].setValue(null);
      this.selectedLevel = null;
    }
    console.log(this.likelihoodForm)
    this.validation(validatorname, e);
  }



  onSubmit() {
    this.submitted = true;
    let validatingpass = true;
    let indexArray = [];
    console.log(this.likelihoodForm);
    $.each(this.validator, function (index, object) {

      console.log(ResidualRiskLikelihoodComponent.self.likelihoodForm.controls[index]['value']);
      if (ResidualRiskLikelihoodComponent.self.likelihoodForm.controls[index]['value'] == ""
        || isNullOrUndefined(ResidualRiskLikelihoodComponent.self.likelihoodForm.controls[index]['value'])) {
        indexArray.push(index);
        validatingpass = false;
      }
    });
    for (const iterator of indexArray) {
      this.validator[iterator]['status'] = true;
    }

    this.submitted = true;
    //stop here if form is invalid
    if (this.likelihoodForm.invalid) {
      return;
    }

    if (validatingpass) {



      this.loading = true;
      let flag = this.id == '' ? 'save' : 'update';
      let saveUpdatePath = this.id == '' ? this.mainpath : this.mainpath + '/' + this.id;
      this.httpclientService.saveOrUpdate(this.likelihoodForm.value, saveUpdatePath, flag)
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
