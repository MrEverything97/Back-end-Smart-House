package com.cg.smart_house.models;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "apartment")
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

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "apartment_roomtype",
               joinColumns = {@JoinColumn(name = "apartment_id")},
               inverseJoinColumns = {@JoinColumn(name = "roomtype_id")})
    private List<RoomType> roomTypes;

    @OneToMany(targetEntity = Picture.class, fetch = FetchType.EAGER)
    private List<Picture> pictures;

    @OneToMany(targetEntity = Status.class, fetch = FetchType.EAGER)
    private List<Status> statuses;

    @OneToMany(targetEntity = Category.class, fetch = FetchType.EAGER)
    private List<Category> categories;

    @OneToMany(targetEntity = Orders.class, fetch = FetchType.EAGER)
    private List<Orders> orders;
}
