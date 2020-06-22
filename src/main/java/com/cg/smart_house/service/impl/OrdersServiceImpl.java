package com.cg.smart_house.service.impl;

import com.cg.smart_house.model.Apartment;
import com.cg.smart_house.model.Order;
import com.cg.smart_house.model.StatusOrders;
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

    //Update trang thai nha
    @Override
    public ServiceResult updateStatusOrders(Order orders) {
        ServiceResult serviceResult = new ServiceResult();
        Optional<Order> orders1 = ordersRepository.findById(orders.getId());
        if (!orders1.isPresent()){
            serviceResult.setStatus(ServiceStatus.FAILED);
            serviceResult.setMessage("Orders not found");
            return serviceResult;
        } else {
            orders.setStatusOrders(orders.getStatusOrders());
            ordersRepository.save(orders);
            serviceResult.setMessage("Update status orders done");
        }
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
    public ServiceResult findAllOrderByStartTimeAndEndTime(Date minTime, Date maxTime) {
        ServiceResult serviceResult = new ServiceResult();
        List<Order> ordersList = ordersRepository.getAllByStartTimeAndEndTime(minTime, maxTime);
        if (ordersList.isEmpty()){
            serviceResult.setMessage("No found orders");
        } else {
            serviceResult.setData(ordersRepository.getAllByStartTimeAndEndTime(minTime,maxTime));
        }
        return serviceResult;
    }

//    @Override
//    public ServiceResult findAllOrderByStartTimeAndEndTime(Date minTime, Date maxTime) {
//        ServiceResult serviceResult = new ServiceResult();
//        List<Order> ordersList = ordersRepository.getAllByStartTimeAndEndTime(minTime, maxTime);
//        if (ordersList.isEmpty()){
//            serviceResult.setMessage("No found orders");
//            return serviceResult;
//        } else {
//            serviceResult.setData(ordersRepository.getAllByStartTimeAndEndTime(  minTime,maxTime));
//        }
//        return serviceResult;
//    }


    @Override
    public ServiceResult findAllApartmentRanting() {
        ServiceResult serviceResult = new ServiceResult();
        List<Order> apartments = ordersRepository.findAllByStatusOrders(StatusOrders.RENTING);
        if (apartments.isEmpty()) {
            serviceResult.setMessage("Not found");
        }
        serviceResult.setData(apartments);
        return serviceResult;
    }

    @Override
    public ServiceResult createOrders(Order orders) {
        ServiceResult serviceResult = new ServiceResult();
        serviceResult.setStatus(ServiceStatus.FAILED);

        Long idApartment = orders.getApartment().getId();
        Optional<Apartment> apartment = apartmentRepository.findById(idApartment);

        // Không có nhà để cho thuê
        if (!apartment.isPresent()) {
            serviceResult.setMessage("No apartment have been orders");
            return serviceResult;
        }

        //Khai bao ngay bat dau
        Date startTimeOrders = orders.getStartTime();
        //khai bao ngay ket thuc
        Date endTimeOrders = orders.getEndTime();
        //Khai bao ngay hien tai
        Date nowDate = new Date();

        //Lay ngay theo lich
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        //Ap dung so sanh
        c1.setTime(startTimeOrders);
        c2.setTime(endTimeOrders);
        //Truoc khi so sanh. Tao so nguyen kieu Long lay du lieu
        //tu Date da tao truoc do. Su dung phuong thuc GetTime().
        long countDayOrders = (c2.getTime().getTime() - c1.getTime().getTime()) / (24 * 3600 * 1000);
        //Tinh gia phong
        Long priceApartment = apartment.get().getPriceByDate() * countDayOrders;


        List<Order> listOrders = ordersRepository.findAllByApartment(apartment.get());
        if (listOrders.isEmpty()) {
            return saveOrdersWithEmptyApartment(orders, serviceResult, priceApartment);
        } else
            return saveOrdersWithFullApartment(orders, serviceResult, startTimeOrders, endTimeOrders, nowDate, priceApartment, listOrders);
    }

    @Override
    public ServiceResult blockOrder(Order order) {
        order.setStatusOrders(StatusOrders.BLOCK);
        ServiceResult serviceResult = new ServiceResult();
        serviceResult.setStatus(ServiceStatus.FAILED);

        Long idApartment = order.getApartment().getId();
        Optional<Apartment> apartment = apartmentRepository.findById(idApartment);

        // Không có nhà để cho thuê
        if (!apartment.isPresent()) {
            serviceResult.setMessage("No apartment have been orders");
            return serviceResult;
        }

        Date startTimeOrders = order.getStartTime();
        Date endTimeOrders = order.getEndTime();
        Date nowDate = new Date();

        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(startTimeOrders);
        c2.setTime(endTimeOrders);
        long countDayOrders = (c2.getTime().getTime() - c1.getTime().getTime()) / (24 * 3600 * 1000);
        // Set 0 because of blocking
        Long priceApartment = 0L;


        List<Order> listOrders = ordersRepository.findAllByApartment(apartment.get());
        if (listOrders.isEmpty()) {
            return saveOrdersWithEmptyApartment(order, serviceResult, priceApartment);
        } else
            return saveOrdersWithFullApartment(order, serviceResult, startTimeOrders, endTimeOrders, nowDate, priceApartment, listOrders);
    }

    private ServiceResult saveOrdersWithFullApartment(Order orders, ServiceResult serviceResult, Date startTimeOrders, Date endTimeOrders, Date nowDate, Long priceApartment, List<Order> listOrders) {
        Collections.sort(listOrders);
        int sizeList = listOrders.size() - 1;
        for (int i = 0; i <= sizeList; i++) {
            if ((startTimeOrders.after(nowDate) && endTimeOrders.before(listOrders.get(0).getStartTime()))
                    || startTimeOrders.after(listOrders.get(sizeList).getEndTime())
                    || (startTimeOrders.after(listOrders.get(i).getEndTime()) && endTimeOrders.before(listOrders.get(i + 1).getStartTime()))){
                serviceResult.setMessage("Success orders apartment");
                return saveOrdersWithPrice(orders, serviceResult, priceApartment);
            }
        }
        return serviceResult;
    }

    private ServiceResult saveOrdersWithEmptyApartment(Order orders, ServiceResult serviceResult, Long priceApartment) {
        serviceResult.setMessage("No apartment orders by customer, order success");
        return saveOrdersWithPrice(orders, serviceResult, priceApartment);
    }

    private ServiceResult saveOrdersWithPrice(Order orders, ServiceResult serviceResult, Long priceApartment) {
        orders.setTotalMoney(priceApartment);
        serviceResult.setData(ordersRepository.save(orders));
        serviceResult.setStatus(ServiceStatus.SUCCESS);
        return serviceResult;
    }

}
