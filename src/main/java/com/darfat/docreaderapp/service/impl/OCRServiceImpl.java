package com.darfat.docreaderapp.service.impl;

import com.darfat.docreaderapp.constants.DocumentsStatusEnum;
import com.darfat.docreaderapp.constants.VerifiedDocumentsStatusEnum;
import com.darfat.docreaderapp.domain.Documents;
import com.darfat.docreaderapp.domain.VerifiedDocuments;
import com.darfat.docreaderapp.service.DocumentsService;
import com.darfat.docreaderapp.service.OCRService;
import com.darfat.docreaderapp.service.VerifiedDocumentsService;
import org.springframework.cloud.gcp.vision.CloudVisionTemplate;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class OCRServiceImpl implements OCRService {
    private final DocumentsService documentsService;
    private final VerifiedDocumentsService verifiedDocumentsService;
    private  final CloudVisionTemplate cloudVisionTemplate;

    public OCRServiceImpl(DocumentsService documentsService, VerifiedDocumentsService verifiedDocumentsService, CloudVisionTemplate cloudVisionTemplate) {
        this.documentsService = documentsService;
        this.verifiedDocumentsService = verifiedDocumentsService;
        this.cloudVisionTemplate = cloudVisionTemplate;
    }

    @Override
    public VerifiedDocuments classifyDocument(Documents documents) {
        Resource resource = documentsService.getDocumentFile(documents);
        String textFromImage = cloudVisionTemplate.extractTextFromImage(resource);
        VerifiedDocuments verifiedDocuments =  documentsService.verify(documents,textFromImage);
        if (verifiedDocuments == null) return null;

        verifiedDocuments = verifiedDocumentsService.classifyDocumentPath(verifiedDocuments, resource);
        verifiedDocumentsService.newDocument(verifiedDocuments,textFromImage);

        return verifiedDocuments;
    }

    @Override
    public List<VerifiedDocuments> classifyDocuments() {
        List<VerifiedDocuments> verifieds = new ArrayList<>();
        List<Documents> documents = documentsService.findAllByStatus(DocumentsStatusEnum.NEW.name());
        for(Documents doc:documents){
            VerifiedDocuments verified = classifyDocument(doc);
            if(verified!=null) {
                verifieds.add(verified);
            }
        }
        return verifieds;
    }

    @Override
    public VerifiedDocuments validateDocument(VerifiedDocuments documents) {
        return null;
    }

    @Override
    public List<VerifiedDocuments> validateDocuments() {
        List<VerifiedDocuments> verifieds = verifiedDocumentsService.findAllByStatus(VerifiedDocumentsStatusEnum.APPROVAL.name());
        for(VerifiedDocuments doc: verifieds){
            this.validateDocument(doc);
        }
        return verifieds;
    }
}
