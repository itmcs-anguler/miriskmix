package com.itmcs.roo.mirisk.service.impl;
import com.itmcs.roo.mirisk.service.api.RecoveryService;
import org.springframework.roo.addon.layers.service.annotations.RooServiceImpl;
import com.itmcs.roo.mirisk.dta.Ccy;
import com.itmcs.roo.mirisk.dta.Recovery;
import com.itmcs.roo.mirisk.repository.RecoveryRepository;
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
 * = RecoveryServiceImpl
 TODO Auto-generated class documentation
 *
 */
@RooServiceImpl(service = RecoveryService.class)
@Service
@Transactional(readOnly = true)
public class RecoveryServiceImpl implements RecoveryService {

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private RecoveryRepository recoveryRepository;

    /**
     * TODO Auto-generated constructor documentation
     *
     * @param recoveryRepository
     */
    @Autowired
    public RecoveryServiceImpl(RecoveryRepository recoveryRepository) {
        setRecoveryRepository(recoveryRepository);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return RecoveryRepository
     */
    public RecoveryRepository getRecoveryRepository() {
        return recoveryRepository;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param recoveryRepository
     */
    public void setRecoveryRepository(RecoveryRepository recoveryRepository) {
        this.recoveryRepository = recoveryRepository;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param recovery
     * @return Map
     */
    public Map<String, List<MessageI18n>> validate(Recovery recovery) {
        Map<String, List<MessageI18n>> messages = new java.util.HashMap<String, List<MessageI18n>>();
        // TODO: IMPLEMENT HERE THE VALIDATION OF YOUR ENTITY
        return messages;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param recovery
     */
    @Transactional
    public void delete(Recovery recovery) {
        // Clear bidirectional many-to-one child relationship with Ccy
        if (recovery.getCcy() != null) {
            recovery.getCcy().getRecovery().remove(recovery);
        }
        getRecoveryRepository().delete(recovery);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param entities
     * @return List
     */
    @Transactional
    public List<Recovery> save(Iterable<Recovery> entities) {
        return getRecoveryRepository().save(entities);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param ids
     */
    @Transactional
    public void delete(Iterable<Long> ids) {
        List<Recovery> toDelete = getRecoveryRepository().findAll(ids);
        getRecoveryRepository().deleteInBatch(toDelete);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param entity
     * @return Recovery
     */
    @Transactional
    public Recovery save(Recovery entity) {
        return getRecoveryRepository().save(entity);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param id
     * @return Recovery
     */
    public Recovery findOne(Long id) {
        return getRecoveryRepository().findOne(id);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param id
     * @return Recovery
     */
    public Recovery findOneForUpdate(Long id) {
        return getRecoveryRepository().findOneDetached(id);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param ids
     * @return List
     */
    public List<Recovery> findAll(Iterable<Long> ids) {
        return getRecoveryRepository().findAll(ids);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return List
     */
    public List<Recovery> findAll() {
        return getRecoveryRepository().findAll();
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return Long
     */
    public long count() {
        return getRecoveryRepository().count();
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public Page<Recovery> findAll(GlobalSearch globalSearch, Pageable pageable) {
        return getRecoveryRepository().findAll(globalSearch, pageable);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param ids
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public Page<Recovery> findAllByIdsIn(List<Long> ids, GlobalSearch globalSearch, Pageable pageable) {
        return getRecoveryRepository().findAllByIdsIn(ids, globalSearch, pageable);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param ccy
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public Page<Recovery> findByCcy(Ccy ccy, GlobalSearch globalSearch, Pageable pageable) {
        return getRecoveryRepository().findByCcy(ccy, globalSearch, pageable);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param ccy
     * @return Long
     */
    public long countByCcy(Ccy ccy) {
        return getRecoveryRepository().countByCcy(ccy);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return Class
     */
    public Class<Recovery> getEntityType() {
        return Recovery.class;
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
