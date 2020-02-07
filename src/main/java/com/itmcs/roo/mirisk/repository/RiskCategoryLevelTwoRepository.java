package com.itmcs.roo.mirisk.repository;
import com.itmcs.roo.mirisk.dta.RiskCategoryLevelTwo;
import io.springlets.data.jpa.repository.DetachableJpaRepository;
import org.springframework.roo.addon.layers.repository.jpa.annotations.RooJpaRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * = RiskCategoryLevelTwoRepository
 TODO Auto-generated class documentation
 *
 */
@RooJpaRepository(entity = RiskCategoryLevelTwo.class)
@Transactional(readOnly = true)
public interface RiskCategoryLevelTwoRepository extends DetachableJpaRepository<RiskCategoryLevelTwo, Long>, RiskCategoryLevelTwoRepositoryCustom {
}
