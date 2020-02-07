package com.itmcs.roo.mirisk.service.api;
import com.itmcs.roo.mirisk.dta.Recovery;
import io.springlets.data.web.validation.ValidatorService;
import io.springlets.format.EntityResolver;
import org.springframework.roo.addon.layers.service.annotations.RooService;
import com.itmcs.roo.mirisk.dta.Ccy;
import io.springlets.data.domain.GlobalSearch;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * = RecoveryService
 TODO Auto-generated class documentation
 *
 */
@RooService(entity = Recovery.class)
public interface RecoveryService extends EntityResolver<Recovery, Long>, ValidatorService<Recovery> {

    /**
     * TODO Auto-generated method documentation
     *
     * @param id
     * @return Recovery
     */
    public abstract Recovery findOne(Long id);

    /**
     * TODO Auto-generated method documentation
     *
     * @param recovery
     */
    public abstract void delete(Recovery recovery);

    /**
     * TODO Auto-generated method documentation
     *
     * @param entities
     * @return List
     */
    public abstract List<Recovery> save(Iterable<Recovery> entities);

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
     * @return Recovery
     */
    public abstract Recovery save(Recovery entity);

    /**
     * TODO Auto-generated method documentation
     *
     * @param id
     * @return Recovery
     */
    public abstract Recovery findOneForUpdate(Long id);

    /**
     * TODO Auto-generated method documentation
     *
     * @param ids
     * @return List
     */
    public abstract List<Recovery> findAll(Iterable<Long> ids);

    /**
     * TODO Auto-generated method documentation
     *
     * @return List
     */
    public abstract List<Recovery> findAll();

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
    public abstract Page<Recovery> findAll(GlobalSearch globalSearch, Pageable pageable);

    /**
     * TODO Auto-generated method documentation
     *
     * @param ids
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public abstract Page<Recovery> findAllByIdsIn(List<Long> ids, GlobalSearch globalSearch, Pageable pageable);

    /**
     * TODO Auto-generated method documentation
     *
     * @param ccy
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public abstract Page<Recovery> findByCcy(Ccy ccy, GlobalSearch globalSearch, Pageable pageable);

    /**
     * TODO Auto-generated method documentation
     *
     * @param ccy
     * @return Long
     */
    public abstract long countByCcy(Ccy ccy);
}
