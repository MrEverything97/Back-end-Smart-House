package com.cg.smart_house.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "room_type")
@Data
public class RoomType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

//    @ManyToMany(mappedBy = "roomTypes")
//    private List<Apartment> apartments;
}
