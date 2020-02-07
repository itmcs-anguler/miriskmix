package com.itmcs.roo.mirisk.service.impl;
import com.itmcs.roo.mirisk.service.api.AdditionalDetailsService;
import org.springframework.roo.addon.layers.service.annotations.RooServiceImpl;
import com.itmcs.roo.mirisk.dta.AdditionalDetails;
import com.itmcs.roo.mirisk.repository.AdditionalDetailsRepository;
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
 * = AdditionalDetailsServiceImpl
 TODO Auto-generated class documentation
 *
 */
@RooServiceImpl(service = AdditionalDetailsService.class)
@Service
@Transactional(readOnly = true)
public class AdditionalDetailsServiceImpl implements AdditionalDetailsService {

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private AdditionalDetailsRepository additionalDetailsRepository;

    /**
     * TODO Auto-generated constructor documentation
     *
     * @param additionalDetailsRepository
     */
    @Autowired
    public AdditionalDetailsServiceImpl(AdditionalDetailsRepository additionalDetailsRepository) {
        setAdditionalDetailsRepository(additionalDetailsRepository);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return AdditionalDetailsRepository
     */
    public AdditionalDetailsRepository getAdditionalDetailsRepository() {
        return additionalDetailsRepository;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param additionalDetailsRepository
     */
    public void setAdditionalDetailsRepository(AdditionalDetailsRepository additionalDetailsRepository) {
        this.additionalDetailsRepository = additionalDetailsRepository;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param additionaldetails
     * @return Map
     */
    public Map<String, List<MessageI18n>> validate(AdditionalDetails additionaldetails) {
        Map<String, List<MessageI18n>> messages = new java.util.HashMap<String, List<MessageI18n>>();
        // TODO: IMPLEMENT HERE THE VALIDATION OF YOUR ENTITY
        return messages;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param additionalDetails
     */
    @Transactional
    public void delete(AdditionalDetails additionalDetails) {
        getAdditionalDetailsRepository().delete(additionalDetails);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param entities
     * @return List
     */
    @Transactional
    public List<AdditionalDetails> save(Iterable<AdditionalDetails> entities) {
        return getAdditionalDetailsRepository().save(entities);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param ids
     */
    @Transactional
    public void delete(Iterable<Long> ids) {
        List<AdditionalDetails> toDelete = getAdditionalDetailsRepository().findAll(ids);
        getAdditionalDetailsRepository().deleteInBatch(toDelete);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param entity
     * @return AdditionalDetails
     */
    @Transactional
    public AdditionalDetails save(AdditionalDetails entity) {
        return getAdditionalDetailsRepository().save(entity);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param id
     * @return AdditionalDetails
     */
    public AdditionalDetails findOne(Long id) {
        return getAdditionalDetailsRepository().findOne(id);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param id
     * @return AdditionalDetails
     */
    public AdditionalDetails findOneForUpdate(Long id) {
        return getAdditionalDetailsRepository().findOneDetached(id);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param ids
     * @return List
     */
    public List<AdditionalDetails> findAll(Iterable<Long> ids) {
        return getAdditionalDetailsRepository().findAll(ids);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return List
     */
    public List<AdditionalDetails> findAll() {
        return getAdditionalDetailsRepository().findAll();
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return Long
     */
    public long count() {
        return getAdditionalDetailsRepository().count();
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public Page<AdditionalDetails> findAll(GlobalSearch globalSearch, Pageable pageable) {
        return getAdditionalDetailsRepository().findAll(globalSearch, pageable);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param ids
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public Page<AdditionalDetails> findAllByIdsIn(List<Long> ids, GlobalSearch globalSearch, Pageable pageable) {
        return getAdditionalDetailsRepository().findAllByIdsIn(ids, globalSearch, pageable);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return Class
     */
    public Class<AdditionalDetails> getEntityType() {
        return AdditionalDetails.class;
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
