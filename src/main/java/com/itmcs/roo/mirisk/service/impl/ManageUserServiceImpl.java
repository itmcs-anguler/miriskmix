package com.itmcs.roo.mirisk.service.impl;
import com.itmcs.roo.mirisk.service.api.ManageUserService;
import org.springframework.roo.addon.layers.service.annotations.RooServiceImpl;
import com.itmcs.roo.mirisk.dta.ManageRole;
import com.itmcs.roo.mirisk.dta.ManageUser;
import com.itmcs.roo.mirisk.repository.ManageUserRepository;
import io.springlets.data.domain.GlobalSearch;
import io.springlets.data.web.validation.MessageI18n;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * = ManageUserServiceImpl
 TODO Auto-generated class documentation
 *
 */
@RooServiceImpl(service = ManageUserService.class)
@Service
@Transactional(readOnly = true)
public class ManageUserServiceImpl implements ManageUserService {

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private ManageUserRepository manageUserRepository;

    /**
     * TODO Auto-generated constructor documentation
     *
     * @param manageUserRepository
     */
    @Autowired
    public ManageUserServiceImpl(ManageUserRepository manageUserRepository) {
        setManageUserRepository(manageUserRepository);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return ManageUserRepository
     */
    public ManageUserRepository getManageUserRepository() {
        return manageUserRepository;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param manageUserRepository
     */
    public void setManageUserRepository(ManageUserRepository manageUserRepository) {
        this.manageUserRepository = manageUserRepository;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param manageuser
     * @return Map
     */
    public Map<String, List<MessageI18n>> validate(ManageUser manageuser) {
        Map<String, List<MessageI18n>> messages = new java.util.HashMap<String, List<MessageI18n>>();
        // TODO: IMPLEMENT HERE THE VALIDATION OF YOUR ENTITY
        return messages;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param manageUser
     */
    @Transactional
    public void delete(ManageUser manageUser) {
        // Clear bidirectional many-to-one child relationship with ManageRole
        if (manageUser.getManageroles() != null) {
            manageUser.getManageroles().getManageUsers().remove(manageUser);
        }
        getManageUserRepository().delete(manageUser);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param entities
     * @return List
     */
    @Transactional
    public List<ManageUser> save(Iterable<ManageUser> entities) {
        return getManageUserRepository().save(entities);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param ids
     */
    @Transactional
    public void delete(Iterable<Long> ids) {
        List<ManageUser> toDelete = getManageUserRepository().findAll(ids);
        getManageUserRepository().deleteInBatch(toDelete);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param entity
     * @return ManageUser
     */
    @Transactional
    public ManageUser save(ManageUser entity) {
        return getManageUserRepository().save(entity);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param id
     * @return ManageUser
     */
    public ManageUser findOne(Long id) {
        return getManageUserRepository().findOne(id);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param id
     * @return ManageUser
     */
    public ManageUser findOneForUpdate(Long id) {
        return getManageUserRepository().findOneDetached(id);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param ids
     * @return List
     */
    public List<ManageUser> findAll(Iterable<Long> ids) {
        return getManageUserRepository().findAll(ids);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return List
     */
    public List<ManageUser> findAll() {
        return getManageUserRepository().findAll();
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return Long
     */
    public long count() {
        return getManageUserRepository().count();
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public Page<ManageUser> findAll(GlobalSearch globalSearch, Pageable pageable) {
        return getManageUserRepository().findAll(globalSearch, pageable);
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
        return getManageUserRepository().findAllByIdsIn(ids, globalSearch, pageable);
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
        return getManageUserRepository().findByManageroles(manageroles, globalSearch, pageable);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param manageroles
     * @return Long
     */
    public long countByManageroles(ManageRole manageroles) {
        return getManageUserRepository().countByManageroles(manageroles);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return Class
     */
    public Class<ManageUser> getEntityType() {
        return ManageUser.class;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return Class
     */
    public Class<Long> getIdType() {
        return Long.class;
    }

    @Override
    public ManageUser findByUserName(String username) {
        // TODO Auto-generated method stub
        return getManageUserRepository().findByUserName(username);
    }
}
