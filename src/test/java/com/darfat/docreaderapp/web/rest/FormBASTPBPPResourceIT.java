package com.darfat.docreaderapp.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.darfat.docreaderapp.IntegrationTest;
import com.darfat.docreaderapp.domain.FormBASTPBPP;
import com.darfat.docreaderapp.repository.FormBASTPBPPRepository;
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
 * Integration tests for the {@link FormBASTPBPPResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class FormBASTPBPPResourceIT {

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

    private static final String DEFAULT_KELURAHAN_DESA = "AAAAAAAAAA";
    private static final String UPDATED_KELURAHAN_DESA = "BBBBBBBBBB";

    private static final String DEFAULT_KECAMATAN = "AAAAAAAAAA";
    private static final String UPDATED_KECAMATAN = "BBBBBBBBBB";

    private static final String DEFAULT_KABUPATEN_KOTA = "AAAAAAAAAA";
    private static final String UPDATED_KABUPATEN_KOTA = "BBBBBBBBBB";

    private static final String DEFAULT_PROVINSI = "AAAAAAAAAA";
    private static final String UPDATED_PROVINSI = "BBBBBBBBBB";

    private static final String DEFAULT_PBP_TIDAK_DITEMUKAN_1 = "AAAAAAAAAA";
    private static final String UPDATED_PBP_TIDAK_DITEMUKAN_1 = "BBBBBBBBBB";

    private static final String DEFAULT_SEBAB_PENGGANTIAN_1 = "AAAAAAAAAA";
    private static final String UPDATED_SEBAB_PENGGANTIAN_1 = "BBBBBBBBBB";

    private static final String DEFAULT_PBP_PENGGANTI_1 = "AAAAAAAAAA";
    private static final String UPDATED_PBP_PENGGANTI_1 = "BBBBBBBBBB";

    private static final String DEFAULT_ALAMAT_PBP_PENGGANTI_1 = "AAAAAAAAAA";
    private static final String UPDATED_ALAMAT_PBP_PENGGANTI_1 = "BBBBBBBBBB";

    private static final String DEFAULT_PBP_TIDAK_DITEMUKAN_2 = "AAAAAAAAAA";
    private static final String UPDATED_PBP_TIDAK_DITEMUKAN_2 = "BBBBBBBBBB";

    private static final String DEFAULT_SEBAB_PENGGANTIAN_2 = "AAAAAAAAAA";
    private static final String UPDATED_SEBAB_PENGGANTIAN_2 = "BBBBBBBBBB";

    private static final String DEFAULT_PBP_PENGGANTI_2 = "AAAAAAAAAA";
    private static final String UPDATED_PBP_PENGGANTI_2 = "BBBBBBBBBB";

    private static final String DEFAULT_ALAMAT_PBP_PENGGANTI_2 = "AAAAAAAAAA";
    private static final String UPDATED_ALAMAT_PBP_PENGGANTI_2 = "BBBBBBBBBB";

    private static final String DEFAULT_PBP_TIDAK_DITEMUKAN_3 = "AAAAAAAAAA";
    private static final String UPDATED_PBP_TIDAK_DITEMUKAN_3 = "BBBBBBBBBB";

    private static final String DEFAULT_SEBAB_PENGGANTIAN_3 = "AAAAAAAAAA";
    private static final String UPDATED_SEBAB_PENGGANTIAN_3 = "BBBBBBBBBB";

    private static final String DEFAULT_PBP_PENGGANTI_3 = "AAAAAAAAAA";
    private static final String UPDATED_PBP_PENGGANTI_3 = "BBBBBBBBBB";

    private static final String DEFAULT_ALAMAT_PBP_PENGGANTI_3 = "AAAAAAAAAA";
    private static final String UPDATED_ALAMAT_PBP_PENGGANTI_3 = "BBBBBBBBBB";

    private static final String DEFAULT_PBP_TIDAK_DITEMUKAN_4 = "AAAAAAAAAA";
    private static final String UPDATED_PBP_TIDAK_DITEMUKAN_4 = "BBBBBBBBBB";

    private static final String DEFAULT_SEBAB_PENGGANTIAN_4 = "AAAAAAAAAA";
    private static final String UPDATED_SEBAB_PENGGANTIAN_4 = "BBBBBBBBBB";

    private static final String DEFAULT_PBP_PENGGANTI_4 = "AAAAAAAAAA";
    private static final String UPDATED_PBP_PENGGANTI_4 = "BBBBBBBBBB";

    private static final String DEFAULT_ALAMAT_PBP_PENGGANTI_4 = "AAAAAAAAAA";
    private static final String UPDATED_ALAMAT_PBP_PENGGANTI_4 = "BBBBBBBBBB";

    private static final String DEFAULT_PBP_TIDAK_DITEMUKAN_5 = "AAAAAAAAAA";
    private static final String UPDATED_PBP_TIDAK_DITEMUKAN_5 = "BBBBBBBBBB";

    private static final String DEFAULT_SEBAB_PENGGANTIAN_5 = "AAAAAAAAAA";
    private static final String UPDATED_SEBAB_PENGGANTIAN_5 = "BBBBBBBBBB";

    private static final String DEFAULT_PBP_PENGGANTI_5 = "AAAAAAAAAA";
    private static final String UPDATED_PBP_PENGGANTI_5 = "BBBBBBBBBB";

    private static final String DEFAULT_ALAMAT_PBP_PENGGANTI_5 = "AAAAAAAAAA";
    private static final String UPDATED_ALAMAT_PBP_PENGGANTI_5 = "BBBBBBBBBB";

    private static final Instant DEFAULT_CREATED_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_CREATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY = "BBBBBBBBBB";

    private static final Instant DEFAULT_LAST_MODIFIED_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_LAST_MODIFIED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_LAST_MODIFIED_BY = "AAAAAAAAAA";
    private static final String UPDATED_LAST_MODIFIED_BY = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/form-bastpbpps";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private FormBASTPBPPRepository formBASTPBPPRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restFormBASTPBPPMockMvc;

    private FormBASTPBPP formBASTPBPP;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FormBASTPBPP createEntity(EntityManager em) {
        FormBASTPBPP formBASTPBPP = new FormBASTPBPP()
            .status(DEFAULT_STATUS)
            .active(DEFAULT_ACTIVE)
            .remarks(DEFAULT_REMARKS)
            .contents(DEFAULT_CONTENTS)
            .documentTitle(DEFAULT_DOCUMENT_TITLE)
            .kelurahanDesa(DEFAULT_KELURAHAN_DESA)
            .kecamatan(DEFAULT_KECAMATAN)
            .kabupatenKota(DEFAULT_KABUPATEN_KOTA)
            .provinsi(DEFAULT_PROVINSI)
            .pbpTidakDitemukan1(DEFAULT_PBP_TIDAK_DITEMUKAN_1)
            .sebabPenggantian1(DEFAULT_SEBAB_PENGGANTIAN_1)
            .pbpPengganti1(DEFAULT_PBP_PENGGANTI_1)
            .alamatPbpPengganti1(DEFAULT_ALAMAT_PBP_PENGGANTI_1)
            .pbpTidakDitemukan2(DEFAULT_PBP_TIDAK_DITEMUKAN_2)
            .sebabPenggantian2(DEFAULT_SEBAB_PENGGANTIAN_2)
            .pbpPengganti2(DEFAULT_PBP_PENGGANTI_2)
            .alamatPbpPengganti2(DEFAULT_ALAMAT_PBP_PENGGANTI_2)
            .pbpTidakDitemukan3(DEFAULT_PBP_TIDAK_DITEMUKAN_3)
            .sebabPenggantian3(DEFAULT_SEBAB_PENGGANTIAN_3)
            .pbpPengganti3(DEFAULT_PBP_PENGGANTI_3)
            .alamatPbpPengganti3(DEFAULT_ALAMAT_PBP_PENGGANTI_3)
            .pbpTidakDitemukan4(DEFAULT_PBP_TIDAK_DITEMUKAN_4)
            .sebabPenggantian4(DEFAULT_SEBAB_PENGGANTIAN_4)
            .pbpPengganti4(DEFAULT_PBP_PENGGANTI_4)
            .alamatPbpPengganti4(DEFAULT_ALAMAT_PBP_PENGGANTI_4)
            .pbpTidakDitemukan5(DEFAULT_PBP_TIDAK_DITEMUKAN_5)
            .sebabPenggantian5(DEFAULT_SEBAB_PENGGANTIAN_5)
            .pbpPengganti5(DEFAULT_PBP_PENGGANTI_5)
            .alamatPbpPengganti5(DEFAULT_ALAMAT_PBP_PENGGANTI_5);
        return formBASTPBPP;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FormBASTPBPP createUpdatedEntity(EntityManager em) {
        FormBASTPBPP formBASTPBPP = new FormBASTPBPP()
            .status(UPDATED_STATUS)
            .active(UPDATED_ACTIVE)
            .remarks(UPDATED_REMARKS)
            .contents(UPDATED_CONTENTS)
            .documentTitle(UPDATED_DOCUMENT_TITLE)
            .kelurahanDesa(UPDATED_KELURAHAN_DESA)
            .kecamatan(UPDATED_KECAMATAN)
            .kabupatenKota(UPDATED_KABUPATEN_KOTA)
            .provinsi(UPDATED_PROVINSI)
            .pbpTidakDitemukan1(UPDATED_PBP_TIDAK_DITEMUKAN_1)
            .sebabPenggantian1(UPDATED_SEBAB_PENGGANTIAN_1)
            .pbpPengganti1(UPDATED_PBP_PENGGANTI_1)
            .alamatPbpPengganti1(UPDATED_ALAMAT_PBP_PENGGANTI_1)
            .pbpTidakDitemukan2(UPDATED_PBP_TIDAK_DITEMUKAN_2)
            .sebabPenggantian2(UPDATED_SEBAB_PENGGANTIAN_2)
            .pbpPengganti2(UPDATED_PBP_PENGGANTI_2)
            .alamatPbpPengganti2(UPDATED_ALAMAT_PBP_PENGGANTI_2)
            .pbpTidakDitemukan3(UPDATED_PBP_TIDAK_DITEMUKAN_3)
            .sebabPenggantian3(UPDATED_SEBAB_PENGGANTIAN_3)
            .pbpPengganti3(UPDATED_PBP_PENGGANTI_3)
            .alamatPbpPengganti3(UPDATED_ALAMAT_PBP_PENGGANTI_3)
            .pbpTidakDitemukan4(UPDATED_PBP_TIDAK_DITEMUKAN_4)
            .sebabPenggantian4(UPDATED_SEBAB_PENGGANTIAN_4)
            .pbpPengganti4(UPDATED_PBP_PENGGANTI_4)
            .alamatPbpPengganti4(UPDATED_ALAMAT_PBP_PENGGANTI_4)
            .pbpTidakDitemukan5(UPDATED_PBP_TIDAK_DITEMUKAN_5)
            .sebabPenggantian5(UPDATED_SEBAB_PENGGANTIAN_5)
            .pbpPengganti5(UPDATED_PBP_PENGGANTI_5)
            .alamatPbpPengganti5(UPDATED_ALAMAT_PBP_PENGGANTI_5);
        return formBASTPBPP;
    }

    @BeforeEach
    public void initTest() {
        formBASTPBPP = createEntity(em);
    }

    @Test
    @Transactional
    void createFormBASTPBPP() throws Exception {
        int databaseSizeBeforeCreate = formBASTPBPPRepository.findAll().size();
        // Create the FormBASTPBPP
        restFormBASTPBPPMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(formBASTPBPP)))
            .andExpect(status().isCreated());

        // Validate the FormBASTPBPP in the database
        List<FormBASTPBPP> formBASTPBPPList = formBASTPBPPRepository.findAll();
        assertThat(formBASTPBPPList).hasSize(databaseSizeBeforeCreate + 1);
        FormBASTPBPP testFormBASTPBPP = formBASTPBPPList.get(formBASTPBPPList.size() - 1);
        assertThat(testFormBASTPBPP.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testFormBASTPBPP.getActive()).isEqualTo(DEFAULT_ACTIVE);
        assertThat(testFormBASTPBPP.getRemarks()).isEqualTo(DEFAULT_REMARKS);
        assertThat(testFormBASTPBPP.getContents()).isEqualTo(DEFAULT_CONTENTS);
        assertThat(testFormBASTPBPP.getDocumentTitle()).isEqualTo(DEFAULT_DOCUMENT_TITLE);
        assertThat(testFormBASTPBPP.getKelurahanDesa()).isEqualTo(DEFAULT_KELURAHAN_DESA);
        assertThat(testFormBASTPBPP.getKecamatan()).isEqualTo(DEFAULT_KECAMATAN);
        assertThat(testFormBASTPBPP.getKabupatenKota()).isEqualTo(DEFAULT_KABUPATEN_KOTA);
        assertThat(testFormBASTPBPP.getProvinsi()).isEqualTo(DEFAULT_PROVINSI);
        assertThat(testFormBASTPBPP.getPbpTidakDitemukan1()).isEqualTo(DEFAULT_PBP_TIDAK_DITEMUKAN_1);
        assertThat(testFormBASTPBPP.getSebabPenggantian1()).isEqualTo(DEFAULT_SEBAB_PENGGANTIAN_1);
        assertThat(testFormBASTPBPP.getPbpPengganti1()).isEqualTo(DEFAULT_PBP_PENGGANTI_1);
        assertThat(testFormBASTPBPP.getAlamatPbpPengganti1()).isEqualTo(DEFAULT_ALAMAT_PBP_PENGGANTI_1);
        assertThat(testFormBASTPBPP.getPbpTidakDitemukan2()).isEqualTo(DEFAULT_PBP_TIDAK_DITEMUKAN_2);
        assertThat(testFormBASTPBPP.getSebabPenggantian2()).isEqualTo(DEFAULT_SEBAB_PENGGANTIAN_2);
        assertThat(testFormBASTPBPP.getPbpPengganti2()).isEqualTo(DEFAULT_PBP_PENGGANTI_2);
        assertThat(testFormBASTPBPP.getAlamatPbpPengganti2()).isEqualTo(DEFAULT_ALAMAT_PBP_PENGGANTI_2);
        assertThat(testFormBASTPBPP.getPbpTidakDitemukan3()).isEqualTo(DEFAULT_PBP_TIDAK_DITEMUKAN_3);
        assertThat(testFormBASTPBPP.getSebabPenggantian3()).isEqualTo(DEFAULT_SEBAB_PENGGANTIAN_3);
        assertThat(testFormBASTPBPP.getPbpPengganti3()).isEqualTo(DEFAULT_PBP_PENGGANTI_3);
        assertThat(testFormBASTPBPP.getAlamatPbpPengganti3()).isEqualTo(DEFAULT_ALAMAT_PBP_PENGGANTI_3);
        assertThat(testFormBASTPBPP.getPbpTidakDitemukan4()).isEqualTo(DEFAULT_PBP_TIDAK_DITEMUKAN_4);
        assertThat(testFormBASTPBPP.getSebabPenggantian4()).isEqualTo(DEFAULT_SEBAB_PENGGANTIAN_4);
        assertThat(testFormBASTPBPP.getPbpPengganti4()).isEqualTo(DEFAULT_PBP_PENGGANTI_4);
        assertThat(testFormBASTPBPP.getAlamatPbpPengganti4()).isEqualTo(DEFAULT_ALAMAT_PBP_PENGGANTI_4);
        assertThat(testFormBASTPBPP.getPbpTidakDitemukan5()).isEqualTo(DEFAULT_PBP_TIDAK_DITEMUKAN_5);
        assertThat(testFormBASTPBPP.getSebabPenggantian5()).isEqualTo(DEFAULT_SEBAB_PENGGANTIAN_5);
        assertThat(testFormBASTPBPP.getPbpPengganti5()).isEqualTo(DEFAULT_PBP_PENGGANTI_5);
        assertThat(testFormBASTPBPP.getAlamatPbpPengganti5()).isEqualTo(DEFAULT_ALAMAT_PBP_PENGGANTI_5);
        assertThat(testFormBASTPBPP.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testFormBASTPBPP.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testFormBASTPBPP.getLastModifiedDate()).isEqualTo(DEFAULT_LAST_MODIFIED_DATE);
        assertThat(testFormBASTPBPP.getLastModifiedBy()).isEqualTo(DEFAULT_LAST_MODIFIED_BY);
    }

    @Test
    @Transactional
    void createFormBASTPBPPWithExistingId() throws Exception {
        // Create the FormBASTPBPP with an existing ID
        formBASTPBPP.setId("existing_id");

        int databaseSizeBeforeCreate = formBASTPBPPRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restFormBASTPBPPMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(formBASTPBPP)))
            .andExpect(status().isBadRequest());

        // Validate the FormBASTPBPP in the database
        List<FormBASTPBPP> formBASTPBPPList = formBASTPBPPRepository.findAll();
        assertThat(formBASTPBPPList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkActiveIsRequired() throws Exception {
        int databaseSizeBeforeTest = formBASTPBPPRepository.findAll().size();
        // set the field null
        formBASTPBPP.setActive(null);

        // Create the FormBASTPBPP, which fails.

        restFormBASTPBPPMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(formBASTPBPP)))
            .andExpect(status().isBadRequest());

        List<FormBASTPBPP> formBASTPBPPList = formBASTPBPPRepository.findAll();
        assertThat(formBASTPBPPList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCreatedDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = formBASTPBPPRepository.findAll().size();
        // set the field null
        formBASTPBPP.setCreatedDate(null);

        // Create the FormBASTPBPP, which fails.

        restFormBASTPBPPMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(formBASTPBPP)))
            .andExpect(status().isBadRequest());

        List<FormBASTPBPP> formBASTPBPPList = formBASTPBPPRepository.findAll();
        assertThat(formBASTPBPPList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllFormBASTPBPPS() throws Exception {
        // Initialize the database
        formBASTPBPP.setId(UUID.randomUUID().toString());
        formBASTPBPPRepository.saveAndFlush(formBASTPBPP);

        // Get all the formBASTPBPPList
        restFormBASTPBPPMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(formBASTPBPP.getId())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].active").value(hasItem(DEFAULT_ACTIVE.booleanValue())))
            .andExpect(jsonPath("$.[*].remarks").value(hasItem(DEFAULT_REMARKS)))
            .andExpect(jsonPath("$.[*].contents").value(hasItem(DEFAULT_CONTENTS)))
            .andExpect(jsonPath("$.[*].documentTitle").value(hasItem(DEFAULT_DOCUMENT_TITLE)))
            .andExpect(jsonPath("$.[*].kelurahanDesa").value(hasItem(DEFAULT_KELURAHAN_DESA)))
            .andExpect(jsonPath("$.[*].kecamatan").value(hasItem(DEFAULT_KECAMATAN)))
            .andExpect(jsonPath("$.[*].kabupatenKota").value(hasItem(DEFAULT_KABUPATEN_KOTA)))
            .andExpect(jsonPath("$.[*].provinsi").value(hasItem(DEFAULT_PROVINSI)))
            .andExpect(jsonPath("$.[*].pbpTidakDitemukan1").value(hasItem(DEFAULT_PBP_TIDAK_DITEMUKAN_1)))
            .andExpect(jsonPath("$.[*].sebabPenggantian1").value(hasItem(DEFAULT_SEBAB_PENGGANTIAN_1)))
            .andExpect(jsonPath("$.[*].pbpPengganti1").value(hasItem(DEFAULT_PBP_PENGGANTI_1)))
            .andExpect(jsonPath("$.[*].alamatPbpPengganti1").value(hasItem(DEFAULT_ALAMAT_PBP_PENGGANTI_1)))
            .andExpect(jsonPath("$.[*].pbpTidakDitemukan2").value(hasItem(DEFAULT_PBP_TIDAK_DITEMUKAN_2)))
            .andExpect(jsonPath("$.[*].sebabPenggantian2").value(hasItem(DEFAULT_SEBAB_PENGGANTIAN_2)))
            .andExpect(jsonPath("$.[*].pbpPengganti2").value(hasItem(DEFAULT_PBP_PENGGANTI_2)))
            .andExpect(jsonPath("$.[*].alamatPbpPengganti2").value(hasItem(DEFAULT_ALAMAT_PBP_PENGGANTI_2)))
            .andExpect(jsonPath("$.[*].pbpTidakDitemukan3").value(hasItem(DEFAULT_PBP_TIDAK_DITEMUKAN_3)))
            .andExpect(jsonPath("$.[*].sebabPenggantian3").value(hasItem(DEFAULT_SEBAB_PENGGANTIAN_3)))
            .andExpect(jsonPath("$.[*].pbpPengganti3").value(hasItem(DEFAULT_PBP_PENGGANTI_3)))
            .andExpect(jsonPath("$.[*].alamatPbpPengganti3").value(hasItem(DEFAULT_ALAMAT_PBP_PENGGANTI_3)))
            .andExpect(jsonPath("$.[*].pbpTidakDitemukan4").value(hasItem(DEFAULT_PBP_TIDAK_DITEMUKAN_4)))
            .andExpect(jsonPath("$.[*].sebabPenggantian4").value(hasItem(DEFAULT_SEBAB_PENGGANTIAN_4)))
            .andExpect(jsonPath("$.[*].pbpPengganti4").value(hasItem(DEFAULT_PBP_PENGGANTI_4)))
            .andExpect(jsonPath("$.[*].alamatPbpPengganti4").value(hasItem(DEFAULT_ALAMAT_PBP_PENGGANTI_4)))
            .andExpect(jsonPath("$.[*].pbpTidakDitemukan5").value(hasItem(DEFAULT_PBP_TIDAK_DITEMUKAN_5)))
            .andExpect(jsonPath("$.[*].sebabPenggantian5").value(hasItem(DEFAULT_SEBAB_PENGGANTIAN_5)))
            .andExpect(jsonPath("$.[*].pbpPengganti5").value(hasItem(DEFAULT_PBP_PENGGANTI_5)))
            .andExpect(jsonPath("$.[*].alamatPbpPengganti5").value(hasItem(DEFAULT_ALAMAT_PBP_PENGGANTI_5)))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)))
            .andExpect(jsonPath("$.[*].lastModifiedDate").value(hasItem(DEFAULT_LAST_MODIFIED_DATE.toString())))
            .andExpect(jsonPath("$.[*].lastModifiedBy").value(hasItem(DEFAULT_LAST_MODIFIED_BY)));
    }

    @Test
    @Transactional
    void getFormBASTPBPP() throws Exception {
        // Initialize the database
        formBASTPBPP.setId(UUID.randomUUID().toString());
        formBASTPBPPRepository.saveAndFlush(formBASTPBPP);

        // Get the formBASTPBPP
        restFormBASTPBPPMockMvc
            .perform(get(ENTITY_API_URL_ID, formBASTPBPP.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(formBASTPBPP.getId()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.active").value(DEFAULT_ACTIVE.booleanValue()))
            .andExpect(jsonPath("$.remarks").value(DEFAULT_REMARKS))
            .andExpect(jsonPath("$.contents").value(DEFAULT_CONTENTS))
            .andExpect(jsonPath("$.documentTitle").value(DEFAULT_DOCUMENT_TITLE))
            .andExpect(jsonPath("$.kelurahanDesa").value(DEFAULT_KELURAHAN_DESA))
            .andExpect(jsonPath("$.kecamatan").value(DEFAULT_KECAMATAN))
            .andExpect(jsonPath("$.kabupatenKota").value(DEFAULT_KABUPATEN_KOTA))
            .andExpect(jsonPath("$.provinsi").value(DEFAULT_PROVINSI))
            .andExpect(jsonPath("$.pbpTidakDitemukan1").value(DEFAULT_PBP_TIDAK_DITEMUKAN_1))
            .andExpect(jsonPath("$.sebabPenggantian1").value(DEFAULT_SEBAB_PENGGANTIAN_1))
            .andExpect(jsonPath("$.pbpPengganti1").value(DEFAULT_PBP_PENGGANTI_1))
            .andExpect(jsonPath("$.alamatPbpPengganti1").value(DEFAULT_ALAMAT_PBP_PENGGANTI_1))
            .andExpect(jsonPath("$.pbpTidakDitemukan2").value(DEFAULT_PBP_TIDAK_DITEMUKAN_2))
            .andExpect(jsonPath("$.sebabPenggantian2").value(DEFAULT_SEBAB_PENGGANTIAN_2))
            .andExpect(jsonPath("$.pbpPengganti2").value(DEFAULT_PBP_PENGGANTI_2))
            .andExpect(jsonPath("$.alamatPbpPengganti2").value(DEFAULT_ALAMAT_PBP_PENGGANTI_2))
            .andExpect(jsonPath("$.pbpTidakDitemukan3").value(DEFAULT_PBP_TIDAK_DITEMUKAN_3))
            .andExpect(jsonPath("$.sebabPenggantian3").value(DEFAULT_SEBAB_PENGGANTIAN_3))
            .andExpect(jsonPath("$.pbpPengganti3").value(DEFAULT_PBP_PENGGANTI_3))
            .andExpect(jsonPath("$.alamatPbpPengganti3").value(DEFAULT_ALAMAT_PBP_PENGGANTI_3))
            .andExpect(jsonPath("$.pbpTidakDitemukan4").value(DEFAULT_PBP_TIDAK_DITEMUKAN_4))
            .andExpect(jsonPath("$.sebabPenggantian4").value(DEFAULT_SEBAB_PENGGANTIAN_4))
            .andExpect(jsonPath("$.pbpPengganti4").value(DEFAULT_PBP_PENGGANTI_4))
            .andExpect(jsonPath("$.alamatPbpPengganti4").value(DEFAULT_ALAMAT_PBP_PENGGANTI_4))
            .andExpect(jsonPath("$.pbpTidakDitemukan5").value(DEFAULT_PBP_TIDAK_DITEMUKAN_5))
            .andExpect(jsonPath("$.sebabPenggantian5").value(DEFAULT_SEBAB_PENGGANTIAN_5))
            .andExpect(jsonPath("$.pbpPengganti5").value(DEFAULT_PBP_PENGGANTI_5))
            .andExpect(jsonPath("$.alamatPbpPengganti5").value(DEFAULT_ALAMAT_PBP_PENGGANTI_5))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY))
            .andExpect(jsonPath("$.lastModifiedDate").value(DEFAULT_LAST_MODIFIED_DATE.toString()))
            .andExpect(jsonPath("$.lastModifiedBy").value(DEFAULT_LAST_MODIFIED_BY));
    }

    @Test
    @Transactional
    void getNonExistingFormBASTPBPP() throws Exception {
        // Get the formBASTPBPP
        restFormBASTPBPPMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingFormBASTPBPP() throws Exception {
        // Initialize the database
        formBASTPBPP.setId(UUID.randomUUID().toString());
        formBASTPBPPRepository.saveAndFlush(formBASTPBPP);

        int databaseSizeBeforeUpdate = formBASTPBPPRepository.findAll().size();

        // Update the formBASTPBPP
        FormBASTPBPP updatedFormBASTPBPP = formBASTPBPPRepository.findById(formBASTPBPP.getId()).get();
        // Disconnect from session so that the updates on updatedFormBASTPBPP are not directly saved in db
        em.detach(updatedFormBASTPBPP);
        updatedFormBASTPBPP
            .status(UPDATED_STATUS)
            .active(UPDATED_ACTIVE)
            .remarks(UPDATED_REMARKS)
            .contents(UPDATED_CONTENTS)
            .documentTitle(UPDATED_DOCUMENT_TITLE)
            .kelurahanDesa(UPDATED_KELURAHAN_DESA)
            .kecamatan(UPDATED_KECAMATAN)
            .kabupatenKota(UPDATED_KABUPATEN_KOTA)
            .provinsi(UPDATED_PROVINSI)
            .pbpTidakDitemukan1(UPDATED_PBP_TIDAK_DITEMUKAN_1)
            .sebabPenggantian1(UPDATED_SEBAB_PENGGANTIAN_1)
            .pbpPengganti1(UPDATED_PBP_PENGGANTI_1)
            .alamatPbpPengganti1(UPDATED_ALAMAT_PBP_PENGGANTI_1)
            .pbpTidakDitemukan2(UPDATED_PBP_TIDAK_DITEMUKAN_2)
            .sebabPenggantian2(UPDATED_SEBAB_PENGGANTIAN_2)
            .pbpPengganti2(UPDATED_PBP_PENGGANTI_2)
            .alamatPbpPengganti2(UPDATED_ALAMAT_PBP_PENGGANTI_2)
            .pbpTidakDitemukan3(UPDATED_PBP_TIDAK_DITEMUKAN_3)
            .sebabPenggantian3(UPDATED_SEBAB_PENGGANTIAN_3)
            .pbpPengganti3(UPDATED_PBP_PENGGANTI_3)
            .alamatPbpPengganti3(UPDATED_ALAMAT_PBP_PENGGANTI_3)
            .pbpTidakDitemukan4(UPDATED_PBP_TIDAK_DITEMUKAN_4)
            .sebabPenggantian4(UPDATED_SEBAB_PENGGANTIAN_4)
            .pbpPengganti4(UPDATED_PBP_PENGGANTI_4)
            .alamatPbpPengganti4(UPDATED_ALAMAT_PBP_PENGGANTI_4)
            .pbpTidakDitemukan5(UPDATED_PBP_TIDAK_DITEMUKAN_5)
            .sebabPenggantian5(UPDATED_SEBAB_PENGGANTIAN_5)
            .pbpPengganti5(UPDATED_PBP_PENGGANTI_5)
            .alamatPbpPengganti5(UPDATED_ALAMAT_PBP_PENGGANTI_5);

        restFormBASTPBPPMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedFormBASTPBPP.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedFormBASTPBPP))
            )
            .andExpect(status().isOk());

        // Validate the FormBASTPBPP in the database
        List<FormBASTPBPP> formBASTPBPPList = formBASTPBPPRepository.findAll();
        assertThat(formBASTPBPPList).hasSize(databaseSizeBeforeUpdate);
        FormBASTPBPP testFormBASTPBPP = formBASTPBPPList.get(formBASTPBPPList.size() - 1);
        assertThat(testFormBASTPBPP.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testFormBASTPBPP.getActive()).isEqualTo(UPDATED_ACTIVE);
        assertThat(testFormBASTPBPP.getRemarks()).isEqualTo(UPDATED_REMARKS);
        assertThat(testFormBASTPBPP.getContents()).isEqualTo(UPDATED_CONTENTS);
        assertThat(testFormBASTPBPP.getDocumentTitle()).isEqualTo(UPDATED_DOCUMENT_TITLE);
        assertThat(testFormBASTPBPP.getKelurahanDesa()).isEqualTo(UPDATED_KELURAHAN_DESA);
        assertThat(testFormBASTPBPP.getKecamatan()).isEqualTo(UPDATED_KECAMATAN);
        assertThat(testFormBASTPBPP.getKabupatenKota()).isEqualTo(UPDATED_KABUPATEN_KOTA);
        assertThat(testFormBASTPBPP.getProvinsi()).isEqualTo(UPDATED_PROVINSI);
        assertThat(testFormBASTPBPP.getPbpTidakDitemukan1()).isEqualTo(UPDATED_PBP_TIDAK_DITEMUKAN_1);
        assertThat(testFormBASTPBPP.getSebabPenggantian1()).isEqualTo(UPDATED_SEBAB_PENGGANTIAN_1);
        assertThat(testFormBASTPBPP.getPbpPengganti1()).isEqualTo(UPDATED_PBP_PENGGANTI_1);
        assertThat(testFormBASTPBPP.getAlamatPbpPengganti1()).isEqualTo(UPDATED_ALAMAT_PBP_PENGGANTI_1);
        assertThat(testFormBASTPBPP.getPbpTidakDitemukan2()).isEqualTo(UPDATED_PBP_TIDAK_DITEMUKAN_2);
        assertThat(testFormBASTPBPP.getSebabPenggantian2()).isEqualTo(UPDATED_SEBAB_PENGGANTIAN_2);
        assertThat(testFormBASTPBPP.getPbpPengganti2()).isEqualTo(UPDATED_PBP_PENGGANTI_2);
        assertThat(testFormBASTPBPP.getAlamatPbpPengganti2()).isEqualTo(UPDATED_ALAMAT_PBP_PENGGANTI_2);
        assertThat(testFormBASTPBPP.getPbpTidakDitemukan3()).isEqualTo(UPDATED_PBP_TIDAK_DITEMUKAN_3);
        assertThat(testFormBASTPBPP.getSebabPenggantian3()).isEqualTo(UPDATED_SEBAB_PENGGANTIAN_3);
        assertThat(testFormBASTPBPP.getPbpPengganti3()).isEqualTo(UPDATED_PBP_PENGGANTI_3);
        assertThat(testFormBASTPBPP.getAlamatPbpPengganti3()).isEqualTo(UPDATED_ALAMAT_PBP_PENGGANTI_3);
        assertThat(testFormBASTPBPP.getPbpTidakDitemukan4()).isEqualTo(UPDATED_PBP_TIDAK_DITEMUKAN_4);
        assertThat(testFormBASTPBPP.getSebabPenggantian4()).isEqualTo(UPDATED_SEBAB_PENGGANTIAN_4);
        assertThat(testFormBASTPBPP.getPbpPengganti4()).isEqualTo(UPDATED_PBP_PENGGANTI_4);
        assertThat(testFormBASTPBPP.getAlamatPbpPengganti4()).isEqualTo(UPDATED_ALAMAT_PBP_PENGGANTI_4);
        assertThat(testFormBASTPBPP.getPbpTidakDitemukan5()).isEqualTo(UPDATED_PBP_TIDAK_DITEMUKAN_5);
        assertThat(testFormBASTPBPP.getSebabPenggantian5()).isEqualTo(UPDATED_SEBAB_PENGGANTIAN_5);
        assertThat(testFormBASTPBPP.getPbpPengganti5()).isEqualTo(UPDATED_PBP_PENGGANTI_5);
        assertThat(testFormBASTPBPP.getAlamatPbpPengganti5()).isEqualTo(UPDATED_ALAMAT_PBP_PENGGANTI_5);
        assertThat(testFormBASTPBPP.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testFormBASTPBPP.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testFormBASTPBPP.getLastModifiedDate()).isEqualTo(UPDATED_LAST_MODIFIED_DATE);
        assertThat(testFormBASTPBPP.getLastModifiedBy()).isEqualTo(UPDATED_LAST_MODIFIED_BY);
    }

    @Test
    @Transactional
    void putNonExistingFormBASTPBPP() throws Exception {
        int databaseSizeBeforeUpdate = formBASTPBPPRepository.findAll().size();
        formBASTPBPP.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFormBASTPBPPMockMvc
            .perform(
                put(ENTITY_API_URL_ID, formBASTPBPP.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(formBASTPBPP))
            )
            .andExpect(status().isBadRequest());

        // Validate the FormBASTPBPP in the database
        List<FormBASTPBPP> formBASTPBPPList = formBASTPBPPRepository.findAll();
        assertThat(formBASTPBPPList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchFormBASTPBPP() throws Exception {
        int databaseSizeBeforeUpdate = formBASTPBPPRepository.findAll().size();
        formBASTPBPP.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restFormBASTPBPPMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(formBASTPBPP))
            )
            .andExpect(status().isBadRequest());

        // Validate the FormBASTPBPP in the database
        List<FormBASTPBPP> formBASTPBPPList = formBASTPBPPRepository.findAll();
        assertThat(formBASTPBPPList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamFormBASTPBPP() throws Exception {
        int databaseSizeBeforeUpdate = formBASTPBPPRepository.findAll().size();
        formBASTPBPP.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restFormBASTPBPPMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(formBASTPBPP)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the FormBASTPBPP in the database
        List<FormBASTPBPP> formBASTPBPPList = formBASTPBPPRepository.findAll();
        assertThat(formBASTPBPPList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateFormBASTPBPPWithPatch() throws Exception {
        // Initialize the database
        formBASTPBPP.setId(UUID.randomUUID().toString());
        formBASTPBPPRepository.saveAndFlush(formBASTPBPP);

        int databaseSizeBeforeUpdate = formBASTPBPPRepository.findAll().size();

        // Update the formBASTPBPP using partial update
        FormBASTPBPP partialUpdatedFormBASTPBPP = new FormBASTPBPP();
        partialUpdatedFormBASTPBPP.setId(formBASTPBPP.getId());

        partialUpdatedFormBASTPBPP
            .status(UPDATED_STATUS)
            .contents(UPDATED_CONTENTS)
            .documentTitle(UPDATED_DOCUMENT_TITLE)
            .provinsi(UPDATED_PROVINSI)
            .pbpTidakDitemukan1(UPDATED_PBP_TIDAK_DITEMUKAN_1)
            .sebabPenggantian1(UPDATED_SEBAB_PENGGANTIAN_1)
            .alamatPbpPengganti1(UPDATED_ALAMAT_PBP_PENGGANTI_1)
            .pbpPengganti2(UPDATED_PBP_PENGGANTI_2)
            .alamatPbpPengganti2(UPDATED_ALAMAT_PBP_PENGGANTI_2)
            .alamatPbpPengganti3(UPDATED_ALAMAT_PBP_PENGGANTI_3)
            .sebabPenggantian5(UPDATED_SEBAB_PENGGANTIAN_5)
            .pbpPengganti5(UPDATED_PBP_PENGGANTI_5)
            .alamatPbpPengganti5(UPDATED_ALAMAT_PBP_PENGGANTI_5)
            .createdBy(UPDATED_CREATED_BY)
            .lastModifiedBy(UPDATED_LAST_MODIFIED_BY);

        restFormBASTPBPPMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedFormBASTPBPP.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedFormBASTPBPP))
            )
            .andExpect(status().isOk());

        // Validate the FormBASTPBPP in the database
        List<FormBASTPBPP> formBASTPBPPList = formBASTPBPPRepository.findAll();
        assertThat(formBASTPBPPList).hasSize(databaseSizeBeforeUpdate);
        FormBASTPBPP testFormBASTPBPP = formBASTPBPPList.get(formBASTPBPPList.size() - 1);
        assertThat(testFormBASTPBPP.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testFormBASTPBPP.getActive()).isEqualTo(DEFAULT_ACTIVE);
        assertThat(testFormBASTPBPP.getRemarks()).isEqualTo(DEFAULT_REMARKS);
        assertThat(testFormBASTPBPP.getContents()).isEqualTo(UPDATED_CONTENTS);
        assertThat(testFormBASTPBPP.getDocumentTitle()).isEqualTo(UPDATED_DOCUMENT_TITLE);
        assertThat(testFormBASTPBPP.getKelurahanDesa()).isEqualTo(DEFAULT_KELURAHAN_DESA);
        assertThat(testFormBASTPBPP.getKecamatan()).isEqualTo(DEFAULT_KECAMATAN);
        assertThat(testFormBASTPBPP.getKabupatenKota()).isEqualTo(DEFAULT_KABUPATEN_KOTA);
        assertThat(testFormBASTPBPP.getProvinsi()).isEqualTo(UPDATED_PROVINSI);
        assertThat(testFormBASTPBPP.getPbpTidakDitemukan1()).isEqualTo(UPDATED_PBP_TIDAK_DITEMUKAN_1);
        assertThat(testFormBASTPBPP.getSebabPenggantian1()).isEqualTo(UPDATED_SEBAB_PENGGANTIAN_1);
        assertThat(testFormBASTPBPP.getPbpPengganti1()).isEqualTo(DEFAULT_PBP_PENGGANTI_1);
        assertThat(testFormBASTPBPP.getAlamatPbpPengganti1()).isEqualTo(UPDATED_ALAMAT_PBP_PENGGANTI_1);
        assertThat(testFormBASTPBPP.getPbpTidakDitemukan2()).isEqualTo(DEFAULT_PBP_TIDAK_DITEMUKAN_2);
        assertThat(testFormBASTPBPP.getSebabPenggantian2()).isEqualTo(DEFAULT_SEBAB_PENGGANTIAN_2);
        assertThat(testFormBASTPBPP.getPbpPengganti2()).isEqualTo(UPDATED_PBP_PENGGANTI_2);
        assertThat(testFormBASTPBPP.getAlamatPbpPengganti2()).isEqualTo(UPDATED_ALAMAT_PBP_PENGGANTI_2);
        assertThat(testFormBASTPBPP.getPbpTidakDitemukan3()).isEqualTo(DEFAULT_PBP_TIDAK_DITEMUKAN_3);
        assertThat(testFormBASTPBPP.getSebabPenggantian3()).isEqualTo(DEFAULT_SEBAB_PENGGANTIAN_3);
        assertThat(testFormBASTPBPP.getPbpPengganti3()).isEqualTo(DEFAULT_PBP_PENGGANTI_3);
        assertThat(testFormBASTPBPP.getAlamatPbpPengganti3()).isEqualTo(UPDATED_ALAMAT_PBP_PENGGANTI_3);
        assertThat(testFormBASTPBPP.getPbpTidakDitemukan4()).isEqualTo(DEFAULT_PBP_TIDAK_DITEMUKAN_4);
        assertThat(testFormBASTPBPP.getSebabPenggantian4()).isEqualTo(DEFAULT_SEBAB_PENGGANTIAN_4);
        assertThat(testFormBASTPBPP.getPbpPengganti4()).isEqualTo(DEFAULT_PBP_PENGGANTI_4);
        assertThat(testFormBASTPBPP.getAlamatPbpPengganti4()).isEqualTo(DEFAULT_ALAMAT_PBP_PENGGANTI_4);
        assertThat(testFormBASTPBPP.getPbpTidakDitemukan5()).isEqualTo(DEFAULT_PBP_TIDAK_DITEMUKAN_5);
        assertThat(testFormBASTPBPP.getSebabPenggantian5()).isEqualTo(UPDATED_SEBAB_PENGGANTIAN_5);
        assertThat(testFormBASTPBPP.getPbpPengganti5()).isEqualTo(UPDATED_PBP_PENGGANTI_5);
        assertThat(testFormBASTPBPP.getAlamatPbpPengganti5()).isEqualTo(UPDATED_ALAMAT_PBP_PENGGANTI_5);
        assertThat(testFormBASTPBPP.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testFormBASTPBPP.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testFormBASTPBPP.getLastModifiedDate()).isEqualTo(DEFAULT_LAST_MODIFIED_DATE);
        assertThat(testFormBASTPBPP.getLastModifiedBy()).isEqualTo(UPDATED_LAST_MODIFIED_BY);
    }

    @Test
    @Transactional
    void fullUpdateFormBASTPBPPWithPatch() throws Exception {
        // Initialize the database
        formBASTPBPP.setId(UUID.randomUUID().toString());
        formBASTPBPPRepository.saveAndFlush(formBASTPBPP);

        int databaseSizeBeforeUpdate = formBASTPBPPRepository.findAll().size();

        // Update the formBASTPBPP using partial update
        FormBASTPBPP partialUpdatedFormBASTPBPP = new FormBASTPBPP();
        partialUpdatedFormBASTPBPP.setId(formBASTPBPP.getId());

        partialUpdatedFormBASTPBPP
            .status(UPDATED_STATUS)
            .active(UPDATED_ACTIVE)
            .remarks(UPDATED_REMARKS)
            .contents(UPDATED_CONTENTS)
            .documentTitle(UPDATED_DOCUMENT_TITLE)
            .kelurahanDesa(UPDATED_KELURAHAN_DESA)
            .kecamatan(UPDATED_KECAMATAN)
            .kabupatenKota(UPDATED_KABUPATEN_KOTA)
            .provinsi(UPDATED_PROVINSI)
            .pbpTidakDitemukan1(UPDATED_PBP_TIDAK_DITEMUKAN_1)
            .sebabPenggantian1(UPDATED_SEBAB_PENGGANTIAN_1)
            .pbpPengganti1(UPDATED_PBP_PENGGANTI_1)
            .alamatPbpPengganti1(UPDATED_ALAMAT_PBP_PENGGANTI_1)
            .pbpTidakDitemukan2(UPDATED_PBP_TIDAK_DITEMUKAN_2)
            .sebabPenggantian2(UPDATED_SEBAB_PENGGANTIAN_2)
            .pbpPengganti2(UPDATED_PBP_PENGGANTI_2)
            .alamatPbpPengganti2(UPDATED_ALAMAT_PBP_PENGGANTI_2)
            .pbpTidakDitemukan3(UPDATED_PBP_TIDAK_DITEMUKAN_3)
            .sebabPenggantian3(UPDATED_SEBAB_PENGGANTIAN_3)
            .pbpPengganti3(UPDATED_PBP_PENGGANTI_3)
            .alamatPbpPengganti3(UPDATED_ALAMAT_PBP_PENGGANTI_3)
            .pbpTidakDitemukan4(UPDATED_PBP_TIDAK_DITEMUKAN_4)
            .sebabPenggantian4(UPDATED_SEBAB_PENGGANTIAN_4)
            .pbpPengganti4(UPDATED_PBP_PENGGANTI_4)
            .alamatPbpPengganti4(UPDATED_ALAMAT_PBP_PENGGANTI_4)
            .pbpTidakDitemukan5(UPDATED_PBP_TIDAK_DITEMUKAN_5)
            .sebabPenggantian5(UPDATED_SEBAB_PENGGANTIAN_5)
            .pbpPengganti5(UPDATED_PBP_PENGGANTI_5)
            .alamatPbpPengganti5(UPDATED_ALAMAT_PBP_PENGGANTI_5);

        restFormBASTPBPPMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedFormBASTPBPP.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedFormBASTPBPP))
            )
            .andExpect(status().isOk());

        // Validate the FormBASTPBPP in the database
        List<FormBASTPBPP> formBASTPBPPList = formBASTPBPPRepository.findAll();
        assertThat(formBASTPBPPList).hasSize(databaseSizeBeforeUpdate);
        FormBASTPBPP testFormBASTPBPP = formBASTPBPPList.get(formBASTPBPPList.size() - 1);
        assertThat(testFormBASTPBPP.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testFormBASTPBPP.getActive()).isEqualTo(UPDATED_ACTIVE);
        assertThat(testFormBASTPBPP.getRemarks()).isEqualTo(UPDATED_REMARKS);
        assertThat(testFormBASTPBPP.getContents()).isEqualTo(UPDATED_CONTENTS);
        assertThat(testFormBASTPBPP.getDocumentTitle()).isEqualTo(UPDATED_DOCUMENT_TITLE);
        assertThat(testFormBASTPBPP.getKelurahanDesa()).isEqualTo(UPDATED_KELURAHAN_DESA);
        assertThat(testFormBASTPBPP.getKecamatan()).isEqualTo(UPDATED_KECAMATAN);
        assertThat(testFormBASTPBPP.getKabupatenKota()).isEqualTo(UPDATED_KABUPATEN_KOTA);
        assertThat(testFormBASTPBPP.getProvinsi()).isEqualTo(UPDATED_PROVINSI);
        assertThat(testFormBASTPBPP.getPbpTidakDitemukan1()).isEqualTo(UPDATED_PBP_TIDAK_DITEMUKAN_1);
        assertThat(testFormBASTPBPP.getSebabPenggantian1()).isEqualTo(UPDATED_SEBAB_PENGGANTIAN_1);
        assertThat(testFormBASTPBPP.getPbpPengganti1()).isEqualTo(UPDATED_PBP_PENGGANTI_1);
        assertThat(testFormBASTPBPP.getAlamatPbpPengganti1()).isEqualTo(UPDATED_ALAMAT_PBP_PENGGANTI_1);
        assertThat(testFormBASTPBPP.getPbpTidakDitemukan2()).isEqualTo(UPDATED_PBP_TIDAK_DITEMUKAN_2);
        assertThat(testFormBASTPBPP.getSebabPenggantian2()).isEqualTo(UPDATED_SEBAB_PENGGANTIAN_2);
        assertThat(testFormBASTPBPP.getPbpPengganti2()).isEqualTo(UPDATED_PBP_PENGGANTI_2);
        assertThat(testFormBASTPBPP.getAlamatPbpPengganti2()).isEqualTo(UPDATED_ALAMAT_PBP_PENGGANTI_2);
        assertThat(testFormBASTPBPP.getPbpTidakDitemukan3()).isEqualTo(UPDATED_PBP_TIDAK_DITEMUKAN_3);
        assertThat(testFormBASTPBPP.getSebabPenggantian3()).isEqualTo(UPDATED_SEBAB_PENGGANTIAN_3);
        assertThat(testFormBASTPBPP.getPbpPengganti3()).isEqualTo(UPDATED_PBP_PENGGANTI_3);
        assertThat(testFormBASTPBPP.getAlamatPbpPengganti3()).isEqualTo(UPDATED_ALAMAT_PBP_PENGGANTI_3);
        assertThat(testFormBASTPBPP.getPbpTidakDitemukan4()).isEqualTo(UPDATED_PBP_TIDAK_DITEMUKAN_4);
        assertThat(testFormBASTPBPP.getSebabPenggantian4()).isEqualTo(UPDATED_SEBAB_PENGGANTIAN_4);
        assertThat(testFormBASTPBPP.getPbpPengganti4()).isEqualTo(UPDATED_PBP_PENGGANTI_4);
        assertThat(testFormBASTPBPP.getAlamatPbpPengganti4()).isEqualTo(UPDATED_ALAMAT_PBP_PENGGANTI_4);
        assertThat(testFormBASTPBPP.getPbpTidakDitemukan5()).isEqualTo(UPDATED_PBP_TIDAK_DITEMUKAN_5);
        assertThat(testFormBASTPBPP.getSebabPenggantian5()).isEqualTo(UPDATED_SEBAB_PENGGANTIAN_5);
        assertThat(testFormBASTPBPP.getPbpPengganti5()).isEqualTo(UPDATED_PBP_PENGGANTI_5);
        assertThat(testFormBASTPBPP.getAlamatPbpPengganti5()).isEqualTo(UPDATED_ALAMAT_PBP_PENGGANTI_5);
        assertThat(testFormBASTPBPP.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testFormBASTPBPP.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testFormBASTPBPP.getLastModifiedDate()).isEqualTo(UPDATED_LAST_MODIFIED_DATE);
        assertThat(testFormBASTPBPP.getLastModifiedBy()).isEqualTo(UPDATED_LAST_MODIFIED_BY);
    }

    @Test
    @Transactional
    void patchNonExistingFormBASTPBPP() throws Exception {
        int databaseSizeBeforeUpdate = formBASTPBPPRepository.findAll().size();
        formBASTPBPP.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFormBASTPBPPMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, formBASTPBPP.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(formBASTPBPP))
            )
            .andExpect(status().isBadRequest());

        // Validate the FormBASTPBPP in the database
        List<FormBASTPBPP> formBASTPBPPList = formBASTPBPPRepository.findAll();
        assertThat(formBASTPBPPList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchFormBASTPBPP() throws Exception {
        int databaseSizeBeforeUpdate = formBASTPBPPRepository.findAll().size();
        formBASTPBPP.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restFormBASTPBPPMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(formBASTPBPP))
            )
            .andExpect(status().isBadRequest());

        // Validate the FormBASTPBPP in the database
        List<FormBASTPBPP> formBASTPBPPList = formBASTPBPPRepository.findAll();
        assertThat(formBASTPBPPList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamFormBASTPBPP() throws Exception {
        int databaseSizeBeforeUpdate = formBASTPBPPRepository.findAll().size();
        formBASTPBPP.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restFormBASTPBPPMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(formBASTPBPP))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the FormBASTPBPP in the database
        List<FormBASTPBPP> formBASTPBPPList = formBASTPBPPRepository.findAll();
        assertThat(formBASTPBPPList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteFormBASTPBPP() throws Exception {
        // Initialize the database
        formBASTPBPP.setId(UUID.randomUUID().toString());
        formBASTPBPPRepository.saveAndFlush(formBASTPBPP);

        int databaseSizeBeforeDelete = formBASTPBPPRepository.findAll().size();

        // Delete the formBASTPBPP
        restFormBASTPBPPMockMvc
            .perform(delete(ENTITY_API_URL_ID, formBASTPBPP.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<FormBASTPBPP> formBASTPBPPList = formBASTPBPPRepository.findAll();
        assertThat(formBASTPBPPList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
