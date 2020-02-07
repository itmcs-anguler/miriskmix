package com.itmcs.roo.mirisk.web;
import com.itmcs.roo.mirisk.dta.ManageRole;
import org.springframework.roo.addon.web.mvc.controller.annotations.ControllerType;
import org.springframework.roo.addon.web.mvc.controller.annotations.RooController;
import org.springframework.roo.addon.web.mvc.controller.annotations.responses.json.RooJSON;
import org.springframework.web.bind.annotation.CrossOrigin;
import com.itmcs.roo.mirisk.service.api.ManageRoleService;
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
 * = ManageRolesItemJsonController
 TODO Auto-generated class documentation
 *
 */
@CrossOrigin
@RooController(entity = ManageRole.class, pathPrefix = "/mirisk", type = ControllerType.ITEM)
@RooJSON
@RestController
@RequestMapping(value = "/mirisk/manageroles/{manageRole}", name = "ManageRolesItemJsonController", produces = MediaType.APPLICATION_JSON_VALUE)
public class ManageRolesItemJsonController {

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private ManageRoleService manageRoleService;

    /**
     * TODO Auto-generated constructor documentation
     *
     * @param manageRoleService
     */
    @Autowired
    public ManageRolesItemJsonController(ManageRoleService manageRoleService) {
        this.manageRoleService = manageRoleService;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return ManageRoleService
     */
    public ManageRoleService getManageRoleService() {
        return manageRoleService;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param manageRoleService
     */
    public void setManageRoleService(ManageRoleService manageRoleService) {
        this.manageRoleService = manageRoleService;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param id
     * @return ManageRole
     */
    @ModelAttribute
    public ManageRole getManageRole(@PathVariable("manageRole") Long id) {
        ManageRole manageRole = manageRoleService.findOne(id);
        if (manageRole == null) {
            throw new NotFoundException(String.format("ManageRole with identifier '%s' not found", id));
        }
        return manageRole;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param manageRole
     * @return ResponseEntity
     */
    @GetMapping(name = "show")
    public ResponseEntity<?> show(@ModelAttribute ManageRole manageRole) {
        return ResponseEntity.ok(manageRole);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param manageRole
     * @return UriComponents
     */
    public static UriComponents showURI(ManageRole manageRole) {
        return MvcUriComponentsBuilder.fromMethodCall(MvcUriComponentsBuilder.on(ManageRolesItemJsonController.class).show(manageRole)).buildAndExpand(manageRole.getId()).encode();
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param storedManageRole
     * @param manageRole
     * @param result
     * @return ResponseEntity
     */
    @PutMapping(name = "update")
    public ResponseEntity<?> update(@ModelAttribute ManageRole storedManageRole, @Valid @RequestBody ManageRole manageRole, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(result);
        }
        manageRole.setId(storedManageRole.getId());
        getManageRoleService().save(manageRole);
        return ResponseEntity.ok().build();
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param manageRole
     * @return ResponseEntity
     */
    @DeleteMapping(name = "delete")
    public ResponseEntity<?> delete(@ModelAttribute ManageRole manageRole) {
        getManageRoleService().delete(manageRole);
        return ResponseEntity.ok().build();
    }
}
