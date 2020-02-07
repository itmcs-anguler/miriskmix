import { Component, OnInit, ViewChild } from '@angular/core';
import { Validators, FormBuilder, FormGroup } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthenticationService, AlertService } from '@app/_services';
import { HttpClientService } from '@app/_services/httpClient.service';
import { isUndefined, isNullOrUndefined } from 'util';
import { first } from 'rxjs/operators';
import { TableAppComponent } from '@app/table-app/table-app.component';
declare var $: any;
@Component({
  selector: 'app-permission',
  templateUrl: './permission.component.html',
  styleUrls: ['./permission.component.css', '../../app.component.css']
})
export class PermissionComponent implements OnInit {
  public static self: PermissionComponent;
  @ViewChild(TableAppComponent, { static: false })
  private tableComponent: TableAppComponent;
  permissionForm: FormGroup;
  roleLabel = 'Role Name';
  roleLabelClass = 'lblgrey18bold';
  map = "map";
  roleid = 'roleid';
  preparedRoleOptions: any = null;
  drpRolevalue = null;
  loading = false;
  submitted = false;
  returnUrl: string;
  setOption = 'Grid';
  operation = 'Add';
  mainHead: string = "Permission";
  pagesarray: any[] = [];
  datatable = {
    title: 'Permission',
    cols: [
      { title: 'ID', data: 'id' },
      { title: 'Role Name', data: 'roleName' },
      { title: 'Authrised Pages', data: 'authrisedPages' },
      {
        title: 'Action', render: function (data: any, type: any, full: any) {
          return PermissionComponent.self.createEditDeleteButton(full);
        }
      }
    ],
    rows: [],
    custominput: false
  };
  pagesGrid = {
    title: 'Page',
    cols: [
      { title: 'ID', data: 'id' },
      { title: 'Page Name', data: 'pageName' },
      {
        title: 'Can Save', render: function (data: any, type: any, full: any) {
          return PermissionComponent.self.createCheckbox(full, 'Save');
        }
      },
      {
        title: 'Can Edit', render: function (data: any, type: any, full: any) {
          return PermissionComponent.self.createCheckbox(full, 'Edit');
        }
      },
      {
        title: 'Can Delete', render: function (data: any, type: any, full: any) {
          return PermissionComponent.self.createCheckbox(full, 'Delete');
        }
      }
    ],
    rows: [],
    custominput: false
  };
  constructor(private formBuilder: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
    private authenticationService: AuthenticationService,
    private alertService: AlertService,
    private httpclientService: HttpClientService) {
  }

  createCheckbox(fullData, buttonId) {

    let pageId = fullData['pageName'];
    let buttonClass: string = pageId + "can" + buttonId;
    return '<input class="checkboxcustom ' + buttonClass + '" style="margin:0% 0% 0% 14%" buttonName="' + buttonId + '"  type="checkbox" value="' + pageId + '">';

  }

  permissionSaveFinalJson = [];
  fun(e) {
    let mainjson = {};
    mainjson['managepages'] = e[0].value;
    for (const checkboxevent of e) {

      let buttonName = checkboxevent.attributes[2].nodeValue;
      let buttonischecked = checkboxevent.checked;
      let temstr = "can";
      if (buttonischecked) {
        let temp = temstr + buttonName;
        mainjson[temp] = true;
      } else {
        let temp = temstr + buttonName;
        mainjson[temp] = false;
      }

    }
    this.makePermissionSaveFinalJson(mainjson);
  }


  makePermissionSaveFinalJson(mainjson) {
    let count = 0;
    let index;
    let result = false;
    for (const obj of this.permissionSaveFinalJson) {

      if (obj['managepages'] == mainjson['managepages']) {
        result = true;
        index = count;
      }
      count++;
    }
    if (result) {
      this.permissionSaveFinalJson[index] = mainjson;
    } else {
      this.permissionSaveFinalJson.push(mainjson);
    }
  }
  fnSetVal(e, flag) {
    let updateEvent = e + '';
    if (!isNullOrUndefined(e) && updateEvent !== "") {
      debugger;
      // var table = $('#map').dataTable();
      // table.$('input[type="checkbox"]').each(function () {
      //   $(this).prop('checked', false);
      // });
      this.permissionForm.controls[flag].setValue(e.id);
      this.getPermissionData(e.id);
    } else {
      this.permissionForm.controls[flag].setValue(null);
      setTimeout(function () {
        var table = $('#map').dataTable();
        table.$('input[type="checkbox"]').each(function () {
          $(this).prop('checked', false);
        });
      }, 300);
    }
  }

  reduceFinalJson() {
    //this.permissionSaveFinalJson = this.tempVar;
    console.log(this.permissionSaveFinalJson);
    debugger;
    let count = 0;
    let index = [];
    let result = false;
    for (const obj of this.permissionSaveFinalJson) {
      this.permissionForm.controls.roleId.value;
      obj['manageroles'] = { "id": this.permissionForm.controls.roleId.value };
      if (obj['canSave'] == false && obj['canEdit'] == false && obj['canDelete'] == false) {
        result = true;
        index.push(count);
      }
      count++;
    }
    if (result) {
      for (const obj of index) {
        this.permissionSaveFinalJson.splice(obj, 1);
      }
    }
    return this.permissionSaveFinalJson;
  }

  canSave = false;
  createEditDeleteButton(fullData) {
    let mainId = fullData['roleId'];
    let editBtn = '';
    let deleteBtn = '';
    let userPermission = JSON.parse(localStorage.getItem('currentUser'))['permissions'];
    let mainpermissionJson = {};
    userPermission.forEach(element => {
      if (element.pageName == 'Manage_Permission') {
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
    if (this.setOption == 'Add') {
      this.pageGridReady();
    }
  }

  pageGridReady() {
    //this.pagesGrid.rows = [];
  }

  ngOnInit() {
    this.canSave = this.httpclientService.cheackCanSavePermission('Manage_Permission');
    PermissionComponent.self = this;
    this.datatable.rows = [];
    this.permissionForm = this.formBuilder.group({
      roleId: [null, Validators.required],
    });

    this.setMainGrid();
    this.getAllPages();
    let drpdownPaths = ['manageroles|id|roleName'];
    this.prepareDrpDownData(drpdownPaths);
  }
  forcefullySaveRightOfPermissionPage() {
    if (this.permissionForm.value.roleId !== null) {
      this.httpclientService.get('managepermissions/search/forcefullysaverightofpermissionpage/' + this.permissionForm.value.roleId).pipe(first()).subscribe(
        data => {
          if (data['canSave'] == true) {
            this.tempVar = [];
            this.permissionSaveFinalJson = [];
            this.loading = false;
            this.submitted = false;
            this.alertService.successSaveUpdate('');
            this.setOption = 'Grid';
            this.setMainGrid();
          }
          // if (isUndefined(this.tableComponent)) {
          //   this.tableComponent = new TableAppComponent();
          // }
          // this.tableComponent.rerender();
        },
        error => {
          this.loading = false;
        });
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

  getAllPages() {
    let allpages = ['Risk_Incident', 'MIS', 'Risk_Register', 'Asset_Register', 'Document', 'Manage_Role', 'Manage_User', 'Manage_Permission'
      , 'Category', 'CCY', 'Residual_Risk_Likelihood', 'Risk_Status', 'Event_Type', 'Event_Status', 'Risk_Category_Level_1', 'Risk_Category_Level_2',
      'Residual_Impact_Assessment', 'Inherent_Risk_Likelihood', 'Inherent_Impact_Assessment'];
    var count = 0;
    allpages.forEach(element => {
      let tempjson = {};
      count++;
      tempjson['id'] = count;
      tempjson['pageName'] = element;
      this.pagesarray.push(tempjson);
    });
    this.pagesGrid.rows = this.pagesarray;
  }

  modifyJsonCount(jsonArr) {
    let count = 0;
    jsonArr.forEach(element => {
      count++;
      element['count'] = count;
    });
  }

  // convenience getter for easy access to form fields
  get f() { return this.permissionForm.controls; }

  onSubmit() {

    this.submitted = true;
    let jsonArray = this.reduceFinalJson();
    debugger;
    this.loading = true;
    this.httpclientService.put(jsonArray, 'managepermissions/batch')
      .pipe(first())
      .subscribe(
        data => {
          this.tempVar = [];
          this.permissionSaveFinalJson = [];
          this.loading = false;
          this.submitted = false;
          this.alertService.successSaveUpdate('');
          this.setOption = 'Grid';
          this.setMainGrid();
        },
        error => {
          this.alertService.error(error);
          this.submitted = false;
          this.loading = false;
          this.setOption = 'Grid';
        });

  }
  refilldrpRole123(roleId) {

    for (const obj of this.preparedRoleOptions) {
      if (obj['id'] == roleId) {
        this.drpRolevalue = obj;
      }
    }
  }
  tempVar = [];
  getPermissionData(roleId) {
    this.setOption = "Add";
    this.httpclientService.get('managepermissions/search/getPermissionDataByManageRoleID/' + roleId).pipe(first()).subscribe(
      data => {
        this.permissionSaveFinalJson = [];
        for (const obj of data['content']) {
          let tempjson = {};

          tempjson['canSave'] = obj['canSave'];
          tempjson['canEdit'] = obj['canEdit'];
          tempjson['canDelete'] = obj['canDelete'];
          tempjson['manageroles'] = { "id": obj['manageroles']['id'] };
          tempjson['managepages'] = obj['managepages'];
          this.permissionSaveFinalJson.push(tempjson);
        }
        setTimeout(function () {
          debugger;

          var table = $('#map').dataTable();
          table.$('input[type="checkbox"]').each(function () {
            $(this).prop('checked', false);
          });

          for (const obj of data['content']) {
            var keys = [];
            let tempjson = {};
            tempjson['id'] = obj['id'];
            tempjson['canSave'] = obj['canSave'];
            tempjson['canEdit'] = obj['canEdit'];
            tempjson['canDelete'] = obj['canDelete'];
            tempjson['manageroles'] = { "id": obj['manageroles']['id'] };
            tempjson['managepages'] = obj['managepages'];

            for (var k in obj) k !== "id" &&
              k !== "version"
              && k !== "manageroles" &&
              k !== "managepages" ? keys.push(k) : '';

            for (const key of keys) {
              let makeclassName = obj['managepages'] + key;
              table.$('.' + makeclassName).prop('checked', obj[key]);
            }
            //  this.permissionSaveFinalJson.push(tempjson);
            PermissionComponent.self.refilldrpRole123(roleId);
          }

        }, 3000);
        // this.tempVar = this.permissionSaveFinalJson;
      },
      error => {
        this.alertService.error(error);
        this.loading = false;
      });



  }
  formSubmitload() {
    this.loading = true;
    setTimeout(function () {
      PermissionComponent.self.loading = false;
      PermissionComponent.self.changeMenu('Grid');
    }, 3000);
  }

  findRolewisePages(wholearray, roleid) {
    let mainstring = '-';
    if (!isNullOrUndefined(roleid) && !isNullOrUndefined(wholearray)) {
      wholearray.forEach(element => {
        let mainroleobj = element['manageroles'];
        if (mainroleobj.id == roleid) {
          let rights = PermissionComponent.self.checkEditDeleteSaveRights(element);
          let span = '<span class="badge badge-primary" style=" margin-right: 2px;font-size: 13px;cursor: pointer;" title="'
            + rights + '">' + element['managepages'] + '</span>';
          mainstring = mainstring == '-' ? span : mainstring + span;
        }
      });
    }
    return mainstring;
  }

  checkEditDeleteSaveRights(json) {
    let maketitle = '-';
    let rightsArray = ['Save', 'Edit', 'Delete'];
    rightsArray.forEach(element => {
      var key = 'can' + element;
      if (json.hasOwnProperty(key) && json[key] == true) {
        maketitle = maketitle == '-' ? element : maketitle + ', ' + element;
      }
    });
    return maketitle;
  }

  hasAllreadyRoleId(mainArry, roleid) {
    let status = false;
    let mainstatus = false;

    mainArry.forEach(element => {
      if (element.roleId == roleid) {
        mainstatus = true;
      }
    });
    status = mainstatus;
    return status;
  }

  setMainGrid() {
    this.httpclientService.get('managepermissions').pipe(first()).subscribe(
      data => {
        let content = data['content'];
        let mainresponsecontent = [];
        let count = 0;
        content.forEach(function (sandwich, index) {
          if (!PermissionComponent.self.hasAllreadyRoleId(mainresponsecontent, sandwich.manageroles.id)) {
            let tempJson = {};
            count++;
            tempJson['id'] = count;
            tempJson['roleName'] = sandwich.manageroles.roleName;
            tempJson['roleId'] = sandwich.manageroles.id;
            tempJson['authrisedPages'] = PermissionComponent.self.findRolewisePages(content, tempJson['roleId']);
            mainresponsecontent.push(tempJson);
          }

        });
        this.datatable.rows = mainresponsecontent;
        if (isUndefined(this.tableComponent)) {
          this.tableComponent = new TableAppComponent();
        }
        this.tableComponent.rerender();
      },
      error => {
        //this.alertService.error(error);
        this.loading = false;
      });
  }
  deleteAllMapping(roleId) {
    this.httpclientService.delete('permissions/search/deleteRoleWiseMapping/' + roleId).pipe(first()).subscribe(
      data => {
        this.alertService.deleteSuccessMsg();
        this.setOption = 'Grid';
        this.setMainGrid();
      },
      error => {
        this.alertService.error(error);
        this.loading = false;
      });
  }

}