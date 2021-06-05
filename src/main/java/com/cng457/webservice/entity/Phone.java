package com.cng457.webservice.entity;

import java.util.ArrayList;
import java.util.List;

import lombok.*;

import javax.persistence.Entity;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Phone extends Product {

    @Getter
    @Setter
    private int internalMemory;

    public Phone(String brand, String model, String screenSize, Double price, int internalMemory) {
        super();
        setBrand(brand);
        setModel(model);
        setScreenSize(screenSize);
        setPrice(price);
        this.internalMemory = internalMemory;
    }


}


/*
spring.datasource.url=jdbc:mysql://85.10.205.173:3306/akintest
spring.datasource.username=akintest
spring.datasource.password=12345678
spring.jpa.hibernate.ddl-auto=update
spring.datasource.driver-class-name =com.mysql.jdbc.Driver
spring.jpa.show-sql = true
spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.MySQL5InnoDBDialect
* */