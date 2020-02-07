package com.itmcs.roo.mirisk.dta;
import org.springframework.roo.addon.javabean.annotations.RooEquals;
import org.springframework.roo.addon.javabean.annotations.RooJavaBean;
import org.springframework.roo.addon.javabean.annotations.RooToString;
import org.springframework.roo.addon.jpa.annotations.entity.RooJpaEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;
import io.springlets.format.EntityFormat;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.util.Collection;
import java.util.Objects;
import javax.persistence.Entity;

/**
 * = ManageUser
 TODO Auto-generated class documentation
 *
 */
@RooJavaBean
@RooToString
@RooJpaEntity
@RooEquals(isJpaEntity = true)
@Entity
@EntityFormat
public class ManageUser implements UserDetails {

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
    @Version
    private Integer version;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private String userName;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private String fullName;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private String passWord;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @EntityFormat
    private ManageRole manageroles;

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
     * @return ManageUser
     */
    public ManageUser setId(Long id) {
        this.id = id;
        return this;
    }

    /**
     * Gets version value
     *
     * @return Integer
     */
    public Integer getVersion() {
        return this.version;
    }

    /**
     * Sets version value
     *
     * @param version
     * @return ManageUser
     */
    public ManageUser setVersion(Integer version) {
        this.version = version;
        return this;
    }

    /**
     * Gets userName value
     *
     * @return String
     */
    public String getUserName() {
        return this.userName;
    }

    /**
     * Sets userName value
     *
     * @param userName
     * @return ManageUser
     */
    public ManageUser setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    /**
     * Gets fullName value
     *
     * @return String
     */
    public String getFullName() {
        return this.fullName;
    }

    /**
     * Sets fullName value
     *
     * @param fullName
     * @return ManageUser
     */
    public ManageUser setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    /**
     * Gets passWord value
     *
     * @return String
     */
    public String getPassWord() {
        return this.passWord;
    }

    /**
     * Sets passWord value
     *
     * @param passWord
     * @return ManageUser
     */
    public ManageUser setPassWord(String passWord) {
        this.passWord = passWord;
        return this;
    }

    /**
     * Gets manageroles value
     *
     * @return ManageRole
     */
    public ManageRole getManageroles() {
        return this.manageroles;
    }

    /**
     * Sets manageroles value
     *
     * @param manageroles
     * @return ManageUser
     */
    public ManageUser setManageroles(ManageRole manageroles) {
        this.manageroles = manageroles;
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
        if (!(obj instanceof ManageUser)) {
            return false;
        }
        return getId() != null && Objects.equals(getId(), ((ManageUser) obj).getId());
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
        return "ManageUser {" + "id='" + id + '\'' + ", version='" + version + '\'' + ", userName='" + userName + '\'' + ", fullName='" + fullName + '\'' + ", passWord='" + passWord + '\'' + "}" + super.toString();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getPassword() {
        // TODO Auto-generated method stub
        return this.passWord;
    }

    @Override
    public String getUsername() {
        // TODO Auto-generated method stub
        return this.userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isEnabled() {
        // TODO Auto-generated method stub
        return true;
    }
}
