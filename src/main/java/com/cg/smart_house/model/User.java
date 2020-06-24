package com.cg.smart_house.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table( uniqueConstraints = @UniqueConstraint(columnNames = "username"))
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String name;
    private String email;
    private String phone;

    @OneToMany(mappedBy = "user")
//    @JoinColumn(name = "user_id")
    private List<Order> orders;

    @OneToMany(mappedBy = "user")
//    @JsonIgnore
    private List<Apartment> apartments;

    @ManyToOne
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @JoinColumn(name = "role_id")
    private Role role;


    public User(){};
    public User(String name, String username, String email, String phone, String password) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.password = password;
    }


}
