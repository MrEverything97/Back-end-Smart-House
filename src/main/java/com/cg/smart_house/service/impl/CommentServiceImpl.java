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
import jdk.nashorn.internal.runtime.regexp.joni.constants.OPCode;
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
    public ServiceResult createComment(String comment, Long idOrder, Long idUser) {
        ServiceResult serviceResult = new ServiceResult();
        serviceResult.setStatus(ServiceStatus.FAILED);

        Optional<Order> orderOptional = ordersRepository.findById(idOrder);
        Optional<Apartment> apartmentOptional = apartmentRepository.findById(orderOptional.get().getApartment().getId());
        Optional<User> userOptional = userRepository.findById(idUser);
        if (!userOptional.isPresent() || !apartmentOptional.isPresent()) {
            serviceResult.setMessage("Don't comment");
        } else {
            Comment findComment = commentRepository.findByUserAndApartmentAndStartTimeRent(userOptional.get(), apartmentOptional.get(), orderOptional.get().getStartTime());
            if (findComment == null) {
                Comment newComment = new Comment(comment, orderOptional.get().getStartTime(), orderOptional.get().getEndTime(), apartmentOptional.get(), userOptional.get());
                commentRepository.save(newComment);
                serviceResult.setData(newComment);
                serviceResult.setMessage("Comment success");
                serviceResult.setStatus(ServiceStatus.SUCCESS);
                return serviceResult;
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
        if (listComment.isEmpty()) {
            serviceResult.setMessage("No comment apartment");
        } else {
            serviceResult.setStatus(ServiceStatus.SUCCESS);
            serviceResult.setData(listComment);
        }
        return serviceResult;
    }
}
