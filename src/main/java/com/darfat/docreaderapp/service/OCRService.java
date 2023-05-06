package com.darfat.docreaderapp.service;

import com.darfat.docreaderapp.domain.Documents;
import com.darfat.docreaderapp.domain.VerifiedDocuments;

import java.util.List;

public interface OCRService {
    VerifiedDocuments classifyDocument(Documents documents);
    List<VerifiedDocuments> classifyDocuments();
    VerifiedDocuments validateDocument(VerifiedDocuments documents);

    List<VerifiedDocuments> validateDocuments();
}
