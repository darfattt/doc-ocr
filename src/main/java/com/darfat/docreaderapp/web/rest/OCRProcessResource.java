package com.darfat.docreaderapp.web.rest;

import com.darfat.docreaderapp.constants.AttachmentTypeEnum;
import com.darfat.docreaderapp.domain.Attachment;
import com.darfat.docreaderapp.domain.Documents;
import com.darfat.docreaderapp.dto.AttachmentDTO;
import com.darfat.docreaderapp.dto.request.AttachmentRequest;
import com.darfat.docreaderapp.dto.response.AttachmentGroupResponse;
import com.darfat.docreaderapp.dto.response.AttachmentResponse;
import com.darfat.docreaderapp.service.AttachmentService;
import com.darfat.docreaderapp.service.DocumentsService;
import com.darfat.docreaderapp.util.ObjectMapperUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gcp.vision.CloudVisionTemplate;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class OCRProcessResource {
    private final Logger log = LoggerFactory.getLogger(OCRProcessResource.class);
    private CloudVisionTemplate cloudVisionTemplate;
    private ResourceLoader resourceLoader;
    private AttachmentService attachmentService;
    private DocumentsService documentsService;
    public OCRProcessResource(ResourceLoader resourceLoader,
                              CloudVisionTemplate cloudVisionTemplate,
                              AttachmentService attachmentService,
                              DocumentsService documentsService){
        this.resourceLoader = resourceLoader;
        this.cloudVisionTemplate = cloudVisionTemplate;
        this.attachmentService = attachmentService;
        this.documentsService = documentsService;
    }

    @PostMapping("/extractText")
    public ResponseEntity<String> processImage(@RequestBody AttachmentRequest attachmentRequest){
        log.info("Extract to Text [{}] , [{}]",attachmentRequest.getName(),attachmentRequest.getBasePath());
        byte[] byteArray = Base64.getMimeDecoder().decode(attachmentRequest.getAttachments().get(0).getBlobFile());
        String textFromImage =
            cloudVisionTemplate.extractTextFromImage(new ByteArrayResource(byteArray));
        return ResponseEntity
            .ok()
            .body(textFromImage);
    }
    @PostMapping("/extractFileToText/{documentType}")
    public ResponseEntity<String> processFileToText(@PathVariable String documentType,@RequestParam("fileName") String fileName,@RequestParam("file") MultipartFile file) throws IOException {
        log.info("Extract to Text [{}] , [{}]",fileName,documentType);
        byte[] encoded = Base64.getEncoder().encode(file.getBytes());
        String blobStr = new String(encoded);

        AttachmentRequest attachmentRequest = new AttachmentRequest();
        attachmentRequest.setName(fileName);
        attachmentRequest.setClassName(Documents.class.getSimpleName());
        AttachmentDTO attachmentDTO = new AttachmentDTO();
        attachmentDTO.setName(fileName);
        attachmentDTO.setBlobFile(blobStr);
        attachmentDTO.setType(AttachmentTypeEnum.Image.name());
        List<AttachmentDTO> attachments = new ArrayList<>();
        attachments.add(attachmentDTO);
        attachmentRequest.setAttachments(attachments);
        AttachmentGroupResponse attachmentGroupResponse = handleAttachment(attachmentRequest);
        Documents documents = new Documents();
        documents.setType(documentType);
        documents.setName(fileName);
        documents.setStatus("NEW");
        documents.setAttachmentGroupId(attachmentGroupResponse.getAttachmentGroupId());
        documents = documentsService.save(documents);

        String textFromImage =
            cloudVisionTemplate.extractTextFromImage(file.getResource());
        documentsService.approved(documents,textFromImage);
        return ResponseEntity
            .ok()
            .body(textFromImage);
    }

    private AttachmentGroupResponse handleAttachment(AttachmentRequest attachmentRequest) {
        String attachmentGroupId = attachmentRequest.getAttachmentGroupId();
        List<Attachment> attachmentResult = attachmentService.saveAttachment(attachmentRequest);
        List<AttachmentResponse> attachmentDTOList = attachmentResult.stream()
            .map(attachment -> ObjectMapperUtil.MAPPER.convertValue(attachment, AttachmentResponse.class)).collect(Collectors.toList());
        if (attachmentGroupId == null && !attachmentResult.isEmpty()) {
            attachmentGroupId = attachmentResult.get(0).getAttachmentGroup().getId();
        }
        return new AttachmentGroupResponse(attachmentGroupId, attachmentDTOList);
    }
}
