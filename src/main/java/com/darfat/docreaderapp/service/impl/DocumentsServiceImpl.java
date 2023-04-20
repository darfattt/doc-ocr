package com.darfat.docreaderapp.service.impl;

import com.darfat.docreaderapp.domain.Attachment;
import com.darfat.docreaderapp.domain.Documents;
import com.darfat.docreaderapp.domain.VerifiedDocuments;
import com.darfat.docreaderapp.dto.OutputContentFile;
import com.darfat.docreaderapp.dto.request.AttachmentRequest;
import com.darfat.docreaderapp.dto.response.AttachmentGroupResponse;
import com.darfat.docreaderapp.dto.response.AttachmentResponse;
import com.darfat.docreaderapp.repository.DocumentsRepository;
import com.darfat.docreaderapp.service.AttachmentService;
import com.darfat.docreaderapp.service.DocumentsService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.darfat.docreaderapp.service.VerifiedDocumentsService;
import com.darfat.docreaderapp.util.ObjectMapperUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

/**
 * Service Implementation for managing {@link Documents}.
 */
@Service
@Transactional
public class DocumentsServiceImpl implements DocumentsService {

    private final Logger log = LoggerFactory.getLogger(DocumentsServiceImpl.class);

    private final DocumentsRepository documentsRepository;
    private final VerifiedDocumentsService verifiedDocumentsService;
    private final AttachmentService attachmentService;

    public DocumentsServiceImpl(DocumentsRepository documentsRepository,
                                VerifiedDocumentsService verifiedDocumentsService, AttachmentService attachmentService) {
        this.documentsRepository = documentsRepository;
        this.verifiedDocumentsService = verifiedDocumentsService;
        this.attachmentService = attachmentService;
    }

    @Override
    public Documents save(Documents documents) {
        log.debug("Request to save Documents : {}", documents);
        return documentsRepository.save(documents);
    }

    @Override
    public Documents update(Documents documents) {
        log.debug("Request to update Documents : {}", documents);
        documents.setIsPersisted();
        return documentsRepository.save(documents);
    }

    @Override
    public Optional<Documents> partialUpdate(Documents documents) {
        log.debug("Request to partially update Documents : {}", documents);

        return documentsRepository
            .findById(documents.getId())
            .map(existingDocuments -> {
                if (documents.getType() != null) {
                    existingDocuments.setType(documents.getType());
                }
                if (documents.getName() != null) {
                    existingDocuments.setName(documents.getName());
                }
                if (documents.getStatus() != null) {
                    existingDocuments.setStatus(documents.getStatus());
                }
                if (documents.getCreatedDate() != null) {
                    existingDocuments.setCreatedDate(documents.getCreatedDate());
                }
                if (documents.getCreatedBy() != null) {
                    existingDocuments.setCreatedBy(documents.getCreatedBy());
                }
                if (documents.getLastModifiedDate() != null) {
                    existingDocuments.setLastModifiedDate(documents.getLastModifiedDate());
                }
                if (documents.getLastModifiedBy() != null) {
                    existingDocuments.setLastModifiedBy(documents.getLastModifiedBy());
                }

                return existingDocuments;
            })
            .map(documentsRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Documents> findAll(Pageable pageable) {
        log.debug("Request to get all Documents");
        return documentsRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Documents> findOne(String id) {
        log.debug("Request to get Documents : {}", id);
        return documentsRepository.findById(id);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete Documents : {}", id);
        documentsRepository.deleteById(id);
    }

    @Override
    public Documents approved(Documents documents, String contents) {
        documents.setStatus("Verify");
        Documents docResult = this.save(documents);
        VerifiedDocuments verifiedDocuments = ObjectMapperUtil.MAPPER.convertValue(docResult,VerifiedDocuments.class);
        verifiedDocuments.setId(null);

        verifiedDocumentsService.newDocument(verifiedDocuments,contents);
        return docResult;
    }

    @Override
    public AttachmentGroupResponse handleAttachment(AttachmentRequest attachmentRequest) {
        String attachmentGroupId = attachmentRequest.getAttachmentGroupId();
        List<Attachment> attachmentResult = attachmentService.saveAttachment(attachmentRequest);
        List<AttachmentResponse> attachmentDTOList = attachmentResult.stream()
            .map(attachment -> ObjectMapperUtil.MAPPER.convertValue(attachment, AttachmentResponse.class)).collect(Collectors.toList());
        if (attachmentGroupId == null && !attachmentResult.isEmpty()) {
            attachmentGroupId = attachmentResult.get(0).getAttachmentGroup().getId();
        }
        return new AttachmentGroupResponse(attachmentGroupId, attachmentDTOList);
    }

    @Override
    public Resource getDocumentFile(Documents documents) {
        List<Attachment> attachments = attachmentService.findAllAttachmentByGroupId(documents.getAttachmentGroupId());
        if(attachments == null || attachments.isEmpty()) {
            throw new EntityNotFoundException("Attachment not found");
        }
        OutputContentFile attachmentResult = attachmentService.downloadAttachment(attachments.get(0).getId());
        // Convert the ByteArrayOutputStream to a byte array
        byte[] bytes = attachmentResult.getFile().toByteArray();
        return  new ByteArrayResource(bytes);
    }

    @Override
    public Documents rejected(Documents documents) {
        documents.setStatus("Rejected");
        Documents docResult = this.save(documents);
        return docResult;
    }
}
