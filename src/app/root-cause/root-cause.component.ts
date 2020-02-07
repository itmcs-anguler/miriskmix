import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { CommonUtility } from '@app/_services/common.service';
import { HttpClientService } from '@app/_services/httpClient.service';
import { AlertService } from '@app/_services';
import { first } from 'rxjs/operators';

@Component({
  selector: 'app-root-cause',
  templateUrl: './root-cause.component.html',
  styleUrls: ['./root-cause.component.css']
})
export class RootCauseComponent implements OnInit {
  public static self: RootCauseComponent;
  riskIncidentId;
  rootCauseForm: FormGroup;
  canSave = false;

  constructor(private formBuilder: FormBuilder,
    private commonUtility: CommonUtility,
    private httpclientService: HttpClientService,
    private alertService: AlertService) { }

  loading = false;

  ngOnInit() {
    this.canSave = this.httpclientService.cheackCanSavePermission('Risk_Incident');
    this.riskIncidentId = sessionStorage.getItem('fkRiskIncident');
    this.rootCauseForm = this.formBuilder.group({
      rootCauseName: ['', Validators.required],
      riskIncident: ['']
    });
  }
  resetRootCauseForm() {
    $('#rootCause').val('');
  }
  get root() { return this.rootCauseForm.controls; }

  rootCauseSubmitted = false;
  onSubmitRootCause() {
    this.rootCauseForm.controls['riskIncident'].setValue({ id: this.riskIncidentId });
    console.log('On Submit Root Cause == > ');
    this.rootCauseSubmitted = true;
    // stop here if form is invalid
    if (this.rootCauseForm.invalid) {
      return;
    }
    this.loading = true;
    this.httpclientService.post(this.rootCauseForm.value, 'rootcauses')
      .pipe(first())
      .subscribe(
        data => {
          this.rootCauseSubmitted = false;
          this.loading = false;
          this.alertService.successSaveUpdate('Successfully Saved');
          console.log('Data Saved Successfully');
          this.resetRootCauseForm();
        },
        error => {
          this.rootCauseSubmitted = false;
          this.alertService.error(error);
          this.loading = false;
        });

  }
}
