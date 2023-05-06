package com.darfat.docreaderapp.service;

import com.darfat.docreaderapp.domain.VerifiedDocuments;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link VerifiedDocuments}.
 */
public interface VerifiedDocumentsService {
    /**
     * Save a verifiedDocuments.
     *
     * @param verifiedDocuments the entity to save.
     * @return the persisted entity.
     */
    VerifiedDocuments save(VerifiedDocuments verifiedDocuments);

    /**
     * Updates a verifiedDocuments.
     *
     * @param verifiedDocuments the entity to update.
     * @return the persisted entity.
     */
    VerifiedDocuments update(VerifiedDocuments verifiedDocuments);

    /**
     * Partially updates a verifiedDocuments.
     *
     * @param verifiedDocuments the entity to update partially.
     * @return the persisted entity.
     */
    Optional<VerifiedDocuments> partialUpdate(VerifiedDocuments verifiedDocuments);

    /**
     * Get all the verifiedDocuments.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<VerifiedDocuments> findAll(Pageable pageable);

    /**
     * Get the "id" verifiedDocuments.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<VerifiedDocuments> findOne(String id);

    /**
     * Delete the "id" verifiedDocuments.
     *
     * @param id the id of the entity.
     */
    void delete(String id);

    VerifiedDocuments newDocument(VerifiedDocuments documents,String text);
    VerifiedDocuments approved(VerifiedDocuments documents);
    VerifiedDocuments classify(VerifiedDocuments documents,String text);
    VerifiedDocuments classifyDocumentPath(VerifiedDocuments documents, Resource originalFile) throws IOException;
    List<VerifiedDocuments> findAllByStatus(String status);
}
