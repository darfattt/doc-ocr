package com.darfat.docreaderapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.Instant;
import javax.persistence.*;
import javax.validation.constraints.*;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.domain.Persistable;

/**
 * A VerifiedDocuments.
 */
@JsonIgnoreProperties(value = { "new" })
@Entity
@Table(name = "verified_documents")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class VerifiedDocuments extends AbstractAuditingEntity<String>  implements Serializable, Persistable<String> {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Size(max = 50)
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "id", length = 50, nullable = false)
    private String id;

    @NotNull
    @Size(max = 12)
    @Column(name = "type", length = 12, nullable = false)
    private String type;

    @NotNull
    @Size(max = 100)
    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @NotNull
    @Size(max = 12)
    @Column(name = "status", length = 12, nullable = false)
    private String status;

    @NotNull
    @Size(max = 50)
    @Column(name = "content_id", length = 50, nullable = false)
    private String contentId;

    @Size(max = 36)
    @Column(name = "attachment_group_id", length = 36)
    private String attachmentGroupId;

    @Transient
    private boolean isPersisted;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public VerifiedDocuments id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return this.type;
    }

    public VerifiedDocuments type(String type) {
        this.setType(type);
        return this;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return this.name;
    }

    public VerifiedDocuments name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return this.status;
    }

    public VerifiedDocuments status(String status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getContentId() {
        return this.contentId;
    }

    public VerifiedDocuments contentId(String contentId) {
        this.setContentId(contentId);
        return this;
    }

    public void setContentId(String contentId) {
        this.contentId = contentId;
    }

    @Transient
    @Override
    public boolean isNew() {
        return !this.isPersisted;
    }

    public VerifiedDocuments setIsPersisted() {
        this.isPersisted = true;
        return this;
    }

    @PostLoad
    @PostPersist
    public void updateEntityState() {
        this.setIsPersisted();
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof VerifiedDocuments)) {
            return false;
        }
        return id != null && id.equals(((VerifiedDocuments) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "VerifiedDocuments{" +
            "id=" + getId() +
            ", type='" + getType() + "'" +
            ", name='" + getName() + "'" +
            ", status='" + getStatus() + "'" +
            ", contentId='" + getContentId() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", lastModifiedDate='" + getLastModifiedDate() + "'" +
            ", lastModifiedBy='" + getLastModifiedBy() + "'" +
            "}";
    }

    public String getAttachmentGroupId() {
        return attachmentGroupId;
    }

    public void setAttachmentGroupId(String attachmentGroupId) {
        this.attachmentGroupId = attachmentGroupId;
    }
}
