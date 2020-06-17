package com.cg.smart_house.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn
    private Apartment apartment;

    @ManyToOne
    @JoinColumn
    private Province provinces;
}
