package com.cg.smart_house.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.ArrayList;


@Entity
@Data
public class Apartment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Please not null name")
    private String name;
    private int bathroom;
    private int bedroom;
    private int priceByDate;
    private String description;

    @OneToMany(mappedBy = "apartment")
    private List<Picture> pictures;


    @OneToMany(mappedBy = "apartment")
    private List<Order> orders;

    @OneToOne(mappedBy = "apartment")
    private Address address;

    @OneToMany(mappedBy = "apartment")
    private List<Comment> comment;
//    @ManyToOne
////    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
////    @JoinColumn(name = "host_id")
////    private Host host;

    @ManyToOne
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany
    @JoinTable(
            name = "apartment_category",
            joinColumns = @JoinColumn(name = "apartment_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private List<Category> categories;

    @ManyToMany
    @JoinTable(
            name = "apartment_room_type",
            joinColumns = @JoinColumn(name = "apartment_id"),
            inverseJoinColumns = @JoinColumn(name = "room_type_id"))
    private List<RoomType> roomTypes;

    public Apartment() {
    }

}
