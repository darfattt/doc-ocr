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
            .createdDate(DEFAULT_CREATED_DATE)
            .createdBy(DEFAULT_CREATED_BY)
            .lastModifiedDate(DEFAULT_LAST_MODIFIED_DATE)
            .lastModifiedBy(DEFAULT_LAST_MODIFIED_BY);
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
            .createdDate(UPDATED_CREATED_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .lastModifiedDate(UPDATED_LAST_MODIFIED_DATE)
            .lastModifiedBy(UPDATED_LAST_MODIFIED_BY);
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
            .createdDate(UPDATED_CREATED_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .lastModifiedDate(UPDATED_LAST_MODIFIED_DATE)
            .lastModifiedBy(UPDATED_LAST_MODIFIED_BY);

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

        partialUpdatedFormBASTPBPP.status(UPDATED_STATUS).createdDate(UPDATED_CREATED_DATE).createdBy(UPDATED_CREATED_BY);

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
        assertThat(testFormBASTPBPP.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testFormBASTPBPP.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testFormBASTPBPP.getLastModifiedDate()).isEqualTo(DEFAULT_LAST_MODIFIED_DATE);
        assertThat(testFormBASTPBPP.getLastModifiedBy()).isEqualTo(DEFAULT_LAST_MODIFIED_BY);
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
            .createdDate(UPDATED_CREATED_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .lastModifiedDate(UPDATED_LAST_MODIFIED_DATE)
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
        assertThat(testFormBASTPBPP.getActive()).isEqualTo(UPDATED_ACTIVE);
        assertThat(testFormBASTPBPP.getRemarks()).isEqualTo(UPDATED_REMARKS);
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
