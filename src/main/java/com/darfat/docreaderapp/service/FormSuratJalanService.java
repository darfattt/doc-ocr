package com.darfat.docreaderapp.service;

import com.darfat.docreaderapp.domain.FormSuratJalan;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link FormSuratJalan}.
 */
public interface FormSuratJalanService {
    /**
     * Save a formSuratJalan.
     *
     * @param formSuratJalan the entity to save.
     * @return the persisted entity.
     */
    FormSuratJalan save(FormSuratJalan formSuratJalan);

    /**
     * Updates a formSuratJalan.
     *
     * @param formSuratJalan the entity to update.
     * @return the persisted entity.
     */
    FormSuratJalan update(FormSuratJalan formSuratJalan);

    /**
     * Partially updates a formSuratJalan.
     *
     * @param formSuratJalan the entity to update partially.
     * @return the persisted entity.
     */
    Optional<FormSuratJalan> partialUpdate(FormSuratJalan formSuratJalan);

    /**
     * Get all the formSuratJalans.
     *
     * @return the list of entities.
     */
    List<FormSuratJalan> findAll();

    /**
     * Get the "id" formSuratJalan.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<FormSuratJalan> findOne(String id);

    /**
     * Delete the "id" formSuratJalan.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
