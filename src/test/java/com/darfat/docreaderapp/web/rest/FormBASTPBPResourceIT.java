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
            .createdDate(DEFAULT_CREATED_DATE)
            .createdBy(DEFAULT_CREATED_BY)
            .lastModifiedDate(DEFAULT_LAST_MODIFIED_DATE)
            .lastModifiedBy(DEFAULT_LAST_MODIFIED_BY);
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
            .createdDate(UPDATED_CREATED_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .lastModifiedDate(UPDATED_LAST_MODIFIED_DATE)
            .lastModifiedBy(UPDATED_LAST_MODIFIED_BY);
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
            .createdDate(UPDATED_CREATED_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .lastModifiedDate(UPDATED_LAST_MODIFIED_DATE)
            .lastModifiedBy(UPDATED_LAST_MODIFIED_BY);

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

        partialUpdatedFormBASTPBP.createdBy(UPDATED_CREATED_BY).lastModifiedDate(UPDATED_LAST_MODIFIED_DATE);

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
        assertThat(testFormBASTPBP.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testFormBASTPBP.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testFormBASTPBP.getLastModifiedDate()).isEqualTo(UPDATED_LAST_MODIFIED_DATE);
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
            .createdDate(UPDATED_CREATED_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .lastModifiedDate(UPDATED_LAST_MODIFIED_DATE)
            .lastModifiedBy(UPDATED_LAST_MODIFIED_BY);

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
