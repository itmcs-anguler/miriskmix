package com.itmcs.roo.mirisk.service.api;
import com.itmcs.roo.mirisk.dta.RiskStatus;
import io.springlets.data.web.validation.ValidatorService;
import io.springlets.format.EntityResolver;
import org.springframework.roo.addon.layers.service.annotations.RooService;
import io.springlets.data.domain.GlobalSearch;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * = RiskStatusService
 TODO Auto-generated class documentation
 *
 */
@RooService(entity = RiskStatus.class)
public interface RiskStatusService extends EntityResolver<RiskStatus, Long>, ValidatorService<RiskStatus> {

    /**
     * TODO Auto-generated method documentation
     *
     * @param id
     * @return RiskStatus
     */
    public abstract RiskStatus findOne(Long id);

    /**
     * TODO Auto-generated method documentation
     *
     * @param riskStatus
     */
    public abstract void delete(RiskStatus riskStatus);

    /**
     * TODO Auto-generated method documentation
     *
     * @param entities
     * @return List
     */
    public abstract List<RiskStatus> save(Iterable<RiskStatus> entities);

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
     * @return RiskStatus
     */
    public abstract RiskStatus save(RiskStatus entity);

    /**
     * TODO Auto-generated method documentation
     *
     * @param id
     * @return RiskStatus
     */
    public abstract RiskStatus findOneForUpdate(Long id);

    /**
     * TODO Auto-generated method documentation
     *
     * @param ids
     * @return List
     */
    public abstract List<RiskStatus> findAll(Iterable<Long> ids);

    /**
     * TODO Auto-generated method documentation
     *
     * @return List
     */
    public abstract List<RiskStatus> findAll();

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
    public abstract Page<RiskStatus> findAll(GlobalSearch globalSearch, Pageable pageable);

    /**
     * TODO Auto-generated method documentation
     *
     * @param ids
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public abstract Page<RiskStatus> findAllByIdsIn(List<Long> ids, GlobalSearch globalSearch, Pageable pageable);

    /**
     * TODO Auto-generated method documentation
     *
     * @param riskStatus
     * @param riskRegistersToAdd
     * @return RiskStatus
     */
    public abstract RiskStatus addToRiskRegisters(RiskStatus riskStatus, Iterable<Long> riskRegistersToAdd);

    /**
     * TODO Auto-generated method documentation
     *
     * @param riskStatus
     * @param riskRegistersToRemove
     * @return RiskStatus
     */
    public abstract RiskStatus removeFromRiskRegisters(RiskStatus riskStatus, Iterable<Long> riskRegistersToRemove);

    /**
     * TODO Auto-generated method documentation
     *
     * @param riskStatus
     * @param riskRegisters
     * @return RiskStatus
     */
    public abstract RiskStatus setRiskRegisters(RiskStatus riskStatus, Iterable<Long> riskRegisters);
}
