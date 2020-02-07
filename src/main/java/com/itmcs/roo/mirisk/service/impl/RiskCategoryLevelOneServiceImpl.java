package com.itmcs.roo.mirisk.service.impl;
import com.itmcs.roo.mirisk.service.api.RiskCategoryLevelOneService;
import org.springframework.roo.addon.layers.service.annotations.RooServiceImpl;
import com.itmcs.roo.mirisk.dta.RiskCategoryLevelOne;
import com.itmcs.roo.mirisk.dta.RiskIncident;
import com.itmcs.roo.mirisk.repository.RiskCategoryLevelOneRepository;
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
 * = RiskCategoryLevelOneServiceImpl
 TODO Auto-generated class documentation
 *
 */
@RooServiceImpl(service = RiskCategoryLevelOneService.class)
@Service
@Transactional(readOnly = true)
public class RiskCategoryLevelOneServiceImpl implements RiskCategoryLevelOneService {

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private RiskCategoryLevelOneRepository riskCategoryLevelOneRepository;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private RiskIncidentService riskIncidentService;

    /**
     * TODO Auto-generated constructor documentation
     *
     * @param riskCategoryLevelOneRepository
     * @param riskIncidentService
     */
    @Autowired
    public RiskCategoryLevelOneServiceImpl(RiskCategoryLevelOneRepository riskCategoryLevelOneRepository, @Lazy RiskIncidentService riskIncidentService) {
        setRiskCategoryLevelOneRepository(riskCategoryLevelOneRepository);
        setRiskIncidentService(riskIncidentService);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return RiskCategoryLevelOneRepository
     */
    public RiskCategoryLevelOneRepository getRiskCategoryLevelOneRepository() {
        return riskCategoryLevelOneRepository;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param riskCategoryLevelOneRepository
     */
    public void setRiskCategoryLevelOneRepository(RiskCategoryLevelOneRepository riskCategoryLevelOneRepository) {
        this.riskCategoryLevelOneRepository = riskCategoryLevelOneRepository;
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
     * @param riskcategorylevelone
     * @return Map
     */
    public Map<String, List<MessageI18n>> validate(RiskCategoryLevelOne riskcategorylevelone) {
        Map<String, List<MessageI18n>> messages = new java.util.HashMap<String, List<MessageI18n>>();
        // TODO: IMPLEMENT HERE THE VALIDATION OF YOUR ENTITY
        return messages;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param riskCategoryLevelOne
     * @param riskIncidentToAdd
     * @return RiskCategoryLevelOne
     */
    @Transactional
    public RiskCategoryLevelOne addToRiskIncident(RiskCategoryLevelOne riskCategoryLevelOne, Iterable<Long> riskIncidentToAdd) {
        List<RiskIncident> riskIncident = getRiskIncidentService().findAll(riskIncidentToAdd);
        riskCategoryLevelOne.addToRiskIncident(riskIncident);
        return getRiskCategoryLevelOneRepository().save(riskCategoryLevelOne);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param riskCategoryLevelOne
     * @param riskIncidentToRemove
     * @return RiskCategoryLevelOne
     */
    @Transactional
    public RiskCategoryLevelOne removeFromRiskIncident(RiskCategoryLevelOne riskCategoryLevelOne, Iterable<Long> riskIncidentToRemove) {
        List<RiskIncident> riskIncident = getRiskIncidentService().findAll(riskIncidentToRemove);
        riskCategoryLevelOne.removeFromRiskIncident(riskIncident);
        return getRiskCategoryLevelOneRepository().save(riskCategoryLevelOne);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param riskCategoryLevelOne
     * @param riskIncident
     * @return RiskCategoryLevelOne
     */
    @Transactional
    public RiskCategoryLevelOne setRiskIncident(RiskCategoryLevelOne riskCategoryLevelOne, Iterable<Long> riskIncident) {
        List<RiskIncident> items = getRiskIncidentService().findAll(riskIncident);
        Set<RiskIncident> currents = riskCategoryLevelOne.getRiskIncident();
        Set<RiskIncident> toRemove = new HashSet<RiskIncident>();
        for (Iterator<RiskIncident> iterator = currents.iterator(); iterator.hasNext(); ) {
            RiskIncident nextRiskIncident = iterator.next();
            if (items.contains(nextRiskIncident)) {
                items.remove(nextRiskIncident);
            } else {
                toRemove.add(nextRiskIncident);
            }
        }
        riskCategoryLevelOne.removeFromRiskIncident(toRemove);
        riskCategoryLevelOne.addToRiskIncident(items);
        return getRiskCategoryLevelOneRepository().save(riskCategoryLevelOne);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param riskCategoryLevelOne
     */
    @Transactional
    public void delete(RiskCategoryLevelOne riskCategoryLevelOne) {
        // Clear bidirectional one-to-many parent relationship with RiskIncident
        for (RiskIncident item : riskCategoryLevelOne.getRiskIncident()) {
            item.setRiskCategoryOne(null);
        }
        getRiskCategoryLevelOneRepository().delete(riskCategoryLevelOne);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param entities
     * @return List
     */
    @Transactional
    public List<RiskCategoryLevelOne> save(Iterable<RiskCategoryLevelOne> entities) {
        return getRiskCategoryLevelOneRepository().save(entities);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param ids
     */
    @Transactional
    public void delete(Iterable<Long> ids) {
        List<RiskCategoryLevelOne> toDelete = getRiskCategoryLevelOneRepository().findAll(ids);
        getRiskCategoryLevelOneRepository().deleteInBatch(toDelete);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param entity
     * @return RiskCategoryLevelOne
     */
    @Transactional
    public RiskCategoryLevelOne save(RiskCategoryLevelOne entity) {
        return getRiskCategoryLevelOneRepository().save(entity);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param id
     * @return RiskCategoryLevelOne
     */
    public RiskCategoryLevelOne findOne(Long id) {
        return getRiskCategoryLevelOneRepository().findOne(id);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param id
     * @return RiskCategoryLevelOne
     */
    public RiskCategoryLevelOne findOneForUpdate(Long id) {
        return getRiskCategoryLevelOneRepository().findOneDetached(id);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param ids
     * @return List
     */
    public List<RiskCategoryLevelOne> findAll(Iterable<Long> ids) {
        return getRiskCategoryLevelOneRepository().findAll(ids);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return List
     */
    public List<RiskCategoryLevelOne> findAll() {
        return getRiskCategoryLevelOneRepository().findAll();
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return Long
     */
    public long count() {
        return getRiskCategoryLevelOneRepository().count();
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public Page<RiskCategoryLevelOne> findAll(GlobalSearch globalSearch, Pageable pageable) {
        return getRiskCategoryLevelOneRepository().findAll(globalSearch, pageable);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param ids
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public Page<RiskCategoryLevelOne> findAllByIdsIn(List<Long> ids, GlobalSearch globalSearch, Pageable pageable) {
        return getRiskCategoryLevelOneRepository().findAllByIdsIn(ids, globalSearch, pageable);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return Class
     */
    public Class<RiskCategoryLevelOne> getEntityType() {
        return RiskCategoryLevelOne.class;
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
