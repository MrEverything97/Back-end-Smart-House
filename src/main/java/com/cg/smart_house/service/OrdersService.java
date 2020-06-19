package com.cg.smart_house.service;

import com.cg.smart_house.models.Apartment;
import com.cg.smart_house.models.Orders;
import com.cg.smart_house.service.ServiceResult;

import java.util.Date;
import java.util.Optional;

public interface OrdersService {
    ServiceResult findALl();
    ServiceResult updateStatusOrders(Orders orders);
    ServiceResult createOrders(Orders orders);
    ServiceResult findAllOrdersByApartment(Long id);
//    ServiceResult findAllOrderByStartTimeAndEndTime(Date minTime, Date maxTime);
}
