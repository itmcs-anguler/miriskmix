package com.itmcs.roo.mirisk.repository;
import com.itmcs.roo.mirisk.dta.ResidualRiskLikelihood;
import io.springlets.data.jpa.repository.DetachableJpaRepository;
import org.springframework.roo.addon.layers.repository.jpa.annotations.RooJpaRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * = ResidualRiskLikelihoodRepository
 TODO Auto-generated class documentation
 *
 */
@RooJpaRepository(entity = ResidualRiskLikelihood.class)
@Transactional(readOnly = true)
public interface ResidualRiskLikelihoodRepository extends DetachableJpaRepository<ResidualRiskLikelihood, Long>, ResidualRiskLikelihoodRepositoryCustom {
}
