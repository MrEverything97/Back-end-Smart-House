package com.cg.smart_house.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
<<<<<<< HEAD
import java.util.ArrayList;
import java.util.List;
=======
>>>>>>> 5c756754e611cad23b6af99de1cf0587f25e6bc4

@Entity
@Data
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

<<<<<<< HEAD
    @ManyToMany(mappedBy = "categories")
    @JsonBackReference
    private List<Apartment> apartments ;

}
=======
    @ManyToOne
//    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @JoinColumn(name = "apartment_id")
    private Apartment apartment;
}
>>>>>>> 5c756754e611cad23b6af99de1cf0587f25e6bc4
