package com.cg.smart_house.model;

import lombok.Data;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "apartment", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Picture> pictures;

    @OneToMany(mappedBy = "apartment", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Category> categories;

    @OneToMany(mappedBy = "apartment", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Order> orders;

    @OneToOne(mappedBy = "apartment")
    private Address address;

    @ManyToOne
    private Host hosts;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "apartment_room_type",
            joinColumns = @JoinColumn(name = "apartment_id"),
            inverseJoinColumns = @JoinColumn(name = "room_type_id"))
    private List<RoomType> roomTypes = new ArrayList<>();

    public Apartment() {
    }

    public Apartment(String name, int bathroom, int bedroom, int priceByDate, String description) {
        this.name = name;
        this.bathroom = bathroom;
        this.priceByDate = priceByDate;
        this.description = description;
    }
}
