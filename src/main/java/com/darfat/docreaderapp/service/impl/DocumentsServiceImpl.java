package com.darfat.docreaderapp.service.impl;

import com.darfat.docreaderapp.config.FileStorageProperties;
import com.darfat.docreaderapp.constants.AttachmentTypeEnum;
import com.darfat.docreaderapp.constants.DocumentsStatusEnum;
import com.darfat.docreaderapp.domain.Attachment;
import com.darfat.docreaderapp.domain.Documents;
import com.darfat.docreaderapp.domain.VerifiedDocuments;
import com.darfat.docreaderapp.dto.AttachmentDTO;
import com.darfat.docreaderapp.dto.OutputContentFile;
import com.darfat.docreaderapp.dto.request.AttachmentRequest;
import com.darfat.docreaderapp.dto.response.AttachmentGroupResponse;
import com.darfat.docreaderapp.dto.response.AttachmentResponse;
import com.darfat.docreaderapp.repository.DocumentsRepository;
import com.darfat.docreaderapp.service.AttachmentService;
import com.darfat.docreaderapp.service.DocumentsService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.darfat.docreaderapp.service.VerifiedDocumentsService;
import com.darfat.docreaderapp.util.DateConvertUtil;
import com.darfat.docreaderapp.util.LocalFileUtil;
import com.darfat.docreaderapp.util.ObjectMapperUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    private final ResourceLoader resourceLoader;

    private final FileStorageProperties fileStorageProperties;


    public DocumentsServiceImpl(DocumentsRepository documentsRepository,
                                VerifiedDocumentsService verifiedDocumentsService, AttachmentService attachmentService, ResourceLoader resourceLoader, FileStorageProperties fileStorageProperties) {
        this.documentsRepository = documentsRepository;
        this.verifiedDocumentsService = verifiedDocumentsService;
        this.attachmentService = attachmentService;
        this.resourceLoader = resourceLoader;
        this.fileStorageProperties = fileStorageProperties;
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
        documents.setStatus(DocumentsStatusEnum.VERIFIED.name());
        Documents docResult = this.save(documents);
        VerifiedDocuments verifiedDocuments = ObjectMapperUtil.MAPPER.convertValue(docResult,VerifiedDocuments.class);
        verifiedDocuments.setId(null);

        verifiedDocumentsService.newDocument(verifiedDocuments,contents);
        return docResult;
    }

    @Override
    public VerifiedDocuments verify(Documents documents, String contents) {
        VerifiedDocuments verifiedDocuments = ObjectMapperUtil.MAPPER.convertValue(documents,VerifiedDocuments.class);
        verifiedDocuments.setId(null);
        verifiedDocuments.setAttachmentGroupId(null);
        verifiedDocuments.setStatus(null);
        verifiedDocuments =  verifiedDocumentsService.classify(verifiedDocuments,contents);
        if(verifiedDocuments.getType()!=null) {
            documents.setStatus(DocumentsStatusEnum.VERIFIED.name());
            this.save(documents);
            verifiedDocuments = verifiedDocumentsService.save(verifiedDocuments);
            return verifiedDocuments;
        }
        return null;
    }

    @Override
    public AttachmentGroupResponse handleAttachment(AttachmentRequest attachmentRequest) {
        String attachmentGroupId = attachmentRequest.getAttachmentGroupId();
        String attachmentGroupBasePath = null;
        List<Attachment> attachmentResult = attachmentService.saveAttachment(attachmentRequest);
        List<AttachmentResponse> attachmentDTOList = attachmentResult.stream()
            .map(attachment -> ObjectMapperUtil.MAPPER.convertValue(attachment, AttachmentResponse.class)).collect(Collectors.toList());
        if (attachmentGroupId == null && !attachmentResult.isEmpty()) {
            attachmentGroupId = attachmentResult.get(0).getAttachmentGroup().getId();
            attachmentGroupBasePath=  attachmentResult.get(0).getAttachmentGroup().getBasePath();
        }
        return new AttachmentGroupResponse(attachmentGroupId, attachmentGroupBasePath,attachmentDTOList);
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

    @Override
    public List<Documents> scanFolderForTheSource() throws IOException {
        List<Documents> documents = new ArrayList<>();
        String originalBasePath = fileStorageProperties.getLocal().getOriginal();
        String sourceDirectory = fileStorageProperties.getLocal().getSourceFullPath();
        String targetDirectory =fileStorageProperties.getLocal().getRoot();

        try {
            File[] files = new File(sourceDirectory).listFiles();

            if (files != null && files.length > 0) {
                for (File file : files) {
                    if (file.isFile()) {
                        String originalFileName = file.getName();
                        String generatedFileName = LocalFileUtil.formatActualFile(String.valueOf(System.currentTimeMillis()), originalFileName);
                        Path sourcePath = Paths.get(file.getAbsolutePath());

                        AttachmentRequest attachmentRequest = this.generateAttachmentRequestWithEmptyFile(originalFileName,generatedFileName,originalBasePath);
                        AttachmentGroupResponse attachmentGroupResponse = handleAttachment(attachmentRequest);
                        Documents document = new Documents();
                        document.setName(originalFileName);
                        document.setStatus(DocumentsStatusEnum.NEW.name());
                        document.setAttachmentGroupId(attachmentGroupResponse.getAttachmentGroupId());
                        document = this.save(document);
                        documents.add(document);

                        String destinationFolder = new StringBuilder()
                            .append(targetDirectory)
                            .append(File.separator)
                            .append(attachmentGroupResponse.getBasePath()).toString();
                        Path targetPath = Paths.get(destinationFolder);
                        if (!Files.exists(targetPath)) {
                            Files.createDirectories(targetPath);
                        }
                        Path destinationPath = Paths.get(destinationFolder+File.separator+generatedFileName);
                        Files.move(sourcePath, destinationPath);
                    }
                }
                log.info("Files moved successfully.");
            } else {
                log.info("No files to move.");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return documents;
    }

    @Override
    public List<Documents> findAllByStatus(String status) {
        return documentsRepository.findAllByStatus(status);
    }

    private AttachmentRequest generateAttachmentRequestWithEmptyFile(String fileName,String generatedFileName,String basePath) throws IOException {
        AttachmentRequest attachmentRequest = new AttachmentRequest();
        attachmentRequest.setName(fileName);
        attachmentRequest.setBasePath(pathBuildPathWithYear(basePath)); //root/original/yyyy/mmm
        attachmentRequest.setClassName(Documents.class.getSimpleName());
        AttachmentDTO attachmentDTO = new AttachmentDTO();
        attachmentDTO.setName(generatedFileName);
        attachmentDTO.setBlobFile(null);
        attachmentDTO.setType(AttachmentTypeEnum.Image.name());
        List<AttachmentDTO> attachments = new ArrayList<>();
        attachments.add(attachmentDTO);
        attachmentRequest.setAttachments(attachments);
        return attachmentRequest;
    }
    private String pathBuildPathWithYear(String path) {
        String year = DateConvertUtil.toString(Instant.now(), DateConvertUtil.DATE_FORMAT_4);
        String month = DateConvertUtil.toString(Instant.now(), DateConvertUtil.DATE_FORMAT_10);
        String yyyymm = new StringBuilder()
            .append(year)
            .append(File.separator)
            .append(month).toString();
        if(path!=null){
            return new StringBuilder()
                .append(path)
                .append(File.separator)
                .append(yyyymm)
                .toString();
        }
        return yyyymm;
    }
}
