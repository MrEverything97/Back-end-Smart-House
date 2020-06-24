package com.cg.smart_house.repository;

import com.cg.smart_house.model.Apartment;
import com.cg.smart_house.model.Order;
import com.cg.smart_house.enumm.StatusOrders;
import com.cg.smart_house.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface OrdersRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByApartment(Apartment apartment);

    @Query(value = "select a from Order a where :minTime <= a.startTime and  a.endTime <= :maxTime")
    List<Order> getAllByStartTimeAndEndTime(@Param("minTime") Date minTime,
                                            @Param("maxTime") Date maxTime);

    @Query(value = "select a from Order a where :minTime <= a.startTime and  a.endTime <= :maxTime")
    List<Order> getAllByStartTimeAndEndTimeNoParam( Date minTime, Date maxTime);

    Order findByApartmentAndUserAndStatusOrders(Apartment apartment,User user,StatusOrders statusOrders);
}
