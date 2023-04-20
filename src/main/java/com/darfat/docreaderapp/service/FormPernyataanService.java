package com.darfat.docreaderapp.service;

import com.darfat.docreaderapp.domain.FormPernyataan;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link FormPernyataan}.
 */
public interface FormPernyataanService {
    /**
     * Save a formPernyataan.
     *
     * @param formPernyataan the entity to save.
     * @return the persisted entity.
     */
    FormPernyataan save(FormPernyataan formPernyataan);

    /**
     * Updates a formPernyataan.
     *
     * @param formPernyataan the entity to update.
     * @return the persisted entity.
     */
    FormPernyataan update(FormPernyataan formPernyataan);

    /**
     * Partially updates a formPernyataan.
     *
     * @param formPernyataan the entity to update partially.
     * @return the persisted entity.
     */
    Optional<FormPernyataan> partialUpdate(FormPernyataan formPernyataan);

    /**
     * Get all the formPernyataans.
     *
     * @return the list of entities.
     */
    List<FormPernyataan> findAll();

    /**
     * Get the "id" formPernyataan.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<FormPernyataan> findOne(String id);

    /**
     * Delete the "id" formPernyataan.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
