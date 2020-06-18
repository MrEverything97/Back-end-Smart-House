package com.cg.smart_house.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@Table(name = "orders")
public class Order implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Date startTime;
    private Date endTime;
    private Long totalMoney;

    @ManyToOne
    private Apartment apartment;

    @ManyToOne
    private Customer customers;
}
