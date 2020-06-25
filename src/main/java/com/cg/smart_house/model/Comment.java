package com.cg.smart_house.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String comment;

    private Date startTimeRent;

    private Date endTimeRent;

    @ManyToOne
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @JoinColumn(name = "apartment_id")
    private Apartment apartment;

    @ManyToOne
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @JoinColumn(name = "user_id")
    private User user;

    public Comment() {
    }

    public Comment(String comment, Date startTimeRent, Date endTimeRent, Apartment apartment, User user){
        this.comment = comment;
        this.startTimeRent = startTimeRent;
        this.endTimeRent = endTimeRent;
        this.apartment = apartment;
        this.user = user;
    }
}
