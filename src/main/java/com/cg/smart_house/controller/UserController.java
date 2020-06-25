package com.cg.smart_house.controller;


import com.cg.smart_house.model.User;
import com.cg.smart_house.repository.UserRepository;
import com.cg.smart_house.service.ServiceResult;
import com.cg.smart_house.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @PutMapping("/updateUser")
    public ResponseEntity<ServiceResult> updateUser(@RequestBody User user) {
        return new ResponseEntity<>(userService.updateUser(user), HttpStatus.OK);
    }

    @PutMapping("/changePassword")
    @PreAuthorize("hasRole('CUSTOMER') or hasRole('HOST')")
    public ResponseEntity<ServiceResult> changePassword(Principal principal, @RequestParam("oldPassword") String oldPassword,
                                                    @RequestParam("newPassWord") String newPassWord) {
        Optional<User> userOptional = userRepository.findByUsername(principal.getName());
        if (!userOptional.isPresent()){
            throw new RuntimeException("Not found");
        }
        User user = userOptional.get();
        return new ResponseEntity<>(userService.changePassword(user, oldPassword, newPassWord), HttpStatus.OK);
    }
}
