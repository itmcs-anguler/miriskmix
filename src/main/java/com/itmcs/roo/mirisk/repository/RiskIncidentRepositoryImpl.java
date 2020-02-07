package com.itmcs.roo.mirisk.repository;
import io.springlets.data.jpa.repository.support.QueryDslRepositorySupportExt;
import org.springframework.roo.addon.layers.repository.jpa.annotations.RooJpaRepositoryCustomImpl;
import com.itmcs.roo.mirisk.dta.RiskIncident;
import com.itmcs.roo.mirisk.dta.EventStatus;
import com.itmcs.roo.mirisk.dta.EventType;
import com.itmcs.roo.mirisk.dta.QRiskIncident;
import com.itmcs.roo.mirisk.dta.RiskCategoryLevelOne;
import com.itmcs.roo.mirisk.dta.RiskCategoryLevelTwo;
import com.querydsl.core.types.Path;
import com.querydsl.jpa.JPQLQuery;
import io.springlets.data.domain.GlobalSearch;
import io.springlets.data.jpa.repository.support.QueryDslRepositorySupportExt.AttributeMappingBuilder;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

/**
 * = RiskIncidentRepositoryImpl
 TODO Auto-generated class documentation
 *
 */
@RooJpaRepositoryCustomImpl(repository = RiskIncidentRepositoryCustom.class)
@Transactional(readOnly = true)
public class RiskIncidentRepositoryImpl extends QueryDslRepositorySupportExt<RiskIncident> implements RiskIncidentRepositoryCustom {

    /**
     * Default constructor
     */
    RiskIncidentRepositoryImpl() {
        super(RiskIncident.class);
    }

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    public static final String INCIDENT_CLOSE = "incidentClose";

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    public static final String LOCATION = "location";

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    public static final String REMEDIATION_PLAN = "remediationPlan";

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    public static final String RISK_CATEGORY_TWO = "riskCategoryTwo";

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    public static final String REMEDIATION_ACTION_OWNER = "remediationActionOwner";

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    public static final String EVENT_TYPE = "eventType";

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    public static final String REPORTABLETO_REGULATOR = "reportabletoRegulator";

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    public static final String UNIQUE_ID = "uniqueId";

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    public static final String DESCRIPTION_INCIDENT = "descriptionIncident";

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    public static final String RISK_CATEGORY_ONE = "riskCategoryOne";

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    public static final String NOTES = "notes";

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    public static final String INCIDENT_TITLE = "incidentTitle";

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    public static final String OWNER = "owner";

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    public static final String OCCURRENCE = "occurrence";

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    public static final String EVENT_STATUS = "eventStatus";

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    public static final String DETECTION = "detection";

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    public static final String INTERNALOR_EXTERNAL = "internalorExternal";

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    public static final String INCIDENT_REPORTED = "incidentReported";

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    public static final String NUMBEROF_PEOPLE = "numberofPeople";

    /**
     * TODO Auto-generated method documentation
     *
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public Page<RiskIncident> findAll(GlobalSearch globalSearch, Pageable pageable) {
        QRiskIncident riskIncident = QRiskIncident.riskIncident;
        JPQLQuery<RiskIncident> query = from(riskIncident);
        Path<?>[] paths = new Path<?>[] { riskIncident.incidentTitle, riskIncident.internalorExternal, riskIncident.owner, riskIncident.descriptionIncident, riskIncident.occurrence, riskIncident.detection, riskIncident.incidentClose, riskIncident.incidentReported, riskIncident.reportabletoRegulator, riskIncident.numberofPeople, riskIncident.location, riskIncident.remediationPlan, riskIncident.remediationActionOwner, riskIncident.notes, riskIncident.eventType, riskIncident.eventStatus, riskIncident.riskCategoryOne, riskIncident.riskCategoryTwo, riskIncident.uniqueId };
        applyGlobalSearch(globalSearch, query, paths);
        AttributeMappingBuilder mapping = buildMapper().map(INCIDENT_TITLE, riskIncident.incidentTitle).map(INTERNALOR_EXTERNAL, riskIncident.internalorExternal).map(OWNER, riskIncident.owner).map(DESCRIPTION_INCIDENT, riskIncident.descriptionIncident).map(OCCURRENCE, riskIncident.occurrence).map(DETECTION, riskIncident.detection).map(INCIDENT_CLOSE, riskIncident.incidentClose).map(INCIDENT_REPORTED, riskIncident.incidentReported).map(REPORTABLETO_REGULATOR, riskIncident.reportabletoRegulator).map(NUMBEROF_PEOPLE, riskIncident.numberofPeople).map(LOCATION, riskIncident.location).map(REMEDIATION_PLAN, riskIncident.remediationPlan).map(REMEDIATION_ACTION_OWNER, riskIncident.remediationActionOwner).map(NOTES, riskIncident.notes).map(EVENT_TYPE, riskIncident.eventType).map(EVENT_STATUS, riskIncident.eventStatus).map(RISK_CATEGORY_ONE, riskIncident.riskCategoryOne).map(RISK_CATEGORY_TWO, riskIncident.riskCategoryTwo).map(UNIQUE_ID, riskIncident.uniqueId);
        applyPagination(pageable, query, mapping);
        applyOrderById(query);
        return loadPage(query, pageable, riskIncident);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param ids
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public Page<RiskIncident> findAllByIdsIn(List<Long> ids, GlobalSearch globalSearch, Pageable pageable) {
        QRiskIncident riskIncident = QRiskIncident.riskIncident;
        JPQLQuery<RiskIncident> query = from(riskIncident);
        Path<?>[] paths = new Path<?>[] { riskIncident.incidentTitle, riskIncident.internalorExternal, riskIncident.owner, riskIncident.descriptionIncident, riskIncident.occurrence, riskIncident.detection, riskIncident.incidentClose, riskIncident.incidentReported, riskIncident.reportabletoRegulator, riskIncident.numberofPeople, riskIncident.location, riskIncident.remediationPlan, riskIncident.remediationActionOwner, riskIncident.notes, riskIncident.eventType, riskIncident.eventStatus, riskIncident.riskCategoryOne, riskIncident.riskCategoryTwo, riskIncident.uniqueId };
        applyGlobalSearch(globalSearch, query, paths);
        // Also, filter by the provided ids
        query.where(riskIncident.id.in(ids));
        AttributeMappingBuilder mapping = buildMapper().map(INCIDENT_TITLE, riskIncident.incidentTitle).map(INTERNALOR_EXTERNAL, riskIncident.internalorExternal).map(OWNER, riskIncident.owner).map(DESCRIPTION_INCIDENT, riskIncident.descriptionIncident).map(OCCURRENCE, riskIncident.occurrence).map(DETECTION, riskIncident.detection).map(INCIDENT_CLOSE, riskIncident.incidentClose).map(INCIDENT_REPORTED, riskIncident.incidentReported).map(REPORTABLETO_REGULATOR, riskIncident.reportabletoRegulator).map(NUMBEROF_PEOPLE, riskIncident.numberofPeople).map(LOCATION, riskIncident.location).map(REMEDIATION_PLAN, riskIncident.remediationPlan).map(REMEDIATION_ACTION_OWNER, riskIncident.remediationActionOwner).map(NOTES, riskIncident.notes).map(EVENT_TYPE, riskIncident.eventType).map(EVENT_STATUS, riskIncident.eventStatus).map(RISK_CATEGORY_ONE, riskIncident.riskCategoryOne).map(RISK_CATEGORY_TWO, riskIncident.riskCategoryTwo).map(UNIQUE_ID, riskIncident.uniqueId);
        applyPagination(pageable, query, mapping);
        applyOrderById(query);
        return loadPage(query, pageable, riskIncident);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param eventStatus
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public Page<RiskIncident> findByEventStatus(EventStatus eventStatus, GlobalSearch globalSearch, Pageable pageable) {
        QRiskIncident riskIncident = QRiskIncident.riskIncident;
        JPQLQuery<RiskIncident> query = from(riskIncident);
        Assert.notNull(eventStatus, "eventStatus is required");
        query.where(riskIncident.eventStatus.eq(eventStatus));
        Path<?>[] paths = new Path<?>[] { riskIncident.incidentTitle, riskIncident.internalorExternal, riskIncident.owner, riskIncident.descriptionIncident, riskIncident.occurrence, riskIncident.detection, riskIncident.incidentClose, riskIncident.incidentReported, riskIncident.reportabletoRegulator, riskIncident.numberofPeople, riskIncident.location, riskIncident.remediationPlan, riskIncident.remediationActionOwner, riskIncident.notes, riskIncident.eventType, riskIncident.eventStatus, riskIncident.riskCategoryOne, riskIncident.riskCategoryTwo, riskIncident.uniqueId };
        applyGlobalSearch(globalSearch, query, paths);
        AttributeMappingBuilder mapping = buildMapper().map(INCIDENT_TITLE, riskIncident.incidentTitle).map(INTERNALOR_EXTERNAL, riskIncident.internalorExternal).map(OWNER, riskIncident.owner).map(DESCRIPTION_INCIDENT, riskIncident.descriptionIncident).map(OCCURRENCE, riskIncident.occurrence).map(DETECTION, riskIncident.detection).map(INCIDENT_CLOSE, riskIncident.incidentClose).map(INCIDENT_REPORTED, riskIncident.incidentReported).map(REPORTABLETO_REGULATOR, riskIncident.reportabletoRegulator).map(NUMBEROF_PEOPLE, riskIncident.numberofPeople).map(LOCATION, riskIncident.location).map(REMEDIATION_PLAN, riskIncident.remediationPlan).map(REMEDIATION_ACTION_OWNER, riskIncident.remediationActionOwner).map(NOTES, riskIncident.notes).map(EVENT_TYPE, riskIncident.eventType).map(EVENT_STATUS, riskIncident.eventStatus).map(RISK_CATEGORY_ONE, riskIncident.riskCategoryOne).map(RISK_CATEGORY_TWO, riskIncident.riskCategoryTwo).map(UNIQUE_ID, riskIncident.uniqueId);
        applyPagination(pageable, query, mapping);
        applyOrderById(query);
        return loadPage(query, pageable, riskIncident);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param eventType
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public Page<RiskIncident> findByEventType(EventType eventType, GlobalSearch globalSearch, Pageable pageable) {
        QRiskIncident riskIncident = QRiskIncident.riskIncident;
        JPQLQuery<RiskIncident> query = from(riskIncident);
        Assert.notNull(eventType, "eventType is required");
        query.where(riskIncident.eventType.eq(eventType));
        Path<?>[] paths = new Path<?>[] { riskIncident.incidentTitle, riskIncident.internalorExternal, riskIncident.owner, riskIncident.descriptionIncident, riskIncident.occurrence, riskIncident.detection, riskIncident.incidentClose, riskIncident.incidentReported, riskIncident.reportabletoRegulator, riskIncident.numberofPeople, riskIncident.location, riskIncident.remediationPlan, riskIncident.remediationActionOwner, riskIncident.notes, riskIncident.eventType, riskIncident.eventStatus, riskIncident.riskCategoryOne, riskIncident.riskCategoryTwo, riskIncident.uniqueId };
        applyGlobalSearch(globalSearch, query, paths);
        AttributeMappingBuilder mapping = buildMapper().map(INCIDENT_TITLE, riskIncident.incidentTitle).map(INTERNALOR_EXTERNAL, riskIncident.internalorExternal).map(OWNER, riskIncident.owner).map(DESCRIPTION_INCIDENT, riskIncident.descriptionIncident).map(OCCURRENCE, riskIncident.occurrence).map(DETECTION, riskIncident.detection).map(INCIDENT_CLOSE, riskIncident.incidentClose).map(INCIDENT_REPORTED, riskIncident.incidentReported).map(REPORTABLETO_REGULATOR, riskIncident.reportabletoRegulator).map(NUMBEROF_PEOPLE, riskIncident.numberofPeople).map(LOCATION, riskIncident.location).map(REMEDIATION_PLAN, riskIncident.remediationPlan).map(REMEDIATION_ACTION_OWNER, riskIncident.remediationActionOwner).map(NOTES, riskIncident.notes).map(EVENT_TYPE, riskIncident.eventType).map(EVENT_STATUS, riskIncident.eventStatus).map(RISK_CATEGORY_ONE, riskIncident.riskCategoryOne).map(RISK_CATEGORY_TWO, riskIncident.riskCategoryTwo).map(UNIQUE_ID, riskIncident.uniqueId);
        applyPagination(pageable, query, mapping);
        applyOrderById(query);
        return loadPage(query, pageable, riskIncident);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param riskCategoryOne
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public Page<RiskIncident> findByRiskCategoryOne(RiskCategoryLevelOne riskCategoryOne, GlobalSearch globalSearch, Pageable pageable) {
        QRiskIncident riskIncident = QRiskIncident.riskIncident;
        JPQLQuery<RiskIncident> query = from(riskIncident);
        Assert.notNull(riskCategoryOne, "riskCategoryOne is required");
        query.where(riskIncident.riskCategoryOne.eq(riskCategoryOne));
        Path<?>[] paths = new Path<?>[] { riskIncident.incidentTitle, riskIncident.internalorExternal, riskIncident.owner, riskIncident.descriptionIncident, riskIncident.occurrence, riskIncident.detection, riskIncident.incidentClose, riskIncident.incidentReported, riskIncident.reportabletoRegulator, riskIncident.numberofPeople, riskIncident.location, riskIncident.remediationPlan, riskIncident.remediationActionOwner, riskIncident.notes, riskIncident.eventType, riskIncident.eventStatus, riskIncident.riskCategoryOne, riskIncident.riskCategoryTwo, riskIncident.uniqueId };
        applyGlobalSearch(globalSearch, query, paths);
        AttributeMappingBuilder mapping = buildMapper().map(INCIDENT_TITLE, riskIncident.incidentTitle).map(INTERNALOR_EXTERNAL, riskIncident.internalorExternal).map(OWNER, riskIncident.owner).map(DESCRIPTION_INCIDENT, riskIncident.descriptionIncident).map(OCCURRENCE, riskIncident.occurrence).map(DETECTION, riskIncident.detection).map(INCIDENT_CLOSE, riskIncident.incidentClose).map(INCIDENT_REPORTED, riskIncident.incidentReported).map(REPORTABLETO_REGULATOR, riskIncident.reportabletoRegulator).map(NUMBEROF_PEOPLE, riskIncident.numberofPeople).map(LOCATION, riskIncident.location).map(REMEDIATION_PLAN, riskIncident.remediationPlan).map(REMEDIATION_ACTION_OWNER, riskIncident.remediationActionOwner).map(NOTES, riskIncident.notes).map(EVENT_TYPE, riskIncident.eventType).map(EVENT_STATUS, riskIncident.eventStatus).map(RISK_CATEGORY_ONE, riskIncident.riskCategoryOne).map(RISK_CATEGORY_TWO, riskIncident.riskCategoryTwo).map(UNIQUE_ID, riskIncident.uniqueId);
        applyPagination(pageable, query, mapping);
        applyOrderById(query);
        return loadPage(query, pageable, riskIncident);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param riskCategoryTwo
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public Page<RiskIncident> findByRiskCategoryTwo(RiskCategoryLevelTwo riskCategoryTwo, GlobalSearch globalSearch, Pageable pageable) {
        QRiskIncident riskIncident = QRiskIncident.riskIncident;
        JPQLQuery<RiskIncident> query = from(riskIncident);
        Assert.notNull(riskCategoryTwo, "riskCategoryTwo is required");
        query.where(riskIncident.riskCategoryTwo.eq(riskCategoryTwo));
        Path<?>[] paths = new Path<?>[] { riskIncident.incidentTitle, riskIncident.internalorExternal, riskIncident.owner, riskIncident.descriptionIncident, riskIncident.occurrence, riskIncident.detection, riskIncident.incidentClose, riskIncident.incidentReported, riskIncident.reportabletoRegulator, riskIncident.numberofPeople, riskIncident.location, riskIncident.remediationPlan, riskIncident.remediationActionOwner, riskIncident.notes, riskIncident.eventType, riskIncident.eventStatus, riskIncident.riskCategoryOne, riskIncident.riskCategoryTwo, riskIncident.uniqueId };
        applyGlobalSearch(globalSearch, query, paths);
        AttributeMappingBuilder mapping = buildMapper().map(INCIDENT_TITLE, riskIncident.incidentTitle).map(INTERNALOR_EXTERNAL, riskIncident.internalorExternal).map(OWNER, riskIncident.owner).map(DESCRIPTION_INCIDENT, riskIncident.descriptionIncident).map(OCCURRENCE, riskIncident.occurrence).map(DETECTION, riskIncident.detection).map(INCIDENT_CLOSE, riskIncident.incidentClose).map(INCIDENT_REPORTED, riskIncident.incidentReported).map(REPORTABLETO_REGULATOR, riskIncident.reportabletoRegulator).map(NUMBEROF_PEOPLE, riskIncident.numberofPeople).map(LOCATION, riskIncident.location).map(REMEDIATION_PLAN, riskIncident.remediationPlan).map(REMEDIATION_ACTION_OWNER, riskIncident.remediationActionOwner).map(NOTES, riskIncident.notes).map(EVENT_TYPE, riskIncident.eventType).map(EVENT_STATUS, riskIncident.eventStatus).map(RISK_CATEGORY_ONE, riskIncident.riskCategoryOne).map(RISK_CATEGORY_TWO, riskIncident.riskCategoryTwo).map(UNIQUE_ID, riskIncident.uniqueId);
        applyPagination(pageable, query, mapping);
        applyOrderById(query);
        return loadPage(query, pageable, riskIncident);
    }
}
