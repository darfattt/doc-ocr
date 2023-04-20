package com.darfat.docreaderapp.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.darfat.docreaderapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class FormBASTPBPTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(FormBASTPBP.class);
        FormBASTPBP formBASTPBP1 = new FormBASTPBP();
        formBASTPBP1.setId("id1");
        FormBASTPBP formBASTPBP2 = new FormBASTPBP();
        formBASTPBP2.setId(formBASTPBP1.getId());
        assertThat(formBASTPBP1).isEqualTo(formBASTPBP2);
        formBASTPBP2.setId("id2");
        assertThat(formBASTPBP1).isNotEqualTo(formBASTPBP2);
        formBASTPBP1.setId(null);
        assertThat(formBASTPBP1).isNotEqualTo(formBASTPBP2);
    }
}
