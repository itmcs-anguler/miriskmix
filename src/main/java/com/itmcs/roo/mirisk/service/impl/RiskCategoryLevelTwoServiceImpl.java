package com.itmcs.roo.mirisk.service.impl;
import com.itmcs.roo.mirisk.service.api.RiskCategoryLevelTwoService;
import org.springframework.roo.addon.layers.service.annotations.RooServiceImpl;
import com.itmcs.roo.mirisk.dta.RiskCategoryLevelTwo;
import com.itmcs.roo.mirisk.dta.RiskIncident;
import com.itmcs.roo.mirisk.repository.RiskCategoryLevelTwoRepository;
import com.itmcs.roo.mirisk.service.api.RiskIncidentService;
import io.springlets.data.domain.GlobalSearch;
import io.springlets.data.web.validation.MessageI18n;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * = RiskCategoryLevelTwoServiceImpl
 TODO Auto-generated class documentation
 *
 */
@RooServiceImpl(service = RiskCategoryLevelTwoService.class)
@Service
@Transactional(readOnly = true)
public class RiskCategoryLevelTwoServiceImpl implements RiskCategoryLevelTwoService {

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private RiskIncidentService riskIncidentService;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private RiskCategoryLevelTwoRepository riskCategoryLevelTwoRepository;

    /**
     * TODO Auto-generated constructor documentation
     *
     * @param riskCategoryLevelTwoRepository
     * @param riskIncidentService
     */
    @Autowired
    public RiskCategoryLevelTwoServiceImpl(RiskCategoryLevelTwoRepository riskCategoryLevelTwoRepository, @Lazy RiskIncidentService riskIncidentService) {
        setRiskCategoryLevelTwoRepository(riskCategoryLevelTwoRepository);
        setRiskIncidentService(riskIncidentService);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return RiskCategoryLevelTwoRepository
     */
    public RiskCategoryLevelTwoRepository getRiskCategoryLevelTwoRepository() {
        return riskCategoryLevelTwoRepository;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param riskCategoryLevelTwoRepository
     */
    public void setRiskCategoryLevelTwoRepository(RiskCategoryLevelTwoRepository riskCategoryLevelTwoRepository) {
        this.riskCategoryLevelTwoRepository = riskCategoryLevelTwoRepository;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return RiskIncidentService
     */
    public RiskIncidentService getRiskIncidentService() {
        return riskIncidentService;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param riskIncidentService
     */
    public void setRiskIncidentService(RiskIncidentService riskIncidentService) {
        this.riskIncidentService = riskIncidentService;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param riskcategoryleveltwo
     * @return Map
     */
    public Map<String, List<MessageI18n>> validate(RiskCategoryLevelTwo riskcategoryleveltwo) {
        Map<String, List<MessageI18n>> messages = new java.util.HashMap<String, List<MessageI18n>>();
        // TODO: IMPLEMENT HERE THE VALIDATION OF YOUR ENTITY
        return messages;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param riskCategoryLevelTwo
     * @param riskIncidentToAdd
     * @return RiskCategoryLevelTwo
     */
    @Transactional
    public RiskCategoryLevelTwo addToRiskIncident(RiskCategoryLevelTwo riskCategoryLevelTwo, Iterable<Long> riskIncidentToAdd) {
        List<RiskIncident> riskIncident = getRiskIncidentService().findAll(riskIncidentToAdd);
        riskCategoryLevelTwo.addToRiskIncident(riskIncident);
        return getRiskCategoryLevelTwoRepository().save(riskCategoryLevelTwo);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param riskCategoryLevelTwo
     * @param riskIncidentToRemove
     * @return RiskCategoryLevelTwo
     */
    @Transactional
    public RiskCategoryLevelTwo removeFromRiskIncident(RiskCategoryLevelTwo riskCategoryLevelTwo, Iterable<Long> riskIncidentToRemove) {
        List<RiskIncident> riskIncident = getRiskIncidentService().findAll(riskIncidentToRemove);
        riskCategoryLevelTwo.removeFromRiskIncident(riskIncident);
        return getRiskCategoryLevelTwoRepository().save(riskCategoryLevelTwo);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param riskCategoryLevelTwo
     * @param riskIncident
     * @return RiskCategoryLevelTwo
     */
    @Transactional
    public RiskCategoryLevelTwo setRiskIncident(RiskCategoryLevelTwo riskCategoryLevelTwo, Iterable<Long> riskIncident) {
        List<RiskIncident> items = getRiskIncidentService().findAll(riskIncident);
        Set<RiskIncident> currents = riskCategoryLevelTwo.getRiskIncident();
        Set<RiskIncident> toRemove = new HashSet<RiskIncident>();
        for (Iterator<RiskIncident> iterator = currents.iterator(); iterator.hasNext(); ) {
            RiskIncident nextRiskIncident = iterator.next();
            if (items.contains(nextRiskIncident)) {
                items.remove(nextRiskIncident);
            } else {
                toRemove.add(nextRiskIncident);
            }
        }
        riskCategoryLevelTwo.removeFromRiskIncident(toRemove);
        riskCategoryLevelTwo.addToRiskIncident(items);
        return getRiskCategoryLevelTwoRepository().save(riskCategoryLevelTwo);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param riskCategoryLevelTwo
     */
    @Transactional
    public void delete(RiskCategoryLevelTwo riskCategoryLevelTwo) {
        // Clear bidirectional one-to-many parent relationship with RiskIncident
        for (RiskIncident item : riskCategoryLevelTwo.getRiskIncident()) {
            item.setRiskCategoryTwo(null);
        }
        getRiskCategoryLevelTwoRepository().delete(riskCategoryLevelTwo);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param entities
     * @return List
     */
    @Transactional
    public List<RiskCategoryLevelTwo> save(Iterable<RiskCategoryLevelTwo> entities) {
        return getRiskCategoryLevelTwoRepository().save(entities);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param ids
     */
    @Transactional
    public void delete(Iterable<Long> ids) {
        List<RiskCategoryLevelTwo> toDelete = getRiskCategoryLevelTwoRepository().findAll(ids);
        getRiskCategoryLevelTwoRepository().deleteInBatch(toDelete);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param entity
     * @return RiskCategoryLevelTwo
     */
    @Transactional
    public RiskCategoryLevelTwo save(RiskCategoryLevelTwo entity) {
        return getRiskCategoryLevelTwoRepository().save(entity);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param id
     * @return RiskCategoryLevelTwo
     */
    public RiskCategoryLevelTwo findOne(Long id) {
        return getRiskCategoryLevelTwoRepository().findOne(id);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param id
     * @return RiskCategoryLevelTwo
     */
    public RiskCategoryLevelTwo findOneForUpdate(Long id) {
        return getRiskCategoryLevelTwoRepository().findOneDetached(id);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param ids
     * @return List
     */
    public List<RiskCategoryLevelTwo> findAll(Iterable<Long> ids) {
        return getRiskCategoryLevelTwoRepository().findAll(ids);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return List
     */
    public List<RiskCategoryLevelTwo> findAll() {
        return getRiskCategoryLevelTwoRepository().findAll();
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return Long
     */
    public long count() {
        return getRiskCategoryLevelTwoRepository().count();
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public Page<RiskCategoryLevelTwo> findAll(GlobalSearch globalSearch, Pageable pageable) {
        return getRiskCategoryLevelTwoRepository().findAll(globalSearch, pageable);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param ids
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public Page<RiskCategoryLevelTwo> findAllByIdsIn(List<Long> ids, GlobalSearch globalSearch, Pageable pageable) {
        return getRiskCategoryLevelTwoRepository().findAllByIdsIn(ids, globalSearch, pageable);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return Class
     */
    public Class<RiskCategoryLevelTwo> getEntityType() {
        return RiskCategoryLevelTwo.class;
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
