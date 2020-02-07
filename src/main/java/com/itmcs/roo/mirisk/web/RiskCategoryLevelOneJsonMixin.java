package com.itmcs.roo.mirisk.web;
import com.itmcs.roo.mirisk.dta.RiskCategoryLevelOne;
import org.springframework.roo.addon.web.mvc.controller.annotations.config.RooJsonMixin;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.itmcs.roo.mirisk.dta.RiskIncident;
import java.util.Set;

/**
 * = RiskCategoryLevelOneJsonMixin
 TODO Auto-generated class documentation
 *
 */
@RooJsonMixin(entity = RiskCategoryLevelOne.class)
public abstract class RiskCategoryLevelOneJsonMixin {

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    @JsonIgnore
    private Set<RiskIncident> riskIncident;

    /**
     * TODO Auto-generated method documentation
     *
     * @return Set
     */
    public Set<RiskIncident> getRiskIncident() {
        return riskIncident;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param riskIncident
     */
    public void setRiskIncident(Set<RiskIncident> riskIncident) {
        this.riskIncident = riskIncident;
    }
}
