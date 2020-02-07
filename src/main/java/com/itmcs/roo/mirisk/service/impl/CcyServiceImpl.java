package com.itmcs.roo.mirisk.service.impl;
import com.itmcs.roo.mirisk.service.api.CcyService;
import org.springframework.roo.addon.layers.service.annotations.RooServiceImpl;
import com.itmcs.roo.mirisk.dta.Ccy;
import com.itmcs.roo.mirisk.dta.GrossImpact;
import com.itmcs.roo.mirisk.dta.Recovery;
import com.itmcs.roo.mirisk.repository.CcyRepository;
import com.itmcs.roo.mirisk.service.api.GrossImpactService;
import com.itmcs.roo.mirisk.service.api.RecoveryService;
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
 * = CcyServiceImpl
 TODO Auto-generated class documentation
 *
 */
@RooServiceImpl(service = CcyService.class)
@Service
@Transactional(readOnly = true)
public class CcyServiceImpl implements CcyService {

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private RecoveryService recoveryService;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private CcyRepository ccyRepository;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private GrossImpactService grossImpactService;

    /**
     * TODO Auto-generated constructor documentation
     *
     * @param ccyRepository
     * @param grossImpactService
     * @param recoveryService
     */
    @Autowired
    public CcyServiceImpl(CcyRepository ccyRepository, @Lazy GrossImpactService grossImpactService, @Lazy RecoveryService recoveryService) {
        setCcyRepository(ccyRepository);
        setGrossImpactService(grossImpactService);
        setRecoveryService(recoveryService);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return CcyRepository
     */
    public CcyRepository getCcyRepository() {
        return ccyRepository;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param ccyRepository
     */
    public void setCcyRepository(CcyRepository ccyRepository) {
        this.ccyRepository = ccyRepository;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return GrossImpactService
     */
    public GrossImpactService getGrossImpactService() {
        return grossImpactService;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param grossImpactService
     */
    public void setGrossImpactService(GrossImpactService grossImpactService) {
        this.grossImpactService = grossImpactService;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return RecoveryService
     */
    public RecoveryService getRecoveryService() {
        return recoveryService;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param recoveryService
     */
    public void setRecoveryService(RecoveryService recoveryService) {
        this.recoveryService = recoveryService;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param ccy
     * @return Map
     */
    public Map<String, List<MessageI18n>> validate(Ccy ccy) {
        Map<String, List<MessageI18n>> messages = new java.util.HashMap<String, List<MessageI18n>>();
        // TODO: IMPLEMENT HERE THE VALIDATION OF YOUR ENTITY
        return messages;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param ccy
     * @param grossImpactToAdd
     * @return Ccy
     */
    @Transactional
    public Ccy addToGrossImpact(Ccy ccy, Iterable<Long> grossImpactToAdd) {
        List<GrossImpact> grossImpact = getGrossImpactService().findAll(grossImpactToAdd);
        ccy.addToGrossImpact(grossImpact);
        return getCcyRepository().save(ccy);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param ccy
     * @param recoveryToAdd
     * @return Ccy
     */
    @Transactional
    public Ccy addToRecovery(Ccy ccy, Iterable<Long> recoveryToAdd) {
        List<Recovery> recovery = getRecoveryService().findAll(recoveryToAdd);
        ccy.addToRecovery(recovery);
        return getCcyRepository().save(ccy);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param ccy
     * @param grossImpactToRemove
     * @return Ccy
     */
    @Transactional
    public Ccy removeFromGrossImpact(Ccy ccy, Iterable<Long> grossImpactToRemove) {
        List<GrossImpact> grossImpact = getGrossImpactService().findAll(grossImpactToRemove);
        ccy.removeFromGrossImpact(grossImpact);
        return getCcyRepository().save(ccy);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param ccy
     * @param recoveryToRemove
     * @return Ccy
     */
    @Transactional
    public Ccy removeFromRecovery(Ccy ccy, Iterable<Long> recoveryToRemove) {
        List<Recovery> recovery = getRecoveryService().findAll(recoveryToRemove);
        ccy.removeFromRecovery(recovery);
        return getCcyRepository().save(ccy);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param ccy
     * @param grossImpact
     * @return Ccy
     */
    @Transactional
    public Ccy setGrossImpact(Ccy ccy, Iterable<Long> grossImpact) {
        List<GrossImpact> items = getGrossImpactService().findAll(grossImpact);
        Set<GrossImpact> currents = ccy.getGrossImpact();
        Set<GrossImpact> toRemove = new HashSet<GrossImpact>();
        for (Iterator<GrossImpact> iterator = currents.iterator(); iterator.hasNext(); ) {
            GrossImpact nextGrossImpact = iterator.next();
            if (items.contains(nextGrossImpact)) {
                items.remove(nextGrossImpact);
            } else {
                toRemove.add(nextGrossImpact);
            }
        }
        ccy.removeFromGrossImpact(toRemove);
        ccy.addToGrossImpact(items);
        return getCcyRepository().save(ccy);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param ccy
     * @param recovery
     * @return Ccy
     */
    @Transactional
    public Ccy setRecovery(Ccy ccy, Iterable<Long> recovery) {
        List<Recovery> items = getRecoveryService().findAll(recovery);
        Set<Recovery> currents = ccy.getRecovery();
        Set<Recovery> toRemove = new HashSet<Recovery>();
        for (Iterator<Recovery> iterator = currents.iterator(); iterator.hasNext(); ) {
            Recovery nextRecovery = iterator.next();
            if (items.contains(nextRecovery)) {
                items.remove(nextRecovery);
            } else {
                toRemove.add(nextRecovery);
            }
        }
        ccy.removeFromRecovery(toRemove);
        ccy.addToRecovery(items);
        return getCcyRepository().save(ccy);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param ccy
     */
    @Transactional
    public void delete(Ccy ccy) {
        // Clear bidirectional one-to-many parent relationship with GrossImpact
        for (GrossImpact item : ccy.getGrossImpact()) {
            item.setCcy(null);
        }
        // Clear bidirectional one-to-many parent relationship with Recovery
        for (Recovery item : ccy.getRecovery()) {
            item.setCcy(null);
        }
        getCcyRepository().delete(ccy);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param entities
     * @return List
     */
    @Transactional
    public List<Ccy> save(Iterable<Ccy> entities) {
        return getCcyRepository().save(entities);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param ids
     */
    @Transactional
    public void delete(Iterable<Long> ids) {
        List<Ccy> toDelete = getCcyRepository().findAll(ids);
        getCcyRepository().deleteInBatch(toDelete);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param entity
     * @return Ccy
     */
    @Transactional
    public Ccy save(Ccy entity) {
        return getCcyRepository().save(entity);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param id
     * @return Ccy
     */
    public Ccy findOne(Long id) {
        return getCcyRepository().findOne(id);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param id
     * @return Ccy
     */
    public Ccy findOneForUpdate(Long id) {
        return getCcyRepository().findOneDetached(id);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param ids
     * @return List
     */
    public List<Ccy> findAll(Iterable<Long> ids) {
        return getCcyRepository().findAll(ids);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return List
     */
    public List<Ccy> findAll() {
        return getCcyRepository().findAll();
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return Long
     */
    public long count() {
        return getCcyRepository().count();
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public Page<Ccy> findAll(GlobalSearch globalSearch, Pageable pageable) {
        return getCcyRepository().findAll(globalSearch, pageable);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param ids
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public Page<Ccy> findAllByIdsIn(List<Long> ids, GlobalSearch globalSearch, Pageable pageable) {
        return getCcyRepository().findAllByIdsIn(ids, globalSearch, pageable);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return Class
     */
    public Class<Ccy> getEntityType() {
        return Ccy.class;
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
