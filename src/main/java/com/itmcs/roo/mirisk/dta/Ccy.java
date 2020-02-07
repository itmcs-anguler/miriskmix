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
 * = Ccy
 TODO Auto-generated class documentation
 *
 */
@RooJavaBean
@RooToString
@RooJpaEntity
@RooEquals(isJpaEntity = true)
@Entity
@EntityFormat
public class Ccy {

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
    private String ccyName;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private String ccyDescription;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    @OneToMany(cascade = { javax.persistence.CascadeType.MERGE, javax.persistence.CascadeType.PERSIST }, fetch = FetchType.EAGER, mappedBy = "ccy")
    @RooJpaRelation(type = JpaRelationType.AGGREGATION)
    private Set<GrossImpact> grossImpact = new HashSet<GrossImpact>();

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    @OneToMany(cascade = { javax.persistence.CascadeType.MERGE, javax.persistence.CascadeType.PERSIST }, fetch = FetchType.EAGER, mappedBy = "ccy")
    @RooJpaRelation(type = JpaRelationType.AGGREGATION)
    private Set<Recovery> recovery = new HashSet<Recovery>();

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
     * @return Ccy
     */
    public Ccy setId(Long id) {
        this.id = id;
        return this;
    }

    /**
     * Gets ccyName value
     *
     * @return String
     */
    public String getCcyName() {
        return this.ccyName;
    }

    /**
     * Sets ccyName value
     *
     * @param ccyName
     * @return Ccy
     */
    public Ccy setCcyName(String ccyName) {
        this.ccyName = ccyName;
        return this;
    }

    /**
     * Gets ccyDescription value
     *
     * @return String
     */
    public String getCcyDescription() {
        return this.ccyDescription;
    }

    /**
     * Sets ccyDescription value
     *
     * @param ccyDescription
     * @return Ccy
     */
    public Ccy setCcyDescription(String ccyDescription) {
        this.ccyDescription = ccyDescription;
        return this;
    }

    /**
     * Gets grossImpact value
     *
     * @return Set
     */
    public Set<GrossImpact> getGrossImpact() {
        return this.grossImpact;
    }

    /**
     * Sets grossImpact value
     *
     * @param grossImpact
     * @return Ccy
     */
    public Ccy setGrossImpact(Set<GrossImpact> grossImpact) {
        this.grossImpact = grossImpact;
        return this;
    }

    /**
     * Gets recovery value
     *
     * @return Set
     */
    public Set<Recovery> getRecovery() {
        return this.recovery;
    }

    /**
     * Sets recovery value
     *
     * @param recovery
     * @return Ccy
     */
    public Ccy setRecovery(Set<Recovery> recovery) {
        this.recovery = recovery;
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
        if (!(obj instanceof Ccy)) {
            return false;
        }
        return getId() != null && Objects.equals(getId(), ((Ccy) obj).getId());
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
        return "Ccy {" + "id='" + id + '\'' + ", ccyName='" + ccyName + '\'' + ", ccyDescription='" + ccyDescription + '\'' + "}" + super.toString();
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param grossImpactToAdd
     */
    public void addToGrossImpact(Iterable<GrossImpact> grossImpactToAdd) {
        Assert.notNull(grossImpactToAdd, ITERABLE_TO_ADD_CANT_BE_NULL_MESSAGE);
        for (GrossImpact item : grossImpactToAdd) {
            this.grossImpact.add(item);
            item.setCcy(this);
        }
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param grossImpactToRemove
     */
    public void removeFromGrossImpact(Iterable<GrossImpact> grossImpactToRemove) {
        Assert.notNull(grossImpactToRemove, ITERABLE_TO_REMOVE_CANT_BE_NULL_MESSAGE);
        for (GrossImpact item : grossImpactToRemove) {
            this.grossImpact.remove(item);
            item.setCcy(null);
        }
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param recoveryToAdd
     */
    public void addToRecovery(Iterable<Recovery> recoveryToAdd) {
        Assert.notNull(recoveryToAdd, ITERABLE_TO_ADD_CANT_BE_NULL_MESSAGE);
        for (Recovery item : recoveryToAdd) {
            this.recovery.add(item);
            item.setCcy(this);
        }
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param recoveryToRemove
     */
    public void removeFromRecovery(Iterable<Recovery> recoveryToRemove) {
        Assert.notNull(recoveryToRemove, ITERABLE_TO_REMOVE_CANT_BE_NULL_MESSAGE);
        for (Recovery item : recoveryToRemove) {
            this.recovery.remove(item);
            item.setCcy(null);
        }
    }
}
