package com.itmcs.roo.mirisk.repository;
import com.itmcs.roo.mirisk.dta.Recovery;
import io.springlets.data.jpa.repository.DetachableJpaRepository;
import org.springframework.roo.addon.layers.repository.jpa.annotations.RooJpaRepository;
import com.itmcs.roo.mirisk.dta.Ccy;
import org.springframework.transaction.annotation.Transactional;

/**
 * = RecoveryRepository
 TODO Auto-generated class documentation
 *
 */
@RooJpaRepository(entity = Recovery.class)
@Transactional(readOnly = true)
public interface RecoveryRepository extends DetachableJpaRepository<Recovery, Long>, RecoveryRepositoryCustom {

    /**
     * TODO Auto-generated method documentation
     *
     * @param ccy
     * @return Long
     */
    public abstract long countByCcy(Ccy ccy);
}
