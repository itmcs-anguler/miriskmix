package com.itmcs.roo.mirisk.service.api;
import com.itmcs.roo.mirisk.dta.RiskCategoryLevelTwo;
import io.springlets.data.web.validation.ValidatorService;
import io.springlets.format.EntityResolver;
import org.springframework.roo.addon.layers.service.annotations.RooService;
import io.springlets.data.domain.GlobalSearch;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * = RiskCategoryLevelTwoService
 TODO Auto-generated class documentation
 *
 */
@RooService(entity = RiskCategoryLevelTwo.class)
public interface RiskCategoryLevelTwoService extends EntityResolver<RiskCategoryLevelTwo, Long>, ValidatorService<RiskCategoryLevelTwo> {

    /**
     * TODO Auto-generated method documentation
     *
     * @param id
     * @return RiskCategoryLevelTwo
     */
    public abstract RiskCategoryLevelTwo findOne(Long id);

    /**
     * TODO Auto-generated method documentation
     *
     * @param riskCategoryLevelTwo
     */
    public abstract void delete(RiskCategoryLevelTwo riskCategoryLevelTwo);

    /**
     * TODO Auto-generated method documentation
     *
     * @param entities
     * @return List
     */
    public abstract List<RiskCategoryLevelTwo> save(Iterable<RiskCategoryLevelTwo> entities);

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
     * @return RiskCategoryLevelTwo
     */
    public abstract RiskCategoryLevelTwo save(RiskCategoryLevelTwo entity);

    /**
     * TODO Auto-generated method documentation
     *
     * @param id
     * @return RiskCategoryLevelTwo
     */
    public abstract RiskCategoryLevelTwo findOneForUpdate(Long id);

    /**
     * TODO Auto-generated method documentation
     *
     * @param ids
     * @return List
     */
    public abstract List<RiskCategoryLevelTwo> findAll(Iterable<Long> ids);

    /**
     * TODO Auto-generated method documentation
     *
     * @return List
     */
    public abstract List<RiskCategoryLevelTwo> findAll();

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
    public abstract Page<RiskCategoryLevelTwo> findAll(GlobalSearch globalSearch, Pageable pageable);

    /**
     * TODO Auto-generated method documentation
     *
     * @param ids
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public abstract Page<RiskCategoryLevelTwo> findAllByIdsIn(List<Long> ids, GlobalSearch globalSearch, Pageable pageable);

    /**
     * TODO Auto-generated method documentation
     *
     * @param riskCategoryLevelTwo
     * @param riskIncidentToAdd
     * @return RiskCategoryLevelTwo
     */
    public abstract RiskCategoryLevelTwo addToRiskIncident(RiskCategoryLevelTwo riskCategoryLevelTwo, Iterable<Long> riskIncidentToAdd);

    /**
     * TODO Auto-generated method documentation
     *
     * @param riskCategoryLevelTwo
     * @param riskIncidentToRemove
     * @return RiskCategoryLevelTwo
     */
    public abstract RiskCategoryLevelTwo removeFromRiskIncident(RiskCategoryLevelTwo riskCategoryLevelTwo, Iterable<Long> riskIncidentToRemove);

    /**
     * TODO Auto-generated method documentation
     *
     * @param riskCategoryLevelTwo
     * @param riskIncident
     * @return RiskCategoryLevelTwo
     */
    public abstract RiskCategoryLevelTwo setRiskIncident(RiskCategoryLevelTwo riskCategoryLevelTwo, Iterable<Long> riskIncident);
}
