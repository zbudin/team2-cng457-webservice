package com.cng457.webservice.entity;

import java.util.ArrayList;
import java.util.List;

import lombok.*;

import javax.persistence.Entity;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class PC extends Product {

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