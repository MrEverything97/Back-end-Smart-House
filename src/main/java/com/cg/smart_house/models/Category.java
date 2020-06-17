package com.cg.smart_house.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

    @ManyToOne
    @JoinColumn
    private Apartment apartment;
}
