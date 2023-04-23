package com.darfat.docreaderapp.service.impl;

import com.darfat.docreaderapp.domain.FormPengeluaranBarang;
import com.darfat.docreaderapp.repository.FormPengeluaranBarangRepository;
import com.darfat.docreaderapp.service.FormPengeluaranBarangService;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link FormPengeluaranBarang}.
 */
@Service
@Transactional
public class FormPengeluaranBarangServiceImpl implements FormPengeluaranBarangService {

    private final Logger log = LoggerFactory.getLogger(FormPengeluaranBarangServiceImpl.class);

    private final FormPengeluaranBarangRepository formPengeluaranBarangRepository;

    public FormPengeluaranBarangServiceImpl(FormPengeluaranBarangRepository formPengeluaranBarangRepository) {
        this.formPengeluaranBarangRepository = formPengeluaranBarangRepository;
    }

    @Override
    public FormPengeluaranBarang save(FormPengeluaranBarang formPengeluaranBarang) {
        log.debug("Request to save FormPengeluaranBarang : {}", formPengeluaranBarang);
        return formPengeluaranBarangRepository.save(formPengeluaranBarang);
    }

    @Override
    public FormPengeluaranBarang update(FormPengeluaranBarang formPengeluaranBarang) {
        log.debug("Request to update FormPengeluaranBarang : {}", formPengeluaranBarang);
        formPengeluaranBarang.setIsPersisted();
        return formPengeluaranBarangRepository.save(formPengeluaranBarang);
    }

    @Override
    public Optional<FormPengeluaranBarang> partialUpdate(FormPengeluaranBarang formPengeluaranBarang) {
        log.debug("Request to partially update FormPengeluaranBarang : {}", formPengeluaranBarang);

        return formPengeluaranBarangRepository
            .findById(formPengeluaranBarang.getId())
            .map(existingFormPengeluaranBarang -> {
                if (formPengeluaranBarang.getStatus() != null) {
                    existingFormPengeluaranBarang.setStatus(formPengeluaranBarang.getStatus());
                }
                if (formPengeluaranBarang.getActive() != null) {
                    existingFormPengeluaranBarang.setActive(formPengeluaranBarang.getActive());
                }
                if (formPengeluaranBarang.getRemarks() != null) {
                    existingFormPengeluaranBarang.setRemarks(formPengeluaranBarang.getRemarks());
                }
                if (formPengeluaranBarang.getContents() != null) {
                    existingFormPengeluaranBarang.setContents(formPengeluaranBarang.getContents());
                }
                if (formPengeluaranBarang.getBranch() != null) {
                    existingFormPengeluaranBarang.setBranch(formPengeluaranBarang.getBranch());
                }
                if (formPengeluaranBarang.getDocumentTitle() != null) {
                    existingFormPengeluaranBarang.setDocumentTitle(formPengeluaranBarang.getDocumentTitle());
                }
                if (formPengeluaranBarang.getDocumentNumber() != null) {
                    existingFormPengeluaranBarang.setDocumentNumber(formPengeluaranBarang.getDocumentNumber());
                }
                if (formPengeluaranBarang.getRecipientAddress() != null) {
                    existingFormPengeluaranBarang.setRecipientAddress(formPengeluaranBarang.getRecipientAddress());
                }
                if (formPengeluaranBarang.getNpwp() != null) {
                    existingFormPengeluaranBarang.setNpwp(formPengeluaranBarang.getNpwp());
                }
                if (formPengeluaranBarang.getWarehouseSource() != null) {
                    existingFormPengeluaranBarang.setWarehouseSource(formPengeluaranBarang.getWarehouseSource());
                }
                if (formPengeluaranBarang.getDocumentSource() != null) {
                    existingFormPengeluaranBarang.setDocumentSource(formPengeluaranBarang.getDocumentSource());
                }
                if (formPengeluaranBarang.getReference() != null) {
                    existingFormPengeluaranBarang.setReference(formPengeluaranBarang.getReference());
                }
                if (formPengeluaranBarang.getStatus() != null) {
                    existingFormPengeluaranBarang.setStatus(formPengeluaranBarang.getStatus());
                }
                if (formPengeluaranBarang.getDate() != null) {
                    existingFormPengeluaranBarang.setDate(formPengeluaranBarang.getDate());
                }
                if (formPengeluaranBarang.getProductDescription() != null) {
                    existingFormPengeluaranBarang.setProductDescription(formPengeluaranBarang.getProductDescription());
                }
                if (formPengeluaranBarang.getSourceLocation() != null) {
                    existingFormPengeluaranBarang.setSourceLocation(formPengeluaranBarang.getSourceLocation());
                }
                if (formPengeluaranBarang.getLotNo() != null) {
                    existingFormPengeluaranBarang.setLotNo(formPengeluaranBarang.getLotNo());
                }
                if (formPengeluaranBarang.getQuantity() != null) {
                    existingFormPengeluaranBarang.setQuantity(formPengeluaranBarang.getQuantity());
                }
                if (formPengeluaranBarang.getAmount() != null) {
                    existingFormPengeluaranBarang.setAmount(formPengeluaranBarang.getAmount());
                }
                if (formPengeluaranBarang.getSourceDestination() != null) {
                    existingFormPengeluaranBarang.setSourceDestination(formPengeluaranBarang.getSourceDestination());
                }
                if (formPengeluaranBarang.getArmadaName() != null) {
                    existingFormPengeluaranBarang.setArmadaName(formPengeluaranBarang.getArmadaName());
                }
                if (formPengeluaranBarang.getArmadaNumber() != null) {
                    existingFormPengeluaranBarang.setArmadaNumber(formPengeluaranBarang.getArmadaNumber());
                }
                if (formPengeluaranBarang.getCreatedDate() != null) {
                    existingFormPengeluaranBarang.setCreatedDate(formPengeluaranBarang.getCreatedDate());
                }
                if (formPengeluaranBarang.getCreatedBy() != null) {
                    existingFormPengeluaranBarang.setCreatedBy(formPengeluaranBarang.getCreatedBy());
                }
                if (formPengeluaranBarang.getLastModifiedDate() != null) {
                    existingFormPengeluaranBarang.setLastModifiedDate(formPengeluaranBarang.getLastModifiedDate());
                }
                if (formPengeluaranBarang.getLastModifiedBy() != null) {
                    existingFormPengeluaranBarang.setLastModifiedBy(formPengeluaranBarang.getLastModifiedBy());
                }

                return existingFormPengeluaranBarang;
            })
            .map(formPengeluaranBarangRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<FormPengeluaranBarang> findAll() {
        log.debug("Request to get all FormPengeluaranBarangs");
        return formPengeluaranBarangRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<FormPengeluaranBarang> findOne(String id) {
        log.debug("Request to get FormPengeluaranBarang : {}", id);
        return formPengeluaranBarangRepository.findById(id);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete FormPengeluaranBarang : {}", id);
        formPengeluaranBarangRepository.deleteById(id);
    }
}
