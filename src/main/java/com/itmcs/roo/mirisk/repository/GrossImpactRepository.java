package com.itmcs.roo.mirisk.repository;
import com.itmcs.roo.mirisk.dta.GrossImpact;
import io.springlets.data.jpa.repository.DetachableJpaRepository;
import org.springframework.roo.addon.layers.repository.jpa.annotations.RooJpaRepository;
import com.itmcs.roo.mirisk.dta.Ccy;
import org.springframework.transaction.annotation.Transactional;

/**
 * = GrossImpactRepository
 TODO Auto-generated class documentation
 *
 */
@RooJpaRepository(entity = GrossImpact.class)
@Transactional(readOnly = true)
public interface GrossImpactRepository extends DetachableJpaRepository<GrossImpact, Long>, GrossImpactRepositoryCustom {

    /**
     * TODO Auto-generated method documentation
     *
     * @param ccy
     * @return Long
     */
    public abstract long countByCcy(Ccy ccy);
}
