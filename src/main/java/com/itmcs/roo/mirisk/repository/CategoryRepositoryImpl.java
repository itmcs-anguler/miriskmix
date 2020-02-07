package com.itmcs.roo.mirisk.repository;
import io.springlets.data.jpa.repository.support.QueryDslRepositorySupportExt;
import org.springframework.roo.addon.layers.repository.jpa.annotations.RooJpaRepositoryCustomImpl;
import com.itmcs.roo.mirisk.dta.Category;
import com.itmcs.roo.mirisk.dta.QCategory;
import com.querydsl.core.types.Path;
import com.querydsl.jpa.JPQLQuery;
import io.springlets.data.domain.GlobalSearch;
import io.springlets.data.jpa.repository.support.QueryDslRepositorySupportExt.AttributeMappingBuilder;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

/**
 * = CategoryRepositoryImpl
 TODO Auto-generated class documentation
 *
 */
@RooJpaRepositoryCustomImpl(repository = CategoryRepositoryCustom.class)
@Transactional(readOnly = true)
public class CategoryRepositoryImpl extends QueryDslRepositorySupportExt<Category> implements CategoryRepositoryCustom {

    /**
     * Default constructor
     */
    CategoryRepositoryImpl() {
        super(Category.class);
    }

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    public static final String CATEGORY_DESCRIPTION = "categoryDescription";

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    public static final String CATEGORY_NAME = "categoryName";

    /**
     * TODO Auto-generated method documentation
     *
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public Page<Category> findAll(GlobalSearch globalSearch, Pageable pageable) {
        QCategory category = QCategory.category;
        JPQLQuery<Category> query = from(category);
        Path<?>[] paths = new Path<?>[] { category.categoryName, category.categoryDescription };
        applyGlobalSearch(globalSearch, query, paths);
        AttributeMappingBuilder mapping = buildMapper().map(CATEGORY_NAME, category.categoryName).map(CATEGORY_DESCRIPTION, category.categoryDescription);
        applyPagination(pageable, query, mapping);
        applyOrderById(query);
        return loadPage(query, pageable, category);
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
        QCategory category = QCategory.category;
        JPQLQuery<Category> query = from(category);
        Path<?>[] paths = new Path<?>[] { category.categoryName, category.categoryDescription };
        applyGlobalSearch(globalSearch, query, paths);
        // Also, filter by the provided ids
        query.where(category.id.in(ids));
        AttributeMappingBuilder mapping = buildMapper().map(CATEGORY_NAME, category.categoryName).map(CATEGORY_DESCRIPTION, category.categoryDescription);
        applyPagination(pageable, query, mapping);
        applyOrderById(query);
        return loadPage(query, pageable, category);
    }
}
