package com.cg.smart_house.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Picture {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String imageUrl;

    @ManyToOne
    @JoinColumn
    private Apartment apartment;

    public Picture() {
    }
}
