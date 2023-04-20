package com.darfat.docreaderapp.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.darfat.docreaderapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class VerifiedDocumentsTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(VerifiedDocuments.class);
        VerifiedDocuments verifiedDocuments1 = new VerifiedDocuments();
        verifiedDocuments1.setId("id1");
        VerifiedDocuments verifiedDocuments2 = new VerifiedDocuments();
        verifiedDocuments2.setId(verifiedDocuments1.getId());
        assertThat(verifiedDocuments1).isEqualTo(verifiedDocuments2);
        verifiedDocuments2.setId("id2");
        assertThat(verifiedDocuments1).isNotEqualTo(verifiedDocuments2);
        verifiedDocuments1.setId(null);
        assertThat(verifiedDocuments1).isNotEqualTo(verifiedDocuments2);
    }
}
