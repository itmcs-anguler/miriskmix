package com.itmcs.roo.mirisk.web;
import com.itmcs.roo.mirisk.dta.ResidualImpactAssessment;
import org.springframework.roo.addon.web.mvc.controller.annotations.config.RooJsonMixin;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.itmcs.roo.mirisk.dta.RiskRegister;
import java.util.Set;

/**
 * = ResidualImpactAssessmentJsonMixin
 TODO Auto-generated class documentation
 *
 */
@RooJsonMixin(entity = ResidualImpactAssessment.class)
public abstract class ResidualImpactAssessmentJsonMixin {

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    @JsonIgnore
    private Set<RiskRegister> riskRegisters;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    @JsonIgnore
    private Set<RiskRegister> inheritImpactAssessment;

    /**
     * TODO Auto-generated method documentation
     *
     * @return Set
     */
    public Set<RiskRegister> getInheritImpactAssessment() {
        return inheritImpactAssessment;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param inheritImpactAssessment
     */
    public void setInheritImpactAssessment(Set<RiskRegister> inheritImpactAssessment) {
        this.inheritImpactAssessment = inheritImpactAssessment;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return Set
     */
    public Set<RiskRegister> getRiskRegisters() {
        return riskRegisters;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param riskRegisters
     */
    public void setRiskRegisters(Set<RiskRegister> riskRegisters) {
        this.riskRegisters = riskRegisters;
    }
}
