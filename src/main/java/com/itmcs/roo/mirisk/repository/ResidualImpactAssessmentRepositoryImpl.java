package com.itmcs.roo.mirisk.repository;
import io.springlets.data.jpa.repository.support.QueryDslRepositorySupportExt;
import org.springframework.roo.addon.layers.repository.jpa.annotations.RooJpaRepositoryCustomImpl;
import com.itmcs.roo.mirisk.dta.ResidualImpactAssessment;
import com.itmcs.roo.mirisk.dta.QResidualImpactAssessment;
import com.querydsl.core.types.Path;
import com.querydsl.jpa.JPQLQuery;
import io.springlets.data.domain.GlobalSearch;
import io.springlets.data.jpa.repository.support.QueryDslRepositorySupportExt.AttributeMappingBuilder;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

/**
 * = ResidualImpactAssessmentRepositoryImpl
 TODO Auto-generated class documentation
 *
 */
@RooJpaRepositoryCustomImpl(repository = ResidualImpactAssessmentRepositoryCustom.class)
@Transactional(readOnly = true)
public class ResidualImpactAssessmentRepositoryImpl extends QueryDslRepositorySupportExt<ResidualImpactAssessment> implements ResidualImpactAssessmentRepositoryCustom {

    /**
     * Default constructor
     */
    ResidualImpactAssessmentRepositoryImpl() {
        super(ResidualImpactAssessment.class);
    }

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    public static final String RESIDUAL_IMPACT_ASSESSMENT_NAME = "residualImpactAssessmentName";

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    public static final String RESIDUAL_IMPACT_ASSESSMENT_DESCRIPTION = "residualImpactAssessmentDescription";

    /**
     * TODO Auto-generated method documentation
     *
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public Page<ResidualImpactAssessment> findAll(GlobalSearch globalSearch, Pageable pageable) {
        QResidualImpactAssessment residualImpactAssessment = QResidualImpactAssessment.residualImpactAssessment;
        JPQLQuery<ResidualImpactAssessment> query = from(residualImpactAssessment);
        Path<?>[] paths = new Path<?>[] { residualImpactAssessment.residualImpactAssessmentName, residualImpactAssessment.residualImpactAssessmentDescription };
        applyGlobalSearch(globalSearch, query, paths);
        AttributeMappingBuilder mapping = buildMapper().map(RESIDUAL_IMPACT_ASSESSMENT_NAME, residualImpactAssessment.residualImpactAssessmentName).map(RESIDUAL_IMPACT_ASSESSMENT_DESCRIPTION, residualImpactAssessment.residualImpactAssessmentDescription);
        applyPagination(pageable, query, mapping);
        applyOrderById(query);
        return loadPage(query, pageable, residualImpactAssessment);
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
        QResidualImpactAssessment residualImpactAssessment = QResidualImpactAssessment.residualImpactAssessment;
        JPQLQuery<ResidualImpactAssessment> query = from(residualImpactAssessment);
        Path<?>[] paths = new Path<?>[] { residualImpactAssessment.residualImpactAssessmentName, residualImpactAssessment.residualImpactAssessmentDescription };
        applyGlobalSearch(globalSearch, query, paths);
        // Also, filter by the provided ids
        query.where(residualImpactAssessment.id.in(ids));
        AttributeMappingBuilder mapping = buildMapper().map(RESIDUAL_IMPACT_ASSESSMENT_NAME, residualImpactAssessment.residualImpactAssessmentName).map(RESIDUAL_IMPACT_ASSESSMENT_DESCRIPTION, residualImpactAssessment.residualImpactAssessmentDescription);
        applyPagination(pageable, query, mapping);
        applyOrderById(query);
        return loadPage(query, pageable, residualImpactAssessment);
    }
}
