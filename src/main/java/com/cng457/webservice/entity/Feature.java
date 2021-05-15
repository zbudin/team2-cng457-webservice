package com.cng457.webservice.entity;

import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Feature {
    @EmbeddedId @Getter @Setter
    private FeatureId featureid;

    @ManyToOne @Getter @Setter
    private Product product;
}
