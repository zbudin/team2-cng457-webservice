package com.cng457.webservice.repository;

import com.cng457.webservice.entity.Phone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

import javax.persistence.LockModeType;

public interface IPhoneRepository extends JpaRepository<Phone, Long>, JpaSpecificationExecutor<Phone> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    List<Phone> findByBrand(@Param("brand") String brand);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    List<Phone> findByModel(@Param("model") String model);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    List<Phone> findByScreenSize(@Param("screenSize") String screenSize);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    List<Phone> findByPrice(@Param("price") Double price);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    List<Phone> findByInternalMemory(@Param("internalMemory") int internalMemory);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query(value = "SELECT * FROM product p WHERE p.id in (SELECT product_id from feature f WHERE f.feature = :feature and dtype='Phone') ", nativeQuery = true)
    List<Phone> queryBy(@Param("feature") String feature);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Phone save(Phone phone);
}
