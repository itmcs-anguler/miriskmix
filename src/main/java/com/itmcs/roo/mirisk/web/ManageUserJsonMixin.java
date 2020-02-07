package com.itmcs.roo.mirisk.web;
import com.itmcs.roo.mirisk.dta.ManageUser;
import org.springframework.roo.addon.web.mvc.controller.annotations.config.RooJsonMixin;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.itmcs.roo.mirisk.dta.ManageRole;

/**
 * = ManageUserJsonMixin
 TODO Auto-generated class documentation
 *
 */
@RooJsonMixin(entity = ManageUser.class)
public abstract class ManageUserJsonMixin {

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    @JsonDeserialize(using = ManageRoleDeserializer.class)
    private ManageRole manageroles;

    /**
     * TODO Auto-generated method documentation
     *
     * @return ManageRole
     */
    public ManageRole getManageroles() {
        return manageroles;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param manageroles
     */
    public void setManageroles(ManageRole manageroles) {
        this.manageroles = manageroles;
    }
}
