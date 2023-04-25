package com.darfat.docreaderapp.web.rest;

import com.darfat.docreaderapp.constants.AttachmentTypeEnum;
import com.darfat.docreaderapp.domain.Documents;
import com.darfat.docreaderapp.dto.AttachmentDTO;
import com.darfat.docreaderapp.dto.request.AttachmentRequest;
import com.darfat.docreaderapp.dto.response.AttachmentGroupResponse;
import com.darfat.docreaderapp.repository.DocumentsRepository;
import com.darfat.docreaderapp.service.DocumentsQueryService;
import com.darfat.docreaderapp.service.DocumentsService;
import com.darfat.docreaderapp.service.criteria.DocumentsCriteria;
import com.darfat.docreaderapp.web.rest.errors.BadRequestAlertException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;
import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gcp.vision.CloudVisionTemplate;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.darfat.docreaderapp.domain.Documents}.
 */
@RestController
@RequestMapping("/api")
public class DocumentsResource {

    private final Logger log = LoggerFactory.getLogger(DocumentsResource.class);

    private static final String ENTITY_NAME = "documents";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DocumentsService documentsService;

    private final DocumentsRepository documentsRepository;

    private final DocumentsQueryService documentsQueryService;
    private CloudVisionTemplate cloudVisionTemplate;

    public DocumentsResource(
        DocumentsService documentsService,
        DocumentsRepository documentsRepository,
        DocumentsQueryService documentsQueryService,
        CloudVisionTemplate cloudVisionTemplate
    ) {
        this.documentsService = documentsService;
        this.documentsRepository = documentsRepository;
        this.documentsQueryService = documentsQueryService;
        this.cloudVisionTemplate = cloudVisionTemplate;
    }

    /**
     * {@code POST  /documents} : Create a new documents.
     *
     * @param documents the documents to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new documents, or with status {@code 400 (Bad Request)} if the documents has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/documents")
    public ResponseEntity<Documents> createDocuments(@Valid @RequestBody Documents documents) throws URISyntaxException {
        log.debug("REST request to save Documents : {}", documents);
        if (documents.getId() != null) {
            throw new BadRequestAlertException("A new documents cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Documents result = documentsService.save(documents);
        return ResponseEntity
            .created(new URI("/api/documents/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId()))
            .body(result);
    }

    /**
     * {@code PUT  /documents/:id} : Updates an existing documents.
     *
     * @param id        the id of the documents to save.
     * @param documents the documents to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated documents,
     * or with status {@code 400 (Bad Request)} if the documents is not valid,
     * or with status {@code 500 (Internal Server Error)} if the documents couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/documents/{id}")
    public ResponseEntity<Documents> updateDocuments(
        @PathVariable(value = "id", required = false) final String id,
        @Valid @RequestBody Documents documents
    ) throws URISyntaxException {
        log.debug("REST request to update Documents : {}, {}", id, documents);
        if (documents.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, documents.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!documentsRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Documents result = documentsService.update(documents);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, documents.getId()))
            .body(result);
    }

    /**
     * {@code PATCH  /documents/:id} : Partial updates given fields of an existing documents, field will ignore if it is null
     *
     * @param id        the id of the documents to save.
     * @param documents the documents to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated documents,
     * or with status {@code 400 (Bad Request)} if the documents is not valid,
     * or with status {@code 404 (Not Found)} if the documents is not found,
     * or with status {@code 500 (Internal Server Error)} if the documents couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/documents/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Documents> partialUpdateDocuments(
        @PathVariable(value = "id", required = false) final String id,
        @NotNull @RequestBody Documents documents
    ) throws URISyntaxException {
        log.debug("REST request to partial update Documents partially : {}, {}", id, documents);
        if (documents.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, documents.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!documentsRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Documents> result = documentsService.partialUpdate(documents);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, documents.getId())
        );
    }

    /**
     * {@code GET  /documents} : get all the documents.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of documents in body.
     */
    @GetMapping("/documents")
    public ResponseEntity<List<Documents>> getAllDocuments(
        DocumentsCriteria criteria,
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get Documents by criteria: {}", criteria);
        Page<Documents> page = documentsQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /documents/count} : count all the documents.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/documents/count")
    public ResponseEntity<Long> countDocuments(DocumentsCriteria criteria) {
        log.debug("REST request to count Documents by criteria: {}", criteria);
        return ResponseEntity.ok().body(documentsQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /documents/:id} : get the "id" documents.
     *
     * @param id the id of the documents to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the documents, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/documents/{id}")
    public ResponseEntity<Documents> getDocuments(@PathVariable String id) {
        log.debug("REST request to get Documents : {}", id);
        Optional<Documents> documents = documentsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(documents);
    }

    /**
     * {@code DELETE  /documents/:id} : delete the "id" documents.
     *
     * @param id the id of the documents to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/documents/{id}")
    public ResponseEntity<Void> deleteDocuments(@PathVariable String id) {
        log.debug("REST request to delete Documents : {}", id);
        documentsService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id)).build();
    }

    @PostMapping("/documents/{documentType}/upload")
    public ResponseEntity<Documents> uploadDocument(@PathVariable String documentType, @RequestBody AttachmentRequest attachmentRequest) {
        attachmentRequest.setClassName(Documents.class.getSimpleName());
        String fileName = attachmentRequest.getName();
        if (attachmentRequest.getBasePath() == null) {
            attachmentRequest.setBasePath(documentType);
        }
        AttachmentGroupResponse attachmentGroupResponse = documentsService.handleAttachment(attachmentRequest);
        Documents documents = new Documents();
        documents.setType(documentType);
        documents.setName(fileName);
        documents.setStatus("NEW");
        documents.setAttachmentGroupId(attachmentGroupResponse.getAttachmentGroupId());
        documents = documentsService.save(documents);

        return ResponseEntity.ok().body(documents);
    }

    @PostMapping("/documents/approved/{documentId}")
    public ResponseEntity<Documents> approvedDocument(@PathVariable String documentId) {
        Documents documents = documentsService.findOne(documentId).orElseThrow(EntityNotFoundException::new);
        Resource resource = documentsService.getDocumentFile(documents);
        String textFromImage = cloudVisionTemplate.extractTextFromImage(resource);
        documents = documentsService.approved(documents, textFromImage);

        return ResponseEntity.ok().body(documents);
    }

    @PostMapping("/documents/rejected/{documentId}")
    public ResponseEntity<Documents> rejectDocument(@PathVariable String documentId) {
        Documents documents = documentsService.findOne(documentId).orElseThrow(EntityNotFoundException::new);
        documents = documentsService.rejected(documents);
        return ResponseEntity.ok().body(documents);
    }

    @PostMapping("/documents/{documentType}/new")
    public ResponseEntity<Documents> newDocument(
        @PathVariable String documentType,
        @RequestParam("fileName") String fileName,
        @RequestParam("file") MultipartFile file
    ) throws IOException {
        byte[] encoded = Base64.getEncoder().encode(file.getBytes());
        String blobStr = new String(encoded);
        AttachmentRequest attachmentRequest = new AttachmentRequest();
        attachmentRequest.setName(fileName);
        attachmentRequest.setClassName(Documents.class.getSimpleName());
        if (attachmentRequest.getBasePath() == null) {
            attachmentRequest.setBasePath(documentType);
        }
        AttachmentDTO attachmentDTO = new AttachmentDTO();
        attachmentDTO.setName(fileName);
        attachmentDTO.setBlobFile(blobStr);
        attachmentDTO.setType(AttachmentTypeEnum.Image.name());
        List<AttachmentDTO> attachments = new ArrayList<>();
        attachments.add(attachmentDTO);
        attachmentRequest.setAttachments(attachments);
        AttachmentGroupResponse attachmentGroupResponse = documentsService.handleAttachment(attachmentRequest);
        Documents documents = new Documents();
        documents.setType(documentType);
        documents.setName(fileName);
        documents.setStatus("NEW");
        documents.setAttachmentGroupId(attachmentGroupResponse.getAttachmentGroupId());
        documents = documentsService.save(documents);

        return ResponseEntity.ok().body(documents);
    }
}
