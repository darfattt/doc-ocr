package com.darfat.docreaderapp.repository;

import com.darfat.docreaderapp.domain.AttachmentGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the AttachmentGroup entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AttachmentGroupRepository extends JpaRepository<AttachmentGroup, String> {

	AttachmentGroup findTopByEntityOrderByIdDesc(String entity);

}
