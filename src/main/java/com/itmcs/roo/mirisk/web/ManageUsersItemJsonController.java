package com.itmcs.roo.mirisk.web;
import com.itmcs.roo.mirisk.dta.ManageUser;
import org.springframework.roo.addon.web.mvc.controller.annotations.ControllerType;
import org.springframework.roo.addon.web.mvc.controller.annotations.RooController;
import org.springframework.roo.addon.web.mvc.controller.annotations.responses.json.RooJSON;
import org.springframework.web.bind.annotation.CrossOrigin;
import com.itmcs.roo.mirisk.service.api.ManageUserService;
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
 * = ManageUsersItemJsonController
 TODO Auto-generated class documentation
 *
 */
@CrossOrigin
@RooController(entity = ManageUser.class, pathPrefix = "/mirisk", type = ControllerType.ITEM)
@RooJSON
@RestController
@RequestMapping(value = "/mirisk/manageusers/{manageUser}", name = "ManageUsersItemJsonController", produces = MediaType.APPLICATION_JSON_VALUE)
public class ManageUsersItemJsonController {

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private ManageUserService manageUserService;

    /**
     * TODO Auto-generated constructor documentation
     *
     * @param manageUserService
     */
    @Autowired
    public ManageUsersItemJsonController(ManageUserService manageUserService) {
        this.manageUserService = manageUserService;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return ManageUserService
     */
    public ManageUserService getManageUserService() {
        return manageUserService;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param manageUserService
     */
    public void setManageUserService(ManageUserService manageUserService) {
        this.manageUserService = manageUserService;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param id
     * @return ManageUser
     */
    @ModelAttribute
    public ManageUser getManageUser(@PathVariable("manageUser") Long id) {
        ManageUser manageUser = manageUserService.findOne(id);
        if (manageUser == null) {
            throw new NotFoundException(String.format("ManageUser with identifier '%s' not found", id));
        }
        return manageUser;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param manageUser
     * @return ResponseEntity
     */
    @GetMapping(name = "show")
    public ResponseEntity<?> show(@ModelAttribute ManageUser manageUser) {
        return ResponseEntity.ok(manageUser);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param manageUser
     * @return UriComponents
     */
    public static UriComponents showURI(ManageUser manageUser) {
        return MvcUriComponentsBuilder.fromMethodCall(MvcUriComponentsBuilder.on(ManageUsersItemJsonController.class).show(manageUser)).buildAndExpand(manageUser.getId()).encode();
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param storedManageUser
     * @param manageUser
     * @param result
     * @return ResponseEntity
     */
    @PutMapping(name = "update")
    public ResponseEntity<?> update(@ModelAttribute ManageUser storedManageUser, @Valid @RequestBody ManageUser manageUser, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(result);
        }
        manageUser.setId(storedManageUser.getId());
        getManageUserService().save(manageUser);
        return ResponseEntity.ok().build();
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param manageUser
     * @return ResponseEntity
     */
    @DeleteMapping(name = "delete")
    public ResponseEntity<?> delete(@ModelAttribute ManageUser manageUser) {
        getManageUserService().delete(manageUser);
        return ResponseEntity.ok().build();
    }
}
