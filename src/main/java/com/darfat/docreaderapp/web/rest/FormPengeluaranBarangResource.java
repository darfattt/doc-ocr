package com.darfat.docreaderapp.web.rest;

import com.darfat.docreaderapp.domain.FormPengeluaranBarang;
import com.darfat.docreaderapp.repository.FormPengeluaranBarangRepository;
import com.darfat.docreaderapp.service.FormPengeluaranBarangService;
import com.darfat.docreaderapp.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.darfat.docreaderapp.domain.FormPengeluaranBarang}.
 */
@RestController
@RequestMapping("/api")
public class FormPengeluaranBarangResource {

    private final Logger log = LoggerFactory.getLogger(FormPengeluaranBarangResource.class);

    private static final String ENTITY_NAME = "formPengeluaranBarang";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final FormPengeluaranBarangService formPengeluaranBarangService;

    private final FormPengeluaranBarangRepository formPengeluaranBarangRepository;

    public FormPengeluaranBarangResource(
        FormPengeluaranBarangService formPengeluaranBarangService,
        FormPengeluaranBarangRepository formPengeluaranBarangRepository
    ) {
        this.formPengeluaranBarangService = formPengeluaranBarangService;
        this.formPengeluaranBarangRepository = formPengeluaranBarangRepository;
    }

    /**
     * {@code POST  /form-pengeluaran-barangs} : Create a new formPengeluaranBarang.
     *
     * @param formPengeluaranBarang the formPengeluaranBarang to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new formPengeluaranBarang, or with status {@code 400 (Bad Request)} if the formPengeluaranBarang has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/form-pengeluaran-barangs")
    public ResponseEntity<FormPengeluaranBarang> createFormPengeluaranBarang(
        @Valid @RequestBody FormPengeluaranBarang formPengeluaranBarang
    ) throws URISyntaxException {
        log.debug("REST request to save FormPengeluaranBarang : {}", formPengeluaranBarang);
        if (formPengeluaranBarang.getId() != null) {
            throw new BadRequestAlertException("A new formPengeluaranBarang cannot already have an ID", ENTITY_NAME, "idexists");
        }
        FormPengeluaranBarang result = formPengeluaranBarangService.save(formPengeluaranBarang);
        return ResponseEntity
            .created(new URI("/api/form-pengeluaran-barangs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId()))
            .body(result);
    }

    /**
     * {@code PUT  /form-pengeluaran-barangs/:id} : Updates an existing formPengeluaranBarang.
     *
     * @param id the id of the formPengeluaranBarang to save.
     * @param formPengeluaranBarang the formPengeluaranBarang to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated formPengeluaranBarang,
     * or with status {@code 400 (Bad Request)} if the formPengeluaranBarang is not valid,
     * or with status {@code 500 (Internal Server Error)} if the formPengeluaranBarang couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/form-pengeluaran-barangs/{id}")
    public ResponseEntity<FormPengeluaranBarang> updateFormPengeluaranBarang(
        @PathVariable(value = "id", required = false) final String id,
        @Valid @RequestBody FormPengeluaranBarang formPengeluaranBarang
    ) throws URISyntaxException {
        log.debug("REST request to update FormPengeluaranBarang : {}, {}", id, formPengeluaranBarang);
        if (formPengeluaranBarang.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, formPengeluaranBarang.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!formPengeluaranBarangRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        FormPengeluaranBarang result = formPengeluaranBarangService.update(formPengeluaranBarang);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, formPengeluaranBarang.getId()))
            .body(result);
    }

    /**
     * {@code PATCH  /form-pengeluaran-barangs/:id} : Partial updates given fields of an existing formPengeluaranBarang, field will ignore if it is null
     *
     * @param id the id of the formPengeluaranBarang to save.
     * @param formPengeluaranBarang the formPengeluaranBarang to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated formPengeluaranBarang,
     * or with status {@code 400 (Bad Request)} if the formPengeluaranBarang is not valid,
     * or with status {@code 404 (Not Found)} if the formPengeluaranBarang is not found,
     * or with status {@code 500 (Internal Server Error)} if the formPengeluaranBarang couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/form-pengeluaran-barangs/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<FormPengeluaranBarang> partialUpdateFormPengeluaranBarang(
        @PathVariable(value = "id", required = false) final String id,
        @NotNull @RequestBody FormPengeluaranBarang formPengeluaranBarang
    ) throws URISyntaxException {
        log.debug("REST request to partial update FormPengeluaranBarang partially : {}, {}", id, formPengeluaranBarang);
        if (formPengeluaranBarang.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, formPengeluaranBarang.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!formPengeluaranBarangRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<FormPengeluaranBarang> result = formPengeluaranBarangService.partialUpdate(formPengeluaranBarang);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, formPengeluaranBarang.getId())
        );
    }

    /**
     * {@code GET  /form-pengeluaran-barangs} : get all the formPengeluaranBarangs.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of formPengeluaranBarangs in body.
     */
    @GetMapping("/form-pengeluaran-barangs")
    public List<FormPengeluaranBarang> getAllFormPengeluaranBarangs() {
        log.debug("REST request to get all FormPengeluaranBarangs");
        return formPengeluaranBarangService.findAll();
    }

    /**
     * {@code GET  /form-pengeluaran-barangs/:id} : get the "id" formPengeluaranBarang.
     *
     * @param id the id of the formPengeluaranBarang to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the formPengeluaranBarang, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/form-pengeluaran-barangs/{id}")
    public ResponseEntity<FormPengeluaranBarang> getFormPengeluaranBarang(@PathVariable String id) {
        log.debug("REST request to get FormPengeluaranBarang : {}", id);
        Optional<FormPengeluaranBarang> formPengeluaranBarang = formPengeluaranBarangService.findOne(id);
        return ResponseUtil.wrapOrNotFound(formPengeluaranBarang);
    }

    /**
     * {@code DELETE  /form-pengeluaran-barangs/:id} : delete the "id" formPengeluaranBarang.
     *
     * @param id the id of the formPengeluaranBarang to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/form-pengeluaran-barangs/{id}")
    public ResponseEntity<Void> deleteFormPengeluaranBarang(@PathVariable String id) {
        log.debug("REST request to delete FormPengeluaranBarang : {}", id);
        formPengeluaranBarangService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id)).build();
    }
}
