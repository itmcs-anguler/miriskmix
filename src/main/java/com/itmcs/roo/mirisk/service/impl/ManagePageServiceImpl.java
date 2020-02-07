package com.itmcs.roo.mirisk.service.impl;
import com.itmcs.roo.mirisk.service.api.ManagePageService;
import org.springframework.roo.addon.layers.service.annotations.RooServiceImpl;
import com.itmcs.roo.mirisk.dta.ManagePage;
import com.itmcs.roo.mirisk.repository.ManagePageRepository;
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
 * = ManagePageServiceImpl
 TODO Auto-generated class documentation
 *
 */
@RooServiceImpl(service = ManagePageService.class)
@Service
@Transactional(readOnly = true)
public class ManagePageServiceImpl implements ManagePageService {

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private ManagePageRepository managePageRepository;

    /**
     * TODO Auto-generated constructor documentation
     *
     * @param managePageRepository
     */
    @Autowired
    public ManagePageServiceImpl(ManagePageRepository managePageRepository) {
        setManagePageRepository(managePageRepository);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return ManagePageRepository
     */
    public ManagePageRepository getManagePageRepository() {
        return managePageRepository;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param managePageRepository
     */
    public void setManagePageRepository(ManagePageRepository managePageRepository) {
        this.managePageRepository = managePageRepository;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param managepage
     * @return Map
     */
    public Map<String, List<MessageI18n>> validate(ManagePage managepage) {
        Map<String, List<MessageI18n>> messages = new java.util.HashMap<String, List<MessageI18n>>();
        // TODO: IMPLEMENT HERE THE VALIDATION OF YOUR ENTITY
        return messages;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param managePage
     */
    @Transactional
    public void delete(ManagePage managePage) {
        getManagePageRepository().delete(managePage);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param entities
     * @return List
     */
    @Transactional
    public List<ManagePage> save(Iterable<ManagePage> entities) {
        return getManagePageRepository().save(entities);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param ids
     */
    @Transactional
    public void delete(Iterable<Long> ids) {
        List<ManagePage> toDelete = getManagePageRepository().findAll(ids);
        getManagePageRepository().deleteInBatch(toDelete);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param entity
     * @return ManagePage
     */
    @Transactional
    public ManagePage save(ManagePage entity) {
        return getManagePageRepository().save(entity);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param id
     * @return ManagePage
     */
    public ManagePage findOne(Long id) {
        return getManagePageRepository().findOne(id);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param id
     * @return ManagePage
     */
    public ManagePage findOneForUpdate(Long id) {
        return getManagePageRepository().findOneDetached(id);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param ids
     * @return List
     */
    public List<ManagePage> findAll(Iterable<Long> ids) {
        return getManagePageRepository().findAll(ids);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return List
     */
    public List<ManagePage> findAll() {
        return getManagePageRepository().findAll();
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return Long
     */
    public long count() {
        return getManagePageRepository().count();
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public Page<ManagePage> findAll(GlobalSearch globalSearch, Pageable pageable) {
        return getManagePageRepository().findAll(globalSearch, pageable);
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
        return getManagePageRepository().findAllByIdsIn(ids, globalSearch, pageable);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param pageName
     * @param pageable
     * @return Page
     */
    public Page<ManagePage> findByPageName(String pageName, Pageable pageable) {
        return getManagePageRepository().findByPageName(pageName, pageable);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param pageName
     * @return Long
     */
    public long countByPageName(String pageName) {
        return getManagePageRepository().countByPageName(pageName);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return Class
     */
    public Class<ManagePage> getEntityType() {
        return ManagePage.class;
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
