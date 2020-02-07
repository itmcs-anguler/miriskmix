package com.itmcs.roo.mirisk.repository;
import com.itmcs.roo.mirisk.dta.RiskIncident;
import io.springlets.data.jpa.repository.DetachableJpaRepository;
import org.springframework.roo.addon.layers.repository.jpa.annotations.RooJpaRepository;
import com.itmcs.roo.mirisk.dta.EventStatus;
import com.itmcs.roo.mirisk.dta.EventType;
import com.itmcs.roo.mirisk.dta.RiskCategoryLevelOne;
import com.itmcs.roo.mirisk.dta.RiskCategoryLevelTwo;
import org.springframework.transaction.annotation.Transactional;

/**
 * = RiskIncidentRepository
 TODO Auto-generated class documentation
 *
 */
@RooJpaRepository(entity = RiskIncident.class)
@Transactional(readOnly = true)
public interface RiskIncidentRepository extends DetachableJpaRepository<RiskIncident, Long>, RiskIncidentRepositoryCustom {

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
}
