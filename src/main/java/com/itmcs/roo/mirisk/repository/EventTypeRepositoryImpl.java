package com.itmcs.roo.mirisk.repository;
import io.springlets.data.jpa.repository.support.QueryDslRepositorySupportExt;
import org.springframework.roo.addon.layers.repository.jpa.annotations.RooJpaRepositoryCustomImpl;
import com.itmcs.roo.mirisk.dta.EventType;
import com.itmcs.roo.mirisk.dta.QEventType;
import com.querydsl.core.types.Path;
import com.querydsl.jpa.JPQLQuery;
import io.springlets.data.domain.GlobalSearch;
import io.springlets.data.jpa.repository.support.QueryDslRepositorySupportExt.AttributeMappingBuilder;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

/**
 * = EventTypeRepositoryImpl
 TODO Auto-generated class documentation
 *
 */
@RooJpaRepositoryCustomImpl(repository = EventTypeRepositoryCustom.class)
@Transactional(readOnly = true)
public class EventTypeRepositoryImpl extends QueryDslRepositorySupportExt<EventType> implements EventTypeRepositoryCustom {

    /**
     * Default constructor
     */
    EventTypeRepositoryImpl() {
        super(EventType.class);
    }

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    public static final String EVENT_TYPE_NAME = "eventTypeName";

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    public static final String EVENT_TYPE_DESCRIPTION = "eventTypeDescription";

    /**
     * TODO Auto-generated method documentation
     *
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public Page<EventType> findAll(GlobalSearch globalSearch, Pageable pageable) {
        QEventType eventType = QEventType.eventType;
        JPQLQuery<EventType> query = from(eventType);
        Path<?>[] paths = new Path<?>[] { eventType.eventTypeName, eventType.eventTypeDescription };
        applyGlobalSearch(globalSearch, query, paths);
        AttributeMappingBuilder mapping = buildMapper().map(EVENT_TYPE_NAME, eventType.eventTypeName).map(EVENT_TYPE_DESCRIPTION, eventType.eventTypeDescription);
        applyPagination(pageable, query, mapping);
        applyOrderById(query);
        return loadPage(query, pageable, eventType);
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
        QEventType eventType = QEventType.eventType;
        JPQLQuery<EventType> query = from(eventType);
        Path<?>[] paths = new Path<?>[] { eventType.eventTypeName, eventType.eventTypeDescription };
        applyGlobalSearch(globalSearch, query, paths);
        // Also, filter by the provided ids
        query.where(eventType.id.in(ids));
        AttributeMappingBuilder mapping = buildMapper().map(EVENT_TYPE_NAME, eventType.eventTypeName).map(EVENT_TYPE_DESCRIPTION, eventType.eventTypeDescription);
        applyPagination(pageable, query, mapping);
        applyOrderById(query);
        return loadPage(query, pageable, eventType);
    }
}
