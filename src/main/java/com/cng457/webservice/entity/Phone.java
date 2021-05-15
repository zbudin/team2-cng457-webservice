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
    private short internalMemory;

}