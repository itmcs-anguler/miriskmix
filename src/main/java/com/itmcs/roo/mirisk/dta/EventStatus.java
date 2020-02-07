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
 * = EventStatus
 TODO Auto-generated class documentation
 *
 */
@RooJavaBean
@RooToString
@RooJpaEntity
@RooEquals(isJpaEntity = true)
@Entity
@EntityFormat
public class EventStatus {

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
    private String eventStatusName;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private String eventStatusDescription;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    @OneToMany(cascade = { javax.persistence.CascadeType.MERGE, javax.persistence.CascadeType.PERSIST }, fetch = FetchType.EAGER, mappedBy = "eventStatus")
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
     * @return EventStatus
     */
    public EventStatus setId(Long id) {
        this.id = id;
        return this;
    }

    /**
     * Gets eventStatusName value
     *
     * @return String
     */
    public String getEventStatusName() {
        return this.eventStatusName;
    }

    /**
     * Sets eventStatusName value
     *
     * @param eventStatusName
     * @return EventStatus
     */
    public EventStatus setEventStatusName(String eventStatusName) {
        this.eventStatusName = eventStatusName;
        return this;
    }

    /**
     * Gets eventStatusDescription value
     *
     * @return String
     */
    public String getEventStatusDescription() {
        return this.eventStatusDescription;
    }

    /**
     * Sets eventStatusDescription value
     *
     * @param eventStatusDescription
     * @return EventStatus
     */
    public EventStatus setEventStatusDescription(String eventStatusDescription) {
        this.eventStatusDescription = eventStatusDescription;
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
     * @return EventStatus
     */
    public EventStatus setRiskIncident(Set<RiskIncident> riskIncident) {
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
        if (!(obj instanceof EventStatus)) {
            return false;
        }
        return getId() != null && Objects.equals(getId(), ((EventStatus) obj).getId());
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
        return "EventStatus {" + "id='" + id + '\'' + ", eventStatusName='" + eventStatusName + '\'' + ", eventStatusDescription='" + eventStatusDescription + '\'' + "}" + super.toString();
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
            item.setEventStatus(this);
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
            item.setEventStatus(null);
        }
    }
}
