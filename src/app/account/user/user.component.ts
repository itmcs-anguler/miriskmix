import { Component, OnInit, ViewChild } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthenticationService, AlertService } from '@app/_services';
import { first } from 'rxjs/operators';
import { HttpClientService } from '@app/_services/httpClient.service';
import { isUndefined, isNullOrUndefined } from 'util';
import { TableAppComponent } from '@app/table-app/table-app.component';
import Swal from 'sweetalert2';
import { CommonUtility } from '@app/_services/common.service';
import { SelectAppComponent } from '@app/select-app/select-app.component';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css', '../../app.component.css']
})
export class UserComponent implements OnInit {
  public static self: UserComponent;
  @ViewChild(TableAppComponent, { static: false })
  private tableComponent: TableAppComponent;
  userForm: FormGroup;
  userID = '';
  loading = false;
  submitted = false;
  selectedvalue = null;
  returnUrl: string;
  setOption = 'Grid';
  operation = 'Add';
  mainHead: string = "User";
  roleLabel = 'Role Name';
  roleLabelClass = 'lblgrey18bold';
  roleid = 'roleid';
  preparedRoleOptions = [];
  drpRolevalue = null;
  passwordCell = true;
  datatable = {
    title: 'User',
    cols: [
      { title: 'ID', data: 'count' },
      { title: 'User Name', data: 'userName' },
      { title: 'Full Name', data: 'fullName' },
      { title: 'Role Name', data: 'manageroles.roleName' },
      {
        title: 'Action', render: function (data: any, type: any, full: any) {
          return UserComponent.self.createEditDeleteButton(full);
        }
      }
    ],
    rows: [],
    custominput: false,
    customcols: []
  };
  validator = {};

  fnSetVal(e, flag, validatorname) {



    if (!isNullOrUndefined(e)) {
      this.userForm.controls[flag].setValue(e.id);
      this.drpRolevalue = e;
    } else {
      this.userForm.controls[flag].setValue(null);
      this.drpRolevalue = null;
    }
    console.log(this.userForm)
    this.validation(validatorname, e);
  }
  validation(validatorname, e) {

    if (isNullOrUndefined(e)) {
      this.validator[validatorname]['status'] = true;
    } else {
      this.validator[validatorname]['status'] = false;
    }
    console.log(this.validator);
  }
  canSave = false;
  createEditDeleteButton(fullData) {
    let mainId = fullData['id'];
    let editBtn = '';
    let deleteBtn = '';
    let userPermission = JSON.parse(localStorage.getItem('currentUser'))['permissions'];
    let mainpermissionJson = {};
    userPermission.forEach(element => {
      if (element.pageName == 'Manage_User') {
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

  exportAsXLSX(): void {
    this.commonUtility.exportExcel(this.userForm.value, 'Manage_User');
  }

  printAsPdf(): void {
    this.commonUtility.generatePdf(this.userForm.value, 'Manage_User', this.commonUtility.printdownloadpdfflag);
  }

  constructor(private formBuilder: FormBuilder,
    private alertService: AlertService,
    private httpclientService: HttpClientService,
    private commonUtility: CommonUtility) {
  }

  changeMenu(flag) {
    this.setOption = flag;
  }
  ngOnInit() {
    this.canSave = this.httpclientService.cheackCanSavePermission('Manage_User');
    this.getAllData();
    UserComponent.self = this;
    this.datatable.rows = [

    ];
    let drpdownPaths = ['manageroles|id|roleName'];
    this.prepareDrpDownData(drpdownPaths);
    this.fillForm('');
    this.validator = {
      manageroles: {
        status: false,
        message: 'Role Name is required',
      }
    }
  }
  checkVal(value) {
    if (value != '' && value != null && value != undefined) {
      return value;
    } else {
      return '';
    }
  }
  prepareDrpDownData(drpdownPaths) {
    drpdownPaths.forEach(element => {
      this.getSetDrpDownData(element);
    });
  }
  getSetDrpDownData(path) {

    let maindrpdowndata = [];
    this.preparedRoleOptions = [];
    this.httpclientService.get(path.split('|')[0]).pipe(first()).subscribe(
      data => {
        maindrpdowndata = data['content'];
        if (path.split('|')[0] == 'manageroles') {
          this.preparedRoleOptions = this.makeCustomDrpdownWithval(maindrpdowndata, path);
        }

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
  get f() { return this.userForm.controls; }

  onSubmit() {
    this.submitted = true;
    let validatingpass = true;
    let indexArray = [];
    console.log(this.userForm);
    $.each(this.validator, function (index, object) {

      console.log(UserComponent.self.userForm.controls[index]['value']);
      if (UserComponent.self.userForm.controls[index]['value'] == ""
        || isNullOrUndefined(UserComponent.self.userForm.controls[index]['value'])) {
        indexArray.push(index);
        validatingpass = false;
      }
    });
    for (const iterator of indexArray) {
      this.validator[iterator]['status'] = true;
    }
    if (this.userForm.invalid) {
      return;
    }


    if (validatingpass) {
      this.loading = true;
      let flag = this.userID == '' ? 'save' : 'update';
      let saveUpdatePath = this.userID == '' ? 'manageusers' : 'manageusers' + '/' + this.userID;
      let json = this.userForm.value;
      let temuser = {};
      temuser['id'] = this.drpRolevalue.id;
      temuser['description'] = this.drpRolevalue.description;
      //temuser['id'] = json['manageroles'];
      json['manageroles'] = temuser;
      this.httpclientService.saveOrUpdate(this.makeJsonForSaveOrUpdate(json, flag), saveUpdatePath, flag)
        .pipe(first())
        .subscribe(
          data => {
            this.loading = false;
            this.submitted = false;
            this.fillForm('');
            this.getAllData();
            this.alertService.successSaveUpdate('');
            this.setOption = 'Grid';
          },
          error => {
            this.alertService.customErrorMessage(error);
            this.submitted = false;
            this.loading = false;
          });
    }

  }
  makeJsonForSaveOrUpdate(formObject, flag) {
    let finaljson = {};
    if (flag == 'save') {
      finaljson['userName'] = formObject.userName;
      finaljson['manageroles'] = formObject.manageroles;
      finaljson['fullName'] = formObject.fullName;
      finaljson['passWord'] = formObject.passWord;
    } else {
      finaljson['userName'] = formObject.userName;
      finaljson['id'] = formObject.id;
      finaljson['manageroles'] = formObject.manageroles;
      finaljson['version'] = formObject.version;
      finaljson['fullName'] = formObject.fullName;
      finaljson['passWord'] = formObject.passWord;
    }
    return finaljson;
  }
  fillForm(json) {
    //this.selectappcomponent.fnTemp(this.formBuilder.control['manageroles']);

    this.userID = this.checkVal(json['id']);
    this.userForm = this.formBuilder.group({
      id: [this.checkVal(json['id'])],
      userName: [this.checkVal(json['userName']), [Validators.required, Validators.email]],
      fullName: [this.checkVal(json['fullName']), Validators.required],
      passWord: [this.checkVal(json['passWord']), [Validators.required, Validators.minLength(6)]],
      manageroles: [this.checkVal(isNullOrUndefined(json['manageroles']) ? null : json['manageroles']['id']), Validators.required]
    });

    this.drpRolevalue = null;
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
  getAllData() {
    this.httpclientService.get('manageusers').pipe(first()).subscribe(
      data => {
        this.modifyJsonCount(data['content']);
        let responsecontent = data['content'];
        this.datatable.rows = responsecontent;
        if (isUndefined(this.tableComponent)) {
          this.tableComponent = new TableAppComponent();
        }
        this.tableComponent.customDatatableobj = this.datatable;
        this.tableComponent.rerender();
        this.passwordCell = true;
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
  fnDelete(id) {
    this.httpclientService.delete("manageusers" + '/' + id).pipe(first()).subscribe(
      data => {
        this.alertService.deleteSuccessMsg();
        this.getAllData();
      },
      error => {
        this.alertService.error(error, false);
        this.loading = false;
      });
  }
  formSubmitload() {
    this.loading = true;
    setTimeout(function () {
      UserComponent.self.loading = false;
      UserComponent.self.changeMenu('Grid');
    }, 3000);
  }
  fnEdit(id) {
    this.resetValidation();
    this.httpclientService.get("manageusers" + '/' + id).pipe(first()).subscribe(
      data => {
        this.fillForm(data);
        this.refilllDropdownvalue(data);
        this.changeMenu('Add');
        this.passwordCell = false;
      },
      error => {
        this.alertService.error(error, false);
        this.loading = false;
      });
  }
  refilllDropdownvalue(json) {
    if (json.hasOwnProperty('manageroles')) {
      let manageRoleObj = json['manageroles'];
      this.drpRolevalue = this.makeDropdownValue(manageRoleObj, 'role');
    }
  }
  makeDropdownValue(json, key) {
    let mainJson = {};
    mainJson['id'] = json['id'];
    mainJson['description'] = json[key + 'Name'];
    return mainJson;
  }

  changePassword() {
    this.passwordCell = true;
    this.userForm.controls['passWord'].setValue('');
  }
  changePasswordPopup() {
    Swal.mixin({
      input: 'text',
      showCancelButton: true,
      progressSteps: []
    }).queue([
      {
        title: 'Please insert new your password'
      }
    ]).then((result) => {
      if (result.value) {
        const answers = result.value[0]
        this.passwordCell = true;
        this.userForm.controls['passWord'].setValue(answers);
      }
    })
  }


}
