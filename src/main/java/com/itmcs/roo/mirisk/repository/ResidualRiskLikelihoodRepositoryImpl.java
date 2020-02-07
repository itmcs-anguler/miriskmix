package com.itmcs.roo.mirisk.repository;
import io.springlets.data.jpa.repository.support.QueryDslRepositorySupportExt;
import org.springframework.roo.addon.layers.repository.jpa.annotations.RooJpaRepositoryCustomImpl;
import com.itmcs.roo.mirisk.dta.ResidualRiskLikelihood;
import com.itmcs.roo.mirisk.dta.QResidualRiskLikelihood;
import com.querydsl.core.types.Path;
import com.querydsl.jpa.JPQLQuery;
import io.springlets.data.domain.GlobalSearch;
import io.springlets.data.jpa.repository.support.QueryDslRepositorySupportExt.AttributeMappingBuilder;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

/**
 * = ResidualRiskLikelihoodRepositoryImpl
 TODO Auto-generated class documentation
 *
 */
@RooJpaRepositoryCustomImpl(repository = ResidualRiskLikelihoodRepositoryCustom.class)
@Transactional(readOnly = true)
public class ResidualRiskLikelihoodRepositoryImpl extends QueryDslRepositorySupportExt<ResidualRiskLikelihood> implements ResidualRiskLikelihoodRepositoryCustom {

    /**
     * Default constructor
     */
    ResidualRiskLikelihoodRepositoryImpl() {
        super(ResidualRiskLikelihood.class);
    }

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    public static final String RESIDUAL_RISK_LIKELIHOOD_DESCRIPTION = "residualRiskLikelihoodDescription";

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    public static final String RESIDUAL_RISK_LIKELIHOOD_NAME = "residualRiskLikelihoodName";

    /**
     * TODO Auto-generated method documentation
     *
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public Page<ResidualRiskLikelihood> findAll(GlobalSearch globalSearch, Pageable pageable) {
        QResidualRiskLikelihood residualRiskLikelihood = QResidualRiskLikelihood.residualRiskLikelihood;
        JPQLQuery<ResidualRiskLikelihood> query = from(residualRiskLikelihood);
        Path<?>[] paths = new Path<?>[] { residualRiskLikelihood.residualRiskLikelihoodName, residualRiskLikelihood.residualRiskLikelihoodDescription };
        applyGlobalSearch(globalSearch, query, paths);
        AttributeMappingBuilder mapping = buildMapper().map(RESIDUAL_RISK_LIKELIHOOD_NAME, residualRiskLikelihood.residualRiskLikelihoodName).map(RESIDUAL_RISK_LIKELIHOOD_DESCRIPTION, residualRiskLikelihood.residualRiskLikelihoodDescription);
        applyPagination(pageable, query, mapping);
        applyOrderById(query);
        return loadPage(query, pageable, residualRiskLikelihood);
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
        QResidualRiskLikelihood residualRiskLikelihood = QResidualRiskLikelihood.residualRiskLikelihood;
        JPQLQuery<ResidualRiskLikelihood> query = from(residualRiskLikelihood);
        Path<?>[] paths = new Path<?>[] { residualRiskLikelihood.residualRiskLikelihoodName, residualRiskLikelihood.residualRiskLikelihoodDescription };
        applyGlobalSearch(globalSearch, query, paths);
        // Also, filter by the provided ids
        query.where(residualRiskLikelihood.id.in(ids));
        AttributeMappingBuilder mapping = buildMapper().map(RESIDUAL_RISK_LIKELIHOOD_NAME, residualRiskLikelihood.residualRiskLikelihoodName).map(RESIDUAL_RISK_LIKELIHOOD_DESCRIPTION, residualRiskLikelihood.residualRiskLikelihoodDescription);
        applyPagination(pageable, query, mapping);
        applyOrderById(query);
        return loadPage(query, pageable, residualRiskLikelihood);
    }
}
