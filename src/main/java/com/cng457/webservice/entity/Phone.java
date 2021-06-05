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