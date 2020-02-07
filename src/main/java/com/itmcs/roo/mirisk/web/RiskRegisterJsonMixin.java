package com.itmcs.roo.mirisk.web;
import com.itmcs.roo.mirisk.dta.RiskRegister;
import org.springframework.roo.addon.web.mvc.controller.annotations.config.RooJsonMixin;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.itmcs.roo.mirisk.dta.Category;
import com.itmcs.roo.mirisk.dta.ResidualImpactAssessment;
import com.itmcs.roo.mirisk.dta.ResidualRiskLikelihood;
import com.itmcs.roo.mirisk.dta.RiskStatus;

/**
 * = RiskRegisterJsonMixin
 TODO Auto-generated class documentation
 *
 */
@RooJsonMixin(entity = RiskRegister.class)
public abstract class RiskRegisterJsonMixin {

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    @JsonDeserialize(using = ResidualRiskLikelihoodDeserializer.class)
    private ResidualRiskLikelihood residualRiskLikelihood;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    @JsonDeserialize(using = ResidualImpactAssessmentDeserializer.class)
    private ResidualImpactAssessment inheritImpactAssessment;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    @JsonDeserialize(using = ResidualImpactAssessmentDeserializer.class)
    private ResidualImpactAssessment residualImpactAssessment;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    @JsonDeserialize(using = RiskStatusDeserializer.class)
    private RiskStatus riskStatus;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    @JsonDeserialize(using = CategoryDeserializer.class)
    private Category category;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    @JsonDeserialize(using = ResidualRiskLikelihoodDeserializer.class)
    private ResidualRiskLikelihood inheritRiskLikelihood;

    /**
     * TODO Auto-generated method documentation
     *
     * @return Category
     */
    public Category getCategory() {
        return category;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param category
     */
    public void setCategory(Category category) {
        this.category = category;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return ResidualImpactAssessment
     */
    public ResidualImpactAssessment getInheritImpactAssessment() {
        return inheritImpactAssessment;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param inheritImpactAssessment
     */
    public void setInheritImpactAssessment(ResidualImpactAssessment inheritImpactAssessment) {
        this.inheritImpactAssessment = inheritImpactAssessment;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return ResidualRiskLikelihood
     */
    public ResidualRiskLikelihood getInheritRiskLikelihood() {
        return inheritRiskLikelihood;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param inheritRiskLikelihood
     */
    public void setInheritRiskLikelihood(ResidualRiskLikelihood inheritRiskLikelihood) {
        this.inheritRiskLikelihood = inheritRiskLikelihood;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return ResidualImpactAssessment
     */
    public ResidualImpactAssessment getResidualImpactAssessment() {
        return residualImpactAssessment;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param residualImpactAssessment
     */
    public void setResidualImpactAssessment(ResidualImpactAssessment residualImpactAssessment) {
        this.residualImpactAssessment = residualImpactAssessment;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return ResidualRiskLikelihood
     */
    public ResidualRiskLikelihood getResidualRiskLikelihood() {
        return residualRiskLikelihood;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param residualRiskLikelihood
     */
    public void setResidualRiskLikelihood(ResidualRiskLikelihood residualRiskLikelihood) {
        this.residualRiskLikelihood = residualRiskLikelihood;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return RiskStatus
     */
    public RiskStatus getRiskStatus() {
        return riskStatus;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param riskStatus
     */
    public void setRiskStatus(RiskStatus riskStatus) {
        this.riskStatus = riskStatus;
    }
}
