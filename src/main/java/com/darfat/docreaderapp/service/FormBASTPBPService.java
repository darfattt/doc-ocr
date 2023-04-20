package com.darfat.docreaderapp.service;

import com.darfat.docreaderapp.domain.FormBASTPBP;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link FormBASTPBP}.
 */
public interface FormBASTPBPService {
    /**
     * Save a formBASTPBP.
     *
     * @param formBASTPBP the entity to save.
     * @return the persisted entity.
     */
    FormBASTPBP save(FormBASTPBP formBASTPBP);

    /**
     * Updates a formBASTPBP.
     *
     * @param formBASTPBP the entity to update.
     * @return the persisted entity.
     */
    FormBASTPBP update(FormBASTPBP formBASTPBP);

    /**
     * Partially updates a formBASTPBP.
     *
     * @param formBASTPBP the entity to update partially.
     * @return the persisted entity.
     */
    Optional<FormBASTPBP> partialUpdate(FormBASTPBP formBASTPBP);

    /**
     * Get all the formBASTPBPS.
     *
     * @return the list of entities.
     */
    List<FormBASTPBP> findAll();

    /**
     * Get the "id" formBASTPBP.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<FormBASTPBP> findOne(String id);

    /**
     * Delete the "id" formBASTPBP.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
