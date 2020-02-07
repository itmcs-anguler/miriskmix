package com.itmcs.roo.mirisk.repository;
import com.itmcs.roo.mirisk.dta.RiskRegister;
import org.springframework.roo.addon.layers.repository.jpa.annotations.RooJpaRepositoryCustom;
import com.itmcs.roo.mirisk.dta.Category;
import com.itmcs.roo.mirisk.dta.ResidualImpactAssessment;
import com.itmcs.roo.mirisk.dta.ResidualRiskLikelihood;
import com.itmcs.roo.mirisk.dta.RiskStatus;
import io.springlets.data.domain.GlobalSearch;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * = RiskRegisterRepositoryCustom
 TODO Auto-generated class documentation
 *
 */
@RooJpaRepositoryCustom(entity = RiskRegister.class)
public interface RiskRegisterRepositoryCustom {

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
     * @param inheritImpactAssessment
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public abstract Page<RiskRegister> findByInheritImpactAssessment(ResidualImpactAssessment inheritImpactAssessment, GlobalSearch globalSearch, Pageable pageable);

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
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public abstract Page<RiskRegister> findByCategory(Category category, GlobalSearch globalSearch, Pageable pageable);

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
}
