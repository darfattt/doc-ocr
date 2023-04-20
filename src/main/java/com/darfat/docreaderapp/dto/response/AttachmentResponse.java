package com.darfat.docreaderapp.dto.response;

import lombok.*;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AttachmentResponse {

	private String id;

	private String name;

	private String type;

	private String referenceNumber;

	private String description;

	private String url;

	private String createdDate;

}
