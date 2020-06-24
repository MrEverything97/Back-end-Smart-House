package com.cg.smart_house.service.impl;

import com.cg.smart_house.enumm.ServiceStatus;
import com.cg.smart_house.enumm.StatusOrders;
import com.cg.smart_house.model.Apartment;
import com.cg.smart_house.model.Comment;
import com.cg.smart_house.model.Order;
import com.cg.smart_house.model.User;
import com.cg.smart_house.repository.CommentRepository;
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

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public ServiceResult createComment(Comment comment) {
        ServiceResult serviceResult = new ServiceResult();
        serviceResult.setStatus(ServiceStatus.FAILED);

        Order findOrder = ordersRepository.findByApartmentAndUserAndStatusOrders(comment.getApartment(), comment.getUser(), StatusOrders.RENTED);
        Comment findComment = commentRepository.findByUserAndApartment(comment.getUser(), comment.getApartment());

        if (findOrder == null || findComment != null) {
            serviceResult.setMessage("Don't comment");
        }
        serviceResult.setStatus(ServiceStatus.SUCCESS);
        serviceResult.setData(commentRepository.save(comment));
        serviceResult.setMessage("Done comment");
        return serviceResult;
    }
}
