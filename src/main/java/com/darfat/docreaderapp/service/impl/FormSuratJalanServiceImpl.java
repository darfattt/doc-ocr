package com.darfat.docreaderapp.service.impl;

import com.darfat.docreaderapp.domain.FormSuratJalan;
import com.darfat.docreaderapp.repository.FormSuratJalanRepository;
import com.darfat.docreaderapp.service.FormSuratJalanService;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link FormSuratJalan}.
 */
@Service
@Transactional
public class FormSuratJalanServiceImpl implements FormSuratJalanService {

    private final Logger log = LoggerFactory.getLogger(FormSuratJalanServiceImpl.class);

    private final FormSuratJalanRepository formSuratJalanRepository;

    public FormSuratJalanServiceImpl(FormSuratJalanRepository formSuratJalanRepository) {
        this.formSuratJalanRepository = formSuratJalanRepository;
    }

    @Override
    public FormSuratJalan save(FormSuratJalan formSuratJalan) {
        log.debug("Request to save FormSuratJalan : {}", formSuratJalan);
        return formSuratJalanRepository.save(formSuratJalan);
    }

    @Override
    public FormSuratJalan update(FormSuratJalan formSuratJalan) {
        log.debug("Request to update FormSuratJalan : {}", formSuratJalan);
        formSuratJalan.setIsPersisted();
        return formSuratJalanRepository.save(formSuratJalan);
    }

    @Override
    public Optional<FormSuratJalan> partialUpdate(FormSuratJalan formSuratJalan) {
        log.debug("Request to partially update FormSuratJalan : {}", formSuratJalan);

        return formSuratJalanRepository
            .findById(formSuratJalan.getId())
            .map(existingFormSuratJalan -> {
                if (formSuratJalan.getStatus() != null) {
                    existingFormSuratJalan.setStatus(formSuratJalan.getStatus());
                }
                if (formSuratJalan.getActive() != null) {
                    existingFormSuratJalan.setActive(formSuratJalan.getActive());
                }
                if (formSuratJalan.getRemarks() != null) {
                    existingFormSuratJalan.setRemarks(formSuratJalan.getRemarks());
                }
                if (formSuratJalan.getContents() != null) {
                    existingFormSuratJalan.setContents(formSuratJalan.getContents());
                }
                if (formSuratJalan.getBranch() != null) {
                    existingFormSuratJalan.setBranch(formSuratJalan.getBranch());
                }
                if (formSuratJalan.getDocumentTitle() != null) {
                    existingFormSuratJalan.setDocumentTitle(formSuratJalan.getDocumentTitle());
                }
                if (formSuratJalan.getDocumentNumber() != null) {
                    existingFormSuratJalan.setDocumentNumber(formSuratJalan.getDocumentNumber());
                }
                if (formSuratJalan.getRecipientAddress() != null) {
                    existingFormSuratJalan.setRecipientAddress(formSuratJalan.getRecipientAddress());
                }
                if (formSuratJalan.getNpwp() != null) {
                    existingFormSuratJalan.setNpwp(formSuratJalan.getNpwp());
                }
                if (formSuratJalan.getWarehouseSource() != null) {
                    existingFormSuratJalan.setWarehouseSource(formSuratJalan.getWarehouseSource());
                }
                if (formSuratJalan.getDocumentSource() != null) {
                    existingFormSuratJalan.setDocumentSource(formSuratJalan.getDocumentSource());
                }
                if (formSuratJalan.getReference() != null) {
                    existingFormSuratJalan.setReference(formSuratJalan.getReference());
                }
                if (formSuratJalan.getDate() != null) {
                    existingFormSuratJalan.setDate(formSuratJalan.getDate());
                }
                if (formSuratJalan.getProductDescription() != null) {
                    existingFormSuratJalan.setProductDescription(formSuratJalan.getProductDescription());
                }
                if (formSuratJalan.getQuantity() != null) {
                    existingFormSuratJalan.setQuantity(formSuratJalan.getQuantity());
                }
                if (formSuratJalan.getAmount() != null) {
                    existingFormSuratJalan.setAmount(formSuratJalan.getAmount());
                }
                if (formSuratJalan.getArmadaNumber() != null) {
                    existingFormSuratJalan.setArmadaNumber(formSuratJalan.getArmadaNumber());
                }
                if (formSuratJalan.getContainerNumber() != null) {
                    existingFormSuratJalan.setContainerNumber(formSuratJalan.getContainerNumber());
                }
                if (formSuratJalan.getCreatedDate() != null) {
                    existingFormSuratJalan.setCreatedDate(formSuratJalan.getCreatedDate());
                }
                if (formSuratJalan.getCreatedBy() != null) {
                    existingFormSuratJalan.setCreatedBy(formSuratJalan.getCreatedBy());
                }
                if (formSuratJalan.getLastModifiedDate() != null) {
                    existingFormSuratJalan.setLastModifiedDate(formSuratJalan.getLastModifiedDate());
                }
                if (formSuratJalan.getLastModifiedBy() != null) {
                    existingFormSuratJalan.setLastModifiedBy(formSuratJalan.getLastModifiedBy());
                }

                return existingFormSuratJalan;
            })
            .map(formSuratJalanRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<FormSuratJalan> findAll() {
        log.debug("Request to get all FormSuratJalans");
        return formSuratJalanRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<FormSuratJalan> findOne(String id) {
        log.debug("Request to get FormSuratJalan : {}", id);
        return formSuratJalanRepository.findById(id);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete FormSuratJalan : {}", id);
        formSuratJalanRepository.deleteById(id);
    }
}
