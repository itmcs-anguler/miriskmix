package com.itmcs.roo.mirisk.repository;
import io.springlets.data.jpa.repository.support.QueryDslRepositorySupportExt;
import org.springframework.roo.addon.layers.repository.jpa.annotations.RooJpaRepositoryCustomImpl;
import com.itmcs.roo.mirisk.dta.RiskStatus;
import com.itmcs.roo.mirisk.dta.QRiskStatus;
import com.querydsl.core.types.Path;
import com.querydsl.jpa.JPQLQuery;
import io.springlets.data.domain.GlobalSearch;
import io.springlets.data.jpa.repository.support.QueryDslRepositorySupportExt.AttributeMappingBuilder;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

/**
 * = RiskStatusRepositoryImpl
 TODO Auto-generated class documentation
 *
 */
@RooJpaRepositoryCustomImpl(repository = RiskStatusRepositoryCustom.class)
@Transactional(readOnly = true)
public class RiskStatusRepositoryImpl extends QueryDslRepositorySupportExt<RiskStatus> implements RiskStatusRepositoryCustom {

    /**
     * Default constructor
     */
    RiskStatusRepositoryImpl() {
        super(RiskStatus.class);
    }

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    public static final String RISK_STATUS_DESCRIPTION = "riskStatusDescription";

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    public static final String RISK_STATUS_NAME = "riskStatusName";

    /**
     * TODO Auto-generated method documentation
     *
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public Page<RiskStatus> findAll(GlobalSearch globalSearch, Pageable pageable) {
        QRiskStatus riskStatus = QRiskStatus.riskStatus;
        JPQLQuery<RiskStatus> query = from(riskStatus);
        Path<?>[] paths = new Path<?>[] { riskStatus.riskStatusName, riskStatus.riskStatusDescription };
        applyGlobalSearch(globalSearch, query, paths);
        AttributeMappingBuilder mapping = buildMapper().map(RISK_STATUS_NAME, riskStatus.riskStatusName).map(RISK_STATUS_DESCRIPTION, riskStatus.riskStatusDescription);
        applyPagination(pageable, query, mapping);
        applyOrderById(query);
        return loadPage(query, pageable, riskStatus);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param ids
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public Page<RiskStatus> findAllByIdsIn(List<Long> ids, GlobalSearch globalSearch, Pageable pageable) {
        QRiskStatus riskStatus = QRiskStatus.riskStatus;
        JPQLQuery<RiskStatus> query = from(riskStatus);
        Path<?>[] paths = new Path<?>[] { riskStatus.riskStatusName, riskStatus.riskStatusDescription };
        applyGlobalSearch(globalSearch, query, paths);
        // Also, filter by the provided ids
        query.where(riskStatus.id.in(ids));
        AttributeMappingBuilder mapping = buildMapper().map(RISK_STATUS_NAME, riskStatus.riskStatusName).map(RISK_STATUS_DESCRIPTION, riskStatus.riskStatusDescription);
        applyPagination(pageable, query, mapping);
        applyOrderById(query);
        return loadPage(query, pageable, riskStatus);
    }
}
