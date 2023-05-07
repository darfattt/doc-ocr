package com.darfat.docreaderapp.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "attachment_group")
@JsonIdentityInfo(generator = ObjectIdGenerators.UUIDGenerator.class, scope = AttachmentGroup.class)
public class AttachmentGroup extends AbstractAuditingEntity<String> implements Serializable, Persistable<String> {

    @NotNull
    @Size(max = 50)
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "id", length = 50, nullable = false)
    private String id;

	@NotNull
	@Size(max = 50)
	@Column(name = "entity_name", length = 50, nullable = false)
	private String entity;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "attachment_group_parent_id")
	@JsonIgnoreProperties(value = { "attachments", "attachmentGroupChildren" })
	private AttachmentGroup attachmentGroupParent;

	@Column(name = "description", length = 400)
	private String description;

	@Column(name = "name", length = 100)
	private String name;

	// only applicable for local storage
	@Column(name = "base_path", length = 100)
	private String basePath;

	@Transient
	private List<Attachment> attachments = new ArrayList<>();

	@OneToMany(mappedBy = "attachmentGroupParent", cascade = { CascadeType.REMOVE })
	private Set<AttachmentGroup> attachmentGroupChildren = new LinkedHashSet<>();

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		AttachmentGroup attachmentGroup = (AttachmentGroup) o;
		if (attachmentGroup.getId() == null || getId() == null) {
			return false;
		}
		return Objects.equals(getId(), attachmentGroup.getId());
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(getId());
	}

	@Override
	public String toString() {
		return "AttachmentGroup{" + "id=" + getId() + ", entity='" + getEntity() + "'" + ", createdBy='"
				+ getCreatedBy() + "'" + ", createdDate='" + getCreatedDate() + "'" + ", lastModifiedBy='"
				+ getLastModifiedBy() + "'" + ", lastModifiedDate='" + getLastModifiedDate() + "'" + "}";
	}

    @Override
    public boolean isNew() {
        return false;
    }
}
