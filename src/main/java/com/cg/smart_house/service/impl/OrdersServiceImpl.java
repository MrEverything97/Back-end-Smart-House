package com.cg.smart_house.service.impl;

import com.cg.smart_house.model.Apartment;
import com.cg.smart_house.model.Order;
import com.cg.smart_house.enumm.StatusOrders;
import com.cg.smart_house.model.User;
import com.cg.smart_house.repository.ApartmentRepository;
import com.cg.smart_house.repository.OrdersRepository;
import com.cg.smart_house.repository.UserRepository;
import com.cg.smart_house.service.OrdersService;
import com.cg.smart_house.service.ServiceResult;
import com.cg.smart_house.enumm.ServiceStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OrdersServiceImpl implements OrdersService {
    @Autowired
    private ApartmentRepository apartmentRepository;

    @Autowired
    private OrdersRepository ordersRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public ServiceResult findALl() {
        ServiceResult serviceResult = new ServiceResult();
        serviceResult.setData(ordersRepository.findAll());
        return serviceResult;
    }

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

        List<Apartment> apartmentList = apartmentRepository.findAll();
        List<Order> orderListByDate = ordersRepository.getAllByStartTimeAndEndTime(minTime, maxTime);
        if (orderListByDate.isEmpty()) {
            serviceResult.setData(apartmentList);
            return serviceResult;
        } else {
            for (Order order : orderListByDate) {
                apartmentList.remove(order.getApartment());
            }
            serviceResult.setData(apartmentList);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult createOrders(Order orders, User user) {
        ServiceResult serviceResult = new ServiceResult();
        serviceResult.setStatus(ServiceStatus.FAILED);

        Long idApartment = orders.getApartment().getId();
        Optional<Apartment> apartment = apartmentRepository.findById(idApartment);

        // Không có nhà để cho thuê
        if (!apartment.isPresent()) {
            serviceResult.setMessage("No apartment have been orders");
            return serviceResult;
        }

        Date startTimeOrders = orders.getStartTime();
        Date endTimeOrders = orders.getEndTime();
        Date nowDate = new Date();

        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(startTimeOrders);
        c2.setTime(endTimeOrders);
        long countDayOrders = (c2.getTime().getTime() - c1.getTime().getTime()) / (24 * 3600 * 1000);
        Long priceApartment = apartment.get().getPriceByDate() * countDayOrders;


        List<Order> listOrders = ordersRepository.findAllByApartment(apartment.get());
        if (listOrders.isEmpty()) {
            return saveOrdersWithEmptyApartment(orders, serviceResult, priceApartment,user);
        } else
            return saveOrdersWithFullApartment(orders, serviceResult, startTimeOrders, endTimeOrders, nowDate, priceApartment, listOrders,user);
    }

    @Override
    public ServiceResult blockOrder(Order order,User host) {

        order.setStatusOrders(StatusOrders.BLOCK);
        ServiceResult serviceResult = new ServiceResult();

        Long idApartment = order.getApartment().getId();
        Optional<Apartment> apartmentOptional = apartmentRepository.findById(idApartment);

        // Không có nhà để cho thuê
        if (!apartmentOptional.isPresent()) {
            serviceResult.setMessage("No apartment have been orders");
            return serviceResult;
        }
        Optional<User> optionalUser = userRepository.findByUsername(host.getName());
        if(!optionalUser.isPresent()) {
            serviceResult.setStatus(ServiceStatus.FAILED);
            return serviceResult;
        }
        host = optionalUser.get();
        List<Apartment> hostApartmentList = apartmentRepository.findAllByUser_Id(host.getId());
        boolean flag = false;
        for(int i = 0; i < hostApartmentList.size(); i++){
            if(hostApartmentList.get(i).getId().equals(idApartment)){
                flag = true;
                break;
            }
        }
        if(!flag){
            serviceResult.setStatus(ServiceStatus.FAILED);
            serviceResult.setMessage("Forbidden");
            return serviceResult;
        }
        Date startTimeOrders = order.getStartTime();
        Date endTimeOrders = order.getEndTime();
        Date nowDate = new Date();
        // Set 0 because of blocking
        Long priceApartment = 0L;


        List<Order> listOrders = ordersRepository.findAllByApartment(apartmentOptional.get());
        if (listOrders.isEmpty()) {
            return saveOrdersWithEmptyApartment(order, serviceResult, priceApartment,host);
        } else
            return saveOrdersWithFullApartment(order, serviceResult, startTimeOrders, endTimeOrders, nowDate, priceApartment, listOrders,host);
    }

    @Override
    public ServiceResult findOrderByUserAndApartmentAndStatusPENDING(Long idUser, Long idApartment) {
        ServiceResult serviceResult = new ServiceResult();

        Optional<User> userOptional = userRepository.findById(idUser);
        if (!userOptional.isPresent()){
            serviceResult.setMessage("No found user");
        }
        Optional<Apartment> apartmentOptional = apartmentRepository.findById(idApartment);
        if (!apartmentOptional.isPresent()){
            serviceResult.setMessage("No found apartment");
        }
        Order order = ordersRepository.findByUserAndApartmentAndStatusOrders(userOptional, apartmentOptional, StatusOrders.PENDING);
        serviceResult.setData(order);
        return serviceResult;
    }

//    @Override
//    public ServiceResult deleteOrder(Order order) {
//        ServiceResult serviceResult = new ServiceResult();
//        serviceResult.setStatus(ServiceStatus.FAILED);
//
//        Optional<Order> orderOptional = ordersRepository.findById(order.getId());
//        if (!orderOptional.isPresent()){
//            serviceResult.setMessage("No found order");
//        }
//        Date nowDate = new Date();
//        long getDiff = order.getStartTime()- nowDate.getTime();
//
//        long getDaysDiff = getDiff / (24 * 60 * 60 * 1000);
//        return null;
//    }


    private ServiceResult saveOrdersWithFullApartment(Order orders, ServiceResult serviceResult, Date startTimeOrders, Date endTimeOrders, Date nowDate, Long priceApartment, List<Order> listOrders, User user ) {
        Collections.sort(listOrders);
        int sizeList = listOrders.size() - 1;
        for (int i = 0; i <= sizeList; i++) {
            if ((startTimeOrders.after(nowDate) && endTimeOrders.before(listOrders.get(0).getStartTime()))
                    || startTimeOrders.after(listOrders.get(sizeList).getEndTime())
                    || (startTimeOrders.after(listOrders.get(i).getEndTime()) && endTimeOrders.before(listOrders.get(i + 1).getStartTime()))) {
                serviceResult.setMessage("Success orders apartment");
                return saveOrdersWithPrice(orders, serviceResult, priceApartment,user);
            }
        }
        serviceResult.setStatus(ServiceStatus.FAILED);
        return serviceResult;
    }

    private ServiceResult saveOrdersWithEmptyApartment(Order orders, ServiceResult serviceResult, Long priceApartment, User user) {
        serviceResult.setMessage("No apartment orders by customer, order success");
        return saveOrdersWithPrice(orders, serviceResult, priceApartment, user);
    }

    private ServiceResult saveOrdersWithPrice(Order orders, ServiceResult serviceResult, Long priceApartment, User user) {
        orders.setTotalMoney(priceApartment);
        orders.setUser(user);
        orders.setStatusOrders(StatusOrders.PENDING);
        serviceResult.setData(ordersRepository.save(orders));
        serviceResult.setStatus(ServiceStatus.SUCCESS);
        return serviceResult;
    }
}


