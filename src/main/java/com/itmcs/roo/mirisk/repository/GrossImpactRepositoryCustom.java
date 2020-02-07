package com.itmcs.roo.mirisk.repository;
import com.itmcs.roo.mirisk.dta.GrossImpact;
import org.springframework.roo.addon.layers.repository.jpa.annotations.RooJpaRepositoryCustom;
import com.itmcs.roo.mirisk.dta.Ccy;
import io.springlets.data.domain.GlobalSearch;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * = GrossImpactRepositoryCustom
 TODO Auto-generated class documentation
 *
 */
@RooJpaRepositoryCustom(entity = GrossImpact.class)
public interface GrossImpactRepositoryCustom {

    /**
     * TODO Auto-generated method documentation
     *
     * @param ccy
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public abstract Page<GrossImpact> findByCcy(Ccy ccy, GlobalSearch globalSearch, Pageable pageable);

    /**
     * TODO Auto-generated method documentation
     *
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public abstract Page<GrossImpact> findAll(GlobalSearch globalSearch, Pageable pageable);

    /**
     * TODO Auto-generated method documentation
     *
     * @param ids
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public abstract Page<GrossImpact> findAllByIdsIn(List<Long> ids, GlobalSearch globalSearch, Pageable pageable);
}
