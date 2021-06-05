package com.cng457.webservice.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @Getter
    @Setter
    private String brand;

    @Getter
    @Setter
    private String model;

    @Getter
    @Setter
    private String screenSize;

    @Getter
    @Setter
    private Double price;

}