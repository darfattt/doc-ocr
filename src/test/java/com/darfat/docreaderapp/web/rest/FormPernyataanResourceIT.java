package com.darfat.docreaderapp.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.darfat.docreaderapp.IntegrationTest;
import com.darfat.docreaderapp.domain.FormPernyataan;
import com.darfat.docreaderapp.repository.FormPernyataanRepository;
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
 * Integration tests for the {@link FormPernyataanResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class FormPernyataanResourceIT {

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

    private static final String DEFAULT_OFFICER_NAME = "AAAAAAAAAA";
    private static final String UPDATED_OFFICER_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_OFFICER_PHONE_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_OFFICER_PHONE_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_OFFICER_POSITION = "AAAAAAAAAA";
    private static final String UPDATED_OFFICER_POSITION = "BBBBBBBBBB";

    private static final String DEFAULT_OFFICER_DEPARTMENT = "AAAAAAAAAA";
    private static final String UPDATED_OFFICER_DEPARTMENT = "BBBBBBBBBB";

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

    private static final String DEFAULT_ALAMAT_PBP_TIDAK_DITEMUKAN_1 = "AAAAAAAAAA";
    private static final String UPDATED_ALAMAT_PBP_TIDAK_DITEMUKAN_1 = "BBBBBBBBBB";

    private static final String DEFAULT_PBP_PENGGANTI_1 = "AAAAAAAAAA";
    private static final String UPDATED_PBP_PENGGANTI_1 = "BBBBBBBBBB";

    private static final String DEFAULT_ALAMAT_PBP_PENGGANTI_1 = "AAAAAAAAAA";
    private static final String UPDATED_ALAMAT_PBP_PENGGANTI_1 = "BBBBBBBBBB";

    private static final String DEFAULT_PBP_TIDAK_DITEMUKAN_2 = "AAAAAAAAAA";
    private static final String UPDATED_PBP_TIDAK_DITEMUKAN_2 = "BBBBBBBBBB";

    private static final String DEFAULT_ALAMAT_PBP_TIDAK_DITEMUKAN_2 = "AAAAAAAAAA";
    private static final String UPDATED_ALAMAT_PBP_TIDAK_DITEMUKAN_2 = "BBBBBBBBBB";

    private static final String DEFAULT_PBP_PENGGANTI_2 = "AAAAAAAAAA";
    private static final String UPDATED_PBP_PENGGANTI_2 = "BBBBBBBBBB";

    private static final String DEFAULT_ALAMAT_PBP_PENGGANTI_2 = "AAAAAAAAAA";
    private static final String UPDATED_ALAMAT_PBP_PENGGANTI_2 = "BBBBBBBBBB";

    private static final String DEFAULT_PBP_TIDAK_DITEMUKAN_3 = "AAAAAAAAAA";
    private static final String UPDATED_PBP_TIDAK_DITEMUKAN_3 = "BBBBBBBBBB";

    private static final String DEFAULT_ALAMAT_PBP_TIDAK_DITEMUKAN_3 = "AAAAAAAAAA";
    private static final String UPDATED_ALAMAT_PBP_TIDAK_DITEMUKAN_3 = "BBBBBBBBBB";

    private static final String DEFAULT_PBP_PENGGANTI_3 = "AAAAAAAAAA";
    private static final String UPDATED_PBP_PENGGANTI_3 = "BBBBBBBBBB";

    private static final String DEFAULT_ALAMAT_PBP_PENGGANTI_3 = "AAAAAAAAAA";
    private static final String UPDATED_ALAMAT_PBP_PENGGANTI_3 = "BBBBBBBBBB";

    private static final String DEFAULT_PBP_TIDAK_DITEMUKAN_4 = "AAAAAAAAAA";
    private static final String UPDATED_PBP_TIDAK_DITEMUKAN_4 = "BBBBBBBBBB";

    private static final String DEFAULT_ALAMAT_PBP_TIDAK_DITEMUKAN_4 = "AAAAAAAAAA";
    private static final String UPDATED_ALAMAT_PBP_TIDAK_DITEMUKAN_4 = "BBBBBBBBBB";

    private static final String DEFAULT_PBP_PENGGANTI_4 = "AAAAAAAAAA";
    private static final String UPDATED_PBP_PENGGANTI_4 = "BBBBBBBBBB";

    private static final String DEFAULT_ALAMAT_PBP_PENGGANTI_4 = "AAAAAAAAAA";
    private static final String UPDATED_ALAMAT_PBP_PENGGANTI_4 = "BBBBBBBBBB";

    private static final String DEFAULT_PBP_TIDAK_DITEMUKAN_5 = "AAAAAAAAAA";
    private static final String UPDATED_PBP_TIDAK_DITEMUKAN_5 = "BBBBBBBBBB";

    private static final String DEFAULT_ALAMAT_PBP_TIDAK_DITEMUKAN_5 = "AAAAAAAAAA";
    private static final String UPDATED_ALAMAT_PBP_TIDAK_DITEMUKAN_5 = "BBBBBBBBBB";

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

    private static final String ENTITY_API_URL = "/api/form-pernyataans";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private FormPernyataanRepository formPernyataanRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restFormPernyataanMockMvc;

    private FormPernyataan formPernyataan;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FormPernyataan createEntity(EntityManager em) {
        FormPernyataan formPernyataan = new FormPernyataan()
            .status(DEFAULT_STATUS)
            .active(DEFAULT_ACTIVE)
            .remarks(DEFAULT_REMARKS)
            .contents(DEFAULT_CONTENTS)
            .documentTitle(DEFAULT_DOCUMENT_TITLE)
            .officerName(DEFAULT_OFFICER_NAME)
            .officerPhoneNumber(DEFAULT_OFFICER_PHONE_NUMBER)
            .officerPosition(DEFAULT_OFFICER_POSITION)
            .officerDepartment(DEFAULT_OFFICER_DEPARTMENT)
            .kelurahanDesa(DEFAULT_KELURAHAN_DESA)
            .kecamatan(DEFAULT_KECAMATAN)
            .kabupatenKota(DEFAULT_KABUPATEN_KOTA)
            .provinsi(DEFAULT_PROVINSI)
            .pbpTidakDitemukan1(DEFAULT_PBP_TIDAK_DITEMUKAN_1)
            .alamatPbpTidakDitemukan1(DEFAULT_ALAMAT_PBP_TIDAK_DITEMUKAN_1)
            .pbpPengganti1(DEFAULT_PBP_PENGGANTI_1)
            .alamatPbpPengganti1(DEFAULT_ALAMAT_PBP_PENGGANTI_1)
            .pbpTidakDitemukan2(DEFAULT_PBP_TIDAK_DITEMUKAN_2)
            .alamatPbpTidakDitemukan2(DEFAULT_ALAMAT_PBP_TIDAK_DITEMUKAN_2)
            .pbpPengganti2(DEFAULT_PBP_PENGGANTI_2)
            .alamatPbpPengganti2(DEFAULT_ALAMAT_PBP_PENGGANTI_2)
            .pbpTidakDitemukan3(DEFAULT_PBP_TIDAK_DITEMUKAN_3)
            .alamatPbpTidakDitemukan3(DEFAULT_ALAMAT_PBP_TIDAK_DITEMUKAN_3)
            .pbpPengganti3(DEFAULT_PBP_PENGGANTI_3)
            .alamatPbpPengganti3(DEFAULT_ALAMAT_PBP_PENGGANTI_3)
            .pbpTidakDitemukan4(DEFAULT_PBP_TIDAK_DITEMUKAN_4)
            .alamatPbpTidakDitemukan4(DEFAULT_ALAMAT_PBP_TIDAK_DITEMUKAN_4)
            .pbpPengganti4(DEFAULT_PBP_PENGGANTI_4)
            .alamatPbpPengganti4(DEFAULT_ALAMAT_PBP_PENGGANTI_4)
            .pbpTidakDitemukan5(DEFAULT_PBP_TIDAK_DITEMUKAN_5)
            .alamatPbpTidakDitemukan5(DEFAULT_ALAMAT_PBP_TIDAK_DITEMUKAN_5)
            .pbpPengganti5(DEFAULT_PBP_PENGGANTI_5)
            .alamatPbpPengganti5(DEFAULT_ALAMAT_PBP_PENGGANTI_5);
        return formPernyataan;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FormPernyataan createUpdatedEntity(EntityManager em) {
        FormPernyataan formPernyataan = new FormPernyataan()
            .status(UPDATED_STATUS)
            .active(UPDATED_ACTIVE)
            .remarks(UPDATED_REMARKS)
            .contents(UPDATED_CONTENTS)
            .documentTitle(UPDATED_DOCUMENT_TITLE)
            .officerName(UPDATED_OFFICER_NAME)
            .officerPhoneNumber(UPDATED_OFFICER_PHONE_NUMBER)
            .officerPosition(UPDATED_OFFICER_POSITION)
            .officerDepartment(UPDATED_OFFICER_DEPARTMENT)
            .kelurahanDesa(UPDATED_KELURAHAN_DESA)
            .kecamatan(UPDATED_KECAMATAN)
            .kabupatenKota(UPDATED_KABUPATEN_KOTA)
            .provinsi(UPDATED_PROVINSI)
            .pbpTidakDitemukan1(UPDATED_PBP_TIDAK_DITEMUKAN_1)
            .alamatPbpTidakDitemukan1(UPDATED_ALAMAT_PBP_TIDAK_DITEMUKAN_1)
            .pbpPengganti1(UPDATED_PBP_PENGGANTI_1)
            .alamatPbpPengganti1(UPDATED_ALAMAT_PBP_PENGGANTI_1)
            .pbpTidakDitemukan2(UPDATED_PBP_TIDAK_DITEMUKAN_2)
            .alamatPbpTidakDitemukan2(UPDATED_ALAMAT_PBP_TIDAK_DITEMUKAN_2)
            .pbpPengganti2(UPDATED_PBP_PENGGANTI_2)
            .alamatPbpPengganti2(UPDATED_ALAMAT_PBP_PENGGANTI_2)
            .pbpTidakDitemukan3(UPDATED_PBP_TIDAK_DITEMUKAN_3)
            .alamatPbpTidakDitemukan3(UPDATED_ALAMAT_PBP_TIDAK_DITEMUKAN_3)
            .pbpPengganti3(UPDATED_PBP_PENGGANTI_3)
            .alamatPbpPengganti3(UPDATED_ALAMAT_PBP_PENGGANTI_3)
            .pbpTidakDitemukan4(UPDATED_PBP_TIDAK_DITEMUKAN_4)
            .alamatPbpTidakDitemukan4(UPDATED_ALAMAT_PBP_TIDAK_DITEMUKAN_4)
            .pbpPengganti4(UPDATED_PBP_PENGGANTI_4)
            .alamatPbpPengganti4(UPDATED_ALAMAT_PBP_PENGGANTI_4)
            .pbpTidakDitemukan5(UPDATED_PBP_TIDAK_DITEMUKAN_5)
            .alamatPbpTidakDitemukan5(UPDATED_ALAMAT_PBP_TIDAK_DITEMUKAN_5)
            .pbpPengganti5(UPDATED_PBP_PENGGANTI_5)
            .alamatPbpPengganti5(UPDATED_ALAMAT_PBP_PENGGANTI_5);
        return formPernyataan;
    }

    @BeforeEach
    public void initTest() {
        formPernyataan = createEntity(em);
    }

    @Test
    @Transactional
    void createFormPernyataan() throws Exception {
        int databaseSizeBeforeCreate = formPernyataanRepository.findAll().size();
        // Create the FormPernyataan
        restFormPernyataanMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(formPernyataan))
            )
            .andExpect(status().isCreated());

        // Validate the FormPernyataan in the database
        List<FormPernyataan> formPernyataanList = formPernyataanRepository.findAll();
        assertThat(formPernyataanList).hasSize(databaseSizeBeforeCreate + 1);
        FormPernyataan testFormPernyataan = formPernyataanList.get(formPernyataanList.size() - 1);
        assertThat(testFormPernyataan.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testFormPernyataan.getActive()).isEqualTo(DEFAULT_ACTIVE);
        assertThat(testFormPernyataan.getRemarks()).isEqualTo(DEFAULT_REMARKS);
        assertThat(testFormPernyataan.getContents()).isEqualTo(DEFAULT_CONTENTS);
        assertThat(testFormPernyataan.getDocumentTitle()).isEqualTo(DEFAULT_DOCUMENT_TITLE);
        assertThat(testFormPernyataan.getOfficerName()).isEqualTo(DEFAULT_OFFICER_NAME);
        assertThat(testFormPernyataan.getOfficerPhoneNumber()).isEqualTo(DEFAULT_OFFICER_PHONE_NUMBER);
        assertThat(testFormPernyataan.getOfficerPosition()).isEqualTo(DEFAULT_OFFICER_POSITION);
        assertThat(testFormPernyataan.getOfficerDepartment()).isEqualTo(DEFAULT_OFFICER_DEPARTMENT);
        assertThat(testFormPernyataan.getKelurahanDesa()).isEqualTo(DEFAULT_KELURAHAN_DESA);
        assertThat(testFormPernyataan.getKecamatan()).isEqualTo(DEFAULT_KECAMATAN);
        assertThat(testFormPernyataan.getKabupatenKota()).isEqualTo(DEFAULT_KABUPATEN_KOTA);
        assertThat(testFormPernyataan.getProvinsi()).isEqualTo(DEFAULT_PROVINSI);
        assertThat(testFormPernyataan.getPbpTidakDitemukan1()).isEqualTo(DEFAULT_PBP_TIDAK_DITEMUKAN_1);
        assertThat(testFormPernyataan.getAlamatPbpTidakDitemukan1()).isEqualTo(DEFAULT_ALAMAT_PBP_TIDAK_DITEMUKAN_1);
        assertThat(testFormPernyataan.getPbpPengganti1()).isEqualTo(DEFAULT_PBP_PENGGANTI_1);
        assertThat(testFormPernyataan.getAlamatPbpPengganti1()).isEqualTo(DEFAULT_ALAMAT_PBP_PENGGANTI_1);
        assertThat(testFormPernyataan.getPbpTidakDitemukan2()).isEqualTo(DEFAULT_PBP_TIDAK_DITEMUKAN_2);
        assertThat(testFormPernyataan.getAlamatPbpTidakDitemukan2()).isEqualTo(DEFAULT_ALAMAT_PBP_TIDAK_DITEMUKAN_2);
        assertThat(testFormPernyataan.getPbpPengganti2()).isEqualTo(DEFAULT_PBP_PENGGANTI_2);
        assertThat(testFormPernyataan.getAlamatPbpPengganti2()).isEqualTo(DEFAULT_ALAMAT_PBP_PENGGANTI_2);
        assertThat(testFormPernyataan.getPbpTidakDitemukan3()).isEqualTo(DEFAULT_PBP_TIDAK_DITEMUKAN_3);
        assertThat(testFormPernyataan.getAlamatPbpTidakDitemukan3()).isEqualTo(DEFAULT_ALAMAT_PBP_TIDAK_DITEMUKAN_3);
        assertThat(testFormPernyataan.getPbpPengganti3()).isEqualTo(DEFAULT_PBP_PENGGANTI_3);
        assertThat(testFormPernyataan.getAlamatPbpPengganti3()).isEqualTo(DEFAULT_ALAMAT_PBP_PENGGANTI_3);
        assertThat(testFormPernyataan.getPbpTidakDitemukan4()).isEqualTo(DEFAULT_PBP_TIDAK_DITEMUKAN_4);
        assertThat(testFormPernyataan.getAlamatPbpTidakDitemukan4()).isEqualTo(DEFAULT_ALAMAT_PBP_TIDAK_DITEMUKAN_4);
        assertThat(testFormPernyataan.getPbpPengganti4()).isEqualTo(DEFAULT_PBP_PENGGANTI_4);
        assertThat(testFormPernyataan.getAlamatPbpPengganti4()).isEqualTo(DEFAULT_ALAMAT_PBP_PENGGANTI_4);
        assertThat(testFormPernyataan.getPbpTidakDitemukan5()).isEqualTo(DEFAULT_PBP_TIDAK_DITEMUKAN_5);
        assertThat(testFormPernyataan.getAlamatPbpTidakDitemukan5()).isEqualTo(DEFAULT_ALAMAT_PBP_TIDAK_DITEMUKAN_5);
        assertThat(testFormPernyataan.getPbpPengganti5()).isEqualTo(DEFAULT_PBP_PENGGANTI_5);
        assertThat(testFormPernyataan.getAlamatPbpPengganti5()).isEqualTo(DEFAULT_ALAMAT_PBP_PENGGANTI_5);
        assertThat(testFormPernyataan.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testFormPernyataan.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testFormPernyataan.getLastModifiedDate()).isEqualTo(DEFAULT_LAST_MODIFIED_DATE);
        assertThat(testFormPernyataan.getLastModifiedBy()).isEqualTo(DEFAULT_LAST_MODIFIED_BY);
    }

    @Test
    @Transactional
    void createFormPernyataanWithExistingId() throws Exception {
        // Create the FormPernyataan with an existing ID
        formPernyataan.setId("existing_id");

        int databaseSizeBeforeCreate = formPernyataanRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restFormPernyataanMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(formPernyataan))
            )
            .andExpect(status().isBadRequest());

        // Validate the FormPernyataan in the database
        List<FormPernyataan> formPernyataanList = formPernyataanRepository.findAll();
        assertThat(formPernyataanList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkActiveIsRequired() throws Exception {
        int databaseSizeBeforeTest = formPernyataanRepository.findAll().size();
        // set the field null
        formPernyataan.setActive(null);

        // Create the FormPernyataan, which fails.

        restFormPernyataanMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(formPernyataan))
            )
            .andExpect(status().isBadRequest());

        List<FormPernyataan> formPernyataanList = formPernyataanRepository.findAll();
        assertThat(formPernyataanList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCreatedDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = formPernyataanRepository.findAll().size();
        // set the field null
        formPernyataan.setCreatedDate(null);

        // Create the FormPernyataan, which fails.

        restFormPernyataanMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(formPernyataan))
            )
            .andExpect(status().isBadRequest());

        List<FormPernyataan> formPernyataanList = formPernyataanRepository.findAll();
        assertThat(formPernyataanList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllFormPernyataans() throws Exception {
        // Initialize the database
        formPernyataan.setId(UUID.randomUUID().toString());
        formPernyataanRepository.saveAndFlush(formPernyataan);

        // Get all the formPernyataanList
        restFormPernyataanMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(formPernyataan.getId())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].active").value(hasItem(DEFAULT_ACTIVE.booleanValue())))
            .andExpect(jsonPath("$.[*].remarks").value(hasItem(DEFAULT_REMARKS)))
            .andExpect(jsonPath("$.[*].contents").value(hasItem(DEFAULT_CONTENTS)))
            .andExpect(jsonPath("$.[*].documentTitle").value(hasItem(DEFAULT_DOCUMENT_TITLE)))
            .andExpect(jsonPath("$.[*].officerName").value(hasItem(DEFAULT_OFFICER_NAME)))
            .andExpect(jsonPath("$.[*].officerPhoneNumber").value(hasItem(DEFAULT_OFFICER_PHONE_NUMBER)))
            .andExpect(jsonPath("$.[*].officerPosition").value(hasItem(DEFAULT_OFFICER_POSITION)))
            .andExpect(jsonPath("$.[*].officerDepartment").value(hasItem(DEFAULT_OFFICER_DEPARTMENT)))
            .andExpect(jsonPath("$.[*].kelurahanDesa").value(hasItem(DEFAULT_KELURAHAN_DESA)))
            .andExpect(jsonPath("$.[*].kecamatan").value(hasItem(DEFAULT_KECAMATAN)))
            .andExpect(jsonPath("$.[*].kabupatenKota").value(hasItem(DEFAULT_KABUPATEN_KOTA)))
            .andExpect(jsonPath("$.[*].provinsi").value(hasItem(DEFAULT_PROVINSI)))
            .andExpect(jsonPath("$.[*].pbpTidakDitemukan1").value(hasItem(DEFAULT_PBP_TIDAK_DITEMUKAN_1)))
            .andExpect(jsonPath("$.[*].alamatPbpTidakDitemukan1").value(hasItem(DEFAULT_ALAMAT_PBP_TIDAK_DITEMUKAN_1)))
            .andExpect(jsonPath("$.[*].pbpPengganti1").value(hasItem(DEFAULT_PBP_PENGGANTI_1)))
            .andExpect(jsonPath("$.[*].alamatPbpPengganti1").value(hasItem(DEFAULT_ALAMAT_PBP_PENGGANTI_1)))
            .andExpect(jsonPath("$.[*].pbpTidakDitemukan2").value(hasItem(DEFAULT_PBP_TIDAK_DITEMUKAN_2)))
            .andExpect(jsonPath("$.[*].alamatPbpTidakDitemukan2").value(hasItem(DEFAULT_ALAMAT_PBP_TIDAK_DITEMUKAN_2)))
            .andExpect(jsonPath("$.[*].pbpPengganti2").value(hasItem(DEFAULT_PBP_PENGGANTI_2)))
            .andExpect(jsonPath("$.[*].alamatPbpPengganti2").value(hasItem(DEFAULT_ALAMAT_PBP_PENGGANTI_2)))
            .andExpect(jsonPath("$.[*].pbpTidakDitemukan3").value(hasItem(DEFAULT_PBP_TIDAK_DITEMUKAN_3)))
            .andExpect(jsonPath("$.[*].alamatPbpTidakDitemukan3").value(hasItem(DEFAULT_ALAMAT_PBP_TIDAK_DITEMUKAN_3)))
            .andExpect(jsonPath("$.[*].pbpPengganti3").value(hasItem(DEFAULT_PBP_PENGGANTI_3)))
            .andExpect(jsonPath("$.[*].alamatPbpPengganti3").value(hasItem(DEFAULT_ALAMAT_PBP_PENGGANTI_3)))
            .andExpect(jsonPath("$.[*].pbpTidakDitemukan4").value(hasItem(DEFAULT_PBP_TIDAK_DITEMUKAN_4)))
            .andExpect(jsonPath("$.[*].alamatPbpTidakDitemukan4").value(hasItem(DEFAULT_ALAMAT_PBP_TIDAK_DITEMUKAN_4)))
            .andExpect(jsonPath("$.[*].pbpPengganti4").value(hasItem(DEFAULT_PBP_PENGGANTI_4)))
            .andExpect(jsonPath("$.[*].alamatPbpPengganti4").value(hasItem(DEFAULT_ALAMAT_PBP_PENGGANTI_4)))
            .andExpect(jsonPath("$.[*].pbpTidakDitemukan5").value(hasItem(DEFAULT_PBP_TIDAK_DITEMUKAN_5)))
            .andExpect(jsonPath("$.[*].alamatPbpTidakDitemukan5").value(hasItem(DEFAULT_ALAMAT_PBP_TIDAK_DITEMUKAN_5)))
            .andExpect(jsonPath("$.[*].pbpPengganti5").value(hasItem(DEFAULT_PBP_PENGGANTI_5)))
            .andExpect(jsonPath("$.[*].alamatPbpPengganti5").value(hasItem(DEFAULT_ALAMAT_PBP_PENGGANTI_5)))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)))
            .andExpect(jsonPath("$.[*].lastModifiedDate").value(hasItem(DEFAULT_LAST_MODIFIED_DATE.toString())))
            .andExpect(jsonPath("$.[*].lastModifiedBy").value(hasItem(DEFAULT_LAST_MODIFIED_BY)));
    }

    @Test
    @Transactional
    void getFormPernyataan() throws Exception {
        // Initialize the database
        formPernyataan.setId(UUID.randomUUID().toString());
        formPernyataanRepository.saveAndFlush(formPernyataan);

        // Get the formPernyataan
        restFormPernyataanMockMvc
            .perform(get(ENTITY_API_URL_ID, formPernyataan.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(formPernyataan.getId()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.active").value(DEFAULT_ACTIVE.booleanValue()))
            .andExpect(jsonPath("$.remarks").value(DEFAULT_REMARKS))
            .andExpect(jsonPath("$.contents").value(DEFAULT_CONTENTS))
            .andExpect(jsonPath("$.documentTitle").value(DEFAULT_DOCUMENT_TITLE))
            .andExpect(jsonPath("$.officerName").value(DEFAULT_OFFICER_NAME))
            .andExpect(jsonPath("$.officerPhoneNumber").value(DEFAULT_OFFICER_PHONE_NUMBER))
            .andExpect(jsonPath("$.officerPosition").value(DEFAULT_OFFICER_POSITION))
            .andExpect(jsonPath("$.officerDepartment").value(DEFAULT_OFFICER_DEPARTMENT))
            .andExpect(jsonPath("$.kelurahanDesa").value(DEFAULT_KELURAHAN_DESA))
            .andExpect(jsonPath("$.kecamatan").value(DEFAULT_KECAMATAN))
            .andExpect(jsonPath("$.kabupatenKota").value(DEFAULT_KABUPATEN_KOTA))
            .andExpect(jsonPath("$.provinsi").value(DEFAULT_PROVINSI))
            .andExpect(jsonPath("$.pbpTidakDitemukan1").value(DEFAULT_PBP_TIDAK_DITEMUKAN_1))
            .andExpect(jsonPath("$.alamatPbpTidakDitemukan1").value(DEFAULT_ALAMAT_PBP_TIDAK_DITEMUKAN_1))
            .andExpect(jsonPath("$.pbpPengganti1").value(DEFAULT_PBP_PENGGANTI_1))
            .andExpect(jsonPath("$.alamatPbpPengganti1").value(DEFAULT_ALAMAT_PBP_PENGGANTI_1))
            .andExpect(jsonPath("$.pbpTidakDitemukan2").value(DEFAULT_PBP_TIDAK_DITEMUKAN_2))
            .andExpect(jsonPath("$.alamatPbpTidakDitemukan2").value(DEFAULT_ALAMAT_PBP_TIDAK_DITEMUKAN_2))
            .andExpect(jsonPath("$.pbpPengganti2").value(DEFAULT_PBP_PENGGANTI_2))
            .andExpect(jsonPath("$.alamatPbpPengganti2").value(DEFAULT_ALAMAT_PBP_PENGGANTI_2))
            .andExpect(jsonPath("$.pbpTidakDitemukan3").value(DEFAULT_PBP_TIDAK_DITEMUKAN_3))
            .andExpect(jsonPath("$.alamatPbpTidakDitemukan3").value(DEFAULT_ALAMAT_PBP_TIDAK_DITEMUKAN_3))
            .andExpect(jsonPath("$.pbpPengganti3").value(DEFAULT_PBP_PENGGANTI_3))
            .andExpect(jsonPath("$.alamatPbpPengganti3").value(DEFAULT_ALAMAT_PBP_PENGGANTI_3))
            .andExpect(jsonPath("$.pbpTidakDitemukan4").value(DEFAULT_PBP_TIDAK_DITEMUKAN_4))
            .andExpect(jsonPath("$.alamatPbpTidakDitemukan4").value(DEFAULT_ALAMAT_PBP_TIDAK_DITEMUKAN_4))
            .andExpect(jsonPath("$.pbpPengganti4").value(DEFAULT_PBP_PENGGANTI_4))
            .andExpect(jsonPath("$.alamatPbpPengganti4").value(DEFAULT_ALAMAT_PBP_PENGGANTI_4))
            .andExpect(jsonPath("$.pbpTidakDitemukan5").value(DEFAULT_PBP_TIDAK_DITEMUKAN_5))
            .andExpect(jsonPath("$.alamatPbpTidakDitemukan5").value(DEFAULT_ALAMAT_PBP_TIDAK_DITEMUKAN_5))
            .andExpect(jsonPath("$.pbpPengganti5").value(DEFAULT_PBP_PENGGANTI_5))
            .andExpect(jsonPath("$.alamatPbpPengganti5").value(DEFAULT_ALAMAT_PBP_PENGGANTI_5))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY))
            .andExpect(jsonPath("$.lastModifiedDate").value(DEFAULT_LAST_MODIFIED_DATE.toString()))
            .andExpect(jsonPath("$.lastModifiedBy").value(DEFAULT_LAST_MODIFIED_BY));
    }

    @Test
    @Transactional
    void getNonExistingFormPernyataan() throws Exception {
        // Get the formPernyataan
        restFormPernyataanMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingFormPernyataan() throws Exception {
        // Initialize the database
        formPernyataan.setId(UUID.randomUUID().toString());
        formPernyataanRepository.saveAndFlush(formPernyataan);

        int databaseSizeBeforeUpdate = formPernyataanRepository.findAll().size();

        // Update the formPernyataan
        FormPernyataan updatedFormPernyataan = formPernyataanRepository.findById(formPernyataan.getId()).get();
        // Disconnect from session so that the updates on updatedFormPernyataan are not directly saved in db
        em.detach(updatedFormPernyataan);
        updatedFormPernyataan
            .status(UPDATED_STATUS)
            .active(UPDATED_ACTIVE)
            .remarks(UPDATED_REMARKS)
            .contents(UPDATED_CONTENTS)
            .documentTitle(UPDATED_DOCUMENT_TITLE)
            .officerName(UPDATED_OFFICER_NAME)
            .officerPhoneNumber(UPDATED_OFFICER_PHONE_NUMBER)
            .officerPosition(UPDATED_OFFICER_POSITION)
            .officerDepartment(UPDATED_OFFICER_DEPARTMENT)
            .kelurahanDesa(UPDATED_KELURAHAN_DESA)
            .kecamatan(UPDATED_KECAMATAN)
            .kabupatenKota(UPDATED_KABUPATEN_KOTA)
            .provinsi(UPDATED_PROVINSI)
            .pbpTidakDitemukan1(UPDATED_PBP_TIDAK_DITEMUKAN_1)
            .alamatPbpTidakDitemukan1(UPDATED_ALAMAT_PBP_TIDAK_DITEMUKAN_1)
            .pbpPengganti1(UPDATED_PBP_PENGGANTI_1)
            .alamatPbpPengganti1(UPDATED_ALAMAT_PBP_PENGGANTI_1)
            .pbpTidakDitemukan2(UPDATED_PBP_TIDAK_DITEMUKAN_2)
            .alamatPbpTidakDitemukan2(UPDATED_ALAMAT_PBP_TIDAK_DITEMUKAN_2)
            .pbpPengganti2(UPDATED_PBP_PENGGANTI_2)
            .alamatPbpPengganti2(UPDATED_ALAMAT_PBP_PENGGANTI_2)
            .pbpTidakDitemukan3(UPDATED_PBP_TIDAK_DITEMUKAN_3)
            .alamatPbpTidakDitemukan3(UPDATED_ALAMAT_PBP_TIDAK_DITEMUKAN_3)
            .pbpPengganti3(UPDATED_PBP_PENGGANTI_3)
            .alamatPbpPengganti3(UPDATED_ALAMAT_PBP_PENGGANTI_3)
            .pbpTidakDitemukan4(UPDATED_PBP_TIDAK_DITEMUKAN_4)
            .alamatPbpTidakDitemukan4(UPDATED_ALAMAT_PBP_TIDAK_DITEMUKAN_4)
            .pbpPengganti4(UPDATED_PBP_PENGGANTI_4)
            .alamatPbpPengganti4(UPDATED_ALAMAT_PBP_PENGGANTI_4)
            .pbpTidakDitemukan5(UPDATED_PBP_TIDAK_DITEMUKAN_5)
            .alamatPbpTidakDitemukan5(UPDATED_ALAMAT_PBP_TIDAK_DITEMUKAN_5)
            .pbpPengganti5(UPDATED_PBP_PENGGANTI_5)
            .alamatPbpPengganti5(UPDATED_ALAMAT_PBP_PENGGANTI_5);

        restFormPernyataanMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedFormPernyataan.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedFormPernyataan))
            )
            .andExpect(status().isOk());

        // Validate the FormPernyataan in the database
        List<FormPernyataan> formPernyataanList = formPernyataanRepository.findAll();
        assertThat(formPernyataanList).hasSize(databaseSizeBeforeUpdate);
        FormPernyataan testFormPernyataan = formPernyataanList.get(formPernyataanList.size() - 1);
        assertThat(testFormPernyataan.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testFormPernyataan.getActive()).isEqualTo(UPDATED_ACTIVE);
        assertThat(testFormPernyataan.getRemarks()).isEqualTo(UPDATED_REMARKS);
        assertThat(testFormPernyataan.getContents()).isEqualTo(UPDATED_CONTENTS);
        assertThat(testFormPernyataan.getDocumentTitle()).isEqualTo(UPDATED_DOCUMENT_TITLE);
        assertThat(testFormPernyataan.getOfficerName()).isEqualTo(UPDATED_OFFICER_NAME);
        assertThat(testFormPernyataan.getOfficerPhoneNumber()).isEqualTo(UPDATED_OFFICER_PHONE_NUMBER);
        assertThat(testFormPernyataan.getOfficerPosition()).isEqualTo(UPDATED_OFFICER_POSITION);
        assertThat(testFormPernyataan.getOfficerDepartment()).isEqualTo(UPDATED_OFFICER_DEPARTMENT);
        assertThat(testFormPernyataan.getKelurahanDesa()).isEqualTo(UPDATED_KELURAHAN_DESA);
        assertThat(testFormPernyataan.getKecamatan()).isEqualTo(UPDATED_KECAMATAN);
        assertThat(testFormPernyataan.getKabupatenKota()).isEqualTo(UPDATED_KABUPATEN_KOTA);
        assertThat(testFormPernyataan.getProvinsi()).isEqualTo(UPDATED_PROVINSI);
        assertThat(testFormPernyataan.getPbpTidakDitemukan1()).isEqualTo(UPDATED_PBP_TIDAK_DITEMUKAN_1);
        assertThat(testFormPernyataan.getAlamatPbpTidakDitemukan1()).isEqualTo(UPDATED_ALAMAT_PBP_TIDAK_DITEMUKAN_1);
        assertThat(testFormPernyataan.getPbpPengganti1()).isEqualTo(UPDATED_PBP_PENGGANTI_1);
        assertThat(testFormPernyataan.getAlamatPbpPengganti1()).isEqualTo(UPDATED_ALAMAT_PBP_PENGGANTI_1);
        assertThat(testFormPernyataan.getPbpTidakDitemukan2()).isEqualTo(UPDATED_PBP_TIDAK_DITEMUKAN_2);
        assertThat(testFormPernyataan.getAlamatPbpTidakDitemukan2()).isEqualTo(UPDATED_ALAMAT_PBP_TIDAK_DITEMUKAN_2);
        assertThat(testFormPernyataan.getPbpPengganti2()).isEqualTo(UPDATED_PBP_PENGGANTI_2);
        assertThat(testFormPernyataan.getAlamatPbpPengganti2()).isEqualTo(UPDATED_ALAMAT_PBP_PENGGANTI_2);
        assertThat(testFormPernyataan.getPbpTidakDitemukan3()).isEqualTo(UPDATED_PBP_TIDAK_DITEMUKAN_3);
        assertThat(testFormPernyataan.getAlamatPbpTidakDitemukan3()).isEqualTo(UPDATED_ALAMAT_PBP_TIDAK_DITEMUKAN_3);
        assertThat(testFormPernyataan.getPbpPengganti3()).isEqualTo(UPDATED_PBP_PENGGANTI_3);
        assertThat(testFormPernyataan.getAlamatPbpPengganti3()).isEqualTo(UPDATED_ALAMAT_PBP_PENGGANTI_3);
        assertThat(testFormPernyataan.getPbpTidakDitemukan4()).isEqualTo(UPDATED_PBP_TIDAK_DITEMUKAN_4);
        assertThat(testFormPernyataan.getAlamatPbpTidakDitemukan4()).isEqualTo(UPDATED_ALAMAT_PBP_TIDAK_DITEMUKAN_4);
        assertThat(testFormPernyataan.getPbpPengganti4()).isEqualTo(UPDATED_PBP_PENGGANTI_4);
        assertThat(testFormPernyataan.getAlamatPbpPengganti4()).isEqualTo(UPDATED_ALAMAT_PBP_PENGGANTI_4);
        assertThat(testFormPernyataan.getPbpTidakDitemukan5()).isEqualTo(UPDATED_PBP_TIDAK_DITEMUKAN_5);
        assertThat(testFormPernyataan.getAlamatPbpTidakDitemukan5()).isEqualTo(UPDATED_ALAMAT_PBP_TIDAK_DITEMUKAN_5);
        assertThat(testFormPernyataan.getPbpPengganti5()).isEqualTo(UPDATED_PBP_PENGGANTI_5);
        assertThat(testFormPernyataan.getAlamatPbpPengganti5()).isEqualTo(UPDATED_ALAMAT_PBP_PENGGANTI_5);
        assertThat(testFormPernyataan.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testFormPernyataan.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testFormPernyataan.getLastModifiedDate()).isEqualTo(UPDATED_LAST_MODIFIED_DATE);
        assertThat(testFormPernyataan.getLastModifiedBy()).isEqualTo(UPDATED_LAST_MODIFIED_BY);
    }

    @Test
    @Transactional
    void putNonExistingFormPernyataan() throws Exception {
        int databaseSizeBeforeUpdate = formPernyataanRepository.findAll().size();
        formPernyataan.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFormPernyataanMockMvc
            .perform(
                put(ENTITY_API_URL_ID, formPernyataan.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(formPernyataan))
            )
            .andExpect(status().isBadRequest());

        // Validate the FormPernyataan in the database
        List<FormPernyataan> formPernyataanList = formPernyataanRepository.findAll();
        assertThat(formPernyataanList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchFormPernyataan() throws Exception {
        int databaseSizeBeforeUpdate = formPernyataanRepository.findAll().size();
        formPernyataan.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restFormPernyataanMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(formPernyataan))
            )
            .andExpect(status().isBadRequest());

        // Validate the FormPernyataan in the database
        List<FormPernyataan> formPernyataanList = formPernyataanRepository.findAll();
        assertThat(formPernyataanList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamFormPernyataan() throws Exception {
        int databaseSizeBeforeUpdate = formPernyataanRepository.findAll().size();
        formPernyataan.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restFormPernyataanMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(formPernyataan)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the FormPernyataan in the database
        List<FormPernyataan> formPernyataanList = formPernyataanRepository.findAll();
        assertThat(formPernyataanList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateFormPernyataanWithPatch() throws Exception {
        // Initialize the database
        formPernyataan.setId(UUID.randomUUID().toString());
        formPernyataanRepository.saveAndFlush(formPernyataan);

        int databaseSizeBeforeUpdate = formPernyataanRepository.findAll().size();

        // Update the formPernyataan using partial update
        FormPernyataan partialUpdatedFormPernyataan = new FormPernyataan();
        partialUpdatedFormPernyataan.setId(formPernyataan.getId());

        partialUpdatedFormPernyataan
            .active(UPDATED_ACTIVE)
            .remarks(UPDATED_REMARKS)
            .contents(UPDATED_CONTENTS)
            .documentTitle(UPDATED_DOCUMENT_TITLE)
            .kecamatan(UPDATED_KECAMATAN)
            .provinsi(UPDATED_PROVINSI)
            .alamatPbpPengganti1(UPDATED_ALAMAT_PBP_PENGGANTI_1)
            .alamatPbpPengganti2(UPDATED_ALAMAT_PBP_PENGGANTI_2)
            .alamatPbpPengganti3(UPDATED_ALAMAT_PBP_PENGGANTI_3)
            .alamatPbpTidakDitemukan4(UPDATED_ALAMAT_PBP_TIDAK_DITEMUKAN_4)
            .alamatPbpPengganti4(UPDATED_ALAMAT_PBP_PENGGANTI_4)
            .pbpTidakDitemukan5(UPDATED_PBP_TIDAK_DITEMUKAN_5)
            .pbpPengganti5(UPDATED_PBP_PENGGANTI_5)
            .alamatPbpPengganti5(UPDATED_ALAMAT_PBP_PENGGANTI_5);

        restFormPernyataanMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedFormPernyataan.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedFormPernyataan))
            )
            .andExpect(status().isOk());

        // Validate the FormPernyataan in the database
        List<FormPernyataan> formPernyataanList = formPernyataanRepository.findAll();
        assertThat(formPernyataanList).hasSize(databaseSizeBeforeUpdate);
        FormPernyataan testFormPernyataan = formPernyataanList.get(formPernyataanList.size() - 1);
        assertThat(testFormPernyataan.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testFormPernyataan.getActive()).isEqualTo(UPDATED_ACTIVE);
        assertThat(testFormPernyataan.getRemarks()).isEqualTo(UPDATED_REMARKS);
        assertThat(testFormPernyataan.getContents()).isEqualTo(UPDATED_CONTENTS);
        assertThat(testFormPernyataan.getDocumentTitle()).isEqualTo(UPDATED_DOCUMENT_TITLE);
        assertThat(testFormPernyataan.getOfficerName()).isEqualTo(DEFAULT_OFFICER_NAME);
        assertThat(testFormPernyataan.getOfficerPhoneNumber()).isEqualTo(DEFAULT_OFFICER_PHONE_NUMBER);
        assertThat(testFormPernyataan.getOfficerPosition()).isEqualTo(DEFAULT_OFFICER_POSITION);
        assertThat(testFormPernyataan.getOfficerDepartment()).isEqualTo(DEFAULT_OFFICER_DEPARTMENT);
        assertThat(testFormPernyataan.getKelurahanDesa()).isEqualTo(DEFAULT_KELURAHAN_DESA);
        assertThat(testFormPernyataan.getKecamatan()).isEqualTo(UPDATED_KECAMATAN);
        assertThat(testFormPernyataan.getKabupatenKota()).isEqualTo(DEFAULT_KABUPATEN_KOTA);
        assertThat(testFormPernyataan.getProvinsi()).isEqualTo(UPDATED_PROVINSI);
        assertThat(testFormPernyataan.getPbpTidakDitemukan1()).isEqualTo(DEFAULT_PBP_TIDAK_DITEMUKAN_1);
        assertThat(testFormPernyataan.getAlamatPbpTidakDitemukan1()).isEqualTo(DEFAULT_ALAMAT_PBP_TIDAK_DITEMUKAN_1);
        assertThat(testFormPernyataan.getPbpPengganti1()).isEqualTo(DEFAULT_PBP_PENGGANTI_1);
        assertThat(testFormPernyataan.getAlamatPbpPengganti1()).isEqualTo(UPDATED_ALAMAT_PBP_PENGGANTI_1);
        assertThat(testFormPernyataan.getPbpTidakDitemukan2()).isEqualTo(DEFAULT_PBP_TIDAK_DITEMUKAN_2);
        assertThat(testFormPernyataan.getAlamatPbpTidakDitemukan2()).isEqualTo(DEFAULT_ALAMAT_PBP_TIDAK_DITEMUKAN_2);
        assertThat(testFormPernyataan.getPbpPengganti2()).isEqualTo(DEFAULT_PBP_PENGGANTI_2);
        assertThat(testFormPernyataan.getAlamatPbpPengganti2()).isEqualTo(UPDATED_ALAMAT_PBP_PENGGANTI_2);
        assertThat(testFormPernyataan.getPbpTidakDitemukan3()).isEqualTo(DEFAULT_PBP_TIDAK_DITEMUKAN_3);
        assertThat(testFormPernyataan.getAlamatPbpTidakDitemukan3()).isEqualTo(DEFAULT_ALAMAT_PBP_TIDAK_DITEMUKAN_3);
        assertThat(testFormPernyataan.getPbpPengganti3()).isEqualTo(DEFAULT_PBP_PENGGANTI_3);
        assertThat(testFormPernyataan.getAlamatPbpPengganti3()).isEqualTo(UPDATED_ALAMAT_PBP_PENGGANTI_3);
        assertThat(testFormPernyataan.getPbpTidakDitemukan4()).isEqualTo(DEFAULT_PBP_TIDAK_DITEMUKAN_4);
        assertThat(testFormPernyataan.getAlamatPbpTidakDitemukan4()).isEqualTo(UPDATED_ALAMAT_PBP_TIDAK_DITEMUKAN_4);
        assertThat(testFormPernyataan.getPbpPengganti4()).isEqualTo(DEFAULT_PBP_PENGGANTI_4);
        assertThat(testFormPernyataan.getAlamatPbpPengganti4()).isEqualTo(UPDATED_ALAMAT_PBP_PENGGANTI_4);
        assertThat(testFormPernyataan.getPbpTidakDitemukan5()).isEqualTo(UPDATED_PBP_TIDAK_DITEMUKAN_5);
        assertThat(testFormPernyataan.getAlamatPbpTidakDitemukan5()).isEqualTo(DEFAULT_ALAMAT_PBP_TIDAK_DITEMUKAN_5);
        assertThat(testFormPernyataan.getPbpPengganti5()).isEqualTo(UPDATED_PBP_PENGGANTI_5);
        assertThat(testFormPernyataan.getAlamatPbpPengganti5()).isEqualTo(UPDATED_ALAMAT_PBP_PENGGANTI_5);
        assertThat(testFormPernyataan.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testFormPernyataan.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testFormPernyataan.getLastModifiedDate()).isEqualTo(DEFAULT_LAST_MODIFIED_DATE);
        assertThat(testFormPernyataan.getLastModifiedBy()).isEqualTo(DEFAULT_LAST_MODIFIED_BY);
    }

    @Test
    @Transactional
    void fullUpdateFormPernyataanWithPatch() throws Exception {
        // Initialize the database
        formPernyataan.setId(UUID.randomUUID().toString());
        formPernyataanRepository.saveAndFlush(formPernyataan);

        int databaseSizeBeforeUpdate = formPernyataanRepository.findAll().size();

        // Update the formPernyataan using partial update
        FormPernyataan partialUpdatedFormPernyataan = new FormPernyataan();
        partialUpdatedFormPernyataan.setId(formPernyataan.getId());

        partialUpdatedFormPernyataan
            .status(UPDATED_STATUS)
            .active(UPDATED_ACTIVE)
            .remarks(UPDATED_REMARKS)
            .contents(UPDATED_CONTENTS)
            .documentTitle(UPDATED_DOCUMENT_TITLE)
            .officerName(UPDATED_OFFICER_NAME)
            .officerPhoneNumber(UPDATED_OFFICER_PHONE_NUMBER)
            .officerPosition(UPDATED_OFFICER_POSITION)
            .officerDepartment(UPDATED_OFFICER_DEPARTMENT)
            .kelurahanDesa(UPDATED_KELURAHAN_DESA)
            .kecamatan(UPDATED_KECAMATAN)
            .kabupatenKota(UPDATED_KABUPATEN_KOTA)
            .provinsi(UPDATED_PROVINSI)
            .pbpTidakDitemukan1(UPDATED_PBP_TIDAK_DITEMUKAN_1)
            .alamatPbpTidakDitemukan1(UPDATED_ALAMAT_PBP_TIDAK_DITEMUKAN_1)
            .pbpPengganti1(UPDATED_PBP_PENGGANTI_1)
            .alamatPbpPengganti1(UPDATED_ALAMAT_PBP_PENGGANTI_1)
            .pbpTidakDitemukan2(UPDATED_PBP_TIDAK_DITEMUKAN_2)
            .alamatPbpTidakDitemukan2(UPDATED_ALAMAT_PBP_TIDAK_DITEMUKAN_2)
            .pbpPengganti2(UPDATED_PBP_PENGGANTI_2)
            .alamatPbpPengganti2(UPDATED_ALAMAT_PBP_PENGGANTI_2)
            .pbpTidakDitemukan3(UPDATED_PBP_TIDAK_DITEMUKAN_3)
            .alamatPbpTidakDitemukan3(UPDATED_ALAMAT_PBP_TIDAK_DITEMUKAN_3)
            .pbpPengganti3(UPDATED_PBP_PENGGANTI_3)
            .alamatPbpPengganti3(UPDATED_ALAMAT_PBP_PENGGANTI_3)
            .pbpTidakDitemukan4(UPDATED_PBP_TIDAK_DITEMUKAN_4)
            .alamatPbpTidakDitemukan4(UPDATED_ALAMAT_PBP_TIDAK_DITEMUKAN_4)
            .pbpPengganti4(UPDATED_PBP_PENGGANTI_4)
            .alamatPbpPengganti4(UPDATED_ALAMAT_PBP_PENGGANTI_4)
            .pbpTidakDitemukan5(UPDATED_PBP_TIDAK_DITEMUKAN_5)
            .alamatPbpTidakDitemukan5(UPDATED_ALAMAT_PBP_TIDAK_DITEMUKAN_5)
            .pbpPengganti5(UPDATED_PBP_PENGGANTI_5)
            .alamatPbpPengganti5(UPDATED_ALAMAT_PBP_PENGGANTI_5)
            .createdDate(UPDATED_CREATED_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .lastModifiedDate(UPDATED_LAST_MODIFIED_DATE)
            .lastModifiedBy(UPDATED_LAST_MODIFIED_BY);

        restFormPernyataanMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedFormPernyataan.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedFormPernyataan))
            )
            .andExpect(status().isOk());

        // Validate the FormPernyataan in the database
        List<FormPernyataan> formPernyataanList = formPernyataanRepository.findAll();
        assertThat(formPernyataanList).hasSize(databaseSizeBeforeUpdate);
        FormPernyataan testFormPernyataan = formPernyataanList.get(formPernyataanList.size() - 1);
        assertThat(testFormPernyataan.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testFormPernyataan.getActive()).isEqualTo(UPDATED_ACTIVE);
        assertThat(testFormPernyataan.getRemarks()).isEqualTo(UPDATED_REMARKS);
        assertThat(testFormPernyataan.getContents()).isEqualTo(UPDATED_CONTENTS);
        assertThat(testFormPernyataan.getDocumentTitle()).isEqualTo(UPDATED_DOCUMENT_TITLE);
        assertThat(testFormPernyataan.getOfficerName()).isEqualTo(UPDATED_OFFICER_NAME);
        assertThat(testFormPernyataan.getOfficerPhoneNumber()).isEqualTo(UPDATED_OFFICER_PHONE_NUMBER);
        assertThat(testFormPernyataan.getOfficerPosition()).isEqualTo(UPDATED_OFFICER_POSITION);
        assertThat(testFormPernyataan.getOfficerDepartment()).isEqualTo(UPDATED_OFFICER_DEPARTMENT);
        assertThat(testFormPernyataan.getKelurahanDesa()).isEqualTo(UPDATED_KELURAHAN_DESA);
        assertThat(testFormPernyataan.getKecamatan()).isEqualTo(UPDATED_KECAMATAN);
        assertThat(testFormPernyataan.getKabupatenKota()).isEqualTo(UPDATED_KABUPATEN_KOTA);
        assertThat(testFormPernyataan.getProvinsi()).isEqualTo(UPDATED_PROVINSI);
        assertThat(testFormPernyataan.getPbpTidakDitemukan1()).isEqualTo(UPDATED_PBP_TIDAK_DITEMUKAN_1);
        assertThat(testFormPernyataan.getAlamatPbpTidakDitemukan1()).isEqualTo(UPDATED_ALAMAT_PBP_TIDAK_DITEMUKAN_1);
        assertThat(testFormPernyataan.getPbpPengganti1()).isEqualTo(UPDATED_PBP_PENGGANTI_1);
        assertThat(testFormPernyataan.getAlamatPbpPengganti1()).isEqualTo(UPDATED_ALAMAT_PBP_PENGGANTI_1);
        assertThat(testFormPernyataan.getPbpTidakDitemukan2()).isEqualTo(UPDATED_PBP_TIDAK_DITEMUKAN_2);
        assertThat(testFormPernyataan.getAlamatPbpTidakDitemukan2()).isEqualTo(UPDATED_ALAMAT_PBP_TIDAK_DITEMUKAN_2);
        assertThat(testFormPernyataan.getPbpPengganti2()).isEqualTo(UPDATED_PBP_PENGGANTI_2);
        assertThat(testFormPernyataan.getAlamatPbpPengganti2()).isEqualTo(UPDATED_ALAMAT_PBP_PENGGANTI_2);
        assertThat(testFormPernyataan.getPbpTidakDitemukan3()).isEqualTo(UPDATED_PBP_TIDAK_DITEMUKAN_3);
        assertThat(testFormPernyataan.getAlamatPbpTidakDitemukan3()).isEqualTo(UPDATED_ALAMAT_PBP_TIDAK_DITEMUKAN_3);
        assertThat(testFormPernyataan.getPbpPengganti3()).isEqualTo(UPDATED_PBP_PENGGANTI_3);
        assertThat(testFormPernyataan.getAlamatPbpPengganti3()).isEqualTo(UPDATED_ALAMAT_PBP_PENGGANTI_3);
        assertThat(testFormPernyataan.getPbpTidakDitemukan4()).isEqualTo(UPDATED_PBP_TIDAK_DITEMUKAN_4);
        assertThat(testFormPernyataan.getAlamatPbpTidakDitemukan4()).isEqualTo(UPDATED_ALAMAT_PBP_TIDAK_DITEMUKAN_4);
        assertThat(testFormPernyataan.getPbpPengganti4()).isEqualTo(UPDATED_PBP_PENGGANTI_4);
        assertThat(testFormPernyataan.getAlamatPbpPengganti4()).isEqualTo(UPDATED_ALAMAT_PBP_PENGGANTI_4);
        assertThat(testFormPernyataan.getPbpTidakDitemukan5()).isEqualTo(UPDATED_PBP_TIDAK_DITEMUKAN_5);
        assertThat(testFormPernyataan.getAlamatPbpTidakDitemukan5()).isEqualTo(UPDATED_ALAMAT_PBP_TIDAK_DITEMUKAN_5);
        assertThat(testFormPernyataan.getPbpPengganti5()).isEqualTo(UPDATED_PBP_PENGGANTI_5);
        assertThat(testFormPernyataan.getAlamatPbpPengganti5()).isEqualTo(UPDATED_ALAMAT_PBP_PENGGANTI_5);
        assertThat(testFormPernyataan.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testFormPernyataan.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testFormPernyataan.getLastModifiedDate()).isEqualTo(UPDATED_LAST_MODIFIED_DATE);
        assertThat(testFormPernyataan.getLastModifiedBy()).isEqualTo(UPDATED_LAST_MODIFIED_BY);
    }

    @Test
    @Transactional
    void patchNonExistingFormPernyataan() throws Exception {
        int databaseSizeBeforeUpdate = formPernyataanRepository.findAll().size();
        formPernyataan.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFormPernyataanMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, formPernyataan.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(formPernyataan))
            )
            .andExpect(status().isBadRequest());

        // Validate the FormPernyataan in the database
        List<FormPernyataan> formPernyataanList = formPernyataanRepository.findAll();
        assertThat(formPernyataanList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchFormPernyataan() throws Exception {
        int databaseSizeBeforeUpdate = formPernyataanRepository.findAll().size();
        formPernyataan.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restFormPernyataanMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(formPernyataan))
            )
            .andExpect(status().isBadRequest());

        // Validate the FormPernyataan in the database
        List<FormPernyataan> formPernyataanList = formPernyataanRepository.findAll();
        assertThat(formPernyataanList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamFormPernyataan() throws Exception {
        int databaseSizeBeforeUpdate = formPernyataanRepository.findAll().size();
        formPernyataan.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restFormPernyataanMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(formPernyataan))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the FormPernyataan in the database
        List<FormPernyataan> formPernyataanList = formPernyataanRepository.findAll();
        assertThat(formPernyataanList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteFormPernyataan() throws Exception {
        // Initialize the database
        formPernyataan.setId(UUID.randomUUID().toString());
        formPernyataanRepository.saveAndFlush(formPernyataan);

        int databaseSizeBeforeDelete = formPernyataanRepository.findAll().size();

        // Delete the formPernyataan
        restFormPernyataanMockMvc
            .perform(delete(ENTITY_API_URL_ID, formPernyataan.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<FormPernyataan> formPernyataanList = formPernyataanRepository.findAll();
        assertThat(formPernyataanList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
