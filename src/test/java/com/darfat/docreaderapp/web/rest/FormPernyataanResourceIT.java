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
            .createdDate(DEFAULT_CREATED_DATE)
            .createdBy(DEFAULT_CREATED_BY)
            .lastModifiedDate(DEFAULT_LAST_MODIFIED_DATE)
            .lastModifiedBy(DEFAULT_LAST_MODIFIED_BY);
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
            .createdDate(UPDATED_CREATED_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .lastModifiedDate(UPDATED_LAST_MODIFIED_DATE)
            .lastModifiedBy(UPDATED_LAST_MODIFIED_BY);
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
            .createdDate(UPDATED_CREATED_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .lastModifiedDate(UPDATED_LAST_MODIFIED_DATE)
            .lastModifiedBy(UPDATED_LAST_MODIFIED_BY);

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
            .createdDate(UPDATED_CREATED_DATE)
            .createdBy(UPDATED_CREATED_BY);

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
        assertThat(testFormPernyataan.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testFormPernyataan.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
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
