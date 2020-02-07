package com.itmcs.roo.mirisk.web;
import com.itmcs.roo.mirisk.dta.ResidualRiskLikelihood;
import org.springframework.roo.addon.web.mvc.controller.annotations.config.RooJsonMixin;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.itmcs.roo.mirisk.dta.RiskRegister;
import java.util.Set;

/**
 * = ResidualRiskLikelihoodJsonMixin
 TODO Auto-generated class documentation
 *
 */
@RooJsonMixin(entity = ResidualRiskLikelihood.class)
public abstract class ResidualRiskLikelihoodJsonMixin {

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
    private Set<RiskRegister> inheritRiskLikelihood;

    /**
     * TODO Auto-generated method documentation
     *
     * @return Set
     */
    public Set<RiskRegister> getInheritRiskLikelihood() {
        return inheritRiskLikelihood;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param inheritRiskLikelihood
     */
    public void setInheritRiskLikelihood(Set<RiskRegister> inheritRiskLikelihood) {
        this.inheritRiskLikelihood = inheritRiskLikelihood;
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
