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
 * = ResidualImpactAssessment
 TODO Auto-generated class documentation
 *
 */
@RooJavaBean
@RooToString
@RooJpaEntity
@RooEquals(isJpaEntity = true)
@Entity
@EntityFormat
public class ResidualImpactAssessment {

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
    private String residualImpactAssessmentName;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private String residualImpactAssessmentDescription;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    @OneToMany(cascade = { javax.persistence.CascadeType.MERGE, javax.persistence.CascadeType.PERSIST }, fetch = FetchType.EAGER, mappedBy = "residualImpactAssessment")
    @RooJpaRelation(type = JpaRelationType.AGGREGATION)
    private Set<RiskRegister> riskRegisters = new HashSet<RiskRegister>();

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    @OneToMany(cascade = { javax.persistence.CascadeType.MERGE, javax.persistence.CascadeType.PERSIST }, fetch = FetchType.LAZY, mappedBy = "inheritImpactAssessment")
    @RooJpaRelation(type = JpaRelationType.AGGREGATION)
    private Set<RiskRegister> inheritImpactAssessment = new HashSet<RiskRegister>();

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
     * @return ResidualImpactAssessment
     */
    public ResidualImpactAssessment setId(Long id) {
        this.id = id;
        return this;
    }

    /**
     * Gets residualImpactAssessmentName value
     *
     * @return String
     */
    public String getResidualImpactAssessmentName() {
        return this.residualImpactAssessmentName;
    }

    /**
     * Sets residualImpactAssessmentName value
     *
     * @param residualImpactAssessmentName
     * @return ResidualImpactAssessment
     */
    public ResidualImpactAssessment setResidualImpactAssessmentName(String residualImpactAssessmentName) {
        this.residualImpactAssessmentName = residualImpactAssessmentName;
        return this;
    }

    /**
     * Gets residualImpactAssessmentDescription value
     *
     * @return String
     */
    public String getResidualImpactAssessmentDescription() {
        return this.residualImpactAssessmentDescription;
    }

    /**
     * Sets residualImpactAssessmentDescription value
     *
     * @param residualImpactAssessmentDescription
     * @return ResidualImpactAssessment
     */
    public ResidualImpactAssessment setResidualImpactAssessmentDescription(String residualImpactAssessmentDescription) {
        this.residualImpactAssessmentDescription = residualImpactAssessmentDescription;
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
     * @return ResidualImpactAssessment
     */
    public ResidualImpactAssessment setRiskRegisters(Set<RiskRegister> riskRegisters) {
        this.riskRegisters = riskRegisters;
        return this;
    }

    /**
     * Gets inheritImpactAssessment value
     *
     * @return Set
     */
    public Set<RiskRegister> getInheritImpactAssessment() {
        return this.inheritImpactAssessment;
    }

    /**
     * Sets inheritImpactAssessment value
     *
     * @param inheritImpactAssessment
     * @return ResidualImpactAssessment
     */
    public ResidualImpactAssessment setInheritImpactAssessment(Set<RiskRegister> inheritImpactAssessment) {
        this.inheritImpactAssessment = inheritImpactAssessment;
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
        if (!(obj instanceof ResidualImpactAssessment)) {
            return false;
        }
        return getId() != null && Objects.equals(getId(), ((ResidualImpactAssessment) obj).getId());
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
        return "ResidualImpactAssessment {" + "id='" + id + '\'' + ", residualImpactAssessmentName='" + residualImpactAssessmentName + '\'' + ", residualImpactAssessmentDescription='" + residualImpactAssessmentDescription + '\'' + "}" + super.toString();
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
            item.setResidualImpactAssessment(this);
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
            item.setResidualImpactAssessment(null);
        }
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param inheritImpactAssessmentToAdd
     */
    public void addToInheritImpactAssessment(Iterable<RiskRegister> inheritImpactAssessmentToAdd) {
        Assert.notNull(inheritImpactAssessmentToAdd, ITERABLE_TO_ADD_CANT_BE_NULL_MESSAGE);
        for (RiskRegister item : inheritImpactAssessmentToAdd) {
            this.inheritImpactAssessment.add(item);
            item.setInheritImpactAssessment(this);
        }
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param inheritImpactAssessmentToRemove
     */
    public void removeFromInheritImpactAssessment(Iterable<RiskRegister> inheritImpactAssessmentToRemove) {
        Assert.notNull(inheritImpactAssessmentToRemove, ITERABLE_TO_REMOVE_CANT_BE_NULL_MESSAGE);
        for (RiskRegister item : inheritImpactAssessmentToRemove) {
            this.inheritImpactAssessment.remove(item);
            item.setInheritImpactAssessment(null);
        }
    }
}
