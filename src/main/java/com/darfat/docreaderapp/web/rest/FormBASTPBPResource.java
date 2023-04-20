package com.darfat.docreaderapp.web.rest;

import com.darfat.docreaderapp.domain.FormBASTPBP;
import com.darfat.docreaderapp.repository.FormBASTPBPRepository;
import com.darfat.docreaderapp.service.FormBASTPBPService;
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
 * REST controller for managing {@link com.darfat.docreaderapp.domain.FormBASTPBP}.
 */
@RestController
@RequestMapping("/api")
public class FormBASTPBPResource {

    private final Logger log = LoggerFactory.getLogger(FormBASTPBPResource.class);

    private static final String ENTITY_NAME = "formBASTPBP";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final FormBASTPBPService formBASTPBPService;

    private final FormBASTPBPRepository formBASTPBPRepository;

    public FormBASTPBPResource(FormBASTPBPService formBASTPBPService, FormBASTPBPRepository formBASTPBPRepository) {
        this.formBASTPBPService = formBASTPBPService;
        this.formBASTPBPRepository = formBASTPBPRepository;
    }

    /**
     * {@code POST  /form-bastpbps} : Create a new formBASTPBP.
     *
     * @param formBASTPBP the formBASTPBP to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new formBASTPBP, or with status {@code 400 (Bad Request)} if the formBASTPBP has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/form-bastpbps")
    public ResponseEntity<FormBASTPBP> createFormBASTPBP(@Valid @RequestBody FormBASTPBP formBASTPBP) throws URISyntaxException {
        log.debug("REST request to save FormBASTPBP : {}", formBASTPBP);
        if (formBASTPBP.getId() != null) {
            throw new BadRequestAlertException("A new formBASTPBP cannot already have an ID", ENTITY_NAME, "idexists");
        }
        FormBASTPBP result = formBASTPBPService.save(formBASTPBP);
        return ResponseEntity
            .created(new URI("/api/form-bastpbps/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId()))
            .body(result);
    }

    /**
     * {@code PUT  /form-bastpbps/:id} : Updates an existing formBASTPBP.
     *
     * @param id the id of the formBASTPBP to save.
     * @param formBASTPBP the formBASTPBP to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated formBASTPBP,
     * or with status {@code 400 (Bad Request)} if the formBASTPBP is not valid,
     * or with status {@code 500 (Internal Server Error)} if the formBASTPBP couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/form-bastpbps/{id}")
    public ResponseEntity<FormBASTPBP> updateFormBASTPBP(
        @PathVariable(value = "id", required = false) final String id,
        @Valid @RequestBody FormBASTPBP formBASTPBP
    ) throws URISyntaxException {
        log.debug("REST request to update FormBASTPBP : {}, {}", id, formBASTPBP);
        if (formBASTPBP.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, formBASTPBP.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!formBASTPBPRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        FormBASTPBP result = formBASTPBPService.update(formBASTPBP);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, formBASTPBP.getId()))
            .body(result);
    }

    /**
     * {@code PATCH  /form-bastpbps/:id} : Partial updates given fields of an existing formBASTPBP, field will ignore if it is null
     *
     * @param id the id of the formBASTPBP to save.
     * @param formBASTPBP the formBASTPBP to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated formBASTPBP,
     * or with status {@code 400 (Bad Request)} if the formBASTPBP is not valid,
     * or with status {@code 404 (Not Found)} if the formBASTPBP is not found,
     * or with status {@code 500 (Internal Server Error)} if the formBASTPBP couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/form-bastpbps/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<FormBASTPBP> partialUpdateFormBASTPBP(
        @PathVariable(value = "id", required = false) final String id,
        @NotNull @RequestBody FormBASTPBP formBASTPBP
    ) throws URISyntaxException {
        log.debug("REST request to partial update FormBASTPBP partially : {}, {}", id, formBASTPBP);
        if (formBASTPBP.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, formBASTPBP.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!formBASTPBPRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<FormBASTPBP> result = formBASTPBPService.partialUpdate(formBASTPBP);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, formBASTPBP.getId())
        );
    }

    /**
     * {@code GET  /form-bastpbps} : get all the formBASTPBPS.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of formBASTPBPS in body.
     */
    @GetMapping("/form-bastpbps")
    public List<FormBASTPBP> getAllFormBASTPBPS() {
        log.debug("REST request to get all FormBASTPBPS");
        return formBASTPBPService.findAll();
    }

    /**
     * {@code GET  /form-bastpbps/:id} : get the "id" formBASTPBP.
     *
     * @param id the id of the formBASTPBP to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the formBASTPBP, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/form-bastpbps/{id}")
    public ResponseEntity<FormBASTPBP> getFormBASTPBP(@PathVariable String id) {
        log.debug("REST request to get FormBASTPBP : {}", id);
        Optional<FormBASTPBP> formBASTPBP = formBASTPBPService.findOne(id);
        return ResponseUtil.wrapOrNotFound(formBASTPBP);
    }

    /**
     * {@code DELETE  /form-bastpbps/:id} : delete the "id" formBASTPBP.
     *
     * @param id the id of the formBASTPBP to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/form-bastpbps/{id}")
    public ResponseEntity<Void> deleteFormBASTPBP(@PathVariable String id) {
        log.debug("REST request to delete FormBASTPBP : {}", id);
        formBASTPBPService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id)).build();
    }
}
