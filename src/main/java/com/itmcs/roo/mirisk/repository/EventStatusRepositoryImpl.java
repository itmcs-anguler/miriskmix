package com.itmcs.roo.mirisk.repository;
import io.springlets.data.jpa.repository.support.QueryDslRepositorySupportExt;
import org.springframework.roo.addon.layers.repository.jpa.annotations.RooJpaRepositoryCustomImpl;
import com.itmcs.roo.mirisk.dta.EventStatus;
import com.itmcs.roo.mirisk.dta.QEventStatus;
import com.querydsl.core.types.Path;
import com.querydsl.jpa.JPQLQuery;
import io.springlets.data.domain.GlobalSearch;
import io.springlets.data.jpa.repository.support.QueryDslRepositorySupportExt.AttributeMappingBuilder;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

/**
 * = EventStatusRepositoryImpl
 TODO Auto-generated class documentation
 *
 */
@RooJpaRepositoryCustomImpl(repository = EventStatusRepositoryCustom.class)
@Transactional(readOnly = true)
public class EventStatusRepositoryImpl extends QueryDslRepositorySupportExt<EventStatus> implements EventStatusRepositoryCustom {

    /**
     * Default constructor
     */
    EventStatusRepositoryImpl() {
        super(EventStatus.class);
    }

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    public static final String EVENT_STATUS_DESCRIPTION = "eventStatusDescription";

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    public static final String EVENT_STATUS_NAME = "eventStatusName";

    /**
     * TODO Auto-generated method documentation
     *
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public Page<EventStatus> findAll(GlobalSearch globalSearch, Pageable pageable) {
        QEventStatus eventStatus = QEventStatus.eventStatus;
        JPQLQuery<EventStatus> query = from(eventStatus);
        Path<?>[] paths = new Path<?>[] { eventStatus.eventStatusName, eventStatus.eventStatusDescription };
        applyGlobalSearch(globalSearch, query, paths);
        AttributeMappingBuilder mapping = buildMapper().map(EVENT_STATUS_NAME, eventStatus.eventStatusName).map(EVENT_STATUS_DESCRIPTION, eventStatus.eventStatusDescription);
        applyPagination(pageable, query, mapping);
        applyOrderById(query);
        return loadPage(query, pageable, eventStatus);
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
        QEventStatus eventStatus = QEventStatus.eventStatus;
        JPQLQuery<EventStatus> query = from(eventStatus);
        Path<?>[] paths = new Path<?>[] { eventStatus.eventStatusName, eventStatus.eventStatusDescription };
        applyGlobalSearch(globalSearch, query, paths);
        // Also, filter by the provided ids
        query.where(eventStatus.id.in(ids));
        AttributeMappingBuilder mapping = buildMapper().map(EVENT_STATUS_NAME, eventStatus.eventStatusName).map(EVENT_STATUS_DESCRIPTION, eventStatus.eventStatusDescription);
        applyPagination(pageable, query, mapping);
        applyOrderById(query);
        return loadPage(query, pageable, eventStatus);
    }
}
