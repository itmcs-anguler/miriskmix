package com.itmcs.roo.mirisk.repository;
import com.itmcs.roo.mirisk.dta.ManageRole;
import io.springlets.data.jpa.repository.DetachableJpaRepository;
import org.springframework.roo.addon.layers.repository.jpa.annotations.RooJpaRepository;
import org.springframework.roo.addon.layers.repository.jpa.annotations.finder.RooFinder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

/**
 * = ManageRoleRepository
 TODO Auto-generated class documentation
 *
 */
@RooJpaRepository(entity = ManageRole.class, finders = { @RooFinder(value = "findByRoleName", returnType = ManageRole.class) })
@Transactional(readOnly = true)
public interface ManageRoleRepository extends DetachableJpaRepository<ManageRole, Long>, ManageRoleRepositoryCustom {

    /**
     * TODO Auto-generated method documentation
     *
     * @param roleName
     * @param pageable
     * @return Page
     */
    public abstract Page<ManageRole> findByRoleName(String roleName, Pageable pageable);

    /**
     * TODO Auto-generated method documentation
     *
     * @param roleName
     * @return Long
     */
    public abstract long countByRoleName(String roleName);
}
