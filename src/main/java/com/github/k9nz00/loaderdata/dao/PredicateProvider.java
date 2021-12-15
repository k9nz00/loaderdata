package com.github.k9nz00.loaderdata.dao;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Collection;

@FunctionalInterface
public interface PredicateProvider<T> {
    Collection<Predicate> getPredicates(CriteriaBuilder criteriaBuilder, Root<T> root);
}
