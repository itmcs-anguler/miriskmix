package com.itmcs.roo.mirisk.repository;
import io.springlets.data.jpa.repository.support.QueryDslRepositorySupportExt;
import org.springframework.roo.addon.layers.repository.jpa.annotations.RooJpaRepositoryCustomImpl;
import com.itmcs.roo.mirisk.dta.ManagePage;
import com.itmcs.roo.mirisk.dta.QManagePage;
import com.querydsl.core.types.Path;
import com.querydsl.jpa.JPQLQuery;
import io.springlets.data.domain.GlobalSearch;
import io.springlets.data.jpa.repository.support.QueryDslRepositorySupportExt.AttributeMappingBuilder;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

/**
 * = ManagePageRepositoryImpl
 TODO Auto-generated class documentation
 *
 */
@RooJpaRepositoryCustomImpl(repository = ManagePageRepositoryCustom.class)
@Transactional(readOnly = true)
public class ManagePageRepositoryImpl extends QueryDslRepositorySupportExt<ManagePage> implements ManagePageRepositoryCustom {

    /**
     * Default constructor
     */
    ManagePageRepositoryImpl() {
        super(ManagePage.class);
    }

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    public static final String PAGE_NAME = "pageName";

    /**
     * TODO Auto-generated method documentation
     *
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public Page<ManagePage> findAll(GlobalSearch globalSearch, Pageable pageable) {
        QManagePage managePage = QManagePage.managePage;
        JPQLQuery<ManagePage> query = from(managePage);
        Path<?>[] paths = new Path<?>[] { managePage.pageName };
        applyGlobalSearch(globalSearch, query, paths);
        AttributeMappingBuilder mapping = buildMapper().map(PAGE_NAME, managePage.pageName);
        applyPagination(pageable, query, mapping);
        applyOrderById(query);
        return loadPage(query, pageable, managePage);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param ids
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public Page<ManagePage> findAllByIdsIn(List<Long> ids, GlobalSearch globalSearch, Pageable pageable) {
        QManagePage managePage = QManagePage.managePage;
        JPQLQuery<ManagePage> query = from(managePage);
        Path<?>[] paths = new Path<?>[] { managePage.pageName };
        applyGlobalSearch(globalSearch, query, paths);
        // Also, filter by the provided ids
        query.where(managePage.id.in(ids));
        AttributeMappingBuilder mapping = buildMapper().map(PAGE_NAME, managePage.pageName);
        applyPagination(pageable, query, mapping);
        applyOrderById(query);
        return loadPage(query, pageable, managePage);
    }
}
