package com.itmcs.roo.mirisk.service.api;
import com.itmcs.roo.mirisk.dta.AssetRegister;
import io.springlets.data.web.validation.ValidatorService;
import io.springlets.format.EntityResolver;
import org.springframework.roo.addon.layers.service.annotations.RooService;
import io.springlets.data.domain.GlobalSearch;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * = AssetRegisterService
 TODO Auto-generated class documentation
 *
 */
@RooService(entity = AssetRegister.class)
public interface AssetRegisterService extends EntityResolver<AssetRegister, Long>, ValidatorService<AssetRegister> {

    /**
     * TODO Auto-generated method documentation
     *
     * @param id
     * @return AssetRegister
     */
    public abstract AssetRegister findOne(Long id);

    /**
     * TODO Auto-generated method documentation
     *
     * @param assetRegister
     */
    public abstract void delete(AssetRegister assetRegister);

    /**
     * TODO Auto-generated method documentation
     *
     * @param entities
     * @return List
     */
    public abstract List<AssetRegister> save(Iterable<AssetRegister> entities);

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
     * @return AssetRegister
     */
    public abstract AssetRegister save(AssetRegister entity);

    /**
     * TODO Auto-generated method documentation
     *
     * @param id
     * @return AssetRegister
     */
    public abstract AssetRegister findOneForUpdate(Long id);

    /**
     * TODO Auto-generated method documentation
     *
     * @param ids
     * @return List
     */
    public abstract List<AssetRegister> findAll(Iterable<Long> ids);

    /**
     * TODO Auto-generated method documentation
     *
     * @return List
     */
    public abstract List<AssetRegister> findAll();

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
    public abstract Page<AssetRegister> findAll(GlobalSearch globalSearch, Pageable pageable);

    /**
     * TODO Auto-generated method documentation
     *
     * @param ids
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public abstract Page<AssetRegister> findAllByIdsIn(List<Long> ids, GlobalSearch globalSearch, Pageable pageable);
}
