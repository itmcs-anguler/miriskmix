package com.itmcs.roo.mirisk.repository;
import com.itmcs.roo.mirisk.dta.ResidualImpactAssessment;
import io.springlets.data.jpa.repository.DetachableJpaRepository;
import org.springframework.roo.addon.layers.repository.jpa.annotations.RooJpaRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * = ResidualImpactAssessmentRepository
 TODO Auto-generated class documentation
 *
 */
@RooJpaRepository(entity = ResidualImpactAssessment.class)
@Transactional(readOnly = true)
public interface ResidualImpactAssessmentRepository extends DetachableJpaRepository<ResidualImpactAssessment, Long>, ResidualImpactAssessmentRepositoryCustom {
}
