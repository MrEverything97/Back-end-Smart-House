package com.cg.smart_house.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import javax.persistence.*;
import java.util.List;



@Entity
@Data
public class Apartment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
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

    @OneToMany(mappedBy = "apartment")
    List<Comment> comments;

    public Apartment() {
    }

}
