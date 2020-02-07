package com.itmcs.roo.mirisk.service.api;
import com.itmcs.roo.mirisk.dta.ManageUser;
import io.springlets.data.web.validation.ValidatorService;
import io.springlets.format.EntityResolver;
import org.springframework.roo.addon.layers.service.annotations.RooService;
import com.itmcs.roo.mirisk.dta.ManageRole;
import io.springlets.data.domain.GlobalSearch;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * = ManageUserService
 TODO Auto-generated class documentation
 *
 */
@RooService(entity = ManageUser.class)
public interface ManageUserService extends EntityResolver<ManageUser, Long>, ValidatorService<ManageUser> {

    /**
     * TODO Auto-generated method documentation
     *
     * @param id
     * @return ManageUser
     */
    public abstract ManageUser findOne(Long id);

    /**
     * TODO Auto-generated method documentation
     *
     * @param manageUser
     */
    public abstract void delete(ManageUser manageUser);

    /**
     * TODO Auto-generated method documentation
     *
     * @param entities
     * @return List
     */
    public abstract List<ManageUser> save(Iterable<ManageUser> entities);

    /**
     * TODO Auto-generated method documentation
     *
     * @param ids
     */
    public abstract void delete(Iterable<Long> ids);

    /**
     * TODO Auto-generated method documentation
     *
     * @param entity
     * @return ManageUser
     */
    public abstract ManageUser save(ManageUser entity);

    /**
     * TODO Auto-generated method documentation
     *
     * @param id
     * @return ManageUser
     */
    public abstract ManageUser findOneForUpdate(Long id);

    /**
     * TODO Auto-generated method documentation
     *
     * @param ids
     * @return List
     */
    public abstract List<ManageUser> findAll(Iterable<Long> ids);

    /**
     * TODO Auto-generated method documentation
     *
     * @return List
     */
    public abstract List<ManageUser> findAll();

    /**
     * TODO Auto-generated method documentation
     *
     * @return Long
     */
    public abstract long count();

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
     * @param manageroles
     * @return Long
     */
    public abstract long countByManageroles(ManageRole manageroles);

    public abstract ManageUser findByUserName(String username);
}
