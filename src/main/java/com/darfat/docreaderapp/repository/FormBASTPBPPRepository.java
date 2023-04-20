package com.darfat.docreaderapp.repository;

import com.darfat.docreaderapp.domain.FormBASTPBPP;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the FormBASTPBPP entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FormBASTPBPPRepository extends JpaRepository<FormBASTPBPP, String> {}
