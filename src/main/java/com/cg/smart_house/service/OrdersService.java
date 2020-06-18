package com.cg.smart_house.service;

import com.cg.smart_house.models.Apartment;
import com.cg.smart_house.models.Orders;
import com.cg.smart_house.service.ServiceResult;

import java.util.Optional;

public interface OrdersService {
    ServiceResult createOrders(Orders orders, Apartment apartment);
    ServiceResult findAllOrdersByApartment(Apartment apartment);

}
