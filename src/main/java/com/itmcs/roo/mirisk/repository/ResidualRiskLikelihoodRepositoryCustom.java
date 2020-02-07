package com.itmcs.roo.mirisk.repository;
import com.itmcs.roo.mirisk.dta.ResidualRiskLikelihood;
import org.springframework.roo.addon.layers.repository.jpa.annotations.RooJpaRepositoryCustom;
import io.springlets.data.domain.GlobalSearch;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * = ResidualRiskLikelihoodRepositoryCustom
 TODO Auto-generated class documentation
 *
 */
@RooJpaRepositoryCustom(entity = ResidualRiskLikelihood.class)
public interface ResidualRiskLikelihoodRepositoryCustom {

    /**
     * TODO Auto-generated method documentation
     *
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public abstract Page<ResidualRiskLikelihood> findAll(GlobalSearch globalSearch, Pageable pageable);

    /**
     * TODO Auto-generated method documentation
     *
     * @param ids
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public abstract Page<ResidualRiskLikelihood> findAllByIdsIn(List<Long> ids, GlobalSearch globalSearch, Pageable pageable);
}
