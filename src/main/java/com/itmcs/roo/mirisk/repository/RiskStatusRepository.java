package com.itmcs.roo.mirisk.repository;
import com.itmcs.roo.mirisk.dta.RiskStatus;
import io.springlets.data.jpa.repository.DetachableJpaRepository;
import org.springframework.roo.addon.layers.repository.jpa.annotations.RooJpaRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * = RiskStatusRepository
 TODO Auto-generated class documentation
 *
 */
@RooJpaRepository(entity = RiskStatus.class)
@Transactional(readOnly = true)
public interface RiskStatusRepository extends DetachableJpaRepository<RiskStatus, Long>, RiskStatusRepositoryCustom {
}
