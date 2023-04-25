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
                if (formPernyataan.getContents() != null) {
                    existingFormPernyataan.setContents(formPernyataan.getContents());
                }
                if (formPernyataan.getDocumentTitle() != null) {
                    existingFormPernyataan.setDocumentTitle(formPernyataan.getDocumentTitle());
                }
                if (formPernyataan.getOfficerName() != null) {
                    existingFormPernyataan.setOfficerName(formPernyataan.getOfficerName());
                }
                if (formPernyataan.getOfficerPhoneNumber() != null) {
                    existingFormPernyataan.setOfficerPhoneNumber(formPernyataan.getOfficerPhoneNumber());
                }
                if (formPernyataan.getOfficerPosition() != null) {
                    existingFormPernyataan.setOfficerPosition(formPernyataan.getOfficerPosition());
                }
                if (formPernyataan.getOfficerDepartment() != null) {
                    existingFormPernyataan.setOfficerDepartment(formPernyataan.getOfficerDepartment());
                }
                if (formPernyataan.getKelurahanDesa() != null) {
                    existingFormPernyataan.setKelurahanDesa(formPernyataan.getKelurahanDesa());
                }
                if (formPernyataan.getKecamatan() != null) {
                    existingFormPernyataan.setKecamatan(formPernyataan.getKecamatan());
                }
                if (formPernyataan.getKabupatenKota() != null) {
                    existingFormPernyataan.setKabupatenKota(formPernyataan.getKabupatenKota());
                }
                if (formPernyataan.getProvinsi() != null) {
                    existingFormPernyataan.setProvinsi(formPernyataan.getProvinsi());
                }
                if (formPernyataan.getPbpTidakDitemukan1() != null) {
                    existingFormPernyataan.setPbpTidakDitemukan1(formPernyataan.getPbpTidakDitemukan1());
                }
                if (formPernyataan.getAlamatPbpTidakDitemukan1() != null) {
                    existingFormPernyataan.setAlamatPbpTidakDitemukan1(formPernyataan.getAlamatPbpTidakDitemukan1());
                }
                if (formPernyataan.getPbpPengganti1() != null) {
                    existingFormPernyataan.setPbpPengganti1(formPernyataan.getPbpPengganti1());
                }
                if (formPernyataan.getAlamatPbpPengganti1() != null) {
                    existingFormPernyataan.setAlamatPbpPengganti1(formPernyataan.getAlamatPbpPengganti1());
                }
                if (formPernyataan.getPbpTidakDitemukan2() != null) {
                    existingFormPernyataan.setPbpTidakDitemukan2(formPernyataan.getPbpTidakDitemukan2());
                }
                if (formPernyataan.getAlamatPbpTidakDitemukan2() != null) {
                    existingFormPernyataan.setAlamatPbpTidakDitemukan2(formPernyataan.getAlamatPbpTidakDitemukan2());
                }
                if (formPernyataan.getPbpPengganti2() != null) {
                    existingFormPernyataan.setPbpPengganti2(formPernyataan.getPbpPengganti2());
                }
                if (formPernyataan.getAlamatPbpPengganti2() != null) {
                    existingFormPernyataan.setAlamatPbpPengganti2(formPernyataan.getAlamatPbpPengganti2());
                }
                if (formPernyataan.getPbpTidakDitemukan3() != null) {
                    existingFormPernyataan.setPbpTidakDitemukan3(formPernyataan.getPbpTidakDitemukan3());
                }
                if (formPernyataan.getAlamatPbpTidakDitemukan3() != null) {
                    existingFormPernyataan.setAlamatPbpTidakDitemukan3(formPernyataan.getAlamatPbpTidakDitemukan3());
                }
                if (formPernyataan.getPbpPengganti3() != null) {
                    existingFormPernyataan.setPbpPengganti3(formPernyataan.getPbpPengganti3());
                }
                if (formPernyataan.getAlamatPbpPengganti3() != null) {
                    existingFormPernyataan.setAlamatPbpPengganti3(formPernyataan.getAlamatPbpPengganti3());
                }
                if (formPernyataan.getPbpTidakDitemukan4() != null) {
                    existingFormPernyataan.setPbpTidakDitemukan4(formPernyataan.getPbpTidakDitemukan4());
                }
                if (formPernyataan.getAlamatPbpTidakDitemukan4() != null) {
                    existingFormPernyataan.setAlamatPbpTidakDitemukan4(formPernyataan.getAlamatPbpTidakDitemukan4());
                }
                if (formPernyataan.getPbpPengganti4() != null) {
                    existingFormPernyataan.setPbpPengganti4(formPernyataan.getPbpPengganti4());
                }
                if (formPernyataan.getAlamatPbpPengganti4() != null) {
                    existingFormPernyataan.setAlamatPbpPengganti4(formPernyataan.getAlamatPbpPengganti4());
                }
                if (formPernyataan.getPbpTidakDitemukan5() != null) {
                    existingFormPernyataan.setPbpTidakDitemukan5(formPernyataan.getPbpTidakDitemukan5());
                }
                if (formPernyataan.getAlamatPbpTidakDitemukan5() != null) {
                    existingFormPernyataan.setAlamatPbpTidakDitemukan5(formPernyataan.getAlamatPbpTidakDitemukan5());
                }
                if (formPernyataan.getPbpPengganti5() != null) {
                    existingFormPernyataan.setPbpPengganti5(formPernyataan.getPbpPengganti5());
                }
                if (formPernyataan.getAlamatPbpPengganti5() != null) {
                    existingFormPernyataan.setAlamatPbpPengganti5(formPernyataan.getAlamatPbpPengganti5());
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
