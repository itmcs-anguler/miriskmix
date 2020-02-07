package com.itmcs.roo.mirisk.service.impl;
import com.itmcs.roo.mirisk.service.api.AssetRegisterService;
import org.springframework.roo.addon.layers.service.annotations.RooServiceImpl;
import com.itmcs.roo.mirisk.dta.AssetRegister;
import com.itmcs.roo.mirisk.repository.AssetRegisterRepository;
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
 * = AssetRegisterServiceImpl
 TODO Auto-generated class documentation
 *
 */
@RooServiceImpl(service = AssetRegisterService.class)
@Service
@Transactional(readOnly = true)
public class AssetRegisterServiceImpl implements AssetRegisterService {

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private AssetRegisterRepository assetRegisterRepository;

    /**
     * TODO Auto-generated constructor documentation
     *
     * @param assetRegisterRepository
     */
    @Autowired
    public AssetRegisterServiceImpl(AssetRegisterRepository assetRegisterRepository) {
        setAssetRegisterRepository(assetRegisterRepository);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return AssetRegisterRepository
     */
    public AssetRegisterRepository getAssetRegisterRepository() {
        return assetRegisterRepository;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param assetRegisterRepository
     */
    public void setAssetRegisterRepository(AssetRegisterRepository assetRegisterRepository) {
        this.assetRegisterRepository = assetRegisterRepository;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param assetregister
     * @return Map
     */
    public Map<String, List<MessageI18n>> validate(AssetRegister assetregister) {
        Map<String, List<MessageI18n>> messages = new java.util.HashMap<String, List<MessageI18n>>();
        // TODO: IMPLEMENT HERE THE VALIDATION OF YOUR ENTITY
        return messages;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param assetRegister
     */
    @Transactional
    public void delete(AssetRegister assetRegister) {
        getAssetRegisterRepository().delete(assetRegister);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param entities
     * @return List
     */
    @Transactional
    public List<AssetRegister> save(Iterable<AssetRegister> entities) {
        return getAssetRegisterRepository().save(entities);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param ids
     */
    @Transactional
    public void delete(Iterable<Long> ids) {
        List<AssetRegister> toDelete = getAssetRegisterRepository().findAll(ids);
        getAssetRegisterRepository().deleteInBatch(toDelete);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param entity
     * @return AssetRegister
     */
    @Transactional
    public AssetRegister save(AssetRegister entity) {
        return getAssetRegisterRepository().save(entity);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param id
     * @return AssetRegister
     */
    public AssetRegister findOne(Long id) {
        return getAssetRegisterRepository().findOne(id);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param id
     * @return AssetRegister
     */
    public AssetRegister findOneForUpdate(Long id) {
        return getAssetRegisterRepository().findOneDetached(id);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param ids
     * @return List
     */
    public List<AssetRegister> findAll(Iterable<Long> ids) {
        return getAssetRegisterRepository().findAll(ids);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return List
     */
    public List<AssetRegister> findAll() {
        return getAssetRegisterRepository().findAll();
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return Long
     */
    public long count() {
        return getAssetRegisterRepository().count();
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public Page<AssetRegister> findAll(GlobalSearch globalSearch, Pageable pageable) {
        return getAssetRegisterRepository().findAll(globalSearch, pageable);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param ids
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public Page<AssetRegister> findAllByIdsIn(List<Long> ids, GlobalSearch globalSearch, Pageable pageable) {
        return getAssetRegisterRepository().findAllByIdsIn(ids, globalSearch, pageable);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return Class
     */
    public Class<AssetRegister> getEntityType() {
        return AssetRegister.class;
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
