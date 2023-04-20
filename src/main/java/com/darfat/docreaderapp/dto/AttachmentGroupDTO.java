package com.darfat.docreaderapp.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AttachmentGroupDTO {

	private String id;

	private String entity;

	private AttachmentGroupDTO attachmentGroupParent;

	private String description;

	private String name;

	private String basePath;

}
