package com.cg.smart_house.repository;

import com.cg.smart_house.model.Apartment;
import com.cg.smart_house.model.Order;
import com.cg.smart_house.model.StatusOrders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;
import java.time.LocalDate;
import java.util.Collection;
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

//    @Query(value = "select a from Order a where :minTime <= a.startTime and  a.endTime <= :maxTime and a.apartment")
//    List<Order> getAllByStartTimeAndEndTimeAndApartment( Date minTime, Date maxTime, Apartment apartment);

    List<Order> findAllByStatusOrders(StatusOrders statusOrders);

//    @Query(value = "select  from Order join Apartment b where Order.apartment= Apartment.id  ")
//    List<Order> getAll();
}
