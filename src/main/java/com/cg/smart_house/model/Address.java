package com.cg.smart_house.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToOne
    private Province provinces;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "apartment_id", referencedColumnName = "id")
    private Apartment apartment;
}
