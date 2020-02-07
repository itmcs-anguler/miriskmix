package com.itmcs.roo.mirisk.service.api;
import com.itmcs.roo.mirisk.dta.RiskIncident;
import io.springlets.data.web.validation.ValidatorService;
import io.springlets.format.EntityResolver;
import org.springframework.roo.addon.layers.service.annotations.RooService;
import com.itmcs.roo.mirisk.dta.EventStatus;
import com.itmcs.roo.mirisk.dta.EventType;
import com.itmcs.roo.mirisk.dta.RiskCategoryLevelOne;
import com.itmcs.roo.mirisk.dta.RiskCategoryLevelTwo;
import io.springlets.data.domain.GlobalSearch;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * = RiskIncidentService
 TODO Auto-generated class documentation
 *
 */
@RooService(entity = RiskIncident.class)
public interface RiskIncidentService extends EntityResolver<RiskIncident, Long>, ValidatorService<RiskIncident> {

    /**
     * TODO Auto-generated method documentation
     *
     * @param id
     * @return RiskIncident
     */
    public abstract RiskIncident findOne(Long id);

    /**
     * TODO Auto-generated method documentation
     *
     * @param riskIncident
     */
    public abstract void delete(RiskIncident riskIncident);

    /**
     * TODO Auto-generated method documentation
     *
     * @param entities
     * @return List
     */
    public abstract List<RiskIncident> save(Iterable<RiskIncident> entities);

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
     * @return RiskIncident
     */
    public abstract RiskIncident save(RiskIncident entity);

    /**
     * TODO Auto-generated method documentation
     *
     * @param id
     * @return RiskIncident
     */
    public abstract RiskIncident findOneForUpdate(Long id);

    /**
     * TODO Auto-generated method documentation
     *
     * @param ids
     * @return List
     */
    public abstract List<RiskIncident> findAll(Iterable<Long> ids);

    /**
     * TODO Auto-generated method documentation
     *
     * @return List
     */
    public abstract List<RiskIncident> findAll();

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
    public abstract Page<RiskIncident> findAll(GlobalSearch globalSearch, Pageable pageable);

    /**
     * TODO Auto-generated method documentation
     *
     * @param ids
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public abstract Page<RiskIncident> findAllByIdsIn(List<Long> ids, GlobalSearch globalSearch, Pageable pageable);

    /**
     * TODO Auto-generated method documentation
     *
     * @param eventStatus
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public abstract Page<RiskIncident> findByEventStatus(EventStatus eventStatus, GlobalSearch globalSearch, Pageable pageable);

    /**
     * TODO Auto-generated method documentation
     *
     * @param eventType
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public abstract Page<RiskIncident> findByEventType(EventType eventType, GlobalSearch globalSearch, Pageable pageable);

    /**
     * TODO Auto-generated method documentation
     *
     * @param riskCategoryOne
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public abstract Page<RiskIncident> findByRiskCategoryOne(RiskCategoryLevelOne riskCategoryOne, GlobalSearch globalSearch, Pageable pageable);

    /**
     * TODO Auto-generated method documentation
     *
     * @param riskCategoryTwo
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public abstract Page<RiskIncident> findByRiskCategoryTwo(RiskCategoryLevelTwo riskCategoryTwo, GlobalSearch globalSearch, Pageable pageable);

    /**
     * TODO Auto-generated method documentation
     *
     * @param eventStatus
     * @return Long
     */
    public abstract long countByEventStatus(EventStatus eventStatus);

    /**
     * TODO Auto-generated method documentation
     *
     * @param eventType
     * @return Long
     */
    public abstract long countByEventType(EventType eventType);

    /**
     * TODO Auto-generated method documentation
     *
     * @param riskCategoryOne
     * @return Long
     */
    public abstract long countByRiskCategoryOne(RiskCategoryLevelOne riskCategoryOne);

    /**
     * TODO Auto-generated method documentation
     *
     * @param riskCategoryTwo
     * @return Long
     */
    public abstract long countByRiskCategoryTwo(RiskCategoryLevelTwo riskCategoryTwo);
}
