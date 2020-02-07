package com.itmcs.roo.mirisk.repository;
import com.itmcs.roo.mirisk.dta.EventStatus;
import io.springlets.data.jpa.repository.DetachableJpaRepository;
import org.springframework.roo.addon.layers.repository.jpa.annotations.RooJpaRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * = EventStatusRepository
 TODO Auto-generated class documentation
 *
 */
@RooJpaRepository(entity = EventStatus.class)
@Transactional(readOnly = true)
public interface EventStatusRepository extends DetachableJpaRepository<EventStatus, Long>, EventStatusRepositoryCustom {
}
