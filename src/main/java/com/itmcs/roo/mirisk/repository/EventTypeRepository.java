package com.itmcs.roo.mirisk.repository;
import com.itmcs.roo.mirisk.dta.EventType;
import io.springlets.data.jpa.repository.DetachableJpaRepository;
import org.springframework.roo.addon.layers.repository.jpa.annotations.RooJpaRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * = EventTypeRepository
 TODO Auto-generated class documentation
 *
 */
@RooJpaRepository(entity = EventType.class)
@Transactional(readOnly = true)
public interface EventTypeRepository extends DetachableJpaRepository<EventType, Long>, EventTypeRepositoryCustom {
}
