package com.darfat.docreaderapp.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.darfat.docreaderapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class FormPernyataanTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(FormPernyataan.class);
        FormPernyataan formPernyataan1 = new FormPernyataan();
        formPernyataan1.setId("id1");
        FormPernyataan formPernyataan2 = new FormPernyataan();
        formPernyataan2.setId(formPernyataan1.getId());
        assertThat(formPernyataan1).isEqualTo(formPernyataan2);
        formPernyataan2.setId("id2");
        assertThat(formPernyataan1).isNotEqualTo(formPernyataan2);
        formPernyataan1.setId(null);
        assertThat(formPernyataan1).isNotEqualTo(formPernyataan2);
    }
}
