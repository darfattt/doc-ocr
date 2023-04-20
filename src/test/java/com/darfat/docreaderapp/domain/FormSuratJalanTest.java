package com.darfat.docreaderapp.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.darfat.docreaderapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class FormSuratJalanTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(FormSuratJalan.class);
        FormSuratJalan formSuratJalan1 = new FormSuratJalan();
        formSuratJalan1.setId("id1");
        FormSuratJalan formSuratJalan2 = new FormSuratJalan();
        formSuratJalan2.setId(formSuratJalan1.getId());
        assertThat(formSuratJalan1).isEqualTo(formSuratJalan2);
        formSuratJalan2.setId("id2");
        assertThat(formSuratJalan1).isNotEqualTo(formSuratJalan2);
        formSuratJalan1.setId(null);
        assertThat(formSuratJalan1).isNotEqualTo(formSuratJalan2);
    }
}
