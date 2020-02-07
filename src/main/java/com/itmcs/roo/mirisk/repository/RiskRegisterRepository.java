package com.itmcs.roo.mirisk.repository;
import com.itmcs.roo.mirisk.dta.RiskRegister;
import io.springlets.data.jpa.repository.DetachableJpaRepository;
import org.springframework.roo.addon.layers.repository.jpa.annotations.RooJpaRepository;
import org.springframework.roo.addon.layers.repository.jpa.annotations.finder.RooFinder;
import com.itmcs.roo.mirisk.dta.Category;
import com.itmcs.roo.mirisk.dta.ResidualImpactAssessment;
import com.itmcs.roo.mirisk.dta.ResidualRiskLikelihood;
import com.itmcs.roo.mirisk.dta.RiskStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

/**
 * = RiskRegisterRepository
 TODO Auto-generated class documentation
 *
 */
@RooJpaRepository(entity = RiskRegister.class, finders = { @RooFinder(value = "findByCategoryId", returnType = RiskRegister.class) })
@Transactional(readOnly = true)
public interface RiskRegisterRepository extends DetachableJpaRepository<RiskRegister, Long>, RiskRegisterRepositoryCustom {

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
     * @param inheritImpactAssessment
     * @return Long
     */
    public abstract long countByInheritImpactAssessment(ResidualImpactAssessment inheritImpactAssessment);

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
     * @param riskStatus
     * @return Long
     */
    public abstract long countByRiskStatus(RiskStatus riskStatus);

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
     * @param inheritRiskLikelihood
     * @return Long
     */
    public abstract long countByInheritRiskLikelihood(ResidualRiskLikelihood inheritRiskLikelihood);

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
