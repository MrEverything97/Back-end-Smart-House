package com.cg.smart_house.model;

import lombok.Data;
import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Data
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    private String password;
    private String name;
    private String phone;

    @OneToMany(mappedBy = "customers")
    private List<Order> orders;
}
