package com.cg.smart_house.service.impl;

import com.cg.smart_house.enumm.ServiceStatus;
import com.cg.smart_house.model.User;
import com.cg.smart_house.repository.UserRepository;
import com.cg.smart_house.service.ServiceResult;
import com.cg.smart_house.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.awt.peer.PanelPeer;

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

    @Override
    public ServiceResult changePassword(User user, String oldPassword, String newPassword) {
        ServiceResult serviceResult = new ServiceResult();
        serviceResult.setStatus(ServiceStatus.FAILED);

        if (!passwordEncoder.matches(oldPassword,user.getPassword())){
            serviceResult.setMessage("Check password fail");
        } else {
            user.setPassword(passwordEncoder.encode(newPassword));
            userRepository.save(user);
            serviceResult.setStatus(ServiceStatus.SUCCESS);
            serviceResult.setData(user);
        }
        return serviceResult;
    }
}
