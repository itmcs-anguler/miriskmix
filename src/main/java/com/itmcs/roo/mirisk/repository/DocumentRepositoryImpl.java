package com.itmcs.roo.mirisk.repository;
import io.springlets.data.jpa.repository.support.QueryDslRepositorySupportExt;
import org.springframework.roo.addon.layers.repository.jpa.annotations.RooJpaRepositoryCustomImpl;
import com.itmcs.roo.mirisk.dta.Document;
import com.itmcs.roo.mirisk.dta.QDocument;
import com.querydsl.core.types.Path;
import com.querydsl.jpa.JPQLQuery;
import io.springlets.data.domain.GlobalSearch;
import io.springlets.data.jpa.repository.support.QueryDslRepositorySupportExt.AttributeMappingBuilder;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

/**
 * = DocumentRepositoryImpl
 TODO Auto-generated class documentation
 *
 */
@RooJpaRepositoryCustomImpl(repository = DocumentRepositoryCustom.class)
@Transactional(readOnly = true)
public class DocumentRepositoryImpl extends QueryDslRepositorySupportExt<Document> implements DocumentRepositoryCustom {

    /**
     * Default constructor
     */
    DocumentRepositoryImpl() {
        super(Document.class);
    }

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    public static final String DOCUMENT_NAME = "documentName";

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    public static final String OWNER = "owner";

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    public static final String NEXT_REVIEW_DATE = "nextReviewDate";

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    public static final String ATTACHMENT = "attachment";

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    public static final String STATUS = "status";

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    public static final String UNIQUE_ID = "uniqueId";

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    public static final String DOCUMENT_UPLOAD_DATE_TIME = "documentUploadDateTime";

    /**
     * TODO Auto-generated method documentation
     *
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public Page<Document> findAll(GlobalSearch globalSearch, Pageable pageable) {
        QDocument document = QDocument.document;
        JPQLQuery<Document> query = from(document);
        Path<?>[] paths = new Path<?>[] { document.documentName, document.owner, document.nextReviewDate, document.attachment, document.documentUploadDateTime, document.status, document.uniqueId };
        applyGlobalSearch(globalSearch, query, paths);
        AttributeMappingBuilder mapping = buildMapper().map(DOCUMENT_NAME, document.documentName).map(OWNER, document.owner).map(NEXT_REVIEW_DATE, document.nextReviewDate).map(ATTACHMENT, document.attachment).map(DOCUMENT_UPLOAD_DATE_TIME, document.documentUploadDateTime).map(STATUS, document.status).map(UNIQUE_ID, document.uniqueId);
        applyPagination(pageable, query, mapping);
        applyOrderById(query);
        return loadPage(query, pageable, document);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param ids
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public Page<Document> findAllByIdsIn(List<Long> ids, GlobalSearch globalSearch, Pageable pageable) {
        QDocument document = QDocument.document;
        JPQLQuery<Document> query = from(document);
        Path<?>[] paths = new Path<?>[] { document.documentName, document.owner, document.nextReviewDate, document.attachment, document.documentUploadDateTime, document.status, document.uniqueId };
        applyGlobalSearch(globalSearch, query, paths);
        // Also, filter by the provided ids
        query.where(document.id.in(ids));
        AttributeMappingBuilder mapping = buildMapper().map(DOCUMENT_NAME, document.documentName).map(OWNER, document.owner).map(NEXT_REVIEW_DATE, document.nextReviewDate).map(ATTACHMENT, document.attachment).map(DOCUMENT_UPLOAD_DATE_TIME, document.documentUploadDateTime).map(STATUS, document.status).map(UNIQUE_ID, document.uniqueId);
        applyPagination(pageable, query, mapping);
        applyOrderById(query);
        return loadPage(query, pageable, document);
    }
}
