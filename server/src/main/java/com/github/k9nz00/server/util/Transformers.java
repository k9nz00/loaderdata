package com.github.k9nz00.server.util;

import com.github.k9nz00.server.dao.PredicateProvider;
import com.github.k9nz00.server.dao.dto.CriteriaDto;
import com.github.k9nz00.server.rest.dto.TableCriteriaDto;

public class Transformers {
    public static <T> CriteriaDto<T> criteriaDto(final Class<T> tClass,
                                                 final PredicateProvider<T> predicateProvider,
                                                 final TableCriteriaDto input) {
        return new CriteriaDto<>(
                tClass,
                predicateProvider,
                input.getOffset(),
                input.getLimit(),
                input.getSortColumn(),
                input.getSortDirection());
    }
}
