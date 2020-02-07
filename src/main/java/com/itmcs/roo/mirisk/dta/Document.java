package com.itmcs.roo.mirisk.dta;
import org.springframework.roo.addon.javabean.annotations.RooEquals;
import org.springframework.roo.addon.javabean.annotations.RooJavaBean;
import org.springframework.roo.addon.javabean.annotations.RooToString;
import org.springframework.roo.addon.jpa.annotations.entity.RooJpaEntity;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;
import java.util.Calendar;
import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.beans.factory.annotation.Value;
import io.springlets.format.EntityFormat;
import java.util.Objects;
import javax.persistence.Entity;

/**
 * = Document
 TODO Auto-generated class documentation
 *
 */
@RooJavaBean
@RooToString
@RooJpaEntity
@RooEquals(isJpaEntity = true)
@Entity
@EntityFormat
public class Document {

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
    private String documentName;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private String owner;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private String nextReviewDate;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private String attachment;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date documentUploadDateTime = new Date();

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private String status = "Due";

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
     * @return Document
     */
    public Document setId(Long id) {
        this.id = id;
        return this;
    }

    /**
     * Gets documentName value
     *
     * @return String
     */
    public String getDocumentName() {
        return this.documentName;
    }

    /**
     * Sets documentName value
     *
     * @param documentName
     * @return Document
     */
    public Document setDocumentName(String documentName) {
        this.documentName = documentName;
        return this;
    }

    /**
     * Gets owner value
     *
     * @return String
     */
    public String getOwner() {
        return this.owner;
    }

    /**
     * Sets owner value
     *
     * @param owner
     * @return Document
     */
    public Document setOwner(String owner) {
        this.owner = owner;
        return this;
    }

    /**
     * Gets nextReviewDate value
     *
     * @return String
     */
    public String getNextReviewDate() {
        return this.nextReviewDate;
    }

    /**
     * Sets nextReviewDate value
     *
     * @param nextReviewDate
     * @return Document
     */
    public Document setNextReviewDate(String nextReviewDate) {
        this.nextReviewDate = nextReviewDate;
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
     * @return Document
     */
    public Document setAttachment(String attachment) {
        this.attachment = attachment;
        return this;
    }

    /**
     * Gets documentUploadDateTime value
     *
     * @return Date
     */
    public Date getDocumentUploadDateTime() {
        return this.documentUploadDateTime;
    }

    /**
     * Sets documentUploadDateTime value
     *
     * @param documentUploadDateTime
     * @return Document
     */
    public Document setDocumentUploadDateTime(Date documentUploadDateTime) {
        this.documentUploadDateTime = documentUploadDateTime;
        return this;
    }

    /**
     * Gets status value
     *
     * @return String
     */
    public String getStatus() {
        return this.status;
    }

    /**
     * Sets status value
     *
     * @param status
     * @return Document
     */
    public Document setStatus(String status) {
        this.status = status;
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
     * @return Document
     */
    public Document setUniqueId(String uniqueId) {
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
        if (!(obj instanceof Document)) {
            return false;
        }
        return getId() != null && Objects.equals(getId(), ((Document) obj).getId());
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
        return "Document {" + "id='" + id + '\'' + ", documentName='" + documentName + '\'' + ", owner='" + owner + '\'' + ", nextReviewDate='" + nextReviewDate + '\'' + ", attachment='" + attachment + '\'' + ", documentUploadDateTime='" + documentUploadDateTime == null ? null : java.text.DateFormat.getDateTimeInstance().format(documentUploadDateTime) + '\'' + ", status='" + status + '\'' + ", uniqueId='" + uniqueId + '\'' + "}" + super.toString();
    }
}
