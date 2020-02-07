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
 * = RiskCategoryLevelTwo
 TODO Auto-generated class documentation
 *
 */
@RooJavaBean
@RooToString
@RooJpaEntity
@RooEquals(isJpaEntity = true)
@Entity
@EntityFormat
public class RiskCategoryLevelTwo {

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
    private String riskCategoryLevelTwoName;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private String riskCategoryLevelTwoDescription;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    @OneToMany(cascade = { javax.persistence.CascadeType.MERGE, javax.persistence.CascadeType.PERSIST }, fetch = FetchType.EAGER, mappedBy = "riskCategoryTwo")
    @RooJpaRelation(type = JpaRelationType.AGGREGATION)
    private Set<RiskIncident> riskIncident = new HashSet<RiskIncident>();

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
     * @return RiskCategoryLevelTwo
     */
    public RiskCategoryLevelTwo setId(Long id) {
        this.id = id;
        return this;
    }

    /**
     * Gets riskCategoryLevelTwoName value
     *
     * @return String
     */
    public String getRiskCategoryLevelTwoName() {
        return this.riskCategoryLevelTwoName;
    }

    /**
     * Sets riskCategoryLevelTwoName value
     *
     * @param riskCategoryLevelTwoName
     * @return RiskCategoryLevelTwo
     */
    public RiskCategoryLevelTwo setRiskCategoryLevelTwoName(String riskCategoryLevelTwoName) {
        this.riskCategoryLevelTwoName = riskCategoryLevelTwoName;
        return this;
    }

    /**
     * Gets riskCategoryLevelTwoDescription value
     *
     * @return String
     */
    public String getRiskCategoryLevelTwoDescription() {
        return this.riskCategoryLevelTwoDescription;
    }

    /**
     * Sets riskCategoryLevelTwoDescription value
     *
     * @param riskCategoryLevelTwoDescription
     * @return RiskCategoryLevelTwo
     */
    public RiskCategoryLevelTwo setRiskCategoryLevelTwoDescription(String riskCategoryLevelTwoDescription) {
        this.riskCategoryLevelTwoDescription = riskCategoryLevelTwoDescription;
        return this;
    }

    /**
     * Gets riskIncident value
     *
     * @return Set
     */
    public Set<RiskIncident> getRiskIncident() {
        return this.riskIncident;
    }

    /**
     * Sets riskIncident value
     *
     * @param riskIncident
     * @return RiskCategoryLevelTwo
     */
    public RiskCategoryLevelTwo setRiskIncident(Set<RiskIncident> riskIncident) {
        this.riskIncident = riskIncident;
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
        if (!(obj instanceof RiskCategoryLevelTwo)) {
            return false;
        }
        return getId() != null && Objects.equals(getId(), ((RiskCategoryLevelTwo) obj).getId());
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
        return "RiskCategoryLevelTwo {" + "id='" + id + '\'' + ", riskCategoryLevelTwoName='" + riskCategoryLevelTwoName + '\'' + ", riskCategoryLevelTwoDescription='" + riskCategoryLevelTwoDescription + '\'' + "}" + super.toString();
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param riskIncidentToAdd
     */
    public void addToRiskIncident(Iterable<RiskIncident> riskIncidentToAdd) {
        Assert.notNull(riskIncidentToAdd, ITERABLE_TO_ADD_CANT_BE_NULL_MESSAGE);
        for (RiskIncident item : riskIncidentToAdd) {
            this.riskIncident.add(item);
            item.setRiskCategoryTwo(this);
        }
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param riskIncidentToRemove
     */
    public void removeFromRiskIncident(Iterable<RiskIncident> riskIncidentToRemove) {
        Assert.notNull(riskIncidentToRemove, ITERABLE_TO_REMOVE_CANT_BE_NULL_MESSAGE);
        for (RiskIncident item : riskIncidentToRemove) {
            this.riskIncident.remove(item);
            item.setRiskCategoryTwo(null);
        }
    }
}
