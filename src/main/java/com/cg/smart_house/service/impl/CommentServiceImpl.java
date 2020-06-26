package com.cg.smart_house.service.impl;

import com.cg.smart_house.dto.CommentDto;
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

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
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
        if(!apartmentOptional.isPresent()){
            return serviceResult;
        }

        List<Comment> listComment = commentRepository.findAllByApartment(apartmentOptional.get());
        List<CommentDto> commentDtoList = convertToCommentDtoList(listComment);
        if (listComment.isEmpty()) {
            serviceResult.setMessage("No comment apartment");
        } else {
            serviceResult.setStatus(ServiceStatus.SUCCESS);
            serviceResult.setData(commentDtoList);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult addComment(String comment, Long apartmentId, String username) {
        ServiceResult serviceResult = new ServiceResult();
        Optional<User> userOptional = userRepository.findByUsername(username);
        Optional<Apartment> apartmentOptional = apartmentRepository.findById(apartmentId);
        if(!apartmentOptional.isPresent() || !userOptional.isPresent()){
            serviceResult.setStatus(ServiceStatus.FAILED);
            return serviceResult;
        }
        User user = userOptional.get();
        Apartment apartment = apartmentOptional.get();
        List<Comment> apartmentCommentList = apartment.getComments();
        int countByApartment = (int) apartmentCommentList.stream().filter(comment1 -> comment1.getUser().getId().equals(user.getId())).count();
        int countByOrder;
        List<Order> userOrderList = ordersRepository.findAllByUser(user);
        countByOrder = (int) userOrderList.stream().filter(order -> order.getApartment().getId().equals(apartment.getId()) && order.getStatusOrders().equals(StatusOrders.RENTED)).count();
        if(countByOrder > countByApartment){
            Comment newComment = new Comment();
            newComment.setComment(comment);
            newComment.setApartment(apartment);
            newComment.setUser(user);
            commentRepository.save(newComment);
            serviceResult.setStatus(ServiceStatus.SUCCESS);
            serviceResult.setData(convertToCommentDto(newComment));
            serviceResult.setMessage("Comment success");
        } else {
            serviceResult.setStatus(ServiceStatus.FAILED);
        }
        return serviceResult;
    }

    private CommentDto convertToCommentDto(Comment comment){
        CommentDto commentDto = new CommentDto();
        commentDto.setComment(comment.getComment());
        commentDto.setUsername(comment.getUser().getUsername());
        commentDto.setRate(comment.getRate());
        return commentDto;
    }

    private List<CommentDto> convertToCommentDtoList(List<Comment> commentList){
        List<CommentDto> commentDtoList = new ArrayList<>();
        commentList.forEach(comment -> {
            commentDtoList.add(convertToCommentDto(comment));
        });
        return commentDtoList;
    }
}
