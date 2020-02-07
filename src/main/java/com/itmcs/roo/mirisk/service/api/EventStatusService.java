package com.itmcs.roo.mirisk.service.api;
import com.itmcs.roo.mirisk.dta.EventStatus;
import io.springlets.data.web.validation.ValidatorService;
import io.springlets.format.EntityResolver;
import org.springframework.roo.addon.layers.service.annotations.RooService;
import io.springlets.data.domain.GlobalSearch;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * = EventStatusService
 TODO Auto-generated class documentation
 *
 */
@RooService(entity = EventStatus.class)
public interface EventStatusService extends EntityResolver<EventStatus, Long>, ValidatorService<EventStatus> {

    /**
     * TODO Auto-generated method documentation
     *
     * @param id
     * @return EventStatus
     */
    public abstract EventStatus findOne(Long id);

    /**
     * TODO Auto-generated method documentation
     *
     * @param eventStatus
     */
    public abstract void delete(EventStatus eventStatus);

    /**
     * TODO Auto-generated method documentation
     *
     * @param entities
     * @return List
     */
    public abstract List<EventStatus> save(Iterable<EventStatus> entities);

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
     * @return EventStatus
     */
    public abstract EventStatus save(EventStatus entity);

    /**
     * TODO Auto-generated method documentation
     *
     * @param id
     * @return EventStatus
     */
    public abstract EventStatus findOneForUpdate(Long id);

    /**
     * TODO Auto-generated method documentation
     *
     * @param ids
     * @return List
     */
    public abstract List<EventStatus> findAll(Iterable<Long> ids);

    /**
     * TODO Auto-generated method documentation
     *
     * @return List
     */
    public abstract List<EventStatus> findAll();

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
    public abstract Page<EventStatus> findAll(GlobalSearch globalSearch, Pageable pageable);

    /**
     * TODO Auto-generated method documentation
     *
     * @param ids
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public abstract Page<EventStatus> findAllByIdsIn(List<Long> ids, GlobalSearch globalSearch, Pageable pageable);

    /**
     * TODO Auto-generated method documentation
     *
     * @param eventStatus
     * @param riskIncidentToAdd
     * @return EventStatus
     */
    public abstract EventStatus addToRiskIncident(EventStatus eventStatus, Iterable<Long> riskIncidentToAdd);

    /**
     * TODO Auto-generated method documentation
     *
     * @param eventStatus
     * @param riskIncidentToRemove
     * @return EventStatus
     */
    public abstract EventStatus removeFromRiskIncident(EventStatus eventStatus, Iterable<Long> riskIncidentToRemove);

    /**
     * TODO Auto-generated method documentation
     *
     * @param eventStatus
     * @param riskIncident
     * @return EventStatus
     */
    public abstract EventStatus setRiskIncident(EventStatus eventStatus, Iterable<Long> riskIncident);
}
