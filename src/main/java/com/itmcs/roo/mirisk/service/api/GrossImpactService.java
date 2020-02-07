package com.itmcs.roo.mirisk.service.api;
import com.itmcs.roo.mirisk.dta.GrossImpact;
import io.springlets.data.web.validation.ValidatorService;
import io.springlets.format.EntityResolver;
import org.springframework.roo.addon.layers.service.annotations.RooService;
import com.itmcs.roo.mirisk.dta.Ccy;
import io.springlets.data.domain.GlobalSearch;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * = GrossImpactService
 TODO Auto-generated class documentation
 *
 */
@RooService(entity = GrossImpact.class)
public interface GrossImpactService extends EntityResolver<GrossImpact, Long>, ValidatorService<GrossImpact> {

    /**
     * TODO Auto-generated method documentation
     *
     * @param id
     * @return GrossImpact
     */
    public abstract GrossImpact findOne(Long id);

    /**
     * TODO Auto-generated method documentation
     *
     * @param grossImpact
     */
    public abstract void delete(GrossImpact grossImpact);

    /**
     * TODO Auto-generated method documentation
     *
     * @param entities
     * @return List
     */
    public abstract List<GrossImpact> save(Iterable<GrossImpact> entities);

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
     * @return GrossImpact
     */
    public abstract GrossImpact save(GrossImpact entity);

    /**
     * TODO Auto-generated method documentation
     *
     * @param id
     * @return GrossImpact
     */
    public abstract GrossImpact findOneForUpdate(Long id);

    /**
     * TODO Auto-generated method documentation
     *
     * @param ids
     * @return List
     */
    public abstract List<GrossImpact> findAll(Iterable<Long> ids);

    /**
     * TODO Auto-generated method documentation
     *
     * @return List
     */
    public abstract List<GrossImpact> findAll();

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
    public abstract Page<GrossImpact> findAll(GlobalSearch globalSearch, Pageable pageable);

    /**
     * TODO Auto-generated method documentation
     *
     * @param ids
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public abstract Page<GrossImpact> findAllByIdsIn(List<Long> ids, GlobalSearch globalSearch, Pageable pageable);

    /**
     * TODO Auto-generated method documentation
     *
     * @param ccy
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public abstract Page<GrossImpact> findByCcy(Ccy ccy, GlobalSearch globalSearch, Pageable pageable);

    /**
     * TODO Auto-generated method documentation
     *
     * @param ccy
     * @return Long
     */
    public abstract long countByCcy(Ccy ccy);
}
