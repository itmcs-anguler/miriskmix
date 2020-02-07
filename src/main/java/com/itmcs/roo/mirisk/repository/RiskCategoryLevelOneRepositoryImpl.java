package com.itmcs.roo.mirisk.repository;
import io.springlets.data.jpa.repository.support.QueryDslRepositorySupportExt;
import org.springframework.roo.addon.layers.repository.jpa.annotations.RooJpaRepositoryCustomImpl;
import com.itmcs.roo.mirisk.dta.RiskCategoryLevelOne;
import com.itmcs.roo.mirisk.dta.QRiskCategoryLevelOne;
import com.querydsl.core.types.Path;
import com.querydsl.jpa.JPQLQuery;
import io.springlets.data.domain.GlobalSearch;
import io.springlets.data.jpa.repository.support.QueryDslRepositorySupportExt.AttributeMappingBuilder;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

/**
 * = RiskCategoryLevelOneRepositoryImpl
 TODO Auto-generated class documentation
 *
 */
@RooJpaRepositoryCustomImpl(repository = RiskCategoryLevelOneRepositoryCustom.class)
@Transactional(readOnly = true)
public class RiskCategoryLevelOneRepositoryImpl extends QueryDslRepositorySupportExt<RiskCategoryLevelOne> implements RiskCategoryLevelOneRepositoryCustom {

    /**
     * Default constructor
     */
    RiskCategoryLevelOneRepositoryImpl() {
        super(RiskCategoryLevelOne.class);
    }

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    public static final String RISK_CATEGORY_LEVEL_ONE_NAME = "riskCategoryLevelOneName";

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    public static final String RISK_CATEGORY_LEVEL_ONE_DESCRIPTION = "riskCategoryLevelOneDescription";

    /**
     * TODO Auto-generated method documentation
     *
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public Page<RiskCategoryLevelOne> findAll(GlobalSearch globalSearch, Pageable pageable) {
        QRiskCategoryLevelOne riskCategoryLevelOne = QRiskCategoryLevelOne.riskCategoryLevelOne;
        JPQLQuery<RiskCategoryLevelOne> query = from(riskCategoryLevelOne);
        Path<?>[] paths = new Path<?>[] { riskCategoryLevelOne.riskCategoryLevelOneName, riskCategoryLevelOne.riskCategoryLevelOneDescription };
        applyGlobalSearch(globalSearch, query, paths);
        AttributeMappingBuilder mapping = buildMapper().map(RISK_CATEGORY_LEVEL_ONE_NAME, riskCategoryLevelOne.riskCategoryLevelOneName).map(RISK_CATEGORY_LEVEL_ONE_DESCRIPTION, riskCategoryLevelOne.riskCategoryLevelOneDescription);
        applyPagination(pageable, query, mapping);
        applyOrderById(query);
        return loadPage(query, pageable, riskCategoryLevelOne);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param ids
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public Page<RiskCategoryLevelOne> findAllByIdsIn(List<Long> ids, GlobalSearch globalSearch, Pageable pageable) {
        QRiskCategoryLevelOne riskCategoryLevelOne = QRiskCategoryLevelOne.riskCategoryLevelOne;
        JPQLQuery<RiskCategoryLevelOne> query = from(riskCategoryLevelOne);
        Path<?>[] paths = new Path<?>[] { riskCategoryLevelOne.riskCategoryLevelOneName, riskCategoryLevelOne.riskCategoryLevelOneDescription };
        applyGlobalSearch(globalSearch, query, paths);
        // Also, filter by the provided ids
        query.where(riskCategoryLevelOne.id.in(ids));
        AttributeMappingBuilder mapping = buildMapper().map(RISK_CATEGORY_LEVEL_ONE_NAME, riskCategoryLevelOne.riskCategoryLevelOneName).map(RISK_CATEGORY_LEVEL_ONE_DESCRIPTION, riskCategoryLevelOne.riskCategoryLevelOneDescription);
        applyPagination(pageable, query, mapping);
        applyOrderById(query);
        return loadPage(query, pageable, riskCategoryLevelOne);
    }
}
