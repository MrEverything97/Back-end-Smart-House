package com.cg.smart_house.repository;

import com.cg.smart_house.models.Apartment;
import com.cg.smart_house.models.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Long> {
    List<Orders> findAllByApartment(Apartment apartment);

//    @Query(value = "select * from Orders where startTime > minTime and endTime > maxTime")
//    List<Orders> findAllByStartTimeAfterAndEndTimeBefore(Date minTime, Date maxTime);

 }
