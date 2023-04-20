package com.darfat.docreaderapp.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.darfat.docreaderapp.IntegrationTest;
import com.darfat.docreaderapp.domain.VerifiedDocuments;
import com.darfat.docreaderapp.repository.VerifiedDocumentsRepository;
import com.darfat.docreaderapp.service.criteria.VerifiedDocumentsCriteria;
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
 * Integration tests for the {@link VerifiedDocumentsResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class VerifiedDocumentsResourceIT {

    private static final String DEFAULT_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_CONTENT_ID = "AAAAAAAAAA";
    private static final String UPDATED_CONTENT_ID = "BBBBBBBBBB";

    private static final Instant DEFAULT_CREATED_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_CREATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY = "BBBBBBBBBB";

    private static final Instant DEFAULT_LAST_MODIFIED_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_LAST_MODIFIED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_LAST_MODIFIED_BY = "AAAAAAAAAA";
    private static final String UPDATED_LAST_MODIFIED_BY = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/verified-documents";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private VerifiedDocumentsRepository verifiedDocumentsRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restVerifiedDocumentsMockMvc;

    private VerifiedDocuments verifiedDocuments;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static VerifiedDocuments createEntity(EntityManager em) {
        VerifiedDocuments verifiedDocuments = new VerifiedDocuments()
            .type(DEFAULT_TYPE)
            .name(DEFAULT_NAME)
            .status(DEFAULT_STATUS)
            .contentId(DEFAULT_CONTENT_ID);
//            .createdDate(DEFAULT_CREATED_DATE)
//            .createdBy(DEFAULT_CREATED_BY)
//            .lastModifiedDate(DEFAULT_LAST_MODIFIED_DATE)
//            .lastModifiedBy(DEFAULT_LAST_MODIFIED_BY);
        return verifiedDocuments;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static VerifiedDocuments createUpdatedEntity(EntityManager em) {
        VerifiedDocuments verifiedDocuments = new VerifiedDocuments()
            .type(UPDATED_TYPE)
            .name(UPDATED_NAME)
            .status(UPDATED_STATUS)
            .contentId(UPDATED_CONTENT_ID);
//            .createdDate(UPDATED_CREATED_DATE)
//            .createdBy(UPDATED_CREATED_BY)
//            .lastModifiedDate(UPDATED_LAST_MODIFIED_DATE)
//            .lastModifiedBy(UPDATED_LAST_MODIFIED_BY);
        return verifiedDocuments;
    }

    @BeforeEach
    public void initTest() {
        verifiedDocuments = createEntity(em);
    }

    @Test
    @Transactional
    void createVerifiedDocuments() throws Exception {
        int databaseSizeBeforeCreate = verifiedDocumentsRepository.findAll().size();
        // Create the VerifiedDocuments
        restVerifiedDocumentsMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(verifiedDocuments))
            )
            .andExpect(status().isCreated());

        // Validate the VerifiedDocuments in the database
        List<VerifiedDocuments> verifiedDocumentsList = verifiedDocumentsRepository.findAll();
        assertThat(verifiedDocumentsList).hasSize(databaseSizeBeforeCreate + 1);
        VerifiedDocuments testVerifiedDocuments = verifiedDocumentsList.get(verifiedDocumentsList.size() - 1);
        assertThat(testVerifiedDocuments.getType()).isEqualTo(DEFAULT_TYPE);
        assertThat(testVerifiedDocuments.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testVerifiedDocuments.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testVerifiedDocuments.getContentId()).isEqualTo(DEFAULT_CONTENT_ID);
        assertThat(testVerifiedDocuments.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testVerifiedDocuments.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testVerifiedDocuments.getLastModifiedDate()).isEqualTo(DEFAULT_LAST_MODIFIED_DATE);
        assertThat(testVerifiedDocuments.getLastModifiedBy()).isEqualTo(DEFAULT_LAST_MODIFIED_BY);
    }

    @Test
    @Transactional
    void createVerifiedDocumentsWithExistingId() throws Exception {
        // Create the VerifiedDocuments with an existing ID
        verifiedDocuments.setId("existing_id");

        int databaseSizeBeforeCreate = verifiedDocumentsRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restVerifiedDocumentsMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(verifiedDocuments))
            )
            .andExpect(status().isBadRequest());

        // Validate the VerifiedDocuments in the database
        List<VerifiedDocuments> verifiedDocumentsList = verifiedDocumentsRepository.findAll();
        assertThat(verifiedDocumentsList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = verifiedDocumentsRepository.findAll().size();
        // set the field null
        verifiedDocuments.setType(null);

        // Create the VerifiedDocuments, which fails.

        restVerifiedDocumentsMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(verifiedDocuments))
            )
            .andExpect(status().isBadRequest());

        List<VerifiedDocuments> verifiedDocumentsList = verifiedDocumentsRepository.findAll();
        assertThat(verifiedDocumentsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = verifiedDocumentsRepository.findAll().size();
        // set the field null
        verifiedDocuments.setName(null);

        // Create the VerifiedDocuments, which fails.

        restVerifiedDocumentsMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(verifiedDocuments))
            )
            .andExpect(status().isBadRequest());

        List<VerifiedDocuments> verifiedDocumentsList = verifiedDocumentsRepository.findAll();
        assertThat(verifiedDocumentsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkStatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = verifiedDocumentsRepository.findAll().size();
        // set the field null
        verifiedDocuments.setStatus(null);

        // Create the VerifiedDocuments, which fails.

        restVerifiedDocumentsMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(verifiedDocuments))
            )
            .andExpect(status().isBadRequest());

        List<VerifiedDocuments> verifiedDocumentsList = verifiedDocumentsRepository.findAll();
        assertThat(verifiedDocumentsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkContentIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = verifiedDocumentsRepository.findAll().size();
        // set the field null
        verifiedDocuments.setContentId(null);

        // Create the VerifiedDocuments, which fails.

        restVerifiedDocumentsMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(verifiedDocuments))
            )
            .andExpect(status().isBadRequest());

        List<VerifiedDocuments> verifiedDocumentsList = verifiedDocumentsRepository.findAll();
        assertThat(verifiedDocumentsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCreatedDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = verifiedDocumentsRepository.findAll().size();
        // set the field null
        verifiedDocuments.setCreatedDate(null);

        // Create the VerifiedDocuments, which fails.

        restVerifiedDocumentsMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(verifiedDocuments))
            )
            .andExpect(status().isBadRequest());

        List<VerifiedDocuments> verifiedDocumentsList = verifiedDocumentsRepository.findAll();
        assertThat(verifiedDocumentsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllVerifiedDocuments() throws Exception {
        // Initialize the database
        verifiedDocuments.setId(UUID.randomUUID().toString());
        verifiedDocumentsRepository.saveAndFlush(verifiedDocuments);

        // Get all the verifiedDocumentsList
        restVerifiedDocumentsMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(verifiedDocuments.getId())))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE)))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].contentId").value(hasItem(DEFAULT_CONTENT_ID)))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)))
            .andExpect(jsonPath("$.[*].lastModifiedDate").value(hasItem(DEFAULT_LAST_MODIFIED_DATE.toString())))
            .andExpect(jsonPath("$.[*].lastModifiedBy").value(hasItem(DEFAULT_LAST_MODIFIED_BY)));
    }

    @Test
    @Transactional
    void getVerifiedDocuments() throws Exception {
        // Initialize the database
        verifiedDocuments.setId(UUID.randomUUID().toString());
        verifiedDocumentsRepository.saveAndFlush(verifiedDocuments);

        // Get the verifiedDocuments
        restVerifiedDocumentsMockMvc
            .perform(get(ENTITY_API_URL_ID, verifiedDocuments.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(verifiedDocuments.getId()))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.contentId").value(DEFAULT_CONTENT_ID))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY))
            .andExpect(jsonPath("$.lastModifiedDate").value(DEFAULT_LAST_MODIFIED_DATE.toString()))
            .andExpect(jsonPath("$.lastModifiedBy").value(DEFAULT_LAST_MODIFIED_BY));
    }

    @Test
    @Transactional
    void getVerifiedDocumentsByIdFiltering() throws Exception {
        // Initialize the database
        verifiedDocumentsRepository.saveAndFlush(verifiedDocuments);

        String id = verifiedDocuments.getId();

        defaultVerifiedDocumentsShouldBeFound("id.equals=" + id);
        defaultVerifiedDocumentsShouldNotBeFound("id.notEquals=" + id);
    }

    @Test
    @Transactional
    void getAllVerifiedDocumentsByTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        verifiedDocumentsRepository.saveAndFlush(verifiedDocuments);

        // Get all the verifiedDocumentsList where type equals to DEFAULT_TYPE
        defaultVerifiedDocumentsShouldBeFound("type.equals=" + DEFAULT_TYPE);

        // Get all the verifiedDocumentsList where type equals to UPDATED_TYPE
        defaultVerifiedDocumentsShouldNotBeFound("type.equals=" + UPDATED_TYPE);
    }

    @Test
    @Transactional
    void getAllVerifiedDocumentsByTypeIsInShouldWork() throws Exception {
        // Initialize the database
        verifiedDocumentsRepository.saveAndFlush(verifiedDocuments);

        // Get all the verifiedDocumentsList where type in DEFAULT_TYPE or UPDATED_TYPE
        defaultVerifiedDocumentsShouldBeFound("type.in=" + DEFAULT_TYPE + "," + UPDATED_TYPE);

        // Get all the verifiedDocumentsList where type equals to UPDATED_TYPE
        defaultVerifiedDocumentsShouldNotBeFound("type.in=" + UPDATED_TYPE);
    }

    @Test
    @Transactional
    void getAllVerifiedDocumentsByTypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        verifiedDocumentsRepository.saveAndFlush(verifiedDocuments);

        // Get all the verifiedDocumentsList where type is not null
        defaultVerifiedDocumentsShouldBeFound("type.specified=true");

        // Get all the verifiedDocumentsList where type is null
        defaultVerifiedDocumentsShouldNotBeFound("type.specified=false");
    }

    @Test
    @Transactional
    void getAllVerifiedDocumentsByTypeContainsSomething() throws Exception {
        // Initialize the database
        verifiedDocumentsRepository.saveAndFlush(verifiedDocuments);

        // Get all the verifiedDocumentsList where type contains DEFAULT_TYPE
        defaultVerifiedDocumentsShouldBeFound("type.contains=" + DEFAULT_TYPE);

        // Get all the verifiedDocumentsList where type contains UPDATED_TYPE
        defaultVerifiedDocumentsShouldNotBeFound("type.contains=" + UPDATED_TYPE);
    }

    @Test
    @Transactional
    void getAllVerifiedDocumentsByTypeNotContainsSomething() throws Exception {
        // Initialize the database
        verifiedDocumentsRepository.saveAndFlush(verifiedDocuments);

        // Get all the verifiedDocumentsList where type does not contain DEFAULT_TYPE
        defaultVerifiedDocumentsShouldNotBeFound("type.doesNotContain=" + DEFAULT_TYPE);

        // Get all the verifiedDocumentsList where type does not contain UPDATED_TYPE
        defaultVerifiedDocumentsShouldBeFound("type.doesNotContain=" + UPDATED_TYPE);
    }

    @Test
    @Transactional
    void getAllVerifiedDocumentsByNameIsEqualToSomething() throws Exception {
        // Initialize the database
        verifiedDocumentsRepository.saveAndFlush(verifiedDocuments);

        // Get all the verifiedDocumentsList where name equals to DEFAULT_NAME
        defaultVerifiedDocumentsShouldBeFound("name.equals=" + DEFAULT_NAME);

        // Get all the verifiedDocumentsList where name equals to UPDATED_NAME
        defaultVerifiedDocumentsShouldNotBeFound("name.equals=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    void getAllVerifiedDocumentsByNameIsInShouldWork() throws Exception {
        // Initialize the database
        verifiedDocumentsRepository.saveAndFlush(verifiedDocuments);

        // Get all the verifiedDocumentsList where name in DEFAULT_NAME or UPDATED_NAME
        defaultVerifiedDocumentsShouldBeFound("name.in=" + DEFAULT_NAME + "," + UPDATED_NAME);

        // Get all the verifiedDocumentsList where name equals to UPDATED_NAME
        defaultVerifiedDocumentsShouldNotBeFound("name.in=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    void getAllVerifiedDocumentsByNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        verifiedDocumentsRepository.saveAndFlush(verifiedDocuments);

        // Get all the verifiedDocumentsList where name is not null
        defaultVerifiedDocumentsShouldBeFound("name.specified=true");

        // Get all the verifiedDocumentsList where name is null
        defaultVerifiedDocumentsShouldNotBeFound("name.specified=false");
    }

    @Test
    @Transactional
    void getAllVerifiedDocumentsByNameContainsSomething() throws Exception {
        // Initialize the database
        verifiedDocumentsRepository.saveAndFlush(verifiedDocuments);

        // Get all the verifiedDocumentsList where name contains DEFAULT_NAME
        defaultVerifiedDocumentsShouldBeFound("name.contains=" + DEFAULT_NAME);

        // Get all the verifiedDocumentsList where name contains UPDATED_NAME
        defaultVerifiedDocumentsShouldNotBeFound("name.contains=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    void getAllVerifiedDocumentsByNameNotContainsSomething() throws Exception {
        // Initialize the database
        verifiedDocumentsRepository.saveAndFlush(verifiedDocuments);

        // Get all the verifiedDocumentsList where name does not contain DEFAULT_NAME
        defaultVerifiedDocumentsShouldNotBeFound("name.doesNotContain=" + DEFAULT_NAME);

        // Get all the verifiedDocumentsList where name does not contain UPDATED_NAME
        defaultVerifiedDocumentsShouldBeFound("name.doesNotContain=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    void getAllVerifiedDocumentsByStatusIsEqualToSomething() throws Exception {
        // Initialize the database
        verifiedDocumentsRepository.saveAndFlush(verifiedDocuments);

        // Get all the verifiedDocumentsList where status equals to DEFAULT_STATUS
        defaultVerifiedDocumentsShouldBeFound("status.equals=" + DEFAULT_STATUS);

        // Get all the verifiedDocumentsList where status equals to UPDATED_STATUS
        defaultVerifiedDocumentsShouldNotBeFound("status.equals=" + UPDATED_STATUS);
    }

    @Test
    @Transactional
    void getAllVerifiedDocumentsByStatusIsInShouldWork() throws Exception {
        // Initialize the database
        verifiedDocumentsRepository.saveAndFlush(verifiedDocuments);

        // Get all the verifiedDocumentsList where status in DEFAULT_STATUS or UPDATED_STATUS
        defaultVerifiedDocumentsShouldBeFound("status.in=" + DEFAULT_STATUS + "," + UPDATED_STATUS);

        // Get all the verifiedDocumentsList where status equals to UPDATED_STATUS
        defaultVerifiedDocumentsShouldNotBeFound("status.in=" + UPDATED_STATUS);
    }

    @Test
    @Transactional
    void getAllVerifiedDocumentsByStatusIsNullOrNotNull() throws Exception {
        // Initialize the database
        verifiedDocumentsRepository.saveAndFlush(verifiedDocuments);

        // Get all the verifiedDocumentsList where status is not null
        defaultVerifiedDocumentsShouldBeFound("status.specified=true");

        // Get all the verifiedDocumentsList where status is null
        defaultVerifiedDocumentsShouldNotBeFound("status.specified=false");
    }

    @Test
    @Transactional
    void getAllVerifiedDocumentsByStatusContainsSomething() throws Exception {
        // Initialize the database
        verifiedDocumentsRepository.saveAndFlush(verifiedDocuments);

        // Get all the verifiedDocumentsList where status contains DEFAULT_STATUS
        defaultVerifiedDocumentsShouldBeFound("status.contains=" + DEFAULT_STATUS);

        // Get all the verifiedDocumentsList where status contains UPDATED_STATUS
        defaultVerifiedDocumentsShouldNotBeFound("status.contains=" + UPDATED_STATUS);
    }

    @Test
    @Transactional
    void getAllVerifiedDocumentsByStatusNotContainsSomething() throws Exception {
        // Initialize the database
        verifiedDocumentsRepository.saveAndFlush(verifiedDocuments);

        // Get all the verifiedDocumentsList where status does not contain DEFAULT_STATUS
        defaultVerifiedDocumentsShouldNotBeFound("status.doesNotContain=" + DEFAULT_STATUS);

        // Get all the verifiedDocumentsList where status does not contain UPDATED_STATUS
        defaultVerifiedDocumentsShouldBeFound("status.doesNotContain=" + UPDATED_STATUS);
    }

    @Test
    @Transactional
    void getAllVerifiedDocumentsByContentIdIsEqualToSomething() throws Exception {
        // Initialize the database
        verifiedDocumentsRepository.saveAndFlush(verifiedDocuments);

        // Get all the verifiedDocumentsList where contentId equals to DEFAULT_CONTENT_ID
        defaultVerifiedDocumentsShouldBeFound("contentId.equals=" + DEFAULT_CONTENT_ID);

        // Get all the verifiedDocumentsList where contentId equals to UPDATED_CONTENT_ID
        defaultVerifiedDocumentsShouldNotBeFound("contentId.equals=" + UPDATED_CONTENT_ID);
    }

    @Test
    @Transactional
    void getAllVerifiedDocumentsByContentIdIsInShouldWork() throws Exception {
        // Initialize the database
        verifiedDocumentsRepository.saveAndFlush(verifiedDocuments);

        // Get all the verifiedDocumentsList where contentId in DEFAULT_CONTENT_ID or UPDATED_CONTENT_ID
        defaultVerifiedDocumentsShouldBeFound("contentId.in=" + DEFAULT_CONTENT_ID + "," + UPDATED_CONTENT_ID);

        // Get all the verifiedDocumentsList where contentId equals to UPDATED_CONTENT_ID
        defaultVerifiedDocumentsShouldNotBeFound("contentId.in=" + UPDATED_CONTENT_ID);
    }

    @Test
    @Transactional
    void getAllVerifiedDocumentsByContentIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        verifiedDocumentsRepository.saveAndFlush(verifiedDocuments);

        // Get all the verifiedDocumentsList where contentId is not null
        defaultVerifiedDocumentsShouldBeFound("contentId.specified=true");

        // Get all the verifiedDocumentsList where contentId is null
        defaultVerifiedDocumentsShouldNotBeFound("contentId.specified=false");
    }

    @Test
    @Transactional
    void getAllVerifiedDocumentsByContentIdContainsSomething() throws Exception {
        // Initialize the database
        verifiedDocumentsRepository.saveAndFlush(verifiedDocuments);

        // Get all the verifiedDocumentsList where contentId contains DEFAULT_CONTENT_ID
        defaultVerifiedDocumentsShouldBeFound("contentId.contains=" + DEFAULT_CONTENT_ID);

        // Get all the verifiedDocumentsList where contentId contains UPDATED_CONTENT_ID
        defaultVerifiedDocumentsShouldNotBeFound("contentId.contains=" + UPDATED_CONTENT_ID);
    }

    @Test
    @Transactional
    void getAllVerifiedDocumentsByContentIdNotContainsSomething() throws Exception {
        // Initialize the database
        verifiedDocumentsRepository.saveAndFlush(verifiedDocuments);

        // Get all the verifiedDocumentsList where contentId does not contain DEFAULT_CONTENT_ID
        defaultVerifiedDocumentsShouldNotBeFound("contentId.doesNotContain=" + DEFAULT_CONTENT_ID);

        // Get all the verifiedDocumentsList where contentId does not contain UPDATED_CONTENT_ID
        defaultVerifiedDocumentsShouldBeFound("contentId.doesNotContain=" + UPDATED_CONTENT_ID);
    }

    @Test
    @Transactional
    void getAllVerifiedDocumentsByCreatedDateIsEqualToSomething() throws Exception {
        // Initialize the database
        verifiedDocumentsRepository.saveAndFlush(verifiedDocuments);

        // Get all the verifiedDocumentsList where createdDate equals to DEFAULT_CREATED_DATE
        defaultVerifiedDocumentsShouldBeFound("createdDate.equals=" + DEFAULT_CREATED_DATE);

        // Get all the verifiedDocumentsList where createdDate equals to UPDATED_CREATED_DATE
        defaultVerifiedDocumentsShouldNotBeFound("createdDate.equals=" + UPDATED_CREATED_DATE);
    }

    @Test
    @Transactional
    void getAllVerifiedDocumentsByCreatedDateIsInShouldWork() throws Exception {
        // Initialize the database
        verifiedDocumentsRepository.saveAndFlush(verifiedDocuments);

        // Get all the verifiedDocumentsList where createdDate in DEFAULT_CREATED_DATE or UPDATED_CREATED_DATE
        defaultVerifiedDocumentsShouldBeFound("createdDate.in=" + DEFAULT_CREATED_DATE + "," + UPDATED_CREATED_DATE);

        // Get all the verifiedDocumentsList where createdDate equals to UPDATED_CREATED_DATE
        defaultVerifiedDocumentsShouldNotBeFound("createdDate.in=" + UPDATED_CREATED_DATE);
    }

    @Test
    @Transactional
    void getAllVerifiedDocumentsByCreatedDateIsNullOrNotNull() throws Exception {
        // Initialize the database
        verifiedDocumentsRepository.saveAndFlush(verifiedDocuments);

        // Get all the verifiedDocumentsList where createdDate is not null
        defaultVerifiedDocumentsShouldBeFound("createdDate.specified=true");

        // Get all the verifiedDocumentsList where createdDate is null
        defaultVerifiedDocumentsShouldNotBeFound("createdDate.specified=false");
    }

    @Test
    @Transactional
    void getAllVerifiedDocumentsByCreatedByIsEqualToSomething() throws Exception {
        // Initialize the database
        verifiedDocumentsRepository.saveAndFlush(verifiedDocuments);

        // Get all the verifiedDocumentsList where createdBy equals to DEFAULT_CREATED_BY
        defaultVerifiedDocumentsShouldBeFound("createdBy.equals=" + DEFAULT_CREATED_BY);

        // Get all the verifiedDocumentsList where createdBy equals to UPDATED_CREATED_BY
        defaultVerifiedDocumentsShouldNotBeFound("createdBy.equals=" + UPDATED_CREATED_BY);
    }

    @Test
    @Transactional
    void getAllVerifiedDocumentsByCreatedByIsInShouldWork() throws Exception {
        // Initialize the database
        verifiedDocumentsRepository.saveAndFlush(verifiedDocuments);

        // Get all the verifiedDocumentsList where createdBy in DEFAULT_CREATED_BY or UPDATED_CREATED_BY
        defaultVerifiedDocumentsShouldBeFound("createdBy.in=" + DEFAULT_CREATED_BY + "," + UPDATED_CREATED_BY);

        // Get all the verifiedDocumentsList where createdBy equals to UPDATED_CREATED_BY
        defaultVerifiedDocumentsShouldNotBeFound("createdBy.in=" + UPDATED_CREATED_BY);
    }

    @Test
    @Transactional
    void getAllVerifiedDocumentsByCreatedByIsNullOrNotNull() throws Exception {
        // Initialize the database
        verifiedDocumentsRepository.saveAndFlush(verifiedDocuments);

        // Get all the verifiedDocumentsList where createdBy is not null
        defaultVerifiedDocumentsShouldBeFound("createdBy.specified=true");

        // Get all the verifiedDocumentsList where createdBy is null
        defaultVerifiedDocumentsShouldNotBeFound("createdBy.specified=false");
    }

    @Test
    @Transactional
    void getAllVerifiedDocumentsByCreatedByContainsSomething() throws Exception {
        // Initialize the database
        verifiedDocumentsRepository.saveAndFlush(verifiedDocuments);

        // Get all the verifiedDocumentsList where createdBy contains DEFAULT_CREATED_BY
        defaultVerifiedDocumentsShouldBeFound("createdBy.contains=" + DEFAULT_CREATED_BY);

        // Get all the verifiedDocumentsList where createdBy contains UPDATED_CREATED_BY
        defaultVerifiedDocumentsShouldNotBeFound("createdBy.contains=" + UPDATED_CREATED_BY);
    }

    @Test
    @Transactional
    void getAllVerifiedDocumentsByCreatedByNotContainsSomething() throws Exception {
        // Initialize the database
        verifiedDocumentsRepository.saveAndFlush(verifiedDocuments);

        // Get all the verifiedDocumentsList where createdBy does not contain DEFAULT_CREATED_BY
        defaultVerifiedDocumentsShouldNotBeFound("createdBy.doesNotContain=" + DEFAULT_CREATED_BY);

        // Get all the verifiedDocumentsList where createdBy does not contain UPDATED_CREATED_BY
        defaultVerifiedDocumentsShouldBeFound("createdBy.doesNotContain=" + UPDATED_CREATED_BY);
    }

    @Test
    @Transactional
    void getAllVerifiedDocumentsByLastModifiedDateIsEqualToSomething() throws Exception {
        // Initialize the database
        verifiedDocumentsRepository.saveAndFlush(verifiedDocuments);

        // Get all the verifiedDocumentsList where lastModifiedDate equals to DEFAULT_LAST_MODIFIED_DATE
        defaultVerifiedDocumentsShouldBeFound("lastModifiedDate.equals=" + DEFAULT_LAST_MODIFIED_DATE);

        // Get all the verifiedDocumentsList where lastModifiedDate equals to UPDATED_LAST_MODIFIED_DATE
        defaultVerifiedDocumentsShouldNotBeFound("lastModifiedDate.equals=" + UPDATED_LAST_MODIFIED_DATE);
    }

    @Test
    @Transactional
    void getAllVerifiedDocumentsByLastModifiedDateIsInShouldWork() throws Exception {
        // Initialize the database
        verifiedDocumentsRepository.saveAndFlush(verifiedDocuments);

        // Get all the verifiedDocumentsList where lastModifiedDate in DEFAULT_LAST_MODIFIED_DATE or UPDATED_LAST_MODIFIED_DATE
        defaultVerifiedDocumentsShouldBeFound("lastModifiedDate.in=" + DEFAULT_LAST_MODIFIED_DATE + "," + UPDATED_LAST_MODIFIED_DATE);

        // Get all the verifiedDocumentsList where lastModifiedDate equals to UPDATED_LAST_MODIFIED_DATE
        defaultVerifiedDocumentsShouldNotBeFound("lastModifiedDate.in=" + UPDATED_LAST_MODIFIED_DATE);
    }

    @Test
    @Transactional
    void getAllVerifiedDocumentsByLastModifiedDateIsNullOrNotNull() throws Exception {
        // Initialize the database
        verifiedDocumentsRepository.saveAndFlush(verifiedDocuments);

        // Get all the verifiedDocumentsList where lastModifiedDate is not null
        defaultVerifiedDocumentsShouldBeFound("lastModifiedDate.specified=true");

        // Get all the verifiedDocumentsList where lastModifiedDate is null
        defaultVerifiedDocumentsShouldNotBeFound("lastModifiedDate.specified=false");
    }

    @Test
    @Transactional
    void getAllVerifiedDocumentsByLastModifiedByIsEqualToSomething() throws Exception {
        // Initialize the database
        verifiedDocumentsRepository.saveAndFlush(verifiedDocuments);

        // Get all the verifiedDocumentsList where lastModifiedBy equals to DEFAULT_LAST_MODIFIED_BY
        defaultVerifiedDocumentsShouldBeFound("lastModifiedBy.equals=" + DEFAULT_LAST_MODIFIED_BY);

        // Get all the verifiedDocumentsList where lastModifiedBy equals to UPDATED_LAST_MODIFIED_BY
        defaultVerifiedDocumentsShouldNotBeFound("lastModifiedBy.equals=" + UPDATED_LAST_MODIFIED_BY);
    }

    @Test
    @Transactional
    void getAllVerifiedDocumentsByLastModifiedByIsInShouldWork() throws Exception {
        // Initialize the database
        verifiedDocumentsRepository.saveAndFlush(verifiedDocuments);

        // Get all the verifiedDocumentsList where lastModifiedBy in DEFAULT_LAST_MODIFIED_BY or UPDATED_LAST_MODIFIED_BY
        defaultVerifiedDocumentsShouldBeFound("lastModifiedBy.in=" + DEFAULT_LAST_MODIFIED_BY + "," + UPDATED_LAST_MODIFIED_BY);

        // Get all the verifiedDocumentsList where lastModifiedBy equals to UPDATED_LAST_MODIFIED_BY
        defaultVerifiedDocumentsShouldNotBeFound("lastModifiedBy.in=" + UPDATED_LAST_MODIFIED_BY);
    }

    @Test
    @Transactional
    void getAllVerifiedDocumentsByLastModifiedByIsNullOrNotNull() throws Exception {
        // Initialize the database
        verifiedDocumentsRepository.saveAndFlush(verifiedDocuments);

        // Get all the verifiedDocumentsList where lastModifiedBy is not null
        defaultVerifiedDocumentsShouldBeFound("lastModifiedBy.specified=true");

        // Get all the verifiedDocumentsList where lastModifiedBy is null
        defaultVerifiedDocumentsShouldNotBeFound("lastModifiedBy.specified=false");
    }

    @Test
    @Transactional
    void getAllVerifiedDocumentsByLastModifiedByContainsSomething() throws Exception {
        // Initialize the database
        verifiedDocumentsRepository.saveAndFlush(verifiedDocuments);

        // Get all the verifiedDocumentsList where lastModifiedBy contains DEFAULT_LAST_MODIFIED_BY
        defaultVerifiedDocumentsShouldBeFound("lastModifiedBy.contains=" + DEFAULT_LAST_MODIFIED_BY);

        // Get all the verifiedDocumentsList where lastModifiedBy contains UPDATED_LAST_MODIFIED_BY
        defaultVerifiedDocumentsShouldNotBeFound("lastModifiedBy.contains=" + UPDATED_LAST_MODIFIED_BY);
    }

    @Test
    @Transactional
    void getAllVerifiedDocumentsByLastModifiedByNotContainsSomething() throws Exception {
        // Initialize the database
        verifiedDocumentsRepository.saveAndFlush(verifiedDocuments);

        // Get all the verifiedDocumentsList where lastModifiedBy does not contain DEFAULT_LAST_MODIFIED_BY
        defaultVerifiedDocumentsShouldNotBeFound("lastModifiedBy.doesNotContain=" + DEFAULT_LAST_MODIFIED_BY);

        // Get all the verifiedDocumentsList where lastModifiedBy does not contain UPDATED_LAST_MODIFIED_BY
        defaultVerifiedDocumentsShouldBeFound("lastModifiedBy.doesNotContain=" + UPDATED_LAST_MODIFIED_BY);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultVerifiedDocumentsShouldBeFound(String filter) throws Exception {
        restVerifiedDocumentsMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(verifiedDocuments.getId())))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE)))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].contentId").value(hasItem(DEFAULT_CONTENT_ID)))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)))
            .andExpect(jsonPath("$.[*].lastModifiedDate").value(hasItem(DEFAULT_LAST_MODIFIED_DATE.toString())))
            .andExpect(jsonPath("$.[*].lastModifiedBy").value(hasItem(DEFAULT_LAST_MODIFIED_BY)));

        // Check, that the count call also returns 1
        restVerifiedDocumentsMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultVerifiedDocumentsShouldNotBeFound(String filter) throws Exception {
        restVerifiedDocumentsMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restVerifiedDocumentsMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    void getNonExistingVerifiedDocuments() throws Exception {
        // Get the verifiedDocuments
        restVerifiedDocumentsMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingVerifiedDocuments() throws Exception {
        // Initialize the database
        verifiedDocuments.setId(UUID.randomUUID().toString());
        verifiedDocumentsRepository.saveAndFlush(verifiedDocuments);

        int databaseSizeBeforeUpdate = verifiedDocumentsRepository.findAll().size();

        // Update the verifiedDocuments
        VerifiedDocuments updatedVerifiedDocuments = verifiedDocumentsRepository.findById(verifiedDocuments.getId()).get();
        // Disconnect from session so that the updates on updatedVerifiedDocuments are not directly saved in db
        em.detach(updatedVerifiedDocuments);
        updatedVerifiedDocuments
            .type(UPDATED_TYPE)
            .name(UPDATED_NAME)
            .status(UPDATED_STATUS)
            .contentId(UPDATED_CONTENT_ID);
//            .createdDate(UPDATED_CREATED_DATE)
//            .createdBy(UPDATED_CREATED_BY)
//            .lastModifiedDate(UPDATED_LAST_MODIFIED_DATE)
//            .lastModifiedBy(UPDATED_LAST_MODIFIED_BY);

        restVerifiedDocumentsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedVerifiedDocuments.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedVerifiedDocuments))
            )
            .andExpect(status().isOk());

        // Validate the VerifiedDocuments in the database
        List<VerifiedDocuments> verifiedDocumentsList = verifiedDocumentsRepository.findAll();
        assertThat(verifiedDocumentsList).hasSize(databaseSizeBeforeUpdate);
        VerifiedDocuments testVerifiedDocuments = verifiedDocumentsList.get(verifiedDocumentsList.size() - 1);
        assertThat(testVerifiedDocuments.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testVerifiedDocuments.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testVerifiedDocuments.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testVerifiedDocuments.getContentId()).isEqualTo(UPDATED_CONTENT_ID);
        assertThat(testVerifiedDocuments.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testVerifiedDocuments.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testVerifiedDocuments.getLastModifiedDate()).isEqualTo(UPDATED_LAST_MODIFIED_DATE);
        assertThat(testVerifiedDocuments.getLastModifiedBy()).isEqualTo(UPDATED_LAST_MODIFIED_BY);
    }

    @Test
    @Transactional
    void putNonExistingVerifiedDocuments() throws Exception {
        int databaseSizeBeforeUpdate = verifiedDocumentsRepository.findAll().size();
        verifiedDocuments.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restVerifiedDocumentsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, verifiedDocuments.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(verifiedDocuments))
            )
            .andExpect(status().isBadRequest());

        // Validate the VerifiedDocuments in the database
        List<VerifiedDocuments> verifiedDocumentsList = verifiedDocumentsRepository.findAll();
        assertThat(verifiedDocumentsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchVerifiedDocuments() throws Exception {
        int databaseSizeBeforeUpdate = verifiedDocumentsRepository.findAll().size();
        verifiedDocuments.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restVerifiedDocumentsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(verifiedDocuments))
            )
            .andExpect(status().isBadRequest());

        // Validate the VerifiedDocuments in the database
        List<VerifiedDocuments> verifiedDocumentsList = verifiedDocumentsRepository.findAll();
        assertThat(verifiedDocumentsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamVerifiedDocuments() throws Exception {
        int databaseSizeBeforeUpdate = verifiedDocumentsRepository.findAll().size();
        verifiedDocuments.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restVerifiedDocumentsMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(verifiedDocuments))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the VerifiedDocuments in the database
        List<VerifiedDocuments> verifiedDocumentsList = verifiedDocumentsRepository.findAll();
        assertThat(verifiedDocumentsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateVerifiedDocumentsWithPatch() throws Exception {
        // Initialize the database
        verifiedDocuments.setId(UUID.randomUUID().toString());
        verifiedDocumentsRepository.saveAndFlush(verifiedDocuments);

        int databaseSizeBeforeUpdate = verifiedDocumentsRepository.findAll().size();

        // Update the verifiedDocuments using partial update
        VerifiedDocuments partialUpdatedVerifiedDocuments = new VerifiedDocuments();
        partialUpdatedVerifiedDocuments.setId(verifiedDocuments.getId());

        partialUpdatedVerifiedDocuments
            .name(UPDATED_NAME)
            .status(UPDATED_STATUS)
            .contentId(UPDATED_CONTENT_ID);
//            .createdBy(UPDATED_CREATED_BY)
//            .lastModifiedBy(UPDATED_LAST_MODIFIED_BY);

        restVerifiedDocumentsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedVerifiedDocuments.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedVerifiedDocuments))
            )
            .andExpect(status().isOk());

        // Validate the VerifiedDocuments in the database
        List<VerifiedDocuments> verifiedDocumentsList = verifiedDocumentsRepository.findAll();
        assertThat(verifiedDocumentsList).hasSize(databaseSizeBeforeUpdate);
        VerifiedDocuments testVerifiedDocuments = verifiedDocumentsList.get(verifiedDocumentsList.size() - 1);
        assertThat(testVerifiedDocuments.getType()).isEqualTo(DEFAULT_TYPE);
        assertThat(testVerifiedDocuments.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testVerifiedDocuments.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testVerifiedDocuments.getContentId()).isEqualTo(UPDATED_CONTENT_ID);
        assertThat(testVerifiedDocuments.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testVerifiedDocuments.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testVerifiedDocuments.getLastModifiedDate()).isEqualTo(DEFAULT_LAST_MODIFIED_DATE);
        assertThat(testVerifiedDocuments.getLastModifiedBy()).isEqualTo(UPDATED_LAST_MODIFIED_BY);
    }

    @Test
    @Transactional
    void fullUpdateVerifiedDocumentsWithPatch() throws Exception {
        // Initialize the database
        verifiedDocuments.setId(UUID.randomUUID().toString());
        verifiedDocumentsRepository.saveAndFlush(verifiedDocuments);

        int databaseSizeBeforeUpdate = verifiedDocumentsRepository.findAll().size();

        // Update the verifiedDocuments using partial update
        VerifiedDocuments partialUpdatedVerifiedDocuments = new VerifiedDocuments();
        partialUpdatedVerifiedDocuments.setId(verifiedDocuments.getId());

        partialUpdatedVerifiedDocuments
            .type(UPDATED_TYPE)
            .name(UPDATED_NAME)
            .status(UPDATED_STATUS)
            .contentId(UPDATED_CONTENT_ID);
//            .createdDate(UPDATED_CREATED_DATE)
//            .createdBy(UPDATED_CREATED_BY)
//            .lastModifiedDate(UPDATED_LAST_MODIFIED_DATE)
//            .lastModifiedBy(UPDATED_LAST_MODIFIED_BY);

        restVerifiedDocumentsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedVerifiedDocuments.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedVerifiedDocuments))
            )
            .andExpect(status().isOk());

        // Validate the VerifiedDocuments in the database
        List<VerifiedDocuments> verifiedDocumentsList = verifiedDocumentsRepository.findAll();
        assertThat(verifiedDocumentsList).hasSize(databaseSizeBeforeUpdate);
        VerifiedDocuments testVerifiedDocuments = verifiedDocumentsList.get(verifiedDocumentsList.size() - 1);
        assertThat(testVerifiedDocuments.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testVerifiedDocuments.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testVerifiedDocuments.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testVerifiedDocuments.getContentId()).isEqualTo(UPDATED_CONTENT_ID);
        assertThat(testVerifiedDocuments.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testVerifiedDocuments.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testVerifiedDocuments.getLastModifiedDate()).isEqualTo(UPDATED_LAST_MODIFIED_DATE);
        assertThat(testVerifiedDocuments.getLastModifiedBy()).isEqualTo(UPDATED_LAST_MODIFIED_BY);
    }

    @Test
    @Transactional
    void patchNonExistingVerifiedDocuments() throws Exception {
        int databaseSizeBeforeUpdate = verifiedDocumentsRepository.findAll().size();
        verifiedDocuments.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restVerifiedDocumentsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, verifiedDocuments.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(verifiedDocuments))
            )
            .andExpect(status().isBadRequest());

        // Validate the VerifiedDocuments in the database
        List<VerifiedDocuments> verifiedDocumentsList = verifiedDocumentsRepository.findAll();
        assertThat(verifiedDocumentsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchVerifiedDocuments() throws Exception {
        int databaseSizeBeforeUpdate = verifiedDocumentsRepository.findAll().size();
        verifiedDocuments.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restVerifiedDocumentsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(verifiedDocuments))
            )
            .andExpect(status().isBadRequest());

        // Validate the VerifiedDocuments in the database
        List<VerifiedDocuments> verifiedDocumentsList = verifiedDocumentsRepository.findAll();
        assertThat(verifiedDocumentsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamVerifiedDocuments() throws Exception {
        int databaseSizeBeforeUpdate = verifiedDocumentsRepository.findAll().size();
        verifiedDocuments.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restVerifiedDocumentsMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(verifiedDocuments))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the VerifiedDocuments in the database
        List<VerifiedDocuments> verifiedDocumentsList = verifiedDocumentsRepository.findAll();
        assertThat(verifiedDocumentsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteVerifiedDocuments() throws Exception {
        // Initialize the database
        verifiedDocuments.setId(UUID.randomUUID().toString());
        verifiedDocumentsRepository.saveAndFlush(verifiedDocuments);

        int databaseSizeBeforeDelete = verifiedDocumentsRepository.findAll().size();

        // Delete the verifiedDocuments
        restVerifiedDocumentsMockMvc
            .perform(delete(ENTITY_API_URL_ID, verifiedDocuments.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<VerifiedDocuments> verifiedDocumentsList = verifiedDocumentsRepository.findAll();
        assertThat(verifiedDocumentsList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
