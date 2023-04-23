package com.darfat.docreaderapp.service;

import com.darfat.docreaderapp.domain.FormPengeluaranBarang;
import com.darfat.docreaderapp.dto.FormPengeluaranBarangDTO;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link FormPengeluaranBarang}.
 */
public interface FormPengeluaranBarangService {
    /**
     * Save a formPengeluaranBarang.
     *
     * @param formPengeluaranBarang the entity to save.
     * @return the persisted entity.
     */
    FormPengeluaranBarang save(FormPengeluaranBarang formPengeluaranBarang);

    /**
     * Updates a formPengeluaranBarang.
     *
     * @param formPengeluaranBarang the entity to update.
     * @return the persisted entity.
     */
    FormPengeluaranBarang update(FormPengeluaranBarang formPengeluaranBarang);

    /**
     * Partially updates a formPengeluaranBarang.
     *
     * @param formPengeluaranBarang the entity to update partially.
     * @return the persisted entity.
     */
    Optional<FormPengeluaranBarang> partialUpdate(FormPengeluaranBarang formPengeluaranBarang);

    /**
     * Get all the formPengeluaranBarangs.
     *
     * @return the list of entities.
     */
    List<FormPengeluaranBarang> findAll();

    /**
     * Get the "id" formPengeluaranBarang.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<FormPengeluaranBarang> findOne(String id);

    /**
     * Delete the "id" formPengeluaranBarang.
     *
     * @param id the id of the entity.
     */
    void delete(String id);

    FormPengeluaranBarangDTO convertText(String text);
}
