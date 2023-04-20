package com.darfat.docreaderapp.web.rest;

import com.darfat.docreaderapp.domain.Documents;
import com.darfat.docreaderapp.domain.VerifiedDocuments;
import com.darfat.docreaderapp.repository.VerifiedDocumentsRepository;
import com.darfat.docreaderapp.service.VerifiedDocumentsQueryService;
import com.darfat.docreaderapp.service.VerifiedDocumentsService;
import com.darfat.docreaderapp.service.criteria.VerifiedDocumentsCriteria;
import com.darfat.docreaderapp.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.darfat.docreaderapp.domain.VerifiedDocuments}.
 */
@RestController
@RequestMapping("/api")
public class VerifiedDocumentsResource {

    private final Logger log = LoggerFactory.getLogger(VerifiedDocumentsResource.class);

    private static final String ENTITY_NAME = "verifiedDocuments";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final VerifiedDocumentsService verifiedDocumentsService;

    private final VerifiedDocumentsRepository verifiedDocumentsRepository;

    private final VerifiedDocumentsQueryService verifiedDocumentsQueryService;

    public VerifiedDocumentsResource(
        VerifiedDocumentsService verifiedDocumentsService,
        VerifiedDocumentsRepository verifiedDocumentsRepository,
        VerifiedDocumentsQueryService verifiedDocumentsQueryService
    ) {
        this.verifiedDocumentsService = verifiedDocumentsService;
        this.verifiedDocumentsRepository = verifiedDocumentsRepository;
        this.verifiedDocumentsQueryService = verifiedDocumentsQueryService;
    }

    /**
     * {@code POST  /verified-documents} : Create a new verifiedDocuments.
     *
     * @param verifiedDocuments the verifiedDocuments to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new verifiedDocuments, or with status {@code 400 (Bad Request)} if the verifiedDocuments has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/verified-documents")
    public ResponseEntity<VerifiedDocuments> createVerifiedDocuments(@Valid @RequestBody VerifiedDocuments verifiedDocuments)
        throws URISyntaxException {
        log.debug("REST request to save VerifiedDocuments : {}", verifiedDocuments);
        if (verifiedDocuments.getId() != null) {
            throw new BadRequestAlertException("A new verifiedDocuments cannot already have an ID", ENTITY_NAME, "idexists");
        }
        VerifiedDocuments result = verifiedDocumentsService.save(verifiedDocuments);
        return ResponseEntity
            .created(new URI("/api/verified-documents/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId()))
            .body(result);
    }

    /**
     * {@code PUT  /verified-documents/:id} : Updates an existing verifiedDocuments.
     *
     * @param id the id of the verifiedDocuments to save.
     * @param verifiedDocuments the verifiedDocuments to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated verifiedDocuments,
     * or with status {@code 400 (Bad Request)} if the verifiedDocuments is not valid,
     * or with status {@code 500 (Internal Server Error)} if the verifiedDocuments couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/verified-documents/{id}")
    public ResponseEntity<VerifiedDocuments> updateVerifiedDocuments(
        @PathVariable(value = "id", required = false) final String id,
        @Valid @RequestBody VerifiedDocuments verifiedDocuments
    ) throws URISyntaxException {
        log.debug("REST request to update VerifiedDocuments : {}, {}", id, verifiedDocuments);
        if (verifiedDocuments.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, verifiedDocuments.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!verifiedDocumentsRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        VerifiedDocuments result = verifiedDocumentsService.update(verifiedDocuments);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, verifiedDocuments.getId()))
            .body(result);
    }

    /**
     * {@code PATCH  /verified-documents/:id} : Partial updates given fields of an existing verifiedDocuments, field will ignore if it is null
     *
     * @param id the id of the verifiedDocuments to save.
     * @param verifiedDocuments the verifiedDocuments to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated verifiedDocuments,
     * or with status {@code 400 (Bad Request)} if the verifiedDocuments is not valid,
     * or with status {@code 404 (Not Found)} if the verifiedDocuments is not found,
     * or with status {@code 500 (Internal Server Error)} if the verifiedDocuments couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/verified-documents/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<VerifiedDocuments> partialUpdateVerifiedDocuments(
        @PathVariable(value = "id", required = false) final String id,
        @NotNull @RequestBody VerifiedDocuments verifiedDocuments
    ) throws URISyntaxException {
        log.debug("REST request to partial update VerifiedDocuments partially : {}, {}", id, verifiedDocuments);
        if (verifiedDocuments.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, verifiedDocuments.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!verifiedDocumentsRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<VerifiedDocuments> result = verifiedDocumentsService.partialUpdate(verifiedDocuments);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, verifiedDocuments.getId())
        );
    }

    /**
     * {@code GET  /verified-documents} : get all the verifiedDocuments.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of verifiedDocuments in body.
     */
    @GetMapping("/verified-documents")
    public ResponseEntity<List<VerifiedDocuments>> getAllVerifiedDocuments(
        VerifiedDocumentsCriteria criteria,
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get VerifiedDocuments by criteria: {}", criteria);
        Page<VerifiedDocuments> page = verifiedDocumentsQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /verified-documents/count} : count all the verifiedDocuments.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/verified-documents/count")
    public ResponseEntity<Long> countVerifiedDocuments(VerifiedDocumentsCriteria criteria) {
        log.debug("REST request to count VerifiedDocuments by criteria: {}", criteria);
        return ResponseEntity.ok().body(verifiedDocumentsQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /verified-documents/:id} : get the "id" verifiedDocuments.
     *
     * @param id the id of the verifiedDocuments to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the verifiedDocuments, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/verified-documents/{id}")
    public ResponseEntity<VerifiedDocuments> getVerifiedDocuments(@PathVariable String id) {
        log.debug("REST request to get VerifiedDocuments : {}", id);
        Optional<VerifiedDocuments> verifiedDocuments = verifiedDocumentsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(verifiedDocuments);
    }

    /**
     * {@code DELETE  /verified-documents/:id} : delete the "id" verifiedDocuments.
     *
     * @param id the id of the verifiedDocuments to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/verified-documents/{id}")
    public ResponseEntity<Void> deleteVerifiedDocuments(@PathVariable String id) {
        log.debug("REST request to delete VerifiedDocuments : {}", id);
        verifiedDocumentsService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id)).build();
    }

    @PostMapping("/documents/approved/{documentId}")
    public ResponseEntity<VerifiedDocuments> approvedDocument(@PathVariable String documentId) {
        VerifiedDocuments doc = verifiedDocumentsService.findOne(documentId).orElseThrow(EntityNotFoundException::new);
        verifiedDocumentsService.approved(doc);
        return ResponseEntity
            .ok()
            .body(doc);
    }

    @PostMapping("/documents/save-and-approved/{documentId}")
    public ResponseEntity<VerifiedDocuments> approvedDocument(@PathVariable String documentId,@RequestBody VerifiedDocuments doc) {
        verifiedDocumentsService.approved(doc);
        return ResponseEntity
            .ok()
            .body(doc);
    }
}
