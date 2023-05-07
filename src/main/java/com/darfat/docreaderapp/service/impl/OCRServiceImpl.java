package com.darfat.docreaderapp.service.impl;

import com.darfat.docreaderapp.constants.DocumentsStatusEnum;
import com.darfat.docreaderapp.constants.VerifiedDocumentsStatusEnum;
import com.darfat.docreaderapp.domain.AttachmentGroup;
import com.darfat.docreaderapp.domain.Documents;
import com.darfat.docreaderapp.domain.VerifiedDocuments;
import com.darfat.docreaderapp.dto.OutputContentFile;
import com.darfat.docreaderapp.exception.ExceptionPredicate;
import com.darfat.docreaderapp.service.DocumentsService;
import com.darfat.docreaderapp.service.OCRService;
import com.darfat.docreaderapp.service.VerifiedDocumentsService;
import com.itextpdf.text.DocumentException;
import org.springframework.cloud.gcp.vision.CloudVisionTemplate;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
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
    public VerifiedDocuments classifyDocument(Documents documents) throws IOException, DocumentException {
        Resource resource = documentsService.getDocumentFile(documents);
        String textFromImage = cloudVisionTemplate.extractTextFromImage(resource);
        VerifiedDocuments verifiedDocuments =  documentsService.verify(documents,textFromImage);
        if (verifiedDocuments == null) return null;
        OutputContentFile fileContent = documentsService.getLocalPath(documents);
        verifiedDocuments = verifiedDocumentsService.classifyDocumentPath(verifiedDocuments,fileContent.getPath(),fileContent.getFileName());
        verifiedDocumentsService.newDocument(verifiedDocuments,textFromImage);

        return verifiedDocuments;
    }

    @Override
    public List<VerifiedDocuments> classifyDocuments() throws IOException, DocumentException {
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
