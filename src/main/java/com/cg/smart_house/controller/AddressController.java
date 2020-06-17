package com.cg.smart_house.controller;

import com.cg.smart_house.models.Address;
import com.cg.smart_house.service.AddressService;
import com.cg.smart_house.service.ServiceResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AddressController {

    @Autowired
    AddressService addressService;

    @PostMapping("/createAddress")
    public ResponseEntity<ServiceResult> createAddress(@RequestBody Address address){
        return new ResponseEntity<>(addressService.createAddress(address), HttpStatus.OK);
    }
}
