package com.itmcs.roo.mirisk.web;
import com.itmcs.roo.mirisk.dta.ManageRole;
import org.springframework.roo.addon.web.mvc.controller.annotations.ControllerType;
import org.springframework.roo.addon.web.mvc.controller.annotations.RooController;
import org.springframework.roo.addon.web.mvc.controller.annotations.finder.RooSearch;
import org.springframework.roo.addon.web.mvc.controller.annotations.responses.json.RooJSON;
import org.springframework.web.bind.annotation.CrossOrigin;
import com.itmcs.roo.mirisk.service.api.ManageRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * = ManageRolesSearchJsonController
 TODO Auto-generated class documentation
 *
 */
@CrossOrigin
@RooController(entity = ManageRole.class, type = ControllerType.SEARCH)
@RooSearch(finders = {  })
@RooJSON
@RestController
@RequestMapping(value = "/manageroles/search", name = "ManageRolesSearchJsonController", produces = MediaType.APPLICATION_JSON_VALUE)
public class ManageRolesSearchJsonController {

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
    public ManageRolesSearchJsonController(ManageRoleService manageRoleService) {
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
}
