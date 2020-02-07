package com.itmcs.roo.mirisk.service.impl;
import com.itmcs.roo.mirisk.service.api.CategoryService;
import org.springframework.roo.addon.layers.service.annotations.RooServiceImpl;
import com.itmcs.roo.mirisk.dta.Category;
import com.itmcs.roo.mirisk.dta.RiskRegister;
import com.itmcs.roo.mirisk.repository.CategoryRepository;
import com.itmcs.roo.mirisk.service.api.RiskRegisterService;
import io.springlets.data.domain.GlobalSearch;
import io.springlets.data.web.validation.MessageI18n;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * = CategoryServiceImpl
 TODO Auto-generated class documentation
 *
 */
@RooServiceImpl(service = CategoryService.class)
@Service
@Transactional(readOnly = true)
public class CategoryServiceImpl implements CategoryService {

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private CategoryRepository categoryRepository;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private RiskRegisterService riskRegisterService;

    /**
     * TODO Auto-generated constructor documentation
     *
     * @param categoryRepository
     * @param riskRegisterService
     */
    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, @Lazy RiskRegisterService riskRegisterService) {
        setCategoryRepository(categoryRepository);
        setRiskRegisterService(riskRegisterService);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return CategoryRepository
     */
    public CategoryRepository getCategoryRepository() {
        return categoryRepository;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param categoryRepository
     */
    public void setCategoryRepository(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return RiskRegisterService
     */
    public RiskRegisterService getRiskRegisterService() {
        return riskRegisterService;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param riskRegisterService
     */
    public void setRiskRegisterService(RiskRegisterService riskRegisterService) {
        this.riskRegisterService = riskRegisterService;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param category
     * @return Map
     */
    public Map<String, List<MessageI18n>> validate(Category category) {
        Map<String, List<MessageI18n>> messages = new java.util.HashMap<String, List<MessageI18n>>();
        // TODO: IMPLEMENT HERE THE VALIDATION OF YOUR ENTITY
        return messages;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param category
     * @param riskRegistersToAdd
     * @return Category
     */
    @Transactional
    public Category addToRiskRegisters(Category category, Iterable<Long> riskRegistersToAdd) {
        List<RiskRegister> riskRegisters = getRiskRegisterService().findAll(riskRegistersToAdd);
        category.addToRiskRegisters(riskRegisters);
        return getCategoryRepository().save(category);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param category
     * @param riskRegistersToRemove
     * @return Category
     */
    @Transactional
    public Category removeFromRiskRegisters(Category category, Iterable<Long> riskRegistersToRemove) {
        List<RiskRegister> riskRegisters = getRiskRegisterService().findAll(riskRegistersToRemove);
        category.removeFromRiskRegisters(riskRegisters);
        return getCategoryRepository().save(category);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param category
     * @param riskRegisters
     * @return Category
     */
    @Transactional
    public Category setRiskRegisters(Category category, Iterable<Long> riskRegisters) {
        List<RiskRegister> items = getRiskRegisterService().findAll(riskRegisters);
        Set<RiskRegister> currents = category.getRiskRegisters();
        Set<RiskRegister> toRemove = new HashSet<RiskRegister>();
        for (Iterator<RiskRegister> iterator = currents.iterator(); iterator.hasNext(); ) {
            RiskRegister nextRiskRegister = iterator.next();
            if (items.contains(nextRiskRegister)) {
                items.remove(nextRiskRegister);
            } else {
                toRemove.add(nextRiskRegister);
            }
        }
        category.removeFromRiskRegisters(toRemove);
        category.addToRiskRegisters(items);
        return getCategoryRepository().save(category);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param category
     */
    @Transactional
    public void delete(Category category) {
        // Clear bidirectional one-to-many parent relationship with RiskRegister
        for (RiskRegister item : category.getRiskRegisters()) {
            item.setCategory(null);
        }
        getCategoryRepository().delete(category);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param entities
     * @return List
     */
    @Transactional
    public List<Category> save(Iterable<Category> entities) {
        return getCategoryRepository().save(entities);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param ids
     */
    @Transactional
    public void delete(Iterable<Long> ids) {
        List<Category> toDelete = getCategoryRepository().findAll(ids);
        getCategoryRepository().deleteInBatch(toDelete);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param entity
     * @return Category
     */
    @Transactional
    public Category save(Category entity) {
        return getCategoryRepository().save(entity);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param id
     * @return Category
     */
    public Category findOne(Long id) {
        return getCategoryRepository().findOne(id);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param id
     * @return Category
     */
    public Category findOneForUpdate(Long id) {
        return getCategoryRepository().findOneDetached(id);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param ids
     * @return List
     */
    public List<Category> findAll(Iterable<Long> ids) {
        return getCategoryRepository().findAll(ids);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return List
     */
    public List<Category> findAll() {
        return getCategoryRepository().findAll();
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return Long
     */
    public long count() {
        return getCategoryRepository().count();
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public Page<Category> findAll(GlobalSearch globalSearch, Pageable pageable) {
        return getCategoryRepository().findAll(globalSearch, pageable);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param ids
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public Page<Category> findAllByIdsIn(List<Long> ids, GlobalSearch globalSearch, Pageable pageable) {
        return getCategoryRepository().findAllByIdsIn(ids, globalSearch, pageable);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return Class
     */
    public Class<Category> getEntityType() {
        return Category.class;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return Class
     */
    public Class<Long> getIdType() {
        return Long.class;
    }
}
