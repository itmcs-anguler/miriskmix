package com.itmcs.roo.mirisk.repository;
import io.springlets.data.jpa.repository.support.QueryDslRepositorySupportExt;
import org.springframework.roo.addon.layers.repository.jpa.annotations.RooJpaRepositoryCustomImpl;
import com.itmcs.roo.mirisk.dta.Recovery;
import com.itmcs.roo.mirisk.dta.Ccy;
import com.itmcs.roo.mirisk.dta.QRecovery;
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
 * = RecoveryRepositoryImpl
 TODO Auto-generated class documentation
 *
 */
@RooJpaRepositoryCustomImpl(repository = RecoveryRepositoryCustom.class)
@Transactional(readOnly = true)
public class RecoveryRepositoryImpl extends QueryDslRepositorySupportExt<Recovery> implements RecoveryRepositoryCustom {

    /**
     * Default constructor
     */
    RecoveryRepositoryImpl() {
        super(Recovery.class);
    }

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    public static final String AMOUNT = "amount";

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    public static final String DESCRIPTION = "description";

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    public static final String IMPACT_DATE = "impactDate";

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    public static final String CCY = "ccy";

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    public static final String UNIQUE_ID = "uniqueId";

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    public static final String GL_REFERENCE = "glReference";

    /**
     * TODO Auto-generated method documentation
     *
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public Page<Recovery> findAll(GlobalSearch globalSearch, Pageable pageable) {
        QRecovery recovery = QRecovery.recovery;
        JPQLQuery<Recovery> query = from(recovery);
        Path<?>[] paths = new Path<?>[] { recovery.amount, recovery.glReference, recovery.impactDate, recovery.description, recovery.ccy, recovery.uniqueId };
        applyGlobalSearch(globalSearch, query, paths);
        AttributeMappingBuilder mapping = buildMapper().map(AMOUNT, recovery.amount).map(GL_REFERENCE, recovery.glReference).map(IMPACT_DATE, recovery.impactDate).map(DESCRIPTION, recovery.description).map(CCY, recovery.ccy).map(UNIQUE_ID, recovery.uniqueId);
        applyPagination(pageable, query, mapping);
        applyOrderById(query);
        return loadPage(query, pageable, recovery);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param ids
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public Page<Recovery> findAllByIdsIn(List<Long> ids, GlobalSearch globalSearch, Pageable pageable) {
        QRecovery recovery = QRecovery.recovery;
        JPQLQuery<Recovery> query = from(recovery);
        Path<?>[] paths = new Path<?>[] { recovery.amount, recovery.glReference, recovery.impactDate, recovery.description, recovery.ccy, recovery.uniqueId };
        applyGlobalSearch(globalSearch, query, paths);
        // Also, filter by the provided ids
        query.where(recovery.id.in(ids));
        AttributeMappingBuilder mapping = buildMapper().map(AMOUNT, recovery.amount).map(GL_REFERENCE, recovery.glReference).map(IMPACT_DATE, recovery.impactDate).map(DESCRIPTION, recovery.description).map(CCY, recovery.ccy).map(UNIQUE_ID, recovery.uniqueId);
        applyPagination(pageable, query, mapping);
        applyOrderById(query);
        return loadPage(query, pageable, recovery);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param ccy
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public Page<Recovery> findByCcy(Ccy ccy, GlobalSearch globalSearch, Pageable pageable) {
        QRecovery recovery = QRecovery.recovery;
        JPQLQuery<Recovery> query = from(recovery);
        Assert.notNull(ccy, "ccy is required");
        query.where(recovery.ccy.eq(ccy));
        Path<?>[] paths = new Path<?>[] { recovery.amount, recovery.glReference, recovery.impactDate, recovery.description, recovery.ccy, recovery.uniqueId };
        applyGlobalSearch(globalSearch, query, paths);
        AttributeMappingBuilder mapping = buildMapper().map(AMOUNT, recovery.amount).map(GL_REFERENCE, recovery.glReference).map(IMPACT_DATE, recovery.impactDate).map(DESCRIPTION, recovery.description).map(CCY, recovery.ccy).map(UNIQUE_ID, recovery.uniqueId);
        applyPagination(pageable, query, mapping);
        applyOrderById(query);
        return loadPage(query, pageable, recovery);
    }
}
