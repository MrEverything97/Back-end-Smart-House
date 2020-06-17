package com.cg.smart_house.models;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Entity
@Table(name = "apartment")
@Data
public class Apartment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank(message = "Please not null name")
    private String name;

    private int bathroom;
    private int bedroom;
    private int priceByDate;
    private String description;

    @OneToMany(mappedBy = "apartment")
    private Set<Picture> pictures;

    @OneToMany(mappedBy = "apartment")
    private Set<Status> statuses;

    @OneToMany(mappedBy = "apartment")
    private Set<Category> categories;

    @OneToMany(mappedBy = "apartment")
    private Set<Orders> orders;

    @OneToOne(mappedBy = "apartment")
    private Address address;

    @ManyToOne
    @JoinColumn(name = "host_id")
    private Host hosts;

    @ManyToMany
    @JoinTable(
            name = "apartment_room_type",
            joinColumns = @JoinColumn(name = "apartment_id"),
            inverseJoinColumns = @JoinColumn(name = "room_type_id"))
    Set<RoomType> roomTypes;

    public Apartment() {
    }
}
