import { Component, OnInit, ViewChild } from '@angular/core';
import { Validators, FormBuilder, FormGroup } from '@angular/forms';
import { UserService, AlertService } from '@app/_services';
import { first } from 'rxjs/operators';
import { TableAppComponent } from '@app/table-app/table-app.component';
import { HttpClientService } from '@app/_services/httpClient.service';
import { isUndefined } from 'util';
import { CommonUtility } from '@app/_services/common.service';

@Component({
  selector: 'app-asset-register',
  templateUrl: './asset-register.component.html',
  styleUrls: ['./asset-register.component.css']
})
export class AssetRegisterComponent implements OnInit {
  public static assetRegistrationOption = 'search';
  public static self: AssetRegisterComponent;
  assestForm: FormGroup;
  loading = false;
  submitted = false;
  returnUrl: string;
  setOption = 'Grid';
  operation = 'Add';
  mainpath = 'assetregisters';
  id = '';

  @ViewChild(TableAppComponent, { static: false })
  private tableComponent: TableAppComponent;

  datatable = {
    title: 'Asset Register',
    cols: [
      { title: 'ID', data: 'count' },
      { title: 'Asset Name', data: 'assetName' },
      { title: 'Description', data: 'description' },
      { title: 'Location', data: 'location' },
      { title: 'Used For', data: 'usedFor' },
      { title: 'Procurement Date', data: 'procurementDate' },
      { title: 'Procurement Price', data: 'procurementPrice' },
      { title: 'Depreciation', data: 'depreciation' },
      { title: 'Current Value', data: 'currentValue' },
      {
        title: 'Action', render: function (data: any, type: any, full: any) {
          return AssetRegisterComponent.self.createEditDeleteButton(full);
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

  changeMenu(flag) {
    this.setOption = flag;
    if(flag == 'Grid'){
      AssetRegisterComponent.assetRegistrationOption = 'search';
    }    
    if (flag == 'Add') {
      AssetRegisterComponent.assetRegistrationOption = 'add';
    }   
  }
  
  canSave = false;
  createEditDeleteButton(fullData) {
    let mainId = fullData['id'];
    let editBtn = '';
    let deleteBtn = '';
    let userPermission = JSON.parse(localStorage.getItem('currentUser'))['permissions'];
    let mainpermissionJson = {};
    userPermission.forEach(element => {
      if (element.pageName == 'Asset_Register') {
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

  exportAsXLSX():void {
    this.commonUtility.exportExcel(this.assestForm.value, 'Asset_Register');
  }

  printAsPdf():void {
	  this.commonUtility.generatePdf(this.assestForm.value, 'Asset_Register', this.commonUtility.printdownloadpdfflag);
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
        for (let index = 0; index < data['content'].length; index++) {
          data['content'][index]['procurementDate'] = this.commonUtility.fnDateFormatter(data['content'][index]['procurementDate']);
        }

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
    this.id = this.checkVal(json['id']);
    this.assestForm = this.formBuilder.group({
      id: [this.checkVal(json['id'])],
      assetName: [this.checkVal(json['assetName']), Validators.required],
      description: [this.checkVal(json['description']), Validators.required],
      location: [this.checkVal(json['location']), Validators.required],
      usedFor: [this.checkVal(json['usedFor']), Validators.required],
      procurementDate: [this.checkVal(json['procurementDate']), Validators.required],
      procurementPrice: [this.checkVal(json['procurementPrice']), Validators.required],
      depreciation: [this.checkVal(json['depreciation']), Validators.required],
      currentValue: [this.checkVal(json['currentValue']), Validators.required]

    });
  }

  checkVal(value) {
    if (value != '' && value != null && value != undefined) {
      return value;
    } else {
      return '';
    }
  }
  ngOnInit() {
    this.canSave = this.httpclientService.cheackCanSavePermission('Asset_Register');
    if(AssetRegisterComponent.assetRegistrationOption == 'add'){
      this.changeMenu('Add');
    }
    
    AssetRegisterComponent.self = this;
    this.getAllData();
    this.fillForm('');

    //this.datatable.rows = this.preparecoldataforExample();

    this.assestForm = this.formBuilder.group({
      assetName: ['', Validators.required],
      description: ['', Validators.required],
      location: ['', Validators.required],
      usedFor: ['', Validators.required],
      procurementDate: ['', Validators.required],
      procurementPrice: ['', Validators.required],
      depreciation: ['', Validators.required],
      currentValue: ['', Validators.required],
    });
  }
  get f() { return this.assestForm.controls; }
  onSubmit() {
    this.submitted = true;
    // stop here if form is invalid
    if (this.assestForm.invalid) {
      return;
    }
    this.loading = true;

    let flag = this.id == '' ? 'save' : 'update';
    let saveUpdatePath = this.id == '' ? this.mainpath : this.mainpath + '/' + this.id;

    this.httpclientService.saveOrUpdate(this.assestForm.value, saveUpdatePath, flag)
      .pipe(first())
      .subscribe(
        data => {
          this.submitted = false;
          this.loading = false;
          this.alertService.successSaveUpdate('');
          this.getAllData();
          this.fillForm('');
          this.setOption = 'Grid';
        },
        error => {
          this.alertService.error(error);
          this.loading = false;
          this.setOption = 'Grid';
        });
  }

}
