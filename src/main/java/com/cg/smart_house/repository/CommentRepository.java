package com.cg.smart_house.repository;

import com.cg.smart_house.model.Apartment;
import com.cg.smart_house.model.Comment;
import com.cg.smart_house.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;


@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {
    Comment findByUserAndApartmentAndStartTimeRent(User user, Apartment apartment, Date startTimeRent);

    List<Comment> findAllByApartment(Apartment apartment);
}
