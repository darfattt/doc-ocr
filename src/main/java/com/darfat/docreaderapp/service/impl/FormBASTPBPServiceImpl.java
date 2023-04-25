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
                if (formBASTPBP.getContents() != null) {
                    existingFormBASTPBP.setContents(formBASTPBP.getContents());
                }
                if (formBASTPBP.getDocumentTitle() != null) {
                    existingFormBASTPBP.setDocumentTitle(formBASTPBP.getDocumentTitle());
                }
                if (formBASTPBP.getDocumentNumber() != null) {
                    existingFormBASTPBP.setDocumentNumber(formBASTPBP.getDocumentNumber());
                }
                if (formBASTPBP.getKelurahanDesa() != null) {
                    existingFormBASTPBP.setKelurahanDesa(formBASTPBP.getKelurahanDesa());
                }
                if (formBASTPBP.getKecamatan() != null) {
                    existingFormBASTPBP.setKecamatan(formBASTPBP.getKecamatan());
                }
                if (formBASTPBP.getKabupatenKota() != null) {
                    existingFormBASTPBP.setKabupatenKota(formBASTPBP.getKabupatenKota());
                }
                if (formBASTPBP.getProvinsi() != null) {
                    existingFormBASTPBP.setProvinsi(formBASTPBP.getProvinsi());
                }
                if (formBASTPBP.getRtRw() != null) {
                    existingFormBASTPBP.setRtRw(formBASTPBP.getRtRw());
                }
                if (formBASTPBP.getKcu() != null) {
                    existingFormBASTPBP.setKcu(formBASTPBP.getKcu());
                }
                if (formBASTPBP.getKantorSerah() != null) {
                    existingFormBASTPBP.setKantorSerah(formBASTPBP.getKantorSerah());
                }
                if (formBASTPBP.getBastNumber() != null) {
                    existingFormBASTPBP.setBastNumber(formBASTPBP.getBastNumber());
                }
                if (formBASTPBP.getDocumentDescription() != null) {
                    existingFormBASTPBP.setDocumentDescription(formBASTPBP.getDocumentDescription());
                }
                if (formBASTPBP.getNama1() != null) {
                    existingFormBASTPBP.setNama1(formBASTPBP.getNama1());
                }
                if (formBASTPBP.getAlamat1() != null) {
                    existingFormBASTPBP.setAlamat1(formBASTPBP.getAlamat1());
                }
                if (formBASTPBP.getNomor1() != null) {
                    existingFormBASTPBP.setNomor1(formBASTPBP.getNomor1());
                }
                if (formBASTPBP.getJumlah1() != null) {
                    existingFormBASTPBP.setJumlah1(formBASTPBP.getJumlah1());
                }
                if (formBASTPBP.getNama2() != null) {
                    existingFormBASTPBP.setNama2(formBASTPBP.getNama2());
                }
                if (formBASTPBP.getAlamat2() != null) {
                    existingFormBASTPBP.setAlamat2(formBASTPBP.getAlamat2());
                }
                if (formBASTPBP.getNomor2() != null) {
                    existingFormBASTPBP.setNomor2(formBASTPBP.getNomor2());
                }
                if (formBASTPBP.getJumlah2() != null) {
                    existingFormBASTPBP.setJumlah2(formBASTPBP.getJumlah2());
                }
                if (formBASTPBP.getNama3() != null) {
                    existingFormBASTPBP.setNama3(formBASTPBP.getNama3());
                }
                if (formBASTPBP.getAlamat3() != null) {
                    existingFormBASTPBP.setAlamat3(formBASTPBP.getAlamat3());
                }
                if (formBASTPBP.getNomor3() != null) {
                    existingFormBASTPBP.setNomor3(formBASTPBP.getNomor3());
                }
                if (formBASTPBP.getJumlah3() != null) {
                    existingFormBASTPBP.setJumlah3(formBASTPBP.getJumlah3());
                }
                if (formBASTPBP.getNama4() != null) {
                    existingFormBASTPBP.setNama4(formBASTPBP.getNama4());
                }
                if (formBASTPBP.getAlamat4() != null) {
                    existingFormBASTPBP.setAlamat4(formBASTPBP.getAlamat4());
                }
                if (formBASTPBP.getNomor4() != null) {
                    existingFormBASTPBP.setNomor4(formBASTPBP.getNomor4());
                }
                if (formBASTPBP.getJumlah4() != null) {
                    existingFormBASTPBP.setJumlah4(formBASTPBP.getJumlah4());
                }
                if (formBASTPBP.getNama5() != null) {
                    existingFormBASTPBP.setNama5(formBASTPBP.getNama5());
                }
                if (formBASTPBP.getAlamat5() != null) {
                    existingFormBASTPBP.setAlamat5(formBASTPBP.getAlamat5());
                }
                if (formBASTPBP.getNomor5() != null) {
                    existingFormBASTPBP.setNomor5(formBASTPBP.getNomor5());
                }
                if (formBASTPBP.getJumlah5() != null) {
                    existingFormBASTPBP.setJumlah5(formBASTPBP.getJumlah5());
                }
                if (formBASTPBP.getNama6() != null) {
                    existingFormBASTPBP.setNama6(formBASTPBP.getNama6());
                }
                if (formBASTPBP.getNama7() != null) {
                    existingFormBASTPBP.setNama7(formBASTPBP.getNama7());
                }
                if (formBASTPBP.getAlamat7() != null) {
                    existingFormBASTPBP.setAlamat7(formBASTPBP.getAlamat7());
                }
                if (formBASTPBP.getNomor7() != null) {
                    existingFormBASTPBP.setNomor7(formBASTPBP.getNomor7());
                }
                if (formBASTPBP.getJumlah7() != null) {
                    existingFormBASTPBP.setJumlah7(formBASTPBP.getJumlah7());
                }
                if (formBASTPBP.getNama8() != null) {
                    existingFormBASTPBP.setNama8(formBASTPBP.getNama8());
                }
                if (formBASTPBP.getAlamat8() != null) {
                    existingFormBASTPBP.setAlamat8(formBASTPBP.getAlamat8());
                }
                if (formBASTPBP.getNomor8() != null) {
                    existingFormBASTPBP.setNomor8(formBASTPBP.getNomor8());
                }
                if (formBASTPBP.getJumlah8() != null) {
                    existingFormBASTPBP.setJumlah8(formBASTPBP.getJumlah8());
                }
                if (formBASTPBP.getNama9() != null) {
                    existingFormBASTPBP.setNama9(formBASTPBP.getNama9());
                }
                if (formBASTPBP.getAlamat9() != null) {
                    existingFormBASTPBP.setAlamat9(formBASTPBP.getAlamat9());
                }
                if (formBASTPBP.getNomor9() != null) {
                    existingFormBASTPBP.setNomor9(formBASTPBP.getNomor9());
                }
                if (formBASTPBP.getJumlah9() != null) {
                    existingFormBASTPBP.setJumlah9(formBASTPBP.getJumlah9());
                }
                if (formBASTPBP.getNama10() != null) {
                    existingFormBASTPBP.setNama10(formBASTPBP.getNama10());
                }
                if (formBASTPBP.getAlamat10() != null) {
                    existingFormBASTPBP.setAlamat10(formBASTPBP.getAlamat10());
                }
                if (formBASTPBP.getNomor10() != null) {
                    existingFormBASTPBP.setNomor10(formBASTPBP.getNomor10());
                }
                if (formBASTPBP.getJumlah10() != null) {
                    existingFormBASTPBP.setJumlah10(formBASTPBP.getJumlah10());
                }
                if (formBASTPBP.getAlamat6() != null) {
                    existingFormBASTPBP.setAlamat6(formBASTPBP.getAlamat6());
                }
                if (formBASTPBP.getNomor6() != null) {
                    existingFormBASTPBP.setNomor6(formBASTPBP.getNomor6());
                }
                if (formBASTPBP.getJumlah6() != null) {
                    existingFormBASTPBP.setJumlah6(formBASTPBP.getJumlah6());
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
