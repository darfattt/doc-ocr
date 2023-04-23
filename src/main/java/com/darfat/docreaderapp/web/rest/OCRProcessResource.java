package com.darfat.docreaderapp.web.rest;

import com.darfat.docreaderapp.constants.AttachmentTypeEnum;
import com.darfat.docreaderapp.domain.Attachment;
import com.darfat.docreaderapp.domain.Documents;
import com.darfat.docreaderapp.dto.AttachmentDTO;
import com.darfat.docreaderapp.dto.FormPengeluaranBarangDTO;
import com.darfat.docreaderapp.dto.request.AttachmentRequest;
import com.darfat.docreaderapp.dto.response.AttachmentGroupResponse;
import com.darfat.docreaderapp.dto.response.AttachmentResponse;
import com.darfat.docreaderapp.service.AttachmentService;
import com.darfat.docreaderapp.service.DocumentsService;
import com.darfat.docreaderapp.util.ObjectMapperUtil;
import com.darfat.docreaderapp.util.form.FormPengeluaranBarangStringUtil;
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
    public ResponseEntity<FormPengeluaranBarangDTO> processImage(@RequestBody AttachmentRequest attachmentRequest) {
        log.info("Extract to Text [{}] , [{}]", attachmentRequest.getName(), attachmentRequest.getBasePath());
        byte[] byteArray = Base64.getMimeDecoder().decode(attachmentRequest.getAttachments().get(0).getBlobFile());
        //        String textFromImage =
        //            cloudVisionTemplate.extractTextFromImage(new ByteArrayResource(byteArray));
        String textFromImage =
            "*\n" +
            "BULOG\n" +
            "10030-KANCAB CIREBON\n" +
            "Penerima:\n" +
            "Badan Pangan Nasional\n" +
            "Kantor Badan Pangan Nasional\n" +
            "Jalan Harsono RM Nomor 3\n" +
            "Ragunan, Pasar Minggu, Jakarta\n" +
            "Selatan\n" +
            "09. DKI Jakarta, Indonesia\n" +
            "(021)7804476 7807377\n" +
            "NPWP: 62.416.790.4-017.000\n" +
            "Gudang Asal: KOMPLEKS PERGUDANGAN KASOKANDEL\n" +
            "Dokumen Sumber : SO/1688/04/2023/10030\n" +
            "Referensi\n" +
            "SO/1688/04/2023/10030\n" +
            "Lokasi Asal\n" +
            "DOKUMEN PENGELUARAN BARANG\n" +
            "OUT/00778/04/2023/10030.092\n" +
            "Informasi Angkutan\n" +
            "Nama Angkutan\n" +
            "POS\n" +
            "Lot/Nomor Seri\n" +
            "[B0010146Z] BERAS MEDIUM MEDIUM 20% LOGO BANTUAN PANGAN 10 KG PSO DN\n" +
            "Utama 002/A01.1.1 [2026-04-04] LT1515355/04/2023/10030 437,00 4.370,00 Partner Locations/Customers\n" +
            "Yang Menerima,\n" +
            "Status\n" +
            "Selesai\n" +
            "Dicetak oleh: Kepala Gudang GUDANG\n" +
            "KASOKANDEL\n" +
            "Pada waktu : 07-04-2023 | 14:25\n" +
            "Tanggal\n" +
            "07-04-2023\n" +
            "Kuantitas Kuantum Lokasi Tujuan\n" +
            "Catatan (Nopol / No Kontainer)\n" +
            "E 8690 VU/WANAHAYU\n" +
            "Cirebon, 07-04-2023\n" +
            "Yang Menyerahkan,\n" +
            "KOMPLEKS PERGUDANGAN\n" +
            "KASOKANDEL\n" +
            "BLOG\n" +
            "Pasokandel\n" +
            "ofconcuer";
        log.info("Text from image [{}]", textFromImage);
        FormPengeluaranBarangDTO dto = FormPengeluaranBarangDTO
            .builder()
            .branch(FormPengeluaranBarangStringUtil.getBranch(textFromImage))
            .documentTitle(FormPengeluaranBarangStringUtil.getDocumentTitle(textFromImage))
            .documentNumber(FormPengeluaranBarangStringUtil.getDocumentNumber(textFromImage))
            .documentSource(FormPengeluaranBarangStringUtil.getSourceDocument(textFromImage))
            .recipientAddress(FormPengeluaranBarangStringUtil.getRecipientAddress(textFromImage))
            .npwp(FormPengeluaranBarangStringUtil.getNpwp(textFromImage))
            .recipientAddress(FormPengeluaranBarangStringUtil.getRecipientAddress(textFromImage))
            .warehouseSource(FormPengeluaranBarangStringUtil.getSourceWarehouse(textFromImage))
            .reference(FormPengeluaranBarangStringUtil.getReference(textFromImage))
            .status(FormPengeluaranBarangStringUtil.getStatus(textFromImage))
            .date(FormPengeluaranBarangStringUtil.getOrderDate(textFromImage))
            .productDescription(FormPengeluaranBarangStringUtil.getProductDescription(textFromImage))
            .sourceLocation(FormPengeluaranBarangStringUtil.getSourceLocation(textFromImage))
            .lotNo(FormPengeluaranBarangStringUtil.getLotNo(textFromImage))
            .quantity(FormPengeluaranBarangStringUtil.getQuantity(textFromImage))
            .amount(FormPengeluaranBarangStringUtil.getAmount(textFromImage))
            .sourceDestination(FormPengeluaranBarangStringUtil.getSourceDestination(textFromImage))
            .armadaName(FormPengeluaranBarangStringUtil.getArmadaName(textFromImage))
            .armadaNumber(FormPengeluaranBarangStringUtil.getArmadaNumber(textFromImage))
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
        documentsService.approved(documents, textFromImage);
        return ResponseEntity.ok().body(textFromImage);
    }

    private AttachmentGroupResponse handleAttachment(AttachmentRequest attachmentRequest) {
        String attachmentGroupId = attachmentRequest.getAttachmentGroupId();
        List<Attachment> attachmentResult = attachmentService.saveAttachment(attachmentRequest);
        List<AttachmentResponse> attachmentDTOList = attachmentResult
            .stream()
            .map(attachment -> ObjectMapperUtil.MAPPER.convertValue(attachment, AttachmentResponse.class))
            .collect(Collectors.toList());
        if (attachmentGroupId == null && !attachmentResult.isEmpty()) {
            attachmentGroupId = attachmentResult.get(0).getAttachmentGroup().getId();
        }
        return new AttachmentGroupResponse(attachmentGroupId, attachmentDTOList);
    }
}
