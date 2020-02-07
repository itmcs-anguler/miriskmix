package com.itmcs.roo.mirisk.repository;
import io.springlets.data.jpa.repository.support.QueryDslRepositorySupportExt;
import org.springframework.roo.addon.layers.repository.jpa.annotations.RooJpaRepositoryCustomImpl;
import com.itmcs.roo.mirisk.dta.ManageRole;
import com.itmcs.roo.mirisk.dta.QManageRole;
import com.querydsl.core.types.Path;
import com.querydsl.jpa.JPQLQuery;
import io.springlets.data.domain.GlobalSearch;
import io.springlets.data.jpa.repository.support.QueryDslRepositorySupportExt.AttributeMappingBuilder;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

/**
 * = ManageRoleRepositoryImpl
 TODO Auto-generated class documentation
 *
 */
@RooJpaRepositoryCustomImpl(repository = ManageRoleRepositoryCustom.class)
@Transactional(readOnly = true)
public class ManageRoleRepositoryImpl extends QueryDslRepositorySupportExt<ManageRole> implements ManageRoleRepositoryCustom {

    /**
     * Default constructor
     */
    ManageRoleRepositoryImpl() {
        super(ManageRole.class);
    }

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    public static final String ROLE_NAME = "roleName";

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    public static final String ROLE_DESCRIPTION = "roleDescription";

    /**
     * TODO Auto-generated method documentation
     *
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public Page<ManageRole> findAll(GlobalSearch globalSearch, Pageable pageable) {
        QManageRole manageRole = QManageRole.manageRole;
        JPQLQuery<ManageRole> query = from(manageRole);
        Path<?>[] paths = new Path<?>[] { manageRole.roleName, manageRole.roleDescription };
        applyGlobalSearch(globalSearch, query, paths);
        AttributeMappingBuilder mapping = buildMapper().map(ROLE_NAME, manageRole.roleName).map(ROLE_DESCRIPTION, manageRole.roleDescription);
        applyPagination(pageable, query, mapping);
        applyOrderById(query);
        return loadPage(query, pageable, manageRole);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param ids
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public Page<ManageRole> findAllByIdsIn(List<Long> ids, GlobalSearch globalSearch, Pageable pageable) {
        QManageRole manageRole = QManageRole.manageRole;
        JPQLQuery<ManageRole> query = from(manageRole);
        Path<?>[] paths = new Path<?>[] { manageRole.roleName, manageRole.roleDescription };
        applyGlobalSearch(globalSearch, query, paths);
        // Also, filter by the provided ids
        query.where(manageRole.id.in(ids));
        AttributeMappingBuilder mapping = buildMapper().map(ROLE_NAME, manageRole.roleName).map(ROLE_DESCRIPTION, manageRole.roleDescription);
        applyPagination(pageable, query, mapping);
        applyOrderById(query);
        return loadPage(query, pageable, manageRole);
    }
}
