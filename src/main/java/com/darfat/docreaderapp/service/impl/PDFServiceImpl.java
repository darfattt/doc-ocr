package com.darfat.docreaderapp.service.impl;

import com.darfat.docreaderapp.service.PDFService;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfWriter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@Transactional
@Slf4j
public class PDFServiceImpl implements PDFService {
    @Override
    public void convertImageToPDF(String sourceDocumentGeneratedFileName,String imagePath, String pdfPath,String pdfFileName) throws IOException, DocumentException {
        log.info("Converting image [{}] to [{}]",imagePath,pdfPath);


        // Create a PdfWriter
        Path targetPath = Paths.get(pdfPath);
        if (!Files.exists(targetPath)) {
            Files.createDirectories(targetPath);
        }
        String imageFilePath = imagePath +File.separator+ sourceDocumentGeneratedFileName;
        String pdfFilePath =  pdfPath +File.separator+ pdfFileName;

        Image image = Image.getInstance(imageFilePath);
        float width = image.getWidth();
        float height = image.getHeight();

        // Create a document with the same size as the image
        Document document = new Document(new Rectangle(width, height));

        PdfWriter.getInstance(document, new FileOutputStream(pdfFilePath));

        // Open the document
        document.open();
        image.setAbsolutePosition((document.getPageSize().getWidth() - width) / 2, (document.getPageSize().getHeight() - height) / 2);
        document.add(image);

        // Close the document
        document.close();
    }
}
