package com.itmcs.roo.mirisk.service.api;
import com.itmcs.roo.mirisk.dta.ManageRole;
import io.springlets.data.web.validation.ValidatorService;
import io.springlets.format.EntityResolver;
import org.springframework.roo.addon.layers.service.annotations.RooService;
import io.springlets.data.domain.GlobalSearch;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * = ManageRoleService
 TODO Auto-generated class documentation
 *
 */
@RooService(entity = ManageRole.class)
public interface ManageRoleService extends EntityResolver<ManageRole, Long>, ValidatorService<ManageRole> {

    /**
     * TODO Auto-generated method documentation
     *
     * @param id
     * @return ManageRole
     */
    public abstract ManageRole findOne(Long id);

    /**
     * TODO Auto-generated method documentation
     *
     * @param manageRole
     */
    public abstract void delete(ManageRole manageRole);

    /**
     * TODO Auto-generated method documentation
     *
     * @param entities
     * @return List
     */
    public abstract List<ManageRole> save(Iterable<ManageRole> entities);

    /**
     * TODO Auto-generated method documentation
     *
     * @param ids
     */
    public abstract void delete(Iterable<Long> ids);

    /**
     * TODO Auto-generated method documentation
     *
     * @param entity
     * @return ManageRole
     */
    public abstract ManageRole save(ManageRole entity);

    /**
     * TODO Auto-generated method documentation
     *
     * @param id
     * @return ManageRole
     */
    public abstract ManageRole findOneForUpdate(Long id);

    /**
     * TODO Auto-generated method documentation
     *
     * @param ids
     * @return List
     */
    public abstract List<ManageRole> findAll(Iterable<Long> ids);

    /**
     * TODO Auto-generated method documentation
     *
     * @return List
     */
    public abstract List<ManageRole> findAll();

    /**
     * TODO Auto-generated method documentation
     *
     * @return Long
     */
    public abstract long count();

    /**
     * TODO Auto-generated method documentation
     *
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public abstract Page<ManageRole> findAll(GlobalSearch globalSearch, Pageable pageable);

    /**
     * TODO Auto-generated method documentation
     *
     * @param ids
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public abstract Page<ManageRole> findAllByIdsIn(List<Long> ids, GlobalSearch globalSearch, Pageable pageable);

    /**
     * TODO Auto-generated method documentation
     *
     * @param manageRole
     * @param manageUsersToAdd
     * @return ManageRole
     */
    public abstract ManageRole addToManageUsers(ManageRole manageRole, Iterable<Long> manageUsersToAdd);

    /**
     * TODO Auto-generated method documentation
     *
     * @param manageRole
     * @param manageUsersToRemove
     * @return ManageRole
     */
    public abstract ManageRole removeFromManageUsers(ManageRole manageRole, Iterable<Long> manageUsersToRemove);

    /**
     * TODO Auto-generated method documentation
     *
     * @param manageRole
     * @param manageUsers
     * @return ManageRole
     */
    public abstract ManageRole setManageUsers(ManageRole manageRole, Iterable<Long> manageUsers);

    /**
     * TODO Auto-generated method documentation
     *
     * @param roleName
     * @param pageable
     * @return Page
     */
    public abstract Page<ManageRole> findByRoleName(String roleName, Pageable pageable);

    /**
     * TODO Auto-generated method documentation
     *
     * @param roleName
     * @return Long
     */
    public abstract long countByRoleName(String roleName);
}
