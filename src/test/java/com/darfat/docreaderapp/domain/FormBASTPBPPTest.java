package com.darfat.docreaderapp.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.darfat.docreaderapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class FormBASTPBPPTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(FormBASTPBPP.class);
        FormBASTPBPP formBASTPBPP1 = new FormBASTPBPP();
        formBASTPBPP1.setId("id1");
        FormBASTPBPP formBASTPBPP2 = new FormBASTPBPP();
        formBASTPBPP2.setId(formBASTPBPP1.getId());
        assertThat(formBASTPBPP1).isEqualTo(formBASTPBPP2);
        formBASTPBPP2.setId("id2");
        assertThat(formBASTPBPP1).isNotEqualTo(formBASTPBPP2);
        formBASTPBPP1.setId(null);
        assertThat(formBASTPBPP1).isNotEqualTo(formBASTPBPP2);
    }
}
