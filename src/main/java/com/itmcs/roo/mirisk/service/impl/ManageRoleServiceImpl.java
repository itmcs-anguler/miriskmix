package com.itmcs.roo.mirisk.service.impl;
import com.itmcs.roo.mirisk.service.api.ManageRoleService;
import org.springframework.roo.addon.layers.service.annotations.RooServiceImpl;
import com.itmcs.roo.mirisk.dta.ManageRole;
import com.itmcs.roo.mirisk.dta.ManageUser;
import com.itmcs.roo.mirisk.repository.ManageRoleRepository;
import com.itmcs.roo.mirisk.service.api.ManageUserService;
import io.springlets.data.domain.GlobalSearch;
import io.springlets.data.web.validation.MessageI18n;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * = ManageRoleServiceImpl
 TODO Auto-generated class documentation
 *
 */
@RooServiceImpl(service = ManageRoleService.class)
@Service
@Transactional(readOnly = true)
public class ManageRoleServiceImpl implements ManageRoleService {

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private ManageRoleRepository manageRoleRepository;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private ManageUserService manageUserService;

    /**
     * TODO Auto-generated constructor documentation
     *
     * @param manageRoleRepository
     * @param manageUserService
     */
    @Autowired
    public ManageRoleServiceImpl(ManageRoleRepository manageRoleRepository, @Lazy ManageUserService manageUserService) {
        setManageRoleRepository(manageRoleRepository);
        setManageUserService(manageUserService);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return ManageRoleRepository
     */
    public ManageRoleRepository getManageRoleRepository() {
        return manageRoleRepository;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param manageRoleRepository
     */
    public void setManageRoleRepository(ManageRoleRepository manageRoleRepository) {
        this.manageRoleRepository = manageRoleRepository;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return ManageUserService
     */
    public ManageUserService getManageUserService() {
        return manageUserService;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param manageUserService
     */
    public void setManageUserService(ManageUserService manageUserService) {
        this.manageUserService = manageUserService;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param managerole
     * @return Map
     */
    public Map<String, List<MessageI18n>> validate(ManageRole managerole) {
        Map<String, List<MessageI18n>> messages = new java.util.HashMap<String, List<MessageI18n>>();
        // TODO: IMPLEMENT HERE THE VALIDATION OF YOUR ENTITY
        return messages;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param manageRole
     * @param manageUsersToAdd
     * @return ManageRole
     */
    @Transactional
    public ManageRole addToManageUsers(ManageRole manageRole, Iterable<Long> manageUsersToAdd) {
        List<ManageUser> manageUsers = getManageUserService().findAll(manageUsersToAdd);
        manageRole.addToManageUsers(manageUsers);
        return getManageRoleRepository().save(manageRole);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param manageRole
     * @param manageUsersToRemove
     * @return ManageRole
     */
    @Transactional
    public ManageRole removeFromManageUsers(ManageRole manageRole, Iterable<Long> manageUsersToRemove) {
        List<ManageUser> manageUsers = getManageUserService().findAll(manageUsersToRemove);
        manageRole.removeFromManageUsers(manageUsers);
        return getManageRoleRepository().save(manageRole);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param manageRole
     * @param manageUsers
     * @return ManageRole
     */
    @Transactional
    public ManageRole setManageUsers(ManageRole manageRole, Iterable<Long> manageUsers) {
        List<ManageUser> items = getManageUserService().findAll(manageUsers);
        Set<ManageUser> currents = manageRole.getManageUsers();
        Set<ManageUser> toRemove = new HashSet<ManageUser>();
        for (Iterator<ManageUser> iterator = currents.iterator(); iterator.hasNext(); ) {
            ManageUser nextManageUser = iterator.next();
            if (items.contains(nextManageUser)) {
                items.remove(nextManageUser);
            } else {
                toRemove.add(nextManageUser);
            }
        }
        manageRole.removeFromManageUsers(toRemove);
        manageRole.addToManageUsers(items);
        return getManageRoleRepository().save(manageRole);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param manageRole
     */
    @Transactional
    public void delete(ManageRole manageRole) {
        // Clear bidirectional one-to-many parent relationship with ManageUser
        for (ManageUser item : manageRole.getManageUsers()) {
            item.setManageroles(null);
        }
        getManageRoleRepository().delete(manageRole);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param entities
     * @return List
     */
    @Transactional
    public List<ManageRole> save(Iterable<ManageRole> entities) {
        return getManageRoleRepository().save(entities);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param ids
     */
    @Transactional
    public void delete(Iterable<Long> ids) {
        List<ManageRole> toDelete = getManageRoleRepository().findAll(ids);
        getManageRoleRepository().deleteInBatch(toDelete);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param entity
     * @return ManageRole
     */
    @Transactional
    public ManageRole save(ManageRole entity) {
        return getManageRoleRepository().save(entity);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param id
     * @return ManageRole
     */
    public ManageRole findOne(Long id) {
        return getManageRoleRepository().findOne(id);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param id
     * @return ManageRole
     */
    public ManageRole findOneForUpdate(Long id) {
        return getManageRoleRepository().findOneDetached(id);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param ids
     * @return List
     */
    public List<ManageRole> findAll(Iterable<Long> ids) {
        return getManageRoleRepository().findAll(ids);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return List
     */
    public List<ManageRole> findAll() {
        return getManageRoleRepository().findAll();
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return Long
     */
    public long count() {
        return getManageRoleRepository().count();
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public Page<ManageRole> findAll(GlobalSearch globalSearch, Pageable pageable) {
        return getManageRoleRepository().findAll(globalSearch, pageable);
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
        return getManageRoleRepository().findAllByIdsIn(ids, globalSearch, pageable);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param roleName
     * @param pageable
     * @return Page
     */
    public Page<ManageRole> findByRoleName(String roleName, Pageable pageable) {
        return getManageRoleRepository().findByRoleName(roleName, pageable);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param roleName
     * @return Long
     */
    public long countByRoleName(String roleName) {
        return getManageRoleRepository().countByRoleName(roleName);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return Class
     */
    public Class<ManageRole> getEntityType() {
        return ManageRole.class;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return Class
     */
    public Class<Long> getIdType() {
        return Long.class;
    }
}
