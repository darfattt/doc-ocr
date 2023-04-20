package com.darfat.docreaderapp.web.rest;

import com.darfat.docreaderapp.domain.FormBASTPBPP;
import com.darfat.docreaderapp.repository.FormBASTPBPPRepository;
import com.darfat.docreaderapp.service.FormBASTPBPPService;
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
 * REST controller for managing {@link com.darfat.docreaderapp.domain.FormBASTPBPP}.
 */
@RestController
@RequestMapping("/api")
public class FormBASTPBPPResource {

    private final Logger log = LoggerFactory.getLogger(FormBASTPBPPResource.class);

    private static final String ENTITY_NAME = "formBASTPBPP";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final FormBASTPBPPService formBASTPBPPService;

    private final FormBASTPBPPRepository formBASTPBPPRepository;

    public FormBASTPBPPResource(FormBASTPBPPService formBASTPBPPService, FormBASTPBPPRepository formBASTPBPPRepository) {
        this.formBASTPBPPService = formBASTPBPPService;
        this.formBASTPBPPRepository = formBASTPBPPRepository;
    }

    /**
     * {@code POST  /form-bastpbpps} : Create a new formBASTPBPP.
     *
     * @param formBASTPBPP the formBASTPBPP to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new formBASTPBPP, or with status {@code 400 (Bad Request)} if the formBASTPBPP has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/form-bastpbpps")
    public ResponseEntity<FormBASTPBPP> createFormBASTPBPP(@Valid @RequestBody FormBASTPBPP formBASTPBPP) throws URISyntaxException {
        log.debug("REST request to save FormBASTPBPP : {}", formBASTPBPP);
        if (formBASTPBPP.getId() != null) {
            throw new BadRequestAlertException("A new formBASTPBPP cannot already have an ID", ENTITY_NAME, "idexists");
        }
        FormBASTPBPP result = formBASTPBPPService.save(formBASTPBPP);
        return ResponseEntity
            .created(new URI("/api/form-bastpbpps/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId()))
            .body(result);
    }

    /**
     * {@code PUT  /form-bastpbpps/:id} : Updates an existing formBASTPBPP.
     *
     * @param id the id of the formBASTPBPP to save.
     * @param formBASTPBPP the formBASTPBPP to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated formBASTPBPP,
     * or with status {@code 400 (Bad Request)} if the formBASTPBPP is not valid,
     * or with status {@code 500 (Internal Server Error)} if the formBASTPBPP couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/form-bastpbpps/{id}")
    public ResponseEntity<FormBASTPBPP> updateFormBASTPBPP(
        @PathVariable(value = "id", required = false) final String id,
        @Valid @RequestBody FormBASTPBPP formBASTPBPP
    ) throws URISyntaxException {
        log.debug("REST request to update FormBASTPBPP : {}, {}", id, formBASTPBPP);
        if (formBASTPBPP.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, formBASTPBPP.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!formBASTPBPPRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        FormBASTPBPP result = formBASTPBPPService.update(formBASTPBPP);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, formBASTPBPP.getId()))
            .body(result);
    }

    /**
     * {@code PATCH  /form-bastpbpps/:id} : Partial updates given fields of an existing formBASTPBPP, field will ignore if it is null
     *
     * @param id the id of the formBASTPBPP to save.
     * @param formBASTPBPP the formBASTPBPP to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated formBASTPBPP,
     * or with status {@code 400 (Bad Request)} if the formBASTPBPP is not valid,
     * or with status {@code 404 (Not Found)} if the formBASTPBPP is not found,
     * or with status {@code 500 (Internal Server Error)} if the formBASTPBPP couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/form-bastpbpps/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<FormBASTPBPP> partialUpdateFormBASTPBPP(
        @PathVariable(value = "id", required = false) final String id,
        @NotNull @RequestBody FormBASTPBPP formBASTPBPP
    ) throws URISyntaxException {
        log.debug("REST request to partial update FormBASTPBPP partially : {}, {}", id, formBASTPBPP);
        if (formBASTPBPP.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, formBASTPBPP.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!formBASTPBPPRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<FormBASTPBPP> result = formBASTPBPPService.partialUpdate(formBASTPBPP);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, formBASTPBPP.getId())
        );
    }

    /**
     * {@code GET  /form-bastpbpps} : get all the formBASTPBPPS.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of formBASTPBPPS in body.
     */
    @GetMapping("/form-bastpbpps")
    public List<FormBASTPBPP> getAllFormBASTPBPPS() {
        log.debug("REST request to get all FormBASTPBPPS");
        return formBASTPBPPService.findAll();
    }

    /**
     * {@code GET  /form-bastpbpps/:id} : get the "id" formBASTPBPP.
     *
     * @param id the id of the formBASTPBPP to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the formBASTPBPP, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/form-bastpbpps/{id}")
    public ResponseEntity<FormBASTPBPP> getFormBASTPBPP(@PathVariable String id) {
        log.debug("REST request to get FormBASTPBPP : {}", id);
        Optional<FormBASTPBPP> formBASTPBPP = formBASTPBPPService.findOne(id);
        return ResponseUtil.wrapOrNotFound(formBASTPBPP);
    }

    /**
     * {@code DELETE  /form-bastpbpps/:id} : delete the "id" formBASTPBPP.
     *
     * @param id the id of the formBASTPBPP to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/form-bastpbpps/{id}")
    public ResponseEntity<Void> deleteFormBASTPBPP(@PathVariable String id) {
        log.debug("REST request to delete FormBASTPBPP : {}", id);
        formBASTPBPPService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id)).build();
    }
}
