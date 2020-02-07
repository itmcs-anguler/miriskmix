package com.itmcs.roo.mirisk.web;
import com.itmcs.roo.mirisk.dta.ManageRole;
import org.springframework.roo.addon.web.mvc.controller.annotations.config.RooJsonMixin;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.itmcs.roo.mirisk.dta.ManageUser;
import java.util.Set;

/**
 * = ManageRoleJsonMixin
 TODO Auto-generated class documentation
 *
 */
@RooJsonMixin(entity = ManageRole.class)
public abstract class ManageRoleJsonMixin {

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    @JsonIgnore
    private Set<ManageUser> manageUsers;

    /**
     * TODO Auto-generated method documentation
     *
     * @return Set
     */
    public Set<ManageUser> getManageUsers() {
        return manageUsers;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param manageUsers
     */
    public void setManageUsers(Set<ManageUser> manageUsers) {
        this.manageUsers = manageUsers;
    }
}
