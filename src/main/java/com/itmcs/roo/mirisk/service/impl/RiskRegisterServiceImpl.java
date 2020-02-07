package com.itmcs.roo.mirisk.service.impl;
import com.itmcs.roo.mirisk.service.api.RiskRegisterService;
import org.springframework.roo.addon.layers.service.annotations.RooServiceImpl;
import com.itmcs.roo.mirisk.dta.Category;
import com.itmcs.roo.mirisk.dta.ResidualImpactAssessment;
import com.itmcs.roo.mirisk.dta.ResidualRiskLikelihood;
import com.itmcs.roo.mirisk.dta.RiskRegister;
import com.itmcs.roo.mirisk.dta.RiskStatus;
import com.itmcs.roo.mirisk.repository.RiskRegisterRepository;
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
 * = RiskRegisterServiceImpl
 TODO Auto-generated class documentation
 *
 */
@RooServiceImpl(service = RiskRegisterService.class)
@Service
@Transactional(readOnly = true)
public class RiskRegisterServiceImpl implements RiskRegisterService {

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private RiskRegisterRepository riskRegisterRepository;

    /**
     * TODO Auto-generated constructor documentation
     *
     * @param riskRegisterRepository
     */
    @Autowired
    public RiskRegisterServiceImpl(RiskRegisterRepository riskRegisterRepository) {
        setRiskRegisterRepository(riskRegisterRepository);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return RiskRegisterRepository
     */
    public RiskRegisterRepository getRiskRegisterRepository() {
        return riskRegisterRepository;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param riskRegisterRepository
     */
    public void setRiskRegisterRepository(RiskRegisterRepository riskRegisterRepository) {
        this.riskRegisterRepository = riskRegisterRepository;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param riskregister
     * @return Map
     */
    public Map<String, List<MessageI18n>> validate(RiskRegister riskregister) {
        Map<String, List<MessageI18n>> messages = new java.util.HashMap<String, List<MessageI18n>>();
        // TODO: IMPLEMENT HERE THE VALIDATION OF YOUR ENTITY
        return messages;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param riskRegister
     */
    @Transactional
    public void delete(RiskRegister riskRegister) {
        // Clear bidirectional many-to-one child relationship with ResidualRiskLikelihood
        if (riskRegister.getResidualRiskLikelihood() != null) {
            riskRegister.getResidualRiskLikelihood().getRiskRegisters().remove(riskRegister);
        }
        // Clear bidirectional many-to-one child relationship with ResidualImpactAssessment
        if (riskRegister.getInheritImpactAssessment() != null) {
            riskRegister.getInheritImpactAssessment().getInheritImpactAssessment().remove(riskRegister);
        }
        // Clear bidirectional many-to-one child relationship with ResidualImpactAssessment
        if (riskRegister.getResidualImpactAssessment() != null) {
            riskRegister.getResidualImpactAssessment().getRiskRegisters().remove(riskRegister);
        }
        // Clear bidirectional many-to-one child relationship with RiskStatus
        if (riskRegister.getRiskStatus() != null) {
            riskRegister.getRiskStatus().getRiskRegisters().remove(riskRegister);
        }
        // Clear bidirectional many-to-one child relationship with Category
        if (riskRegister.getCategory() != null) {
            riskRegister.getCategory().getRiskRegisters().remove(riskRegister);
        }
        // Clear bidirectional many-to-one child relationship with ResidualRiskLikelihood
        if (riskRegister.getInheritRiskLikelihood() != null) {
            riskRegister.getInheritRiskLikelihood().getInheritRiskLikelihood().remove(riskRegister);
        }
        getRiskRegisterRepository().delete(riskRegister);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param entities
     * @return List
     */
    @Transactional
    public List<RiskRegister> save(Iterable<RiskRegister> entities) {
        return getRiskRegisterRepository().save(entities);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param ids
     */
    @Transactional
    public void delete(Iterable<Long> ids) {
        List<RiskRegister> toDelete = getRiskRegisterRepository().findAll(ids);
        getRiskRegisterRepository().deleteInBatch(toDelete);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param entity
     * @return RiskRegister
     */
    @Transactional
    public RiskRegister save(RiskRegister entity) {
        return getRiskRegisterRepository().save(entity);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param id
     * @return RiskRegister
     */
    public RiskRegister findOne(Long id) {
        return getRiskRegisterRepository().findOne(id);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param id
     * @return RiskRegister
     */
    public RiskRegister findOneForUpdate(Long id) {
        return getRiskRegisterRepository().findOneDetached(id);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param ids
     * @return List
     */
    public List<RiskRegister> findAll(Iterable<Long> ids) {
        return getRiskRegisterRepository().findAll(ids);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return List
     */
    public List<RiskRegister> findAll() {
        return getRiskRegisterRepository().findAll();
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return Long
     */
    public long count() {
        return getRiskRegisterRepository().count();
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public Page<RiskRegister> findAll(GlobalSearch globalSearch, Pageable pageable) {
        return getRiskRegisterRepository().findAll(globalSearch, pageable);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param ids
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public Page<RiskRegister> findAllByIdsIn(List<Long> ids, GlobalSearch globalSearch, Pageable pageable) {
        return getRiskRegisterRepository().findAllByIdsIn(ids, globalSearch, pageable);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param category
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public Page<RiskRegister> findByCategory(Category category, GlobalSearch globalSearch, Pageable pageable) {
        return getRiskRegisterRepository().findByCategory(category, globalSearch, pageable);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param inheritImpactAssessment
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public Page<RiskRegister> findByInheritImpactAssessment(ResidualImpactAssessment inheritImpactAssessment, GlobalSearch globalSearch, Pageable pageable) {
        return getRiskRegisterRepository().findByInheritImpactAssessment(inheritImpactAssessment, globalSearch, pageable);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param inheritRiskLikelihood
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public Page<RiskRegister> findByInheritRiskLikelihood(ResidualRiskLikelihood inheritRiskLikelihood, GlobalSearch globalSearch, Pageable pageable) {
        return getRiskRegisterRepository().findByInheritRiskLikelihood(inheritRiskLikelihood, globalSearch, pageable);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param residualImpactAssessment
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public Page<RiskRegister> findByResidualImpactAssessment(ResidualImpactAssessment residualImpactAssessment, GlobalSearch globalSearch, Pageable pageable) {
        return getRiskRegisterRepository().findByResidualImpactAssessment(residualImpactAssessment, globalSearch, pageable);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param residualRiskLikelihood
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public Page<RiskRegister> findByResidualRiskLikelihood(ResidualRiskLikelihood residualRiskLikelihood, GlobalSearch globalSearch, Pageable pageable) {
        return getRiskRegisterRepository().findByResidualRiskLikelihood(residualRiskLikelihood, globalSearch, pageable);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param riskStatus
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public Page<RiskRegister> findByRiskStatus(RiskStatus riskStatus, GlobalSearch globalSearch, Pageable pageable) {
        return getRiskRegisterRepository().findByRiskStatus(riskStatus, globalSearch, pageable);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param category
     * @return Long
     */
    public long countByCategory(Category category) {
        return getRiskRegisterRepository().countByCategory(category);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param inheritImpactAssessment
     * @return Long
     */
    public long countByInheritImpactAssessment(ResidualImpactAssessment inheritImpactAssessment) {
        return getRiskRegisterRepository().countByInheritImpactAssessment(inheritImpactAssessment);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param inheritRiskLikelihood
     * @return Long
     */
    public long countByInheritRiskLikelihood(ResidualRiskLikelihood inheritRiskLikelihood) {
        return getRiskRegisterRepository().countByInheritRiskLikelihood(inheritRiskLikelihood);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param residualImpactAssessment
     * @return Long
     */
    public long countByResidualImpactAssessment(ResidualImpactAssessment residualImpactAssessment) {
        return getRiskRegisterRepository().countByResidualImpactAssessment(residualImpactAssessment);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param residualRiskLikelihood
     * @return Long
     */
    public long countByResidualRiskLikelihood(ResidualRiskLikelihood residualRiskLikelihood) {
        return getRiskRegisterRepository().countByResidualRiskLikelihood(residualRiskLikelihood);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param riskStatus
     * @return Long
     */
    public long countByRiskStatus(RiskStatus riskStatus) {
        return getRiskRegisterRepository().countByRiskStatus(riskStatus);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param id
     * @param pageable
     * @return Page
     */
    public Page<RiskRegister> findByCategoryId(Long id, Pageable pageable) {
        return getRiskRegisterRepository().findByCategoryId(id, pageable);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param id
     * @return Long
     */
    public long countByCategoryId(Long id) {
        return getRiskRegisterRepository().countByCategoryId(id);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return Class
     */
    public Class<RiskRegister> getEntityType() {
        return RiskRegister.class;
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
