package com.itmcs.roo.mirisk.repository;
import com.itmcs.roo.mirisk.dta.AdditionalDetails;
import io.springlets.data.jpa.repository.DetachableJpaRepository;
import org.springframework.roo.addon.layers.repository.jpa.annotations.RooJpaRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * = AdditionalDetailsRepository
 TODO Auto-generated class documentation
 *
 */
@RooJpaRepository(entity = AdditionalDetails.class)
@Transactional(readOnly = true)
public interface AdditionalDetailsRepository extends DetachableJpaRepository<AdditionalDetails, Long>, AdditionalDetailsRepositoryCustom {
}
