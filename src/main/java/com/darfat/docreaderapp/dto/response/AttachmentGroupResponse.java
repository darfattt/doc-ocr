package com.darfat.docreaderapp.dto.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AttachmentGroupResponse {

	String attachmentGroupId;
    String basePath;
	List<AttachmentResponse> attachments;

}
