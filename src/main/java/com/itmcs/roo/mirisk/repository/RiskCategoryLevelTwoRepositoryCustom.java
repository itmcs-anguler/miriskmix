package com.itmcs.roo.mirisk.repository;
import com.itmcs.roo.mirisk.dta.RiskCategoryLevelTwo;
import org.springframework.roo.addon.layers.repository.jpa.annotations.RooJpaRepositoryCustom;
import io.springlets.data.domain.GlobalSearch;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * = RiskCategoryLevelTwoRepositoryCustom
 TODO Auto-generated class documentation
 *
 */
@RooJpaRepositoryCustom(entity = RiskCategoryLevelTwo.class)
public interface RiskCategoryLevelTwoRepositoryCustom {

    /**
     * TODO Auto-generated method documentation
     *
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public abstract Page<RiskCategoryLevelTwo> findAll(GlobalSearch globalSearch, Pageable pageable);

    /**
     * TODO Auto-generated method documentation
     *
     * @param ids
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public abstract Page<RiskCategoryLevelTwo> findAllByIdsIn(List<Long> ids, GlobalSearch globalSearch, Pageable pageable);
}
