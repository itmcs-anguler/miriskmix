package com.itmcs.roo.mirisk.web;
import com.itmcs.roo.mirisk.dta.RiskStatus;
import org.springframework.roo.addon.web.mvc.controller.annotations.config.RooJsonMixin;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.itmcs.roo.mirisk.dta.RiskRegister;
import java.util.Set;

/**
 * = RiskStatusJsonMixin
 TODO Auto-generated class documentation
 *
 */
@RooJsonMixin(entity = RiskStatus.class)
public abstract class RiskStatusJsonMixin {

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    @JsonIgnore
    private Set<RiskRegister> riskRegisters;

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
