package com.darfat.docreaderapp.repository;

import com.darfat.docreaderapp.domain.FormPernyataan;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the FormPernyataan entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FormPernyataanRepository extends JpaRepository<FormPernyataan, String> {}
