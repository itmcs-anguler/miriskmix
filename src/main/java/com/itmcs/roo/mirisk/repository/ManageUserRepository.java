package com.itmcs.roo.mirisk.repository;
import com.itmcs.roo.mirisk.dta.ManageUser;
import io.springlets.data.jpa.repository.DetachableJpaRepository;
import org.springframework.roo.addon.layers.repository.jpa.annotations.RooJpaRepository;
import com.itmcs.roo.mirisk.dta.ManageRole;
import org.springframework.transaction.annotation.Transactional;

/**
 * = ManageUserRepository
 TODO Auto-generated class documentation
 *
 */
@RooJpaRepository(entity = ManageUser.class)
@Transactional(readOnly = true)
public interface ManageUserRepository extends DetachableJpaRepository<ManageUser, Long>, ManageUserRepositoryCustom {

    /**
     * TODO Auto-generated method documentation
     *
     * @param manageroles
     * @return Long
     */
    public abstract long countByManageroles(ManageRole manageroles);

    public abstract ManageUser findByUserName(String username);
}
