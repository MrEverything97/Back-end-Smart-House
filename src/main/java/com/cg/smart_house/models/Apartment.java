package com.cg.smart_house.models;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "apartment")
@Data
public class Apartment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private int bathroom;
    private int bedroom;
    private int priceByDate;
    private String description;

    @OneToMany(mappedBy = "apartment")
    private Set<Picture> pictures;

    @OneToMany(mappedBy = "apartment")
    private Set<Category> categories;

    public Apartment() {
    }
}
