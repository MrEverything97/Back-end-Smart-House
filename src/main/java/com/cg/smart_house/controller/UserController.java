package com.cg.smart_house.controller;


import com.cg.smart_house.model.User;
import com.cg.smart_house.service.ServiceResult;
import com.cg.smart_house.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class UserController {

    @Autowired
    UserService userService;

    @PutMapping("/updateUser")
    public ResponseEntity<ServiceResult> updateUser(@RequestBody User user) {
        return new ResponseEntity<>(userService.updateUser(user), HttpStatus.OK);
    }
}
