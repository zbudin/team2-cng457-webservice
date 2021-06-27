package com.cng457.webservice.repository;

import com.cng457.webservice.entity.PC;

import java.util.List;

import javax.persistence.LockModeType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IPCRepository extends JpaRepository<PC, Long>, JpaSpecificationExecutor<PC> {

    List<PC> findByBrand(@Param("brand") String brand);

    List<PC> findByModel(@Param("model") String model);

    List<PC> findByScreenSize(@Param("screenSize") String screenSize);

    List<PC> findByPrice(@Param("price") Double price);

    List<PC> findByProcessor(@Param("processor") String processor);

    List<PC> findByMemory(@Param("memory") int memory);

    List<PC> findByScreenResolution(@Param("screenResolution") String screenResolution);

    List<PC> findByStorage(@Param("storage") int storage);

    @Query(value = "SELECT * FROM product p WHERE p.id in (SELECT product_id from comment f WHERE f.rating = :rating) ", nativeQuery = true)
    List<PC> findByRating(@Param("rating") int rating);

    List<PC> findAll();

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    PC save(PC computer);

}
