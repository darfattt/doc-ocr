package com.darfat.docreaderapp.web.rest;

import com.darfat.docreaderapp.domain.Attachment;
import com.darfat.docreaderapp.dto.OutputContentFile;
import com.darfat.docreaderapp.exception.ExceptionPredicate;
import com.darfat.docreaderapp.service.AttachmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityNotFoundException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api")
public class AttachmentResource {


    private final AttachmentService attachmentService;


    AttachmentResource(AttachmentService attachmentService) {
        this.attachmentService = attachmentService;
    }

    @GetMapping("/attachments/download/{attachmentGroupId}")
    public ResponseEntity<Resource> downloadAttachmentById(@PathVariable String attachmentGroupId) throws Exception {
        List<Attachment> attachments = attachmentService.findAllAttachmentByGroupId(attachmentGroupId);
        if(attachments == null || attachments.isEmpty()){
            throw new EntityNotFoundException("No Attachments found");
        }
        Collections.sort(attachments, new Comparator<Attachment>() {
            public int compare(Attachment a1, Attachment a2) {
                return a2.getCreatedDate().compareTo(a1.getCreatedDate());
            }
        });
        Attachment attachment = attachments.stream().findFirst().orElseThrow(ExceptionPredicate.attachmentNotFound("-"));
        OutputContentFile attachmentResult = attachmentService.downloadAttachment(attachment.getId());
        MediaType mediaType;
        try {
            mediaType = MediaType.parseMediaType(attachmentResult.getMimeType());
        }
        catch (Exception e) {
            mediaType = MediaType.APPLICATION_OCTET_STREAM;
        }
        // Convert the ByteArrayOutputStream to a byte array
        byte[] bytes = attachmentResult.getFile().toByteArray();
        Resource resource = new ByteArrayResource(bytes);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment;filename=" + attachmentResult.getFileName());
        log.info(resource.getFilename());
        return ResponseEntity.ok().contentType(mediaType).headers(headers).body(resource);

    }

}
