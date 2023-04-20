package com.darfat.docreaderapp.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.darfat.docreaderapp.IntegrationTest;
import com.darfat.docreaderapp.domain.FormSuratJalan;
import com.darfat.docreaderapp.repository.FormSuratJalanRepository;
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
 * Integration tests for the {@link FormSuratJalanResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class FormSuratJalanResourceIT {

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

    private static final String ENTITY_API_URL = "/api/form-surat-jalans";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private FormSuratJalanRepository formSuratJalanRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restFormSuratJalanMockMvc;

    private FormSuratJalan formSuratJalan;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FormSuratJalan createEntity(EntityManager em) {
        FormSuratJalan formSuratJalan = new FormSuratJalan()
            .status(DEFAULT_STATUS)
            .active(DEFAULT_ACTIVE)
            .remarks(DEFAULT_REMARKS)
            .createdDate(DEFAULT_CREATED_DATE)
            .createdBy(DEFAULT_CREATED_BY)
            .lastModifiedDate(DEFAULT_LAST_MODIFIED_DATE)
            .lastModifiedBy(DEFAULT_LAST_MODIFIED_BY);
        return formSuratJalan;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FormSuratJalan createUpdatedEntity(EntityManager em) {
        FormSuratJalan formSuratJalan = new FormSuratJalan()
            .status(UPDATED_STATUS)
            .active(UPDATED_ACTIVE)
            .remarks(UPDATED_REMARKS)
            .createdDate(UPDATED_CREATED_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .lastModifiedDate(UPDATED_LAST_MODIFIED_DATE)
            .lastModifiedBy(UPDATED_LAST_MODIFIED_BY);
        return formSuratJalan;
    }

    @BeforeEach
    public void initTest() {
        formSuratJalan = createEntity(em);
    }

    @Test
    @Transactional
    void createFormSuratJalan() throws Exception {
        int databaseSizeBeforeCreate = formSuratJalanRepository.findAll().size();
        // Create the FormSuratJalan
        restFormSuratJalanMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(formSuratJalan))
            )
            .andExpect(status().isCreated());

        // Validate the FormSuratJalan in the database
        List<FormSuratJalan> formSuratJalanList = formSuratJalanRepository.findAll();
        assertThat(formSuratJalanList).hasSize(databaseSizeBeforeCreate + 1);
        FormSuratJalan testFormSuratJalan = formSuratJalanList.get(formSuratJalanList.size() - 1);
        assertThat(testFormSuratJalan.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testFormSuratJalan.getActive()).isEqualTo(DEFAULT_ACTIVE);
        assertThat(testFormSuratJalan.getRemarks()).isEqualTo(DEFAULT_REMARKS);
        assertThat(testFormSuratJalan.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testFormSuratJalan.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testFormSuratJalan.getLastModifiedDate()).isEqualTo(DEFAULT_LAST_MODIFIED_DATE);
        assertThat(testFormSuratJalan.getLastModifiedBy()).isEqualTo(DEFAULT_LAST_MODIFIED_BY);
    }

    @Test
    @Transactional
    void createFormSuratJalanWithExistingId() throws Exception {
        // Create the FormSuratJalan with an existing ID
        formSuratJalan.setId("existing_id");

        int databaseSizeBeforeCreate = formSuratJalanRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restFormSuratJalanMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(formSuratJalan))
            )
            .andExpect(status().isBadRequest());

        // Validate the FormSuratJalan in the database
        List<FormSuratJalan> formSuratJalanList = formSuratJalanRepository.findAll();
        assertThat(formSuratJalanList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkActiveIsRequired() throws Exception {
        int databaseSizeBeforeTest = formSuratJalanRepository.findAll().size();
        // set the field null
        formSuratJalan.setActive(null);

        // Create the FormSuratJalan, which fails.

        restFormSuratJalanMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(formSuratJalan))
            )
            .andExpect(status().isBadRequest());

        List<FormSuratJalan> formSuratJalanList = formSuratJalanRepository.findAll();
        assertThat(formSuratJalanList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCreatedDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = formSuratJalanRepository.findAll().size();
        // set the field null
        formSuratJalan.setCreatedDate(null);

        // Create the FormSuratJalan, which fails.

        restFormSuratJalanMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(formSuratJalan))
            )
            .andExpect(status().isBadRequest());

        List<FormSuratJalan> formSuratJalanList = formSuratJalanRepository.findAll();
        assertThat(formSuratJalanList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllFormSuratJalans() throws Exception {
        // Initialize the database
        formSuratJalan.setId(UUID.randomUUID().toString());
        formSuratJalanRepository.saveAndFlush(formSuratJalan);

        // Get all the formSuratJalanList
        restFormSuratJalanMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(formSuratJalan.getId())))
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
    void getFormSuratJalan() throws Exception {
        // Initialize the database
        formSuratJalan.setId(UUID.randomUUID().toString());
        formSuratJalanRepository.saveAndFlush(formSuratJalan);

        // Get the formSuratJalan
        restFormSuratJalanMockMvc
            .perform(get(ENTITY_API_URL_ID, formSuratJalan.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(formSuratJalan.getId()))
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
    void getNonExistingFormSuratJalan() throws Exception {
        // Get the formSuratJalan
        restFormSuratJalanMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingFormSuratJalan() throws Exception {
        // Initialize the database
        formSuratJalan.setId(UUID.randomUUID().toString());
        formSuratJalanRepository.saveAndFlush(formSuratJalan);

        int databaseSizeBeforeUpdate = formSuratJalanRepository.findAll().size();

        // Update the formSuratJalan
        FormSuratJalan updatedFormSuratJalan = formSuratJalanRepository.findById(formSuratJalan.getId()).get();
        // Disconnect from session so that the updates on updatedFormSuratJalan are not directly saved in db
        em.detach(updatedFormSuratJalan);
        updatedFormSuratJalan
            .status(UPDATED_STATUS)
            .active(UPDATED_ACTIVE)
            .remarks(UPDATED_REMARKS)
            .createdDate(UPDATED_CREATED_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .lastModifiedDate(UPDATED_LAST_MODIFIED_DATE)
            .lastModifiedBy(UPDATED_LAST_MODIFIED_BY);

        restFormSuratJalanMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedFormSuratJalan.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedFormSuratJalan))
            )
            .andExpect(status().isOk());

        // Validate the FormSuratJalan in the database
        List<FormSuratJalan> formSuratJalanList = formSuratJalanRepository.findAll();
        assertThat(formSuratJalanList).hasSize(databaseSizeBeforeUpdate);
        FormSuratJalan testFormSuratJalan = formSuratJalanList.get(formSuratJalanList.size() - 1);
        assertThat(testFormSuratJalan.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testFormSuratJalan.getActive()).isEqualTo(UPDATED_ACTIVE);
        assertThat(testFormSuratJalan.getRemarks()).isEqualTo(UPDATED_REMARKS);
        assertThat(testFormSuratJalan.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testFormSuratJalan.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testFormSuratJalan.getLastModifiedDate()).isEqualTo(UPDATED_LAST_MODIFIED_DATE);
        assertThat(testFormSuratJalan.getLastModifiedBy()).isEqualTo(UPDATED_LAST_MODIFIED_BY);
    }

    @Test
    @Transactional
    void putNonExistingFormSuratJalan() throws Exception {
        int databaseSizeBeforeUpdate = formSuratJalanRepository.findAll().size();
        formSuratJalan.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFormSuratJalanMockMvc
            .perform(
                put(ENTITY_API_URL_ID, formSuratJalan.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(formSuratJalan))
            )
            .andExpect(status().isBadRequest());

        // Validate the FormSuratJalan in the database
        List<FormSuratJalan> formSuratJalanList = formSuratJalanRepository.findAll();
        assertThat(formSuratJalanList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchFormSuratJalan() throws Exception {
        int databaseSizeBeforeUpdate = formSuratJalanRepository.findAll().size();
        formSuratJalan.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restFormSuratJalanMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(formSuratJalan))
            )
            .andExpect(status().isBadRequest());

        // Validate the FormSuratJalan in the database
        List<FormSuratJalan> formSuratJalanList = formSuratJalanRepository.findAll();
        assertThat(formSuratJalanList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamFormSuratJalan() throws Exception {
        int databaseSizeBeforeUpdate = formSuratJalanRepository.findAll().size();
        formSuratJalan.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restFormSuratJalanMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(formSuratJalan)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the FormSuratJalan in the database
        List<FormSuratJalan> formSuratJalanList = formSuratJalanRepository.findAll();
        assertThat(formSuratJalanList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateFormSuratJalanWithPatch() throws Exception {
        // Initialize the database
        formSuratJalan.setId(UUID.randomUUID().toString());
        formSuratJalanRepository.saveAndFlush(formSuratJalan);

        int databaseSizeBeforeUpdate = formSuratJalanRepository.findAll().size();

        // Update the formSuratJalan using partial update
        FormSuratJalan partialUpdatedFormSuratJalan = new FormSuratJalan();
        partialUpdatedFormSuratJalan.setId(formSuratJalan.getId());

        partialUpdatedFormSuratJalan.lastModifiedBy(UPDATED_LAST_MODIFIED_BY);

        restFormSuratJalanMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedFormSuratJalan.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedFormSuratJalan))
            )
            .andExpect(status().isOk());

        // Validate the FormSuratJalan in the database
        List<FormSuratJalan> formSuratJalanList = formSuratJalanRepository.findAll();
        assertThat(formSuratJalanList).hasSize(databaseSizeBeforeUpdate);
        FormSuratJalan testFormSuratJalan = formSuratJalanList.get(formSuratJalanList.size() - 1);
        assertThat(testFormSuratJalan.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testFormSuratJalan.getActive()).isEqualTo(DEFAULT_ACTIVE);
        assertThat(testFormSuratJalan.getRemarks()).isEqualTo(DEFAULT_REMARKS);
        assertThat(testFormSuratJalan.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testFormSuratJalan.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testFormSuratJalan.getLastModifiedDate()).isEqualTo(DEFAULT_LAST_MODIFIED_DATE);
        assertThat(testFormSuratJalan.getLastModifiedBy()).isEqualTo(UPDATED_LAST_MODIFIED_BY);
    }

    @Test
    @Transactional
    void fullUpdateFormSuratJalanWithPatch() throws Exception {
        // Initialize the database
        formSuratJalan.setId(UUID.randomUUID().toString());
        formSuratJalanRepository.saveAndFlush(formSuratJalan);

        int databaseSizeBeforeUpdate = formSuratJalanRepository.findAll().size();

        // Update the formSuratJalan using partial update
        FormSuratJalan partialUpdatedFormSuratJalan = new FormSuratJalan();
        partialUpdatedFormSuratJalan.setId(formSuratJalan.getId());

        partialUpdatedFormSuratJalan
            .status(UPDATED_STATUS)
            .active(UPDATED_ACTIVE)
            .remarks(UPDATED_REMARKS)
            .createdDate(UPDATED_CREATED_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .lastModifiedDate(UPDATED_LAST_MODIFIED_DATE)
            .lastModifiedBy(UPDATED_LAST_MODIFIED_BY);

        restFormSuratJalanMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedFormSuratJalan.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedFormSuratJalan))
            )
            .andExpect(status().isOk());

        // Validate the FormSuratJalan in the database
        List<FormSuratJalan> formSuratJalanList = formSuratJalanRepository.findAll();
        assertThat(formSuratJalanList).hasSize(databaseSizeBeforeUpdate);
        FormSuratJalan testFormSuratJalan = formSuratJalanList.get(formSuratJalanList.size() - 1);
        assertThat(testFormSuratJalan.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testFormSuratJalan.getActive()).isEqualTo(UPDATED_ACTIVE);
        assertThat(testFormSuratJalan.getRemarks()).isEqualTo(UPDATED_REMARKS);
        assertThat(testFormSuratJalan.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testFormSuratJalan.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testFormSuratJalan.getLastModifiedDate()).isEqualTo(UPDATED_LAST_MODIFIED_DATE);
        assertThat(testFormSuratJalan.getLastModifiedBy()).isEqualTo(UPDATED_LAST_MODIFIED_BY);
    }

    @Test
    @Transactional
    void patchNonExistingFormSuratJalan() throws Exception {
        int databaseSizeBeforeUpdate = formSuratJalanRepository.findAll().size();
        formSuratJalan.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFormSuratJalanMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, formSuratJalan.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(formSuratJalan))
            )
            .andExpect(status().isBadRequest());

        // Validate the FormSuratJalan in the database
        List<FormSuratJalan> formSuratJalanList = formSuratJalanRepository.findAll();
        assertThat(formSuratJalanList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchFormSuratJalan() throws Exception {
        int databaseSizeBeforeUpdate = formSuratJalanRepository.findAll().size();
        formSuratJalan.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restFormSuratJalanMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(formSuratJalan))
            )
            .andExpect(status().isBadRequest());

        // Validate the FormSuratJalan in the database
        List<FormSuratJalan> formSuratJalanList = formSuratJalanRepository.findAll();
        assertThat(formSuratJalanList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamFormSuratJalan() throws Exception {
        int databaseSizeBeforeUpdate = formSuratJalanRepository.findAll().size();
        formSuratJalan.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restFormSuratJalanMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(formSuratJalan))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the FormSuratJalan in the database
        List<FormSuratJalan> formSuratJalanList = formSuratJalanRepository.findAll();
        assertThat(formSuratJalanList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteFormSuratJalan() throws Exception {
        // Initialize the database
        formSuratJalan.setId(UUID.randomUUID().toString());
        formSuratJalanRepository.saveAndFlush(formSuratJalan);

        int databaseSizeBeforeDelete = formSuratJalanRepository.findAll().size();

        // Delete the formSuratJalan
        restFormSuratJalanMockMvc
            .perform(delete(ENTITY_API_URL_ID, formSuratJalan.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<FormSuratJalan> formSuratJalanList = formSuratJalanRepository.findAll();
        assertThat(formSuratJalanList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
