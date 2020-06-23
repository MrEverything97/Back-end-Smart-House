package com.cg.smart_house.service;

import com.cg.smart_house.model.Apartment;
import com.cg.smart_house.model.User;

public interface CommentService {
    ServiceResult createComment(Apartment apartment, User user);
}
