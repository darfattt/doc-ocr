package com.darfat.docreaderapp.web.rest;

import com.darfat.docreaderapp.constants.AttachmentTypeEnum;
import com.darfat.docreaderapp.domain.Attachment;
import com.darfat.docreaderapp.domain.Documents;
import com.darfat.docreaderapp.dto.*;
import com.darfat.docreaderapp.dto.request.AttachmentRequest;
import com.darfat.docreaderapp.dto.response.AttachmentGroupResponse;
import com.darfat.docreaderapp.dto.response.AttachmentResponse;
import com.darfat.docreaderapp.service.AttachmentService;
import com.darfat.docreaderapp.service.DocumentsService;
import com.darfat.docreaderapp.util.ObjectMapperUtil;
import com.darfat.docreaderapp.util.form.*;
import com.google.cloud.vision.v1.AnnotateImageResponse;
import com.google.cloud.vision.v1.Feature;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gcp.vision.CloudVisionTemplate;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api")
public class OCRProcessResource {

    private final Logger log = LoggerFactory.getLogger(OCRProcessResource.class);
    private CloudVisionTemplate cloudVisionTemplate;
    private ResourceLoader resourceLoader;
    private AttachmentService attachmentService;
    private DocumentsService documentsService;

    public OCRProcessResource(
        ResourceLoader resourceLoader,
        CloudVisionTemplate cloudVisionTemplate,
        AttachmentService attachmentService,
        DocumentsService documentsService
    ) {
        this.resourceLoader = resourceLoader;
        this.cloudVisionTemplate = cloudVisionTemplate;
        this.attachmentService = attachmentService;
        this.documentsService = documentsService;
    }

    @PostMapping("/extractText")
    public ResponseEntity<FormBASTPBPDTO> processImage(@RequestBody AttachmentRequest attachmentRequest) {
        log.info("Extract to Text [{}] , [{}]", attachmentRequest.getName(), attachmentRequest.getBasePath());
        byte[] byteArray = Base64.getMimeDecoder().decode(attachmentRequest.getAttachments().get(0).getBlobFile());
        //        String textFromImage =
        //            cloudVisionTemplate.extractTextFromImage(new ByteArrayResource(byteArray));
        String textFromImage =
            "POS INDONESIA\n" +
            "Provinsi\n" +
            "Kabupaten\n" +
            "Kecamatan\n" +
            "Kelurahan/Desa\n" +
            "NO\n" +
            "1\n" +
            "2\n" +
            "3\n" +
            "4\n" +
            "RW/RT 004/012\n" +
            "3210062002\n" +
            "Kami yang bertanda tangan pada daftar dibawah ini, menyatakan dengan sebenar-benarnya bahwa telah menerima 10 Kg Beras Bantuan Pangan\n" +
            "CBP 2023 dengan kualitas baik\n" +
            "5\n" +
            "6\n" +
            "7\n" +
            "8\n" +
            "9\n" +
            "10\n" +
            "3210062002212375\n" +
            "ABIDIN\n" +
            "NAMA\n" +
            "3210062002212759 BLOK SUKARESMI RW 004\n" +
            "RT 012\n" +
            "AAM AMALIAH\n" +
            "3210062002212701\n" +
            "ACENG NENDI\n" +
            "JAWA BARAT\n" +
            ":MAJALENGKA\n" +
            "MAJA\n" +
            "WANAHAYU\n" +
            "3210062002212420\n" +
            "ACENG\n" +
            "NURKAMAL\n" +
            "ACIH\n" +
            "ACIH\n" +
            "3210062002212501\n" +
            "ADE\n" +
            "3210062002212540 BLOK LEBAKRARANG RW\n" +
            "005 RT 014\n" +
            "3210062002212662\n" +
            "ADE BARON\n" +
            "3210062002212774\n" +
            "ADRIA\n" +
            "BERITA ACARA SERAH TERIMA (BASI)\n" +
            "PENERIMA BANTUAN PANGAN - CBP 2023\n" +
            "ADUN\n" +
            "Nomor Dokumen Out :\n" +
            "Alokasi Bulan / Tahap: MARET 2023\n" +
            "BUAPTEN\n" +
            "ALAMAT\n" +
            "3210062002212443 BLOK LANGGENG RW 006\n" +
            "RT 016\n" +
            "Cap Dinas\n" +
            "BLOK JUMAT RW 002 RT\n" +
            "006\n" +
            "BLOK SELASA RW 001 RT\n" +
            "002\n" +
            "BLOK LANGGENG RW 006\n" +
            "RT 015\n" +
            "BLOK LEBAKLARANG RW\n" +
            "005 RT 013\n" +
            "3210062002212722 BLOK SUKARESMI RW 004\n" +
            "RT 010\n" +
            "BLOK REBO RW 001 RT 002\n" +
            "BLOK SUKARESMI RW 004\n" +
            "RT 009\n" +
            "U\n" +
            "Mengetahui\n" +
            "Aparat Setempat \\\"\n" +
            "PBP32100620022127591)\n" +
            "1x 10\n" +
            "OXO\n" +
            "NOMOR BARCODE\n" +
            "OX\n" +
            "PUP3210082002212/011\n" +
            "1. 10\n" +
            "VE\n" +
            "Okjo\n" +
            "PBP32100620022125401\n" +
            "1x 10\n" +
            "BEW\n" +
            "PBPS210062002212-431\n" +
            "1x 10\n" +
            "OXO\n" +
            "ON\n" +
            "PAP3210062002212/741\n" +
            "1x 10\n" +
            "KCU/KC\n" +
            "KANTOR SERAH\n" +
            "No BAST\n" +
            "3\n" +
            "OO\n" +
            "Jumlah\n" +
            "DO\n" +
            "PUP3200620022123753\n" +
            "1x 10\n" +
            "P3200620022124201\n" +
            "1x 10\n" +
            "PUP32100620022125011\n" +
            "PBP2100620022126621\n" +
            "1x 10\n" +
            "DD\n" +
            "PSON\n" +
            "PBFS2100620022127221\n" +
            "1. 10\n" +
            "45400-MAJALENGKA\n" +
            "45400-MAJALENGKA\n" +
            "JUMLAH\n" +
            "(KG)\n" +
            "10\n" +
            "10\n" +
            "10\n" +
            "10\n" +
            "10\n" +
            "10\n" +
            "10\n" +
            "10\n" +
            "10\n" +
            "10\n" +
            "BULOG\n" +
            "10\n" +
            "Cap Pos\n" +
            "TTD\n" +
            "Ad\n" +
            "Јелен\n" +
            "ہل\n" +
            "que\n" +
            "of\n" +
            "ACIH\n" +
            "Heiß\n" +
            "TGL SERAH\n" +
            "Yang Menyerankan\n" +
            "PT Pos Indonesia\n" +
            "for\n" +
            "Keterangan\n" +
            "Aparat setempat adalah pengurus RT/RW atau sebutan nama lainnya atau aparat Keurahan Dusa atau perwakian penerima bantuan pangan sasaran\n" +
            "**Stempel bagi yang memiliki\n" +
            "Halaman 1\n" +
            "2023\n" +
            "dan 44";

        log.info("Text from image [{}]", textFromImage);
        FormBASTPBPDTO dto = FormBASTPBPDTO
            .builder()
            .kelurahanDesa(FormBASTPBPUtil.getKelurahanDesa(textFromImage))
            .kecamatan(FormBASTPBPUtil.getKecamatan(textFromImage))
            .kabupatenKota(FormBASTPBPUtil.getKabupatenKota(textFromImage))
            .provinsi(FormBASTPBPUtil.getProvinsi(textFromImage))
            .documentTitle(FormBASTPBPUtil.getDocumentTitle(textFromImage))
            .period(FormBASTPBPUtil.getPeriod(textFromImage))
            .rtRw(FormBASTPBPUtil.getRWRT(textFromImage))
            .kcu(FormBASTPBPUtil.getKCU(textFromImage))
            .kantorSerah(FormBASTPBPUtil.getKantorSerah(textFromImage))
            .bastNumber(FormBASTPBPUtil.getBastNumber(textFromImage))
            .documentDescription(FormBASTPBPUtil.getDocumentDescription(textFromImage))
            .pbp1(FormBASTPBPDTO.PenerimaBantuanPanganDTO.builder().nama("1").alamat("2").nomor("").jumlah("3").build())
            .build();
        return ResponseEntity.ok().body(dto);
    }

    @PostMapping("/extractFileToText/{documentType}")
    public ResponseEntity<String> processFileToText(
        @PathVariable String documentType,
        @RequestParam("fileName") String fileName,
        @RequestParam("file") MultipartFile file
    ) throws IOException {
        log.info("Extract to Text [{}] , [{}]", fileName, documentType);
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

        String textFromImage = cloudVisionTemplate.extractTextFromImage(file.getResource());
        log.info(textFromImage);
        //documentsService.approved(documents, textFromImage);
        return ResponseEntity.ok().body(textFromImage);
    }

    private AttachmentGroupResponse handleAttachment(AttachmentRequest attachmentRequest) {
        String attachmentGroupId = attachmentRequest.getAttachmentGroupId();
        String basePath = null;
        List<Attachment> attachmentResult = attachmentService.saveAttachment(attachmentRequest);
        List<AttachmentResponse> attachmentDTOList = attachmentResult
            .stream()
            .map(attachment -> ObjectMapperUtil.MAPPER.convertValue(attachment, AttachmentResponse.class))
            .collect(Collectors.toList());
        if (attachmentGroupId == null && !attachmentResult.isEmpty()) {
            attachmentGroupId = attachmentResult.get(0).getAttachmentGroup().getId();
            basePath = attachmentResult.get(0).getAttachmentGroup().getBasePath();

        }
        return new AttachmentGroupResponse(attachmentGroupId, basePath,attachmentDTOList);
    }
}
