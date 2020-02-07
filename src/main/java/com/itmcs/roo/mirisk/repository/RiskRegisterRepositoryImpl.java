package com.itmcs.roo.mirisk.repository;
import io.springlets.data.jpa.repository.support.QueryDslRepositorySupportExt;
import org.springframework.roo.addon.layers.repository.jpa.annotations.RooJpaRepositoryCustomImpl;
import com.itmcs.roo.mirisk.dta.RiskRegister;
import com.itmcs.roo.mirisk.dta.Category;
import com.itmcs.roo.mirisk.dta.QRiskRegister;
import com.itmcs.roo.mirisk.dta.ResidualImpactAssessment;
import com.itmcs.roo.mirisk.dta.ResidualRiskLikelihood;
import com.itmcs.roo.mirisk.dta.RiskStatus;
import com.querydsl.core.types.Path;
import com.querydsl.jpa.JPQLQuery;
import io.springlets.data.domain.GlobalSearch;
import io.springlets.data.jpa.repository.support.QueryDslRepositorySupportExt.AttributeMappingBuilder;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

/**
 * = RiskRegisterRepositoryImpl
 TODO Auto-generated class documentation
 *
 */
@RooJpaRepositoryCustomImpl(repository = RiskRegisterRepositoryCustom.class)
@Transactional(readOnly = true)
public class RiskRegisterRepositoryImpl extends QueryDslRepositorySupportExt<RiskRegister> implements RiskRegisterRepositoryCustom {

    /**
     * Default constructor
     */
    RiskRegisterRepositoryImpl() {
        super(RiskRegister.class);
    }

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    public static final String RESIDUAL_RISK_ASSESSMENT_SCORE = "residualRiskAssessmentScore";

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    public static final String RESIDUAL_IMPACT_ASSESSMENT = "residualImpactAssessment";

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    public static final String RISK_DESCRIPTION = "riskDescription";

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    public static final String RISK_STATUS = "riskStatus";

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    public static final String FURTHUR_ACTION = "furthurAction";

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    public static final String UNIQUE_ID = "uniqueId";

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    public static final String CURRENT_CONTROLS = "currentControls";

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    public static final String INHERIT_RISK_LIKELIHOOD = "inheritRiskLikelihood";

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    public static final String INHERIT_RISK_ASSESSMENT_SCORE = "inheritRiskAssessmentScore";

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    public static final String CATEGORY = "category";

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    public static final String INHERIT_IMPACT_ASSESSMENT = "inheritImpactAssessment";

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    public static final String RESIDUAL_RISK_LIKELIHOOD = "residualRiskLikelihood";

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    public static final String RISK_OWNER = "riskOwner";

    /**
     * TODO Auto-generated method documentation
     *
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public Page<RiskRegister> findAll(GlobalSearch globalSearch, Pageable pageable) {
        QRiskRegister riskRegister = QRiskRegister.riskRegister;
        JPQLQuery<RiskRegister> query = from(riskRegister);
        Path<?>[] paths = new Path<?>[] { riskRegister.inheritRiskAssessmentScore, riskRegister.currentControls, riskRegister.riskOwner, riskRegister.furthurAction, riskRegister.residualRiskAssessmentScore, riskRegister.category, riskRegister.residualRiskLikelihood, riskRegister.residualImpactAssessment, riskRegister.riskStatus, riskRegister.inheritRiskLikelihood, riskRegister.inheritImpactAssessment, riskRegister.riskDescription, riskRegister.uniqueId };
        applyGlobalSearch(globalSearch, query, paths);
        AttributeMappingBuilder mapping = buildMapper().map(INHERIT_RISK_ASSESSMENT_SCORE, riskRegister.inheritRiskAssessmentScore).map(CURRENT_CONTROLS, riskRegister.currentControls).map(RISK_OWNER, riskRegister.riskOwner).map(FURTHUR_ACTION, riskRegister.furthurAction).map(RESIDUAL_RISK_ASSESSMENT_SCORE, riskRegister.residualRiskAssessmentScore).map(CATEGORY, riskRegister.category).map(RESIDUAL_RISK_LIKELIHOOD, riskRegister.residualRiskLikelihood).map(RESIDUAL_IMPACT_ASSESSMENT, riskRegister.residualImpactAssessment).map(RISK_STATUS, riskRegister.riskStatus).map(INHERIT_RISK_LIKELIHOOD, riskRegister.inheritRiskLikelihood).map(INHERIT_IMPACT_ASSESSMENT, riskRegister.inheritImpactAssessment).map(RISK_DESCRIPTION, riskRegister.riskDescription).map(UNIQUE_ID, riskRegister.uniqueId);
        applyPagination(pageable, query, mapping);
        applyOrderById(query);
        return loadPage(query, pageable, riskRegister);
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
        QRiskRegister riskRegister = QRiskRegister.riskRegister;
        JPQLQuery<RiskRegister> query = from(riskRegister);
        Path<?>[] paths = new Path<?>[] { riskRegister.inheritRiskAssessmentScore, riskRegister.currentControls, riskRegister.riskOwner, riskRegister.furthurAction, riskRegister.residualRiskAssessmentScore, riskRegister.category, riskRegister.residualRiskLikelihood, riskRegister.residualImpactAssessment, riskRegister.riskStatus, riskRegister.inheritRiskLikelihood, riskRegister.inheritImpactAssessment, riskRegister.riskDescription, riskRegister.uniqueId };
        applyGlobalSearch(globalSearch, query, paths);
        // Also, filter by the provided ids
        query.where(riskRegister.id.in(ids));
        AttributeMappingBuilder mapping = buildMapper().map(INHERIT_RISK_ASSESSMENT_SCORE, riskRegister.inheritRiskAssessmentScore).map(CURRENT_CONTROLS, riskRegister.currentControls).map(RISK_OWNER, riskRegister.riskOwner).map(FURTHUR_ACTION, riskRegister.furthurAction).map(RESIDUAL_RISK_ASSESSMENT_SCORE, riskRegister.residualRiskAssessmentScore).map(CATEGORY, riskRegister.category).map(RESIDUAL_RISK_LIKELIHOOD, riskRegister.residualRiskLikelihood).map(RESIDUAL_IMPACT_ASSESSMENT, riskRegister.residualImpactAssessment).map(RISK_STATUS, riskRegister.riskStatus).map(INHERIT_RISK_LIKELIHOOD, riskRegister.inheritRiskLikelihood).map(INHERIT_IMPACT_ASSESSMENT, riskRegister.inheritImpactAssessment).map(RISK_DESCRIPTION, riskRegister.riskDescription).map(UNIQUE_ID, riskRegister.uniqueId);
        applyPagination(pageable, query, mapping);
        applyOrderById(query);
        return loadPage(query, pageable, riskRegister);
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
        QRiskRegister riskRegister = QRiskRegister.riskRegister;
        JPQLQuery<RiskRegister> query = from(riskRegister);
        Assert.notNull(category, "category is required");
        query.where(riskRegister.category.eq(category));
        Path<?>[] paths = new Path<?>[] { riskRegister.inheritRiskAssessmentScore, riskRegister.currentControls, riskRegister.riskOwner, riskRegister.furthurAction, riskRegister.residualRiskAssessmentScore, riskRegister.category, riskRegister.residualRiskLikelihood, riskRegister.residualImpactAssessment, riskRegister.riskStatus, riskRegister.inheritRiskLikelihood, riskRegister.inheritImpactAssessment, riskRegister.riskDescription, riskRegister.uniqueId };
        applyGlobalSearch(globalSearch, query, paths);
        AttributeMappingBuilder mapping = buildMapper().map(INHERIT_RISK_ASSESSMENT_SCORE, riskRegister.inheritRiskAssessmentScore).map(CURRENT_CONTROLS, riskRegister.currentControls).map(RISK_OWNER, riskRegister.riskOwner).map(FURTHUR_ACTION, riskRegister.furthurAction).map(RESIDUAL_RISK_ASSESSMENT_SCORE, riskRegister.residualRiskAssessmentScore).map(CATEGORY, riskRegister.category).map(RESIDUAL_RISK_LIKELIHOOD, riskRegister.residualRiskLikelihood).map(RESIDUAL_IMPACT_ASSESSMENT, riskRegister.residualImpactAssessment).map(RISK_STATUS, riskRegister.riskStatus).map(INHERIT_RISK_LIKELIHOOD, riskRegister.inheritRiskLikelihood).map(INHERIT_IMPACT_ASSESSMENT, riskRegister.inheritImpactAssessment).map(RISK_DESCRIPTION, riskRegister.riskDescription).map(UNIQUE_ID, riskRegister.uniqueId);
        applyPagination(pageable, query, mapping);
        applyOrderById(query);
        return loadPage(query, pageable, riskRegister);
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
        QRiskRegister riskRegister = QRiskRegister.riskRegister;
        JPQLQuery<RiskRegister> query = from(riskRegister);
        Assert.notNull(inheritImpactAssessment, "inheritImpactAssessment is required");
        query.where(riskRegister.inheritImpactAssessment.eq(inheritImpactAssessment));
        Path<?>[] paths = new Path<?>[] { riskRegister.inheritRiskAssessmentScore, riskRegister.currentControls, riskRegister.riskOwner, riskRegister.furthurAction, riskRegister.residualRiskAssessmentScore, riskRegister.category, riskRegister.residualRiskLikelihood, riskRegister.residualImpactAssessment, riskRegister.riskStatus, riskRegister.inheritRiskLikelihood, riskRegister.inheritImpactAssessment, riskRegister.riskDescription, riskRegister.uniqueId };
        applyGlobalSearch(globalSearch, query, paths);
        AttributeMappingBuilder mapping = buildMapper().map(INHERIT_RISK_ASSESSMENT_SCORE, riskRegister.inheritRiskAssessmentScore).map(CURRENT_CONTROLS, riskRegister.currentControls).map(RISK_OWNER, riskRegister.riskOwner).map(FURTHUR_ACTION, riskRegister.furthurAction).map(RESIDUAL_RISK_ASSESSMENT_SCORE, riskRegister.residualRiskAssessmentScore).map(CATEGORY, riskRegister.category).map(RESIDUAL_RISK_LIKELIHOOD, riskRegister.residualRiskLikelihood).map(RESIDUAL_IMPACT_ASSESSMENT, riskRegister.residualImpactAssessment).map(RISK_STATUS, riskRegister.riskStatus).map(INHERIT_RISK_LIKELIHOOD, riskRegister.inheritRiskLikelihood).map(INHERIT_IMPACT_ASSESSMENT, riskRegister.inheritImpactAssessment).map(RISK_DESCRIPTION, riskRegister.riskDescription).map(UNIQUE_ID, riskRegister.uniqueId);
        applyPagination(pageable, query, mapping);
        applyOrderById(query);
        return loadPage(query, pageable, riskRegister);
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
        QRiskRegister riskRegister = QRiskRegister.riskRegister;
        JPQLQuery<RiskRegister> query = from(riskRegister);
        Assert.notNull(inheritRiskLikelihood, "inheritRiskLikelihood is required");
        query.where(riskRegister.inheritRiskLikelihood.eq(inheritRiskLikelihood));
        Path<?>[] paths = new Path<?>[] { riskRegister.inheritRiskAssessmentScore, riskRegister.currentControls, riskRegister.riskOwner, riskRegister.furthurAction, riskRegister.residualRiskAssessmentScore, riskRegister.category, riskRegister.residualRiskLikelihood, riskRegister.residualImpactAssessment, riskRegister.riskStatus, riskRegister.inheritRiskLikelihood, riskRegister.inheritImpactAssessment, riskRegister.riskDescription, riskRegister.uniqueId };
        applyGlobalSearch(globalSearch, query, paths);
        AttributeMappingBuilder mapping = buildMapper().map(INHERIT_RISK_ASSESSMENT_SCORE, riskRegister.inheritRiskAssessmentScore).map(CURRENT_CONTROLS, riskRegister.currentControls).map(RISK_OWNER, riskRegister.riskOwner).map(FURTHUR_ACTION, riskRegister.furthurAction).map(RESIDUAL_RISK_ASSESSMENT_SCORE, riskRegister.residualRiskAssessmentScore).map(CATEGORY, riskRegister.category).map(RESIDUAL_RISK_LIKELIHOOD, riskRegister.residualRiskLikelihood).map(RESIDUAL_IMPACT_ASSESSMENT, riskRegister.residualImpactAssessment).map(RISK_STATUS, riskRegister.riskStatus).map(INHERIT_RISK_LIKELIHOOD, riskRegister.inheritRiskLikelihood).map(INHERIT_IMPACT_ASSESSMENT, riskRegister.inheritImpactAssessment).map(RISK_DESCRIPTION, riskRegister.riskDescription).map(UNIQUE_ID, riskRegister.uniqueId);
        applyPagination(pageable, query, mapping);
        applyOrderById(query);
        return loadPage(query, pageable, riskRegister);
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
        QRiskRegister riskRegister = QRiskRegister.riskRegister;
        JPQLQuery<RiskRegister> query = from(riskRegister);
        Assert.notNull(residualImpactAssessment, "residualImpactAssessment is required");
        query.where(riskRegister.residualImpactAssessment.eq(residualImpactAssessment));
        Path<?>[] paths = new Path<?>[] { riskRegister.inheritRiskAssessmentScore, riskRegister.currentControls, riskRegister.riskOwner, riskRegister.furthurAction, riskRegister.residualRiskAssessmentScore, riskRegister.category, riskRegister.residualRiskLikelihood, riskRegister.residualImpactAssessment, riskRegister.riskStatus, riskRegister.inheritRiskLikelihood, riskRegister.inheritImpactAssessment, riskRegister.riskDescription, riskRegister.uniqueId };
        applyGlobalSearch(globalSearch, query, paths);
        AttributeMappingBuilder mapping = buildMapper().map(INHERIT_RISK_ASSESSMENT_SCORE, riskRegister.inheritRiskAssessmentScore).map(CURRENT_CONTROLS, riskRegister.currentControls).map(RISK_OWNER, riskRegister.riskOwner).map(FURTHUR_ACTION, riskRegister.furthurAction).map(RESIDUAL_RISK_ASSESSMENT_SCORE, riskRegister.residualRiskAssessmentScore).map(CATEGORY, riskRegister.category).map(RESIDUAL_RISK_LIKELIHOOD, riskRegister.residualRiskLikelihood).map(RESIDUAL_IMPACT_ASSESSMENT, riskRegister.residualImpactAssessment).map(RISK_STATUS, riskRegister.riskStatus).map(INHERIT_RISK_LIKELIHOOD, riskRegister.inheritRiskLikelihood).map(INHERIT_IMPACT_ASSESSMENT, riskRegister.inheritImpactAssessment).map(RISK_DESCRIPTION, riskRegister.riskDescription).map(UNIQUE_ID, riskRegister.uniqueId);
        applyPagination(pageable, query, mapping);
        applyOrderById(query);
        return loadPage(query, pageable, riskRegister);
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
        QRiskRegister riskRegister = QRiskRegister.riskRegister;
        JPQLQuery<RiskRegister> query = from(riskRegister);
        Assert.notNull(residualRiskLikelihood, "residualRiskLikelihood is required");
        query.where(riskRegister.residualRiskLikelihood.eq(residualRiskLikelihood));
        Path<?>[] paths = new Path<?>[] { riskRegister.inheritRiskAssessmentScore, riskRegister.currentControls, riskRegister.riskOwner, riskRegister.furthurAction, riskRegister.residualRiskAssessmentScore, riskRegister.category, riskRegister.residualRiskLikelihood, riskRegister.residualImpactAssessment, riskRegister.riskStatus, riskRegister.inheritRiskLikelihood, riskRegister.inheritImpactAssessment, riskRegister.riskDescription, riskRegister.uniqueId };
        applyGlobalSearch(globalSearch, query, paths);
        AttributeMappingBuilder mapping = buildMapper().map(INHERIT_RISK_ASSESSMENT_SCORE, riskRegister.inheritRiskAssessmentScore).map(CURRENT_CONTROLS, riskRegister.currentControls).map(RISK_OWNER, riskRegister.riskOwner).map(FURTHUR_ACTION, riskRegister.furthurAction).map(RESIDUAL_RISK_ASSESSMENT_SCORE, riskRegister.residualRiskAssessmentScore).map(CATEGORY, riskRegister.category).map(RESIDUAL_RISK_LIKELIHOOD, riskRegister.residualRiskLikelihood).map(RESIDUAL_IMPACT_ASSESSMENT, riskRegister.residualImpactAssessment).map(RISK_STATUS, riskRegister.riskStatus).map(INHERIT_RISK_LIKELIHOOD, riskRegister.inheritRiskLikelihood).map(INHERIT_IMPACT_ASSESSMENT, riskRegister.inheritImpactAssessment).map(RISK_DESCRIPTION, riskRegister.riskDescription).map(UNIQUE_ID, riskRegister.uniqueId);
        applyPagination(pageable, query, mapping);
        applyOrderById(query);
        return loadPage(query, pageable, riskRegister);
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
        QRiskRegister riskRegister = QRiskRegister.riskRegister;
        JPQLQuery<RiskRegister> query = from(riskRegister);
        Assert.notNull(riskStatus, "riskStatus is required");
        query.where(riskRegister.riskStatus.eq(riskStatus));
        Path<?>[] paths = new Path<?>[] { riskRegister.inheritRiskAssessmentScore, riskRegister.currentControls, riskRegister.riskOwner, riskRegister.furthurAction, riskRegister.residualRiskAssessmentScore, riskRegister.category, riskRegister.residualRiskLikelihood, riskRegister.residualImpactAssessment, riskRegister.riskStatus, riskRegister.inheritRiskLikelihood, riskRegister.inheritImpactAssessment, riskRegister.riskDescription, riskRegister.uniqueId };
        applyGlobalSearch(globalSearch, query, paths);
        AttributeMappingBuilder mapping = buildMapper().map(INHERIT_RISK_ASSESSMENT_SCORE, riskRegister.inheritRiskAssessmentScore).map(CURRENT_CONTROLS, riskRegister.currentControls).map(RISK_OWNER, riskRegister.riskOwner).map(FURTHUR_ACTION, riskRegister.furthurAction).map(RESIDUAL_RISK_ASSESSMENT_SCORE, riskRegister.residualRiskAssessmentScore).map(CATEGORY, riskRegister.category).map(RESIDUAL_RISK_LIKELIHOOD, riskRegister.residualRiskLikelihood).map(RESIDUAL_IMPACT_ASSESSMENT, riskRegister.residualImpactAssessment).map(RISK_STATUS, riskRegister.riskStatus).map(INHERIT_RISK_LIKELIHOOD, riskRegister.inheritRiskLikelihood).map(INHERIT_IMPACT_ASSESSMENT, riskRegister.inheritImpactAssessment).map(RISK_DESCRIPTION, riskRegister.riskDescription).map(UNIQUE_ID, riskRegister.uniqueId);
        applyPagination(pageable, query, mapping);
        applyOrderById(query);
        return loadPage(query, pageable, riskRegister);
    }
}
