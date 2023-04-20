package com.darfat.docreaderapp.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.darfat.docreaderapp.IntegrationTest;
import com.darfat.docreaderapp.domain.FormPengeluaranBarang;
import com.darfat.docreaderapp.repository.FormPengeluaranBarangRepository;
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
            .createdDate(DEFAULT_CREATED_DATE)
            .createdBy(DEFAULT_CREATED_BY)
            .lastModifiedDate(DEFAULT_LAST_MODIFIED_DATE)
            .lastModifiedBy(DEFAULT_LAST_MODIFIED_BY);
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
            .createdDate(UPDATED_CREATED_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .lastModifiedDate(UPDATED_LAST_MODIFIED_DATE)
            .lastModifiedBy(UPDATED_LAST_MODIFIED_BY);
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
            .createdDate(UPDATED_CREATED_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .lastModifiedDate(UPDATED_LAST_MODIFIED_DATE)
            .lastModifiedBy(UPDATED_LAST_MODIFIED_BY);

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
            .createdBy(UPDATED_CREATED_BY)
            .lastModifiedDate(UPDATED_LAST_MODIFIED_DATE);

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
        assertThat(testFormPengeluaranBarang.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testFormPengeluaranBarang.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testFormPengeluaranBarang.getLastModifiedDate()).isEqualTo(UPDATED_LAST_MODIFIED_DATE);
        assertThat(testFormPengeluaranBarang.getLastModifiedBy()).isEqualTo(DEFAULT_LAST_MODIFIED_BY);
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
            .createdDate(UPDATED_CREATED_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .lastModifiedDate(UPDATED_LAST_MODIFIED_DATE)
            .lastModifiedBy(UPDATED_LAST_MODIFIED_BY);

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
