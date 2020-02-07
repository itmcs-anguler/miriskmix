package com.itmcs.roo.mirisk.service.impl;
import com.itmcs.roo.mirisk.service.api.RiskStatusService;
import org.springframework.roo.addon.layers.service.annotations.RooServiceImpl;
import com.itmcs.roo.mirisk.dta.RiskRegister;
import com.itmcs.roo.mirisk.dta.RiskStatus;
import com.itmcs.roo.mirisk.repository.RiskStatusRepository;
import com.itmcs.roo.mirisk.service.api.RiskRegisterService;
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
 * = RiskStatusServiceImpl
 TODO Auto-generated class documentation
 *
 */
@RooServiceImpl(service = RiskStatusService.class)
@Service
@Transactional(readOnly = true)
public class RiskStatusServiceImpl implements RiskStatusService {

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private RiskStatusRepository riskStatusRepository;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private RiskRegisterService riskRegisterService;

    /**
     * TODO Auto-generated constructor documentation
     *
     * @param riskStatusRepository
     * @param riskRegisterService
     */
    @Autowired
    public RiskStatusServiceImpl(RiskStatusRepository riskStatusRepository, @Lazy RiskRegisterService riskRegisterService) {
        setRiskStatusRepository(riskStatusRepository);
        setRiskRegisterService(riskRegisterService);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return RiskStatusRepository
     */
    public RiskStatusRepository getRiskStatusRepository() {
        return riskStatusRepository;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param riskStatusRepository
     */
    public void setRiskStatusRepository(RiskStatusRepository riskStatusRepository) {
        this.riskStatusRepository = riskStatusRepository;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return RiskRegisterService
     */
    public RiskRegisterService getRiskRegisterService() {
        return riskRegisterService;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param riskRegisterService
     */
    public void setRiskRegisterService(RiskRegisterService riskRegisterService) {
        this.riskRegisterService = riskRegisterService;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param riskstatus
     * @return Map
     */
    public Map<String, List<MessageI18n>> validate(RiskStatus riskstatus) {
        Map<String, List<MessageI18n>> messages = new java.util.HashMap<String, List<MessageI18n>>();
        // TODO: IMPLEMENT HERE THE VALIDATION OF YOUR ENTITY
        return messages;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param riskStatus
     * @param riskRegistersToAdd
     * @return RiskStatus
     */
    @Transactional
    public RiskStatus addToRiskRegisters(RiskStatus riskStatus, Iterable<Long> riskRegistersToAdd) {
        List<RiskRegister> riskRegisters = getRiskRegisterService().findAll(riskRegistersToAdd);
        riskStatus.addToRiskRegisters(riskRegisters);
        return getRiskStatusRepository().save(riskStatus);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param riskStatus
     * @param riskRegistersToRemove
     * @return RiskStatus
     */
    @Transactional
    public RiskStatus removeFromRiskRegisters(RiskStatus riskStatus, Iterable<Long> riskRegistersToRemove) {
        List<RiskRegister> riskRegisters = getRiskRegisterService().findAll(riskRegistersToRemove);
        riskStatus.removeFromRiskRegisters(riskRegisters);
        return getRiskStatusRepository().save(riskStatus);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param riskStatus
     * @param riskRegisters
     * @return RiskStatus
     */
    @Transactional
    public RiskStatus setRiskRegisters(RiskStatus riskStatus, Iterable<Long> riskRegisters) {
        List<RiskRegister> items = getRiskRegisterService().findAll(riskRegisters);
        Set<RiskRegister> currents = riskStatus.getRiskRegisters();
        Set<RiskRegister> toRemove = new HashSet<RiskRegister>();
        for (Iterator<RiskRegister> iterator = currents.iterator(); iterator.hasNext(); ) {
            RiskRegister nextRiskRegister = iterator.next();
            if (items.contains(nextRiskRegister)) {
                items.remove(nextRiskRegister);
            } else {
                toRemove.add(nextRiskRegister);
            }
        }
        riskStatus.removeFromRiskRegisters(toRemove);
        riskStatus.addToRiskRegisters(items);
        return getRiskStatusRepository().save(riskStatus);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param riskStatus
     */
    @Transactional
    public void delete(RiskStatus riskStatus) {
        // Clear bidirectional one-to-many parent relationship with RiskRegister
        for (RiskRegister item : riskStatus.getRiskRegisters()) {
            item.setRiskStatus(null);
        }
        getRiskStatusRepository().delete(riskStatus);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param entities
     * @return List
     */
    @Transactional
    public List<RiskStatus> save(Iterable<RiskStatus> entities) {
        return getRiskStatusRepository().save(entities);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param ids
     */
    @Transactional
    public void delete(Iterable<Long> ids) {
        List<RiskStatus> toDelete = getRiskStatusRepository().findAll(ids);
        getRiskStatusRepository().deleteInBatch(toDelete);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param entity
     * @return RiskStatus
     */
    @Transactional
    public RiskStatus save(RiskStatus entity) {
        return getRiskStatusRepository().save(entity);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param id
     * @return RiskStatus
     */
    public RiskStatus findOne(Long id) {
        return getRiskStatusRepository().findOne(id);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param id
     * @return RiskStatus
     */
    public RiskStatus findOneForUpdate(Long id) {
        return getRiskStatusRepository().findOneDetached(id);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param ids
     * @return List
     */
    public List<RiskStatus> findAll(Iterable<Long> ids) {
        return getRiskStatusRepository().findAll(ids);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return List
     */
    public List<RiskStatus> findAll() {
        return getRiskStatusRepository().findAll();
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return Long
     */
    public long count() {
        return getRiskStatusRepository().count();
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public Page<RiskStatus> findAll(GlobalSearch globalSearch, Pageable pageable) {
        return getRiskStatusRepository().findAll(globalSearch, pageable);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param ids
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public Page<RiskStatus> findAllByIdsIn(List<Long> ids, GlobalSearch globalSearch, Pageable pageable) {
        return getRiskStatusRepository().findAllByIdsIn(ids, globalSearch, pageable);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return Class
     */
    public Class<RiskStatus> getEntityType() {
        return RiskStatus.class;
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
