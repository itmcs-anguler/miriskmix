package com.itmcs.roo.mirisk.service.impl;
import com.itmcs.roo.mirisk.service.api.ResidualRiskLikelihoodService;
import org.springframework.roo.addon.layers.service.annotations.RooServiceImpl;
import com.itmcs.roo.mirisk.dta.ResidualRiskLikelihood;
import com.itmcs.roo.mirisk.dta.RiskRegister;
import com.itmcs.roo.mirisk.repository.ResidualRiskLikelihoodRepository;
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
 * = ResidualRiskLikelihoodServiceImpl
 TODO Auto-generated class documentation
 *
 */
@RooServiceImpl(service = ResidualRiskLikelihoodService.class)
@Service
@Transactional(readOnly = true)
public class ResidualRiskLikelihoodServiceImpl implements ResidualRiskLikelihoodService {

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private RiskRegisterService riskRegisterService;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private ResidualRiskLikelihoodRepository residualRiskLikelihoodRepository;

    /**
     * TODO Auto-generated constructor documentation
     *
     * @param residualRiskLikelihoodRepository
     * @param riskRegisterService
     */
    @Autowired
    public ResidualRiskLikelihoodServiceImpl(ResidualRiskLikelihoodRepository residualRiskLikelihoodRepository, @Lazy RiskRegisterService riskRegisterService) {
        setResidualRiskLikelihoodRepository(residualRiskLikelihoodRepository);
        setRiskRegisterService(riskRegisterService);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return ResidualRiskLikelihoodRepository
     */
    public ResidualRiskLikelihoodRepository getResidualRiskLikelihoodRepository() {
        return residualRiskLikelihoodRepository;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param residualRiskLikelihoodRepository
     */
    public void setResidualRiskLikelihoodRepository(ResidualRiskLikelihoodRepository residualRiskLikelihoodRepository) {
        this.residualRiskLikelihoodRepository = residualRiskLikelihoodRepository;
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
     * @param residualrisklikelihood
     * @return Map
     */
    public Map<String, List<MessageI18n>> validate(ResidualRiskLikelihood residualrisklikelihood) {
        Map<String, List<MessageI18n>> messages = new java.util.HashMap<String, List<MessageI18n>>();
        // TODO: IMPLEMENT HERE THE VALIDATION OF YOUR ENTITY
        return messages;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param residualRiskLikelihood
     * @param inheritRiskLikelihoodToAdd
     * @return ResidualRiskLikelihood
     */
    @Transactional
    public ResidualRiskLikelihood addToInheritRiskLikelihood(ResidualRiskLikelihood residualRiskLikelihood, Iterable<Long> inheritRiskLikelihoodToAdd) {
        List<RiskRegister> inheritRiskLikelihood = getRiskRegisterService().findAll(inheritRiskLikelihoodToAdd);
        residualRiskLikelihood.addToInheritRiskLikelihood(inheritRiskLikelihood);
        return getResidualRiskLikelihoodRepository().save(residualRiskLikelihood);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param residualRiskLikelihood
     * @param inheritRiskLikelihoodToRemove
     * @return ResidualRiskLikelihood
     */
    @Transactional
    public ResidualRiskLikelihood removeFromInheritRiskLikelihood(ResidualRiskLikelihood residualRiskLikelihood, Iterable<Long> inheritRiskLikelihoodToRemove) {
        List<RiskRegister> inheritRiskLikelihood = getRiskRegisterService().findAll(inheritRiskLikelihoodToRemove);
        residualRiskLikelihood.removeFromInheritRiskLikelihood(inheritRiskLikelihood);
        return getResidualRiskLikelihoodRepository().save(residualRiskLikelihood);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param residualRiskLikelihood
     * @param inheritRiskLikelihood
     * @return ResidualRiskLikelihood
     */
    @Transactional
    public ResidualRiskLikelihood setInheritRiskLikelihood(ResidualRiskLikelihood residualRiskLikelihood, Iterable<Long> inheritRiskLikelihood) {
        List<RiskRegister> items = getRiskRegisterService().findAll(inheritRiskLikelihood);
        Set<RiskRegister> currents = residualRiskLikelihood.getInheritRiskLikelihood();
        Set<RiskRegister> toRemove = new HashSet<RiskRegister>();
        for (Iterator<RiskRegister> iterator = currents.iterator(); iterator.hasNext(); ) {
            RiskRegister nextRiskRegister = iterator.next();
            if (items.contains(nextRiskRegister)) {
                items.remove(nextRiskRegister);
            } else {
                toRemove.add(nextRiskRegister);
            }
        }
        residualRiskLikelihood.removeFromInheritRiskLikelihood(toRemove);
        residualRiskLikelihood.addToInheritRiskLikelihood(items);
        return getResidualRiskLikelihoodRepository().save(residualRiskLikelihood);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param residualRiskLikelihood
     */
    @Transactional
    public void delete(ResidualRiskLikelihood residualRiskLikelihood) {
        // Clear bidirectional one-to-many parent relationship with RiskRegister
        for (RiskRegister item : residualRiskLikelihood.getInheritRiskLikelihood()) {
            item.setInheritRiskLikelihood(null);
        }
        // Clear bidirectional one-to-many parent relationship with RiskRegister
        for (RiskRegister item : residualRiskLikelihood.getRiskRegisters()) {
            item.setResidualRiskLikelihood(null);
        }
        getResidualRiskLikelihoodRepository().delete(residualRiskLikelihood);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param entities
     * @return List
     */
    @Transactional
    public List<ResidualRiskLikelihood> save(Iterable<ResidualRiskLikelihood> entities) {
        return getResidualRiskLikelihoodRepository().save(entities);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param ids
     */
    @Transactional
    public void delete(Iterable<Long> ids) {
        List<ResidualRiskLikelihood> toDelete = getResidualRiskLikelihoodRepository().findAll(ids);
        getResidualRiskLikelihoodRepository().deleteInBatch(toDelete);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param entity
     * @return ResidualRiskLikelihood
     */
    @Transactional
    public ResidualRiskLikelihood save(ResidualRiskLikelihood entity) {
        return getResidualRiskLikelihoodRepository().save(entity);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param id
     * @return ResidualRiskLikelihood
     */
    public ResidualRiskLikelihood findOne(Long id) {
        return getResidualRiskLikelihoodRepository().findOne(id);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param id
     * @return ResidualRiskLikelihood
     */
    public ResidualRiskLikelihood findOneForUpdate(Long id) {
        return getResidualRiskLikelihoodRepository().findOneDetached(id);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param ids
     * @return List
     */
    public List<ResidualRiskLikelihood> findAll(Iterable<Long> ids) {
        return getResidualRiskLikelihoodRepository().findAll(ids);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return List
     */
    public List<ResidualRiskLikelihood> findAll() {
        return getResidualRiskLikelihoodRepository().findAll();
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return Long
     */
    public long count() {
        return getResidualRiskLikelihoodRepository().count();
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public Page<ResidualRiskLikelihood> findAll(GlobalSearch globalSearch, Pageable pageable) {
        return getResidualRiskLikelihoodRepository().findAll(globalSearch, pageable);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param ids
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public Page<ResidualRiskLikelihood> findAllByIdsIn(List<Long> ids, GlobalSearch globalSearch, Pageable pageable) {
        return getResidualRiskLikelihoodRepository().findAllByIdsIn(ids, globalSearch, pageable);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return Class
     */
    public Class<ResidualRiskLikelihood> getEntityType() {
        return ResidualRiskLikelihood.class;
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
