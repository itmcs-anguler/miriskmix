package com.itmcs.roo.mirisk.service.api;
import com.itmcs.roo.mirisk.dta.RiskCategoryLevelOne;
import io.springlets.data.web.validation.ValidatorService;
import io.springlets.format.EntityResolver;
import org.springframework.roo.addon.layers.service.annotations.RooService;
import io.springlets.data.domain.GlobalSearch;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * = RiskCategoryLevelOneService
 TODO Auto-generated class documentation
 *
 */
@RooService(entity = RiskCategoryLevelOne.class)
public interface RiskCategoryLevelOneService extends EntityResolver<RiskCategoryLevelOne, Long>, ValidatorService<RiskCategoryLevelOne> {

    /**
     * TODO Auto-generated method documentation
     *
     * @param id
     * @return RiskCategoryLevelOne
     */
    public abstract RiskCategoryLevelOne findOne(Long id);

    /**
     * TODO Auto-generated method documentation
     *
     * @param riskCategoryLevelOne
     */
    public abstract void delete(RiskCategoryLevelOne riskCategoryLevelOne);

    /**
     * TODO Auto-generated method documentation
     *
     * @param entities
     * @return List
     */
    public abstract List<RiskCategoryLevelOne> save(Iterable<RiskCategoryLevelOne> entities);

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
     * @return RiskCategoryLevelOne
     */
    public abstract RiskCategoryLevelOne save(RiskCategoryLevelOne entity);

    /**
     * TODO Auto-generated method documentation
     *
     * @param id
     * @return RiskCategoryLevelOne
     */
    public abstract RiskCategoryLevelOne findOneForUpdate(Long id);

    /**
     * TODO Auto-generated method documentation
     *
     * @param ids
     * @return List
     */
    public abstract List<RiskCategoryLevelOne> findAll(Iterable<Long> ids);

    /**
     * TODO Auto-generated method documentation
     *
     * @return List
     */
    public abstract List<RiskCategoryLevelOne> findAll();

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
    public abstract Page<RiskCategoryLevelOne> findAll(GlobalSearch globalSearch, Pageable pageable);

    /**
     * TODO Auto-generated method documentation
     *
     * @param ids
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public abstract Page<RiskCategoryLevelOne> findAllByIdsIn(List<Long> ids, GlobalSearch globalSearch, Pageable pageable);

    /**
     * TODO Auto-generated method documentation
     *
     * @param riskCategoryLevelOne
     * @param riskIncidentToAdd
     * @return RiskCategoryLevelOne
     */
    public abstract RiskCategoryLevelOne addToRiskIncident(RiskCategoryLevelOne riskCategoryLevelOne, Iterable<Long> riskIncidentToAdd);

    /**
     * TODO Auto-generated method documentation
     *
     * @param riskCategoryLevelOne
     * @param riskIncidentToRemove
     * @return RiskCategoryLevelOne
     */
    public abstract RiskCategoryLevelOne removeFromRiskIncident(RiskCategoryLevelOne riskCategoryLevelOne, Iterable<Long> riskIncidentToRemove);

    /**
     * TODO Auto-generated method documentation
     *
     * @param riskCategoryLevelOne
     * @param riskIncident
     * @return RiskCategoryLevelOne
     */
    public abstract RiskCategoryLevelOne setRiskIncident(RiskCategoryLevelOne riskCategoryLevelOne, Iterable<Long> riskIncident);
}
