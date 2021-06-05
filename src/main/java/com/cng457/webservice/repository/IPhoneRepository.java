package com.cng457.webservice.repository;

import com.cng457.webservice.entity.Phone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IPhoneRepository extends JpaRepository<Phone, Long>, JpaSpecificationExecutor<Phone> {

    List<Phone> findByBrand(@Param("brand") String brand);

    List<Phone> findByModel(@Param("model") String model);

    List<Phone> findByScreenSize(@Param("screenSize") String screenSize);

    List<Phone> findByPrice(@Param("price") Double price);

    List<Phone> findByInternalMemory(@Param("internalMemory") int internalMemory);

    @Query(value = "SELECT * FROM product p WHERE p.id in (SELECT product_id from feature f WHERE f.feature = :feature and dtype='Phone') ", nativeQuery = true)
    List<Phone> queryBy(@Param("feature") String feature);
}
