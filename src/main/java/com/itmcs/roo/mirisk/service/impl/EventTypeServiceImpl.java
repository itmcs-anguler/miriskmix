package com.itmcs.roo.mirisk.service.impl;
import com.itmcs.roo.mirisk.service.api.EventTypeService;
import org.springframework.roo.addon.layers.service.annotations.RooServiceImpl;
import com.itmcs.roo.mirisk.dta.EventType;
import com.itmcs.roo.mirisk.dta.RiskIncident;
import com.itmcs.roo.mirisk.repository.EventTypeRepository;
import com.itmcs.roo.mirisk.service.api.RiskIncidentService;
import io.springlets.data.domain.GlobalSearch;
import io.springlets.data.web.validation.MessageI18n;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * = EventTypeServiceImpl
 TODO Auto-generated class documentation
 *
 */
@RooServiceImpl(service = EventTypeService.class)
@Service
@Transactional(readOnly = true)
public class EventTypeServiceImpl implements EventTypeService {

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private RiskIncidentService riskIncidentService;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private EventTypeRepository eventTypeRepository;

    /**
     * TODO Auto-generated constructor documentation
     *
     * @param eventTypeRepository
     * @param riskIncidentService
     */
    @Autowired
    public EventTypeServiceImpl(EventTypeRepository eventTypeRepository, @Lazy RiskIncidentService riskIncidentService) {
        setEventTypeRepository(eventTypeRepository);
        setRiskIncidentService(riskIncidentService);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return EventTypeRepository
     */
    public EventTypeRepository getEventTypeRepository() {
        return eventTypeRepository;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param eventTypeRepository
     */
    public void setEventTypeRepository(EventTypeRepository eventTypeRepository) {
        this.eventTypeRepository = eventTypeRepository;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return RiskIncidentService
     */
    public RiskIncidentService getRiskIncidentService() {
        return riskIncidentService;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param riskIncidentService
     */
    public void setRiskIncidentService(RiskIncidentService riskIncidentService) {
        this.riskIncidentService = riskIncidentService;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param eventtype
     * @return Map
     */
    public Map<String, List<MessageI18n>> validate(EventType eventtype) {
        Map<String, List<MessageI18n>> messages = new java.util.HashMap<String, List<MessageI18n>>();
        // TODO: IMPLEMENT HERE THE VALIDATION OF YOUR ENTITY
        return messages;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param eventType
     * @param riskIncidentToAdd
     * @return EventType
     */
    @Transactional
    public EventType addToRiskIncident(EventType eventType, Iterable<Long> riskIncidentToAdd) {
        List<RiskIncident> riskIncident = getRiskIncidentService().findAll(riskIncidentToAdd);
        eventType.addToRiskIncident(riskIncident);
        return getEventTypeRepository().save(eventType);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param eventType
     * @param riskIncidentToRemove
     * @return EventType
     */
    @Transactional
    public EventType removeFromRiskIncident(EventType eventType, Iterable<Long> riskIncidentToRemove) {
        List<RiskIncident> riskIncident = getRiskIncidentService().findAll(riskIncidentToRemove);
        eventType.removeFromRiskIncident(riskIncident);
        return getEventTypeRepository().save(eventType);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param eventType
     * @param riskIncident
     * @return EventType
     */
    @Transactional
    public EventType setRiskIncident(EventType eventType, Iterable<Long> riskIncident) {
        List<RiskIncident> items = getRiskIncidentService().findAll(riskIncident);
        Set<RiskIncident> currents = eventType.getRiskIncident();
        Set<RiskIncident> toRemove = new HashSet<RiskIncident>();
        for (Iterator<RiskIncident> iterator = currents.iterator(); iterator.hasNext(); ) {
            RiskIncident nextRiskIncident = iterator.next();
            if (items.contains(nextRiskIncident)) {
                items.remove(nextRiskIncident);
            } else {
                toRemove.add(nextRiskIncident);
            }
        }
        eventType.removeFromRiskIncident(toRemove);
        eventType.addToRiskIncident(items);
        return getEventTypeRepository().save(eventType);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param eventType
     */
    @Transactional
    public void delete(EventType eventType) {
        // Clear bidirectional one-to-many parent relationship with RiskIncident
        for (RiskIncident item : eventType.getRiskIncident()) {
            item.setEventType(null);
        }
        getEventTypeRepository().delete(eventType);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param entities
     * @return List
     */
    @Transactional
    public List<EventType> save(Iterable<EventType> entities) {
        return getEventTypeRepository().save(entities);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param ids
     */
    @Transactional
    public void delete(Iterable<Long> ids) {
        List<EventType> toDelete = getEventTypeRepository().findAll(ids);
        getEventTypeRepository().deleteInBatch(toDelete);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param entity
     * @return EventType
     */
    @Transactional
    public EventType save(EventType entity) {
        return getEventTypeRepository().save(entity);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param id
     * @return EventType
     */
    public EventType findOne(Long id) {
        return getEventTypeRepository().findOne(id);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param id
     * @return EventType
     */
    public EventType findOneForUpdate(Long id) {
        return getEventTypeRepository().findOneDetached(id);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param ids
     * @return List
     */
    public List<EventType> findAll(Iterable<Long> ids) {
        return getEventTypeRepository().findAll(ids);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return List
     */
    public List<EventType> findAll() {
        return getEventTypeRepository().findAll();
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return Long
     */
    public long count() {
        return getEventTypeRepository().count();
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public Page<EventType> findAll(GlobalSearch globalSearch, Pageable pageable) {
        return getEventTypeRepository().findAll(globalSearch, pageable);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param ids
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public Page<EventType> findAllByIdsIn(List<Long> ids, GlobalSearch globalSearch, Pageable pageable) {
        return getEventTypeRepository().findAllByIdsIn(ids, globalSearch, pageable);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return Class
     */
    public Class<EventType> getEntityType() {
        return EventType.class;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return Class
     */
    public Class<Long> getIdType() {
        return Long.class;
    }
}
