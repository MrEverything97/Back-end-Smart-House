package com.cg.smart_house.service.impl;

import com.cg.smart_house.enumm.ServiceStatus;
import com.cg.smart_house.model.Apartment;
import com.cg.smart_house.model.Order;
import com.cg.smart_house.model.User;
import com.cg.smart_house.repository.OrdersRepository;
import com.cg.smart_house.service.CommentService;
import com.cg.smart_house.service.ServiceResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private OrdersRepository ordersRepository;

    @Override
    public ServiceResult createComment(Apartment apartment, User user) {
        ServiceResult serviceResult = new ServiceResult();
        serviceResult.setStatus(ServiceStatus.FAILED);

        List<Order> orderList = ordersRepository.findAllByUser(user);
        if (orderList.isEmpty()){
            serviceResult.setMessage("Don't comment");
        }

        return null;
    }
}
