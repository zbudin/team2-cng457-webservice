package com.cng457.webservice.repository;

import java.util.List;

import com.cng457.webservice.entity.PC;

public interface IPCService {

    List<PC> findComputersByCriteria(String brand, String model, String screenSize, String minPrice, String maxPrice,
            String processor, String memory, String screenResolution, String storage, String feature);
}