package com.itmcs.roo.mirisk.service.impl;
import com.itmcs.roo.mirisk.service.api.EventStatusService;
import org.springframework.roo.addon.layers.service.annotations.RooServiceImpl;
import com.itmcs.roo.mirisk.dta.EventStatus;
import com.itmcs.roo.mirisk.dta.RiskIncident;
import com.itmcs.roo.mirisk.repository.EventStatusRepository;
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
 * = EventStatusServiceImpl
 TODO Auto-generated class documentation
 *
 */
@RooServiceImpl(service = EventStatusService.class)
@Service
@Transactional(readOnly = true)
public class EventStatusServiceImpl implements EventStatusService {

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private EventStatusRepository eventStatusRepository;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private RiskIncidentService riskIncidentService;

    /**
     * TODO Auto-generated constructor documentation
     *
     * @param eventStatusRepository
     * @param riskIncidentService
     */
    @Autowired
    public EventStatusServiceImpl(EventStatusRepository eventStatusRepository, @Lazy RiskIncidentService riskIncidentService) {
        setEventStatusRepository(eventStatusRepository);
        setRiskIncidentService(riskIncidentService);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return EventStatusRepository
     */
    public EventStatusRepository getEventStatusRepository() {
        return eventStatusRepository;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param eventStatusRepository
     */
    public void setEventStatusRepository(EventStatusRepository eventStatusRepository) {
        this.eventStatusRepository = eventStatusRepository;
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
     * @param eventstatus
     * @return Map
     */
    public Map<String, List<MessageI18n>> validate(EventStatus eventstatus) {
        Map<String, List<MessageI18n>> messages = new java.util.HashMap<String, List<MessageI18n>>();
        // TODO: IMPLEMENT HERE THE VALIDATION OF YOUR ENTITY
        return messages;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param eventStatus
     * @param riskIncidentToAdd
     * @return EventStatus
     */
    @Transactional
    public EventStatus addToRiskIncident(EventStatus eventStatus, Iterable<Long> riskIncidentToAdd) {
        List<RiskIncident> riskIncident = getRiskIncidentService().findAll(riskIncidentToAdd);
        eventStatus.addToRiskIncident(riskIncident);
        return getEventStatusRepository().save(eventStatus);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param eventStatus
     * @param riskIncidentToRemove
     * @return EventStatus
     */
    @Transactional
    public EventStatus removeFromRiskIncident(EventStatus eventStatus, Iterable<Long> riskIncidentToRemove) {
        List<RiskIncident> riskIncident = getRiskIncidentService().findAll(riskIncidentToRemove);
        eventStatus.removeFromRiskIncident(riskIncident);
        return getEventStatusRepository().save(eventStatus);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param eventStatus
     * @param riskIncident
     * @return EventStatus
     */
    @Transactional
    public EventStatus setRiskIncident(EventStatus eventStatus, Iterable<Long> riskIncident) {
        List<RiskIncident> items = getRiskIncidentService().findAll(riskIncident);
        Set<RiskIncident> currents = eventStatus.getRiskIncident();
        Set<RiskIncident> toRemove = new HashSet<RiskIncident>();
        for (Iterator<RiskIncident> iterator = currents.iterator(); iterator.hasNext(); ) {
            RiskIncident nextRiskIncident = iterator.next();
            if (items.contains(nextRiskIncident)) {
                items.remove(nextRiskIncident);
            } else {
                toRemove.add(nextRiskIncident);
            }
        }
        eventStatus.removeFromRiskIncident(toRemove);
        eventStatus.addToRiskIncident(items);
        return getEventStatusRepository().save(eventStatus);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param eventStatus
     */
    @Transactional
    public void delete(EventStatus eventStatus) {
        // Clear bidirectional one-to-many parent relationship with RiskIncident
        for (RiskIncident item : eventStatus.getRiskIncident()) {
            item.setEventStatus(null);
        }
        getEventStatusRepository().delete(eventStatus);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param entities
     * @return List
     */
    @Transactional
    public List<EventStatus> save(Iterable<EventStatus> entities) {
        return getEventStatusRepository().save(entities);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param ids
     */
    @Transactional
    public void delete(Iterable<Long> ids) {
        List<EventStatus> toDelete = getEventStatusRepository().findAll(ids);
        getEventStatusRepository().deleteInBatch(toDelete);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param entity
     * @return EventStatus
     */
    @Transactional
    public EventStatus save(EventStatus entity) {
        return getEventStatusRepository().save(entity);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param id
     * @return EventStatus
     */
    public EventStatus findOne(Long id) {
        return getEventStatusRepository().findOne(id);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param id
     * @return EventStatus
     */
    public EventStatus findOneForUpdate(Long id) {
        return getEventStatusRepository().findOneDetached(id);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param ids
     * @return List
     */
    public List<EventStatus> findAll(Iterable<Long> ids) {
        return getEventStatusRepository().findAll(ids);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return List
     */
    public List<EventStatus> findAll() {
        return getEventStatusRepository().findAll();
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return Long
     */
    public long count() {
        return getEventStatusRepository().count();
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public Page<EventStatus> findAll(GlobalSearch globalSearch, Pageable pageable) {
        return getEventStatusRepository().findAll(globalSearch, pageable);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param ids
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public Page<EventStatus> findAllByIdsIn(List<Long> ids, GlobalSearch globalSearch, Pageable pageable) {
        return getEventStatusRepository().findAllByIdsIn(ids, globalSearch, pageable);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return Class
     */
    public Class<EventStatus> getEntityType() {
        return EventStatus.class;
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
