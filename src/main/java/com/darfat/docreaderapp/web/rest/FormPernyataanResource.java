package com.darfat.docreaderapp.web.rest;

import com.darfat.docreaderapp.domain.FormPernyataan;
import com.darfat.docreaderapp.repository.FormPernyataanRepository;
import com.darfat.docreaderapp.service.FormPernyataanService;
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
 * REST controller for managing {@link com.darfat.docreaderapp.domain.FormPernyataan}.
 */
@RestController
@RequestMapping("/api")
public class FormPernyataanResource {

    private final Logger log = LoggerFactory.getLogger(FormPernyataanResource.class);

    private static final String ENTITY_NAME = "formPernyataan";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final FormPernyataanService formPernyataanService;

    private final FormPernyataanRepository formPernyataanRepository;

    public FormPernyataanResource(FormPernyataanService formPernyataanService, FormPernyataanRepository formPernyataanRepository) {
        this.formPernyataanService = formPernyataanService;
        this.formPernyataanRepository = formPernyataanRepository;
    }

    /**
     * {@code POST  /form-pernyataans} : Create a new formPernyataan.
     *
     * @param formPernyataan the formPernyataan to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new formPernyataan, or with status {@code 400 (Bad Request)} if the formPernyataan has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/form-pernyataans")
    public ResponseEntity<FormPernyataan> createFormPernyataan(@Valid @RequestBody FormPernyataan formPernyataan)
        throws URISyntaxException {
        log.debug("REST request to save FormPernyataan : {}", formPernyataan);
        if (formPernyataan.getId() != null) {
            throw new BadRequestAlertException("A new formPernyataan cannot already have an ID", ENTITY_NAME, "idexists");
        }
        FormPernyataan result = formPernyataanService.save(formPernyataan);
        return ResponseEntity
            .created(new URI("/api/form-pernyataans/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId()))
            .body(result);
    }

    /**
     * {@code PUT  /form-pernyataans/:id} : Updates an existing formPernyataan.
     *
     * @param id the id of the formPernyataan to save.
     * @param formPernyataan the formPernyataan to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated formPernyataan,
     * or with status {@code 400 (Bad Request)} if the formPernyataan is not valid,
     * or with status {@code 500 (Internal Server Error)} if the formPernyataan couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/form-pernyataans/{id}")
    public ResponseEntity<FormPernyataan> updateFormPernyataan(
        @PathVariable(value = "id", required = false) final String id,
        @Valid @RequestBody FormPernyataan formPernyataan
    ) throws URISyntaxException {
        log.debug("REST request to update FormPernyataan : {}, {}", id, formPernyataan);
        if (formPernyataan.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, formPernyataan.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!formPernyataanRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        FormPernyataan result = formPernyataanService.update(formPernyataan);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, formPernyataan.getId()))
            .body(result);
    }

    /**
     * {@code PATCH  /form-pernyataans/:id} : Partial updates given fields of an existing formPernyataan, field will ignore if it is null
     *
     * @param id the id of the formPernyataan to save.
     * @param formPernyataan the formPernyataan to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated formPernyataan,
     * or with status {@code 400 (Bad Request)} if the formPernyataan is not valid,
     * or with status {@code 404 (Not Found)} if the formPernyataan is not found,
     * or with status {@code 500 (Internal Server Error)} if the formPernyataan couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/form-pernyataans/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<FormPernyataan> partialUpdateFormPernyataan(
        @PathVariable(value = "id", required = false) final String id,
        @NotNull @RequestBody FormPernyataan formPernyataan
    ) throws URISyntaxException {
        log.debug("REST request to partial update FormPernyataan partially : {}, {}", id, formPernyataan);
        if (formPernyataan.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, formPernyataan.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!formPernyataanRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<FormPernyataan> result = formPernyataanService.partialUpdate(formPernyataan);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, formPernyataan.getId())
        );
    }

    /**
     * {@code GET  /form-pernyataans} : get all the formPernyataans.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of formPernyataans in body.
     */
    @GetMapping("/form-pernyataans")
    public List<FormPernyataan> getAllFormPernyataans() {
        log.debug("REST request to get all FormPernyataans");
        return formPernyataanService.findAll();
    }

    /**
     * {@code GET  /form-pernyataans/:id} : get the "id" formPernyataan.
     *
     * @param id the id of the formPernyataan to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the formPernyataan, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/form-pernyataans/{id}")
    public ResponseEntity<FormPernyataan> getFormPernyataan(@PathVariable String id) {
        log.debug("REST request to get FormPernyataan : {}", id);
        Optional<FormPernyataan> formPernyataan = formPernyataanService.findOne(id);
        return ResponseUtil.wrapOrNotFound(formPernyataan);
    }

    /**
     * {@code DELETE  /form-pernyataans/:id} : delete the "id" formPernyataan.
     *
     * @param id the id of the formPernyataan to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/form-pernyataans/{id}")
    public ResponseEntity<Void> deleteFormPernyataan(@PathVariable String id) {
        log.debug("REST request to delete FormPernyataan : {}", id);
        formPernyataanService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id)).build();
    }
}
