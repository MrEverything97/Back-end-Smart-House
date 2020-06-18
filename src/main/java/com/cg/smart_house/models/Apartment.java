package com.cg.smart_house.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import javax.persistence.*;
import java.util.List;
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

    @OneToMany(mappedBy = "apartment")
    @JsonIgnore
    private Set<Picture> pictures;

    @OneToMany(mappedBy = "apartment")
    private List<Status> statuses;

    @OneToMany(mappedBy = "apartment")
    @JsonIgnore
    private List<Category> categories;

    @OneToMany(mappedBy = "apartment")
    private Set<Order> orders;

    @OneToOne(mappedBy = "apartment")
    private Address address;

    @ManyToOne
    @JoinColumn(name = "host_id")
    private Host hosts;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "apartment_room_type",
            joinColumns = @JoinColumn(name = "apartment_id"),
            inverseJoinColumns = @JoinColumn(name = "room_type_id"))
    private List<RoomType> roomTypes;
}
