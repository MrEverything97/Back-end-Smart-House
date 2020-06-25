package com.cg.smart_house.service;

import com.cg.smart_house.model.Apartment;
import com.cg.smart_house.model.Comment;
import com.cg.smart_house.model.Order;
import com.cg.smart_house.model.User;

public interface CommentService {
    ServiceResult createComment(String comment, Long idOrder, Long idUser);

    ServiceResult findAllCommentByApartment(Long idApartment);
}
