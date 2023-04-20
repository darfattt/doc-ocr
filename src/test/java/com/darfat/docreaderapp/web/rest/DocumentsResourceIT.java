package com.darfat.docreaderapp.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.darfat.docreaderapp.IntegrationTest;
import com.darfat.docreaderapp.domain.Documents;
import com.darfat.docreaderapp.repository.DocumentsRepository;
import com.darfat.docreaderapp.service.criteria.DocumentsCriteria;
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
 * Integration tests for the {@link DocumentsResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class DocumentsResourceIT {

    private static final String DEFAULT_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_STATUS = "BBBBBBBBBB";

    private static final Instant DEFAULT_CREATED_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_CREATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY = "BBBBBBBBBB";

    private static final Instant DEFAULT_LAST_MODIFIED_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_LAST_MODIFIED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_LAST_MODIFIED_BY = "AAAAAAAAAA";
    private static final String UPDATED_LAST_MODIFIED_BY = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/documents";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private DocumentsRepository documentsRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restDocumentsMockMvc;

    private Documents documents;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Documents createEntity(EntityManager em) {
        Documents documents = new Documents()
            .type(DEFAULT_TYPE)
            .name(DEFAULT_NAME)
            .status(DEFAULT_STATUS);
//            .createdDate(DEFAULT_CREATED_DATE)
//            .createdBy(DEFAULT_CREATED_BY)
//            .lastModifiedDate(DEFAULT_LAST_MODIFIED_DATE)
//            .lastModifiedBy(DEFAULT_LAST_MODIFIED_BY);
        return documents;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Documents createUpdatedEntity(EntityManager em) {
        Documents documents = new Documents()
            .type(UPDATED_TYPE)
            .name(UPDATED_NAME)
            .status(UPDATED_STATUS);
//            .createdDate(UPDATED_CREATED_DATE)
//            .createdBy(UPDATED_CREATED_BY)
//            .lastModifiedDate(UPDATED_LAST_MODIFIED_DATE)
//            .lastModifiedBy(UPDATED_LAST_MODIFIED_BY);
        return documents;
    }

    @BeforeEach
    public void initTest() {
        documents = createEntity(em);
    }

    @Test
    @Transactional
    void createDocuments() throws Exception {
        int databaseSizeBeforeCreate = documentsRepository.findAll().size();
        // Create the Documents
        restDocumentsMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(documents)))
            .andExpect(status().isCreated());

        // Validate the Documents in the database
        List<Documents> documentsList = documentsRepository.findAll();
        assertThat(documentsList).hasSize(databaseSizeBeforeCreate + 1);
        Documents testDocuments = documentsList.get(documentsList.size() - 1);
        assertThat(testDocuments.getType()).isEqualTo(DEFAULT_TYPE);
        assertThat(testDocuments.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testDocuments.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testDocuments.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testDocuments.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testDocuments.getLastModifiedDate()).isEqualTo(DEFAULT_LAST_MODIFIED_DATE);
        assertThat(testDocuments.getLastModifiedBy()).isEqualTo(DEFAULT_LAST_MODIFIED_BY);
    }

    @Test
    @Transactional
    void createDocumentsWithExistingId() throws Exception {
        // Create the Documents with an existing ID
        documents.setId("existing_id");

        int databaseSizeBeforeCreate = documentsRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restDocumentsMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(documents)))
            .andExpect(status().isBadRequest());

        // Validate the Documents in the database
        List<Documents> documentsList = documentsRepository.findAll();
        assertThat(documentsList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = documentsRepository.findAll().size();
        // set the field null
        documents.setType(null);

        // Create the Documents, which fails.

        restDocumentsMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(documents)))
            .andExpect(status().isBadRequest());

        List<Documents> documentsList = documentsRepository.findAll();
        assertThat(documentsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = documentsRepository.findAll().size();
        // set the field null
        documents.setName(null);

        // Create the Documents, which fails.

        restDocumentsMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(documents)))
            .andExpect(status().isBadRequest());

        List<Documents> documentsList = documentsRepository.findAll();
        assertThat(documentsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkStatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = documentsRepository.findAll().size();
        // set the field null
        documents.setStatus(null);

        // Create the Documents, which fails.

        restDocumentsMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(documents)))
            .andExpect(status().isBadRequest());

        List<Documents> documentsList = documentsRepository.findAll();
        assertThat(documentsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCreatedDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = documentsRepository.findAll().size();
        // set the field null
        documents.setCreatedDate(null);

        // Create the Documents, which fails.

        restDocumentsMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(documents)))
            .andExpect(status().isBadRequest());

        List<Documents> documentsList = documentsRepository.findAll();
        assertThat(documentsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllDocuments() throws Exception {
        // Initialize the database
        documents.setId(UUID.randomUUID().toString());
        documentsRepository.saveAndFlush(documents);

        // Get all the documentsList
        restDocumentsMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(documents.getId())))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE)))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)))
            .andExpect(jsonPath("$.[*].lastModifiedDate").value(hasItem(DEFAULT_LAST_MODIFIED_DATE.toString())))
            .andExpect(jsonPath("$.[*].lastModifiedBy").value(hasItem(DEFAULT_LAST_MODIFIED_BY)));
    }

    @Test
    @Transactional
    void getDocuments() throws Exception {
        // Initialize the database
        documents.setId(UUID.randomUUID().toString());
        documentsRepository.saveAndFlush(documents);

        // Get the documents
        restDocumentsMockMvc
            .perform(get(ENTITY_API_URL_ID, documents.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(documents.getId()))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY))
            .andExpect(jsonPath("$.lastModifiedDate").value(DEFAULT_LAST_MODIFIED_DATE.toString()))
            .andExpect(jsonPath("$.lastModifiedBy").value(DEFAULT_LAST_MODIFIED_BY));
    }

    @Test
    @Transactional
    void getDocumentsByIdFiltering() throws Exception {
        // Initialize the database
        documentsRepository.saveAndFlush(documents);

        String id = documents.getId();

        defaultDocumentsShouldBeFound("id.equals=" + id);
        defaultDocumentsShouldNotBeFound("id.notEquals=" + id);
    }

    @Test
    @Transactional
    void getAllDocumentsByTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        documentsRepository.saveAndFlush(documents);

        // Get all the documentsList where type equals to DEFAULT_TYPE
        defaultDocumentsShouldBeFound("type.equals=" + DEFAULT_TYPE);

        // Get all the documentsList where type equals to UPDATED_TYPE
        defaultDocumentsShouldNotBeFound("type.equals=" + UPDATED_TYPE);
    }

    @Test
    @Transactional
    void getAllDocumentsByTypeIsInShouldWork() throws Exception {
        // Initialize the database
        documentsRepository.saveAndFlush(documents);

        // Get all the documentsList where type in DEFAULT_TYPE or UPDATED_TYPE
        defaultDocumentsShouldBeFound("type.in=" + DEFAULT_TYPE + "," + UPDATED_TYPE);

        // Get all the documentsList where type equals to UPDATED_TYPE
        defaultDocumentsShouldNotBeFound("type.in=" + UPDATED_TYPE);
    }

    @Test
    @Transactional
    void getAllDocumentsByTypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        documentsRepository.saveAndFlush(documents);

        // Get all the documentsList where type is not null
        defaultDocumentsShouldBeFound("type.specified=true");

        // Get all the documentsList where type is null
        defaultDocumentsShouldNotBeFound("type.specified=false");
    }

    @Test
    @Transactional
    void getAllDocumentsByTypeContainsSomething() throws Exception {
        // Initialize the database
        documentsRepository.saveAndFlush(documents);

        // Get all the documentsList where type contains DEFAULT_TYPE
        defaultDocumentsShouldBeFound("type.contains=" + DEFAULT_TYPE);

        // Get all the documentsList where type contains UPDATED_TYPE
        defaultDocumentsShouldNotBeFound("type.contains=" + UPDATED_TYPE);
    }

    @Test
    @Transactional
    void getAllDocumentsByTypeNotContainsSomething() throws Exception {
        // Initialize the database
        documentsRepository.saveAndFlush(documents);

        // Get all the documentsList where type does not contain DEFAULT_TYPE
        defaultDocumentsShouldNotBeFound("type.doesNotContain=" + DEFAULT_TYPE);

        // Get all the documentsList where type does not contain UPDATED_TYPE
        defaultDocumentsShouldBeFound("type.doesNotContain=" + UPDATED_TYPE);
    }

    @Test
    @Transactional
    void getAllDocumentsByNameIsEqualToSomething() throws Exception {
        // Initialize the database
        documentsRepository.saveAndFlush(documents);

        // Get all the documentsList where name equals to DEFAULT_NAME
        defaultDocumentsShouldBeFound("name.equals=" + DEFAULT_NAME);

        // Get all the documentsList where name equals to UPDATED_NAME
        defaultDocumentsShouldNotBeFound("name.equals=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    void getAllDocumentsByNameIsInShouldWork() throws Exception {
        // Initialize the database
        documentsRepository.saveAndFlush(documents);

        // Get all the documentsList where name in DEFAULT_NAME or UPDATED_NAME
        defaultDocumentsShouldBeFound("name.in=" + DEFAULT_NAME + "," + UPDATED_NAME);

        // Get all the documentsList where name equals to UPDATED_NAME
        defaultDocumentsShouldNotBeFound("name.in=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    void getAllDocumentsByNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        documentsRepository.saveAndFlush(documents);

        // Get all the documentsList where name is not null
        defaultDocumentsShouldBeFound("name.specified=true");

        // Get all the documentsList where name is null
        defaultDocumentsShouldNotBeFound("name.specified=false");
    }

    @Test
    @Transactional
    void getAllDocumentsByNameContainsSomething() throws Exception {
        // Initialize the database
        documentsRepository.saveAndFlush(documents);

        // Get all the documentsList where name contains DEFAULT_NAME
        defaultDocumentsShouldBeFound("name.contains=" + DEFAULT_NAME);

        // Get all the documentsList where name contains UPDATED_NAME
        defaultDocumentsShouldNotBeFound("name.contains=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    void getAllDocumentsByNameNotContainsSomething() throws Exception {
        // Initialize the database
        documentsRepository.saveAndFlush(documents);

        // Get all the documentsList where name does not contain DEFAULT_NAME
        defaultDocumentsShouldNotBeFound("name.doesNotContain=" + DEFAULT_NAME);

        // Get all the documentsList where name does not contain UPDATED_NAME
        defaultDocumentsShouldBeFound("name.doesNotContain=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    void getAllDocumentsByStatusIsEqualToSomething() throws Exception {
        // Initialize the database
        documentsRepository.saveAndFlush(documents);

        // Get all the documentsList where status equals to DEFAULT_STATUS
        defaultDocumentsShouldBeFound("status.equals=" + DEFAULT_STATUS);

        // Get all the documentsList where status equals to UPDATED_STATUS
        defaultDocumentsShouldNotBeFound("status.equals=" + UPDATED_STATUS);
    }

    @Test
    @Transactional
    void getAllDocumentsByStatusIsInShouldWork() throws Exception {
        // Initialize the database
        documentsRepository.saveAndFlush(documents);

        // Get all the documentsList where status in DEFAULT_STATUS or UPDATED_STATUS
        defaultDocumentsShouldBeFound("status.in=" + DEFAULT_STATUS + "," + UPDATED_STATUS);

        // Get all the documentsList where status equals to UPDATED_STATUS
        defaultDocumentsShouldNotBeFound("status.in=" + UPDATED_STATUS);
    }

    @Test
    @Transactional
    void getAllDocumentsByStatusIsNullOrNotNull() throws Exception {
        // Initialize the database
        documentsRepository.saveAndFlush(documents);

        // Get all the documentsList where status is not null
        defaultDocumentsShouldBeFound("status.specified=true");

        // Get all the documentsList where status is null
        defaultDocumentsShouldNotBeFound("status.specified=false");
    }

    @Test
    @Transactional
    void getAllDocumentsByStatusContainsSomething() throws Exception {
        // Initialize the database
        documentsRepository.saveAndFlush(documents);

        // Get all the documentsList where status contains DEFAULT_STATUS
        defaultDocumentsShouldBeFound("status.contains=" + DEFAULT_STATUS);

        // Get all the documentsList where status contains UPDATED_STATUS
        defaultDocumentsShouldNotBeFound("status.contains=" + UPDATED_STATUS);
    }

    @Test
    @Transactional
    void getAllDocumentsByStatusNotContainsSomething() throws Exception {
        // Initialize the database
        documentsRepository.saveAndFlush(documents);

        // Get all the documentsList where status does not contain DEFAULT_STATUS
        defaultDocumentsShouldNotBeFound("status.doesNotContain=" + DEFAULT_STATUS);

        // Get all the documentsList where status does not contain UPDATED_STATUS
        defaultDocumentsShouldBeFound("status.doesNotContain=" + UPDATED_STATUS);
    }

    @Test
    @Transactional
    void getAllDocumentsByCreatedDateIsEqualToSomething() throws Exception {
        // Initialize the database
        documentsRepository.saveAndFlush(documents);

        // Get all the documentsList where createdDate equals to DEFAULT_CREATED_DATE
        defaultDocumentsShouldBeFound("createdDate.equals=" + DEFAULT_CREATED_DATE);

        // Get all the documentsList where createdDate equals to UPDATED_CREATED_DATE
        defaultDocumentsShouldNotBeFound("createdDate.equals=" + UPDATED_CREATED_DATE);
    }

    @Test
    @Transactional
    void getAllDocumentsByCreatedDateIsInShouldWork() throws Exception {
        // Initialize the database
        documentsRepository.saveAndFlush(documents);

        // Get all the documentsList where createdDate in DEFAULT_CREATED_DATE or UPDATED_CREATED_DATE
        defaultDocumentsShouldBeFound("createdDate.in=" + DEFAULT_CREATED_DATE + "," + UPDATED_CREATED_DATE);

        // Get all the documentsList where createdDate equals to UPDATED_CREATED_DATE
        defaultDocumentsShouldNotBeFound("createdDate.in=" + UPDATED_CREATED_DATE);
    }

    @Test
    @Transactional
    void getAllDocumentsByCreatedDateIsNullOrNotNull() throws Exception {
        // Initialize the database
        documentsRepository.saveAndFlush(documents);

        // Get all the documentsList where createdDate is not null
        defaultDocumentsShouldBeFound("createdDate.specified=true");

        // Get all the documentsList where createdDate is null
        defaultDocumentsShouldNotBeFound("createdDate.specified=false");
    }

    @Test
    @Transactional
    void getAllDocumentsByCreatedByIsEqualToSomething() throws Exception {
        // Initialize the database
        documentsRepository.saveAndFlush(documents);

        // Get all the documentsList where createdBy equals to DEFAULT_CREATED_BY
        defaultDocumentsShouldBeFound("createdBy.equals=" + DEFAULT_CREATED_BY);

        // Get all the documentsList where createdBy equals to UPDATED_CREATED_BY
        defaultDocumentsShouldNotBeFound("createdBy.equals=" + UPDATED_CREATED_BY);
    }

    @Test
    @Transactional
    void getAllDocumentsByCreatedByIsInShouldWork() throws Exception {
        // Initialize the database
        documentsRepository.saveAndFlush(documents);

        // Get all the documentsList where createdBy in DEFAULT_CREATED_BY or UPDATED_CREATED_BY
        defaultDocumentsShouldBeFound("createdBy.in=" + DEFAULT_CREATED_BY + "," + UPDATED_CREATED_BY);

        // Get all the documentsList where createdBy equals to UPDATED_CREATED_BY
        defaultDocumentsShouldNotBeFound("createdBy.in=" + UPDATED_CREATED_BY);
    }

    @Test
    @Transactional
    void getAllDocumentsByCreatedByIsNullOrNotNull() throws Exception {
        // Initialize the database
        documentsRepository.saveAndFlush(documents);

        // Get all the documentsList where createdBy is not null
        defaultDocumentsShouldBeFound("createdBy.specified=true");

        // Get all the documentsList where createdBy is null
        defaultDocumentsShouldNotBeFound("createdBy.specified=false");
    }

    @Test
    @Transactional
    void getAllDocumentsByCreatedByContainsSomething() throws Exception {
        // Initialize the database
        documentsRepository.saveAndFlush(documents);

        // Get all the documentsList where createdBy contains DEFAULT_CREATED_BY
        defaultDocumentsShouldBeFound("createdBy.contains=" + DEFAULT_CREATED_BY);

        // Get all the documentsList where createdBy contains UPDATED_CREATED_BY
        defaultDocumentsShouldNotBeFound("createdBy.contains=" + UPDATED_CREATED_BY);
    }

    @Test
    @Transactional
    void getAllDocumentsByCreatedByNotContainsSomething() throws Exception {
        // Initialize the database
        documentsRepository.saveAndFlush(documents);

        // Get all the documentsList where createdBy does not contain DEFAULT_CREATED_BY
        defaultDocumentsShouldNotBeFound("createdBy.doesNotContain=" + DEFAULT_CREATED_BY);

        // Get all the documentsList where createdBy does not contain UPDATED_CREATED_BY
        defaultDocumentsShouldBeFound("createdBy.doesNotContain=" + UPDATED_CREATED_BY);
    }

    @Test
    @Transactional
    void getAllDocumentsByLastModifiedDateIsEqualToSomething() throws Exception {
        // Initialize the database
        documentsRepository.saveAndFlush(documents);

        // Get all the documentsList where lastModifiedDate equals to DEFAULT_LAST_MODIFIED_DATE
        defaultDocumentsShouldBeFound("lastModifiedDate.equals=" + DEFAULT_LAST_MODIFIED_DATE);

        // Get all the documentsList where lastModifiedDate equals to UPDATED_LAST_MODIFIED_DATE
        defaultDocumentsShouldNotBeFound("lastModifiedDate.equals=" + UPDATED_LAST_MODIFIED_DATE);
    }

    @Test
    @Transactional
    void getAllDocumentsByLastModifiedDateIsInShouldWork() throws Exception {
        // Initialize the database
        documentsRepository.saveAndFlush(documents);

        // Get all the documentsList where lastModifiedDate in DEFAULT_LAST_MODIFIED_DATE or UPDATED_LAST_MODIFIED_DATE
        defaultDocumentsShouldBeFound("lastModifiedDate.in=" + DEFAULT_LAST_MODIFIED_DATE + "," + UPDATED_LAST_MODIFIED_DATE);

        // Get all the documentsList where lastModifiedDate equals to UPDATED_LAST_MODIFIED_DATE
        defaultDocumentsShouldNotBeFound("lastModifiedDate.in=" + UPDATED_LAST_MODIFIED_DATE);
    }

    @Test
    @Transactional
    void getAllDocumentsByLastModifiedDateIsNullOrNotNull() throws Exception {
        // Initialize the database
        documentsRepository.saveAndFlush(documents);

        // Get all the documentsList where lastModifiedDate is not null
        defaultDocumentsShouldBeFound("lastModifiedDate.specified=true");

        // Get all the documentsList where lastModifiedDate is null
        defaultDocumentsShouldNotBeFound("lastModifiedDate.specified=false");
    }

    @Test
    @Transactional
    void getAllDocumentsByLastModifiedByIsEqualToSomething() throws Exception {
        // Initialize the database
        documentsRepository.saveAndFlush(documents);

        // Get all the documentsList where lastModifiedBy equals to DEFAULT_LAST_MODIFIED_BY
        defaultDocumentsShouldBeFound("lastModifiedBy.equals=" + DEFAULT_LAST_MODIFIED_BY);

        // Get all the documentsList where lastModifiedBy equals to UPDATED_LAST_MODIFIED_BY
        defaultDocumentsShouldNotBeFound("lastModifiedBy.equals=" + UPDATED_LAST_MODIFIED_BY);
    }

    @Test
    @Transactional
    void getAllDocumentsByLastModifiedByIsInShouldWork() throws Exception {
        // Initialize the database
        documentsRepository.saveAndFlush(documents);

        // Get all the documentsList where lastModifiedBy in DEFAULT_LAST_MODIFIED_BY or UPDATED_LAST_MODIFIED_BY
        defaultDocumentsShouldBeFound("lastModifiedBy.in=" + DEFAULT_LAST_MODIFIED_BY + "," + UPDATED_LAST_MODIFIED_BY);

        // Get all the documentsList where lastModifiedBy equals to UPDATED_LAST_MODIFIED_BY
        defaultDocumentsShouldNotBeFound("lastModifiedBy.in=" + UPDATED_LAST_MODIFIED_BY);
    }

    @Test
    @Transactional
    void getAllDocumentsByLastModifiedByIsNullOrNotNull() throws Exception {
        // Initialize the database
        documentsRepository.saveAndFlush(documents);

        // Get all the documentsList where lastModifiedBy is not null
        defaultDocumentsShouldBeFound("lastModifiedBy.specified=true");

        // Get all the documentsList where lastModifiedBy is null
        defaultDocumentsShouldNotBeFound("lastModifiedBy.specified=false");
    }

    @Test
    @Transactional
    void getAllDocumentsByLastModifiedByContainsSomething() throws Exception {
        // Initialize the database
        documentsRepository.saveAndFlush(documents);

        // Get all the documentsList where lastModifiedBy contains DEFAULT_LAST_MODIFIED_BY
        defaultDocumentsShouldBeFound("lastModifiedBy.contains=" + DEFAULT_LAST_MODIFIED_BY);

        // Get all the documentsList where lastModifiedBy contains UPDATED_LAST_MODIFIED_BY
        defaultDocumentsShouldNotBeFound("lastModifiedBy.contains=" + UPDATED_LAST_MODIFIED_BY);
    }

    @Test
    @Transactional
    void getAllDocumentsByLastModifiedByNotContainsSomething() throws Exception {
        // Initialize the database
        documentsRepository.saveAndFlush(documents);

        // Get all the documentsList where lastModifiedBy does not contain DEFAULT_LAST_MODIFIED_BY
        defaultDocumentsShouldNotBeFound("lastModifiedBy.doesNotContain=" + DEFAULT_LAST_MODIFIED_BY);

        // Get all the documentsList where lastModifiedBy does not contain UPDATED_LAST_MODIFIED_BY
        defaultDocumentsShouldBeFound("lastModifiedBy.doesNotContain=" + UPDATED_LAST_MODIFIED_BY);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultDocumentsShouldBeFound(String filter) throws Exception {
        restDocumentsMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(documents.getId())))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE)))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)))
            .andExpect(jsonPath("$.[*].lastModifiedDate").value(hasItem(DEFAULT_LAST_MODIFIED_DATE.toString())))
            .andExpect(jsonPath("$.[*].lastModifiedBy").value(hasItem(DEFAULT_LAST_MODIFIED_BY)));

        // Check, that the count call also returns 1
        restDocumentsMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultDocumentsShouldNotBeFound(String filter) throws Exception {
        restDocumentsMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restDocumentsMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    void getNonExistingDocuments() throws Exception {
        // Get the documents
        restDocumentsMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingDocuments() throws Exception {
        // Initialize the database
        documents.setId(UUID.randomUUID().toString());
        documentsRepository.saveAndFlush(documents);

        int databaseSizeBeforeUpdate = documentsRepository.findAll().size();

        // Update the documents
        Documents updatedDocuments = documentsRepository.findById(documents.getId()).get();
        // Disconnect from session so that the updates on updatedDocuments are not directly saved in db
        em.detach(updatedDocuments);
        updatedDocuments
            .type(UPDATED_TYPE)
            .name(UPDATED_NAME)
            .status(UPDATED_STATUS);
//            .createdDate(UPDATED_CREATED_DATE)
//            .createdBy(UPDATED_CREATED_BY)
//            .lastModifiedDate(UPDATED_LAST_MODIFIED_DATE)
//            .lastModifiedBy(UPDATED_LAST_MODIFIED_BY);

        restDocumentsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedDocuments.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedDocuments))
            )
            .andExpect(status().isOk());

        // Validate the Documents in the database
        List<Documents> documentsList = documentsRepository.findAll();
        assertThat(documentsList).hasSize(databaseSizeBeforeUpdate);
        Documents testDocuments = documentsList.get(documentsList.size() - 1);
        assertThat(testDocuments.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testDocuments.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testDocuments.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testDocuments.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testDocuments.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testDocuments.getLastModifiedDate()).isEqualTo(UPDATED_LAST_MODIFIED_DATE);
        assertThat(testDocuments.getLastModifiedBy()).isEqualTo(UPDATED_LAST_MODIFIED_BY);
    }

    @Test
    @Transactional
    void putNonExistingDocuments() throws Exception {
        int databaseSizeBeforeUpdate = documentsRepository.findAll().size();
        documents.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDocumentsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, documents.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(documents))
            )
            .andExpect(status().isBadRequest());

        // Validate the Documents in the database
        List<Documents> documentsList = documentsRepository.findAll();
        assertThat(documentsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchDocuments() throws Exception {
        int databaseSizeBeforeUpdate = documentsRepository.findAll().size();
        documents.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDocumentsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(documents))
            )
            .andExpect(status().isBadRequest());

        // Validate the Documents in the database
        List<Documents> documentsList = documentsRepository.findAll();
        assertThat(documentsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamDocuments() throws Exception {
        int databaseSizeBeforeUpdate = documentsRepository.findAll().size();
        documents.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDocumentsMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(documents)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Documents in the database
        List<Documents> documentsList = documentsRepository.findAll();
        assertThat(documentsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateDocumentsWithPatch() throws Exception {
        // Initialize the database
        documents.setId(UUID.randomUUID().toString());
        documentsRepository.saveAndFlush(documents);

        int databaseSizeBeforeUpdate = documentsRepository.findAll().size();

        // Update the documents using partial update
        Documents partialUpdatedDocuments = new Documents();
        partialUpdatedDocuments.setId(documents.getId());

       // partialUpdatedDocuments
            //.createdBy(UPDATED_CREATED_BY)
            //.lastModifiedDate(UPDATED_LAST_MODIFIED_DATE)
            //.lastModifiedBy(UPDATED_LAST_MODIFIED_BY);

        restDocumentsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDocuments.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedDocuments))
            )
            .andExpect(status().isOk());

        // Validate the Documents in the database
        List<Documents> documentsList = documentsRepository.findAll();
        assertThat(documentsList).hasSize(databaseSizeBeforeUpdate);
        Documents testDocuments = documentsList.get(documentsList.size() - 1);
        assertThat(testDocuments.getType()).isEqualTo(DEFAULT_TYPE);
        assertThat(testDocuments.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testDocuments.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testDocuments.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testDocuments.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testDocuments.getLastModifiedDate()).isEqualTo(UPDATED_LAST_MODIFIED_DATE);
        assertThat(testDocuments.getLastModifiedBy()).isEqualTo(UPDATED_LAST_MODIFIED_BY);
    }

    @Test
    @Transactional
    void fullUpdateDocumentsWithPatch() throws Exception {
        // Initialize the database
        documents.setId(UUID.randomUUID().toString());
        documentsRepository.saveAndFlush(documents);

        int databaseSizeBeforeUpdate = documentsRepository.findAll().size();

        // Update the documents using partial update
        Documents partialUpdatedDocuments = new Documents();
        partialUpdatedDocuments.setId(documents.getId());

        partialUpdatedDocuments
            .type(UPDATED_TYPE)
            .name(UPDATED_NAME)
            .status(UPDATED_STATUS);
            //.createdDate(UPDATED_CREATED_DATE)
            //.createdBy(UPDATED_CREATED_BY)
            //.lastModifiedDate(UPDATED_LAST_MODIFIED_DATE)
            //.lastModifiedBy(UPDATED_LAST_MODIFIED_BY);

        restDocumentsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDocuments.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedDocuments))
            )
            .andExpect(status().isOk());

        // Validate the Documents in the database
        List<Documents> documentsList = documentsRepository.findAll();
        assertThat(documentsList).hasSize(databaseSizeBeforeUpdate);
        Documents testDocuments = documentsList.get(documentsList.size() - 1);
        assertThat(testDocuments.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testDocuments.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testDocuments.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testDocuments.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testDocuments.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testDocuments.getLastModifiedDate()).isEqualTo(UPDATED_LAST_MODIFIED_DATE);
        assertThat(testDocuments.getLastModifiedBy()).isEqualTo(UPDATED_LAST_MODIFIED_BY);
    }

    @Test
    @Transactional
    void patchNonExistingDocuments() throws Exception {
        int databaseSizeBeforeUpdate = documentsRepository.findAll().size();
        documents.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDocumentsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, documents.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(documents))
            )
            .andExpect(status().isBadRequest());

        // Validate the Documents in the database
        List<Documents> documentsList = documentsRepository.findAll();
        assertThat(documentsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchDocuments() throws Exception {
        int databaseSizeBeforeUpdate = documentsRepository.findAll().size();
        documents.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDocumentsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(documents))
            )
            .andExpect(status().isBadRequest());

        // Validate the Documents in the database
        List<Documents> documentsList = documentsRepository.findAll();
        assertThat(documentsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamDocuments() throws Exception {
        int databaseSizeBeforeUpdate = documentsRepository.findAll().size();
        documents.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDocumentsMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(documents))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the Documents in the database
        List<Documents> documentsList = documentsRepository.findAll();
        assertThat(documentsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteDocuments() throws Exception {
        // Initialize the database
        documents.setId(UUID.randomUUID().toString());
        documentsRepository.saveAndFlush(documents);

        int databaseSizeBeforeDelete = documentsRepository.findAll().size();

        // Delete the documents
        restDocumentsMockMvc
            .perform(delete(ENTITY_API_URL_ID, documents.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Documents> documentsList = documentsRepository.findAll();
        assertThat(documentsList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
