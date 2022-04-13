package com.github.k9nz00.server.factory.impl;

import com.github.k9nz00.server.dao.PredicateProvider;
import com.github.k9nz00.server.dao.entity.UserEntity;
import com.github.k9nz00.server.factory.CriteriaPredicateFactory;
import com.github.k9nz00.server.rest.dto.UserCriteriaDto;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.github.k9nz00.server.factory.ParamNameConstants.USER_IS_ACTIVE;

@Component
public class UsersCriteriaPredicateFactory implements CriteriaPredicateFactory<UserCriteriaDto, UserEntity> {

    @Override
    public PredicateProvider<UserEntity> create(UserCriteriaDto input) {
        return ((criteriaBuilder, root) -> {
            List<Predicate> predicates = new ArrayList<>();

            Optional.ofNullable(input.getActive())
                    .ifPresent(value -> predicates.add(criteriaBuilder.equal(root.get(USER_IS_ACTIVE), value)));
            return predicates;
        });
    }
}
