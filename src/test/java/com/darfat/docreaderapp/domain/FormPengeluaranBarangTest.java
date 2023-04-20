package com.darfat.docreaderapp.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.darfat.docreaderapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class FormPengeluaranBarangTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(FormPengeluaranBarang.class);
        FormPengeluaranBarang formPengeluaranBarang1 = new FormPengeluaranBarang();
        formPengeluaranBarang1.setId("id1");
        FormPengeluaranBarang formPengeluaranBarang2 = new FormPengeluaranBarang();
        formPengeluaranBarang2.setId(formPengeluaranBarang1.getId());
        assertThat(formPengeluaranBarang1).isEqualTo(formPengeluaranBarang2);
        formPengeluaranBarang2.setId("id2");
        assertThat(formPengeluaranBarang1).isNotEqualTo(formPengeluaranBarang2);
        formPengeluaranBarang1.setId(null);
        assertThat(formPengeluaranBarang1).isNotEqualTo(formPengeluaranBarang2);
    }
}
