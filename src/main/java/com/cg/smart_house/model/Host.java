package com.cg.smart_house.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "host")
@Data
public class Host {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    private String password;
    private String email;
    private String phone;

//    @OneToMany(mappedBy = "hosts")
//    private Set<Apartment> apartment;
}
