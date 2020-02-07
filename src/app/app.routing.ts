import { Routes, RouterModule } from '@angular/router';

import { HomeComponent } from './home';
import { LoginComponent } from './login';
import { RegisterComponent } from './register';
import { AuthGuard } from './_guards';
import { DashboardComponent } from './dashboard/dashboard.component';
import { CategoryComponent } from './master/category/category.component';
import { RiskEventComponent } from './risk-event/risk-event.component';
import { RiskRegisterComponent } from './risk-register/risk-register.component';
import { AssetRegisterComponent } from './asset-register/asset-register.component';
import { DocumentComponent } from './document/document.component';
import { PermissionComponent } from './account/permission/permission.component';
import { RolesComponent } from './account/roles/roles.component';
import { MisReportComponent } from './mis-report/mis-report.component';
import { UserComponent } from './account/user/user.component';

import { ResidualRiskLikelihoodComponent } from './master/residual-risk-likelihood/residual-risk-likelihood.component';
import { RiskStatusComponent } from './master/risk-status/risk-status.component';
import { CcyComponent } from './master/ccy/ccy.component';
import { AuditlogComponent } from './master/auditlog/auditlog.component';
import { EventTypeComponent } from './master/event-type/event-type.component';
import { EventStatusComponent } from './master/event-status/event-status.component';
import { RiskCategoryLevelOneComponent } from './master/risk-category-level-one/risk-category-level-one.component';
import { RiskCategoryLevelTwoComponent } from './master/risk-category-level-two/risk-category-level-two.component';
import { ResidualImpactAssessmentComponent } from './master/residual-impact-assessment/residual-impact-assessment.component';
import { ManagepageComponent } from './account/managepage/managepage.component';
import { RiskIncidentMenuComponent } from './risk-incident-menu/risk-incident-menu.component';
import { InherentRiskLikelihoodComponent } from './master/inherent-risk-likelihood/inherent-risk-likelihood.component';
import { InherentImpactAssessmentComponent } from './master/inherent-impact-assessment/inherent-impact-assessment.component';

const appRoutes: Routes = [
    { path: '', component: HomeComponent, canActivate: [AuthGuard] },
    { path: 'login', component: LoginComponent },
    { path: 'register', component: RegisterComponent },
    { path: 'dashboard', component: DashboardComponent, canActivate: [AuthGuard] },
    { path: 'category', component: CategoryComponent, canActivate: [AuthGuard] },
    // { path: 'risk-incident', component: RiskEventComponent, canActivate: [AuthGuard] },
    { path: 'risk-incident', component: RiskIncidentMenuComponent, canActivate: [AuthGuard] },
    { path: 'risk-register', component: RiskRegisterComponent, canActivate: [AuthGuard] },
    { path: 'asset-register', component: AssetRegisterComponent, canActivate: [AuthGuard] },
    { path: 'document', component: DocumentComponent, canActivate: [AuthGuard] },
    { path: 'managePage', component: ManagepageComponent, canActivate: [AuthGuard] },
    { path: 'permission', component: PermissionComponent, canActivate: [AuthGuard] },
    { path: 'role', component: RolesComponent, canActivate: [AuthGuard] },
    { path: 'user', component: UserComponent, canActivate: [AuthGuard] },
    { path: 'auditlog', component: AuditlogComponent, canActivate: [AuthGuard] },
    { path: 'mis-report', component: MisReportComponent, canActivate: [AuthGuard] },
    { path: 'likelihood', component: ResidualRiskLikelihoodComponent, canActivate: [AuthGuard] },
    { path: 'risk-status', component: RiskStatusComponent, canActivate: [AuthGuard] },
    { path: 'ccy', component: CcyComponent, canActivate: [AuthGuard] },
    { path: 'event-type', component: EventTypeComponent, canActivate: [AuthGuard] },
    { path: 'event-status', component: EventStatusComponent, canActivate: [AuthGuard] },
    { path: 'risk-category-one', component: RiskCategoryLevelOneComponent, canActivate: [AuthGuard] },
    { path: 'risk-category-two', component: RiskCategoryLevelTwoComponent, canActivate: [AuthGuard] },
    { path: 'residual-impact-assessment', component: ResidualImpactAssessmentComponent, canActivate: [AuthGuard] },
    { path: 'inherit-risk-likelihood', component: InherentRiskLikelihoodComponent, canActivate: [AuthGuard] },
    { path: 'inherent-impact-assessment', component: InherentImpactAssessmentComponent, canActivate: [AuthGuard] },

    // otherwise redirect to home
    { path: '**', redirectTo: '' }
];

export const routing = RouterModule.forRoot(appRoutes);