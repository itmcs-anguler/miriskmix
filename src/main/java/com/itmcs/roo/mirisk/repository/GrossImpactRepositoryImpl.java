package com.itmcs.roo.mirisk.repository;
import io.springlets.data.jpa.repository.support.QueryDslRepositorySupportExt;
import org.springframework.roo.addon.layers.repository.jpa.annotations.RooJpaRepositoryCustomImpl;
import com.itmcs.roo.mirisk.dta.GrossImpact;
import com.itmcs.roo.mirisk.dta.Ccy;
import com.itmcs.roo.mirisk.dta.QGrossImpact;
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
 * = GrossImpactRepositoryImpl
 TODO Auto-generated class documentation
 *
 */
@RooJpaRepositoryCustomImpl(repository = GrossImpactRepositoryCustom.class)
@Transactional(readOnly = true)
public class GrossImpactRepositoryImpl extends QueryDslRepositorySupportExt<GrossImpact> implements GrossImpactRepositoryCustom {

    /**
     * Default constructor
     */
    GrossImpactRepositoryImpl() {
        super(GrossImpact.class);
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
    public Page<GrossImpact> findAll(GlobalSearch globalSearch, Pageable pageable) {
        QGrossImpact grossImpact = QGrossImpact.grossImpact;
        JPQLQuery<GrossImpact> query = from(grossImpact);
        Path<?>[] paths = new Path<?>[] { grossImpact.amount, grossImpact.glReference, grossImpact.impactDate, grossImpact.description, grossImpact.ccy, grossImpact.uniqueId };
        applyGlobalSearch(globalSearch, query, paths);
        AttributeMappingBuilder mapping = buildMapper().map(AMOUNT, grossImpact.amount).map(GL_REFERENCE, grossImpact.glReference).map(IMPACT_DATE, grossImpact.impactDate).map(DESCRIPTION, grossImpact.description).map(CCY, grossImpact.ccy).map(UNIQUE_ID, grossImpact.uniqueId);
        applyPagination(pageable, query, mapping);
        applyOrderById(query);
        return loadPage(query, pageable, grossImpact);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param ids
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public Page<GrossImpact> findAllByIdsIn(List<Long> ids, GlobalSearch globalSearch, Pageable pageable) {
        QGrossImpact grossImpact = QGrossImpact.grossImpact;
        JPQLQuery<GrossImpact> query = from(grossImpact);
        Path<?>[] paths = new Path<?>[] { grossImpact.amount, grossImpact.glReference, grossImpact.impactDate, grossImpact.description, grossImpact.ccy, grossImpact.uniqueId };
        applyGlobalSearch(globalSearch, query, paths);
        // Also, filter by the provided ids
        query.where(grossImpact.id.in(ids));
        AttributeMappingBuilder mapping = buildMapper().map(AMOUNT, grossImpact.amount).map(GL_REFERENCE, grossImpact.glReference).map(IMPACT_DATE, grossImpact.impactDate).map(DESCRIPTION, grossImpact.description).map(CCY, grossImpact.ccy).map(UNIQUE_ID, grossImpact.uniqueId);
        applyPagination(pageable, query, mapping);
        applyOrderById(query);
        return loadPage(query, pageable, grossImpact);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param ccy
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public Page<GrossImpact> findByCcy(Ccy ccy, GlobalSearch globalSearch, Pageable pageable) {
        QGrossImpact grossImpact = QGrossImpact.grossImpact;
        JPQLQuery<GrossImpact> query = from(grossImpact);
        Assert.notNull(ccy, "ccy is required");
        query.where(grossImpact.ccy.eq(ccy));
        Path<?>[] paths = new Path<?>[] { grossImpact.amount, grossImpact.glReference, grossImpact.impactDate, grossImpact.description, grossImpact.ccy, grossImpact.uniqueId };
        applyGlobalSearch(globalSearch, query, paths);
        AttributeMappingBuilder mapping = buildMapper().map(AMOUNT, grossImpact.amount).map(GL_REFERENCE, grossImpact.glReference).map(IMPACT_DATE, grossImpact.impactDate).map(DESCRIPTION, grossImpact.description).map(CCY, grossImpact.ccy).map(UNIQUE_ID, grossImpact.uniqueId);
        applyPagination(pageable, query, mapping);
        applyOrderById(query);
        return loadPage(query, pageable, grossImpact);
    }
}
