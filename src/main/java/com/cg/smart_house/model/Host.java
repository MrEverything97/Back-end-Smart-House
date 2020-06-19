package com.cg.smart_house.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import javax.persistence.*;
import java.util.Set;

@Entity
@Data
public class Host {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    private String password;
    private String email;
    private String phone;

    @OneToMany(mappedBy = "hosts")
    @JsonIgnore
    private Set<Apartment> apartment;
}
