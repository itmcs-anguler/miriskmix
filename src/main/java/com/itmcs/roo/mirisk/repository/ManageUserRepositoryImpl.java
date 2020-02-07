package com.itmcs.roo.mirisk.repository;
import io.springlets.data.jpa.repository.support.QueryDslRepositorySupportExt;
import org.springframework.roo.addon.layers.repository.jpa.annotations.RooJpaRepositoryCustomImpl;
import com.itmcs.roo.mirisk.dta.ManageUser;
import com.itmcs.roo.mirisk.dta.ManageRole;
import com.itmcs.roo.mirisk.dta.QManageUser;
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
 * = ManageUserRepositoryImpl
 TODO Auto-generated class documentation
 *
 */
@RooJpaRepositoryCustomImpl(repository = ManageUserRepositoryCustom.class)
@Transactional(readOnly = true)
public class ManageUserRepositoryImpl extends QueryDslRepositorySupportExt<ManageUser> implements ManageUserRepositoryCustom {

    /**
     * Default constructor
     */
    ManageUserRepositoryImpl() {
        super(ManageUser.class);
    }

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    public static final String PASS_WORD = "passWord";

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    public static final String FULL_NAME = "fullName";

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    public static final String USER_NAME = "userName";

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    public static final String MANAGEROLES = "manageroles";

    /**
     * TODO Auto-generated method documentation
     *
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public Page<ManageUser> findAll(GlobalSearch globalSearch, Pageable pageable) {
        QManageUser manageUser = QManageUser.manageUser;
        JPQLQuery<ManageUser> query = from(manageUser);
        Path<?>[] paths = new Path<?>[] { manageUser.userName, manageUser.fullName, manageUser.passWord, manageUser.manageroles };
        applyGlobalSearch(globalSearch, query, paths);
        AttributeMappingBuilder mapping = buildMapper().map(USER_NAME, manageUser.userName).map(FULL_NAME, manageUser.fullName).map(PASS_WORD, manageUser.passWord).map(MANAGEROLES, manageUser.manageroles);
        applyPagination(pageable, query, mapping);
        applyOrderById(query);
        return loadPage(query, pageable, manageUser);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param ids
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public Page<ManageUser> findAllByIdsIn(List<Long> ids, GlobalSearch globalSearch, Pageable pageable) {
        QManageUser manageUser = QManageUser.manageUser;
        JPQLQuery<ManageUser> query = from(manageUser);
        Path<?>[] paths = new Path<?>[] { manageUser.userName, manageUser.fullName, manageUser.passWord, manageUser.manageroles };
        applyGlobalSearch(globalSearch, query, paths);
        // Also, filter by the provided ids
        query.where(manageUser.id.in(ids));
        AttributeMappingBuilder mapping = buildMapper().map(USER_NAME, manageUser.userName).map(FULL_NAME, manageUser.fullName).map(PASS_WORD, manageUser.passWord).map(MANAGEROLES, manageUser.manageroles);
        applyPagination(pageable, query, mapping);
        applyOrderById(query);
        return loadPage(query, pageable, manageUser);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param manageroles
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public Page<ManageUser> findByManageroles(ManageRole manageroles, GlobalSearch globalSearch, Pageable pageable) {
        QManageUser manageUser = QManageUser.manageUser;
        JPQLQuery<ManageUser> query = from(manageUser);
        Assert.notNull(manageroles, "manageroles is required");
        query.where(manageUser.manageroles.eq(manageroles));
        Path<?>[] paths = new Path<?>[] { manageUser.userName, manageUser.fullName, manageUser.passWord, manageUser.manageroles };
        applyGlobalSearch(globalSearch, query, paths);
        AttributeMappingBuilder mapping = buildMapper().map(USER_NAME, manageUser.userName).map(FULL_NAME, manageUser.fullName).map(PASS_WORD, manageUser.passWord).map(MANAGEROLES, manageUser.manageroles);
        applyPagination(pageable, query, mapping);
        applyOrderById(query);
        return loadPage(query, pageable, manageUser);
    }
}
