package com.itmcs.roo.mirisk.service.api;
import com.itmcs.roo.mirisk.dta.ResidualRiskLikelihood;
import io.springlets.data.web.validation.ValidatorService;
import io.springlets.format.EntityResolver;
import org.springframework.roo.addon.layers.service.annotations.RooService;
import io.springlets.data.domain.GlobalSearch;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * = ResidualRiskLikelihoodService
 TODO Auto-generated class documentation
 *
 */
@RooService(entity = ResidualRiskLikelihood.class)
public interface ResidualRiskLikelihoodService extends EntityResolver<ResidualRiskLikelihood, Long>, ValidatorService<ResidualRiskLikelihood> {

    /**
     * TODO Auto-generated method documentation
     *
     * @param id
     * @return ResidualRiskLikelihood
     */
    public abstract ResidualRiskLikelihood findOne(Long id);

    /**
     * TODO Auto-generated method documentation
     *
     * @param residualRiskLikelihood
     */
    public abstract void delete(ResidualRiskLikelihood residualRiskLikelihood);

    /**
     * TODO Auto-generated method documentation
     *
     * @param entities
     * @return List
     */
    public abstract List<ResidualRiskLikelihood> save(Iterable<ResidualRiskLikelihood> entities);

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
     * @return ResidualRiskLikelihood
     */
    public abstract ResidualRiskLikelihood save(ResidualRiskLikelihood entity);

    /**
     * TODO Auto-generated method documentation
     *
     * @param id
     * @return ResidualRiskLikelihood
     */
    public abstract ResidualRiskLikelihood findOneForUpdate(Long id);

    /**
     * TODO Auto-generated method documentation
     *
     * @param ids
     * @return List
     */
    public abstract List<ResidualRiskLikelihood> findAll(Iterable<Long> ids);

    /**
     * TODO Auto-generated method documentation
     *
     * @return List
     */
    public abstract List<ResidualRiskLikelihood> findAll();

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
    public abstract Page<ResidualRiskLikelihood> findAll(GlobalSearch globalSearch, Pageable pageable);

    /**
     * TODO Auto-generated method documentation
     *
     * @param ids
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public abstract Page<ResidualRiskLikelihood> findAllByIdsIn(List<Long> ids, GlobalSearch globalSearch, Pageable pageable);

    /**
     * TODO Auto-generated method documentation
     *
     * @param residualRiskLikelihood
     * @param inheritRiskLikelihoodToAdd
     * @return ResidualRiskLikelihood
     */
    public abstract ResidualRiskLikelihood addToInheritRiskLikelihood(ResidualRiskLikelihood residualRiskLikelihood, Iterable<Long> inheritRiskLikelihoodToAdd);

    /**
     * TODO Auto-generated method documentation
     *
     * @param residualRiskLikelihood
     * @param inheritRiskLikelihoodToRemove
     * @return ResidualRiskLikelihood
     */
    public abstract ResidualRiskLikelihood removeFromInheritRiskLikelihood(ResidualRiskLikelihood residualRiskLikelihood, Iterable<Long> inheritRiskLikelihoodToRemove);

    /**
     * TODO Auto-generated method documentation
     *
     * @param residualRiskLikelihood
     * @param inheritRiskLikelihood
     * @return ResidualRiskLikelihood
     */
    public abstract ResidualRiskLikelihood setInheritRiskLikelihood(ResidualRiskLikelihood residualRiskLikelihood, Iterable<Long> inheritRiskLikelihood);
}
