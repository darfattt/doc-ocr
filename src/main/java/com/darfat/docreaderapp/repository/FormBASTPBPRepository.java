package com.darfat.docreaderapp.repository;

import com.darfat.docreaderapp.domain.FormBASTPBP;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the FormBASTPBP entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FormBASTPBPRepository extends JpaRepository<FormBASTPBP, String> {}
