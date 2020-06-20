package com.cg.smart_house.model;

import lombok.Data;
import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Data
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String name;
    private String phone;

    @OneToMany
    @JoinColumn(name = "customer_id")
    private List<Order> orders;
}
