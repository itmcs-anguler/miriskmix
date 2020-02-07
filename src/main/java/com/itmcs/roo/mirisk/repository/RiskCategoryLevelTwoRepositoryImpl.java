package com.itmcs.roo.mirisk.repository;
import io.springlets.data.jpa.repository.support.QueryDslRepositorySupportExt;
import org.springframework.roo.addon.layers.repository.jpa.annotations.RooJpaRepositoryCustomImpl;
import com.itmcs.roo.mirisk.dta.RiskCategoryLevelTwo;
import com.itmcs.roo.mirisk.dta.QRiskCategoryLevelTwo;
import com.querydsl.core.types.Path;
import com.querydsl.jpa.JPQLQuery;
import io.springlets.data.domain.GlobalSearch;
import io.springlets.data.jpa.repository.support.QueryDslRepositorySupportExt.AttributeMappingBuilder;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

/**
 * = RiskCategoryLevelTwoRepositoryImpl
 TODO Auto-generated class documentation
 *
 */
@RooJpaRepositoryCustomImpl(repository = RiskCategoryLevelTwoRepositoryCustom.class)
@Transactional(readOnly = true)
public class RiskCategoryLevelTwoRepositoryImpl extends QueryDslRepositorySupportExt<RiskCategoryLevelTwo> implements RiskCategoryLevelTwoRepositoryCustom {

    /**
     * Default constructor
     */
    RiskCategoryLevelTwoRepositoryImpl() {
        super(RiskCategoryLevelTwo.class);
    }

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    public static final String RISK_CATEGORY_LEVEL_TWO_NAME = "riskCategoryLevelTwoName";

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    public static final String RISK_CATEGORY_LEVEL_TWO_DESCRIPTION = "riskCategoryLevelTwoDescription";

    /**
     * TODO Auto-generated method documentation
     *
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public Page<RiskCategoryLevelTwo> findAll(GlobalSearch globalSearch, Pageable pageable) {
        QRiskCategoryLevelTwo riskCategoryLevelTwo = QRiskCategoryLevelTwo.riskCategoryLevelTwo;
        JPQLQuery<RiskCategoryLevelTwo> query = from(riskCategoryLevelTwo);
        Path<?>[] paths = new Path<?>[] { riskCategoryLevelTwo.riskCategoryLevelTwoName, riskCategoryLevelTwo.riskCategoryLevelTwoDescription };
        applyGlobalSearch(globalSearch, query, paths);
        AttributeMappingBuilder mapping = buildMapper().map(RISK_CATEGORY_LEVEL_TWO_NAME, riskCategoryLevelTwo.riskCategoryLevelTwoName).map(RISK_CATEGORY_LEVEL_TWO_DESCRIPTION, riskCategoryLevelTwo.riskCategoryLevelTwoDescription);
        applyPagination(pageable, query, mapping);
        applyOrderById(query);
        return loadPage(query, pageable, riskCategoryLevelTwo);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param ids
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public Page<RiskCategoryLevelTwo> findAllByIdsIn(List<Long> ids, GlobalSearch globalSearch, Pageable pageable) {
        QRiskCategoryLevelTwo riskCategoryLevelTwo = QRiskCategoryLevelTwo.riskCategoryLevelTwo;
        JPQLQuery<RiskCategoryLevelTwo> query = from(riskCategoryLevelTwo);
        Path<?>[] paths = new Path<?>[] { riskCategoryLevelTwo.riskCategoryLevelTwoName, riskCategoryLevelTwo.riskCategoryLevelTwoDescription };
        applyGlobalSearch(globalSearch, query, paths);
        // Also, filter by the provided ids
        query.where(riskCategoryLevelTwo.id.in(ids));
        AttributeMappingBuilder mapping = buildMapper().map(RISK_CATEGORY_LEVEL_TWO_NAME, riskCategoryLevelTwo.riskCategoryLevelTwoName).map(RISK_CATEGORY_LEVEL_TWO_DESCRIPTION, riskCategoryLevelTwo.riskCategoryLevelTwoDescription);
        applyPagination(pageable, query, mapping);
        applyOrderById(query);
        return loadPage(query, pageable, riskCategoryLevelTwo);
    }
}
