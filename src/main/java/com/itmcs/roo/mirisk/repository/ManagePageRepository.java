package com.itmcs.roo.mirisk.repository;
import com.itmcs.roo.mirisk.dta.ManagePage;
import io.springlets.data.jpa.repository.DetachableJpaRepository;
import org.springframework.roo.addon.layers.repository.jpa.annotations.RooJpaRepository;
import org.springframework.roo.addon.layers.repository.jpa.annotations.finder.RooFinder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

/**
 * = ManagePageRepository
 TODO Auto-generated class documentation
 *
 */
@RooJpaRepository(entity = ManagePage.class, finders = { @RooFinder(value = "findByPageName", returnType = ManagePage.class) })
@Transactional(readOnly = true)
public interface ManagePageRepository extends DetachableJpaRepository<ManagePage, Long>, ManagePageRepositoryCustom {

    /**
     * TODO Auto-generated method documentation
     *
     * @param pageName
     * @param pageable
     * @return Page
     */
    public abstract Page<ManagePage> findByPageName(String pageName, Pageable pageable);

    /**
     * TODO Auto-generated method documentation
     *
     * @param pageName
     * @return Long
     */
    public abstract long countByPageName(String pageName);
}
