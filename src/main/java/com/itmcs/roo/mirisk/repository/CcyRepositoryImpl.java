package com.itmcs.roo.mirisk.repository;
import io.springlets.data.jpa.repository.support.QueryDslRepositorySupportExt;
import org.springframework.roo.addon.layers.repository.jpa.annotations.RooJpaRepositoryCustomImpl;
import com.itmcs.roo.mirisk.dta.Ccy;
import com.itmcs.roo.mirisk.dta.QCcy;
import com.querydsl.core.types.Path;
import com.querydsl.jpa.JPQLQuery;
import io.springlets.data.domain.GlobalSearch;
import io.springlets.data.jpa.repository.support.QueryDslRepositorySupportExt.AttributeMappingBuilder;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

/**
 * = CcyRepositoryImpl
 TODO Auto-generated class documentation
 *
 */
@RooJpaRepositoryCustomImpl(repository = CcyRepositoryCustom.class)
@Transactional(readOnly = true)
public class CcyRepositoryImpl extends QueryDslRepositorySupportExt<Ccy> implements CcyRepositoryCustom {

    /**
     * Default constructor
     */
    CcyRepositoryImpl() {
        super(Ccy.class);
    }

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    public static final String CCY_DESCRIPTION = "ccyDescription";

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    public static final String CCY_NAME = "ccyName";

    /**
     * TODO Auto-generated method documentation
     *
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public Page<Ccy> findAll(GlobalSearch globalSearch, Pageable pageable) {
        QCcy ccy = QCcy.ccy;
        JPQLQuery<Ccy> query = from(ccy);
        Path<?>[] paths = new Path<?>[] { ccy.ccyName, ccy.ccyDescription };
        applyGlobalSearch(globalSearch, query, paths);
        AttributeMappingBuilder mapping = buildMapper().map(CCY_NAME, ccy.ccyName).map(CCY_DESCRIPTION, ccy.ccyDescription);
        applyPagination(pageable, query, mapping);
        applyOrderById(query);
        return loadPage(query, pageable, ccy);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param ids
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public Page<Ccy> findAllByIdsIn(List<Long> ids, GlobalSearch globalSearch, Pageable pageable) {
        QCcy ccy = QCcy.ccy;
        JPQLQuery<Ccy> query = from(ccy);
        Path<?>[] paths = new Path<?>[] { ccy.ccyName, ccy.ccyDescription };
        applyGlobalSearch(globalSearch, query, paths);
        // Also, filter by the provided ids
        query.where(ccy.id.in(ids));
        AttributeMappingBuilder mapping = buildMapper().map(CCY_NAME, ccy.ccyName).map(CCY_DESCRIPTION, ccy.ccyDescription);
        applyPagination(pageable, query, mapping);
        applyOrderById(query);
        return loadPage(query, pageable, ccy);
    }
}
