package com.nowij.groupware.specification;

import com.nowij.groupware.entity.EmployeeEntity;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EmployeeSpec {
    public static Specification<EmployeeEntity> searchEmployee(Map<String, Object> searchKey) {
        return ((root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            for (String key : searchKey.keySet()) {
                predicates.add(criteriaBuilder.like(root.get(key), "%" + searchKey.get(key) + "%"));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        });
    }
}
