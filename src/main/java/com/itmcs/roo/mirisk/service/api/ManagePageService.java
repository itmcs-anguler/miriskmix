package com.itmcs.roo.mirisk.service.api;
import com.itmcs.roo.mirisk.dta.ManagePage;
import io.springlets.data.web.validation.ValidatorService;
import io.springlets.format.EntityResolver;
import org.springframework.roo.addon.layers.service.annotations.RooService;
import io.springlets.data.domain.GlobalSearch;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * = ManagePageService
 TODO Auto-generated class documentation
 *
 */
@RooService(entity = ManagePage.class)
public interface ManagePageService extends EntityResolver<ManagePage, Long>, ValidatorService<ManagePage> {

    /**
     * TODO Auto-generated method documentation
     *
     * @param id
     * @return ManagePage
     */
    public abstract ManagePage findOne(Long id);

    /**
     * TODO Auto-generated method documentation
     *
     * @param managePage
     */
    public abstract void delete(ManagePage managePage);

    /**
     * TODO Auto-generated method documentation
     *
     * @param entities
     * @return List
     */
    public abstract List<ManagePage> save(Iterable<ManagePage> entities);

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
     * @return ManagePage
     */
    public abstract ManagePage save(ManagePage entity);

    /**
     * TODO Auto-generated method documentation
     *
     * @param id
     * @return ManagePage
     */
    public abstract ManagePage findOneForUpdate(Long id);

    /**
     * TODO Auto-generated method documentation
     *
     * @param ids
     * @return List
     */
    public abstract List<ManagePage> findAll(Iterable<Long> ids);

    /**
     * TODO Auto-generated method documentation
     *
     * @return List
     */
    public abstract List<ManagePage> findAll();

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
    public abstract Page<ManagePage> findAll(GlobalSearch globalSearch, Pageable pageable);

    /**
     * TODO Auto-generated method documentation
     *
     * @param ids
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public abstract Page<ManagePage> findAllByIdsIn(List<Long> ids, GlobalSearch globalSearch, Pageable pageable);

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
