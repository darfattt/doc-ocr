package com.darfat.docreaderapp.service;

import com.darfat.docreaderapp.domain.*; // for static metamodels
import com.darfat.docreaderapp.domain.VerifiedDocuments;
import com.darfat.docreaderapp.repository.VerifiedDocumentsRepository;
import com.darfat.docreaderapp.service.criteria.VerifiedDocumentsCriteria;
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
 * Service for executing complex queries for {@link VerifiedDocuments} entities in the database.
 * The main input is a {@link VerifiedDocumentsCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link VerifiedDocuments} or a {@link Page} of {@link VerifiedDocuments} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class VerifiedDocumentsQueryService extends QueryService<VerifiedDocuments> {

    private final Logger log = LoggerFactory.getLogger(VerifiedDocumentsQueryService.class);

    private final VerifiedDocumentsRepository verifiedDocumentsRepository;

    public VerifiedDocumentsQueryService(VerifiedDocumentsRepository verifiedDocumentsRepository) {
        this.verifiedDocumentsRepository = verifiedDocumentsRepository;
    }

    /**
     * Return a {@link List} of {@link VerifiedDocuments} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<VerifiedDocuments> findByCriteria(VerifiedDocumentsCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<VerifiedDocuments> specification = createSpecification(criteria);
        return verifiedDocumentsRepository.findAll(specification);
    }

    /**
     * Return a {@link Page} of {@link VerifiedDocuments} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<VerifiedDocuments> findByCriteria(VerifiedDocumentsCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<VerifiedDocuments> specification = createSpecification(criteria);
        return verifiedDocumentsRepository.findAll(specification, page);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(VerifiedDocumentsCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<VerifiedDocuments> specification = createSpecification(criteria);
        return verifiedDocumentsRepository.count(specification);
    }

    /**
     * Function to convert {@link VerifiedDocumentsCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<VerifiedDocuments> createSpecification(VerifiedDocumentsCriteria criteria) {
        Specification<VerifiedDocuments> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildStringSpecification(criteria.getId(), VerifiedDocuments_.id));
            }
            if (criteria.getType() != null) {
                specification = specification.and(buildStringSpecification(criteria.getType(), VerifiedDocuments_.type));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), VerifiedDocuments_.name));
            }
            if (criteria.getStatus() != null) {
                specification = specification.and(buildStringSpecification(criteria.getStatus(), VerifiedDocuments_.status));
            }
            if (criteria.getContentId() != null) {
                specification = specification.and(buildStringSpecification(criteria.getContentId(), VerifiedDocuments_.contentId));
            }
            if (criteria.getCreatedDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreatedDate(), VerifiedDocuments_.createdDate));
            }
            if (criteria.getCreatedBy() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreatedBy(), VerifiedDocuments_.createdBy));
            }
            if (criteria.getLastModifiedDate() != null) {
                specification =
                    specification.and(buildRangeSpecification(criteria.getLastModifiedDate(), VerifiedDocuments_.lastModifiedDate));
            }
            if (criteria.getLastModifiedBy() != null) {
                specification =
                    specification.and(buildStringSpecification(criteria.getLastModifiedBy(), VerifiedDocuments_.lastModifiedBy));
            }
        }
        return specification;
    }
}
