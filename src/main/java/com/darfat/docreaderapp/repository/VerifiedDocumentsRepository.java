package com.darfat.docreaderapp.repository;

import com.darfat.docreaderapp.domain.VerifiedDocuments;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data JPA repository for the VerifiedDocuments entity.
 */
@SuppressWarnings("unused")
@Repository
public interface VerifiedDocumentsRepository
    extends JpaRepository<VerifiedDocuments, String>, JpaSpecificationExecutor<VerifiedDocuments> {
    List<VerifiedDocuments> findAllByStatus(String status);
}
