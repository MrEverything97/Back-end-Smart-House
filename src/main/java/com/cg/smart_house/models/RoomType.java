package com.cg.smart_house.models;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "roomtype")
@Data
public class RoomType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

    @ManyToMany(mappedBy = "roomTypes")
    private List<Apartment> apartments;
}
