import { Component, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthenticationService, AlertService, UserService } from '@app/_services';
import { first } from 'rxjs/operators';
import { TableAppComponent } from '@app/table-app/table-app.component';
import { CommonUtility } from '@app/_services/common.service';
import { HttpClientService } from '@app/_services/httpClient.service';
import { isUndefined } from 'util';
import * as fileSaver from 'file-saver';

@Component({
  selector: 'app-document',
  templateUrl: './document.component.html',
  styleUrls: ['./document.component.css']
})
export class DocumentComponent implements OnInit {
  public static self: DocumentComponent;
  documentForm: FormGroup;
  loading = false;
  submitted = false;
  returnUrl: string;
  setOption = 'Grid';
  operation = 'Add';
  id = '';
  mainpath = 'documents';
  tempDocUrl = '#';
  tempDocName = '';
  editDoc = false;
  uploadNewDoc = '';
  formData: FormData = new FormData();

  @ViewChild(TableAppComponent, { static: false })
  private tableComponent: TableAppComponent;

  datatable = {
    title: 'Document',
    cols: [
      { title: 'ID', data: 'count' },
      { title: 'Name', data: 'documentName' },
      { title: 'Owner', data: 'owner' },
      { title: 'Next Review Date', data: 'nextReviewDate' },
      {
        title: 'Status', render: function (data: any, type: any, full: any) {
          return DocumentComponent.self.crateStausElement(full);
        }
      },
      { title: 'Document Upload Date & Time', data: 'documentUploadDateTime' },
      {
        title: 'Action', render: function (data: any, type: any, full: any) {
          return DocumentComponent.self.createEditDeleteButton(full);
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

  crateStausElement(fullData) {
    let mainDate = fullData['nextReviewDate'];
    let statusFlag = new Date(mainDate.split('-')[2], mainDate.split('-')[1] - 1, mainDate.split('-')[0]) < new Date();
    if (statusFlag) {
      return '<span style="color:red;font-weight: 600;text - align: center;">OverDue</sapn>';
    } else {
      return '<span style="color:green;font-weight: 600;text - align: center;">Not OverDue</sapn>';
    }
  }

  exportAsXLSX():void {
    this.commonUtility.exportExcel(this.documentForm.value, 'Document');
 }

 printAsPdf():void {
	this.commonUtility.generatePdf(this.documentForm.value, 'Document', this.commonUtility.printdownloadpdfflag);
  }

  canSave = false;
  createEditDeleteButton(fullData) {
    let mainId = fullData['id'];
    let editBtn = '';
    let deleteBtn = '';
    let userPermission = JSON.parse(localStorage.getItem('currentUser'))['permissions'];
    let mainpermissionJson = {};
    userPermission.forEach(element => {
      if (element.pageName == 'Document') {
        mainpermissionJson = element;
      }
    });
    this.canSave = mainpermissionJson['canSave'];
    if (mainpermissionJson['canEdit']) {
      editBtn = '<i control="categories" class="fa fa-edit primary editcustom" value="' + mainId + '" style="margin-right: 14px;color: #ed5c0e;  font-size: 20px;font - weight: 500;cursor: pointer;" ></i>';
    }
    if (mainpermissionJson['canDelete']) {
      deleteBtn = '<i control="categories" class="fa fa-trash deletecustom" value="' + mainId + '" style="margin-right: 14px;color: #ff001f;  font-size: 20px;font - weight: 500;cursor: pointer;" ></i>';
    }
   
    let attachmentpath = fullData['attachmentpath'];
    let downloadBtn = '<i class="fa fa-download downloadcustom" value="' + attachmentpath + '" style="color: #4169e1;  font-size: 20px;font - weight: 500;cursor: pointer;" ></i>';
    return editBtn + '&nbsp;' + deleteBtn + '&nbsp;' + downloadBtn;
  }

 /*  createEditDeleteButton(fullData) {
    let mainId = fullData['id'];
    let attachmentpath = fullData['attachmentpath'];
    let editBtn = '<i class="fa fa-edit primary editcustom" value="' + mainId + '" style="margin-right: 14px;color: #ed5c0e;  font-size: 20px;font - weight: 500;cursor: pointer;" ></i>';
    let deleteBtn = '<i class="fa fa-trash deletecustom" value="' + mainId + '" style="margin-right: 14px;color: #ff001f;  font-size: 20px;font - weight: 500;cursor: pointer;" ></i>';
    let downloadBtn = '<i class="fa fa-download downloadcustom" value="' + attachmentpath + '" style="color: #4169e1;  font-size: 20px;font - weight: 500;cursor: pointer;" ></i>';
    return editBtn + '&nbsp;' + deleteBtn + '&nbsp;' + downloadBtn;
  } */

  onSubmit() {

    let fileattechment = '';
    if (this.uploadNewDoc != '' && this.tempDocName != this.uploadNewDoc) {
      this.httpclientService.uploadDocument(this.formData, 'uploadFile').pipe(first()).subscribe(
        data => {
          fileattechment = data['filename'];
          this.documentForm.controls['attachment'].setValue(fileattechment);
          this.saveDocumentData();
        });
    } else {
      fileattechment = this.tempDocUrl;
      this.documentForm.controls['attachment'].setValue(fileattechment);
      this.saveDocumentData();
    }
  }

  saveDocumentData() {
    this.submitted = true;
    if (this.documentForm.invalid) {
      return;
    }
    this.loading = true;
    let flag = this.id == '' ? 'save' : 'update';
    if (flag == 'save') {
      this.documentForm.controls['uniqueId'].setValue(this.commonUtility.genRandom());
    }
    let saveUpdatePath = this.id == '' ? this.mainpath : this.mainpath + '/' + this.id;
    this.httpclientService.saveOrUpdate(this.documentForm.value, saveUpdatePath, flag)
      .pipe(first())
      .subscribe(
        data => {
          this.loading = false;
          this.alertService.successSaveUpdate('');
          this.getAllData();
          this.setOption = 'Grid';
        },
        error => {
          this.alertService.error(error);
          this.loading = false;
          this.setOption = 'Grid';
        });

  }

  changeMenu(flag) {
    this.setOption = flag;
  }

  fillForm(json) {
    if (this.commonUtility.checkVal(json['attachment']) != '') {
      this.tempDocUrl = json['attachment'];
      this.tempDocName = json['attachment'].replace(/^.*[\\\/]/, '');
      this.editDoc = true;
    }
    this.editDoc = json == '' ? false : true;
    this.id = this.commonUtility.checkVal(json['id']);
    this.documentForm = this.formBuilder.group({
      id: [this.commonUtility.checkVal(json['id'])],
      documentName: [this.commonUtility.checkVal(json['documentName']), Validators.required],
      owner: [this.commonUtility.checkVal(json['owner']), Validators.required],
      nextReviewDate: [json['nextReviewDate'], Validators.required],
      attachment: [this.commonUtility.checkVal(json['attachment'])],
      status: [this.commonUtility.checkVal(json['status'])],
      documentUploadDateTime: [this.commonUtility.checkVal(json['documentUploadDateTime'])],
      uniqueId: [this.commonUtility.checkVal(json['uniqueId'])],
    });
  }



  ngOnInit() {
    this.canSave = this.httpclientService.cheackCanSavePermission('Document');
    DocumentComponent.self = this;
    this.fillForm('');
    this.getAllData();
    this.documentForm = this.formBuilder.group({
      documentName: ['', Validators.required],
      owner: ['', Validators.required],
      nextReviewDate: ['', Validators.required],
      attachment: ['', Validators.required],
      uniqueId: ['']
    });
  }

  modifyJsonCount(jsonArr) {
    let count = 0;
    jsonArr.forEach(element => {
      count++;
      element['count'] = count;
    });
  }

  get f() { return this.documentForm.controls; }

  getAllData() {
    this.httpclientService.get(this.mainpath).pipe(first()).subscribe(
      data => {
        console.log(data);
        for (let index = 0; index < data['content'].length; index++) {
          data['content'][index]['nextReviewDate'] = this.commonUtility.fnDateFormatter(data['content'][index]['nextReviewDate']);
          data['content'][index]['attachmentpath'] = data['content'][index]['attachment'];
          var filename = data['content'][index]['attachment'].replace(/^.*[\\\/]/, '');
          data['content'][index]['attachment'] = filename;
          data['content'][index]['documentUploadDateTime'] = new Date(data['content'][index]['documentUploadDateTime']).toUTCString();
        }
        this.modifyJsonCount(data['content']);
        let responsecontent = data['content'];
        console.log(responsecontent);
        this.datatable.rows = responsecontent;
        if (isUndefined(this.tableComponent)) {
          this.tableComponent = new TableAppComponent();
        }
        this.tableComponent.customDatatableobj = this.datatable;
        this.tableComponent.rerender();
        this.editDoc = false;
      },
      error => {
        this.alertService.error(error);
        this.loading = false;
      });
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

  fileChange(event, key) {
    const fileList: FileList = event.target.files;
    if (fileList.length > 0) {
      const file: File = fileList[0];
      this.formData.set(key, file, file.name);
      this.uploadNewDoc = file.name;
      $('.custom-file-label').html(file.name);
    }
  }

  fnDownload(downloadPath) {
    this.loading = true;
    this.httpclientService.downloadDocument(downloadPath).pipe(first()).subscribe(
      data => {
        const filename = data.url.split("/")[data.url.split("/").length - 1];
        this.saveFile(data.body, filename);
        this.loading = false;
      },
      error => {
        this.alertService.customErrorMessage(downloadPath.split("/")[downloadPath.split("/").length - 1] + ' file is not avaliable on the server.');
        this.loading = false;
      });
  }

  saveFile(data: any, filename?: string) {
    const blob = new Blob([data], { type: ' charset=utf-8' });
    fileSaver.saveAs(blob, filename);
  }

  loadFile(data: any, filename?: string) {
    const blob = new Blob([data], { type: ' charset=utf-8' });
    const file = new File([blob], "filename");
    this.formData.set('file', file, file.name);
  }


}
