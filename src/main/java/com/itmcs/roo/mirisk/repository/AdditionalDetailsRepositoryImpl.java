package com.itmcs.roo.mirisk.repository;
import io.springlets.data.jpa.repository.support.QueryDslRepositorySupportExt;
import org.springframework.roo.addon.layers.repository.jpa.annotations.RooJpaRepositoryCustomImpl;
import com.itmcs.roo.mirisk.dta.AdditionalDetails;
import com.itmcs.roo.mirisk.dta.QAdditionalDetails;
import com.querydsl.core.types.Path;
import com.querydsl.jpa.JPQLQuery;
import io.springlets.data.domain.GlobalSearch;
import io.springlets.data.jpa.repository.support.QueryDslRepositorySupportExt.AttributeMappingBuilder;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

/**
 * = AdditionalDetailsRepositoryImpl
 TODO Auto-generated class documentation
 *
 */
@RooJpaRepositoryCustomImpl(repository = AdditionalDetailsRepositoryCustom.class)
@Transactional(readOnly = true)
public class AdditionalDetailsRepositoryImpl extends QueryDslRepositorySupportExt<AdditionalDetails> implements AdditionalDetailsRepositoryCustom {

    /**
     * Default constructor
     */
    AdditionalDetailsRepositoryImpl() {
        super(AdditionalDetails.class);
    }

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    public static final String DOCUMENT_TITLE = "documentTitle";

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    public static final String ATTACHMENT = "attachment";

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    public static final String DOCUMENT_OWNER = "documentOwner";

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    public static final String UNIQUE_ID = "uniqueId";

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    public static final String FILE_NAME = "fileName";

    /**
     * TODO Auto-generated method documentation
     *
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public Page<AdditionalDetails> findAll(GlobalSearch globalSearch, Pageable pageable) {
        QAdditionalDetails additionalDetails = QAdditionalDetails.additionalDetails;
        JPQLQuery<AdditionalDetails> query = from(additionalDetails);
        Path<?>[] paths = new Path<?>[] { additionalDetails.fileName, additionalDetails.documentTitle, additionalDetails.documentOwner, additionalDetails.attachment, additionalDetails.uniqueId };
        applyGlobalSearch(globalSearch, query, paths);
        AttributeMappingBuilder mapping = buildMapper().map(FILE_NAME, additionalDetails.fileName).map(DOCUMENT_TITLE, additionalDetails.documentTitle).map(DOCUMENT_OWNER, additionalDetails.documentOwner).map(ATTACHMENT, additionalDetails.attachment).map(UNIQUE_ID, additionalDetails.uniqueId);
        applyPagination(pageable, query, mapping);
        applyOrderById(query);
        return loadPage(query, pageable, additionalDetails);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param ids
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public Page<AdditionalDetails> findAllByIdsIn(List<Long> ids, GlobalSearch globalSearch, Pageable pageable) {
        QAdditionalDetails additionalDetails = QAdditionalDetails.additionalDetails;
        JPQLQuery<AdditionalDetails> query = from(additionalDetails);
        Path<?>[] paths = new Path<?>[] { additionalDetails.fileName, additionalDetails.documentTitle, additionalDetails.documentOwner, additionalDetails.attachment, additionalDetails.uniqueId };
        applyGlobalSearch(globalSearch, query, paths);
        // Also, filter by the provided ids
        query.where(additionalDetails.id.in(ids));
        AttributeMappingBuilder mapping = buildMapper().map(FILE_NAME, additionalDetails.fileName).map(DOCUMENT_TITLE, additionalDetails.documentTitle).map(DOCUMENT_OWNER, additionalDetails.documentOwner).map(ATTACHMENT, additionalDetails.attachment).map(UNIQUE_ID, additionalDetails.uniqueId);
        applyPagination(pageable, query, mapping);
        applyOrderById(query);
        return loadPage(query, pageable, additionalDetails);
    }
}
