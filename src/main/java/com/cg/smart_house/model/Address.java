package com.cg.smart_house.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
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
//    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @JoinColumn(name = "province_id")
    private Province province;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "apartment_id", referencedColumnName = "id")
    private Apartment apartment;
}
