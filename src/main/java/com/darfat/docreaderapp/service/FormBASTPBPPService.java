package com.darfat.docreaderapp.service;

import com.darfat.docreaderapp.domain.FormBASTPBPP;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link FormBASTPBPP}.
 */
public interface FormBASTPBPPService {
    /**
     * Save a formBASTPBPP.
     *
     * @param formBASTPBPP the entity to save.
     * @return the persisted entity.
     */
    FormBASTPBPP save(FormBASTPBPP formBASTPBPP);

    /**
     * Updates a formBASTPBPP.
     *
     * @param formBASTPBPP the entity to update.
     * @return the persisted entity.
     */
    FormBASTPBPP update(FormBASTPBPP formBASTPBPP);

    /**
     * Partially updates a formBASTPBPP.
     *
     * @param formBASTPBPP the entity to update partially.
     * @return the persisted entity.
     */
    Optional<FormBASTPBPP> partialUpdate(FormBASTPBPP formBASTPBPP);

    /**
     * Get all the formBASTPBPPS.
     *
     * @return the list of entities.
     */
    List<FormBASTPBPP> findAll();

    /**
     * Get the "id" formBASTPBPP.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<FormBASTPBPP> findOne(String id);

    /**
     * Delete the "id" formBASTPBPP.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
