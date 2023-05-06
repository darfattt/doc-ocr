package com.darfat.docreaderapp.service.impl;

import com.darfat.docreaderapp.config.FileStorageProperties;
import com.darfat.docreaderapp.constants.AttachmentTypeEnum;
import com.darfat.docreaderapp.constants.DocumentsTypeEnum;
import com.darfat.docreaderapp.constants.VerifiedDocumentsStatusEnum;
import com.darfat.docreaderapp.domain.*;
import com.darfat.docreaderapp.dto.*;
import com.darfat.docreaderapp.dto.request.AttachmentRequest;
import com.darfat.docreaderapp.dto.response.AttachmentGroupResponse;
import com.darfat.docreaderapp.dto.response.AttachmentResponse;
import com.darfat.docreaderapp.repository.VerifiedDocumentsRepository;
import com.darfat.docreaderapp.service.*;
import com.darfat.docreaderapp.util.DateConvertUtil;
import com.darfat.docreaderapp.util.LocalFileUtil;
import com.darfat.docreaderapp.util.ObjectMapperUtil;
import com.darfat.docreaderapp.util.form.*;

import java.io.File;
import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link VerifiedDocuments}.
 */
@Service
@Transactional
public class VerifiedDocumentsServiceImpl implements VerifiedDocumentsService {

    private final Logger log = LoggerFactory.getLogger(VerifiedDocumentsServiceImpl.class);

    private final VerifiedDocumentsRepository verifiedDocumentsRepository;
    private final FormPengeluaranBarangService formPengeluaranBarangService;
    private final FormSuratJalanService formSuratJalanService;
    private final FormPernyataanService formPernyataanService;
    private final FormBASTPBPPService formBASTPBPPService;
    private final FormBASTPBPService formBASTPBPService;
    private final FileStorageProperties fileStorageProperties;

    private final AttachmentService attachmentService;

    public VerifiedDocumentsServiceImpl(
        VerifiedDocumentsRepository verifiedDocumentsRepository,
        FormPengeluaranBarangService formPengeluaranBarangService,
        FormSuratJalanService formSuratJalanService,
        FormPernyataanService formPernyataanService,
        FormBASTPBPPService formBASTPBPPService,
        FormBASTPBPService formBASTPBPService,
        FileStorageProperties fileStorageProperties, AttachmentService attachmentService) {
        this.verifiedDocumentsRepository = verifiedDocumentsRepository;
        this.formPengeluaranBarangService = formPengeluaranBarangService;
        this.formSuratJalanService = formSuratJalanService;
        this.formPernyataanService = formPernyataanService;
        this.formBASTPBPPService = formBASTPBPPService;
        this.formBASTPBPService = formBASTPBPService;
        this.fileStorageProperties = fileStorageProperties;
        this.attachmentService = attachmentService;
    }

    @Override
    public VerifiedDocuments save(VerifiedDocuments verifiedDocuments) {
        log.debug("Request to save VerifiedDocuments : {}", verifiedDocuments);
        return verifiedDocumentsRepository.save(verifiedDocuments);
    }

    @Override
    public VerifiedDocuments update(VerifiedDocuments verifiedDocuments) {
        log.debug("Request to update VerifiedDocuments : {}", verifiedDocuments);
        verifiedDocuments.setIsPersisted();
        return verifiedDocumentsRepository.save(verifiedDocuments);
    }

    @Override
    public Optional<VerifiedDocuments> partialUpdate(VerifiedDocuments verifiedDocuments) {
        log.debug("Request to partially update VerifiedDocuments : {}", verifiedDocuments);

        return verifiedDocumentsRepository
            .findById(verifiedDocuments.getId())
            .map(existingVerifiedDocuments -> {
                if (verifiedDocuments.getType() != null) {
                    existingVerifiedDocuments.setType(verifiedDocuments.getType());
                }
                if (verifiedDocuments.getName() != null) {
                    existingVerifiedDocuments.setName(verifiedDocuments.getName());
                }
                if (verifiedDocuments.getStatus() != null) {
                    existingVerifiedDocuments.setStatus(verifiedDocuments.getStatus());
                }
                if (verifiedDocuments.getContentId() != null) {
                    existingVerifiedDocuments.setContentId(verifiedDocuments.getContentId());
                }
                if (verifiedDocuments.getCreatedDate() != null) {
                    existingVerifiedDocuments.setCreatedDate(verifiedDocuments.getCreatedDate());
                }
                if (verifiedDocuments.getCreatedBy() != null) {
                    existingVerifiedDocuments.setCreatedBy(verifiedDocuments.getCreatedBy());
                }
                if (verifiedDocuments.getLastModifiedDate() != null) {
                    existingVerifiedDocuments.setLastModifiedDate(verifiedDocuments.getLastModifiedDate());
                }
                if (verifiedDocuments.getLastModifiedBy() != null) {
                    existingVerifiedDocuments.setLastModifiedBy(verifiedDocuments.getLastModifiedBy());
                }

                return existingVerifiedDocuments;
            })
            .map(verifiedDocumentsRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<VerifiedDocuments> findAll(Pageable pageable) {
        log.debug("Request to get all VerifiedDocuments");
        return verifiedDocumentsRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<VerifiedDocuments> findOne(String id) {
        log.debug("Request to get VerifiedDocuments : {}", id);
        return verifiedDocumentsRepository.findById(id);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete VerifiedDocuments : {}", id);
        verifiedDocumentsRepository.deleteById(id);
    }

    @Override
    public VerifiedDocuments newDocument(VerifiedDocuments documents, String text) {
        documents.setStatus(VerifiedDocumentsStatusEnum.APPROVAL.name());
        boolean isValid = false;
        if (documents.getType().equals("001")) {
            FormPengeluaranBarang form = generateFormPengeluaranBarang(text);
            documents.setContentId(form.getId());
            isValid = this.validate(documents,form);
        } else if (documents.getType().equals("002")) {
            FormSuratJalan form = generateFormSuratJalan(text);
            documents.setContentId(form.getId());
            isValid = this.validate(documents,form);
        } else if (documents.getType().equals("003")) {
            FormPernyataan form = generateFormPernyataan(text);
            documents.setContentId(form.getId());
        } else if (documents.getType().equals("004")) {
            FormBASTPBPP form = generateFormBASTPengganti(text);
            documents.setContentId(form.getId());
        } else if (documents.getType().equals("005")) {
            FormBASTPBP form = generateFormBAST(text);
            documents.setContentId(form.getId());
        }

        if(isValid){
            documents = approved(documents);
        }
        return this.save(documents);
    }

    @Override
    public VerifiedDocuments approved(VerifiedDocuments documents) {
        documents.setStatus(VerifiedDocumentsStatusEnum.APPROVED.name());
        return this.save(documents);
    }


    @Override
    public VerifiedDocuments classify(VerifiedDocuments documents, String text) {
        documents.setStatus(VerifiedDocumentsStatusEnum.APPROVAL.name());
        if(text.contains(DocumentsTypeEnum.SURAT_KELUAR.getValue())){
            documents.setType(DocumentsTypeEnum.SURAT_KELUAR.getCode());
        }else if(text.contains(DocumentsTypeEnum.SURAT_JALAN.getValue())){
            documents.setType(DocumentsTypeEnum.SURAT_JALAN.getCode());
        }
        return documents;
    }

    @Override
    public VerifiedDocuments classifyDocumentPath(VerifiedDocuments documents, Resource originalFile) throws IOException {
        File file = originalFile.getFile();
        String originalFileName = documents.getName();
        String generatedFileName = LocalFileUtil.formatActualFile(String.valueOf(System.currentTimeMillis()), originalFileName);
        String verifiedBasePath = fileStorageProperties.getLocal().getOriginal();

        AttachmentRequest attachmentRequest = this.generateAttachmentRequest(file,originalFileName,generatedFileName,verifiedBasePath);
        AttachmentGroupResponse attachmentGroupResponse = handleAttachment(attachmentRequest);
        documents.setAttachmentGroupId(attachmentGroupResponse.getAttachmentGroupId());
        return this.save(documents);
    }

    @Override
    public List<VerifiedDocuments> findAllByStatus(String status) {
        return verifiedDocumentsRepository.findAllByStatus(status);
    }

    public boolean validate(VerifiedDocuments documents,FormPengeluaranBarang form) {
        return true;
    }
    public boolean validate(VerifiedDocuments documents,FormSuratJalan form) {
        return false;
    }

    private FormPengeluaranBarang generateFormPengeluaranBarang(String textFromImage) {
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
            .orderStatus(FormPengeluaranBarangStringUtil.getStatus(textFromImage))
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
        FormPengeluaranBarang form = ObjectMapperUtil.MAPPER.convertValue(dto, FormPengeluaranBarang.class);
        form.setStatus("01");
        form.setContents(textFromImage);
        form.setActive(Boolean.TRUE);
        return formPengeluaranBarangService.save(form);
    }

    private FormSuratJalan generateFormSuratJalan(String textFromImage) {
        FormSuratJalanDTO dto = FormSuratJalanDTO
            .builder()
            .branch(FormSuratJalanUtil.getBranch(textFromImage))
            .documentTitle(FormSuratJalanUtil.getDocumentTitle(textFromImage))
            .documentNumber(FormSuratJalanUtil.getDocumentNumber(textFromImage))
            .documentSource(FormSuratJalanUtil.getSourceDocument(textFromImage))
            .recipientAddress(FormSuratJalanUtil.getRecipientAddress(textFromImage))
            .npwp(FormSuratJalanUtil.getNpwp(textFromImage))
            .recipientAddress(FormSuratJalanUtil.getRecipientAddress(textFromImage))
            .warehouseSource(FormSuratJalanUtil.getSourceWarehouse(textFromImage))
            .reference(FormSuratJalanUtil.getReference(textFromImage))
            .date(FormSuratJalanUtil.getOrderDate(textFromImage))
            .productDescription(FormSuratJalanUtil.getProductDescription(textFromImage))
            .quantity(FormSuratJalanUtil.getQuantity(textFromImage))
            .amount(FormSuratJalanUtil.getAmount(textFromImage))
            .armadaNumber(FormSuratJalanUtil.getArmadaNumber(textFromImage))
            .notes(FormSuratJalanUtil.getNotes(textFromImage))
            .containerNumber(FormSuratJalanUtil.getContainerNo(textFromImage))
            .build();
        FormSuratJalan form = ObjectMapperUtil.MAPPER.convertValue(dto, FormSuratJalan.class);
        form.setStatus("01");
        form.setContents(textFromImage);
        form.setActive(Boolean.TRUE);
        return formSuratJalanService.save(form);
    }

    private FormPernyataan generateFormPernyataan(String textFromImage) {
        FormPernyataanDTO dto = FormPernyataanDTO
            .builder()
            .officerName(FormPernyataanUtil.getOfficerName(textFromImage))
            .officerPhoneNumber(FormPernyataanUtil.getOfficerPhoneNumber(textFromImage))
            .officerPosition(FormPernyataanUtil.getOfficerPosition(textFromImage))
            .officerDepartment(FormPernyataanUtil.getOfficerDepartment(textFromImage))
            .kelurahanDesa(FormPernyataanUtil.getKelurahanDesa(textFromImage))
            .kecamatan(FormPernyataanUtil.getKecamatan(textFromImage))
            .kabupatenKota(FormPernyataanUtil.getKabupatenKota(textFromImage))
            .provinsi(FormPernyataanUtil.getProvinsi(textFromImage))
            .documentTitle(FormPernyataanUtil.getDocumentTitle(textFromImage))
            .pbp1(
                FormPernyataanDTO.PenerimaBantuanPanganDTO
                    .builder()
                    .pbpTidakDitemukan("1")
                    .alamatPbpTidakDitemukan("2")
                    .pbpPengganti("")
                    .alamatPbpPengganti("3")
                    .build()
            )
            .build();

        FormPernyataan form = ObjectMapperUtil.MAPPER.convertValue(dto, FormPernyataan.class);
        form.setStatus("01");
        form.setContents(textFromImage);
        form.setActive(Boolean.TRUE);
        return formPernyataanService.save(form);
    }

    private FormBASTPBPP generateFormBASTPengganti(String textFromImage) {
        FormBASTPBPPDTO dto = FormBASTPBPPDTO
            .builder()
            .kelurahanDesa(FormBASTPBPPUtil.getKelurahanDesa(textFromImage))
            .kecamatan(FormBASTPBPPUtil.getKecamatan(textFromImage))
            .kabupatenKota(FormBASTPBPPUtil.getKabupatenKota(textFromImage))
            .provinsi(FormBASTPBPPUtil.getProvinsi(textFromImage))
            .documentTitle(FormBASTPBPPUtil.getDocumentTitle(textFromImage))
            .pbp1(
                FormBASTPBPPDTO.PenerimaBantuanPanganDTO
                    .builder()
                    .pbpTidakDitemukan("1")
                    .sebabPenggantian("2")
                    .pbpPengganti("")
                    .alamatPbpPengganti("3")
                    .build()
            )
            .build();

        FormBASTPBPP form = ObjectMapperUtil.MAPPER.convertValue(dto, FormBASTPBPP.class);
        form.setStatus("01");
        form.setContents(textFromImage);
        form.setActive(Boolean.TRUE);
        return formBASTPBPPService.save(form);
    }

    private FormBASTPBP generateFormBAST(String textFromImage) {
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

        FormBASTPBP form = ObjectMapperUtil.MAPPER.convertValue(dto, FormBASTPBP.class);
        form.setStatus("01");
        form.setContents(textFromImage);
        form.setActive(Boolean.TRUE);
        return formBASTPBPService.save(form);
    }

    private AttachmentRequest generateAttachmentRequest(File file,String fileName, String generatedFileName, String basePath) throws IOException {
        byte[] encoded = FileUtils.readFileToByteArray(file);
        String blobStr = new String(encoded);
        AttachmentRequest attachmentRequest = new AttachmentRequest();
        attachmentRequest.setName(fileName);
        attachmentRequest.setBasePath(pathBuildPathWithYear(basePath)); //root/original/yyyy/mmm
        attachmentRequest.setClassName(Documents.class.getSimpleName());
        AttachmentDTO attachmentDTO = new AttachmentDTO();
        attachmentDTO.setName(generatedFileName);
        attachmentDTO.setBlobFile(blobStr);
        attachmentDTO.setType(AttachmentTypeEnum.Image.name());
        List<AttachmentDTO> attachments = new ArrayList<>();
        attachments.add(attachmentDTO);
        attachmentRequest.setAttachments(attachments);
        return attachmentRequest;
    }
    private String pathBuildPathWithYear(String path) {
        String year = DateConvertUtil.toString(Instant.now(), DateConvertUtil.DATE_FORMAT_4);
        String month = DateConvertUtil.toString(Instant.now(), DateConvertUtil.DATE_FORMAT_10);
        String yyyymm = new StringBuilder()
            .append(year)
            .append(File.separator)
            .append(month).toString();
        if(path!=null){
            return new StringBuilder()
                .append(path)
                .append(File.separator)
                .append(yyyymm)
                .toString();
        }
        return yyyymm;
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
