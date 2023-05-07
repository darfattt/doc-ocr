package com.darfat.docreaderapp.service;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.DocumentException;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface PDFService {
    void convertImageToPDF(String sourceDocumentGeneratedFileName,String imagePath, String pdfPath,String pdfFileName) throws IOException, DocumentException;
}
