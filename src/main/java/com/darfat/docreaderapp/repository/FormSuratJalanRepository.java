package com.darfat.docreaderapp.repository;

import com.darfat.docreaderapp.domain.FormSuratJalan;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the FormSuratJalan entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FormSuratJalanRepository extends JpaRepository<FormSuratJalan, String> {}
