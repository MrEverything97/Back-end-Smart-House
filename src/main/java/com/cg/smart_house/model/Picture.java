package com.cg.smart_house.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Picture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String imageUrl;

    @ManyToOne
<<<<<<< HEAD
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
=======
//    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
>>>>>>> 5c756754e611cad23b6af99de1cf0587f25e6bc4
    @JoinColumn(name = "apartment_id")
    private Apartment apartment;

}