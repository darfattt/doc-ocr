package com.darfat.docreaderapp.repository;

import com.darfat.docreaderapp.domain.FormPengeluaranBarang;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the FormPengeluaranBarang entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FormPengeluaranBarangRepository extends JpaRepository<FormPengeluaranBarang, String> {}
