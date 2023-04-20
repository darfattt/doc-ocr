package com.darfat.docreaderapp.service.impl;

import com.darfat.docreaderapp.domain.FormPernyataan;
import com.darfat.docreaderapp.repository.FormPernyataanRepository;
import com.darfat.docreaderapp.service.FormPernyataanService;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link FormPernyataan}.
 */
@Service
@Transactional
public class FormPernyataanServiceImpl implements FormPernyataanService {

    private final Logger log = LoggerFactory.getLogger(FormPernyataanServiceImpl.class);

    private final FormPernyataanRepository formPernyataanRepository;

    public FormPernyataanServiceImpl(FormPernyataanRepository formPernyataanRepository) {
        this.formPernyataanRepository = formPernyataanRepository;
    }

    @Override
    public FormPernyataan save(FormPernyataan formPernyataan) {
        log.debug("Request to save FormPernyataan : {}", formPernyataan);
        return formPernyataanRepository.save(formPernyataan);
    }

    @Override
    public FormPernyataan update(FormPernyataan formPernyataan) {
        log.debug("Request to update FormPernyataan : {}", formPernyataan);
        formPernyataan.setIsPersisted();
        return formPernyataanRepository.save(formPernyataan);
    }

    @Override
    public Optional<FormPernyataan> partialUpdate(FormPernyataan formPernyataan) {
        log.debug("Request to partially update FormPernyataan : {}", formPernyataan);

        return formPernyataanRepository
            .findById(formPernyataan.getId())
            .map(existingFormPernyataan -> {
                if (formPernyataan.getStatus() != null) {
                    existingFormPernyataan.setStatus(formPernyataan.getStatus());
                }
                if (formPernyataan.getActive() != null) {
                    existingFormPernyataan.setActive(formPernyataan.getActive());
                }
                if (formPernyataan.getRemarks() != null) {
                    existingFormPernyataan.setRemarks(formPernyataan.getRemarks());
                }
                if (formPernyataan.getCreatedDate() != null) {
                    existingFormPernyataan.setCreatedDate(formPernyataan.getCreatedDate());
                }
                if (formPernyataan.getCreatedBy() != null) {
                    existingFormPernyataan.setCreatedBy(formPernyataan.getCreatedBy());
                }
                if (formPernyataan.getLastModifiedDate() != null) {
                    existingFormPernyataan.setLastModifiedDate(formPernyataan.getLastModifiedDate());
                }
                if (formPernyataan.getLastModifiedBy() != null) {
                    existingFormPernyataan.setLastModifiedBy(formPernyataan.getLastModifiedBy());
                }

                return existingFormPernyataan;
            })
            .map(formPernyataanRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<FormPernyataan> findAll() {
        log.debug("Request to get all FormPernyataans");
        return formPernyataanRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<FormPernyataan> findOne(String id) {
        log.debug("Request to get FormPernyataan : {}", id);
        return formPernyataanRepository.findById(id);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete FormPernyataan : {}", id);
        formPernyataanRepository.deleteById(id);
    }
}
