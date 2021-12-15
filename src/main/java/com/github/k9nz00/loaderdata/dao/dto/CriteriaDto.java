package com.github.k9nz00.loaderdata.dao.dto;

import com.github.k9nz00.loaderdata.dao.PredicateProvider;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CriteriaDto<T> {
    private final Class<T> tClass;
    private final PredicateProvider<T> predicateProvider;
    private final Integer offset;
    private final Integer limit;
    private final String sortColumn;
    private final String sortDirection;
}
