package com.cg.smart_house.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "room_type")
@Data
public class RoomType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

    @ManyToMany(mappedBy = "roomTypes")
    @JsonBackReference
    private List<Apartment> apartments = new ArrayList<>();
<<<<<<< HEAD
}
=======
}
>>>>>>> 5c756754e611cad23b6af99de1cf0587f25e6bc4
