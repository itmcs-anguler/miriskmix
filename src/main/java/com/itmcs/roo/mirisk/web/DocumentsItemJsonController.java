package com.itmcs.roo.mirisk.web;
import com.itmcs.roo.mirisk.dta.Document;
import org.springframework.roo.addon.web.mvc.controller.annotations.ControllerType;
import org.springframework.roo.addon.web.mvc.controller.annotations.RooController;
import org.springframework.roo.addon.web.mvc.controller.annotations.responses.json.RooJSON;
import org.springframework.web.bind.annotation.CrossOrigin;
import com.itmcs.roo.mirisk.service.api.DocumentService;
import io.springlets.web.NotFoundException;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.util.UriComponents;

/**
 * = DocumentsItemJsonController
 TODO Auto-generated class documentation
 *
 */
@CrossOrigin
@RooController(entity = Document.class, pathPrefix = "/mirisk", type = ControllerType.ITEM)
@RooJSON
@RestController
@RequestMapping(value = "/mirisk/documents/{document}", name = "DocumentsItemJsonController", produces = MediaType.APPLICATION_JSON_VALUE)
public class DocumentsItemJsonController {

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private DocumentService documentService;

    /**
     * TODO Auto-generated constructor documentation
     *
     * @param documentService
     */
    @Autowired
    public DocumentsItemJsonController(DocumentService documentService) {
        this.documentService = documentService;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return DocumentService
     */
    public DocumentService getDocumentService() {
        return documentService;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param documentService
     */
    public void setDocumentService(DocumentService documentService) {
        this.documentService = documentService;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param id
     * @return Document
     */
    @ModelAttribute
    public Document getDocument(@PathVariable("document") Long id) {
        Document document = documentService.findOne(id);
        if (document == null) {
            throw new NotFoundException(String.format("Document with identifier '%s' not found", id));
        }
        return document;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param document
     * @return ResponseEntity
     */
    @GetMapping(name = "show")
    public ResponseEntity<?> show(@ModelAttribute Document document) {
        return ResponseEntity.ok(document);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param document
     * @return UriComponents
     */
    public static UriComponents showURI(Document document) {
        return MvcUriComponentsBuilder.fromMethodCall(MvcUriComponentsBuilder.on(DocumentsItemJsonController.class).show(document)).buildAndExpand(document.getId()).encode();
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param storedDocument
     * @param document
     * @param result
     * @return ResponseEntity
     */
    @PutMapping(name = "update")
    public ResponseEntity<?> update(@ModelAttribute Document storedDocument, @Valid @RequestBody Document document, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(result);
        }
        document.setId(storedDocument.getId());
        getDocumentService().save(document);
        return ResponseEntity.ok().build();
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param document
     * @return ResponseEntity
     */
    @DeleteMapping(name = "delete")
    public ResponseEntity<?> delete(@ModelAttribute Document document) {
        getDocumentService().delete(document);
        return ResponseEntity.ok().build();
    }
}
