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
 * = RiskStatus
 TODO Auto-generated class documentation
 *
 */
@RooJavaBean
@RooToString
@RooJpaEntity
@RooEquals(isJpaEntity = true)
@Entity
@EntityFormat
public class RiskStatus {

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
    private String riskStatusName;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private String riskStatusDescription;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    @OneToMany(cascade = { javax.persistence.CascadeType.MERGE, javax.persistence.CascadeType.PERSIST }, fetch = FetchType.EAGER, mappedBy = "riskStatus")
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
     * @return RiskStatus
     */
    public RiskStatus setId(Long id) {
        this.id = id;
        return this;
    }

    /**
     * Gets riskStatusName value
     *
     * @return String
     */
    public String getRiskStatusName() {
        return this.riskStatusName;
    }

    /**
     * Sets riskStatusName value
     *
     * @param riskStatusName
     * @return RiskStatus
     */
    public RiskStatus setRiskStatusName(String riskStatusName) {
        this.riskStatusName = riskStatusName;
        return this;
    }

    /**
     * Gets riskStatusDescription value
     *
     * @return String
     */
    public String getRiskStatusDescription() {
        return this.riskStatusDescription;
    }

    /**
     * Sets riskStatusDescription value
     *
     * @param riskStatusDescription
     * @return RiskStatus
     */
    public RiskStatus setRiskStatusDescription(String riskStatusDescription) {
        this.riskStatusDescription = riskStatusDescription;
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
     * @return RiskStatus
     */
    public RiskStatus setRiskRegisters(Set<RiskRegister> riskRegisters) {
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
        if (!(obj instanceof RiskStatus)) {
            return false;
        }
        return getId() != null && Objects.equals(getId(), ((RiskStatus) obj).getId());
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
        return "RiskStatus {" + "id='" + id + '\'' + ", riskStatusName='" + riskStatusName + '\'' + ", riskStatusDescription='" + riskStatusDescription + '\'' + "}" + super.toString();
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
            item.setRiskStatus(this);
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
            item.setRiskStatus(null);
        }
    }
}
