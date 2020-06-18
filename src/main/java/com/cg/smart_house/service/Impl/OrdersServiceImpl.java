package com.cg.smart_house.service.Impl;

import com.cg.smart_house.models.Apartment;
import com.cg.smart_house.models.Orders;
import com.cg.smart_house.repository.ApartmentRepository;
import com.cg.smart_house.repository.OrdersRepository;
import com.cg.smart_house.service.OrdersService;
import com.cg.smart_house.service.ServiceResult;
import com.cg.smart_house.service.ServiceStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OrdersServiceImpl implements OrdersService {
    @Autowired
    private ApartmentRepository apartmentRepository;

    @Autowired
    private OrdersRepository ordersRepository;

    @Override
    public ServiceResult findALl() {
        ServiceResult serviceResult = new ServiceResult();
        serviceResult.setData(ordersRepository.findAll());
        return serviceResult;
    }

    @Override
    public ServiceResult findAllOrdersByApartment(Long id) {
        ServiceResult serviceResult = new ServiceResult();
        Optional<Apartment> apartment = apartmentRepository.findById(id);
        if (!apartment.isPresent()) {
            serviceResult.setMessage("Apartment no found");
            return serviceResult;
        } else {
            serviceResult.setData(ordersRepository.findAllByApartment(apartment.get()));
        }
        return serviceResult;
    }

    @Override
    public ServiceResult createOrders(Orders orders) {
        ServiceResult serviceResult = new ServiceResult();

        Long idApartment = orders.getApartment().getId();

        Optional<Apartment> apartment = apartmentRepository.findById(idApartment);

        serviceResult.setStatus(ServiceStatus.FAILED);

        if (!apartment.isPresent()) {
            serviceResult.setMessage("No apartment have been orders");
            return serviceResult;
        }
        List<Orders> listOrders = ordersRepository.findAllByApartment(apartment.get());
        if (listOrders.isEmpty()) {
            serviceResult.setMessage("No apartment orders by customer, order success");
            serviceResult.setData(ordersRepository.save(orders));
            serviceResult.setStatus(ServiceStatus.SUCCESS);
            return serviceResult;
        }

        Date startTimeOrders = orders.getStartTime();
        Date endTimeOrders = orders.getEndTime();

        Collections.sort(listOrders);
        int size = listOrders.size() - 1;

        if (endTimeOrders.before(listOrders.get(0).getStartTime()) || startTimeOrders.after(listOrders.get(size).getEndTime())) {
            serviceResult.setMessage("Success orders apartment");
            serviceResult.setData(ordersRepository.save(orders));
            serviceResult.setStatus(ServiceStatus.SUCCESS);
            return serviceResult;
        } else {
            for (int i = 1; i <= listOrders.size() - 1; i++) {
                if (startTimeOrders.before(listOrders.get(i).getEndTime()) && endTimeOrders.after(listOrders.get(i).getStartTime())) {
                    serviceResult.setMessage("Success orders apartment");
                    serviceResult.setData(ordersRepository.save(orders));
                    serviceResult.setStatus(ServiceStatus.SUCCESS);
                    return serviceResult;
                }
            }
        }
        return serviceResult;
    }
}


