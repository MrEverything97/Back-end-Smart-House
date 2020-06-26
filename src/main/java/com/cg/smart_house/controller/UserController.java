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

    @PreAuthorize("hasRole('CUSTOMER') or hasRole('HOST')")
    @PutMapping("/updateUser")
    public ResponseEntity<ServiceResult> updateUser(@RequestBody User user,Principal principal) {
        String username = principal.getName();
        return new ResponseEntity<>(userService.updateUser(user,username), HttpStatus.OK);
    }

    @PutMapping("/changePassword")
    @PreAuthorize("hasRole('CUSTOMER') or hasRole('HOST')")
    public ResponseEntity<ServiceResult> changePassword(Principal principal, @RequestParam(value = "oldPassword") String oldPassword,
                                                    @RequestParam(value = "newPassWord") String newPassWord) {
        Optional<User> userOptional = userRepository.findByUsername(principal.getName());
        if (!userOptional.isPresent()){
            throw new RuntimeException("Not found");
        }
        User user = userOptional.get();
        return new ResponseEntity<>(userService.changePassword(user, oldPassword, newPassWord), HttpStatus.OK);
    }

    @GetMapping("/getUser")
    @PreAuthorize("hasRole('CUSTOMER') or hasRole('HOST')")
    public ResponseEntity<User> getUserByUsername(Principal principal){
        Optional<User> user = userRepository.findByUsername(principal.getName());
        if (!user.isPresent()){
            throw  new RuntimeException("Not found");
        }
        return new ResponseEntity<>(user.get(),HttpStatus.OK);
    }
}
