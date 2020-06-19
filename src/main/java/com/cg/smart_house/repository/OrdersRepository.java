package com.cg.smart_house.repository;

import com.cg.smart_house.model.Order;
import com.cg.smart_house.model.Apartment;
import com.cg.smart_house.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface OrdersRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByApartment(Apartment apartment);

//    @Query(value = "select * from Orders where startTime > minTime and endTime > maxTime")
//    List<Orders> findAllByStartTimeAfterAndEndTimeBefore(Date minTime, Date maxTime);

 }
