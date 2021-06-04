package com.cng457.webservice.entity;

import java.util.ArrayList;
import java.util.List;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;

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

    // @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true, targetEntity = Comment.class)
    // private List<Comment> comments;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true, targetEntity = Feature.class)
    private List<Feature> features;

    // public List<Comment> getComments() {
    //     return comments;
    // }

    public List<Feature> getFeatures() {
        return features;
    }
}