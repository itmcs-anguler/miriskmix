package com.itmcs.roo.mirisk.dta;
import org.springframework.roo.addon.javabean.annotations.RooEquals;
import org.springframework.roo.addon.javabean.annotations.RooJavaBean;
import org.springframework.roo.addon.javabean.annotations.RooToString;
import org.springframework.roo.addon.jpa.annotations.entity.RooJpaEntity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import io.springlets.format.EntityFormat;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.util.Objects;
import javax.persistence.Entity;

/**
 * = RiskIncident
 TODO Auto-generated class documentation
 *
 */
@RooJavaBean
@RooToString
@RooJpaEntity
@RooEquals(isJpaEntity = true)
@Entity
@EntityFormat
public class RiskIncident {

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private String incidentTitle;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private String internalorExternal;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private String owner;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private String descriptionIncident;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private String occurrence;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private String detection;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private String incidentClose;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private String incidentReported;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private String reportabletoRegulator;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private String numberofPeople;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private String location;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private String remediationPlan;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private String remediationActionOwner;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private String notes;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @EntityFormat
    private EventType eventType;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @EntityFormat
    private EventStatus eventStatus;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @EntityFormat
    private RiskCategoryLevelOne riskCategoryOne;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @EntityFormat
    private RiskCategoryLevelTwo riskCategoryTwo;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private String uniqueId;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    public static final String ITERABLE_TO_ADD_CANT_BE_NULL_MESSAGE = "The given Iterable of items to add can't be null!";

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    public static final String ITERABLE_TO_REMOVE_CANT_BE_NULL_MESSAGE = "The given Iterable of items to add can't be null!";

    /**
     * Gets id value
     *
     * @return Long
     */
    public Long getId() {
        return this.id;
    }

    /**
     * Sets id value
     *
     * @param id
     * @return RiskIncident
     */
    public RiskIncident setId(Long id) {
        this.id = id;
        return this;
    }

    /**
     * Gets incidentTitle value
     *
     * @return String
     */
    public String getIncidentTitle() {
        return this.incidentTitle;
    }

    /**
     * Sets incidentTitle value
     *
     * @param incidentTitle
     * @return RiskIncident
     */
    public RiskIncident setIncidentTitle(String incidentTitle) {
        this.incidentTitle = incidentTitle;
        return this;
    }

    /**
     * Gets internalorExternal value
     *
     * @return String
     */
    public String getInternalorExternal() {
        return this.internalorExternal;
    }

    /**
     * Sets internalorExternal value
     *
     * @param internalorExternal
     * @return RiskIncident
     */
    public RiskIncident setInternalorExternal(String internalorExternal) {
        this.internalorExternal = internalorExternal;
        return this;
    }

    /**
     * Gets owner value
     *
     * @return String
     */
    public String getOwner() {
        return this.owner;
    }

    /**
     * Sets owner value
     *
     * @param owner
     * @return RiskIncident
     */
    public RiskIncident setOwner(String owner) {
        this.owner = owner;
        return this;
    }

    /**
     * Gets descriptionIncident value
     *
     * @return String
     */
    public String getDescriptionIncident() {
        return this.descriptionIncident;
    }

    /**
     * Sets descriptionIncident value
     *
     * @param descriptionIncident
     * @return RiskIncident
     */
    public RiskIncident setDescriptionIncident(String descriptionIncident) {
        this.descriptionIncident = descriptionIncident;
        return this;
    }

    /**
     * Gets occurrence value
     *
     * @return String
     */
    public String getOccurrence() {
        return this.occurrence;
    }

    /**
     * Sets occurrence value
     *
     * @param occurrence
     * @return RiskIncident
     */
    public RiskIncident setOccurrence(String occurrence) {
        this.occurrence = occurrence;
        return this;
    }

    /**
     * Gets detection value
     *
     * @return String
     */
    public String getDetection() {
        return this.detection;
    }

    /**
     * Sets detection value
     *
     * @param detection
     * @return RiskIncident
     */
    public RiskIncident setDetection(String detection) {
        this.detection = detection;
        return this;
    }

    /**
     * Gets incidentClose value
     *
     * @return String
     */
    public String getIncidentClose() {
        return this.incidentClose;
    }

    /**
     * Sets incidentClose value
     *
     * @param incidentClose
     * @return RiskIncident
     */
    public RiskIncident setIncidentClose(String incidentClose) {
        this.incidentClose = incidentClose;
        return this;
    }

    /**
     * Gets incidentReported value
     *
     * @return String
     */
    public String getIncidentReported() {
        return this.incidentReported;
    }

    /**
     * Sets incidentReported value
     *
     * @param incidentReported
     * @return RiskIncident
     */
    public RiskIncident setIncidentReported(String incidentReported) {
        this.incidentReported = incidentReported;
        return this;
    }

    /**
     * Gets reportabletoRegulator value
     *
     * @return String
     */
    public String getReportabletoRegulator() {
        return this.reportabletoRegulator;
    }

    /**
     * Sets reportabletoRegulator value
     *
     * @param reportabletoRegulator
     * @return RiskIncident
     */
    public RiskIncident setReportabletoRegulator(String reportabletoRegulator) {
        this.reportabletoRegulator = reportabletoRegulator;
        return this;
    }

    /**
     * Gets numberofPeople value
     *
     * @return String
     */
    public String getNumberofPeople() {
        return this.numberofPeople;
    }

    /**
     * Sets numberofPeople value
     *
     * @param numberofPeople
     * @return RiskIncident
     */
    public RiskIncident setNumberofPeople(String numberofPeople) {
        this.numberofPeople = numberofPeople;
        return this;
    }

    /**
     * Gets location value
     *
     * @return String
     */
    public String getLocation() {
        return this.location;
    }

    /**
     * Sets location value
     *
     * @param location
     * @return RiskIncident
     */
    public RiskIncident setLocation(String location) {
        this.location = location;
        return this;
    }

    /**
     * Gets remediationPlan value
     *
     * @return String
     */
    public String getRemediationPlan() {
        return this.remediationPlan;
    }

    /**
     * Sets remediationPlan value
     *
     * @param remediationPlan
     * @return RiskIncident
     */
    public RiskIncident setRemediationPlan(String remediationPlan) {
        this.remediationPlan = remediationPlan;
        return this;
    }

    /**
     * Gets remediationActionOwner value
     *
     * @return String
     */
    public String getRemediationActionOwner() {
        return this.remediationActionOwner;
    }

    /**
     * Sets remediationActionOwner value
     *
     * @param remediationActionOwner
     * @return RiskIncident
     */
    public RiskIncident setRemediationActionOwner(String remediationActionOwner) {
        this.remediationActionOwner = remediationActionOwner;
        return this;
    }

    /**
     * Gets notes value
     *
     * @return String
     */
    public String getNotes() {
        return this.notes;
    }

    /**
     * Sets notes value
     *
     * @param notes
     * @return RiskIncident
     */
    public RiskIncident setNotes(String notes) {
        this.notes = notes;
        return this;
    }

    /**
     * Gets eventType value
     *
     * @return EventType
     */
    public EventType getEventType() {
        return this.eventType;
    }

    /**
     * Sets eventType value
     *
     * @param eventType
     * @return RiskIncident
     */
    public RiskIncident setEventType(EventType eventType) {
        this.eventType = eventType;
        return this;
    }

    /**
     * Gets eventStatus value
     *
     * @return EventStatus
     */
    public EventStatus getEventStatus() {
        return this.eventStatus;
    }

    /**
     * Sets eventStatus value
     *
     * @param eventStatus
     * @return RiskIncident
     */
    public RiskIncident setEventStatus(EventStatus eventStatus) {
        this.eventStatus = eventStatus;
        return this;
    }

    /**
     * Gets riskCategoryOne value
     *
     * @return RiskCategoryLevelOne
     */
    public RiskCategoryLevelOne getRiskCategoryOne() {
        return this.riskCategoryOne;
    }

    /**
     * Sets riskCategoryOne value
     *
     * @param riskCategoryOne
     * @return RiskIncident
     */
    public RiskIncident setRiskCategoryOne(RiskCategoryLevelOne riskCategoryOne) {
        this.riskCategoryOne = riskCategoryOne;
        return this;
    }

    /**
     * Gets riskCategoryTwo value
     *
     * @return RiskCategoryLevelTwo
     */
    public RiskCategoryLevelTwo getRiskCategoryTwo() {
        return this.riskCategoryTwo;
    }

    /**
     * Sets riskCategoryTwo value
     *
     * @param riskCategoryTwo
     * @return RiskIncident
     */
    public RiskIncident setRiskCategoryTwo(RiskCategoryLevelTwo riskCategoryTwo) {
        this.riskCategoryTwo = riskCategoryTwo;
        return this;
    }

    /**
     * Gets uniqueId value
     *
     * @return String
     */
    public String getUniqueId() {
        return this.uniqueId;
    }

    /**
     * Sets uniqueId value
     *
     * @param uniqueId
     * @return RiskIncident
     */
    public RiskIncident setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
        return this;
    }

    /**
     * This `equals` implementation is specific for JPA entities and uses
     * the entity identifier for it, following the article in
     * https://vladmihalcea.com/2016/06/06/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
     *
     * @param obj
     * @return Boolean
     */
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        // instanceof is false if the instance is null
        if (!(obj instanceof RiskIncident)) {
            return false;
        }
        return getId() != null && Objects.equals(getId(), ((RiskIncident) obj).getId());
    }

    /**
     * This `hashCode` implementation is specific for JPA entities and uses a fixed `int` value to be able
     * to identify the entity in collections after a new id is assigned to the entity, following the article in
     * https://vladmihalcea.com/2016/06/06/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
     *
     * @return Integer
     */
    public int hashCode() {
        return 31;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return String
     */
    public String toString() {
        return "RiskIncident {" + "id='" + id + '\'' + ", incidentTitle='" + incidentTitle + '\'' + ", internalorExternal='" + internalorExternal + '\'' + ", owner='" + owner + '\'' + ", descriptionIncident='" + descriptionIncident + '\'' + ", occurrence='" + occurrence + '\'' + ", detection='" + detection + '\'' + ", incidentClose='" + incidentClose + '\'' + ", incidentReported='" + incidentReported + '\'' + ", reportabletoRegulator='" + reportabletoRegulator + '\'' + ", numberofPeople='" + numberofPeople + '\'' + ", location='" + location + '\'' + ", remediationPlan='" + remediationPlan + '\'' + ", remediationActionOwner='" + remediationActionOwner + '\'' + ", notes='" + notes + '\'' + ", uniqueId='" + uniqueId + '\'' + "}" + super.toString();
    }
}
