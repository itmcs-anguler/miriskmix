package com.itmcs.roo.mirisk.service.impl;
import com.itmcs.roo.mirisk.service.api.GrossImpactService;
import org.springframework.roo.addon.layers.service.annotations.RooServiceImpl;
import com.itmcs.roo.mirisk.dta.Ccy;
import com.itmcs.roo.mirisk.dta.GrossImpact;
import com.itmcs.roo.mirisk.repository.GrossImpactRepository;
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
 * = GrossImpactServiceImpl
 TODO Auto-generated class documentation
 *
 */
@RooServiceImpl(service = GrossImpactService.class)
@Service
@Transactional(readOnly = true)
public class GrossImpactServiceImpl implements GrossImpactService {

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private GrossImpactRepository grossImpactRepository;

    /**
     * TODO Auto-generated constructor documentation
     *
     * @param grossImpactRepository
     */
    @Autowired
    public GrossImpactServiceImpl(GrossImpactRepository grossImpactRepository) {
        setGrossImpactRepository(grossImpactRepository);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return GrossImpactRepository
     */
    public GrossImpactRepository getGrossImpactRepository() {
        return grossImpactRepository;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param grossImpactRepository
     */
    public void setGrossImpactRepository(GrossImpactRepository grossImpactRepository) {
        this.grossImpactRepository = grossImpactRepository;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param grossimpact
     * @return Map
     */
    public Map<String, List<MessageI18n>> validate(GrossImpact grossimpact) {
        Map<String, List<MessageI18n>> messages = new java.util.HashMap<String, List<MessageI18n>>();
        // TODO: IMPLEMENT HERE THE VALIDATION OF YOUR ENTITY
        return messages;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param grossImpact
     */
    @Transactional
    public void delete(GrossImpact grossImpact) {
        // Clear bidirectional many-to-one child relationship with Ccy
        if (grossImpact.getCcy() != null) {
            grossImpact.getCcy().getGrossImpact().remove(grossImpact);
        }
        getGrossImpactRepository().delete(grossImpact);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param entities
     * @return List
     */
    @Transactional
    public List<GrossImpact> save(Iterable<GrossImpact> entities) {
        return getGrossImpactRepository().save(entities);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param ids
     */
    @Transactional
    public void delete(Iterable<Long> ids) {
        List<GrossImpact> toDelete = getGrossImpactRepository().findAll(ids);
        getGrossImpactRepository().deleteInBatch(toDelete);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param entity
     * @return GrossImpact
     */
    @Transactional
    public GrossImpact save(GrossImpact entity) {
        return getGrossImpactRepository().save(entity);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param id
     * @return GrossImpact
     */
    public GrossImpact findOne(Long id) {
        return getGrossImpactRepository().findOne(id);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param id
     * @return GrossImpact
     */
    public GrossImpact findOneForUpdate(Long id) {
        return getGrossImpactRepository().findOneDetached(id);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param ids
     * @return List
     */
    public List<GrossImpact> findAll(Iterable<Long> ids) {
        return getGrossImpactRepository().findAll(ids);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return List
     */
    public List<GrossImpact> findAll() {
        return getGrossImpactRepository().findAll();
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return Long
     */
    public long count() {
        return getGrossImpactRepository().count();
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public Page<GrossImpact> findAll(GlobalSearch globalSearch, Pageable pageable) {
        return getGrossImpactRepository().findAll(globalSearch, pageable);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param ids
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public Page<GrossImpact> findAllByIdsIn(List<Long> ids, GlobalSearch globalSearch, Pageable pageable) {
        return getGrossImpactRepository().findAllByIdsIn(ids, globalSearch, pageable);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param ccy
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public Page<GrossImpact> findByCcy(Ccy ccy, GlobalSearch globalSearch, Pageable pageable) {
        return getGrossImpactRepository().findByCcy(ccy, globalSearch, pageable);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param ccy
     * @return Long
     */
    public long countByCcy(Ccy ccy) {
        return getGrossImpactRepository().countByCcy(ccy);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return Class
     */
    public Class<GrossImpact> getEntityType() {
        return GrossImpact.class;
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
