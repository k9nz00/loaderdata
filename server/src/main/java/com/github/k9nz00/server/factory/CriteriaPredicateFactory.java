package com.github.k9nz00.server.factory;

import com.github.k9nz00.server.dao.PredicateProvider;

public interface CriteriaPredicateFactory<I, U> {
    PredicateProvider<U> create(I input);
}
