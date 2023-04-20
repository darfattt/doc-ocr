package com.darfat.docreaderapp.service.impl;

import com.darfat.docreaderapp.domain.FormPengeluaranBarang;
import com.darfat.docreaderapp.domain.VerifiedDocuments;
import com.darfat.docreaderapp.repository.VerifiedDocumentsRepository;
import com.darfat.docreaderapp.service.FormPengeluaranBarangService;
import com.darfat.docreaderapp.service.VerifiedDocumentsService;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    public VerifiedDocumentsServiceImpl(VerifiedDocumentsRepository verifiedDocumentsRepository,
                                        FormPengeluaranBarangService formPengeluaranBarangService) {
        this.verifiedDocumentsRepository = verifiedDocumentsRepository;
        this.formPengeluaranBarangService = formPengeluaranBarangService;
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
        documents.setStatus("Approval");
        if(documents.getType().equals("001")){
            FormPengeluaranBarang form = generateFormPengeluaranBarang(text);
            documents.setContentId(form.getId());
        }
        return this.save(documents);
    }

    @Override
    public VerifiedDocuments approved(VerifiedDocuments documents) {
        documents.setStatus("Approved");
        return this.save(documents);
    }

    private FormPengeluaranBarang generateFormPengeluaranBarang(String text){
        FormPengeluaranBarang form = new FormPengeluaranBarang();
        form.setStatus("01");
        form.setActive(Boolean.TRUE);
        form.setRemarks(text.substring(0,450));
        return formPengeluaranBarangService.save(form);
    }
}
