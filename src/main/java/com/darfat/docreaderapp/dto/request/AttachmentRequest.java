package com.darfat.docreaderapp.dto.request;

import com.darfat.docreaderapp.dto.AttachmentDTO;
import lombok.*;

import java.util.List;


@ToString
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AttachmentRequest {

	private String attachmentGroupId;

	private String attachmentGroupParentId;

	private String name;

	private String description;

	private String basePath;

	private String className;

	List<AttachmentDTO> attachments;

}
