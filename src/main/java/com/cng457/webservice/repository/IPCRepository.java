package com.cng457.webservice.repository;

import com.cng457.webservice.entity.PC;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.query.Param;

public interface IPCRepository extends JpaRepository<PC, Long>, JpaSpecificationExecutor<PC> {

    List<PC> findByBrand(@Param("brand") String brand);

    List<PC> findByModel(@Param("model") String model);

    List<PC> findByScreenSize(@Param("screenSize") String screenSize);

    List<PC> findByPrice(@Param("price") String price);

    List<PC> findByProcessor(@Param("processor") String processor);

    List<PC> findByMemory(@Param("memory") String memory);

    List<PC> findByScreenResolution(@Param("screenResolution") String screenResolution);

    List<PC> findByStorage(@Param("storage") String storage);

}
