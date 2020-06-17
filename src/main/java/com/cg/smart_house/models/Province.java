package com.cg.smart_house.models;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
public class Province {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "provinces")
    private Set<Address> addresses;
}
