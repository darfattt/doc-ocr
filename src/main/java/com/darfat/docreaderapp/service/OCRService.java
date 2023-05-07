package com.darfat.docreaderapp.service;

import com.darfat.docreaderapp.domain.Documents;
import com.darfat.docreaderapp.domain.VerifiedDocuments;
import com.itextpdf.text.DocumentException;

import java.io.IOException;
import java.util.List;

public interface OCRService {
    VerifiedDocuments classifyDocument(Documents documents) throws IOException, DocumentException;
    List<VerifiedDocuments> classifyDocuments() throws IOException, DocumentException;
    VerifiedDocuments validateDocument(VerifiedDocuments documents);

    List<VerifiedDocuments> validateDocuments();
}
