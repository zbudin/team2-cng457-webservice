package com.cng457.webservice.entity;

import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Feature {
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
