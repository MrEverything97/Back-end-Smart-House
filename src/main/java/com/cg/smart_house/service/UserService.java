package com.cg.smart_house.service;

import com.cg.smart_house.model.User;

public interface UserService  {
    ServiceResult updateUser(User user);

    ServiceResult changePassword(User user, String checkPassword, String newPassword);
}
