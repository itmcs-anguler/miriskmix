import { Component, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { TableAppComponent } from '@app/table-app/table-app.component';
import { AlertService, AuthenticationService } from '@app/_services';
import { HttpClientService } from '@app/_services/httpClient.service';
import { isUndefined } from 'util';
import { first } from 'rxjs/operators';
import { CommonUtility } from '@app/_services/common.service';

@Component({
  selector: 'app-roles',
  templateUrl: './roles.component.html',
  styleUrls: ['./roles.component.css']
})
export class RolesComponent implements OnInit {
  public static self: RolesComponent; 
  mainpath = 'manageroles';
  roleid = '';
  roleForm: FormGroup;
  loading = false;
  submitted = false;
  returnUrl: string;
  setOption = 'Grid';
  operation = 'Add';
  mainHead: string = "Role";
  @ViewChild(TableAppComponent, { static: false })
  private tableComponent: TableAppComponent;
  datatable = {
    title: 'Role',
    cols: [
      { title: 'ID', data: 'count' },
      { title: 'Role Name', data: 'roleName' },
      { title: 'Role Description', data: 'roleDescription' },
      { title: 'Action', render: function (data: any, type: any, full: any) {
          return RolesComponent.self.createEditDeleteButton(full);
        }
      }
    ],
    rows: [],
    custominput: false,
    customcols: []
  };

  exportAsXLSX():void {
    this.commonUtility.exportExcel(this.roleForm.value, 'Manage_Role');
 }

 printAsPdf():void {
	this.commonUtility.generatePdf(this.roleForm.value, 'Manage_Role', this.commonUtility.printdownloadpdfflag);
  }

  canSave = false;
  createEditDeleteButton(fullData) {
    let mainId = fullData['id'];
    let editBtn = '';
    let deleteBtn = '';
    let userPermission = JSON.parse(localStorage.getItem('currentUser'))['permissions'];
    let mainpermissionJson = {};
    userPermission.forEach(element => {
      if (element.pageName == 'Manage_Role') {
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
    private router: Router,
    private authenticationService: AuthenticationService,
    private httpclientService: HttpClientService,
    private alertService: AlertService,
    private commonUtility: CommonUtility) {
  }

  changeMenu(flag) {
    this.setOption = flag;
  }

  ngOnInit() {
    this.canSave = this.httpclientService.cheackCanSavePermission('Manage_Role');
    RolesComponent.self = this;
    this.getAllData();
    this.fillForm('');  
    this.roleForm = this.formBuilder.group({
      roleName: ['', Validators.required],
      roleDescription: ['', Validators.required]
    });
  }

  // convenience getter for easy access to form fields
  get f() { return this.roleForm.controls; }

  onSubmit() {
    this.submitted = true;
    // stop here if form is invalid
    if (this.roleForm.invalid) {
      return;
    }
    this.loading = true;

    let flag = this.roleid == '' ? 'save' : 'update';
    let saveUpdatePath = this.roleid == '' ? this.mainpath : this.mainpath + '/' + this.roleid;

    this.httpclientService.saveOrUpdate(this.roleForm.value, saveUpdatePath, flag)
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

  modifyJsonCount(jsonArr) {
    let count = 0;
    jsonArr.forEach(element => {
      count++;
      element['count'] = count;
    });
  }

  fillForm(json) {
   this.roleid = this.checkVal(json['id']);
    this.roleForm = this.formBuilder.group({
      id: [this.checkVal(json['id'])],
      roleName: [this.checkVal(json['roleName']), Validators.required],
      roleDescription: [this.checkVal(json['roleDescription']), Validators.required]
    });
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
    this.httpclientService.get(this.mainpath + '/' + id).pipe(first()).subscribe(
      data => {
        this.fillForm(data);
        this.changeMenu('Add');
      },
      error => {
        this.alertService.error(error, false);
        this.loading = false;
      });
  }

}
