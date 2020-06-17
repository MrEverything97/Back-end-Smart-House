package com.cg.smart_house.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "address")
@Data
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "apartment_id", referencedColumnName = "id")
    private Apartment apartment;

    @ManyToOne
    @JoinColumn(name = "province_id")
    private Province provinces;
}
