package com.example.lab3.service.specification;

import com.example.lab3.dao.entity.CustomerEntity;
import com.example.lab3.dao.entity.CustomerEntity.Fields;
import com.example.lab3.model.criteria.CustomerCriteria;
import com.example.lab3.util.PredicateUtil;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;


public record CustomerSpecification(CustomerCriteria customerCriteria) implements Specification<CustomerEntity> {


    @Override
    public Predicate toPredicate(Root<CustomerEntity> root,
                                 CriteriaQuery<?> query,
                                 CriteriaBuilder cb) {

        var predicates = PredicateUtil.builder()
                .addNullSafety(
                        customerCriteria.getPin(),
                        pin -> cb.like(
                                root.get(Fields.pin), applyLikePattern(pin)
                        )
                )
                .addNullSafety(
                        customerCriteria.getFullName(),
                        fullName -> cb.like(
                                root.get(Fields.fullName), applyLikePattern(fullName)
                        )
                )
                .addNullSafety(
                        customerCriteria.getPhoneNumber(),
                        phoneNumber -> cb.like(
                                root.get(Fields.phoneNumber), applyLikePattern(phoneNumber)
                        )
                )
                .build();
        return cb.and(predicates);
    }

    private String applyLikePattern(String data) {
        return "%" + data + "%";
    }
}
