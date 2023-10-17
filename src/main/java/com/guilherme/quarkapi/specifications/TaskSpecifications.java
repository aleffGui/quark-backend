package com.guilherme.quarkapi.specifications;

import org.springframework.data.jpa.domain.Specification;

import com.guilherme.quarkapi.dtos.TaskFilterDTO;
import com.guilherme.quarkapi.models.Task;

import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Predicate;

public class TaskSpecifications {

    public static Specification<Task> withFilters(TaskFilterDTO filter) {
        return (root, query, criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.conjunction();

            if (filter.getId() != null) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("id"), filter.getId()));
            }

            if (filter.getTitleOrDescription() != null && !filter.getTitleOrDescription().isEmpty()) {
                String filterText = filter.getTitleOrDescription().toLowerCase();
                Expression<String> titleExpression = criteriaBuilder.lower(root.get("title"));
                Expression<String> descriptionExpression = criteriaBuilder.lower(root.get("description"));

                predicate = criteriaBuilder.and(predicate, criteriaBuilder.or(
                    criteriaBuilder.like(titleExpression, "%" + filterText + "%"),
                    criteriaBuilder.like(descriptionExpression, "%" + filterText + "%")
                ));
            }

            if (filter.getUserId() != null) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("user").get("id"), filter.getUserId()));
            }

            if (filter.getPriority() != null) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("priority"), filter.getPriority()));
            }

            if (filter.getStatus() != null) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("status"), filter.getStatus()));
            }

            return predicate;
        };
    }
}