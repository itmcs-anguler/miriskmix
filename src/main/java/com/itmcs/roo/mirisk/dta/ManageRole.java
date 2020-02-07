package com.itmcs.roo.mirisk.dta;
import org.springframework.roo.addon.javabean.annotations.RooEquals;
import org.springframework.roo.addon.javabean.annotations.RooJavaBean;
import org.springframework.roo.addon.javabean.annotations.RooToString;
import org.springframework.roo.addon.jpa.annotations.entity.RooJpaEntity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import org.springframework.roo.addon.jpa.annotations.entity.JpaRelationType;
import org.springframework.roo.addon.jpa.annotations.entity.RooJpaRelation;
import io.springlets.format.EntityFormat;
import java.util.Objects;
import javax.persistence.Entity;
import org.springframework.util.Assert;

/**
 * = ManageRole
 TODO Auto-generated class documentation
 *
 */
@RooJavaBean
@RooToString
@RooJpaEntity
@RooEquals(isJpaEntity = true)
@Entity
@EntityFormat
public class ManageRole {

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    @NotNull
    @Size(max = 300)
    private String roleName;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    @Size(max = 600)
    private String roleDescription;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    @OneToMany(cascade = { javax.persistence.CascadeType.MERGE, javax.persistence.CascadeType.PERSIST }, fetch = FetchType.EAGER, mappedBy = "manageroles")
    @RooJpaRelation(type = JpaRelationType.AGGREGATION)
    private Set<ManageUser> manageUsers = new HashSet<ManageUser>();

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    public static final String ITERABLE_TO_ADD_CANT_BE_NULL_MESSAGE = "The given Iterable of items to add can't be null!";

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    public static final String ITERABLE_TO_REMOVE_CANT_BE_NULL_MESSAGE = "The given Iterable of items to add can't be null!";

    /**
     * Gets id value
     *
     * @return Long
     */
    public Long getId() {
        return this.id;
    }

    /**
     * Sets id value
     *
     * @param id
     * @return ManageRole
     */
    public ManageRole setId(Long id) {
        this.id = id;
        return this;
    }

    /**
     * Gets roleName value
     *
     * @return String
     */
    public String getRoleName() {
        return this.roleName;
    }

    /**
     * Sets roleName value
     *
     * @param roleName
     * @return ManageRole
     */
    public ManageRole setRoleName(String roleName) {
        this.roleName = roleName;
        return this;
    }

    /**
     * Gets roleDescription value
     *
     * @return String
     */
    public String getRoleDescription() {
        return this.roleDescription;
    }

    /**
     * Sets roleDescription value
     *
     * @param roleDescription
     * @return ManageRole
     */
    public ManageRole setRoleDescription(String roleDescription) {
        this.roleDescription = roleDescription;
        return this;
    }

    /**
     * Gets manageUsers value
     *
     * @return Set
     */
    public Set<ManageUser> getManageUsers() {
        return this.manageUsers;
    }

    /**
     * Sets manageUsers value
     *
     * @param manageUsers
     * @return ManageRole
     */
    public ManageRole setManageUsers(Set<ManageUser> manageUsers) {
        this.manageUsers = manageUsers;
        return this;
    }

    /**
     * This `equals` implementation is specific for JPA entities and uses
     * the entity identifier for it, following the article in
     * https://vladmihalcea.com/2016/06/06/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
     *
     * @param obj
     * @return Boolean
     */
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        // instanceof is false if the instance is null
        if (!(obj instanceof ManageRole)) {
            return false;
        }
        return getId() != null && Objects.equals(getId(), ((ManageRole) obj).getId());
    }

    /**
     * This `hashCode` implementation is specific for JPA entities and uses a fixed `int` value to be able
     * to identify the entity in collections after a new id is assigned to the entity, following the article in
     * https://vladmihalcea.com/2016/06/06/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
     *
     * @return Integer
     */
    public int hashCode() {
        return 31;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return String
     */
    public String toString() {
        return "ManageRole {" + "id='" + id + '\'' + ", roleName='" + roleName + '\'' + ", roleDescription='" + roleDescription + '\'' + "}" + super.toString();
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param manageUsersToAdd
     */
    public void addToManageUsers(Iterable<ManageUser> manageUsersToAdd) {
        Assert.notNull(manageUsersToAdd, ITERABLE_TO_ADD_CANT_BE_NULL_MESSAGE);
        for (ManageUser item : manageUsersToAdd) {
            this.manageUsers.add(item);
            item.setManageroles(this);
        }
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param manageUsersToRemove
     */
    public void removeFromManageUsers(Iterable<ManageUser> manageUsersToRemove) {
        Assert.notNull(manageUsersToRemove, ITERABLE_TO_REMOVE_CANT_BE_NULL_MESSAGE);
        for (ManageUser item : manageUsersToRemove) {
            this.manageUsers.remove(item);
            item.setManageroles(null);
        }
    }
}
