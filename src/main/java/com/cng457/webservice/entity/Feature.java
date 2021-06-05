package com.cng457.webservice.entity;

import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Feature {
    //@EmbeddedId @Getter @Setter
    //private FeatureId featureid;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @Getter
    @Setter
    private String feature;

    @ManyToOne(fetch = FetchType.EAGER) @Getter @Setter
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;
}
