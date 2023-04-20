package com.darfat.docreaderapp.service;

import com.darfat.docreaderapp.domain.*; // for static metamodels
import com.darfat.docreaderapp.domain.Documents;
import com.darfat.docreaderapp.repository.DocumentsRepository;
import com.darfat.docreaderapp.service.criteria.DocumentsCriteria;
import java.util.List;
import javax.persistence.criteria.JoinType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.QueryService;

/**
 * Service for executing complex queries for {@link Documents} entities in the database.
 * The main input is a {@link DocumentsCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link Documents} or a {@link Page} of {@link Documents} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class DocumentsQueryService extends QueryService<Documents> {

    private final Logger log = LoggerFactory.getLogger(DocumentsQueryService.class);

    private final DocumentsRepository documentsRepository;

    public DocumentsQueryService(DocumentsRepository documentsRepository) {
        this.documentsRepository = documentsRepository;
    }

    /**
     * Return a {@link List} of {@link Documents} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<Documents> findByCriteria(DocumentsCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Documents> specification = createSpecification(criteria);
        return documentsRepository.findAll(specification);
    }

    /**
     * Return a {@link Page} of {@link Documents} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<Documents> findByCriteria(DocumentsCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Documents> specification = createSpecification(criteria);
        return documentsRepository.findAll(specification, page);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(DocumentsCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Documents> specification = createSpecification(criteria);
        return documentsRepository.count(specification);
    }

    /**
     * Function to convert {@link DocumentsCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Documents> createSpecification(DocumentsCriteria criteria) {
        Specification<Documents> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildStringSpecification(criteria.getId(), Documents_.id));
            }
            if (criteria.getType() != null) {
                specification = specification.and(buildStringSpecification(criteria.getType(), Documents_.type));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), Documents_.name));
            }
            if (criteria.getStatus() != null) {
                specification = specification.and(buildStringSpecification(criteria.getStatus(), Documents_.status));
            }
            if (criteria.getCreatedDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreatedDate(), Documents_.createdDate));
            }
            if (criteria.getCreatedBy() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreatedBy(), Documents_.createdBy));
            }
            if (criteria.getLastModifiedDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getLastModifiedDate(), Documents_.lastModifiedDate));
            }
            if (criteria.getLastModifiedBy() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLastModifiedBy(), Documents_.lastModifiedBy));
            }
        }
        return specification;
    }
}
