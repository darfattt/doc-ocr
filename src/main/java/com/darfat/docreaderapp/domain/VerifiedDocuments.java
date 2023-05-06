package com.darfat.docreaderapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.Instant;
import javax.persistence.*;
import javax.validation.constraints.*;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.domain.Persistable;

/**
 * A VerifiedDocuments.
 */
@JsonIgnoreProperties(value = { "new" })
@Entity
@Table(name = "verified_documents")
@Getter
@Setter
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

    @Size(max = 50)
    @Column(name = "content_id", length = 50, nullable = true)
    private String contentId;

    @Size(max = 36)
    @Column(name = "attachment_group_id", length = 36)
    private String attachmentGroupId;

    @Transient
    private boolean isPersisted;

    @Size(max = 50)
    @Column(name = "branch", length = 50)
    private String branch;

    @Size(max = 50)
    @Column(name = "documents_id", length = 50)
    private String documentsId;

    @Size(max = 50)
    @Column(name = "reference_number", length = 50)
    private String referenceNumber;

    // jhipster-needle-entity-add-field - JHipster will add fields here

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

}
