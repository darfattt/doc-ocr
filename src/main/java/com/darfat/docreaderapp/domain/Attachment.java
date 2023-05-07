package com.darfat.docreaderapp.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "attachments")
@JsonIdentityInfo(generator = ObjectIdGenerators.UUIDGenerator.class, scope = Attachment.class)
public class Attachment extends AbstractAuditingEntity<String>  implements Serializable, Persistable<String> {

    @NotNull
    @Size(max = 50)
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "id", length = 50, nullable = false)
    private String id;

	@Size(max = 400)
	@Column(name = "description", length = 400, nullable = true)
	private String description;

	@NotNull
	@Size(max = 200)
	@Column(name = "name", length = 200, nullable = false)
	private String name;

	@Size(max = 50)
	@Column(name = "attachment_type", length = 50)
	private String type;

	@Size(max = 50)
	@Column(name = "reference_number", length = 50)
	private String referenceNumber;

	@ManyToOne(optional = false)
	@NotNull
	@JsonIgnoreProperties(value = { "attachments", "attachmentGroupParent", "attachmentGroupChildren" })
	private AttachmentGroup attachmentGroup;

	@Transient
	@JsonSerialize
	@JsonDeserialize
	private String blobFile;

	@Transient
	@JsonSerialize
	@JsonDeserialize
	private Boolean removeFlag;

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Attachment attachment = (Attachment) o;
		if (attachment.getId() == null || getId() == null) {
			return false;
		}
		return Objects.equals(getId(), attachment.getId());
	}

	@Override
	public String toString() {
		return "Attachment{" + "id=" + getId() + ", groupId='" + getAttachmentGroup() + "'" + ", name='" + getName()
				+ "'" + ", type='" + getType() + "'" + ", createdBy='" + "getCreatedBy()" + "'" + ", createdDate='"
				+ "getCreatedDate()" + "'" + ", lastModifiedBy='" + "getLastModifiedBy()" + "'" + ", lastModifiedDate='"
				+ "getLastModifiedDate()" + "'" + "}";
	}

    @Override
    public boolean isNew() {
        return false;
    }
}
