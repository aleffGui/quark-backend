package com.guilherme.quarkapi.specifications;

import org.springframework.data.jpa.domain.Specification;

import com.guilherme.quarkapi.dtos.UserFilterDTO;
import com.guilherme.quarkapi.models.User;

import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Predicate;

public class UserSpecifications {
    public static Specification<User> withFilters(UserFilterDTO filter) {
        return (root, query, criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.conjunction();

            if (filter.getId() != null) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("id"), filter.getId()));
            }

            if (filter.getFirstNameOrLastName() != null && !filter.getFirstNameOrLastName().isEmpty()) {
                String filterText = filter.getFirstNameOrLastName().toLowerCase();
                Expression<String> firstNameExpression = criteriaBuilder.lower(root.get("firstName"));
                Expression<String> lastNameExpression = criteriaBuilder.lower(root.get("lastName"));

                predicate = criteriaBuilder.and(predicate, criteriaBuilder.or(
                    criteriaBuilder.like(firstNameExpression, "%" + filterText + "%"),
                    criteriaBuilder.like(lastNameExpression, "%" + filterText + "%")
                ));
            }

            if (filter.getId() != null) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("id"), filter.getId()));
            }

            if (filter.getRole() != null) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("role"), filter.getRole()));
            }

            if (filter.getUserName() != null && !filter.getUserName().isEmpty()) {
                String filterText = filter.getUserName().toLowerCase();
                Expression<String> userNameExpression = criteriaBuilder.lower(root.get("userName"));

                predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(userNameExpression, "%" + filterText + "%"));
            }

            return predicate;
        };
    }
}
