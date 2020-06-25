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
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public ServiceResult updateUser(User user,String username) {
        ServiceResult serviceResult = new ServiceResult();

        Optional<User> optionalUser = userRepository.findByUsername(username);
        if(!optionalUser.isPresent()) {
            serviceResult.setMessage("No found user");
            serviceResult.setStatus(ServiceStatus.FAILED);
            return serviceResult;
        }
        User currentUser = optionalUser.get();
        if(user.getEmail() != null){
            currentUser.setEmail(user.getEmail());
        }
        if(user.getName() != null){
            currentUser.setName(user.getName());
        }
        if(user.getPhone() != null){
            currentUser.setPhone(user.getPhone());
        }
        userRepository.save(currentUser);
        serviceResult.setStatus(ServiceStatus.SUCCESS);
        serviceResult.setMessage("Update success");
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
