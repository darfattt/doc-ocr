package com.darfat.docreaderapp.repository;

import com.darfat.docreaderapp.domain.Documents;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Documents entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DocumentsRepository extends JpaRepository<Documents, String>, JpaSpecificationExecutor<Documents> {}
