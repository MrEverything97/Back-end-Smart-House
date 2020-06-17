package com.cg.smart_house.models;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
public class Orders implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Date startTime;
    private Date endTime;
    private int totalMoney;

    @ManyToOne
    @JoinColumn
    private Apartment apartment;

    @ManyToOne
    @JoinColumn
    private Customer customers;
}
