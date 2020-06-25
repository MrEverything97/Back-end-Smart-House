package com.cg.smart_house.service.impl;

import com.cg.smart_house.model.User;
import com.cg.smart_house.repository.UserRepository;
import com.cg.smart_house.service.ServiceResult;
import com.cg.smart_house.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public ServiceResult updateUser(User user) {
        ServiceResult serviceResult = new ServiceResult();

        User user1 = userRepository.findById(user.getId()).orElse(null);
        if (user1 == null){
            serviceResult.setMessage("No found user");
            return serviceResult;
        } else {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);
            serviceResult.setMessage("Update user done");
        }
        return serviceResult;
    }
}
