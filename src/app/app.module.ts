import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { AngularFontAwesomeModule } from 'angular-font-awesome';



import { AppComponent } from './app.component';
import { routing } from './app.routing';

import { AlertComponent } from './_components';
import { JwtInterceptor, ErrorInterceptor } from './_helpers';
import { HomeComponent } from './home';
import { LoginComponent } from './login';
import { RegisterComponent } from './register';
import { DashboardComponent } from './dashboard/dashboard.component';
import { NavbarComponent } from './navbar/navbar.component';
import { FooterComponent } from './footer/footer.component';
import { RiskEventComponent } from './risk-event/risk-event.component';
import { RiskRegisterComponent } from './risk-register/risk-register.component';
import { CategoryComponent } from './master/category/category.component';
import { AssetRegisterComponent } from './asset-register/asset-register.component';
import { DocumentComponent } from './document/document.component';
import { DataTablesModule } from 'angular-datatables';
import { TableAppComponent } from './table-app/table-app.component';
import { RolesComponent } from './account/roles/roles.component';
import { PermissionComponent } from './account/permission/permission.component';
import { MisReportComponent } from './mis-report/mis-report.component';
import { UserComponent } from './account/user/user.component';
import { ResidualRiskLikelihoodComponent } from './master/residual-risk-likelihood/residual-risk-likelihood.component';
import { RiskStatusComponent } from './master/risk-status/risk-status.component';
import { CcyComponent } from './master/ccy/ccy.component';
import { EventTypeComponent } from './master/event-type/event-type.component';
import { EventStatusComponent } from './master/event-status/event-status.component';
import { RiskCategoryLevelOneComponent } from './master/risk-category-level-one/risk-category-level-one.component';
import { RiskCategoryLevelTwoComponent } from './master/risk-category-level-two/risk-category-level-two.component';
import { ResidualImpactAssessmentComponent } from './master/residual-impact-assessment/residual-impact-assessment.component';
import { MatDatepickerModule, MatFormFieldModule, MatNativeDateModule, MatInputModule, MatCheckboxModule } from '@angular/material';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AuditlogComponent } from './master/auditlog/auditlog.component';
import { SelectAppComponent } from './select-app/select-app.component';
import { SelectDropDownModule } from 'ngx-select-dropdown';
import { OwlDateTimeModule, OwlNativeDateTimeModule } from 'ng-pick-datetime';
import { SweetAlert2Module } from '@sweetalert2/ngx-sweetalert2';;
import { ManagepageComponent } from './account/managepage/managepage.component'
    ;
import { RiskIncidentMenuComponent } from './risk-incident-menu/risk-incident-menu.component'
    ;
import { RiskIncidentComponent } from './risk-incident/risk-incident.component';;
import { GrossImpactComponent } from './gross-impact/gross-impact.component'
    ;
import { RecoveryComponent } from './recovery/recovery.component'
    ;
import { RootCauseComponent } from './root-cause/root-cause.component';
import { AdditionalDetailsComponent } from './additional-details/additional-details.component';
import { InherentRiskLikelihoodComponent } from './master/inherent-risk-likelihood/inherent-risk-likelihood.component';;
import { InherentImpactAssessmentComponent } from './master/inherent-impact-assessment/inherent-impact-assessment.component'
import { ChartsModule } from 'ng2-charts';

@NgModule({
    imports: [
        BrowserModule,
        MatCheckboxModule,
        ReactiveFormsModule,
        HttpClientModule,
        routing,
        AngularFontAwesomeModule,
        DataTablesModule,
        OwlDateTimeModule,
        OwlNativeDateTimeModule,
        MatDatepickerModule,
        MatDatepickerModule,
        MatFormFieldModule,
        MatNativeDateModule,
        MatInputModule,
        BrowserAnimationsModule,
        SelectDropDownModule,
        SweetAlert2Module.forRoot(),
        FormsModule,
        SelectDropDownModule,
        ChartsModule
    ],
    declarations: [
        AppComponent,
        AlertComponent,
        HomeComponent,
        LoginComponent,
        RegisterComponent
        ,
        DashboardComponent
        ,
        NavbarComponent,
        FooterComponent,
        RiskEventComponent
        ,
        RiskRegisterComponent,
        CategoryComponent
        ,
        AssetRegisterComponent
        ,
        DocumentComponent,
        TableAppComponent,
        PermissionComponent,
        RolesComponent,
        MisReportComponent,

        UserComponent,
        ResidualRiskLikelihoodComponent,
        RiskStatusComponent,
        CcyComponent,
        AuditlogComponent
        ,
        EventTypeComponent
        ,
        EventStatusComponent,
        RiskCategoryLevelOneComponent,
        RiskCategoryLevelTwoComponent,
        ResidualImpactAssessmentComponent,
        SelectAppComponent
        ,
        ManagepageComponent
        ,
        RiskIncidentMenuComponent
        ,
        RiskIncidentComponent
        ,
        GrossImpactComponent
        ,
        RecoveryComponent,
        RootCauseComponent,
        AdditionalDetailsComponent,
        InherentRiskLikelihoodComponent
        ,
        InherentImpactAssessmentComponent
    ],


    providers: [
        { provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor, multi: true },
        { provide: HTTP_INTERCEPTORS, useClass: ErrorInterceptor, multi: true },

    ],
    exports: [TableAppComponent,
        MatDatepickerModule,
        MatDatepickerModule,
        MatFormFieldModule,
        MatNativeDateModule,
        MatInputModule,
        BrowserAnimationsModule
    ],
    bootstrap: [AppComponent]
})

export class AppModule { }