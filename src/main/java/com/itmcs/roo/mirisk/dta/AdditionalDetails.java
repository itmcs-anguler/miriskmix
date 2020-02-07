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
 * = AdditionalDetails
 TODO Auto-generated class documentation
 *
 */
@RooJavaBean
@RooToString
@RooJpaEntity
@RooEquals(isJpaEntity = true)
@Entity
@EntityFormat
public class AdditionalDetails {

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
    private String fileName;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private String documentTitle;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private String documentOwner;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private String attachment;

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
     * @return AdditionalDetails
     */
    public AdditionalDetails setId(Long id) {
        this.id = id;
        return this;
    }

    /**
     * Gets fileName value
     *
     * @return String
     */
    public String getFileName() {
        return this.fileName;
    }

    /**
     * Sets fileName value
     *
     * @param fileName
     * @return AdditionalDetails
     */
    public AdditionalDetails setFileName(String fileName) {
        this.fileName = fileName;
        return this;
    }

    /**
     * Gets documentTitle value
     *
     * @return String
     */
    public String getDocumentTitle() {
        return this.documentTitle;
    }

    /**
     * Sets documentTitle value
     *
     * @param documentTitle
     * @return AdditionalDetails
     */
    public AdditionalDetails setDocumentTitle(String documentTitle) {
        this.documentTitle = documentTitle;
        return this;
    }

    /**
     * Gets documentOwner value
     *
     * @return String
     */
    public String getDocumentOwner() {
        return this.documentOwner;
    }

    /**
     * Sets documentOwner value
     *
     * @param documentOwner
     * @return AdditionalDetails
     */
    public AdditionalDetails setDocumentOwner(String documentOwner) {
        this.documentOwner = documentOwner;
        return this;
    }

    /**
     * Gets attachment value
     *
     * @return String
     */
    public String getAttachment() {
        return this.attachment;
    }

    /**
     * Sets attachment value
     *
     * @param attachment
     * @return AdditionalDetails
     */
    public AdditionalDetails setAttachment(String attachment) {
        this.attachment = attachment;
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
     * @return AdditionalDetails
     */
    public AdditionalDetails setUniqueId(String uniqueId) {
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
        if (!(obj instanceof AdditionalDetails)) {
            return false;
        }
        return getId() != null && Objects.equals(getId(), ((AdditionalDetails) obj).getId());
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
        return "AdditionalDetails {" + "id='" + id + '\'' + ", fileName='" + fileName + '\'' + ", documentTitle='" + documentTitle + '\'' + ", documentOwner='" + documentOwner + '\'' + ", attachment='" + attachment + '\'' + ", uniqueId='" + uniqueId + '\'' + "}" + super.toString();
    }
}
