package com.cg.smart_house.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "host")
public class Host {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    private String password;
    private String email;
    private String phone;

    @OneToMany(targetEntity = Apartment.class, fetch = FetchType.EAGER)
    private List<Apartment> apartments;
}
