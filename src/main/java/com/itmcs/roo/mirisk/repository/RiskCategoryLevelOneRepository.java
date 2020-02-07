package com.itmcs.roo.mirisk.repository;
import com.itmcs.roo.mirisk.dta.RiskCategoryLevelOne;
import io.springlets.data.jpa.repository.DetachableJpaRepository;
import org.springframework.roo.addon.layers.repository.jpa.annotations.RooJpaRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * = RiskCategoryLevelOneRepository
 TODO Auto-generated class documentation
 *
 */
@RooJpaRepository(entity = RiskCategoryLevelOne.class)
@Transactional(readOnly = true)
public interface RiskCategoryLevelOneRepository extends DetachableJpaRepository<RiskCategoryLevelOne, Long>, RiskCategoryLevelOneRepositoryCustom {
}
