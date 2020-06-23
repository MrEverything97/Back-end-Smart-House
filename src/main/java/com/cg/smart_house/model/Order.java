package com.cg.smart_house.model;

import com.cg.smart_house.enumm.StatusOrders;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@Table(name = "`order`")
public class Order implements Serializable,Comparable<Order> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date startTime;
    private Date endTime;
    private Long totalMoney;
    private StatusOrders statusOrders = StatusOrders.NOT_RENTED;

    @ManyToOne
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @JoinColumn(name = "apartment_id")
    private Apartment apartment;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "user_id")
    private User user;

    @Override
    public int compareTo(Order order) {
        return getStartTime().compareTo(order.getStartTime());
    }
}
