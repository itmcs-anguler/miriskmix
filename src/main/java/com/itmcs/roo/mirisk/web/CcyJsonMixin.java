package com.itmcs.roo.mirisk.web;
import com.itmcs.roo.mirisk.dta.Ccy;
import org.springframework.roo.addon.web.mvc.controller.annotations.config.RooJsonMixin;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.itmcs.roo.mirisk.dta.GrossImpact;
import com.itmcs.roo.mirisk.dta.Recovery;
import java.util.Set;

/**
 * = CcyJsonMixin
 TODO Auto-generated class documentation
 *
 */
@RooJsonMixin(entity = Ccy.class)
public abstract class CcyJsonMixin {

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    @JsonIgnore
    private Set<GrossImpact> grossImpact;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    @JsonIgnore
    private Set<Recovery> recovery;

    /**
     * TODO Auto-generated method documentation
     *
     * @return Set
     */
    public Set<GrossImpact> getGrossImpact() {
        return grossImpact;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param grossImpact
     */
    public void setGrossImpact(Set<GrossImpact> grossImpact) {
        this.grossImpact = grossImpact;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return Set
     */
    public Set<Recovery> getRecovery() {
        return recovery;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param recovery
     */
    public void setRecovery(Set<Recovery> recovery) {
        this.recovery = recovery;
    }
}
