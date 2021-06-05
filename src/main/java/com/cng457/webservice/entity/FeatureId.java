package com.cng457.webservice.entity;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class FeatureId implements Serializable {
    private String feature;
    private int id;

    public FeatureId(){   }
    public FeatureId(String feature,int id){
        this.feature = feature;
        this.id = id;
    }
}
