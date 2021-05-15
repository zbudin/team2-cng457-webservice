package com.cng457.webservice.entity;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class FeatureId implements Serializable {
    private String feature;
    private int productId;
}
