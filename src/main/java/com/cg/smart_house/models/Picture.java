package com.cg.smart_house.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Picture {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String imageUrl;

    @ManyToOne
    @JsonIgnore
    @JoinColumn
    private Apartment apartment;

}
