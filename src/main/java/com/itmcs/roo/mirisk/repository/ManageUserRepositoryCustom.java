package com.itmcs.roo.mirisk.repository;
import com.itmcs.roo.mirisk.dta.ManageUser;
import org.springframework.roo.addon.layers.repository.jpa.annotations.RooJpaRepositoryCustom;
import com.itmcs.roo.mirisk.dta.ManageRole;
import io.springlets.data.domain.GlobalSearch;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * = ManageUserRepositoryCustom
 TODO Auto-generated class documentation
 *
 */
@RooJpaRepositoryCustom(entity = ManageUser.class)
public interface ManageUserRepositoryCustom {

    /**
     * TODO Auto-generated method documentation
     *
     * @param manageroles
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public abstract Page<ManageUser> findByManageroles(ManageRole manageroles, GlobalSearch globalSearch, Pageable pageable);

    /**
     * TODO Auto-generated method documentation
     *
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public abstract Page<ManageUser> findAll(GlobalSearch globalSearch, Pageable pageable);

    /**
     * TODO Auto-generated method documentation
     *
     * @param ids
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public abstract Page<ManageUser> findAllByIdsIn(List<Long> ids, GlobalSearch globalSearch, Pageable pageable);
}
