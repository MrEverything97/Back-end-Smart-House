package com.cg.smart_house.service.impl;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ApartmentRepository apartmentRepository;
    @Autowired
    private OrdersRepository ordersRepository;

    @Override
    public ServiceResult findAllCommentById(Long Id) {
        ServiceResult serviceResult=new ServiceResult();
        List<Comment> commentList = commentRepository.findAllByApartment(Id);
        serviceResult.setData(commentList);
        return serviceResult;
    }
//    fix lai nhe!!!
    @Override
    public ServiceResult saveComment(Comment comment) {
        ServiceResult serviceResult= new ServiceResult();
        int amountOrder = 0;
        int amountComment=0;
        List<Order> orderList= ordersRepository.findAllByApartment_Id(comment.getApartment().getId());
        List<Comment> commentList=commentRepository.findAllByApartment(comment.getId());
//        so lan rented order cho 1 user
        try{
            for (Order value : orderList) {
                if (value.getUser().getUsername().equals(comment.getUser().getUsername())&&value.getStatusOrders().name().equals("RENTED")) {
                    amountOrder += 1;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
//       so lan da comment
        try {
            if (commentList!=null){
                for (Comment value : commentList){
                    if (value.getUser().getUsername().equals(comment.getUser().getUsername())){
                        amountComment +=1;
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        if (amountComment<amountOrder){
            serviceResult.setData(commentRepository.save(comment));
        }

        return serviceResult;
    }
}
