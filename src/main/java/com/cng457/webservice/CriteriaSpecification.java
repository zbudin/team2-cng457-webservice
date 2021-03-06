package com.cng457.webservice;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

import com.cng457.webservice.entity.Feature;
import com.cng457.webservice.entity.Product;
import com.cng457.webservice.entity.SearchCriteria;
import com.cng457.webservice.entity.SearchOperation;

import org.springframework.data.jpa.domain.Specification;

public class CriteriaSpecification<T> implements Specification<T> {

    private List<SearchCriteria> list;

    public CriteriaSpecification() {
        this.list = new ArrayList<>();
    }

    public void add(SearchCriteria criteria) {
        list.add(criteria);
    }

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {

        List<Predicate> predicates = new ArrayList<>();

        for (SearchCriteria criteria : list) {
            if (criteria.getKey() == "feature") {
                Subquery<Long> subquery = query.subquery(Long.class);
                Root<Feature> feat = subquery.from(Feature.class);
                Join<Feature, Product> joinOnProduct = feat.join("product");
                Path<Long> pId = joinOnProduct.get("id");
                subquery.select(pId).where(builder.equal(feat.get("feature"), criteria.getValue().toString()));
                predicates.add(builder.in(root.get("id")).value(subquery));
            } else {
                if (criteria.getOperation().equals(SearchOperation.GREATER_THAN)) {
                    predicates.add(builder.greaterThan(root.get(criteria.getKey()), criteria.getValue().toString()));
                } else if (criteria.getOperation().equals(SearchOperation.LESS_THAN)) {
                    predicates.add(builder.lessThan(root.get(criteria.getKey()), criteria.getValue().toString()));
                } else if (criteria.getOperation().equals(SearchOperation.GREATER_THAN_EQUAL)) {
                    predicates.add(
                            builder.greaterThanOrEqualTo(root.get(criteria.getKey()), criteria.getValue().toString()));
                } else if (criteria.getOperation().equals(SearchOperation.LESS_THAN_EQUAL)) {
                    predicates.add(
                            builder.lessThanOrEqualTo(root.get(criteria.getKey()), criteria.getValue().toString()));
                } else if (criteria.getOperation().equals(SearchOperation.NOT_EQUAL)) {
                    predicates.add(builder.notEqual(root.get(criteria.getKey()), criteria.getValue()));
                } else if (criteria.getOperation().equals(SearchOperation.EQUAL)) {
                    predicates.add(builder.equal(root.get(criteria.getKey()), criteria.getValue()));
                } else if (criteria.getOperation().equals(SearchOperation.MATCH)) {
                    predicates.add(builder.like(builder.lower(root.get(criteria.getKey())),
                            "%" + criteria.getValue().toString().toLowerCase() + "%"));
                } else if (criteria.getOperation().equals(SearchOperation.MATCH_END)) {
                    predicates.add(builder.like(builder.lower(root.get(criteria.getKey())),
                            criteria.getValue().toString().toLowerCase() + "%"));
                }
            }
        }

        return builder.and(predicates.toArray(new Predicate[0]));
    }
}