package com.itmcs.roo.mirisk.service.impl;
import com.itmcs.roo.mirisk.service.api.ResidualImpactAssessmentService;
import org.springframework.roo.addon.layers.service.annotations.RooServiceImpl;
import com.itmcs.roo.mirisk.dta.ResidualImpactAssessment;
import com.itmcs.roo.mirisk.dta.RiskRegister;
import com.itmcs.roo.mirisk.repository.ResidualImpactAssessmentRepository;
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
 * = ResidualImpactAssessmentServiceImpl
 TODO Auto-generated class documentation
 *
 */
@RooServiceImpl(service = ResidualImpactAssessmentService.class)
@Service
@Transactional(readOnly = true)
public class ResidualImpactAssessmentServiceImpl implements ResidualImpactAssessmentService {

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private ResidualImpactAssessmentRepository residualImpactAssessmentRepository;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private RiskRegisterService riskRegisterService;

    /**
     * TODO Auto-generated constructor documentation
     *
     * @param residualImpactAssessmentRepository
     * @param riskRegisterService
     */
    @Autowired
    public ResidualImpactAssessmentServiceImpl(ResidualImpactAssessmentRepository residualImpactAssessmentRepository, @Lazy RiskRegisterService riskRegisterService) {
        setResidualImpactAssessmentRepository(residualImpactAssessmentRepository);
        setRiskRegisterService(riskRegisterService);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return ResidualImpactAssessmentRepository
     */
    public ResidualImpactAssessmentRepository getResidualImpactAssessmentRepository() {
        return residualImpactAssessmentRepository;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param residualImpactAssessmentRepository
     */
    public void setResidualImpactAssessmentRepository(ResidualImpactAssessmentRepository residualImpactAssessmentRepository) {
        this.residualImpactAssessmentRepository = residualImpactAssessmentRepository;
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
     * @param residualimpactassessment
     * @return Map
     */
    public Map<String, List<MessageI18n>> validate(ResidualImpactAssessment residualimpactassessment) {
        Map<String, List<MessageI18n>> messages = new java.util.HashMap<String, List<MessageI18n>>();
        // TODO: IMPLEMENT HERE THE VALIDATION OF YOUR ENTITY
        return messages;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param residualImpactAssessment
     * @param inheritImpactAssessmentToAdd
     * @return ResidualImpactAssessment
     */
    @Transactional
    public ResidualImpactAssessment addToInheritImpactAssessment(ResidualImpactAssessment residualImpactAssessment, Iterable<Long> inheritImpactAssessmentToAdd) {
        List<RiskRegister> inheritImpactAssessment = getRiskRegisterService().findAll(inheritImpactAssessmentToAdd);
        residualImpactAssessment.addToInheritImpactAssessment(inheritImpactAssessment);
        return getResidualImpactAssessmentRepository().save(residualImpactAssessment);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param residualImpactAssessment
     * @param inheritImpactAssessmentToRemove
     * @return ResidualImpactAssessment
     */
    @Transactional
    public ResidualImpactAssessment removeFromInheritImpactAssessment(ResidualImpactAssessment residualImpactAssessment, Iterable<Long> inheritImpactAssessmentToRemove) {
        List<RiskRegister> inheritImpactAssessment = getRiskRegisterService().findAll(inheritImpactAssessmentToRemove);
        residualImpactAssessment.removeFromInheritImpactAssessment(inheritImpactAssessment);
        return getResidualImpactAssessmentRepository().save(residualImpactAssessment);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param residualImpactAssessment
     * @param inheritImpactAssessment
     * @return ResidualImpactAssessment
     */
    @Transactional
    public ResidualImpactAssessment setInheritImpactAssessment(ResidualImpactAssessment residualImpactAssessment, Iterable<Long> inheritImpactAssessment) {
        List<RiskRegister> items = getRiskRegisterService().findAll(inheritImpactAssessment);
        Set<RiskRegister> currents = residualImpactAssessment.getInheritImpactAssessment();
        Set<RiskRegister> toRemove = new HashSet<RiskRegister>();
        for (Iterator<RiskRegister> iterator = currents.iterator(); iterator.hasNext(); ) {
            RiskRegister nextRiskRegister = iterator.next();
            if (items.contains(nextRiskRegister)) {
                items.remove(nextRiskRegister);
            } else {
                toRemove.add(nextRiskRegister);
            }
        }
        residualImpactAssessment.removeFromInheritImpactAssessment(toRemove);
        residualImpactAssessment.addToInheritImpactAssessment(items);
        return getResidualImpactAssessmentRepository().save(residualImpactAssessment);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param residualImpactAssessment
     */
    @Transactional
    public void delete(ResidualImpactAssessment residualImpactAssessment) {
        // Clear bidirectional one-to-many parent relationship with RiskRegister
        for (RiskRegister item : residualImpactAssessment.getInheritImpactAssessment()) {
            item.setInheritImpactAssessment(null);
        }
        // Clear bidirectional one-to-many parent relationship with RiskRegister
        for (RiskRegister item : residualImpactAssessment.getRiskRegisters()) {
            item.setResidualImpactAssessment(null);
        }
        getResidualImpactAssessmentRepository().delete(residualImpactAssessment);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param entities
     * @return List
     */
    @Transactional
    public List<ResidualImpactAssessment> save(Iterable<ResidualImpactAssessment> entities) {
        return getResidualImpactAssessmentRepository().save(entities);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param ids
     */
    @Transactional
    public void delete(Iterable<Long> ids) {
        List<ResidualImpactAssessment> toDelete = getResidualImpactAssessmentRepository().findAll(ids);
        getResidualImpactAssessmentRepository().deleteInBatch(toDelete);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param entity
     * @return ResidualImpactAssessment
     */
    @Transactional
    public ResidualImpactAssessment save(ResidualImpactAssessment entity) {
        return getResidualImpactAssessmentRepository().save(entity);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param id
     * @return ResidualImpactAssessment
     */
    public ResidualImpactAssessment findOne(Long id) {
        return getResidualImpactAssessmentRepository().findOne(id);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param id
     * @return ResidualImpactAssessment
     */
    public ResidualImpactAssessment findOneForUpdate(Long id) {
        return getResidualImpactAssessmentRepository().findOneDetached(id);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param ids
     * @return List
     */
    public List<ResidualImpactAssessment> findAll(Iterable<Long> ids) {
        return getResidualImpactAssessmentRepository().findAll(ids);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return List
     */
    public List<ResidualImpactAssessment> findAll() {
        return getResidualImpactAssessmentRepository().findAll();
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return Long
     */
    public long count() {
        return getResidualImpactAssessmentRepository().count();
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public Page<ResidualImpactAssessment> findAll(GlobalSearch globalSearch, Pageable pageable) {
        return getResidualImpactAssessmentRepository().findAll(globalSearch, pageable);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param ids
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public Page<ResidualImpactAssessment> findAllByIdsIn(List<Long> ids, GlobalSearch globalSearch, Pageable pageable) {
        return getResidualImpactAssessmentRepository().findAllByIdsIn(ids, globalSearch, pageable);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return Class
     */
    public Class<ResidualImpactAssessment> getEntityType() {
        return ResidualImpactAssessment.class;
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
