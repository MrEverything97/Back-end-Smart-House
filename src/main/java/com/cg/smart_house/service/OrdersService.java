package com.cg.smart_house.service;

import com.cg.smart_house.model.Order;

public interface OrdersService {
    ServiceResult findALl();
    ServiceResult updateStatusOrders(Order orders);
    ServiceResult createOrders(Order orders);
    ServiceResult findAllOrdersByApartment(Long id);
    ServiceResult blockOrder(Order order);

//    ServiceResult findAllOrderByStartTimeAndEndTime(Date minTime, Date maxTime);
}
