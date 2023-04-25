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
                if (formBASTPBPP.getContents() != null) {
                    existingFormBASTPBPP.setContents(formBASTPBPP.getContents());
                }
                if (formBASTPBPP.getDocumentTitle() != null) {
                    existingFormBASTPBPP.setDocumentTitle(formBASTPBPP.getDocumentTitle());
                }
                if (formBASTPBPP.getKelurahanDesa() != null) {
                    existingFormBASTPBPP.setKelurahanDesa(formBASTPBPP.getKelurahanDesa());
                }
                if (formBASTPBPP.getKecamatan() != null) {
                    existingFormBASTPBPP.setKecamatan(formBASTPBPP.getKecamatan());
                }
                if (formBASTPBPP.getKabupatenKota() != null) {
                    existingFormBASTPBPP.setKabupatenKota(formBASTPBPP.getKabupatenKota());
                }
                if (formBASTPBPP.getProvinsi() != null) {
                    existingFormBASTPBPP.setProvinsi(formBASTPBPP.getProvinsi());
                }
                if (formBASTPBPP.getPbpTidakDitemukan1() != null) {
                    existingFormBASTPBPP.setPbpTidakDitemukan1(formBASTPBPP.getPbpTidakDitemukan1());
                }
                if (formBASTPBPP.getSebabPenggantian1() != null) {
                    existingFormBASTPBPP.setSebabPenggantian1(formBASTPBPP.getSebabPenggantian1());
                }
                if (formBASTPBPP.getPbpPengganti1() != null) {
                    existingFormBASTPBPP.setPbpPengganti1(formBASTPBPP.getPbpPengganti1());
                }
                if (formBASTPBPP.getAlamatPbpPengganti1() != null) {
                    existingFormBASTPBPP.setAlamatPbpPengganti1(formBASTPBPP.getAlamatPbpPengganti1());
                }
                if (formBASTPBPP.getPbpTidakDitemukan2() != null) {
                    existingFormBASTPBPP.setPbpTidakDitemukan2(formBASTPBPP.getPbpTidakDitemukan2());
                }
                if (formBASTPBPP.getSebabPenggantian2() != null) {
                    existingFormBASTPBPP.setSebabPenggantian2(formBASTPBPP.getSebabPenggantian2());
                }
                if (formBASTPBPP.getPbpPengganti2() != null) {
                    existingFormBASTPBPP.setPbpPengganti2(formBASTPBPP.getPbpPengganti2());
                }
                if (formBASTPBPP.getAlamatPbpPengganti2() != null) {
                    existingFormBASTPBPP.setAlamatPbpPengganti2(formBASTPBPP.getAlamatPbpPengganti2());
                }
                if (formBASTPBPP.getPbpTidakDitemukan3() != null) {
                    existingFormBASTPBPP.setPbpTidakDitemukan3(formBASTPBPP.getPbpTidakDitemukan3());
                }
                if (formBASTPBPP.getSebabPenggantian3() != null) {
                    existingFormBASTPBPP.setSebabPenggantian3(formBASTPBPP.getSebabPenggantian3());
                }
                if (formBASTPBPP.getPbpPengganti3() != null) {
                    existingFormBASTPBPP.setPbpPengganti3(formBASTPBPP.getPbpPengganti3());
                }
                if (formBASTPBPP.getAlamatPbpPengganti3() != null) {
                    existingFormBASTPBPP.setAlamatPbpPengganti3(formBASTPBPP.getAlamatPbpPengganti3());
                }
                if (formBASTPBPP.getPbpTidakDitemukan4() != null) {
                    existingFormBASTPBPP.setPbpTidakDitemukan4(formBASTPBPP.getPbpTidakDitemukan4());
                }
                if (formBASTPBPP.getSebabPenggantian4() != null) {
                    existingFormBASTPBPP.setSebabPenggantian4(formBASTPBPP.getSebabPenggantian4());
                }
                if (formBASTPBPP.getPbpPengganti4() != null) {
                    existingFormBASTPBPP.setPbpPengganti4(formBASTPBPP.getPbpPengganti4());
                }
                if (formBASTPBPP.getAlamatPbpPengganti4() != null) {
                    existingFormBASTPBPP.setAlamatPbpPengganti4(formBASTPBPP.getAlamatPbpPengganti4());
                }
                if (formBASTPBPP.getPbpTidakDitemukan5() != null) {
                    existingFormBASTPBPP.setPbpTidakDitemukan5(formBASTPBPP.getPbpTidakDitemukan5());
                }
                if (formBASTPBPP.getSebabPenggantian5() != null) {
                    existingFormBASTPBPP.setSebabPenggantian5(formBASTPBPP.getSebabPenggantian5());
                }
                if (formBASTPBPP.getPbpPengganti5() != null) {
                    existingFormBASTPBPP.setPbpPengganti5(formBASTPBPP.getPbpPengganti5());
                }
                if (formBASTPBPP.getAlamatPbpPengganti5() != null) {
                    existingFormBASTPBPP.setAlamatPbpPengganti5(formBASTPBPP.getAlamatPbpPengganti5());
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
