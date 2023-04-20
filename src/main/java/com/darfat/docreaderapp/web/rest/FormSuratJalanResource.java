package com.darfat.docreaderapp.web.rest;

import com.darfat.docreaderapp.domain.FormSuratJalan;
import com.darfat.docreaderapp.repository.FormSuratJalanRepository;
import com.darfat.docreaderapp.service.FormSuratJalanService;
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
 * REST controller for managing {@link com.darfat.docreaderapp.domain.FormSuratJalan}.
 */
@RestController
@RequestMapping("/api")
public class FormSuratJalanResource {

    private final Logger log = LoggerFactory.getLogger(FormSuratJalanResource.class);

    private static final String ENTITY_NAME = "formSuratJalan";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final FormSuratJalanService formSuratJalanService;

    private final FormSuratJalanRepository formSuratJalanRepository;

    public FormSuratJalanResource(FormSuratJalanService formSuratJalanService, FormSuratJalanRepository formSuratJalanRepository) {
        this.formSuratJalanService = formSuratJalanService;
        this.formSuratJalanRepository = formSuratJalanRepository;
    }

    /**
     * {@code POST  /form-surat-jalans} : Create a new formSuratJalan.
     *
     * @param formSuratJalan the formSuratJalan to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new formSuratJalan, or with status {@code 400 (Bad Request)} if the formSuratJalan has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/form-surat-jalans")
    public ResponseEntity<FormSuratJalan> createFormSuratJalan(@Valid @RequestBody FormSuratJalan formSuratJalan)
        throws URISyntaxException {
        log.debug("REST request to save FormSuratJalan : {}", formSuratJalan);
        if (formSuratJalan.getId() != null) {
            throw new BadRequestAlertException("A new formSuratJalan cannot already have an ID", ENTITY_NAME, "idexists");
        }
        FormSuratJalan result = formSuratJalanService.save(formSuratJalan);
        return ResponseEntity
            .created(new URI("/api/form-surat-jalans/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId()))
            .body(result);
    }

    /**
     * {@code PUT  /form-surat-jalans/:id} : Updates an existing formSuratJalan.
     *
     * @param id the id of the formSuratJalan to save.
     * @param formSuratJalan the formSuratJalan to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated formSuratJalan,
     * or with status {@code 400 (Bad Request)} if the formSuratJalan is not valid,
     * or with status {@code 500 (Internal Server Error)} if the formSuratJalan couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/form-surat-jalans/{id}")
    public ResponseEntity<FormSuratJalan> updateFormSuratJalan(
        @PathVariable(value = "id", required = false) final String id,
        @Valid @RequestBody FormSuratJalan formSuratJalan
    ) throws URISyntaxException {
        log.debug("REST request to update FormSuratJalan : {}, {}", id, formSuratJalan);
        if (formSuratJalan.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, formSuratJalan.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!formSuratJalanRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        FormSuratJalan result = formSuratJalanService.update(formSuratJalan);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, formSuratJalan.getId()))
            .body(result);
    }

    /**
     * {@code PATCH  /form-surat-jalans/:id} : Partial updates given fields of an existing formSuratJalan, field will ignore if it is null
     *
     * @param id the id of the formSuratJalan to save.
     * @param formSuratJalan the formSuratJalan to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated formSuratJalan,
     * or with status {@code 400 (Bad Request)} if the formSuratJalan is not valid,
     * or with status {@code 404 (Not Found)} if the formSuratJalan is not found,
     * or with status {@code 500 (Internal Server Error)} if the formSuratJalan couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/form-surat-jalans/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<FormSuratJalan> partialUpdateFormSuratJalan(
        @PathVariable(value = "id", required = false) final String id,
        @NotNull @RequestBody FormSuratJalan formSuratJalan
    ) throws URISyntaxException {
        log.debug("REST request to partial update FormSuratJalan partially : {}, {}", id, formSuratJalan);
        if (formSuratJalan.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, formSuratJalan.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!formSuratJalanRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<FormSuratJalan> result = formSuratJalanService.partialUpdate(formSuratJalan);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, formSuratJalan.getId())
        );
    }

    /**
     * {@code GET  /form-surat-jalans} : get all the formSuratJalans.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of formSuratJalans in body.
     */
    @GetMapping("/form-surat-jalans")
    public List<FormSuratJalan> getAllFormSuratJalans() {
        log.debug("REST request to get all FormSuratJalans");
        return formSuratJalanService.findAll();
    }

    /**
     * {@code GET  /form-surat-jalans/:id} : get the "id" formSuratJalan.
     *
     * @param id the id of the formSuratJalan to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the formSuratJalan, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/form-surat-jalans/{id}")
    public ResponseEntity<FormSuratJalan> getFormSuratJalan(@PathVariable String id) {
        log.debug("REST request to get FormSuratJalan : {}", id);
        Optional<FormSuratJalan> formSuratJalan = formSuratJalanService.findOne(id);
        return ResponseUtil.wrapOrNotFound(formSuratJalan);
    }

    /**
     * {@code DELETE  /form-surat-jalans/:id} : delete the "id" formSuratJalan.
     *
     * @param id the id of the formSuratJalan to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/form-surat-jalans/{id}")
    public ResponseEntity<Void> deleteFormSuratJalan(@PathVariable String id) {
        log.debug("REST request to delete FormSuratJalan : {}", id);
        formSuratJalanService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id)).build();
    }
}
