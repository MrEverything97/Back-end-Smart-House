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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne
    @JoinColumn(name = "apartment_id")
    private Apartment apartment;

    public Picture() {
    }
}
