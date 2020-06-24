package com.cg.smart_house.service;

import com.cg.smart_house.enumm.StatusOrders;
import com.cg.smart_house.model.Apartment;
import com.cg.smart_house.model.Order;

import java.util.Date;

public interface OrdersService {
    ServiceResult findALl();

    ServiceResult updateStatusOrders(Order orders);

    ServiceResult createOrders(Order orders);

    ServiceResult findAllOrdersByApartment(Long id);

    ServiceResult findAllOrderByStartTimeAndEndTime(Date minTime, Date maxTime);

    ServiceResult blockOrder(Order order);


}

