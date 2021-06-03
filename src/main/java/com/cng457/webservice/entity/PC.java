package com.cng457.webservice.entity;

import lombok.*;

import javax.persistence.Entity;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class PC extends Product {

    public PC(String brand, String model, String screenSize, Double price, String processor, int memory,
            String screenResolution, int storage) {
        super();
        setBrand(brand);
        setModel(model);
        setScreenSize(screenSize);
        setPrice(price);
        this.processor = processor;
        this.memory = memory;
        this.screenResolution = screenResolution;
        this.storage = storage;
    }

    @Getter
    @Setter
    private String processor;

    @Getter
    @Setter
    private int memory;

    @Getter
    @Setter
    private String screenResolution;

    @Getter
    @Setter
    private int storage;

}