package com.darfat.docreaderapp.service;

import com.darfat.docreaderapp.domain.Documents;
import java.util.Optional;

import com.darfat.docreaderapp.dto.request.AttachmentRequest;
import com.darfat.docreaderapp.dto.response.AttachmentGroupResponse;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link Documents}.
 */
public interface DocumentsService {
    /**
     * Save a documents.
     *
     * @param documents the entity to save.
     * @return the persisted entity.
     */
    Documents save(Documents documents);

    /**
     * Updates a documents.
     *
     * @param documents the entity to update.
     * @return the persisted entity.
     */
    Documents update(Documents documents);

    /**
     * Partially updates a documents.
     *
     * @param documents the entity to update partially.
     * @return the persisted entity.
     */
    Optional<Documents> partialUpdate(Documents documents);

    /**
     * Get all the documents.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<Documents> findAll(Pageable pageable);

    /**
     * Get the "id" documents.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Documents> findOne(String id);

    /**
     * Delete the "id" documents.
     *
     * @param id the id of the entity.
     */
    void delete(String id);

    Documents approved(Documents documents,String contents);
    AttachmentGroupResponse handleAttachment(AttachmentRequest attachmentRequest);
    Resource getDocumentFile(Documents documents);
    Documents rejected(Documents documents);
}
