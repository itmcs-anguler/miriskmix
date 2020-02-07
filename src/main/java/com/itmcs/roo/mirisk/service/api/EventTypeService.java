package com.itmcs.roo.mirisk.service.api;
import com.itmcs.roo.mirisk.dta.EventType;
import io.springlets.data.web.validation.ValidatorService;
import io.springlets.format.EntityResolver;
import org.springframework.roo.addon.layers.service.annotations.RooService;
import io.springlets.data.domain.GlobalSearch;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * = EventTypeService
 TODO Auto-generated class documentation
 *
 */
@RooService(entity = EventType.class)
public interface EventTypeService extends EntityResolver<EventType, Long>, ValidatorService<EventType> {

    /**
     * TODO Auto-generated method documentation
     *
     * @param id
     * @return EventType
     */
    public abstract EventType findOne(Long id);

    /**
     * TODO Auto-generated method documentation
     *
     * @param eventType
     */
    public abstract void delete(EventType eventType);

    /**
     * TODO Auto-generated method documentation
     *
     * @param entities
     * @return List
     */
    public abstract List<EventType> save(Iterable<EventType> entities);

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
     * @return EventType
     */
    public abstract EventType save(EventType entity);

    /**
     * TODO Auto-generated method documentation
     *
     * @param id
     * @return EventType
     */
    public abstract EventType findOneForUpdate(Long id);

    /**
     * TODO Auto-generated method documentation
     *
     * @param ids
     * @return List
     */
    public abstract List<EventType> findAll(Iterable<Long> ids);

    /**
     * TODO Auto-generated method documentation
     *
     * @return List
     */
    public abstract List<EventType> findAll();

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
    public abstract Page<EventType> findAll(GlobalSearch globalSearch, Pageable pageable);

    /**
     * TODO Auto-generated method documentation
     *
     * @param ids
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public abstract Page<EventType> findAllByIdsIn(List<Long> ids, GlobalSearch globalSearch, Pageable pageable);

    /**
     * TODO Auto-generated method documentation
     *
     * @param eventType
     * @param riskIncidentToAdd
     * @return EventType
     */
    public abstract EventType addToRiskIncident(EventType eventType, Iterable<Long> riskIncidentToAdd);

    /**
     * TODO Auto-generated method documentation
     *
     * @param eventType
     * @param riskIncidentToRemove
     * @return EventType
     */
    public abstract EventType removeFromRiskIncident(EventType eventType, Iterable<Long> riskIncidentToRemove);

    /**
     * TODO Auto-generated method documentation
     *
     * @param eventType
     * @param riskIncident
     * @return EventType
     */
    public abstract EventType setRiskIncident(EventType eventType, Iterable<Long> riskIncident);
}
