package com.cg.smart_house.models;

import lombok.Data;
import javax.persistence.*;
import java.util.Set;

@Entity
@Data
public class Apartment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private int bathroom;

    private int bedroom;
    private int priceByDate;
    private String description;

    @OneToMany
    private Set<Picture> pictures;

    @OneToMany
    private Set<Status> statuses;

    @OneToMany
    private Set<Category> categories;

    @OneToMany
    private Set<Order> orders;

    @ManyToOne
    private Host hosts;

    @OneToOne
    private Address address;

    @ManyToMany
    Set<RoomType> roomTypes;

}
