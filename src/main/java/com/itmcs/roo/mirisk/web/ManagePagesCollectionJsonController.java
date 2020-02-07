package com.itmcs.roo.mirisk.web;
import com.itmcs.roo.mirisk.dta.ManagePage;
import org.springframework.roo.addon.web.mvc.controller.annotations.ControllerType;
import org.springframework.roo.addon.web.mvc.controller.annotations.RooController;
import org.springframework.roo.addon.web.mvc.controller.annotations.responses.json.RooJSON;
import org.springframework.web.bind.annotation.CrossOrigin;
import com.itmcs.roo.mirisk.service.api.ManagePageService;
import io.springlets.data.domain.GlobalSearch;
import java.util.Collection;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.util.UriComponents;

/**
 * = ManagePagesCollectionJsonController
 TODO Auto-generated class documentation
 *
 */
@CrossOrigin
@RooController(entity = ManagePage.class, pathPrefix = "/mirisk", type = ControllerType.COLLECTION)
@RooJSON
@RestController
@RequestMapping(value = "/mirisk/managepages", name = "ManagePagesCollectionJsonController", produces = MediaType.APPLICATION_JSON_VALUE)
public class ManagePagesCollectionJsonController {

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private ManagePageService managePageService;

    /**
     * TODO Auto-generated constructor documentation
     *
     * @param managePageService
     */
    @Autowired
    public ManagePagesCollectionJsonController(ManagePageService managePageService) {
        this.managePageService = managePageService;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return ManagePageService
     */
    public ManagePageService getManagePageService() {
        return managePageService;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param managePageService
     */
    public void setManagePageService(ManagePageService managePageService) {
        this.managePageService = managePageService;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param globalSearch
     * @param pageable
     * @return ResponseEntity
     */
    @GetMapping(name = "list")
    public ResponseEntity<Page<ManagePage>> list(GlobalSearch globalSearch, Pageable pageable) {
        Page<ManagePage> managePages = getManagePageService().findAll(globalSearch, pageable);
        return ResponseEntity.ok(managePages);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return UriComponents
     */
    public static UriComponents listURI() {
        return MvcUriComponentsBuilder.fromMethodCall(MvcUriComponentsBuilder.on(ManagePagesCollectionJsonController.class).list(null, null)).build().encode();
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param managePage
     * @param result
     * @return ResponseEntity
     */
    @PostMapping(name = "create")
    public ResponseEntity<?> create(@Valid @RequestBody ManagePage managePage, BindingResult result) {
        if (managePage.getId() != null || managePage.getVersion() != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        if (result.hasErrors()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(result);
        }
        ManagePage newManagePage = getManagePageService().save(managePage);
        UriComponents showURI = ManagePagesItemJsonController.showURI(newManagePage);
        return ResponseEntity.created(showURI.toUri()).build();
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param managePages
     * @param result
     * @return ResponseEntity
     */
    @PostMapping(value = "/batch", name = "createBatch")
    public ResponseEntity<?> createBatch(@Valid @RequestBody Collection<ManagePage> managePages, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(result);
        }
        getManagePageService().save(managePages);
        return ResponseEntity.created(listURI().toUri()).build();
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param managePages
     * @param result
     * @return ResponseEntity
     */
    @PutMapping(value = "/batch", name = "updateBatch")
    public ResponseEntity<?> updateBatch(@Valid @RequestBody Collection<ManagePage> managePages, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(result);
        }
        getManagePageService().save(managePages);
        return ResponseEntity.ok().build();
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param ids
     * @return ResponseEntity
     */
    @DeleteMapping(value = "/batch/{ids}", name = "deleteBatch")
    public ResponseEntity<?> deleteBatch(@PathVariable("ids") Collection<Long> ids) {
        getManagePageService().delete(ids);
        return ResponseEntity.ok().build();
    }
}
