package com.darfat.docreaderapp.web.rest;

import static com.darfat.docreaderapp.web.rest.TestUtil.sameNumber;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.darfat.docreaderapp.IntegrationTest;
import com.darfat.docreaderapp.domain.FormPengeluaranBarang;
import com.darfat.docreaderapp.repository.FormPengeluaranBarangRepository;
import java.math.BigDecimal;
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
 * Integration tests for the {@link FormPengeluaranBarangResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class FormPengeluaranBarangResourceIT {

    private static final String DEFAULT_STATUS = "AA";
    private static final String UPDATED_STATUS = "BB";

    private static final Boolean DEFAULT_ACTIVE = false;
    private static final Boolean UPDATED_ACTIVE = true;

    private static final String DEFAULT_REMARKS = "AAAAAAAAAA";
    private static final String UPDATED_REMARKS = "BBBBBBBBBB";

    private static final String DEFAULT_CONTENTS = "AAAAAAAAAA";
    private static final String UPDATED_CONTENTS = "BBBBBBBBBB";

    private static final String DEFAULT_BRANCH = "AAAAAAAAAA";
    private static final String UPDATED_BRANCH = "BBBBBBBBBB";

    private static final String DEFAULT_DOCUMENT_TITLE = "AAAAAAAAAA";
    private static final String UPDATED_DOCUMENT_TITLE = "BBBBBBBBBB";

    private static final String DEFAULT_DOCUMENT_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_DOCUMENT_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_RECIPIENT_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_RECIPIENT_ADDRESS = "BBBBBBBBBB";

    private static final String DEFAULT_NPWP = "AAAAAAAAAA";
    private static final String UPDATED_NPWP = "BBBBBBBBBB";

    private static final String DEFAULT_WAREHOUSE_SOURCE = "AAAAAAAAAA";
    private static final String UPDATED_WAREHOUSE_SOURCE = "BBBBBBBBBB";

    private static final String DEFAULT_DOCUMENT_SOURCE = "AAAAAAAAAA";
    private static final String UPDATED_DOCUMENT_SOURCE = "BBBBBBBBBB";

    private static final String DEFAULT_REFERENCE = "AAAAAAAAAA";
    private static final String UPDATED_REFERENCE = "BBBBBBBBBB";

    private static final String DEFAULT_ORDER_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_ORDER_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_DATE = "AAAAAAAAAA";
    private static final String UPDATED_DATE = "BBBBBBBBBB";

    private static final String DEFAULT_PRODUCT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_PRODUCT_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_SOURCE_LOCATION = "AAAAAAAAAA";
    private static final String UPDATED_SOURCE_LOCATION = "BBBBBBBBBB";

    private static final String DEFAULT_LOT_NO = "AAAAAAAAAA";
    private static final String UPDATED_LOT_NO = "BBBBBBBBBB";

    private static final Float DEFAULT_QUANTITY = new Float(1);
    private static final Float UPDATED_QUANTITY = new Float(1);

    private static final BigDecimal DEFAULT_AMOUNT = new BigDecimal(1);
    private static final BigDecimal UPDATED_AMOUNT = new BigDecimal(2);

    private static final String DEFAULT_SOURCE_DESTINATION = "AAAAAAAAAA";
    private static final String UPDATED_SOURCE_DESTINATION = "BBBBBBBBBB";

    private static final String DEFAULT_ARMADA_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ARMADA_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_ARMADA_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_ARMADA_NUMBER = "BBBBBBBBBB";

    private static final Instant DEFAULT_CREATED_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_CREATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY = "BBBBBBBBBB";

    private static final Instant DEFAULT_LAST_MODIFIED_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_LAST_MODIFIED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_LAST_MODIFIED_BY = "AAAAAAAAAA";
    private static final String UPDATED_LAST_MODIFIED_BY = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/form-pengeluaran-barangs";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private FormPengeluaranBarangRepository formPengeluaranBarangRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restFormPengeluaranBarangMockMvc;

    private FormPengeluaranBarang formPengeluaranBarang;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FormPengeluaranBarang createEntity(EntityManager em) {
        FormPengeluaranBarang formPengeluaranBarang = new FormPengeluaranBarang()
            .status(DEFAULT_STATUS)
            .active(DEFAULT_ACTIVE)
            .remarks(DEFAULT_REMARKS)
            .contents(DEFAULT_CONTENTS)
            .branch(DEFAULT_BRANCH)
            .documentTitle(DEFAULT_DOCUMENT_TITLE)
            .documentNumber(DEFAULT_DOCUMENT_NUMBER)
            .recipientAddress(DEFAULT_RECIPIENT_ADDRESS)
            .npwp(DEFAULT_NPWP)
            .warehouseSource(DEFAULT_WAREHOUSE_SOURCE)
            .documentSource(DEFAULT_DOCUMENT_SOURCE)
            .reference(DEFAULT_REFERENCE)
            .status(DEFAULT_STATUS)
            .date(DEFAULT_DATE)
            .productDescription(DEFAULT_PRODUCT_DESCRIPTION)
            .sourceLocation(DEFAULT_SOURCE_LOCATION)
            .lotNo(DEFAULT_LOT_NO)
            .quantity(DEFAULT_QUANTITY)
            .amount(DEFAULT_AMOUNT)
            .sourceDestination(DEFAULT_SOURCE_DESTINATION)
            .armadaName(DEFAULT_ARMADA_NAME)
            .armadaNumber(DEFAULT_ARMADA_NUMBER);
        return formPengeluaranBarang;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FormPengeluaranBarang createUpdatedEntity(EntityManager em) {
        FormPengeluaranBarang formPengeluaranBarang = new FormPengeluaranBarang()
            .status(UPDATED_STATUS)
            .active(UPDATED_ACTIVE)
            .remarks(UPDATED_REMARKS)
            .contents(UPDATED_CONTENTS)
            .branch(UPDATED_BRANCH)
            .documentTitle(UPDATED_DOCUMENT_TITLE)
            .documentNumber(UPDATED_DOCUMENT_NUMBER)
            .recipientAddress(UPDATED_RECIPIENT_ADDRESS)
            .npwp(UPDATED_NPWP)
            .warehouseSource(UPDATED_WAREHOUSE_SOURCE)
            .documentSource(UPDATED_DOCUMENT_SOURCE)
            .reference(UPDATED_REFERENCE)
            .status(UPDATED_STATUS)
            .date(UPDATED_DATE)
            .productDescription(UPDATED_PRODUCT_DESCRIPTION)
            .sourceLocation(UPDATED_SOURCE_LOCATION)
            .lotNo(UPDATED_LOT_NO)
            .quantity(UPDATED_QUANTITY)
            .amount(UPDATED_AMOUNT)
            .sourceDestination(UPDATED_SOURCE_DESTINATION)
            .armadaName(UPDATED_ARMADA_NAME)
            .armadaNumber(UPDATED_ARMADA_NUMBER)
            ;
        return formPengeluaranBarang;
    }

    @BeforeEach
    public void initTest() {
        formPengeluaranBarang = createEntity(em);
    }

    @Test
    @Transactional
    void createFormPengeluaranBarang() throws Exception {
        int databaseSizeBeforeCreate = formPengeluaranBarangRepository.findAll().size();
        // Create the FormPengeluaranBarang
        restFormPengeluaranBarangMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(formPengeluaranBarang))
            )
            .andExpect(status().isCreated());

        // Validate the FormPengeluaranBarang in the database
        List<FormPengeluaranBarang> formPengeluaranBarangList = formPengeluaranBarangRepository.findAll();
        assertThat(formPengeluaranBarangList).hasSize(databaseSizeBeforeCreate + 1);
        FormPengeluaranBarang testFormPengeluaranBarang = formPengeluaranBarangList.get(formPengeluaranBarangList.size() - 1);
        assertThat(testFormPengeluaranBarang.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testFormPengeluaranBarang.getActive()).isEqualTo(DEFAULT_ACTIVE);
        assertThat(testFormPengeluaranBarang.getRemarks()).isEqualTo(DEFAULT_REMARKS);
        assertThat(testFormPengeluaranBarang.getContents()).isEqualTo(DEFAULT_CONTENTS);
        assertThat(testFormPengeluaranBarang.getBranch()).isEqualTo(DEFAULT_BRANCH);
        assertThat(testFormPengeluaranBarang.getDocumentTitle()).isEqualTo(DEFAULT_DOCUMENT_TITLE);
        assertThat(testFormPengeluaranBarang.getDocumentNumber()).isEqualTo(DEFAULT_DOCUMENT_NUMBER);
        assertThat(testFormPengeluaranBarang.getRecipientAddress()).isEqualTo(DEFAULT_RECIPIENT_ADDRESS);
        assertThat(testFormPengeluaranBarang.getNpwp()).isEqualTo(DEFAULT_NPWP);
        assertThat(testFormPengeluaranBarang.getWarehouseSource()).isEqualTo(DEFAULT_WAREHOUSE_SOURCE);
        assertThat(testFormPengeluaranBarang.getDocumentSource()).isEqualTo(DEFAULT_DOCUMENT_SOURCE);
        assertThat(testFormPengeluaranBarang.getReference()).isEqualTo(DEFAULT_REFERENCE);
        assertThat(testFormPengeluaranBarang.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testFormPengeluaranBarang.getDate()).isEqualTo(DEFAULT_DATE);
        assertThat(testFormPengeluaranBarang.getProductDescription()).isEqualTo(DEFAULT_PRODUCT_DESCRIPTION);
        assertThat(testFormPengeluaranBarang.getSourceLocation()).isEqualTo(DEFAULT_SOURCE_LOCATION);
        assertThat(testFormPengeluaranBarang.getLotNo()).isEqualTo(DEFAULT_LOT_NO);
        assertThat(testFormPengeluaranBarang.getQuantity()).isEqualTo(DEFAULT_QUANTITY);
        assertThat(testFormPengeluaranBarang.getAmount()).isEqualByComparingTo(DEFAULT_AMOUNT);
        assertThat(testFormPengeluaranBarang.getSourceDestination()).isEqualTo(DEFAULT_SOURCE_DESTINATION);
        assertThat(testFormPengeluaranBarang.getArmadaName()).isEqualTo(DEFAULT_ARMADA_NAME);
        assertThat(testFormPengeluaranBarang.getArmadaNumber()).isEqualTo(DEFAULT_ARMADA_NUMBER);
        assertThat(testFormPengeluaranBarang.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testFormPengeluaranBarang.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testFormPengeluaranBarang.getLastModifiedDate()).isEqualTo(DEFAULT_LAST_MODIFIED_DATE);
        assertThat(testFormPengeluaranBarang.getLastModifiedBy()).isEqualTo(DEFAULT_LAST_MODIFIED_BY);
    }

    @Test
    @Transactional
    void createFormPengeluaranBarangWithExistingId() throws Exception {
        // Create the FormPengeluaranBarang with an existing ID
        formPengeluaranBarang.setId("existing_id");

        int databaseSizeBeforeCreate = formPengeluaranBarangRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restFormPengeluaranBarangMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(formPengeluaranBarang))
            )
            .andExpect(status().isBadRequest());

        // Validate the FormPengeluaranBarang in the database
        List<FormPengeluaranBarang> formPengeluaranBarangList = formPengeluaranBarangRepository.findAll();
        assertThat(formPengeluaranBarangList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkActiveIsRequired() throws Exception {
        int databaseSizeBeforeTest = formPengeluaranBarangRepository.findAll().size();
        // set the field null
        formPengeluaranBarang.setActive(null);

        // Create the FormPengeluaranBarang, which fails.

        restFormPengeluaranBarangMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(formPengeluaranBarang))
            )
            .andExpect(status().isBadRequest());

        List<FormPengeluaranBarang> formPengeluaranBarangList = formPengeluaranBarangRepository.findAll();
        assertThat(formPengeluaranBarangList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCreatedDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = formPengeluaranBarangRepository.findAll().size();
        // set the field null
        formPengeluaranBarang.setCreatedDate(null);

        // Create the FormPengeluaranBarang, which fails.

        restFormPengeluaranBarangMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(formPengeluaranBarang))
            )
            .andExpect(status().isBadRequest());

        List<FormPengeluaranBarang> formPengeluaranBarangList = formPengeluaranBarangRepository.findAll();
        assertThat(formPengeluaranBarangList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllFormPengeluaranBarangs() throws Exception {
        // Initialize the database
        formPengeluaranBarang.setId(UUID.randomUUID().toString());
        formPengeluaranBarangRepository.saveAndFlush(formPengeluaranBarang);

        // Get all the formPengeluaranBarangList
        restFormPengeluaranBarangMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(formPengeluaranBarang.getId())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].active").value(hasItem(DEFAULT_ACTIVE.booleanValue())))
            .andExpect(jsonPath("$.[*].remarks").value(hasItem(DEFAULT_REMARKS)))
            .andExpect(jsonPath("$.[*].contents").value(hasItem(DEFAULT_CONTENTS)))
            .andExpect(jsonPath("$.[*].branch").value(hasItem(DEFAULT_BRANCH)))
            .andExpect(jsonPath("$.[*].documentTitle").value(hasItem(DEFAULT_DOCUMENT_TITLE)))
            .andExpect(jsonPath("$.[*].documentNumber").value(hasItem(DEFAULT_DOCUMENT_NUMBER)))
            .andExpect(jsonPath("$.[*].recipientAddress").value(hasItem(DEFAULT_RECIPIENT_ADDRESS)))
            .andExpect(jsonPath("$.[*].npwp").value(hasItem(DEFAULT_NPWP)))
            .andExpect(jsonPath("$.[*].warehouseSource").value(hasItem(DEFAULT_WAREHOUSE_SOURCE)))
            .andExpect(jsonPath("$.[*].documentSource").value(hasItem(DEFAULT_DOCUMENT_SOURCE)))
            .andExpect(jsonPath("$.[*].reference").value(hasItem(DEFAULT_REFERENCE)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].date").value(hasItem(DEFAULT_DATE)))
            .andExpect(jsonPath("$.[*].productDescription").value(hasItem(DEFAULT_PRODUCT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].sourceLocation").value(hasItem(DEFAULT_SOURCE_LOCATION)))
            .andExpect(jsonPath("$.[*].lotNo").value(hasItem(DEFAULT_LOT_NO)))
            .andExpect(jsonPath("$.[*].quantity").value(hasItem(DEFAULT_QUANTITY)))
            .andExpect(jsonPath("$.[*].amount").value(hasItem(sameNumber(DEFAULT_AMOUNT))))
            .andExpect(jsonPath("$.[*].sourceDestination").value(hasItem(DEFAULT_SOURCE_DESTINATION)))
            .andExpect(jsonPath("$.[*].armadaName").value(hasItem(DEFAULT_ARMADA_NAME)))
            .andExpect(jsonPath("$.[*].armadaNumber").value(hasItem(DEFAULT_ARMADA_NUMBER)))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)))
            .andExpect(jsonPath("$.[*].lastModifiedDate").value(hasItem(DEFAULT_LAST_MODIFIED_DATE.toString())))
            .andExpect(jsonPath("$.[*].lastModifiedBy").value(hasItem(DEFAULT_LAST_MODIFIED_BY)));
    }

    @Test
    @Transactional
    void getFormPengeluaranBarang() throws Exception {
        // Initialize the database
        formPengeluaranBarang.setId(UUID.randomUUID().toString());
        formPengeluaranBarangRepository.saveAndFlush(formPengeluaranBarang);

        // Get the formPengeluaranBarang
        restFormPengeluaranBarangMockMvc
            .perform(get(ENTITY_API_URL_ID, formPengeluaranBarang.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(formPengeluaranBarang.getId()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.active").value(DEFAULT_ACTIVE.booleanValue()))
            .andExpect(jsonPath("$.remarks").value(DEFAULT_REMARKS))
            .andExpect(jsonPath("$.contents").value(DEFAULT_CONTENTS))
            .andExpect(jsonPath("$.branch").value(DEFAULT_BRANCH))
            .andExpect(jsonPath("$.documentTitle").value(DEFAULT_DOCUMENT_TITLE))
            .andExpect(jsonPath("$.documentNumber").value(DEFAULT_DOCUMENT_NUMBER))
            .andExpect(jsonPath("$.recipientAddress").value(DEFAULT_RECIPIENT_ADDRESS))
            .andExpect(jsonPath("$.npwp").value(DEFAULT_NPWP))
            .andExpect(jsonPath("$.warehouseSource").value(DEFAULT_WAREHOUSE_SOURCE))
            .andExpect(jsonPath("$.documentSource").value(DEFAULT_DOCUMENT_SOURCE))
            .andExpect(jsonPath("$.reference").value(DEFAULT_REFERENCE))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.date").value(DEFAULT_DATE))
            .andExpect(jsonPath("$.productDescription").value(DEFAULT_PRODUCT_DESCRIPTION))
            .andExpect(jsonPath("$.sourceLocation").value(DEFAULT_SOURCE_LOCATION))
            .andExpect(jsonPath("$.lotNo").value(DEFAULT_LOT_NO))
            .andExpect(jsonPath("$.quantity").value(DEFAULT_QUANTITY))
            .andExpect(jsonPath("$.amount").value(sameNumber(DEFAULT_AMOUNT)))
            .andExpect(jsonPath("$.sourceDestination").value(DEFAULT_SOURCE_DESTINATION))
            .andExpect(jsonPath("$.armadaName").value(DEFAULT_ARMADA_NAME))
            .andExpect(jsonPath("$.armadaNumber").value(DEFAULT_ARMADA_NUMBER))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY))
            .andExpect(jsonPath("$.lastModifiedDate").value(DEFAULT_LAST_MODIFIED_DATE.toString()))
            .andExpect(jsonPath("$.lastModifiedBy").value(DEFAULT_LAST_MODIFIED_BY));
    }

    @Test
    @Transactional
    void getNonExistingFormPengeluaranBarang() throws Exception {
        // Get the formPengeluaranBarang
        restFormPengeluaranBarangMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingFormPengeluaranBarang() throws Exception {
        // Initialize the database
        formPengeluaranBarang.setId(UUID.randomUUID().toString());
        formPengeluaranBarangRepository.saveAndFlush(formPengeluaranBarang);

        int databaseSizeBeforeUpdate = formPengeluaranBarangRepository.findAll().size();

        // Update the formPengeluaranBarang
        FormPengeluaranBarang updatedFormPengeluaranBarang = formPengeluaranBarangRepository.findById(formPengeluaranBarang.getId()).get();
        // Disconnect from session so that the updates on updatedFormPengeluaranBarang are not directly saved in db
        em.detach(updatedFormPengeluaranBarang);
        updatedFormPengeluaranBarang
            .status(UPDATED_STATUS)
            .active(UPDATED_ACTIVE)
            .remarks(UPDATED_REMARKS)
            .contents(UPDATED_CONTENTS)
            .branch(UPDATED_BRANCH)
            .documentTitle(UPDATED_DOCUMENT_TITLE)
            .documentNumber(UPDATED_DOCUMENT_NUMBER)
            .recipientAddress(UPDATED_RECIPIENT_ADDRESS)
            .npwp(UPDATED_NPWP)
            .warehouseSource(UPDATED_WAREHOUSE_SOURCE)
            .documentSource(UPDATED_DOCUMENT_SOURCE)
            .reference(UPDATED_REFERENCE)
            .status(UPDATED_STATUS)
            .date(UPDATED_DATE)
            .productDescription(UPDATED_PRODUCT_DESCRIPTION)
            .sourceLocation(UPDATED_SOURCE_LOCATION)
            .lotNo(UPDATED_LOT_NO)
            .quantity(UPDATED_QUANTITY)
            .amount(UPDATED_AMOUNT)
            .sourceDestination(UPDATED_SOURCE_DESTINATION)
            .armadaName(UPDATED_ARMADA_NAME)
            .armadaNumber(UPDATED_ARMADA_NUMBER);

        restFormPengeluaranBarangMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedFormPengeluaranBarang.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedFormPengeluaranBarang))
            )
            .andExpect(status().isOk());

        // Validate the FormPengeluaranBarang in the database
        List<FormPengeluaranBarang> formPengeluaranBarangList = formPengeluaranBarangRepository.findAll();
        assertThat(formPengeluaranBarangList).hasSize(databaseSizeBeforeUpdate);
        FormPengeluaranBarang testFormPengeluaranBarang = formPengeluaranBarangList.get(formPengeluaranBarangList.size() - 1);
        assertThat(testFormPengeluaranBarang.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testFormPengeluaranBarang.getActive()).isEqualTo(UPDATED_ACTIVE);
        assertThat(testFormPengeluaranBarang.getRemarks()).isEqualTo(UPDATED_REMARKS);
        assertThat(testFormPengeluaranBarang.getContents()).isEqualTo(UPDATED_CONTENTS);
        assertThat(testFormPengeluaranBarang.getBranch()).isEqualTo(UPDATED_BRANCH);
        assertThat(testFormPengeluaranBarang.getDocumentTitle()).isEqualTo(UPDATED_DOCUMENT_TITLE);
        assertThat(testFormPengeluaranBarang.getDocumentNumber()).isEqualTo(UPDATED_DOCUMENT_NUMBER);
        assertThat(testFormPengeluaranBarang.getRecipientAddress()).isEqualTo(UPDATED_RECIPIENT_ADDRESS);
        assertThat(testFormPengeluaranBarang.getNpwp()).isEqualTo(UPDATED_NPWP);
        assertThat(testFormPengeluaranBarang.getWarehouseSource()).isEqualTo(UPDATED_WAREHOUSE_SOURCE);
        assertThat(testFormPengeluaranBarang.getDocumentSource()).isEqualTo(UPDATED_DOCUMENT_SOURCE);
        assertThat(testFormPengeluaranBarang.getReference()).isEqualTo(UPDATED_REFERENCE);
        assertThat(testFormPengeluaranBarang.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testFormPengeluaranBarang.getDate()).isEqualTo(UPDATED_DATE);
        assertThat(testFormPengeluaranBarang.getProductDescription()).isEqualTo(UPDATED_PRODUCT_DESCRIPTION);
        assertThat(testFormPengeluaranBarang.getSourceLocation()).isEqualTo(UPDATED_SOURCE_LOCATION);
        assertThat(testFormPengeluaranBarang.getLotNo()).isEqualTo(UPDATED_LOT_NO);
        assertThat(testFormPengeluaranBarang.getQuantity()).isEqualTo(UPDATED_QUANTITY);
        assertThat(testFormPengeluaranBarang.getAmount()).isEqualByComparingTo(UPDATED_AMOUNT);
        assertThat(testFormPengeluaranBarang.getSourceDestination()).isEqualTo(UPDATED_SOURCE_DESTINATION);
        assertThat(testFormPengeluaranBarang.getArmadaName()).isEqualTo(UPDATED_ARMADA_NAME);
        assertThat(testFormPengeluaranBarang.getArmadaNumber()).isEqualTo(UPDATED_ARMADA_NUMBER);
        assertThat(testFormPengeluaranBarang.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testFormPengeluaranBarang.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testFormPengeluaranBarang.getLastModifiedDate()).isEqualTo(UPDATED_LAST_MODIFIED_DATE);
        assertThat(testFormPengeluaranBarang.getLastModifiedBy()).isEqualTo(UPDATED_LAST_MODIFIED_BY);
    }

    @Test
    @Transactional
    void putNonExistingFormPengeluaranBarang() throws Exception {
        int databaseSizeBeforeUpdate = formPengeluaranBarangRepository.findAll().size();
        formPengeluaranBarang.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFormPengeluaranBarangMockMvc
            .perform(
                put(ENTITY_API_URL_ID, formPengeluaranBarang.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(formPengeluaranBarang))
            )
            .andExpect(status().isBadRequest());

        // Validate the FormPengeluaranBarang in the database
        List<FormPengeluaranBarang> formPengeluaranBarangList = formPengeluaranBarangRepository.findAll();
        assertThat(formPengeluaranBarangList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchFormPengeluaranBarang() throws Exception {
        int databaseSizeBeforeUpdate = formPengeluaranBarangRepository.findAll().size();
        formPengeluaranBarang.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restFormPengeluaranBarangMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(formPengeluaranBarang))
            )
            .andExpect(status().isBadRequest());

        // Validate the FormPengeluaranBarang in the database
        List<FormPengeluaranBarang> formPengeluaranBarangList = formPengeluaranBarangRepository.findAll();
        assertThat(formPengeluaranBarangList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamFormPengeluaranBarang() throws Exception {
        int databaseSizeBeforeUpdate = formPengeluaranBarangRepository.findAll().size();
        formPengeluaranBarang.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restFormPengeluaranBarangMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(formPengeluaranBarang))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the FormPengeluaranBarang in the database
        List<FormPengeluaranBarang> formPengeluaranBarangList = formPengeluaranBarangRepository.findAll();
        assertThat(formPengeluaranBarangList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateFormPengeluaranBarangWithPatch() throws Exception {
        // Initialize the database
        formPengeluaranBarang.setId(UUID.randomUUID().toString());
        formPengeluaranBarangRepository.saveAndFlush(formPengeluaranBarang);

        int databaseSizeBeforeUpdate = formPengeluaranBarangRepository.findAll().size();

        // Update the formPengeluaranBarang using partial update
        FormPengeluaranBarang partialUpdatedFormPengeluaranBarang = new FormPengeluaranBarang();
        partialUpdatedFormPengeluaranBarang.setId(formPengeluaranBarang.getId());

        partialUpdatedFormPengeluaranBarang
            .status(UPDATED_STATUS)
            .active(UPDATED_ACTIVE)
            .branch(UPDATED_BRANCH)
            .documentTitle(UPDATED_DOCUMENT_TITLE)
            .recipientAddress(UPDATED_RECIPIENT_ADDRESS)
            .warehouseSource(UPDATED_WAREHOUSE_SOURCE)
            .documentSource(UPDATED_DOCUMENT_SOURCE)
            .reference(UPDATED_REFERENCE)
            .status(UPDATED_STATUS)
            .sourceLocation(UPDATED_SOURCE_LOCATION)
            .lotNo(UPDATED_LOT_NO)
            .amount(UPDATED_AMOUNT)
            .armadaName(UPDATED_ARMADA_NAME);

        restFormPengeluaranBarangMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedFormPengeluaranBarang.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedFormPengeluaranBarang))
            )
            .andExpect(status().isOk());

        // Validate the FormPengeluaranBarang in the database
        List<FormPengeluaranBarang> formPengeluaranBarangList = formPengeluaranBarangRepository.findAll();
        assertThat(formPengeluaranBarangList).hasSize(databaseSizeBeforeUpdate);
        FormPengeluaranBarang testFormPengeluaranBarang = formPengeluaranBarangList.get(formPengeluaranBarangList.size() - 1);
        assertThat(testFormPengeluaranBarang.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testFormPengeluaranBarang.getActive()).isEqualTo(UPDATED_ACTIVE);
        assertThat(testFormPengeluaranBarang.getRemarks()).isEqualTo(DEFAULT_REMARKS);
        assertThat(testFormPengeluaranBarang.getContents()).isEqualTo(DEFAULT_CONTENTS);
        assertThat(testFormPengeluaranBarang.getBranch()).isEqualTo(UPDATED_BRANCH);
        assertThat(testFormPengeluaranBarang.getDocumentTitle()).isEqualTo(UPDATED_DOCUMENT_TITLE);
        assertThat(testFormPengeluaranBarang.getDocumentNumber()).isEqualTo(DEFAULT_DOCUMENT_NUMBER);
        assertThat(testFormPengeluaranBarang.getRecipientAddress()).isEqualTo(UPDATED_RECIPIENT_ADDRESS);
        assertThat(testFormPengeluaranBarang.getNpwp()).isEqualTo(DEFAULT_NPWP);
        assertThat(testFormPengeluaranBarang.getWarehouseSource()).isEqualTo(UPDATED_WAREHOUSE_SOURCE);
        assertThat(testFormPengeluaranBarang.getDocumentSource()).isEqualTo(UPDATED_DOCUMENT_SOURCE);
        assertThat(testFormPengeluaranBarang.getReference()).isEqualTo(UPDATED_REFERENCE);
        assertThat(testFormPengeluaranBarang.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testFormPengeluaranBarang.getDate()).isEqualTo(DEFAULT_DATE);
        assertThat(testFormPengeluaranBarang.getProductDescription()).isEqualTo(DEFAULT_PRODUCT_DESCRIPTION);
        assertThat(testFormPengeluaranBarang.getSourceLocation()).isEqualTo(UPDATED_SOURCE_LOCATION);
        assertThat(testFormPengeluaranBarang.getLotNo()).isEqualTo(UPDATED_LOT_NO);
        assertThat(testFormPengeluaranBarang.getQuantity()).isEqualTo(DEFAULT_QUANTITY);
        assertThat(testFormPengeluaranBarang.getAmount()).isEqualByComparingTo(UPDATED_AMOUNT);
        assertThat(testFormPengeluaranBarang.getSourceDestination()).isEqualTo(DEFAULT_SOURCE_DESTINATION);
        assertThat(testFormPengeluaranBarang.getArmadaName()).isEqualTo(UPDATED_ARMADA_NAME);
        assertThat(testFormPengeluaranBarang.getArmadaNumber()).isEqualTo(DEFAULT_ARMADA_NUMBER);
        assertThat(testFormPengeluaranBarang.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testFormPengeluaranBarang.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testFormPengeluaranBarang.getLastModifiedDate()).isEqualTo(UPDATED_LAST_MODIFIED_DATE);
        assertThat(testFormPengeluaranBarang.getLastModifiedBy()).isEqualTo(UPDATED_LAST_MODIFIED_BY);
    }

    @Test
    @Transactional
    void fullUpdateFormPengeluaranBarangWithPatch() throws Exception {
        // Initialize the database
        formPengeluaranBarang.setId(UUID.randomUUID().toString());
        formPengeluaranBarangRepository.saveAndFlush(formPengeluaranBarang);

        int databaseSizeBeforeUpdate = formPengeluaranBarangRepository.findAll().size();

        // Update the formPengeluaranBarang using partial update
        FormPengeluaranBarang partialUpdatedFormPengeluaranBarang = new FormPengeluaranBarang();
        partialUpdatedFormPengeluaranBarang.setId(formPengeluaranBarang.getId());

        partialUpdatedFormPengeluaranBarang
            .status(UPDATED_STATUS)
            .active(UPDATED_ACTIVE)
            .remarks(UPDATED_REMARKS)
            .contents(UPDATED_CONTENTS)
            .branch(UPDATED_BRANCH)
            .documentTitle(UPDATED_DOCUMENT_TITLE)
            .documentNumber(UPDATED_DOCUMENT_NUMBER)
            .recipientAddress(UPDATED_RECIPIENT_ADDRESS)
            .npwp(UPDATED_NPWP)
            .warehouseSource(UPDATED_WAREHOUSE_SOURCE)
            .documentSource(UPDATED_DOCUMENT_SOURCE)
            .reference(UPDATED_REFERENCE)
            .status(UPDATED_STATUS)
            .date(UPDATED_DATE)
            .productDescription(UPDATED_PRODUCT_DESCRIPTION)
            .sourceLocation(UPDATED_SOURCE_LOCATION)
            .lotNo(UPDATED_LOT_NO)
            .quantity(UPDATED_QUANTITY)
            .amount(UPDATED_AMOUNT)
            .sourceDestination(UPDATED_SOURCE_DESTINATION)
            .armadaName(UPDATED_ARMADA_NAME)
            .armadaNumber(UPDATED_ARMADA_NUMBER);

        restFormPengeluaranBarangMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedFormPengeluaranBarang.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedFormPengeluaranBarang))
            )
            .andExpect(status().isOk());

        // Validate the FormPengeluaranBarang in the database
        List<FormPengeluaranBarang> formPengeluaranBarangList = formPengeluaranBarangRepository.findAll();
        assertThat(formPengeluaranBarangList).hasSize(databaseSizeBeforeUpdate);
        FormPengeluaranBarang testFormPengeluaranBarang = formPengeluaranBarangList.get(formPengeluaranBarangList.size() - 1);
        assertThat(testFormPengeluaranBarang.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testFormPengeluaranBarang.getActive()).isEqualTo(UPDATED_ACTIVE);
        assertThat(testFormPengeluaranBarang.getRemarks()).isEqualTo(UPDATED_REMARKS);
        assertThat(testFormPengeluaranBarang.getContents()).isEqualTo(UPDATED_CONTENTS);
        assertThat(testFormPengeluaranBarang.getBranch()).isEqualTo(UPDATED_BRANCH);
        assertThat(testFormPengeluaranBarang.getDocumentTitle()).isEqualTo(UPDATED_DOCUMENT_TITLE);
        assertThat(testFormPengeluaranBarang.getDocumentNumber()).isEqualTo(UPDATED_DOCUMENT_NUMBER);
        assertThat(testFormPengeluaranBarang.getRecipientAddress()).isEqualTo(UPDATED_RECIPIENT_ADDRESS);
        assertThat(testFormPengeluaranBarang.getNpwp()).isEqualTo(UPDATED_NPWP);
        assertThat(testFormPengeluaranBarang.getWarehouseSource()).isEqualTo(UPDATED_WAREHOUSE_SOURCE);
        assertThat(testFormPengeluaranBarang.getDocumentSource()).isEqualTo(UPDATED_DOCUMENT_SOURCE);
        assertThat(testFormPengeluaranBarang.getReference()).isEqualTo(UPDATED_REFERENCE);
        assertThat(testFormPengeluaranBarang.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testFormPengeluaranBarang.getDate()).isEqualTo(UPDATED_DATE);
        assertThat(testFormPengeluaranBarang.getProductDescription()).isEqualTo(UPDATED_PRODUCT_DESCRIPTION);
        assertThat(testFormPengeluaranBarang.getSourceLocation()).isEqualTo(UPDATED_SOURCE_LOCATION);
        assertThat(testFormPengeluaranBarang.getLotNo()).isEqualTo(UPDATED_LOT_NO);
        assertThat(testFormPengeluaranBarang.getQuantity()).isEqualTo(UPDATED_QUANTITY);
        assertThat(testFormPengeluaranBarang.getAmount()).isEqualByComparingTo(UPDATED_AMOUNT);
        assertThat(testFormPengeluaranBarang.getSourceDestination()).isEqualTo(UPDATED_SOURCE_DESTINATION);
        assertThat(testFormPengeluaranBarang.getArmadaName()).isEqualTo(UPDATED_ARMADA_NAME);
        assertThat(testFormPengeluaranBarang.getArmadaNumber()).isEqualTo(UPDATED_ARMADA_NUMBER);
        assertThat(testFormPengeluaranBarang.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testFormPengeluaranBarang.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testFormPengeluaranBarang.getLastModifiedDate()).isEqualTo(UPDATED_LAST_MODIFIED_DATE);
        assertThat(testFormPengeluaranBarang.getLastModifiedBy()).isEqualTo(UPDATED_LAST_MODIFIED_BY);
    }

    @Test
    @Transactional
    void patchNonExistingFormPengeluaranBarang() throws Exception {
        int databaseSizeBeforeUpdate = formPengeluaranBarangRepository.findAll().size();
        formPengeluaranBarang.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFormPengeluaranBarangMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, formPengeluaranBarang.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(formPengeluaranBarang))
            )
            .andExpect(status().isBadRequest());

        // Validate the FormPengeluaranBarang in the database
        List<FormPengeluaranBarang> formPengeluaranBarangList = formPengeluaranBarangRepository.findAll();
        assertThat(formPengeluaranBarangList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchFormPengeluaranBarang() throws Exception {
        int databaseSizeBeforeUpdate = formPengeluaranBarangRepository.findAll().size();
        formPengeluaranBarang.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restFormPengeluaranBarangMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(formPengeluaranBarang))
            )
            .andExpect(status().isBadRequest());

        // Validate the FormPengeluaranBarang in the database
        List<FormPengeluaranBarang> formPengeluaranBarangList = formPengeluaranBarangRepository.findAll();
        assertThat(formPengeluaranBarangList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamFormPengeluaranBarang() throws Exception {
        int databaseSizeBeforeUpdate = formPengeluaranBarangRepository.findAll().size();
        formPengeluaranBarang.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restFormPengeluaranBarangMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(formPengeluaranBarang))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the FormPengeluaranBarang in the database
        List<FormPengeluaranBarang> formPengeluaranBarangList = formPengeluaranBarangRepository.findAll();
        assertThat(formPengeluaranBarangList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteFormPengeluaranBarang() throws Exception {
        // Initialize the database
        formPengeluaranBarang.setId(UUID.randomUUID().toString());
        formPengeluaranBarangRepository.saveAndFlush(formPengeluaranBarang);

        int databaseSizeBeforeDelete = formPengeluaranBarangRepository.findAll().size();

        // Delete the formPengeluaranBarang
        restFormPengeluaranBarangMockMvc
            .perform(delete(ENTITY_API_URL_ID, formPengeluaranBarang.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<FormPengeluaranBarang> formPengeluaranBarangList = formPengeluaranBarangRepository.findAll();
        assertThat(formPengeluaranBarangList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
