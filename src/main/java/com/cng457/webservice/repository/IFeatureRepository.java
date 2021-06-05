package com.cng457.webservice.repository;

import com.cng457.webservice.entity.Feature;
import com.cng457.webservice.entity.Product;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface IFeatureRepository extends JpaRepository<Feature, Long>, JpaSpecificationExecutor<Feature> {

    List<Feature> findByProduct(@Param("computer") Product product, Sort sort);

    List<Feature> findByProductId(Long id);

    List<Feature> findPhoneCommentsById(Long id);
}
