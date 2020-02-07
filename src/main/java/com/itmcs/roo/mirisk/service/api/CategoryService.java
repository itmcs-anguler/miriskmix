package com.itmcs.roo.mirisk.service.api;
import com.itmcs.roo.mirisk.dta.Category;
import io.springlets.data.web.validation.ValidatorService;
import io.springlets.format.EntityResolver;
import org.springframework.roo.addon.layers.service.annotations.RooService;
import io.springlets.data.domain.GlobalSearch;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * = CategoryService
 TODO Auto-generated class documentation
 *
 */
@RooService(entity = Category.class)
public interface CategoryService extends EntityResolver<Category, Long>, ValidatorService<Category> {

    /**
     * TODO Auto-generated method documentation
     *
     * @param id
     * @return Category
     */
    public abstract Category findOne(Long id);

    /**
     * TODO Auto-generated method documentation
     *
     * @param category
     */
    public abstract void delete(Category category);

    /**
     * TODO Auto-generated method documentation
     *
     * @param entities
     * @return List
     */
    public abstract List<Category> save(Iterable<Category> entities);

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
     * @return Category
     */
    public abstract Category save(Category entity);

    /**
     * TODO Auto-generated method documentation
     *
     * @param id
     * @return Category
     */
    public abstract Category findOneForUpdate(Long id);

    /**
     * TODO Auto-generated method documentation
     *
     * @param ids
     * @return List
     */
    public abstract List<Category> findAll(Iterable<Long> ids);

    /**
     * TODO Auto-generated method documentation
     *
     * @return List
     */
    public abstract List<Category> findAll();

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
    public abstract Page<Category> findAll(GlobalSearch globalSearch, Pageable pageable);

    /**
     * TODO Auto-generated method documentation
     *
     * @param ids
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public abstract Page<Category> findAllByIdsIn(List<Long> ids, GlobalSearch globalSearch, Pageable pageable);

    /**
     * TODO Auto-generated method documentation
     *
     * @param category
     * @param riskRegistersToAdd
     * @return Category
     */
    public abstract Category addToRiskRegisters(Category category, Iterable<Long> riskRegistersToAdd);

    /**
     * TODO Auto-generated method documentation
     *
     * @param category
     * @param riskRegistersToRemove
     * @return Category
     */
    public abstract Category removeFromRiskRegisters(Category category, Iterable<Long> riskRegistersToRemove);

    /**
     * TODO Auto-generated method documentation
     *
     * @param category
     * @param riskRegisters
     * @return Category
     */
    public abstract Category setRiskRegisters(Category category, Iterable<Long> riskRegisters);
}
