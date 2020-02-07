package com.itmcs.roo.mirisk.service.impl;
import com.itmcs.roo.mirisk.service.api.RiskIncidentService;
import org.springframework.roo.addon.layers.service.annotations.RooServiceImpl;
import com.itmcs.roo.mirisk.dta.EventStatus;
import com.itmcs.roo.mirisk.dta.EventType;
import com.itmcs.roo.mirisk.dta.RiskCategoryLevelOne;
import com.itmcs.roo.mirisk.dta.RiskCategoryLevelTwo;
import com.itmcs.roo.mirisk.dta.RiskIncident;
import com.itmcs.roo.mirisk.repository.RiskIncidentRepository;
import io.springlets.data.domain.GlobalSearch;
import io.springlets.data.web.validation.MessageI18n;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * = RiskIncidentServiceImpl
 TODO Auto-generated class documentation
 *
 */
@RooServiceImpl(service = RiskIncidentService.class)
@Service
@Transactional(readOnly = true)
public class RiskIncidentServiceImpl implements RiskIncidentService {

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private RiskIncidentRepository riskIncidentRepository;

    /**
     * TODO Auto-generated constructor documentation
     *
     * @param riskIncidentRepository
     */
    @Autowired
    public RiskIncidentServiceImpl(RiskIncidentRepository riskIncidentRepository) {
        setRiskIncidentRepository(riskIncidentRepository);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return RiskIncidentRepository
     */
    public RiskIncidentRepository getRiskIncidentRepository() {
        return riskIncidentRepository;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param riskIncidentRepository
     */
    public void setRiskIncidentRepository(RiskIncidentRepository riskIncidentRepository) {
        this.riskIncidentRepository = riskIncidentRepository;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param riskincident
     * @return Map
     */
    public Map<String, List<MessageI18n>> validate(RiskIncident riskincident) {
        Map<String, List<MessageI18n>> messages = new java.util.HashMap<String, List<MessageI18n>>();
        // TODO: IMPLEMENT HERE THE VALIDATION OF YOUR ENTITY
        return messages;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param riskIncident
     */
    @Transactional
    public void delete(RiskIncident riskIncident) {
        // Clear bidirectional many-to-one child relationship with RiskCategoryLevelOne
        if (riskIncident.getRiskCategoryOne() != null) {
            riskIncident.getRiskCategoryOne().getRiskIncident().remove(riskIncident);
        }
        // Clear bidirectional many-to-one child relationship with RiskCategoryLevelTwo
        if (riskIncident.getRiskCategoryTwo() != null) {
            riskIncident.getRiskCategoryTwo().getRiskIncident().remove(riskIncident);
        }
        // Clear bidirectional many-to-one child relationship with EventStatus
        if (riskIncident.getEventStatus() != null) {
            riskIncident.getEventStatus().getRiskIncident().remove(riskIncident);
        }
        // Clear bidirectional many-to-one child relationship with EventType
        if (riskIncident.getEventType() != null) {
            riskIncident.getEventType().getRiskIncident().remove(riskIncident);
        }
        getRiskIncidentRepository().delete(riskIncident);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param entities
     * @return List
     */
    @Transactional
    public List<RiskIncident> save(Iterable<RiskIncident> entities) {
        return getRiskIncidentRepository().save(entities);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param ids
     */
    @Transactional
    public void delete(Iterable<Long> ids) {
        List<RiskIncident> toDelete = getRiskIncidentRepository().findAll(ids);
        getRiskIncidentRepository().deleteInBatch(toDelete);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param entity
     * @return RiskIncident
     */
    @Transactional
    public RiskIncident save(RiskIncident entity) {
        return getRiskIncidentRepository().save(entity);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param id
     * @return RiskIncident
     */
    public RiskIncident findOne(Long id) {
        return getRiskIncidentRepository().findOne(id);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param id
     * @return RiskIncident
     */
    public RiskIncident findOneForUpdate(Long id) {
        return getRiskIncidentRepository().findOneDetached(id);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param ids
     * @return List
     */
    public List<RiskIncident> findAll(Iterable<Long> ids) {
        return getRiskIncidentRepository().findAll(ids);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return List
     */
    public List<RiskIncident> findAll() {
        return getRiskIncidentRepository().findAll();
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return Long
     */
    public long count() {
        return getRiskIncidentRepository().count();
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public Page<RiskIncident> findAll(GlobalSearch globalSearch, Pageable pageable) {
        return getRiskIncidentRepository().findAll(globalSearch, pageable);
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
        return getRiskIncidentRepository().findAllByIdsIn(ids, globalSearch, pageable);
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
        return getRiskIncidentRepository().findByEventStatus(eventStatus, globalSearch, pageable);
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
        return getRiskIncidentRepository().findByEventType(eventType, globalSearch, pageable);
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
        return getRiskIncidentRepository().findByRiskCategoryOne(riskCategoryOne, globalSearch, pageable);
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
        return getRiskIncidentRepository().findByRiskCategoryTwo(riskCategoryTwo, globalSearch, pageable);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param eventStatus
     * @return Long
     */
    public long countByEventStatus(EventStatus eventStatus) {
        return getRiskIncidentRepository().countByEventStatus(eventStatus);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param eventType
     * @return Long
     */
    public long countByEventType(EventType eventType) {
        return getRiskIncidentRepository().countByEventType(eventType);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param riskCategoryOne
     * @return Long
     */
    public long countByRiskCategoryOne(RiskCategoryLevelOne riskCategoryOne) {
        return getRiskIncidentRepository().countByRiskCategoryOne(riskCategoryOne);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param riskCategoryTwo
     * @return Long
     */
    public long countByRiskCategoryTwo(RiskCategoryLevelTwo riskCategoryTwo) {
        return getRiskIncidentRepository().countByRiskCategoryTwo(riskCategoryTwo);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return Class
     */
    public Class<RiskIncident> getEntityType() {
        return RiskIncident.class;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return Class
     */
    public Class<Long> getIdType() {
        return Long.class;
    }
}
