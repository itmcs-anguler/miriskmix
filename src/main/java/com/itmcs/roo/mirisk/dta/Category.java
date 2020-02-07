package com.itmcs.roo.mirisk.dta;
import org.springframework.roo.addon.javabean.annotations.RooEquals;
import org.springframework.roo.addon.javabean.annotations.RooJavaBean;
import org.springframework.roo.addon.javabean.annotations.RooToString;
import org.springframework.roo.addon.jpa.annotations.entity.RooJpaEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
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
 * = Category
 TODO Auto-generated class documentation
 *
 */
@RooJavaBean
@RooToString
@RooJpaEntity
@RooEquals(isJpaEntity = true)
@Entity
@EntityFormat
public class Category {

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
    private String categoryName;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private String categoryDescription;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    @OneToMany(cascade = { javax.persistence.CascadeType.MERGE, javax.persistence.CascadeType.PERSIST }, fetch = FetchType.EAGER, mappedBy = "category")
    @RooJpaRelation(type = JpaRelationType.AGGREGATION)
    private Set<RiskRegister> riskRegisters = new HashSet<RiskRegister>();

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
     * @return Category
     */
    public Category setId(Long id) {
        this.id = id;
        return this;
    }

    /**
     * Gets categoryName value
     *
     * @return String
     */
    public String getCategoryName() {
        return this.categoryName;
    }

    /**
     * Sets categoryName value
     *
     * @param categoryName
     * @return Category
     */
    public Category setCategoryName(String categoryName) {
        this.categoryName = categoryName;
        return this;
    }

    /**
     * Gets categoryDescription value
     *
     * @return String
     */
    public String getCategoryDescription() {
        return this.categoryDescription;
    }

    /**
     * Sets categoryDescription value
     *
     * @param categoryDescription
     * @return Category
     */
    public Category setCategoryDescription(String categoryDescription) {
        this.categoryDescription = categoryDescription;
        return this;
    }

    /**
     * Gets riskRegisters value
     *
     * @return Set
     */
    public Set<RiskRegister> getRiskRegisters() {
        return this.riskRegisters;
    }

    /**
     * Sets riskRegisters value
     *
     * @param riskRegisters
     * @return Category
     */
    public Category setRiskRegisters(Set<RiskRegister> riskRegisters) {
        this.riskRegisters = riskRegisters;
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
        if (!(obj instanceof Category)) {
            return false;
        }
        return getId() != null && Objects.equals(getId(), ((Category) obj).getId());
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
        return "Category {" + "id='" + id + '\'' + ", categoryName='" + categoryName + '\'' + ", categoryDescription='" + categoryDescription + '\'' + "}" + super.toString();
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param riskRegistersToAdd
     */
    public void addToRiskRegisters(Iterable<RiskRegister> riskRegistersToAdd) {
        Assert.notNull(riskRegistersToAdd, ITERABLE_TO_ADD_CANT_BE_NULL_MESSAGE);
        for (RiskRegister item : riskRegistersToAdd) {
            this.riskRegisters.add(item);
            item.setCategory(this);
        }
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param riskRegistersToRemove
     */
    public void removeFromRiskRegisters(Iterable<RiskRegister> riskRegistersToRemove) {
        Assert.notNull(riskRegistersToRemove, ITERABLE_TO_REMOVE_CANT_BE_NULL_MESSAGE);
        for (RiskRegister item : riskRegistersToRemove) {
            this.riskRegisters.remove(item);
            item.setCategory(null);
        }
    }
}
