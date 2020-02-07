import { Component, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { CommonUtility } from '@app/_services/common.service';
import { HttpClientService } from '@app/_services/httpClient.service';
import { AlertService } from '@app/_services';
import { TableAppComponent } from '@app/table-app/table-app.component';
import { isUndefined } from 'util';
import { first } from 'rxjs/operators';
import * as fileSaver from 'file-saver';
@Component({
  selector: 'app-additional-details',
  templateUrl: './additional-details.component.html',
  styleUrls: ['./additional-details.component.css']
})
export class AdditionalDetailsComponent implements OnInit {
  public static self: AdditionalDetailsComponent;
  mainPathAdditional = 'additionaldetailss';
  additionalDocumentId = '';
  loading = false;
  additionalSubmitted = false;
  additionalForm: FormGroup;
  tempDocUrl = '#';
  tempDocName = '';
  editDoc = false;
  uploadNewDoc = '';
  makeCustomJson;
  formData: FormData = new FormData();
  attechmentPath = '';
  datatableDocument = {
    title: 'Document',
    cols: [
      { title: 'ID', data: 'uniqueId' },
      { title: 'File Name', data: 'fileName' },
      { title: 'Document Title', data: 'documentTitle' },
      { title: 'Document Owner', data: 'documentOwner' },
      {
        title: 'Action', render: function (data: any, type: any, full: any) {
          return AdditionalDetailsComponent.self.createEditDeleteButton(full);
        }
      }
    ],
    rows: [],
    custominput: false,
    customcols: []
  };

  setOptionSub = 'Document';
  @ViewChild(TableAppComponent, { static: false })
  private tableComponentDocument: TableAppComponent;

  constructor(private formBuilder: FormBuilder,
    private commonUtility: CommonUtility,
    private httpclientService: HttpClientService,
    private alertService: AlertService) { }
  riskIncidentId;
  ngOnInit() {
    this.canSave = this.httpclientService.cheackCanSavePermission('Risk_Incident');
    this.riskIncidentId = sessionStorage.getItem('fkRiskIncident');
    this.makeCustomJson = { 'riskIncidentId': this.riskIncidentId };
    AdditionalDetailsComponent.self = this;

    this.additionalForm = this.formBuilder.group({
      fileName: ['', Validators.required],
      documentTitle: ['', Validators.required],
      documentOwner: ['', Validators.required],
      attachment: [''],
      uniqueId: [''],
      riskIncident: [this.riskIncidentId]
    });
    this.getAllDataAdditionDocumentDetails();
  }

  exportAsXLSX(): void {
    this.commonUtility.exportExcel(this.additionalForm.value, 'Additional_Form');
  }

  printAsPdf(): void {
    this.commonUtility.generatePdf(this.additionalForm.value, 'Additional_Form', this.commonUtility.printdownloadpdfflag);
  }

  canSave = false;
  createEditDeleteButton(fullData) {
    let mainId = fullData['id'];
    let editBtn = '';
    let deleteBtn = '';
    let userPermission = JSON.parse(localStorage.getItem('currentUser'))['permissions'];
    let mainpermissionJson = {};
    userPermission.forEach(element => {
      if (element.pageName == 'Risk_Incident') {
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

  /* createEditDeleteButton(fullData) {
    let mainId = fullData['id'];
    let editBtn = '<i control="riskevent" class="fa fa-edit primary editcustom" value="' + mainId + '" style="margin-right: 14px;color: #ed5c0e;  font-size: 20px;font - weight: 500;cursor: pointer;" ></i>';
    let deleteBtn = '<i control="riskevent" class="fa fa-trash deletecustom" value="' + mainId + '" style="color: #ff001f;  font-size: 20px;font - weight: 500;cursor: pointer;" ></i>';

    return editBtn + '&nbsp;' + deleteBtn;
  } */

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
    console.log('Fn Download :: ');
    console.log('Downlaod Path :: ' + downloadPath);
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

  get a() { return this.additionalForm.controls; }

  modifyJsonCount(jsonArr) {
    let count = 0;
    jsonArr.forEach(element => {
      count++;
      element['count'] = count;
    });
  }

  getAllDataAdditionDocumentDetails() {
    this.httpclientService.get(this.mainPathAdditional + '/search/findbyRiskIncident/' + JSON.stringify(this.makeCustomJson)).subscribe(
      data => {
        this.modifyJsonCount(data['content']);
        let responsecontent = data['content'];
        this.datatableDocument.rows = responsecontent;
        if (isUndefined(this.tableComponentDocument)) {
          this.tableComponentDocument = new TableAppComponent();
        }
        this.tableComponentDocument.customDatatableobj = this.datatableDocument;
        this.tableComponentDocument.rerender();
      },
      error => {
        this.alertService.error(error);
        this.loading = false;
      });
  }
  changeDocument() {
    this.setOptionSub = 'show';
  }
  cancelDocument() {
    this.setOptionSub = 'Document';
  }

  fillFormAdditionDocument(json) {
    if (this.commonUtility.checkVal(json['attachment']) != '') {
      this.tempDocUrl = json['attachment'];
      this.tempDocName = json['attachment'].replace(/^.*[\\\/]/, '');
      this.editDoc = true;
    }
    this.editDoc = json == '' ? false : true;
    this.additionalDocumentId = this.commonUtility.checkVal(json['id']);
    this.additionalForm = this.formBuilder.group({
      id: [this.commonUtility.checkVal(this.additionalDocumentId)],
      fileName: [this.commonUtility.checkVal(json['fileName']), Validators.required],
      documentTitle: [this.commonUtility.checkVal(json['documentTitle']), Validators.required],
      documentOwner: [this.commonUtility.checkVal(json['documentOwner']), Validators.required],
      attachment: [this.commonUtility.checkVal(json['attachment'])],
      uniqueId: [this.commonUtility.checkVal(json['uniqueId'])],
      riskIncident: [this.commonUtility.checkVal(json['riskIncident'])]
    });
  }
  fnDeleteAdditionDocument(id) {
    this.httpclientService.delete(this.mainPathAdditional + '/' + id).pipe(first()).subscribe(
      data => {
        this.alertService.deleteSuccessMsg();
        this.getAllDataAdditionDocumentDetails();
      },
      error => {
        this.alertService.error(error, false);
        this.loading = false;
      });
  }
  fnEditAdditionDocument(id) {
    console.log('fnEditAdditionDocument ::: === >>>>> ');
    this.httpclientService.get(this.mainPathAdditional + '/' + id).pipe(first()).subscribe(
      data => {
        this.fillFormAdditionDocument(data);
        this.changeDocument();
      },
      error => {
        this.alertService.error(error, false);
        this.loading = false;
      });
  }
  updatedate = [];
  filterDatabyId(data) {
    console.log('filterDatabyId==============>');
    data.forEach(element => {
      if (element['riskIncident']['id'] == this.riskIncidentId) {
        this.updatedate.push(element);
        console.log(element);
      }
    });
    return this.updatedate;
  }
  onSubmit() {
    this.additionalForm.controls['riskIncident'].setValue({ id: this.riskIncidentId });
    let fileattechment = '';
    if (this.uploadNewDoc != '' && this.tempDocName != this.uploadNewDoc) {
      this.httpclientService.uploadDocument(this.formData, 'uploadFile').pipe(first()).subscribe(
        data => {
          console.log('File Name :: ' + data['filename']);
          fileattechment = data['filename'];
          this.attechmentPath = fileattechment;
          // this.additionalForm.controls['attachment'].setValue(fileattechment);
          this.onSubmitAdditional();
        });
    } else {
      fileattechment = this.tempDocUrl;
      this.attechmentPath = fileattechment;
      //this.additionalForm.controls['attachment'].setValue(fileattechment);
      this.onSubmitAdditional();
    }
  }

  onSubmitAdditional() {

    console.log('======>');
    this.additionalSubmitted = true;
    // stop here if form is invalid
    if (this.additionalForm.invalid) {
      return;
    }
    this.loading = true;
    let flag = this.additionalDocumentId == '' ? 'save' : 'update';
    if (flag == 'save') {
      this.additionalForm.controls['uniqueId'].setValue(this.commonUtility.genRandom());
    }

    console.log('======>');
    console.log(this.additionalForm.controls['attachment'].value);
    this.additionalForm.controls['attachment'].setValue(this.attechmentPath);

    let saveUpdatePath = this.additionalDocumentId == '' ? this.mainPathAdditional : this.mainPathAdditional + '/' + this.additionalDocumentId;
    this.httpclientService.saveOrUpdate(this.additionalForm.value, saveUpdatePath, flag)
      .pipe(first())
      .subscribe(
        data => {
          this.loading = false;
          this.alertService.successSaveUpdate('');
          this.getAllDataAdditionDocumentDetails();
          this.cancelDocument();
        },
        error => {
          this.alertService.error(error);
          this.loading = false;
          this.cancelDocument();
        });
  }

}
