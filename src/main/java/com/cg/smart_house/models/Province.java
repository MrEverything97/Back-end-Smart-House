package com.cg.smart_house.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "province")
@Data
public class Province {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

//    @OneToMany(targetEntity = Address.class, fetch = FetchType.EAGER)
//    private List<Address> addresses;
}
