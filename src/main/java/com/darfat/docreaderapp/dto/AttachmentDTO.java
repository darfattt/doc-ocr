package com.darfat.docreaderapp.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class AttachmentDTO {

	private String name;

	private String type;

	private String referenceNumber;

	private String description;

	private AttachmentGroupDTO attachmentGroup;

	@JsonSerialize
	@JsonDeserialize
	private String blobFile;

	@JsonSerialize
	@JsonDeserialize
	private Boolean removeFlag;

}
