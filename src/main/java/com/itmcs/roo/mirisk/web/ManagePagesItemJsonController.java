package com.itmcs.roo.mirisk.web;
import org.springframework.roo.addon.web.mvc.controller.annotations.ControllerType;
import org.springframework.roo.addon.web.mvc.controller.annotations.RooController;
import org.springframework.roo.addon.web.mvc.controller.annotations.responses.json.RooJSON;
import org.springframework.web.bind.annotation.CrossOrigin;
import com.itmcs.roo.mirisk.dta.ManagePage;
import com.itmcs.roo.mirisk.service.api.ManagePageService;
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
 * = ManagePagesItemJsonController
 TODO Auto-generated class documentation
 *
 */
@CrossOrigin
@RooController(entity = ManagePage.class, pathPrefix = "/mirisk", type = ControllerType.ITEM)
@RooJSON
@RestController
@RequestMapping(value = "/mirisk/managepages/{managePage}", name = "ManagePagesItemJsonController", produces = MediaType.APPLICATION_JSON_VALUE)
public class ManagePagesItemJsonController {

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
    public ManagePagesItemJsonController(ManagePageService managePageService) {
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
     * @param id
     * @return ManagePage
     */
    @ModelAttribute
    public ManagePage getManagePage(@PathVariable("managePage") Long id) {
        ManagePage managePage = managePageService.findOne(id);
        if (managePage == null) {
            throw new NotFoundException(String.format("ManagePage with identifier '%s' not found", id));
        }
        return managePage;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param managePage
     * @return ResponseEntity
     */
    @GetMapping(name = "show")
    public ResponseEntity<?> show(@ModelAttribute ManagePage managePage) {
        return ResponseEntity.ok(managePage);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param managePage
     * @return UriComponents
     */
    public static UriComponents showURI(ManagePage managePage) {
        return MvcUriComponentsBuilder.fromMethodCall(MvcUriComponentsBuilder.on(ManagePagesItemJsonController.class).show(managePage)).buildAndExpand(managePage.getId()).encode();
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param storedManagePage
     * @param managePage
     * @param result
     * @return ResponseEntity
     */
    @PutMapping(name = "update")
    public ResponseEntity<?> update(@ModelAttribute ManagePage storedManagePage, @Valid @RequestBody ManagePage managePage, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(result);
        }
        managePage.setId(storedManagePage.getId());
        getManagePageService().save(managePage);
        return ResponseEntity.ok().build();
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param managePage
     * @return ResponseEntity
     */
    @DeleteMapping(name = "delete")
    public ResponseEntity<?> delete(@ModelAttribute ManagePage managePage) {
        getManagePageService().delete(managePage);
        return ResponseEntity.ok().build();
    }
}
