package com.darfat.docreaderapp.service.impl;

import com.darfat.docreaderapp.domain.FormBASTPBP;
import com.darfat.docreaderapp.repository.FormBASTPBPRepository;
import com.darfat.docreaderapp.service.FormBASTPBPService;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link FormBASTPBP}.
 */
@Service
@Transactional
public class FormBASTPBPServiceImpl implements FormBASTPBPService {

    private final Logger log = LoggerFactory.getLogger(FormBASTPBPServiceImpl.class);

    private final FormBASTPBPRepository formBASTPBPRepository;

    public FormBASTPBPServiceImpl(FormBASTPBPRepository formBASTPBPRepository) {
        this.formBASTPBPRepository = formBASTPBPRepository;
    }

    @Override
    public FormBASTPBP save(FormBASTPBP formBASTPBP) {
        log.debug("Request to save FormBASTPBP : {}", formBASTPBP);
        return formBASTPBPRepository.save(formBASTPBP);
    }

    @Override
    public FormBASTPBP update(FormBASTPBP formBASTPBP) {
        log.debug("Request to update FormBASTPBP : {}", formBASTPBP);
        formBASTPBP.setIsPersisted();
        return formBASTPBPRepository.save(formBASTPBP);
    }

    @Override
    public Optional<FormBASTPBP> partialUpdate(FormBASTPBP formBASTPBP) {
        log.debug("Request to partially update FormBASTPBP : {}", formBASTPBP);

        return formBASTPBPRepository
            .findById(formBASTPBP.getId())
            .map(existingFormBASTPBP -> {
                if (formBASTPBP.getStatus() != null) {
                    existingFormBASTPBP.setStatus(formBASTPBP.getStatus());
                }
                if (formBASTPBP.getActive() != null) {
                    existingFormBASTPBP.setActive(formBASTPBP.getActive());
                }
                if (formBASTPBP.getRemarks() != null) {
                    existingFormBASTPBP.setRemarks(formBASTPBP.getRemarks());
                }
                if (formBASTPBP.getCreatedDate() != null) {
                    existingFormBASTPBP.setCreatedDate(formBASTPBP.getCreatedDate());
                }
                if (formBASTPBP.getCreatedBy() != null) {
                    existingFormBASTPBP.setCreatedBy(formBASTPBP.getCreatedBy());
                }
                if (formBASTPBP.getLastModifiedDate() != null) {
                    existingFormBASTPBP.setLastModifiedDate(formBASTPBP.getLastModifiedDate());
                }
                if (formBASTPBP.getLastModifiedBy() != null) {
                    existingFormBASTPBP.setLastModifiedBy(formBASTPBP.getLastModifiedBy());
                }

                return existingFormBASTPBP;
            })
            .map(formBASTPBPRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<FormBASTPBP> findAll() {
        log.debug("Request to get all FormBASTPBPS");
        return formBASTPBPRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<FormBASTPBP> findOne(String id) {
        log.debug("Request to get FormBASTPBP : {}", id);
        return formBASTPBPRepository.findById(id);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete FormBASTPBP : {}", id);
        formBASTPBPRepository.deleteById(id);
    }
}
