package com.itmcs.roo.mirisk.service.api;
import com.itmcs.roo.mirisk.dta.AdditionalDetails;
import io.springlets.data.web.validation.ValidatorService;
import io.springlets.format.EntityResolver;
import org.springframework.roo.addon.layers.service.annotations.RooService;
import io.springlets.data.domain.GlobalSearch;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * = AdditionalDetailsService
 TODO Auto-generated class documentation
 *
 */
@RooService(entity = AdditionalDetails.class)
public interface AdditionalDetailsService extends EntityResolver<AdditionalDetails, Long>, ValidatorService<AdditionalDetails> {

    /**
     * TODO Auto-generated method documentation
     *
     * @param id
     * @return AdditionalDetails
     */
    public abstract AdditionalDetails findOne(Long id);

    /**
     * TODO Auto-generated method documentation
     *
     * @param additionalDetails
     */
    public abstract void delete(AdditionalDetails additionalDetails);

    /**
     * TODO Auto-generated method documentation
     *
     * @param entities
     * @return List
     */
    public abstract List<AdditionalDetails> save(Iterable<AdditionalDetails> entities);

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
     * @return AdditionalDetails
     */
    public abstract AdditionalDetails save(AdditionalDetails entity);

    /**
     * TODO Auto-generated method documentation
     *
     * @param id
     * @return AdditionalDetails
     */
    public abstract AdditionalDetails findOneForUpdate(Long id);

    /**
     * TODO Auto-generated method documentation
     *
     * @param ids
     * @return List
     */
    public abstract List<AdditionalDetails> findAll(Iterable<Long> ids);

    /**
     * TODO Auto-generated method documentation
     *
     * @return List
     */
    public abstract List<AdditionalDetails> findAll();

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
    public abstract Page<AdditionalDetails> findAll(GlobalSearch globalSearch, Pageable pageable);

    /**
     * TODO Auto-generated method documentation
     *
     * @param ids
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public abstract Page<AdditionalDetails> findAllByIdsIn(List<Long> ids, GlobalSearch globalSearch, Pageable pageable);
}
