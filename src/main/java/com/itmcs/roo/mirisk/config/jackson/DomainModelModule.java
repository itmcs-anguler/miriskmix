package com.itmcs.roo.mirisk.config.jackson;
import org.springframework.roo.addon.web.mvc.controller.annotations.config.RooDomainModelModule;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.itmcs.roo.mirisk.dta.AdditionalDetails;
import com.itmcs.roo.mirisk.dta.AssetRegister;
import com.itmcs.roo.mirisk.dta.Category;
import com.itmcs.roo.mirisk.dta.Ccy;
import com.itmcs.roo.mirisk.dta.Document;
import com.itmcs.roo.mirisk.dta.EventStatus;
import com.itmcs.roo.mirisk.dta.EventType;
import com.itmcs.roo.mirisk.dta.GrossImpact;
import com.itmcs.roo.mirisk.dta.ManagePage;
import com.itmcs.roo.mirisk.dta.ManageRole;
import com.itmcs.roo.mirisk.dta.ManageUser;
import com.itmcs.roo.mirisk.dta.Recovery;
import com.itmcs.roo.mirisk.dta.ResidualImpactAssessment;
import com.itmcs.roo.mirisk.dta.ResidualRiskLikelihood;
import com.itmcs.roo.mirisk.dta.RiskCategoryLevelOne;
import com.itmcs.roo.mirisk.dta.RiskCategoryLevelTwo;
import com.itmcs.roo.mirisk.dta.RiskIncident;
import com.itmcs.roo.mirisk.dta.RiskRegister;
import com.itmcs.roo.mirisk.dta.RiskStatus;
import com.itmcs.roo.mirisk.web.AdditionalDetailsJsonMixin;
import com.itmcs.roo.mirisk.web.AssetRegisterJsonMixin;
import com.itmcs.roo.mirisk.web.CategoryJsonMixin;
import com.itmcs.roo.mirisk.web.CcyJsonMixin;
import com.itmcs.roo.mirisk.web.DocumentJsonMixin;
import com.itmcs.roo.mirisk.web.EventStatusJsonMixin;
import com.itmcs.roo.mirisk.web.EventTypeJsonMixin;
import com.itmcs.roo.mirisk.web.GrossImpactJsonMixin;
import com.itmcs.roo.mirisk.web.ManagePageJsonMixin;
import com.itmcs.roo.mirisk.web.ManageRoleJsonMixin;
import com.itmcs.roo.mirisk.web.ManageUserJsonMixin;
import com.itmcs.roo.mirisk.web.RecoveryJsonMixin;
import com.itmcs.roo.mirisk.web.ResidualImpactAssessmentJsonMixin;
import com.itmcs.roo.mirisk.web.ResidualRiskLikelihoodJsonMixin;
import com.itmcs.roo.mirisk.web.RiskCategoryLevelOneJsonMixin;
import com.itmcs.roo.mirisk.web.RiskCategoryLevelTwoJsonMixin;
import com.itmcs.roo.mirisk.web.RiskIncidentJsonMixin;
import com.itmcs.roo.mirisk.web.RiskRegisterJsonMixin;
import com.itmcs.roo.mirisk.web.RiskStatusJsonMixin;
import org.springframework.boot.jackson.JsonComponent;

/**
 * = DomainModelModule
 TODO Auto-generated class documentation
 *
 */
@RooDomainModelModule
@JsonComponent
public class DomainModelModule extends SimpleModule {

    /**
     * TODO Auto-generated constructor documentation
     *
     */
    public DomainModelModule() {
        // Mixin registration
        setMixInAnnotation(AdditionalDetails.class, AdditionalDetailsJsonMixin.class);
        setMixInAnnotation(AssetRegister.class, AssetRegisterJsonMixin.class);
        setMixInAnnotation(Category.class, CategoryJsonMixin.class);
        setMixInAnnotation(Ccy.class, CcyJsonMixin.class);
        setMixInAnnotation(Document.class, DocumentJsonMixin.class);
        setMixInAnnotation(EventStatus.class, EventStatusJsonMixin.class);
        setMixInAnnotation(EventType.class, EventTypeJsonMixin.class);
        setMixInAnnotation(GrossImpact.class, GrossImpactJsonMixin.class);
        setMixInAnnotation(ManagePage.class, ManagePageJsonMixin.class);
        setMixInAnnotation(ManageRole.class, ManageRoleJsonMixin.class);
        setMixInAnnotation(ManageUser.class, ManageUserJsonMixin.class);
        setMixInAnnotation(Recovery.class, RecoveryJsonMixin.class);
        setMixInAnnotation(ResidualImpactAssessment.class, ResidualImpactAssessmentJsonMixin.class);
        setMixInAnnotation(ResidualRiskLikelihood.class, ResidualRiskLikelihoodJsonMixin.class);
        setMixInAnnotation(RiskCategoryLevelOne.class, RiskCategoryLevelOneJsonMixin.class);
        setMixInAnnotation(RiskCategoryLevelTwo.class, RiskCategoryLevelTwoJsonMixin.class);
        setMixInAnnotation(RiskIncident.class, RiskIncidentJsonMixin.class);
        setMixInAnnotation(RiskRegister.class, RiskRegisterJsonMixin.class);
        setMixInAnnotation(RiskStatus.class, RiskStatusJsonMixin.class);
    }
}
