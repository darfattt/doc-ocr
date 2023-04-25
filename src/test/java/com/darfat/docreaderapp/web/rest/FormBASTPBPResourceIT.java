package com.darfat.docreaderapp.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.darfat.docreaderapp.IntegrationTest;
import com.darfat.docreaderapp.domain.FormBASTPBP;
import com.darfat.docreaderapp.repository.FormBASTPBPRepository;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.UUID;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link FormBASTPBPResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class FormBASTPBPResourceIT {

    private static final String DEFAULT_STATUS = "AA";
    private static final String UPDATED_STATUS = "BB";

    private static final Boolean DEFAULT_ACTIVE = false;
    private static final Boolean UPDATED_ACTIVE = true;

    private static final String DEFAULT_REMARKS = "AAAAAAAAAA";
    private static final String UPDATED_REMARKS = "BBBBBBBBBB";

    private static final String DEFAULT_CONTENTS = "AAAAAAAAAA";
    private static final String UPDATED_CONTENTS = "BBBBBBBBBB";

    private static final String DEFAULT_DOCUMENT_TITLE = "AAAAAAAAAA";
    private static final String UPDATED_DOCUMENT_TITLE = "BBBBBBBBBB";

    private static final String DEFAULT_DOCUMENT_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_DOCUMENT_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_KELURAHAN_DESA = "AAAAAAAAAA";
    private static final String UPDATED_KELURAHAN_DESA = "BBBBBBBBBB";

    private static final String DEFAULT_KECAMATAN = "AAAAAAAAAA";
    private static final String UPDATED_KECAMATAN = "BBBBBBBBBB";

    private static final String DEFAULT_KABUPATEN_KOTA = "AAAAAAAAAA";
    private static final String UPDATED_KABUPATEN_KOTA = "BBBBBBBBBB";

    private static final String DEFAULT_PROVINSI = "AAAAAAAAAA";
    private static final String UPDATED_PROVINSI = "BBBBBBBBBB";

    private static final String DEFAULT_RT_RW = "AAAAAAAAAA";
    private static final String UPDATED_RT_RW = "BBBBBBBBBB";

    private static final String DEFAULT_KCU = "AAAAAAAAAA";
    private static final String UPDATED_KCU = "BBBBBBBBBB";

    private static final String DEFAULT_KANTOR_SERAH = "AAAAAAAAAA";
    private static final String UPDATED_KANTOR_SERAH = "BBBBBBBBBB";

    private static final String DEFAULT_BAST_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_BAST_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_DOCUMENT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DOCUMENT_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_NAMA_1 = "AAAAAAAAAA";
    private static final String UPDATED_NAMA_1 = "BBBBBBBBBB";

    private static final String DEFAULT_ALAMAT_1 = "AAAAAAAAAA";
    private static final String UPDATED_ALAMAT_1 = "BBBBBBBBBB";

    private static final String DEFAULT_NOMOR_1 = "AAAAAAAAAA";
    private static final String UPDATED_NOMOR_1 = "BBBBBBBBBB";

    private static final String DEFAULT_JUMLAH_1 = "AAA";
    private static final String UPDATED_JUMLAH_1 = "BBB";

    private static final String DEFAULT_NAMA_2 = "AAAAAAAAAA";
    private static final String UPDATED_NAMA_2 = "BBBBBBBBBB";

    private static final String DEFAULT_ALAMAT_2 = "AAAAAAAAAA";
    private static final String UPDATED_ALAMAT_2 = "BBBBBBBBBB";

    private static final String DEFAULT_NOMOR_2 = "AAAAAAAAAA";
    private static final String UPDATED_NOMOR_2 = "BBBBBBBBBB";

    private static final String DEFAULT_JUMLAH_2 = "AAA";
    private static final String UPDATED_JUMLAH_2 = "BBB";

    private static final String DEFAULT_NAMA_3 = "AAAAAAAAAA";
    private static final String UPDATED_NAMA_3 = "BBBBBBBBBB";

    private static final String DEFAULT_ALAMAT_3 = "AAAAAAAAAA";
    private static final String UPDATED_ALAMAT_3 = "BBBBBBBBBB";

    private static final String DEFAULT_NOMOR_3 = "AAAAAAAAAA";
    private static final String UPDATED_NOMOR_3 = "BBBBBBBBBB";

    private static final String DEFAULT_JUMLAH_3 = "AAA";
    private static final String UPDATED_JUMLAH_3 = "BBB";

    private static final String DEFAULT_NAMA_4 = "AAAAAAAAAA";
    private static final String UPDATED_NAMA_4 = "BBBBBBBBBB";

    private static final String DEFAULT_ALAMAT_4 = "AAAAAAAAAA";
    private static final String UPDATED_ALAMAT_4 = "BBBBBBBBBB";

    private static final String DEFAULT_NOMOR_4 = "AAAAAAAAAA";
    private static final String UPDATED_NOMOR_4 = "BBBBBBBBBB";

    private static final String DEFAULT_JUMLAH_4 = "AAA";
    private static final String UPDATED_JUMLAH_4 = "BBB";

    private static final String DEFAULT_NAMA_5 = "AAAAAAAAAA";
    private static final String UPDATED_NAMA_5 = "BBBBBBBBBB";

    private static final String DEFAULT_ALAMAT_5 = "AAAAAAAAAA";
    private static final String UPDATED_ALAMAT_5 = "BBBBBBBBBB";

    private static final String DEFAULT_NOMOR_5 = "AAAAAAAAAA";
    private static final String UPDATED_NOMOR_5 = "BBBBBBBBBB";

    private static final String DEFAULT_JUMLAH_5 = "AAA";
    private static final String UPDATED_JUMLAH_5 = "BBB";

    private static final String DEFAULT_NAMA_6 = "AAAAAAAAAA";
    private static final String UPDATED_NAMA_6 = "BBBBBBBBBB";

    private static final String DEFAULT_NAMA_7 = "AAAAAAAAAA";
    private static final String UPDATED_NAMA_7 = "BBBBBBBBBB";

    private static final String DEFAULT_ALAMAT_7 = "AAAAAAAAAA";
    private static final String UPDATED_ALAMAT_7 = "BBBBBBBBBB";

    private static final String DEFAULT_NOMOR_7 = "AAAAAAAAAA";
    private static final String UPDATED_NOMOR_7 = "BBBBBBBBBB";

    private static final String DEFAULT_JUMLAH_7 = "AAA";
    private static final String UPDATED_JUMLAH_7 = "BBB";

    private static final String DEFAULT_NAMA_8 = "AAAAAAAAAA";
    private static final String UPDATED_NAMA_8 = "BBBBBBBBBB";

    private static final String DEFAULT_ALAMAT_8 = "AAAAAAAAAA";
    private static final String UPDATED_ALAMAT_8 = "BBBBBBBBBB";

    private static final String DEFAULT_NOMOR_8 = "AAAAAAAAAA";
    private static final String UPDATED_NOMOR_8 = "BBBBBBBBBB";

    private static final String DEFAULT_JUMLAH_8 = "AAA";
    private static final String UPDATED_JUMLAH_8 = "BBB";

    private static final String DEFAULT_NAMA_9 = "AAAAAAAAAA";
    private static final String UPDATED_NAMA_9 = "BBBBBBBBBB";

    private static final String DEFAULT_ALAMAT_9 = "AAAAAAAAAA";
    private static final String UPDATED_ALAMAT_9 = "BBBBBBBBBB";

    private static final String DEFAULT_NOMOR_9 = "AAAAAAAAAA";
    private static final String UPDATED_NOMOR_9 = "BBBBBBBBBB";

    private static final String DEFAULT_JUMLAH_9 = "AAA";
    private static final String UPDATED_JUMLAH_9 = "BBB";

    private static final String DEFAULT_NAMA_10 = "AAAAAAAAAA";
    private static final String UPDATED_NAMA_10 = "BBBBBBBBBB";

    private static final String DEFAULT_ALAMAT_10 = "AAAAAAAAAA";
    private static final String UPDATED_ALAMAT_10 = "BBBBBBBBBB";

    private static final String DEFAULT_NOMOR_10 = "AAAAAAAAAA";
    private static final String UPDATED_NOMOR_10 = "BBBBBBBBBB";

    private static final String DEFAULT_JUMLAH_10 = "AAA";
    private static final String UPDATED_JUMLAH_10 = "BBB";

    private static final String DEFAULT_ALAMAT_6 = "AAAAAAAAAA";
    private static final String UPDATED_ALAMAT_6 = "BBBBBBBBBB";

    private static final String DEFAULT_NOMOR_6 = "AAAAAAAAAA";
    private static final String UPDATED_NOMOR_6 = "BBBBBBBBBB";

    private static final String DEFAULT_JUMLAH_6 = "AAA";
    private static final String UPDATED_JUMLAH_6 = "BBB";

    private static final Instant DEFAULT_CREATED_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_CREATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY = "BBBBBBBBBB";

    private static final Instant DEFAULT_LAST_MODIFIED_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_LAST_MODIFIED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_LAST_MODIFIED_BY = "AAAAAAAAAA";
    private static final String UPDATED_LAST_MODIFIED_BY = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/form-bastpbps";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private FormBASTPBPRepository formBASTPBPRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restFormBASTPBPMockMvc;

    private FormBASTPBP formBASTPBP;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FormBASTPBP createEntity(EntityManager em) {
        FormBASTPBP formBASTPBP = new FormBASTPBP()
            .status(DEFAULT_STATUS)
            .active(DEFAULT_ACTIVE)
            .remarks(DEFAULT_REMARKS)
            .contents(DEFAULT_CONTENTS)
            .documentTitle(DEFAULT_DOCUMENT_TITLE)
            .documentNumber(DEFAULT_DOCUMENT_NUMBER)
            .kelurahanDesa(DEFAULT_KELURAHAN_DESA)
            .kecamatan(DEFAULT_KECAMATAN)
            .kabupatenKota(DEFAULT_KABUPATEN_KOTA)
            .provinsi(DEFAULT_PROVINSI)
            .rtRw(DEFAULT_RT_RW)
            .kcu(DEFAULT_KCU)
            .kantorSerah(DEFAULT_KANTOR_SERAH)
            .bastNumber(DEFAULT_BAST_NUMBER)
            .documentDescription(DEFAULT_DOCUMENT_DESCRIPTION)
            .nama1(DEFAULT_NAMA_1)
            .alamat1(DEFAULT_ALAMAT_1)
            .nomor1(DEFAULT_NOMOR_1)
            .jumlah1(DEFAULT_JUMLAH_1)
            .nama2(DEFAULT_NAMA_2)
            .alamat2(DEFAULT_ALAMAT_2)
            .nomor2(DEFAULT_NOMOR_2)
            .jumlah2(DEFAULT_JUMLAH_2)
            .nama3(DEFAULT_NAMA_3)
            .alamat3(DEFAULT_ALAMAT_3)
            .nomor3(DEFAULT_NOMOR_3)
            .jumlah3(DEFAULT_JUMLAH_3)
            .nama4(DEFAULT_NAMA_4)
            .alamat4(DEFAULT_ALAMAT_4)
            .nomor4(DEFAULT_NOMOR_4)
            .jumlah4(DEFAULT_JUMLAH_4)
            .nama5(DEFAULT_NAMA_5)
            .alamat5(DEFAULT_ALAMAT_5)
            .nomor5(DEFAULT_NOMOR_5)
            .jumlah5(DEFAULT_JUMLAH_5)
            .nama6(DEFAULT_NAMA_6)
            .nama7(DEFAULT_NAMA_7)
            .alamat7(DEFAULT_ALAMAT_7)
            .nomor7(DEFAULT_NOMOR_7)
            .jumlah7(DEFAULT_JUMLAH_7)
            .nama8(DEFAULT_NAMA_8)
            .alamat8(DEFAULT_ALAMAT_8)
            .nomor8(DEFAULT_NOMOR_8)
            .jumlah8(DEFAULT_JUMLAH_8)
            .nama9(DEFAULT_NAMA_9)
            .alamat9(DEFAULT_ALAMAT_9)
            .nomor9(DEFAULT_NOMOR_9)
            .jumlah9(DEFAULT_JUMLAH_9)
            .nama10(DEFAULT_NAMA_10)
            .alamat10(DEFAULT_ALAMAT_10)
            .nomor10(DEFAULT_NOMOR_10)
            .jumlah10(DEFAULT_JUMLAH_10)
            .alamat6(DEFAULT_ALAMAT_6)
            .nomor6(DEFAULT_NOMOR_6)
            .jumlah6(DEFAULT_JUMLAH_6);
        return formBASTPBP;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FormBASTPBP createUpdatedEntity(EntityManager em) {
        FormBASTPBP formBASTPBP = new FormBASTPBP()
            .status(UPDATED_STATUS)
            .active(UPDATED_ACTIVE)
            .remarks(UPDATED_REMARKS)
            .contents(UPDATED_CONTENTS)
            .documentTitle(UPDATED_DOCUMENT_TITLE)
            .documentNumber(UPDATED_DOCUMENT_NUMBER)
            .kelurahanDesa(UPDATED_KELURAHAN_DESA)
            .kecamatan(UPDATED_KECAMATAN)
            .kabupatenKota(UPDATED_KABUPATEN_KOTA)
            .provinsi(UPDATED_PROVINSI)
            .rtRw(UPDATED_RT_RW)
            .kcu(UPDATED_KCU)
            .kantorSerah(UPDATED_KANTOR_SERAH)
            .bastNumber(UPDATED_BAST_NUMBER)
            .documentDescription(UPDATED_DOCUMENT_DESCRIPTION)
            .nama1(UPDATED_NAMA_1)
            .alamat1(UPDATED_ALAMAT_1)
            .nomor1(UPDATED_NOMOR_1)
            .jumlah1(UPDATED_JUMLAH_1)
            .nama2(UPDATED_NAMA_2)
            .alamat2(UPDATED_ALAMAT_2)
            .nomor2(UPDATED_NOMOR_2)
            .jumlah2(UPDATED_JUMLAH_2)
            .nama3(UPDATED_NAMA_3)
            .alamat3(UPDATED_ALAMAT_3)
            .nomor3(UPDATED_NOMOR_3)
            .jumlah3(UPDATED_JUMLAH_3)
            .nama4(UPDATED_NAMA_4)
            .alamat4(UPDATED_ALAMAT_4)
            .nomor4(UPDATED_NOMOR_4)
            .jumlah4(UPDATED_JUMLAH_4)
            .nama5(UPDATED_NAMA_5)
            .alamat5(UPDATED_ALAMAT_5)
            .nomor5(UPDATED_NOMOR_5)
            .jumlah5(UPDATED_JUMLAH_5)
            .nama6(UPDATED_NAMA_6)
            .nama7(UPDATED_NAMA_7)
            .alamat7(UPDATED_ALAMAT_7)
            .nomor7(UPDATED_NOMOR_7)
            .jumlah7(UPDATED_JUMLAH_7)
            .nama8(UPDATED_NAMA_8)
            .alamat8(UPDATED_ALAMAT_8)
            .nomor8(UPDATED_NOMOR_8)
            .jumlah8(UPDATED_JUMLAH_8)
            .nama9(UPDATED_NAMA_9)
            .alamat9(UPDATED_ALAMAT_9)
            .nomor9(UPDATED_NOMOR_9)
            .jumlah9(UPDATED_JUMLAH_9)
            .nama10(UPDATED_NAMA_10)
            .alamat10(UPDATED_ALAMAT_10)
            .nomor10(UPDATED_NOMOR_10)
            .jumlah10(UPDATED_JUMLAH_10)
            .alamat6(UPDATED_ALAMAT_6)
            .nomor6(UPDATED_NOMOR_6)
            .jumlah6(UPDATED_JUMLAH_6);
        return formBASTPBP;
    }

    @BeforeEach
    public void initTest() {
        formBASTPBP = createEntity(em);
    }

    @Test
    @Transactional
    void createFormBASTPBP() throws Exception {
        int databaseSizeBeforeCreate = formBASTPBPRepository.findAll().size();
        // Create the FormBASTPBP
        restFormBASTPBPMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(formBASTPBP)))
            .andExpect(status().isCreated());

        // Validate the FormBASTPBP in the database
        List<FormBASTPBP> formBASTPBPList = formBASTPBPRepository.findAll();
        assertThat(formBASTPBPList).hasSize(databaseSizeBeforeCreate + 1);
        FormBASTPBP testFormBASTPBP = formBASTPBPList.get(formBASTPBPList.size() - 1);
        assertThat(testFormBASTPBP.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testFormBASTPBP.getActive()).isEqualTo(DEFAULT_ACTIVE);
        assertThat(testFormBASTPBP.getRemarks()).isEqualTo(DEFAULT_REMARKS);
        assertThat(testFormBASTPBP.getContents()).isEqualTo(DEFAULT_CONTENTS);
        assertThat(testFormBASTPBP.getDocumentTitle()).isEqualTo(DEFAULT_DOCUMENT_TITLE);
        assertThat(testFormBASTPBP.getDocumentNumber()).isEqualTo(DEFAULT_DOCUMENT_NUMBER);
        assertThat(testFormBASTPBP.getKelurahanDesa()).isEqualTo(DEFAULT_KELURAHAN_DESA);
        assertThat(testFormBASTPBP.getKecamatan()).isEqualTo(DEFAULT_KECAMATAN);
        assertThat(testFormBASTPBP.getKabupatenKota()).isEqualTo(DEFAULT_KABUPATEN_KOTA);
        assertThat(testFormBASTPBP.getProvinsi()).isEqualTo(DEFAULT_PROVINSI);
        assertThat(testFormBASTPBP.getRtRw()).isEqualTo(DEFAULT_RT_RW);
        assertThat(testFormBASTPBP.getKcu()).isEqualTo(DEFAULT_KCU);
        assertThat(testFormBASTPBP.getKantorSerah()).isEqualTo(DEFAULT_KANTOR_SERAH);
        assertThat(testFormBASTPBP.getBastNumber()).isEqualTo(DEFAULT_BAST_NUMBER);
        assertThat(testFormBASTPBP.getDocumentDescription()).isEqualTo(DEFAULT_DOCUMENT_DESCRIPTION);
        assertThat(testFormBASTPBP.getNama1()).isEqualTo(DEFAULT_NAMA_1);
        assertThat(testFormBASTPBP.getAlamat1()).isEqualTo(DEFAULT_ALAMAT_1);
        assertThat(testFormBASTPBP.getNomor1()).isEqualTo(DEFAULT_NOMOR_1);
        assertThat(testFormBASTPBP.getJumlah1()).isEqualTo(DEFAULT_JUMLAH_1);
        assertThat(testFormBASTPBP.getNama2()).isEqualTo(DEFAULT_NAMA_2);
        assertThat(testFormBASTPBP.getAlamat2()).isEqualTo(DEFAULT_ALAMAT_2);
        assertThat(testFormBASTPBP.getNomor2()).isEqualTo(DEFAULT_NOMOR_2);
        assertThat(testFormBASTPBP.getJumlah2()).isEqualTo(DEFAULT_JUMLAH_2);
        assertThat(testFormBASTPBP.getNama3()).isEqualTo(DEFAULT_NAMA_3);
        assertThat(testFormBASTPBP.getAlamat3()).isEqualTo(DEFAULT_ALAMAT_3);
        assertThat(testFormBASTPBP.getNomor3()).isEqualTo(DEFAULT_NOMOR_3);
        assertThat(testFormBASTPBP.getJumlah3()).isEqualTo(DEFAULT_JUMLAH_3);
        assertThat(testFormBASTPBP.getNama4()).isEqualTo(DEFAULT_NAMA_4);
        assertThat(testFormBASTPBP.getAlamat4()).isEqualTo(DEFAULT_ALAMAT_4);
        assertThat(testFormBASTPBP.getNomor4()).isEqualTo(DEFAULT_NOMOR_4);
        assertThat(testFormBASTPBP.getJumlah4()).isEqualTo(DEFAULT_JUMLAH_4);
        assertThat(testFormBASTPBP.getNama5()).isEqualTo(DEFAULT_NAMA_5);
        assertThat(testFormBASTPBP.getAlamat5()).isEqualTo(DEFAULT_ALAMAT_5);
        assertThat(testFormBASTPBP.getNomor5()).isEqualTo(DEFAULT_NOMOR_5);
        assertThat(testFormBASTPBP.getJumlah5()).isEqualTo(DEFAULT_JUMLAH_5);
        assertThat(testFormBASTPBP.getNama6()).isEqualTo(DEFAULT_NAMA_6);
        assertThat(testFormBASTPBP.getNama7()).isEqualTo(DEFAULT_NAMA_7);
        assertThat(testFormBASTPBP.getAlamat7()).isEqualTo(DEFAULT_ALAMAT_7);
        assertThat(testFormBASTPBP.getNomor7()).isEqualTo(DEFAULT_NOMOR_7);
        assertThat(testFormBASTPBP.getJumlah7()).isEqualTo(DEFAULT_JUMLAH_7);
        assertThat(testFormBASTPBP.getNama8()).isEqualTo(DEFAULT_NAMA_8);
        assertThat(testFormBASTPBP.getAlamat8()).isEqualTo(DEFAULT_ALAMAT_8);
        assertThat(testFormBASTPBP.getNomor8()).isEqualTo(DEFAULT_NOMOR_8);
        assertThat(testFormBASTPBP.getJumlah8()).isEqualTo(DEFAULT_JUMLAH_8);
        assertThat(testFormBASTPBP.getNama9()).isEqualTo(DEFAULT_NAMA_9);
        assertThat(testFormBASTPBP.getAlamat9()).isEqualTo(DEFAULT_ALAMAT_9);
        assertThat(testFormBASTPBP.getNomor9()).isEqualTo(DEFAULT_NOMOR_9);
        assertThat(testFormBASTPBP.getJumlah9()).isEqualTo(DEFAULT_JUMLAH_9);
        assertThat(testFormBASTPBP.getNama10()).isEqualTo(DEFAULT_NAMA_10);
        assertThat(testFormBASTPBP.getAlamat10()).isEqualTo(DEFAULT_ALAMAT_10);
        assertThat(testFormBASTPBP.getNomor10()).isEqualTo(DEFAULT_NOMOR_10);
        assertThat(testFormBASTPBP.getJumlah10()).isEqualTo(DEFAULT_JUMLAH_10);
        assertThat(testFormBASTPBP.getAlamat6()).isEqualTo(DEFAULT_ALAMAT_6);
        assertThat(testFormBASTPBP.getNomor6()).isEqualTo(DEFAULT_NOMOR_6);
        assertThat(testFormBASTPBP.getJumlah6()).isEqualTo(DEFAULT_JUMLAH_6);
        assertThat(testFormBASTPBP.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testFormBASTPBP.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testFormBASTPBP.getLastModifiedDate()).isEqualTo(DEFAULT_LAST_MODIFIED_DATE);
        assertThat(testFormBASTPBP.getLastModifiedBy()).isEqualTo(DEFAULT_LAST_MODIFIED_BY);
    }

    @Test
    @Transactional
    void createFormBASTPBPWithExistingId() throws Exception {
        // Create the FormBASTPBP with an existing ID
        formBASTPBP.setId("existing_id");

        int databaseSizeBeforeCreate = formBASTPBPRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restFormBASTPBPMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(formBASTPBP)))
            .andExpect(status().isBadRequest());

        // Validate the FormBASTPBP in the database
        List<FormBASTPBP> formBASTPBPList = formBASTPBPRepository.findAll();
        assertThat(formBASTPBPList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkActiveIsRequired() throws Exception {
        int databaseSizeBeforeTest = formBASTPBPRepository.findAll().size();
        // set the field null
        formBASTPBP.setActive(null);

        // Create the FormBASTPBP, which fails.

        restFormBASTPBPMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(formBASTPBP)))
            .andExpect(status().isBadRequest());

        List<FormBASTPBP> formBASTPBPList = formBASTPBPRepository.findAll();
        assertThat(formBASTPBPList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCreatedDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = formBASTPBPRepository.findAll().size();
        // set the field null
        formBASTPBP.setCreatedDate(null);

        // Create the FormBASTPBP, which fails.

        restFormBASTPBPMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(formBASTPBP)))
            .andExpect(status().isBadRequest());

        List<FormBASTPBP> formBASTPBPList = formBASTPBPRepository.findAll();
        assertThat(formBASTPBPList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllFormBASTPBPS() throws Exception {
        // Initialize the database
        formBASTPBP.setId(UUID.randomUUID().toString());
        formBASTPBPRepository.saveAndFlush(formBASTPBP);

        // Get all the formBASTPBPList
        restFormBASTPBPMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(formBASTPBP.getId())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].active").value(hasItem(DEFAULT_ACTIVE.booleanValue())))
            .andExpect(jsonPath("$.[*].remarks").value(hasItem(DEFAULT_REMARKS)))
            .andExpect(jsonPath("$.[*].contents").value(hasItem(DEFAULT_CONTENTS)))
            .andExpect(jsonPath("$.[*].documentTitle").value(hasItem(DEFAULT_DOCUMENT_TITLE)))
            .andExpect(jsonPath("$.[*].documentNumber").value(hasItem(DEFAULT_DOCUMENT_NUMBER)))
            .andExpect(jsonPath("$.[*].kelurahanDesa").value(hasItem(DEFAULT_KELURAHAN_DESA)))
            .andExpect(jsonPath("$.[*].kecamatan").value(hasItem(DEFAULT_KECAMATAN)))
            .andExpect(jsonPath("$.[*].kabupatenKota").value(hasItem(DEFAULT_KABUPATEN_KOTA)))
            .andExpect(jsonPath("$.[*].provinsi").value(hasItem(DEFAULT_PROVINSI)))
            .andExpect(jsonPath("$.[*].rtRw").value(hasItem(DEFAULT_RT_RW)))
            .andExpect(jsonPath("$.[*].kcu").value(hasItem(DEFAULT_KCU)))
            .andExpect(jsonPath("$.[*].kantorSerah").value(hasItem(DEFAULT_KANTOR_SERAH)))
            .andExpect(jsonPath("$.[*].bastNumber").value(hasItem(DEFAULT_BAST_NUMBER)))
            .andExpect(jsonPath("$.[*].documentDescription").value(hasItem(DEFAULT_DOCUMENT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].nama1").value(hasItem(DEFAULT_NAMA_1)))
            .andExpect(jsonPath("$.[*].alamat1").value(hasItem(DEFAULT_ALAMAT_1)))
            .andExpect(jsonPath("$.[*].nomor1").value(hasItem(DEFAULT_NOMOR_1)))
            .andExpect(jsonPath("$.[*].jumlah1").value(hasItem(DEFAULT_JUMLAH_1)))
            .andExpect(jsonPath("$.[*].nama2").value(hasItem(DEFAULT_NAMA_2)))
            .andExpect(jsonPath("$.[*].alamat2").value(hasItem(DEFAULT_ALAMAT_2)))
            .andExpect(jsonPath("$.[*].nomor2").value(hasItem(DEFAULT_NOMOR_2)))
            .andExpect(jsonPath("$.[*].jumlah2").value(hasItem(DEFAULT_JUMLAH_2)))
            .andExpect(jsonPath("$.[*].nama3").value(hasItem(DEFAULT_NAMA_3)))
            .andExpect(jsonPath("$.[*].alamat3").value(hasItem(DEFAULT_ALAMAT_3)))
            .andExpect(jsonPath("$.[*].nomor3").value(hasItem(DEFAULT_NOMOR_3)))
            .andExpect(jsonPath("$.[*].jumlah3").value(hasItem(DEFAULT_JUMLAH_3)))
            .andExpect(jsonPath("$.[*].nama4").value(hasItem(DEFAULT_NAMA_4)))
            .andExpect(jsonPath("$.[*].alamat4").value(hasItem(DEFAULT_ALAMAT_4)))
            .andExpect(jsonPath("$.[*].nomor4").value(hasItem(DEFAULT_NOMOR_4)))
            .andExpect(jsonPath("$.[*].jumlah4").value(hasItem(DEFAULT_JUMLAH_4)))
            .andExpect(jsonPath("$.[*].nama5").value(hasItem(DEFAULT_NAMA_5)))
            .andExpect(jsonPath("$.[*].alamat5").value(hasItem(DEFAULT_ALAMAT_5)))
            .andExpect(jsonPath("$.[*].nomor5").value(hasItem(DEFAULT_NOMOR_5)))
            .andExpect(jsonPath("$.[*].jumlah5").value(hasItem(DEFAULT_JUMLAH_5)))
            .andExpect(jsonPath("$.[*].nama6").value(hasItem(DEFAULT_NAMA_6)))
            .andExpect(jsonPath("$.[*].nama7").value(hasItem(DEFAULT_NAMA_7)))
            .andExpect(jsonPath("$.[*].alamat7").value(hasItem(DEFAULT_ALAMAT_7)))
            .andExpect(jsonPath("$.[*].nomor7").value(hasItem(DEFAULT_NOMOR_7)))
            .andExpect(jsonPath("$.[*].jumlah7").value(hasItem(DEFAULT_JUMLAH_7)))
            .andExpect(jsonPath("$.[*].nama8").value(hasItem(DEFAULT_NAMA_8)))
            .andExpect(jsonPath("$.[*].alamat8").value(hasItem(DEFAULT_ALAMAT_8)))
            .andExpect(jsonPath("$.[*].nomor8").value(hasItem(DEFAULT_NOMOR_8)))
            .andExpect(jsonPath("$.[*].jumlah8").value(hasItem(DEFAULT_JUMLAH_8)))
            .andExpect(jsonPath("$.[*].nama9").value(hasItem(DEFAULT_NAMA_9)))
            .andExpect(jsonPath("$.[*].alamat9").value(hasItem(DEFAULT_ALAMAT_9)))
            .andExpect(jsonPath("$.[*].nomor9").value(hasItem(DEFAULT_NOMOR_9)))
            .andExpect(jsonPath("$.[*].jumlah9").value(hasItem(DEFAULT_JUMLAH_9)))
            .andExpect(jsonPath("$.[*].nama10").value(hasItem(DEFAULT_NAMA_10)))
            .andExpect(jsonPath("$.[*].alamat10").value(hasItem(DEFAULT_ALAMAT_10)))
            .andExpect(jsonPath("$.[*].nomor10").value(hasItem(DEFAULT_NOMOR_10)))
            .andExpect(jsonPath("$.[*].jumlah10").value(hasItem(DEFAULT_JUMLAH_10)))
            .andExpect(jsonPath("$.[*].alamat6").value(hasItem(DEFAULT_ALAMAT_6)))
            .andExpect(jsonPath("$.[*].nomor6").value(hasItem(DEFAULT_NOMOR_6)))
            .andExpect(jsonPath("$.[*].jumlah6").value(hasItem(DEFAULT_JUMLAH_6)))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)))
            .andExpect(jsonPath("$.[*].lastModifiedDate").value(hasItem(DEFAULT_LAST_MODIFIED_DATE.toString())))
            .andExpect(jsonPath("$.[*].lastModifiedBy").value(hasItem(DEFAULT_LAST_MODIFIED_BY)));
    }

    @Test
    @Transactional
    void getFormBASTPBP() throws Exception {
        // Initialize the database
        formBASTPBP.setId(UUID.randomUUID().toString());
        formBASTPBPRepository.saveAndFlush(formBASTPBP);

        // Get the formBASTPBP
        restFormBASTPBPMockMvc
            .perform(get(ENTITY_API_URL_ID, formBASTPBP.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(formBASTPBP.getId()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.active").value(DEFAULT_ACTIVE.booleanValue()))
            .andExpect(jsonPath("$.remarks").value(DEFAULT_REMARKS))
            .andExpect(jsonPath("$.contents").value(DEFAULT_CONTENTS))
            .andExpect(jsonPath("$.documentTitle").value(DEFAULT_DOCUMENT_TITLE))
            .andExpect(jsonPath("$.documentNumber").value(DEFAULT_DOCUMENT_NUMBER))
            .andExpect(jsonPath("$.kelurahanDesa").value(DEFAULT_KELURAHAN_DESA))
            .andExpect(jsonPath("$.kecamatan").value(DEFAULT_KECAMATAN))
            .andExpect(jsonPath("$.kabupatenKota").value(DEFAULT_KABUPATEN_KOTA))
            .andExpect(jsonPath("$.provinsi").value(DEFAULT_PROVINSI))
            .andExpect(jsonPath("$.rtRw").value(DEFAULT_RT_RW))
            .andExpect(jsonPath("$.kcu").value(DEFAULT_KCU))
            .andExpect(jsonPath("$.kantorSerah").value(DEFAULT_KANTOR_SERAH))
            .andExpect(jsonPath("$.bastNumber").value(DEFAULT_BAST_NUMBER))
            .andExpect(jsonPath("$.documentDescription").value(DEFAULT_DOCUMENT_DESCRIPTION))
            .andExpect(jsonPath("$.nama1").value(DEFAULT_NAMA_1))
            .andExpect(jsonPath("$.alamat1").value(DEFAULT_ALAMAT_1))
            .andExpect(jsonPath("$.nomor1").value(DEFAULT_NOMOR_1))
            .andExpect(jsonPath("$.jumlah1").value(DEFAULT_JUMLAH_1))
            .andExpect(jsonPath("$.nama2").value(DEFAULT_NAMA_2))
            .andExpect(jsonPath("$.alamat2").value(DEFAULT_ALAMAT_2))
            .andExpect(jsonPath("$.nomor2").value(DEFAULT_NOMOR_2))
            .andExpect(jsonPath("$.jumlah2").value(DEFAULT_JUMLAH_2))
            .andExpect(jsonPath("$.nama3").value(DEFAULT_NAMA_3))
            .andExpect(jsonPath("$.alamat3").value(DEFAULT_ALAMAT_3))
            .andExpect(jsonPath("$.nomor3").value(DEFAULT_NOMOR_3))
            .andExpect(jsonPath("$.jumlah3").value(DEFAULT_JUMLAH_3))
            .andExpect(jsonPath("$.nama4").value(DEFAULT_NAMA_4))
            .andExpect(jsonPath("$.alamat4").value(DEFAULT_ALAMAT_4))
            .andExpect(jsonPath("$.nomor4").value(DEFAULT_NOMOR_4))
            .andExpect(jsonPath("$.jumlah4").value(DEFAULT_JUMLAH_4))
            .andExpect(jsonPath("$.nama5").value(DEFAULT_NAMA_5))
            .andExpect(jsonPath("$.alamat5").value(DEFAULT_ALAMAT_5))
            .andExpect(jsonPath("$.nomor5").value(DEFAULT_NOMOR_5))
            .andExpect(jsonPath("$.jumlah5").value(DEFAULT_JUMLAH_5))
            .andExpect(jsonPath("$.nama6").value(DEFAULT_NAMA_6))
            .andExpect(jsonPath("$.nama7").value(DEFAULT_NAMA_7))
            .andExpect(jsonPath("$.alamat7").value(DEFAULT_ALAMAT_7))
            .andExpect(jsonPath("$.nomor7").value(DEFAULT_NOMOR_7))
            .andExpect(jsonPath("$.jumlah7").value(DEFAULT_JUMLAH_7))
            .andExpect(jsonPath("$.nama8").value(DEFAULT_NAMA_8))
            .andExpect(jsonPath("$.alamat8").value(DEFAULT_ALAMAT_8))
            .andExpect(jsonPath("$.nomor8").value(DEFAULT_NOMOR_8))
            .andExpect(jsonPath("$.jumlah8").value(DEFAULT_JUMLAH_8))
            .andExpect(jsonPath("$.nama9").value(DEFAULT_NAMA_9))
            .andExpect(jsonPath("$.alamat9").value(DEFAULT_ALAMAT_9))
            .andExpect(jsonPath("$.nomor9").value(DEFAULT_NOMOR_9))
            .andExpect(jsonPath("$.jumlah9").value(DEFAULT_JUMLAH_9))
            .andExpect(jsonPath("$.nama10").value(DEFAULT_NAMA_10))
            .andExpect(jsonPath("$.alamat10").value(DEFAULT_ALAMAT_10))
            .andExpect(jsonPath("$.nomor10").value(DEFAULT_NOMOR_10))
            .andExpect(jsonPath("$.jumlah10").value(DEFAULT_JUMLAH_10))
            .andExpect(jsonPath("$.alamat6").value(DEFAULT_ALAMAT_6))
            .andExpect(jsonPath("$.nomor6").value(DEFAULT_NOMOR_6))
            .andExpect(jsonPath("$.jumlah6").value(DEFAULT_JUMLAH_6))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY))
            .andExpect(jsonPath("$.lastModifiedDate").value(DEFAULT_LAST_MODIFIED_DATE.toString()))
            .andExpect(jsonPath("$.lastModifiedBy").value(DEFAULT_LAST_MODIFIED_BY));
    }

    @Test
    @Transactional
    void getNonExistingFormBASTPBP() throws Exception {
        // Get the formBASTPBP
        restFormBASTPBPMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingFormBASTPBP() throws Exception {
        // Initialize the database
        formBASTPBP.setId(UUID.randomUUID().toString());
        formBASTPBPRepository.saveAndFlush(formBASTPBP);

        int databaseSizeBeforeUpdate = formBASTPBPRepository.findAll().size();

        // Update the formBASTPBP
        FormBASTPBP updatedFormBASTPBP = formBASTPBPRepository.findById(formBASTPBP.getId()).get();
        // Disconnect from session so that the updates on updatedFormBASTPBP are not directly saved in db
        em.detach(updatedFormBASTPBP);
        updatedFormBASTPBP
            .status(UPDATED_STATUS)
            .active(UPDATED_ACTIVE)
            .remarks(UPDATED_REMARKS)
            .contents(UPDATED_CONTENTS)
            .documentTitle(UPDATED_DOCUMENT_TITLE)
            .documentNumber(UPDATED_DOCUMENT_NUMBER)
            .kelurahanDesa(UPDATED_KELURAHAN_DESA)
            .kecamatan(UPDATED_KECAMATAN)
            .kabupatenKota(UPDATED_KABUPATEN_KOTA)
            .provinsi(UPDATED_PROVINSI)
            .rtRw(UPDATED_RT_RW)
            .kcu(UPDATED_KCU)
            .kantorSerah(UPDATED_KANTOR_SERAH)
            .bastNumber(UPDATED_BAST_NUMBER)
            .documentDescription(UPDATED_DOCUMENT_DESCRIPTION)
            .nama1(UPDATED_NAMA_1)
            .alamat1(UPDATED_ALAMAT_1)
            .nomor1(UPDATED_NOMOR_1)
            .jumlah1(UPDATED_JUMLAH_1)
            .nama2(UPDATED_NAMA_2)
            .alamat2(UPDATED_ALAMAT_2)
            .nomor2(UPDATED_NOMOR_2)
            .jumlah2(UPDATED_JUMLAH_2)
            .nama3(UPDATED_NAMA_3)
            .alamat3(UPDATED_ALAMAT_3)
            .nomor3(UPDATED_NOMOR_3)
            .jumlah3(UPDATED_JUMLAH_3)
            .nama4(UPDATED_NAMA_4)
            .alamat4(UPDATED_ALAMAT_4)
            .nomor4(UPDATED_NOMOR_4)
            .jumlah4(UPDATED_JUMLAH_4)
            .nama5(UPDATED_NAMA_5)
            .alamat5(UPDATED_ALAMAT_5)
            .nomor5(UPDATED_NOMOR_5)
            .jumlah5(UPDATED_JUMLAH_5)
            .nama6(UPDATED_NAMA_6)
            .nama7(UPDATED_NAMA_7)
            .alamat7(UPDATED_ALAMAT_7)
            .nomor7(UPDATED_NOMOR_7)
            .jumlah7(UPDATED_JUMLAH_7)
            .nama8(UPDATED_NAMA_8)
            .alamat8(UPDATED_ALAMAT_8)
            .nomor8(UPDATED_NOMOR_8)
            .jumlah8(UPDATED_JUMLAH_8)
            .nama9(UPDATED_NAMA_9)
            .alamat9(UPDATED_ALAMAT_9)
            .nomor9(UPDATED_NOMOR_9)
            .jumlah9(UPDATED_JUMLAH_9)
            .nama10(UPDATED_NAMA_10)
            .alamat10(UPDATED_ALAMAT_10)
            .nomor10(UPDATED_NOMOR_10)
            .jumlah10(UPDATED_JUMLAH_10)
            .alamat6(UPDATED_ALAMAT_6)
            .nomor6(UPDATED_NOMOR_6)
            .jumlah6(UPDATED_JUMLAH_6);

        restFormBASTPBPMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedFormBASTPBP.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedFormBASTPBP))
            )
            .andExpect(status().isOk());

        // Validate the FormBASTPBP in the database
        List<FormBASTPBP> formBASTPBPList = formBASTPBPRepository.findAll();
        assertThat(formBASTPBPList).hasSize(databaseSizeBeforeUpdate);
        FormBASTPBP testFormBASTPBP = formBASTPBPList.get(formBASTPBPList.size() - 1);
        assertThat(testFormBASTPBP.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testFormBASTPBP.getActive()).isEqualTo(UPDATED_ACTIVE);
        assertThat(testFormBASTPBP.getRemarks()).isEqualTo(UPDATED_REMARKS);
        assertThat(testFormBASTPBP.getContents()).isEqualTo(UPDATED_CONTENTS);
        assertThat(testFormBASTPBP.getDocumentTitle()).isEqualTo(UPDATED_DOCUMENT_TITLE);
        assertThat(testFormBASTPBP.getDocumentNumber()).isEqualTo(UPDATED_DOCUMENT_NUMBER);
        assertThat(testFormBASTPBP.getKelurahanDesa()).isEqualTo(UPDATED_KELURAHAN_DESA);
        assertThat(testFormBASTPBP.getKecamatan()).isEqualTo(UPDATED_KECAMATAN);
        assertThat(testFormBASTPBP.getKabupatenKota()).isEqualTo(UPDATED_KABUPATEN_KOTA);
        assertThat(testFormBASTPBP.getProvinsi()).isEqualTo(UPDATED_PROVINSI);
        assertThat(testFormBASTPBP.getRtRw()).isEqualTo(UPDATED_RT_RW);
        assertThat(testFormBASTPBP.getKcu()).isEqualTo(UPDATED_KCU);
        assertThat(testFormBASTPBP.getKantorSerah()).isEqualTo(UPDATED_KANTOR_SERAH);
        assertThat(testFormBASTPBP.getBastNumber()).isEqualTo(UPDATED_BAST_NUMBER);
        assertThat(testFormBASTPBP.getDocumentDescription()).isEqualTo(UPDATED_DOCUMENT_DESCRIPTION);
        assertThat(testFormBASTPBP.getNama1()).isEqualTo(UPDATED_NAMA_1);
        assertThat(testFormBASTPBP.getAlamat1()).isEqualTo(UPDATED_ALAMAT_1);
        assertThat(testFormBASTPBP.getNomor1()).isEqualTo(UPDATED_NOMOR_1);
        assertThat(testFormBASTPBP.getJumlah1()).isEqualTo(UPDATED_JUMLAH_1);
        assertThat(testFormBASTPBP.getNama2()).isEqualTo(UPDATED_NAMA_2);
        assertThat(testFormBASTPBP.getAlamat2()).isEqualTo(UPDATED_ALAMAT_2);
        assertThat(testFormBASTPBP.getNomor2()).isEqualTo(UPDATED_NOMOR_2);
        assertThat(testFormBASTPBP.getJumlah2()).isEqualTo(UPDATED_JUMLAH_2);
        assertThat(testFormBASTPBP.getNama3()).isEqualTo(UPDATED_NAMA_3);
        assertThat(testFormBASTPBP.getAlamat3()).isEqualTo(UPDATED_ALAMAT_3);
        assertThat(testFormBASTPBP.getNomor3()).isEqualTo(UPDATED_NOMOR_3);
        assertThat(testFormBASTPBP.getJumlah3()).isEqualTo(UPDATED_JUMLAH_3);
        assertThat(testFormBASTPBP.getNama4()).isEqualTo(UPDATED_NAMA_4);
        assertThat(testFormBASTPBP.getAlamat4()).isEqualTo(UPDATED_ALAMAT_4);
        assertThat(testFormBASTPBP.getNomor4()).isEqualTo(UPDATED_NOMOR_4);
        assertThat(testFormBASTPBP.getJumlah4()).isEqualTo(UPDATED_JUMLAH_4);
        assertThat(testFormBASTPBP.getNama5()).isEqualTo(UPDATED_NAMA_5);
        assertThat(testFormBASTPBP.getAlamat5()).isEqualTo(UPDATED_ALAMAT_5);
        assertThat(testFormBASTPBP.getNomor5()).isEqualTo(UPDATED_NOMOR_5);
        assertThat(testFormBASTPBP.getJumlah5()).isEqualTo(UPDATED_JUMLAH_5);
        assertThat(testFormBASTPBP.getNama6()).isEqualTo(UPDATED_NAMA_6);
        assertThat(testFormBASTPBP.getNama7()).isEqualTo(UPDATED_NAMA_7);
        assertThat(testFormBASTPBP.getAlamat7()).isEqualTo(UPDATED_ALAMAT_7);
        assertThat(testFormBASTPBP.getNomor7()).isEqualTo(UPDATED_NOMOR_7);
        assertThat(testFormBASTPBP.getJumlah7()).isEqualTo(UPDATED_JUMLAH_7);
        assertThat(testFormBASTPBP.getNama8()).isEqualTo(UPDATED_NAMA_8);
        assertThat(testFormBASTPBP.getAlamat8()).isEqualTo(UPDATED_ALAMAT_8);
        assertThat(testFormBASTPBP.getNomor8()).isEqualTo(UPDATED_NOMOR_8);
        assertThat(testFormBASTPBP.getJumlah8()).isEqualTo(UPDATED_JUMLAH_8);
        assertThat(testFormBASTPBP.getNama9()).isEqualTo(UPDATED_NAMA_9);
        assertThat(testFormBASTPBP.getAlamat9()).isEqualTo(UPDATED_ALAMAT_9);
        assertThat(testFormBASTPBP.getNomor9()).isEqualTo(UPDATED_NOMOR_9);
        assertThat(testFormBASTPBP.getJumlah9()).isEqualTo(UPDATED_JUMLAH_9);
        assertThat(testFormBASTPBP.getNama10()).isEqualTo(UPDATED_NAMA_10);
        assertThat(testFormBASTPBP.getAlamat10()).isEqualTo(UPDATED_ALAMAT_10);
        assertThat(testFormBASTPBP.getNomor10()).isEqualTo(UPDATED_NOMOR_10);
        assertThat(testFormBASTPBP.getJumlah10()).isEqualTo(UPDATED_JUMLAH_10);
        assertThat(testFormBASTPBP.getAlamat6()).isEqualTo(UPDATED_ALAMAT_6);
        assertThat(testFormBASTPBP.getNomor6()).isEqualTo(UPDATED_NOMOR_6);
        assertThat(testFormBASTPBP.getJumlah6()).isEqualTo(UPDATED_JUMLAH_6);
        assertThat(testFormBASTPBP.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testFormBASTPBP.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testFormBASTPBP.getLastModifiedDate()).isEqualTo(UPDATED_LAST_MODIFIED_DATE);
        assertThat(testFormBASTPBP.getLastModifiedBy()).isEqualTo(UPDATED_LAST_MODIFIED_BY);
    }

    @Test
    @Transactional
    void putNonExistingFormBASTPBP() throws Exception {
        int databaseSizeBeforeUpdate = formBASTPBPRepository.findAll().size();
        formBASTPBP.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFormBASTPBPMockMvc
            .perform(
                put(ENTITY_API_URL_ID, formBASTPBP.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(formBASTPBP))
            )
            .andExpect(status().isBadRequest());

        // Validate the FormBASTPBP in the database
        List<FormBASTPBP> formBASTPBPList = formBASTPBPRepository.findAll();
        assertThat(formBASTPBPList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchFormBASTPBP() throws Exception {
        int databaseSizeBeforeUpdate = formBASTPBPRepository.findAll().size();
        formBASTPBP.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restFormBASTPBPMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(formBASTPBP))
            )
            .andExpect(status().isBadRequest());

        // Validate the FormBASTPBP in the database
        List<FormBASTPBP> formBASTPBPList = formBASTPBPRepository.findAll();
        assertThat(formBASTPBPList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamFormBASTPBP() throws Exception {
        int databaseSizeBeforeUpdate = formBASTPBPRepository.findAll().size();
        formBASTPBP.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restFormBASTPBPMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(formBASTPBP)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the FormBASTPBP in the database
        List<FormBASTPBP> formBASTPBPList = formBASTPBPRepository.findAll();
        assertThat(formBASTPBPList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateFormBASTPBPWithPatch() throws Exception {
        // Initialize the database
        formBASTPBP.setId(UUID.randomUUID().toString());
        formBASTPBPRepository.saveAndFlush(formBASTPBP);

        int databaseSizeBeforeUpdate = formBASTPBPRepository.findAll().size();

        // Update the formBASTPBP using partial update
        FormBASTPBP partialUpdatedFormBASTPBP = new FormBASTPBP();
        partialUpdatedFormBASTPBP.setId(formBASTPBP.getId());

        partialUpdatedFormBASTPBP
            .documentTitle(UPDATED_DOCUMENT_TITLE)
            .documentNumber(UPDATED_DOCUMENT_NUMBER)
            .kabupatenKota(UPDATED_KABUPATEN_KOTA)
            .kantorSerah(UPDATED_KANTOR_SERAH)
            .bastNumber(UPDATED_BAST_NUMBER)
            .documentDescription(UPDATED_DOCUMENT_DESCRIPTION)
            .nama1(UPDATED_NAMA_1)
            .nomor1(UPDATED_NOMOR_1)
            .nama2(UPDATED_NAMA_2)
            .nomor2(UPDATED_NOMOR_2)
            .jumlah3(UPDATED_JUMLAH_3)
            .alamat4(UPDATED_ALAMAT_4)
            .nomor4(UPDATED_NOMOR_4)
            .jumlah4(UPDATED_JUMLAH_4)
            .nama5(UPDATED_NAMA_5)
            .nomor5(UPDATED_NOMOR_5)
            .jumlah5(UPDATED_JUMLAH_5)
            .alamat7(UPDATED_ALAMAT_7)
            .jumlah7(UPDATED_JUMLAH_7)
            .nama8(UPDATED_NAMA_8)
            .nama9(UPDATED_NAMA_9)
            .alamat9(UPDATED_ALAMAT_9)
            .jumlah9(UPDATED_JUMLAH_9)
            .nama10(UPDATED_NAMA_10)
            .alamat10(UPDATED_ALAMAT_10)
            .jumlah10(UPDATED_JUMLAH_10)
            .alamat6(UPDATED_ALAMAT_6)
            .nomor6(UPDATED_NOMOR_6)
            .createdDate(UPDATED_CREATED_DATE);

        restFormBASTPBPMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedFormBASTPBP.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedFormBASTPBP))
            )
            .andExpect(status().isOk());

        // Validate the FormBASTPBP in the database
        List<FormBASTPBP> formBASTPBPList = formBASTPBPRepository.findAll();
        assertThat(formBASTPBPList).hasSize(databaseSizeBeforeUpdate);
        FormBASTPBP testFormBASTPBP = formBASTPBPList.get(formBASTPBPList.size() - 1);
        assertThat(testFormBASTPBP.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testFormBASTPBP.getActive()).isEqualTo(DEFAULT_ACTIVE);
        assertThat(testFormBASTPBP.getRemarks()).isEqualTo(DEFAULT_REMARKS);
        assertThat(testFormBASTPBP.getContents()).isEqualTo(DEFAULT_CONTENTS);
        assertThat(testFormBASTPBP.getDocumentTitle()).isEqualTo(UPDATED_DOCUMENT_TITLE);
        assertThat(testFormBASTPBP.getDocumentNumber()).isEqualTo(UPDATED_DOCUMENT_NUMBER);
        assertThat(testFormBASTPBP.getKelurahanDesa()).isEqualTo(DEFAULT_KELURAHAN_DESA);
        assertThat(testFormBASTPBP.getKecamatan()).isEqualTo(DEFAULT_KECAMATAN);
        assertThat(testFormBASTPBP.getKabupatenKota()).isEqualTo(UPDATED_KABUPATEN_KOTA);
        assertThat(testFormBASTPBP.getProvinsi()).isEqualTo(DEFAULT_PROVINSI);
        assertThat(testFormBASTPBP.getRtRw()).isEqualTo(DEFAULT_RT_RW);
        assertThat(testFormBASTPBP.getKcu()).isEqualTo(DEFAULT_KCU);
        assertThat(testFormBASTPBP.getKantorSerah()).isEqualTo(UPDATED_KANTOR_SERAH);
        assertThat(testFormBASTPBP.getBastNumber()).isEqualTo(UPDATED_BAST_NUMBER);
        assertThat(testFormBASTPBP.getDocumentDescription()).isEqualTo(UPDATED_DOCUMENT_DESCRIPTION);
        assertThat(testFormBASTPBP.getNama1()).isEqualTo(UPDATED_NAMA_1);
        assertThat(testFormBASTPBP.getAlamat1()).isEqualTo(DEFAULT_ALAMAT_1);
        assertThat(testFormBASTPBP.getNomor1()).isEqualTo(UPDATED_NOMOR_1);
        assertThat(testFormBASTPBP.getJumlah1()).isEqualTo(DEFAULT_JUMLAH_1);
        assertThat(testFormBASTPBP.getNama2()).isEqualTo(UPDATED_NAMA_2);
        assertThat(testFormBASTPBP.getAlamat2()).isEqualTo(DEFAULT_ALAMAT_2);
        assertThat(testFormBASTPBP.getNomor2()).isEqualTo(UPDATED_NOMOR_2);
        assertThat(testFormBASTPBP.getJumlah2()).isEqualTo(DEFAULT_JUMLAH_2);
        assertThat(testFormBASTPBP.getNama3()).isEqualTo(DEFAULT_NAMA_3);
        assertThat(testFormBASTPBP.getAlamat3()).isEqualTo(DEFAULT_ALAMAT_3);
        assertThat(testFormBASTPBP.getNomor3()).isEqualTo(DEFAULT_NOMOR_3);
        assertThat(testFormBASTPBP.getJumlah3()).isEqualTo(UPDATED_JUMLAH_3);
        assertThat(testFormBASTPBP.getNama4()).isEqualTo(DEFAULT_NAMA_4);
        assertThat(testFormBASTPBP.getAlamat4()).isEqualTo(UPDATED_ALAMAT_4);
        assertThat(testFormBASTPBP.getNomor4()).isEqualTo(UPDATED_NOMOR_4);
        assertThat(testFormBASTPBP.getJumlah4()).isEqualTo(UPDATED_JUMLAH_4);
        assertThat(testFormBASTPBP.getNama5()).isEqualTo(UPDATED_NAMA_5);
        assertThat(testFormBASTPBP.getAlamat5()).isEqualTo(DEFAULT_ALAMAT_5);
        assertThat(testFormBASTPBP.getNomor5()).isEqualTo(UPDATED_NOMOR_5);
        assertThat(testFormBASTPBP.getJumlah5()).isEqualTo(UPDATED_JUMLAH_5);
        assertThat(testFormBASTPBP.getNama6()).isEqualTo(DEFAULT_NAMA_6);
        assertThat(testFormBASTPBP.getNama7()).isEqualTo(DEFAULT_NAMA_7);
        assertThat(testFormBASTPBP.getAlamat7()).isEqualTo(UPDATED_ALAMAT_7);
        assertThat(testFormBASTPBP.getNomor7()).isEqualTo(DEFAULT_NOMOR_7);
        assertThat(testFormBASTPBP.getJumlah7()).isEqualTo(UPDATED_JUMLAH_7);
        assertThat(testFormBASTPBP.getNama8()).isEqualTo(UPDATED_NAMA_8);
        assertThat(testFormBASTPBP.getAlamat8()).isEqualTo(DEFAULT_ALAMAT_8);
        assertThat(testFormBASTPBP.getNomor8()).isEqualTo(DEFAULT_NOMOR_8);
        assertThat(testFormBASTPBP.getJumlah8()).isEqualTo(DEFAULT_JUMLAH_8);
        assertThat(testFormBASTPBP.getNama9()).isEqualTo(UPDATED_NAMA_9);
        assertThat(testFormBASTPBP.getAlamat9()).isEqualTo(UPDATED_ALAMAT_9);
        assertThat(testFormBASTPBP.getNomor9()).isEqualTo(DEFAULT_NOMOR_9);
        assertThat(testFormBASTPBP.getJumlah9()).isEqualTo(UPDATED_JUMLAH_9);
        assertThat(testFormBASTPBP.getNama10()).isEqualTo(UPDATED_NAMA_10);
        assertThat(testFormBASTPBP.getAlamat10()).isEqualTo(UPDATED_ALAMAT_10);
        assertThat(testFormBASTPBP.getNomor10()).isEqualTo(DEFAULT_NOMOR_10);
        assertThat(testFormBASTPBP.getJumlah10()).isEqualTo(UPDATED_JUMLAH_10);
        assertThat(testFormBASTPBP.getAlamat6()).isEqualTo(UPDATED_ALAMAT_6);
        assertThat(testFormBASTPBP.getNomor6()).isEqualTo(UPDATED_NOMOR_6);
        assertThat(testFormBASTPBP.getJumlah6()).isEqualTo(DEFAULT_JUMLAH_6);
        assertThat(testFormBASTPBP.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testFormBASTPBP.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testFormBASTPBP.getLastModifiedDate()).isEqualTo(DEFAULT_LAST_MODIFIED_DATE);
        assertThat(testFormBASTPBP.getLastModifiedBy()).isEqualTo(DEFAULT_LAST_MODIFIED_BY);
    }

    @Test
    @Transactional
    void fullUpdateFormBASTPBPWithPatch() throws Exception {
        // Initialize the database
        formBASTPBP.setId(UUID.randomUUID().toString());
        formBASTPBPRepository.saveAndFlush(formBASTPBP);

        int databaseSizeBeforeUpdate = formBASTPBPRepository.findAll().size();

        // Update the formBASTPBP using partial update
        FormBASTPBP partialUpdatedFormBASTPBP = new FormBASTPBP();
        partialUpdatedFormBASTPBP.setId(formBASTPBP.getId());

        partialUpdatedFormBASTPBP
            .status(UPDATED_STATUS)
            .active(UPDATED_ACTIVE)
            .remarks(UPDATED_REMARKS)
            .contents(UPDATED_CONTENTS)
            .documentTitle(UPDATED_DOCUMENT_TITLE)
            .documentNumber(UPDATED_DOCUMENT_NUMBER)
            .kelurahanDesa(UPDATED_KELURAHAN_DESA)
            .kecamatan(UPDATED_KECAMATAN)
            .kabupatenKota(UPDATED_KABUPATEN_KOTA)
            .provinsi(UPDATED_PROVINSI)
            .rtRw(UPDATED_RT_RW)
            .kcu(UPDATED_KCU)
            .kantorSerah(UPDATED_KANTOR_SERAH)
            .bastNumber(UPDATED_BAST_NUMBER)
            .documentDescription(UPDATED_DOCUMENT_DESCRIPTION)
            .nama1(UPDATED_NAMA_1)
            .alamat1(UPDATED_ALAMAT_1)
            .nomor1(UPDATED_NOMOR_1)
            .jumlah1(UPDATED_JUMLAH_1)
            .nama2(UPDATED_NAMA_2)
            .alamat2(UPDATED_ALAMAT_2)
            .nomor2(UPDATED_NOMOR_2)
            .jumlah2(UPDATED_JUMLAH_2)
            .nama3(UPDATED_NAMA_3)
            .alamat3(UPDATED_ALAMAT_3)
            .nomor3(UPDATED_NOMOR_3)
            .jumlah3(UPDATED_JUMLAH_3)
            .nama4(UPDATED_NAMA_4)
            .alamat4(UPDATED_ALAMAT_4)
            .nomor4(UPDATED_NOMOR_4)
            .jumlah4(UPDATED_JUMLAH_4)
            .nama5(UPDATED_NAMA_5)
            .alamat5(UPDATED_ALAMAT_5)
            .nomor5(UPDATED_NOMOR_5)
            .jumlah5(UPDATED_JUMLAH_5)
            .nama6(UPDATED_NAMA_6)
            .nama7(UPDATED_NAMA_7)
            .alamat7(UPDATED_ALAMAT_7)
            .nomor7(UPDATED_NOMOR_7)
            .jumlah7(UPDATED_JUMLAH_7)
            .nama8(UPDATED_NAMA_8)
            .alamat8(UPDATED_ALAMAT_8)
            .nomor8(UPDATED_NOMOR_8)
            .jumlah8(UPDATED_JUMLAH_8)
            .nama9(UPDATED_NAMA_9)
            .alamat9(UPDATED_ALAMAT_9)
            .nomor9(UPDATED_NOMOR_9)
            .jumlah9(UPDATED_JUMLAH_9)
            .nama10(UPDATED_NAMA_10)
            .alamat10(UPDATED_ALAMAT_10)
            .nomor10(UPDATED_NOMOR_10)
            .jumlah10(UPDATED_JUMLAH_10)
            .alamat6(UPDATED_ALAMAT_6)
            .nomor6(UPDATED_NOMOR_6)
            .jumlah6(UPDATED_JUMLAH_6);

        restFormBASTPBPMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedFormBASTPBP.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedFormBASTPBP))
            )
            .andExpect(status().isOk());

        // Validate the FormBASTPBP in the database
        List<FormBASTPBP> formBASTPBPList = formBASTPBPRepository.findAll();
        assertThat(formBASTPBPList).hasSize(databaseSizeBeforeUpdate);
        FormBASTPBP testFormBASTPBP = formBASTPBPList.get(formBASTPBPList.size() - 1);
        assertThat(testFormBASTPBP.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testFormBASTPBP.getActive()).isEqualTo(UPDATED_ACTIVE);
        assertThat(testFormBASTPBP.getRemarks()).isEqualTo(UPDATED_REMARKS);
        assertThat(testFormBASTPBP.getContents()).isEqualTo(UPDATED_CONTENTS);
        assertThat(testFormBASTPBP.getDocumentTitle()).isEqualTo(UPDATED_DOCUMENT_TITLE);
        assertThat(testFormBASTPBP.getDocumentNumber()).isEqualTo(UPDATED_DOCUMENT_NUMBER);
        assertThat(testFormBASTPBP.getKelurahanDesa()).isEqualTo(UPDATED_KELURAHAN_DESA);
        assertThat(testFormBASTPBP.getKecamatan()).isEqualTo(UPDATED_KECAMATAN);
        assertThat(testFormBASTPBP.getKabupatenKota()).isEqualTo(UPDATED_KABUPATEN_KOTA);
        assertThat(testFormBASTPBP.getProvinsi()).isEqualTo(UPDATED_PROVINSI);
        assertThat(testFormBASTPBP.getRtRw()).isEqualTo(UPDATED_RT_RW);
        assertThat(testFormBASTPBP.getKcu()).isEqualTo(UPDATED_KCU);
        assertThat(testFormBASTPBP.getKantorSerah()).isEqualTo(UPDATED_KANTOR_SERAH);
        assertThat(testFormBASTPBP.getBastNumber()).isEqualTo(UPDATED_BAST_NUMBER);
        assertThat(testFormBASTPBP.getDocumentDescription()).isEqualTo(UPDATED_DOCUMENT_DESCRIPTION);
        assertThat(testFormBASTPBP.getNama1()).isEqualTo(UPDATED_NAMA_1);
        assertThat(testFormBASTPBP.getAlamat1()).isEqualTo(UPDATED_ALAMAT_1);
        assertThat(testFormBASTPBP.getNomor1()).isEqualTo(UPDATED_NOMOR_1);
        assertThat(testFormBASTPBP.getJumlah1()).isEqualTo(UPDATED_JUMLAH_1);
        assertThat(testFormBASTPBP.getNama2()).isEqualTo(UPDATED_NAMA_2);
        assertThat(testFormBASTPBP.getAlamat2()).isEqualTo(UPDATED_ALAMAT_2);
        assertThat(testFormBASTPBP.getNomor2()).isEqualTo(UPDATED_NOMOR_2);
        assertThat(testFormBASTPBP.getJumlah2()).isEqualTo(UPDATED_JUMLAH_2);
        assertThat(testFormBASTPBP.getNama3()).isEqualTo(UPDATED_NAMA_3);
        assertThat(testFormBASTPBP.getAlamat3()).isEqualTo(UPDATED_ALAMAT_3);
        assertThat(testFormBASTPBP.getNomor3()).isEqualTo(UPDATED_NOMOR_3);
        assertThat(testFormBASTPBP.getJumlah3()).isEqualTo(UPDATED_JUMLAH_3);
        assertThat(testFormBASTPBP.getNama4()).isEqualTo(UPDATED_NAMA_4);
        assertThat(testFormBASTPBP.getAlamat4()).isEqualTo(UPDATED_ALAMAT_4);
        assertThat(testFormBASTPBP.getNomor4()).isEqualTo(UPDATED_NOMOR_4);
        assertThat(testFormBASTPBP.getJumlah4()).isEqualTo(UPDATED_JUMLAH_4);
        assertThat(testFormBASTPBP.getNama5()).isEqualTo(UPDATED_NAMA_5);
        assertThat(testFormBASTPBP.getAlamat5()).isEqualTo(UPDATED_ALAMAT_5);
        assertThat(testFormBASTPBP.getNomor5()).isEqualTo(UPDATED_NOMOR_5);
        assertThat(testFormBASTPBP.getJumlah5()).isEqualTo(UPDATED_JUMLAH_5);
        assertThat(testFormBASTPBP.getNama6()).isEqualTo(UPDATED_NAMA_6);
        assertThat(testFormBASTPBP.getNama7()).isEqualTo(UPDATED_NAMA_7);
        assertThat(testFormBASTPBP.getAlamat7()).isEqualTo(UPDATED_ALAMAT_7);
        assertThat(testFormBASTPBP.getNomor7()).isEqualTo(UPDATED_NOMOR_7);
        assertThat(testFormBASTPBP.getJumlah7()).isEqualTo(UPDATED_JUMLAH_7);
        assertThat(testFormBASTPBP.getNama8()).isEqualTo(UPDATED_NAMA_8);
        assertThat(testFormBASTPBP.getAlamat8()).isEqualTo(UPDATED_ALAMAT_8);
        assertThat(testFormBASTPBP.getNomor8()).isEqualTo(UPDATED_NOMOR_8);
        assertThat(testFormBASTPBP.getJumlah8()).isEqualTo(UPDATED_JUMLAH_8);
        assertThat(testFormBASTPBP.getNama9()).isEqualTo(UPDATED_NAMA_9);
        assertThat(testFormBASTPBP.getAlamat9()).isEqualTo(UPDATED_ALAMAT_9);
        assertThat(testFormBASTPBP.getNomor9()).isEqualTo(UPDATED_NOMOR_9);
        assertThat(testFormBASTPBP.getJumlah9()).isEqualTo(UPDATED_JUMLAH_9);
        assertThat(testFormBASTPBP.getNama10()).isEqualTo(UPDATED_NAMA_10);
        assertThat(testFormBASTPBP.getAlamat10()).isEqualTo(UPDATED_ALAMAT_10);
        assertThat(testFormBASTPBP.getNomor10()).isEqualTo(UPDATED_NOMOR_10);
        assertThat(testFormBASTPBP.getJumlah10()).isEqualTo(UPDATED_JUMLAH_10);
        assertThat(testFormBASTPBP.getAlamat6()).isEqualTo(UPDATED_ALAMAT_6);
        assertThat(testFormBASTPBP.getNomor6()).isEqualTo(UPDATED_NOMOR_6);
        assertThat(testFormBASTPBP.getJumlah6()).isEqualTo(UPDATED_JUMLAH_6);
        assertThat(testFormBASTPBP.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testFormBASTPBP.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testFormBASTPBP.getLastModifiedDate()).isEqualTo(UPDATED_LAST_MODIFIED_DATE);
        assertThat(testFormBASTPBP.getLastModifiedBy()).isEqualTo(UPDATED_LAST_MODIFIED_BY);
    }

    @Test
    @Transactional
    void patchNonExistingFormBASTPBP() throws Exception {
        int databaseSizeBeforeUpdate = formBASTPBPRepository.findAll().size();
        formBASTPBP.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFormBASTPBPMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, formBASTPBP.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(formBASTPBP))
            )
            .andExpect(status().isBadRequest());

        // Validate the FormBASTPBP in the database
        List<FormBASTPBP> formBASTPBPList = formBASTPBPRepository.findAll();
        assertThat(formBASTPBPList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchFormBASTPBP() throws Exception {
        int databaseSizeBeforeUpdate = formBASTPBPRepository.findAll().size();
        formBASTPBP.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restFormBASTPBPMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(formBASTPBP))
            )
            .andExpect(status().isBadRequest());

        // Validate the FormBASTPBP in the database
        List<FormBASTPBP> formBASTPBPList = formBASTPBPRepository.findAll();
        assertThat(formBASTPBPList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamFormBASTPBP() throws Exception {
        int databaseSizeBeforeUpdate = formBASTPBPRepository.findAll().size();
        formBASTPBP.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restFormBASTPBPMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(formBASTPBP))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the FormBASTPBP in the database
        List<FormBASTPBP> formBASTPBPList = formBASTPBPRepository.findAll();
        assertThat(formBASTPBPList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteFormBASTPBP() throws Exception {
        // Initialize the database
        formBASTPBP.setId(UUID.randomUUID().toString());
        formBASTPBPRepository.saveAndFlush(formBASTPBP);

        int databaseSizeBeforeDelete = formBASTPBPRepository.findAll().size();

        // Delete the formBASTPBP
        restFormBASTPBPMockMvc
            .perform(delete(ENTITY_API_URL_ID, formBASTPBP.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<FormBASTPBP> formBASTPBPList = formBASTPBPRepository.findAll();
        assertThat(formBASTPBPList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
