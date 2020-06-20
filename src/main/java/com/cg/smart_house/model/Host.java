package com.cg.smart_house.model;

import lombok.Data;
import javax.persistence.*;
import java.util.Set;

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

<<<<<<< HEAD
    @OneToMany(mappedBy = "host")
    private Set<Apartment> apartment;
}
=======
    @OneToMany(mappedBy = "hosts")
    private Set<Apartment> apartment;
}
>>>>>>> 5c756754e611cad23b6af99de1cf0587f25e6bc4
