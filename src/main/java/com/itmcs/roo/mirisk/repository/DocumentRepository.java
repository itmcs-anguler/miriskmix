package com.itmcs.roo.mirisk.repository;
import com.itmcs.roo.mirisk.dta.Document;
import io.springlets.data.jpa.repository.DetachableJpaRepository;
import org.springframework.roo.addon.layers.repository.jpa.annotations.RooJpaRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * = DocumentRepository
 TODO Auto-generated class documentation
 *
 */
@RooJpaRepository(entity = Document.class)
@Transactional(readOnly = true)
public interface DocumentRepository extends DetachableJpaRepository<Document, Long>, DocumentRepositoryCustom {
}
