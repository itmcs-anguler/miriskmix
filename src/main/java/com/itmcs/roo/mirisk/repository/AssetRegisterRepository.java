package com.itmcs.roo.mirisk.repository;
import com.itmcs.roo.mirisk.dta.AssetRegister;
import io.springlets.data.jpa.repository.DetachableJpaRepository;
import org.springframework.roo.addon.layers.repository.jpa.annotations.RooJpaRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * = AssetRegisterRepository
 TODO Auto-generated class documentation
 *
 */
@RooJpaRepository(entity = AssetRegister.class)
@Transactional(readOnly = true)
public interface AssetRegisterRepository extends DetachableJpaRepository<AssetRegister, Long>, AssetRegisterRepositoryCustom {
}
