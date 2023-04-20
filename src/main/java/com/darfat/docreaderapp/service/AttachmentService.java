package com.darfat.docreaderapp.service;


import com.darfat.docreaderapp.domain.Attachment;
import com.darfat.docreaderapp.domain.AttachmentGroup;
import com.darfat.docreaderapp.dto.OutputContentFile;
import com.darfat.docreaderapp.dto.request.AttachmentRequest;

import java.util.List;
import java.util.Set;

public interface AttachmentService {

	public <T> AttachmentGroup createAttachmentGroup(Class<T> object);

	public <T> AttachmentGroup createAttachmentGroup(Class<T> object, String attachmentGroupParentId,
			String description, String name, String basePath);

	public <T> AttachmentGroup createAttachmentGroup(String entityName, String attachmentGroupParentId,
			String description, String name, String basePath);

	public List<Attachment> saveAttachment(AttachmentGroup attachmentGroup, Set<Attachment> attachments,
			String reference) throws Exception;

	public Boolean removeGroup(AttachmentGroup attachmentGroupRequest);

	public void removeAttachment(String attachmentId, String reference) throws Exception;

	public OutputContentFile downloadAttachment(String id);

	public List<Attachment> findAllAttachmentByGroupId(String attachmentGroupId);

	public String generateUrl(Attachment attachment);

	public List<Attachment> saveAttachment(AttachmentRequest attachmentRequest);

}
