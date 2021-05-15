package com.cng457.webservice.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter private int commentId;
    @Getter @Setter private String comment;
    @Getter @Setter private int rating;
    @Getter @Setter private Timestamp timestamp;

    @ManyToOne
    private Product product;

}
