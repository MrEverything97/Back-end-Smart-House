package com.cg.smart_house.service.Impl;

import com.cg.smart_house.models.Apartment;
import com.cg.smart_house.models.Orders;
import com.cg.smart_house.repository.ApartmentRepository;
import com.cg.smart_house.repository.OrdersRepository;
import com.cg.smart_house.service.OrdersService;
import com.cg.smart_house.service.ServiceResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OrdersServiceImpl implements OrdersService {
    @Autowired
    private ApartmentRepository apartmentRepository;

    @Autowired
    private OrdersRepository ordersRepository;

    @Override
    public ServiceResult findAllOrdersByApartment(Apartment apartment) {
        ServiceResult serviceResult = new ServiceResult();
        Optional<Apartment> apartment1 = apartmentRepository.findById(apartment.getId());
        if (apartment1.isPresent()) {
            serviceResult.setMessage("Apartment no found");
            return serviceResult;
        }
        serviceResult.setData(ordersRepository.findAllByApartment(apartment.getId()));
        return serviceResult;
    }

    @Override
    public ServiceResult createOrders(Orders orders, Apartment apartment) {
        ServiceResult serviceResult = new ServiceResult();

        List<Orders> listOrders = ordersRepository.findAllByApartment(apartment.getId());

        Optional<Apartment> apartment1 = apartmentRepository.findById(apartment.getId());

        if (!apartment1.isPresent()) {
            serviceResult.setMessage("No apartment have been orders");
            return serviceResult;
        }
        if (listOrders.isEmpty()) {
            serviceResult.setMessage("No apartment orders by customer, order success");
            serviceResult.setData(ordersRepository.save(orders));
            return serviceResult;
        }

        Date startTimeOrders = orders.getStartTime();
        Date endTimeOrders = orders.getEndTime();
        int size = listOrders.size() - 1;
        if (endTimeOrders.before(listOrders.get(0).getStartTime()) || startTimeOrders.after(listOrders.get(size).getEndTime())) {
            serviceResult.setMessage("Success orders apartment");
            serviceResult.setData(ordersRepository.save(orders));
            return serviceResult;
        } else {
            for (int i = 1; i <= listOrders.size() - 2; i++) {
                if (startTimeOrders.before(listOrders.get(i).getEndTime()) && endTimeOrders.after(listOrders.get(i).getStartTime())) {
                    serviceResult.setMessage("Success orders apartment");
                    serviceResult.setData(orders);
                    return serviceResult;
                }
            }
        }
        return serviceResult;
    }
}


