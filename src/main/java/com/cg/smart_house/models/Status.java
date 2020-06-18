package com.cg.smart_house.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "status")
@Data
public class Status {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Boolean status;
    private Date startTime;
    private Date endTime;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "apartment_id")
    private Apartment apartment;

}
