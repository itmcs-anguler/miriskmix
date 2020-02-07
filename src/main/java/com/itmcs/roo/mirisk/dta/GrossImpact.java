package com.itmcs.roo.mirisk.dta;
import org.springframework.roo.addon.javabean.annotations.RooEquals;
import org.springframework.roo.addon.javabean.annotations.RooJavaBean;
import org.springframework.roo.addon.javabean.annotations.RooToString;
import org.springframework.roo.addon.jpa.annotations.entity.RooJpaEntity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;
import io.springlets.format.EntityFormat;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.util.Objects;
import javax.persistence.Entity;

/**
 * = GrossImpact
 TODO Auto-generated class documentation
 *
 */
@RooJavaBean
@RooToString
@RooJpaEntity
@RooEquals(isJpaEntity = true)
@Entity
@EntityFormat
public class GrossImpact {

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
    private String amount;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private String glReference;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private String impactDate;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private String description;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @EntityFormat
    private Ccy ccy;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private String uniqueId;

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
     * @return GrossImpact
     */
    public GrossImpact setId(Long id) {
        this.id = id;
        return this;
    }

    /**
     * Gets amount value
     *
     * @return String
     */
    public String getAmount() {
        return this.amount;
    }

    /**
     * Sets amount value
     *
     * @param amount
     * @return GrossImpact
     */
    public GrossImpact setAmount(String amount) {
        this.amount = amount;
        return this;
    }

    /**
     * Gets glReference value
     *
     * @return String
     */
    public String getGlReference() {
        return this.glReference;
    }

    /**
     * Sets glReference value
     *
     * @param glReference
     * @return GrossImpact
     */
    public GrossImpact setGlReference(String glReference) {
        this.glReference = glReference;
        return this;
    }

    /**
     * Gets impactDate value
     *
     * @return String
     */
    public String getImpactDate() {
        return this.impactDate;
    }

    /**
     * Sets impactDate value
     *
     * @param impactDate
     * @return GrossImpact
     */
    public GrossImpact setImpactDate(String impactDate) {
        this.impactDate = impactDate;
        return this;
    }

    /**
     * Gets description value
     *
     * @return String
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Sets description value
     *
     * @param description
     * @return GrossImpact
     */
    public GrossImpact setDescription(String description) {
        this.description = description;
        return this;
    }

    /**
     * Gets ccy value
     *
     * @return Ccy
     */
    public Ccy getCcy() {
        return this.ccy;
    }

    /**
     * Sets ccy value
     *
     * @param ccy
     * @return GrossImpact
     */
    public GrossImpact setCcy(Ccy ccy) {
        this.ccy = ccy;
        return this;
    }

    /**
     * Gets uniqueId value
     *
     * @return String
     */
    public String getUniqueId() {
        return this.uniqueId;
    }

    /**
     * Sets uniqueId value
     *
     * @param uniqueId
     * @return GrossImpact
     */
    public GrossImpact setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
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
        if (!(obj instanceof GrossImpact)) {
            return false;
        }
        return getId() != null && Objects.equals(getId(), ((GrossImpact) obj).getId());
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
        return "GrossImpact {" + "id='" + id + '\'' + ", amount='" + amount + '\'' + ", glReference='" + glReference + '\'' + ", impactDate='" + impactDate + '\'' + ", description='" + description + '\'' + ", uniqueId='" + uniqueId + '\'' + "}" + super.toString();
    }
}
