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
import java.util.Objects;
import javax.persistence.Entity;

/**
 * = AssetRegister
 TODO Auto-generated class documentation
 *
 */
@RooJavaBean
@RooToString
@RooJpaEntity
@RooEquals(isJpaEntity = true)
@Entity
@EntityFormat
public class AssetRegister {

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
    private String assetName;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private String description;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private String location;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private String usedFor;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private String procurementDate;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private String procurementPrice;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private String depreciation;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private String currentValue;

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
     * @return AssetRegister
     */
    public AssetRegister setId(Long id) {
        this.id = id;
        return this;
    }

    /**
     * Gets assetName value
     *
     * @return String
     */
    public String getAssetName() {
        return this.assetName;
    }

    /**
     * Sets assetName value
     *
     * @param assetName
     * @return AssetRegister
     */
    public AssetRegister setAssetName(String assetName) {
        this.assetName = assetName;
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
     * @return AssetRegister
     */
    public AssetRegister setDescription(String description) {
        this.description = description;
        return this;
    }

    /**
     * Gets location value
     *
     * @return String
     */
    public String getLocation() {
        return this.location;
    }

    /**
     * Sets location value
     *
     * @param location
     * @return AssetRegister
     */
    public AssetRegister setLocation(String location) {
        this.location = location;
        return this;
    }

    /**
     * Gets usedFor value
     *
     * @return String
     */
    public String getUsedFor() {
        return this.usedFor;
    }

    /**
     * Sets usedFor value
     *
     * @param usedFor
     * @return AssetRegister
     */
    public AssetRegister setUsedFor(String usedFor) {
        this.usedFor = usedFor;
        return this;
    }

    /**
     * Gets procurementDate value
     *
     * @return String
     */
    public String getProcurementDate() {
        return this.procurementDate;
    }

    /**
     * Sets procurementDate value
     *
     * @param procurementDate
     * @return AssetRegister
     */
    public AssetRegister setProcurementDate(String procurementDate) {
        this.procurementDate = procurementDate;
        return this;
    }

    /**
     * Gets procurementPrice value
     *
     * @return String
     */
    public String getProcurementPrice() {
        return this.procurementPrice;
    }

    /**
     * Sets procurementPrice value
     *
     * @param procurementPrice
     * @return AssetRegister
     */
    public AssetRegister setProcurementPrice(String procurementPrice) {
        this.procurementPrice = procurementPrice;
        return this;
    }

    /**
     * Gets depreciation value
     *
     * @return String
     */
    public String getDepreciation() {
        return this.depreciation;
    }

    /**
     * Sets depreciation value
     *
     * @param depreciation
     * @return AssetRegister
     */
    public AssetRegister setDepreciation(String depreciation) {
        this.depreciation = depreciation;
        return this;
    }

    /**
     * Gets currentValue value
     *
     * @return String
     */
    public String getCurrentValue() {
        return this.currentValue;
    }

    /**
     * Sets currentValue value
     *
     * @param currentValue
     * @return AssetRegister
     */
    public AssetRegister setCurrentValue(String currentValue) {
        this.currentValue = currentValue;
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
        if (!(obj instanceof AssetRegister)) {
            return false;
        }
        return getId() != null && Objects.equals(getId(), ((AssetRegister) obj).getId());
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
        return "AssetRegister {" + "id='" + id + '\'' + ", assetName='" + assetName + '\'' + ", description='" + description + '\'' + ", location='" + location + '\'' + ", usedFor='" + usedFor + '\'' + ", procurementDate='" + procurementDate + '\'' + ", procurementPrice='" + procurementPrice + '\'' + ", depreciation='" + depreciation + '\'' + ", currentValue='" + currentValue + '\'' + "}" + super.toString();
    }
}
