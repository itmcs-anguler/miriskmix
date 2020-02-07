package com.itmcs.roo.mirisk.service.api;
import com.itmcs.roo.mirisk.dta.Ccy;
import io.springlets.data.web.validation.ValidatorService;
import io.springlets.format.EntityResolver;
import org.springframework.roo.addon.layers.service.annotations.RooService;
import io.springlets.data.domain.GlobalSearch;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * = CcyService
 TODO Auto-generated class documentation
 *
 */
@RooService(entity = Ccy.class)
public interface CcyService extends EntityResolver<Ccy, Long>, ValidatorService<Ccy> {

    /**
     * TODO Auto-generated method documentation
     *
     * @param id
     * @return Ccy
     */
    public abstract Ccy findOne(Long id);

    /**
     * TODO Auto-generated method documentation
     *
     * @param ccy
     */
    public abstract void delete(Ccy ccy);

    /**
     * TODO Auto-generated method documentation
     *
     * @param entities
     * @return List
     */
    public abstract List<Ccy> save(Iterable<Ccy> entities);

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
     * @return Ccy
     */
    public abstract Ccy save(Ccy entity);

    /**
     * TODO Auto-generated method documentation
     *
     * @param id
     * @return Ccy
     */
    public abstract Ccy findOneForUpdate(Long id);

    /**
     * TODO Auto-generated method documentation
     *
     * @param ids
     * @return List
     */
    public abstract List<Ccy> findAll(Iterable<Long> ids);

    /**
     * TODO Auto-generated method documentation
     *
     * @return List
     */
    public abstract List<Ccy> findAll();

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
    public abstract Page<Ccy> findAll(GlobalSearch globalSearch, Pageable pageable);

    /**
     * TODO Auto-generated method documentation
     *
     * @param ids
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public abstract Page<Ccy> findAllByIdsIn(List<Long> ids, GlobalSearch globalSearch, Pageable pageable);

    /**
     * TODO Auto-generated method documentation
     *
     * @param ccy
     * @param grossImpactToAdd
     * @return Ccy
     */
    public abstract Ccy addToGrossImpact(Ccy ccy, Iterable<Long> grossImpactToAdd);

    /**
     * TODO Auto-generated method documentation
     *
     * @param ccy
     * @param grossImpactToRemove
     * @return Ccy
     */
    public abstract Ccy removeFromGrossImpact(Ccy ccy, Iterable<Long> grossImpactToRemove);

    /**
     * TODO Auto-generated method documentation
     *
     * @param ccy
     * @param grossImpact
     * @return Ccy
     */
    public abstract Ccy setGrossImpact(Ccy ccy, Iterable<Long> grossImpact);

    /**
     * TODO Auto-generated method documentation
     *
     * @param ccy
     * @param recoveryToAdd
     * @return Ccy
     */
    public abstract Ccy addToRecovery(Ccy ccy, Iterable<Long> recoveryToAdd);

    /**
     * TODO Auto-generated method documentation
     *
     * @param ccy
     * @param recoveryToRemove
     * @return Ccy
     */
    public abstract Ccy removeFromRecovery(Ccy ccy, Iterable<Long> recoveryToRemove);

    /**
     * TODO Auto-generated method documentation
     *
     * @param ccy
     * @param recovery
     * @return Ccy
     */
    public abstract Ccy setRecovery(Ccy ccy, Iterable<Long> recovery);
}
