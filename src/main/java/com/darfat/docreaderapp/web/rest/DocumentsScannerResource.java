package com.darfat.docreaderapp.web.rest;

import com.darfat.docreaderapp.service.DocumentsService;
import com.darfat.docreaderapp.service.OCRService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api")
@Slf4j
public class DocumentsScannerResource {

    private final DocumentsService documentsService;
    private final OCRService ocrService;

    public DocumentsScannerResource(DocumentsService documentsService, OCRService ocrService) {
        this.documentsService = documentsService;
        this.ocrService = ocrService;
    }

    @PostMapping("/scan/start")
    public ResponseEntity<String> scan(
    ) throws IOException {
        log.info("Start Scan..");
        documentsService.scanFolderForTheSource();
        log.info("End Scan...");
        return ResponseEntity.ok("done");
    }

    @PostMapping("/classify")
    public ResponseEntity<String> classify(
    ) throws IOException {
        log.info("Start classify..");
        ocrService.classifyDocuments();
        log.info("End classify...");
        return ResponseEntity.ok("done");
    }
}
