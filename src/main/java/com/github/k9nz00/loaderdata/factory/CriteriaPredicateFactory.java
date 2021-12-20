package com.github.k9nz00.loaderdata.factory;

import com.github.k9nz00.loaderdata.dao.PredicateProvider;

public interface CriteriaPredicateFactory<I, U> {
    PredicateProvider<U> create(I input);
}
