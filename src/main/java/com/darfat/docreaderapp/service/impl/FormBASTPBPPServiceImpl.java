package com.darfat.docreaderapp.service.impl;

import com.darfat.docreaderapp.domain.FormBASTPBPP;
import com.darfat.docreaderapp.repository.FormBASTPBPPRepository;
import com.darfat.docreaderapp.service.FormBASTPBPPService;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link FormBASTPBPP}.
 */
@Service
@Transactional
public class FormBASTPBPPServiceImpl implements FormBASTPBPPService {

    private final Logger log = LoggerFactory.getLogger(FormBASTPBPPServiceImpl.class);

    private final FormBASTPBPPRepository formBASTPBPPRepository;

    public FormBASTPBPPServiceImpl(FormBASTPBPPRepository formBASTPBPPRepository) {
        this.formBASTPBPPRepository = formBASTPBPPRepository;
    }

    @Override
    public FormBASTPBPP save(FormBASTPBPP formBASTPBPP) {
        log.debug("Request to save FormBASTPBPP : {}", formBASTPBPP);
        return formBASTPBPPRepository.save(formBASTPBPP);
    }

    @Override
    public FormBASTPBPP update(FormBASTPBPP formBASTPBPP) {
        log.debug("Request to update FormBASTPBPP : {}", formBASTPBPP);
        formBASTPBPP.setIsPersisted();
        return formBASTPBPPRepository.save(formBASTPBPP);
    }

    @Override
    public Optional<FormBASTPBPP> partialUpdate(FormBASTPBPP formBASTPBPP) {
        log.debug("Request to partially update FormBASTPBPP : {}", formBASTPBPP);

        return formBASTPBPPRepository
            .findById(formBASTPBPP.getId())
            .map(existingFormBASTPBPP -> {
                if (formBASTPBPP.getStatus() != null) {
                    existingFormBASTPBPP.setStatus(formBASTPBPP.getStatus());
                }
                if (formBASTPBPP.getActive() != null) {
                    existingFormBASTPBPP.setActive(formBASTPBPP.getActive());
                }
                if (formBASTPBPP.getRemarks() != null) {
                    existingFormBASTPBPP.setRemarks(formBASTPBPP.getRemarks());
                }
                if (formBASTPBPP.getCreatedDate() != null) {
                    existingFormBASTPBPP.setCreatedDate(formBASTPBPP.getCreatedDate());
                }
                if (formBASTPBPP.getCreatedBy() != null) {
                    existingFormBASTPBPP.setCreatedBy(formBASTPBPP.getCreatedBy());
                }
                if (formBASTPBPP.getLastModifiedDate() != null) {
                    existingFormBASTPBPP.setLastModifiedDate(formBASTPBPP.getLastModifiedDate());
                }
                if (formBASTPBPP.getLastModifiedBy() != null) {
                    existingFormBASTPBPP.setLastModifiedBy(formBASTPBPP.getLastModifiedBy());
                }

                return existingFormBASTPBPP;
            })
            .map(formBASTPBPPRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<FormBASTPBPP> findAll() {
        log.debug("Request to get all FormBASTPBPPS");
        return formBASTPBPPRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<FormBASTPBPP> findOne(String id) {
        log.debug("Request to get FormBASTPBPP : {}", id);
        return formBASTPBPPRepository.findById(id);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete FormBASTPBPP : {}", id);
        formBASTPBPPRepository.deleteById(id);
    }
}
