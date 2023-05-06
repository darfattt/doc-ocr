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
 * A Documents.
 */
@JsonIgnoreProperties(value = { "new" })
@Entity
@Table(name = "documents")
@Getter
@Setter
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Documents extends AbstractAuditingEntity<String> implements Serializable, Persistable<String> {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Size(max = 50)
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "id", length = 50, nullable = false)
    private String id;

    @Size(max = 12)
    @Column(name = "type", length = 12, nullable = true)
    private String type;

    @NotNull
    @Size(max = 100)
    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @NotNull
    @Size(max = 12)
    @Column(name = "status", length = 12, nullable = false)
    private String status;

    @Size(max = 36)
    @Column(name = "attachment_group_id", length = 36)
    private String attachmentGroupId;

    @Transient
    private boolean isPersisted;

    @Size(max = 50)
    @Column(name = "branch", length = 50)
    private String branch;

    // jhipster-needle-entity-add-field - JHipster will add fields here


    @Transient
    @Override
    public boolean isNew() {
        return !this.isPersisted;
    }

    public Documents setIsPersisted() {
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
        if (!(o instanceof Documents)) {
            return false;
        }
        return id != null && id.equals(((Documents) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Documents{" +
            "id=" + getId() +
            ", type='" + getType() + "'" +
            ", name='" + getName() + "'" +
            ", status='" + getStatus() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", lastModifiedDate='" + getLastModifiedDate() + "'" +
            ", lastModifiedBy='" + getLastModifiedBy() + "'" +
            "}";
    }


}
