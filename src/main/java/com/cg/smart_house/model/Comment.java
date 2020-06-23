package com.cg.smart_house.model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Data
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    @OneToOne
    private Order order;
    private String content;
    private Timestamp timestamp;

}
