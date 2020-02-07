package com.itmcs.roo.mirisk.web;
import com.itmcs.roo.mirisk.dta.RiskIncident;
import org.springframework.roo.addon.web.mvc.controller.annotations.config.RooJsonMixin;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.itmcs.roo.mirisk.dta.EventStatus;
import com.itmcs.roo.mirisk.dta.EventType;
import com.itmcs.roo.mirisk.dta.RiskCategoryLevelOne;
import com.itmcs.roo.mirisk.dta.RiskCategoryLevelTwo;

/**
 * = RiskIncidentJsonMixin
 TODO Auto-generated class documentation
 *
 */
@RooJsonMixin(entity = RiskIncident.class)
public abstract class RiskIncidentJsonMixin {

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    @JsonDeserialize(using = RiskCategoryLevelOneDeserializer.class)
    private RiskCategoryLevelOne riskCategoryOne;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    @JsonDeserialize(using = RiskCategoryLevelTwoDeserializer.class)
    private RiskCategoryLevelTwo riskCategoryTwo;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    @JsonDeserialize(using = EventStatusDeserializer.class)
    private EventStatus eventStatus;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    @JsonDeserialize(using = EventTypeDeserializer.class)
    private EventType eventType;

    /**
     * TODO Auto-generated method documentation
     *
     * @return EventStatus
     */
    public EventStatus getEventStatus() {
        return eventStatus;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param eventStatus
     */
    public void setEventStatus(EventStatus eventStatus) {
        this.eventStatus = eventStatus;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return EventType
     */
    public EventType getEventType() {
        return eventType;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param eventType
     */
    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return RiskCategoryLevelOne
     */
    public RiskCategoryLevelOne getRiskCategoryOne() {
        return riskCategoryOne;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param riskCategoryOne
     */
    public void setRiskCategoryOne(RiskCategoryLevelOne riskCategoryOne) {
        this.riskCategoryOne = riskCategoryOne;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return RiskCategoryLevelTwo
     */
    public RiskCategoryLevelTwo getRiskCategoryTwo() {
        return riskCategoryTwo;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param riskCategoryTwo
     */
    public void setRiskCategoryTwo(RiskCategoryLevelTwo riskCategoryTwo) {
        this.riskCategoryTwo = riskCategoryTwo;
    }
}
