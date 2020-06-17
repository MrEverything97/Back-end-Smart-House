package com.cg.smart_house.models;

import javax.persistence.*;
import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "apartment")
public class Apartment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private int bathroom;
    private int bedroom;
    private String description;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "apartment_roomtype",
               joinColumns = {@JoinColumn(name = "apartment_id")},
               inverseJoinColumns = {@JoinColumn(name = "roomtype_id")})
    private List<RoomType> roomTypes;

    @OneToMany(targetEntity = Picture.class, fetch = FetchType.EAGER)
    private List<Picture> pictures;

    @OneToMany(targetEntity = Category.class, fetch = FetchType.EAGER)
    private List<Category> categories;

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public List<Picture> getPictures() {
        return pictures;
    }

    public void setPictures(List<Picture> pictures) {
        this.pictures = pictures;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBathroom() {
        return bathroom;
    }

    public void setBathroom(int bathroom) {
        this.bathroom = bathroom;
    }

    public int getBedroom() {
        return bedroom;
    }

    public void setBedroom(int bedroom) {
        this.bedroom = bedroom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<RoomType> getRoomTypes() {
        return roomTypes;
    }

    public void setRoomTypes(List<RoomType> roomTypes) {
        this.roomTypes = roomTypes;
    }
}
