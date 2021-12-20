package com.github.k9nz00.loaderdata.dao.impl;

import com.github.k9nz00.loaderdata.dao.dto.CriteriaDto;
import com.github.k9nz00.loaderdata.exception.NotAllowedMissingPredicatesException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
public abstract class AbstractDaoImpl {
    protected final EntityManager entityManager;
    protected final int defaultLimit;

    protected <T> Collection<T> execute(final CriteriaDto<T> criteriaDto) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        Class<T> tClass = criteriaDto.getTClass();
        CriteriaQuery<T> criteria = criteriaBuilder.createQuery(tClass);
        Root<T> root = criteria.from(tClass);

        try {
            Optional.ofNullable(criteriaDto.getPredicateProvider())
                    .map(factory -> factory.getPredicates(criteriaBuilder, root).toArray(Predicate[]::new))
                    .ifPresent(criteria::where);
        } catch (NotAllowedMissingPredicatesException e) {
            log.trace("Predicates is missing for [{}] query", tClass.getName());
            return Collections.emptyList();
        }

        String sortColumn = criteriaDto.getSortColumn();
        if (sortColumn != null){
            String[] paths = sortColumn.split("\\.");
            Path<Object> sort = root.get(paths[0]);
            for (int i = 1; i < paths.length; i++) {
                sort = sort.get(paths[i]);
            }

            Order order = "DESC".equalsIgnoreCase(criteriaDto.getSortDirection())
                    ? criteriaBuilder.desc(sort)
                    : criteriaBuilder.asc(sort);
            criteria.orderBy(order);
        }

        TypedQuery<T> query = entityManager.createQuery(criteria);
        Optional.ofNullable(criteriaDto.getOffset()).ifPresent(query::setFirstResult);
        query.setMaxResults(Optional.ofNullable(criteriaDto.getLimit()).orElse(defaultLimit));
        return query.getResultList();
    }
}
