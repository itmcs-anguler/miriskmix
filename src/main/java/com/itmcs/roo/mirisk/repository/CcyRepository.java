package com.itmcs.roo.mirisk.repository;
import com.itmcs.roo.mirisk.dta.Ccy;
import io.springlets.data.jpa.repository.DetachableJpaRepository;
import org.springframework.roo.addon.layers.repository.jpa.annotations.RooJpaRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * = CcyRepository
 TODO Auto-generated class documentation
 *
 */
@RooJpaRepository(entity = Ccy.class)
@Transactional(readOnly = true)
public interface CcyRepository extends DetachableJpaRepository<Ccy, Long>, CcyRepositoryCustom {
}
