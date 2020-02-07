package com.itmcs.roo.mirisk.service.api;
import com.itmcs.roo.mirisk.dta.RiskRegister;
import io.springlets.data.web.validation.ValidatorService;
import io.springlets.format.EntityResolver;
import org.springframework.roo.addon.layers.service.annotations.RooService;
import com.itmcs.roo.mirisk.dta.Category;
import com.itmcs.roo.mirisk.dta.ResidualImpactAssessment;
import com.itmcs.roo.mirisk.dta.ResidualRiskLikelihood;
import com.itmcs.roo.mirisk.dta.RiskStatus;
import io.springlets.data.domain.GlobalSearch;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * = RiskRegisterService
 TODO Auto-generated class documentation
 *
 */
@RooService(entity = RiskRegister.class)
public interface RiskRegisterService extends EntityResolver<RiskRegister, Long>, ValidatorService<RiskRegister> {

    /**
     * TODO Auto-generated method documentation
     *
     * @param id
     * @return RiskRegister
     */
    public abstract RiskRegister findOne(Long id);

    /**
     * TODO Auto-generated method documentation
     *
     * @param riskRegister
     */
    public abstract void delete(RiskRegister riskRegister);

    /**
     * TODO Auto-generated method documentation
     *
     * @param entities
     * @return List
     */
    public abstract List<RiskRegister> save(Iterable<RiskRegister> entities);

    /**
     * TODO Auto-generated method documentation
     *
     * @param ids
     */
    public abstract void delete(Iterable<Long> ids);

    /**
     * TODO Auto-generated method documentation
     *
     * @param entity
     * @return RiskRegister
     */
    public abstract RiskRegister save(RiskRegister entity);

    /**
     * TODO Auto-generated method documentation
     *
     * @param id
     * @return RiskRegister
     */
    public abstract RiskRegister findOneForUpdate(Long id);

    /**
     * TODO Auto-generated method documentation
     *
     * @param ids
     * @return List
     */
    public abstract List<RiskRegister> findAll(Iterable<Long> ids);

    /**
     * TODO Auto-generated method documentation
     *
     * @return List
     */
    public abstract List<RiskRegister> findAll();

    /**
     * TODO Auto-generated method documentation
     *
     * @return Long
     */
    public abstract long count();

    /**
     * TODO Auto-generated method documentation
     *
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public abstract Page<RiskRegister> findAll(GlobalSearch globalSearch, Pageable pageable);

    /**
     * TODO Auto-generated method documentation
     *
     * @param ids
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public abstract Page<RiskRegister> findAllByIdsIn(List<Long> ids, GlobalSearch globalSearch, Pageable pageable);

    /**
     * TODO Auto-generated method documentation
     *
     * @param category
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public abstract Page<RiskRegister> findByCategory(Category category, GlobalSearch globalSearch, Pageable pageable);

    /**
     * TODO Auto-generated method documentation
     *
     * @param inheritImpactAssessment
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public abstract Page<RiskRegister> findByInheritImpactAssessment(ResidualImpactAssessment inheritImpactAssessment, GlobalSearch globalSearch, Pageable pageable);

    /**
     * TODO Auto-generated method documentation
     *
     * @param inheritRiskLikelihood
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public abstract Page<RiskRegister> findByInheritRiskLikelihood(ResidualRiskLikelihood inheritRiskLikelihood, GlobalSearch globalSearch, Pageable pageable);

    /**
     * TODO Auto-generated method documentation
     *
     * @param residualImpactAssessment
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public abstract Page<RiskRegister> findByResidualImpactAssessment(ResidualImpactAssessment residualImpactAssessment, GlobalSearch globalSearch, Pageable pageable);

    /**
     * TODO Auto-generated method documentation
     *
     * @param residualRiskLikelihood
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public abstract Page<RiskRegister> findByResidualRiskLikelihood(ResidualRiskLikelihood residualRiskLikelihood, GlobalSearch globalSearch, Pageable pageable);

    /**
     * TODO Auto-generated method documentation
     *
     * @param riskStatus
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public abstract Page<RiskRegister> findByRiskStatus(RiskStatus riskStatus, GlobalSearch globalSearch, Pageable pageable);

    /**
     * TODO Auto-generated method documentation
     *
     * @param category
     * @return Long
     */
    public abstract long countByCategory(Category category);

    /**
     * TODO Auto-generated method documentation
     *
     * @param inheritImpactAssessment
     * @return Long
     */
    public abstract long countByInheritImpactAssessment(ResidualImpactAssessment inheritImpactAssessment);

    /**
     * TODO Auto-generated method documentation
     *
     * @param inheritRiskLikelihood
     * @return Long
     */
    public abstract long countByInheritRiskLikelihood(ResidualRiskLikelihood inheritRiskLikelihood);

    /**
     * TODO Auto-generated method documentation
     *
     * @param residualImpactAssessment
     * @return Long
     */
    public abstract long countByResidualImpactAssessment(ResidualImpactAssessment residualImpactAssessment);

    /**
     * TODO Auto-generated method documentation
     *
     * @param residualRiskLikelihood
     * @return Long
     */
    public abstract long countByResidualRiskLikelihood(ResidualRiskLikelihood residualRiskLikelihood);

    /**
     * TODO Auto-generated method documentation
     *
     * @param riskStatus
     * @return Long
     */
    public abstract long countByRiskStatus(RiskStatus riskStatus);

    /**
     * TODO Auto-generated method documentation
     *
     * @param id
     * @param pageable
     * @return Page
     */
    public abstract Page<RiskRegister> findByCategoryId(Long id, Pageable pageable);

    /**
     * TODO Auto-generated method documentation
     *
     * @param id
     * @return Long
     */
    public abstract long countByCategoryId(Long id);
}
