package com.cg.smart_house.service;

import com.cg.smart_house.model.Apartment;
import com.cg.smart_house.model.Order;

public interface OrdersService {
    ServiceResult findALl();
    ServiceResult updateStatusOrders(Order order);
    ServiceResult createOrders(Order order);
    ServiceResult findAllOrdersByApartment(Long id);
//    ServiceResult findAllOrderByStartTimeAndEndTime(Date minTime, Date maxTime);
}
