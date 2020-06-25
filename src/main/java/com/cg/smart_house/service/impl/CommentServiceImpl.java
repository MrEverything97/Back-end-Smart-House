package com.cg.smart_house.service.impl;

import com.cg.smart_house.enumm.ServiceStatus;
import com.cg.smart_house.enumm.StatusOrders;
import com.cg.smart_house.model.Apartment;
import com.cg.smart_house.model.Comment;
import com.cg.smart_house.model.Order;
import com.cg.smart_house.model.User;
import com.cg.smart_house.repository.ApartmentRepository;
import com.cg.smart_house.repository.CommentRepository;
import com.cg.smart_house.repository.OrdersRepository;
import com.cg.smart_house.repository.UserRepository;
import com.cg.smart_house.service.CommentService;
import com.cg.smart_house.service.ServiceResult;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private OrdersRepository ordersRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserRepository userRepository;


    @Autowired
    private ApartmentRepository apartmentRepository;

    @Override
    public ServiceResult createComment(Comment comment, Long idApartment, Long idUser) {
        ServiceResult serviceResult = new ServiceResult();
        serviceResult.setStatus(ServiceStatus.FAILED);

        Optional<Apartment> apartmentOptional = apartmentRepository.findById(idApartment);
        Optional<User> userOptional = userRepository.findById(idUser);
        if (!userOptional.isPresent() || !apartmentOptional.isPresent())
        {
            serviceResult.setMessage("Don't comment");
        } else {
            List<Order> findAllOrder = ordersRepository.findAllByUserAndApartmentAndStatusOrders(userOptional, apartmentOptional, StatusOrders.RENTED);
            if (findAllOrder.isEmpty()){
                serviceResult.setMessage("Don't comment");
            }
            for (Order order: findAllOrder){
                Comment findComment = commentRepository.findByUserAndApartmentAndEndTimeRent(userOptional.get(), apartmentOptional.get(),order.getStartTime());
                if (findComment == null){
                    comment.setApartment(apartmentOptional.get());
                    comment.setUser(userOptional.get());
                    comment.setStartTimeRent(order.getStartTime());
                    commentRepository.save(comment);
                    serviceResult.setData(comment);
                    serviceResult.setMessage("Comment success");
                    serviceResult.setStatus(ServiceStatus.SUCCESS);
                    return serviceResult;
                }
            }
        }
        return serviceResult;
    }

    @Override
    public ServiceResult findAllCommentByApartment(Long idApartment) {
        ServiceResult serviceResult = new ServiceResult();
        serviceResult.setStatus(ServiceStatus.FAILED);

        Optional<Apartment> apartmentOptional = apartmentRepository.findById(idApartment);

        List<Comment> listComment = commentRepository.findAllByApartment(apartmentOptional.get());
        if (listComment.isEmpty()){
            serviceResult.setMessage("No comment apartment");
        } else {
            serviceResult.setStatus(ServiceStatus.SUCCESS);
            serviceResult.setData(listComment);
        }
        return serviceResult;
    }
}
