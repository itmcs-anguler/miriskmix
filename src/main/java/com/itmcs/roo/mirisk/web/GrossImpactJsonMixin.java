package com.itmcs.roo.mirisk.web;
import com.itmcs.roo.mirisk.dta.GrossImpact;
import org.springframework.roo.addon.web.mvc.controller.annotations.config.RooJsonMixin;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.itmcs.roo.mirisk.dta.Ccy;

/**
 * = GrossImpactJsonMixin
 TODO Auto-generated class documentation
 *
 */
@RooJsonMixin(entity = GrossImpact.class)
public abstract class GrossImpactJsonMixin {

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    @JsonDeserialize(using = CcyDeserializer.class)
    private Ccy ccy;

    /**
     * TODO Auto-generated method documentation
     *
     * @return Ccy
     */
    public Ccy getCcy() {
        return ccy;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param ccy
     */
    public void setCcy(Ccy ccy) {
        this.ccy = ccy;
    }
}
