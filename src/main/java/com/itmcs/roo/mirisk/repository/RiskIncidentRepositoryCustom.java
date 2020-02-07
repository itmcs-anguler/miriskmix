package com.itmcs.roo.mirisk.repository;
import com.itmcs.roo.mirisk.dta.RiskIncident;
import org.springframework.roo.addon.layers.repository.jpa.annotations.RooJpaRepositoryCustom;
import com.itmcs.roo.mirisk.dta.EventStatus;
import com.itmcs.roo.mirisk.dta.EventType;
import com.itmcs.roo.mirisk.dta.RiskCategoryLevelOne;
import com.itmcs.roo.mirisk.dta.RiskCategoryLevelTwo;
import io.springlets.data.domain.GlobalSearch;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * = RiskIncidentRepositoryCustom
 TODO Auto-generated class documentation
 *
 */
@RooJpaRepositoryCustom(entity = RiskIncident.class)
public interface RiskIncidentRepositoryCustom {

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
}
