package com.darfat.docreaderapp.repository;

import com.darfat.docreaderapp.domain.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data JPA repository for the Attachment entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AttachmentRepository extends JpaRepository<Attachment, String> {

	List<Attachment> findAllByAttachmentGroupId(@Param("attachmentGroupId") String attachmentGroupId);

	Attachment findTopByAttachmentGroupIdOrderByIdDesc(String attachmentGroupId);

	List<Attachment> findAllByAttachmentGroupIdOrderByCreatedDateDesc(String attachmentGroupId);

	List<Attachment> findAllByAttachmentGroup_AttachmentGroupParent_IdOrderByIdAsc(Long id);

}
