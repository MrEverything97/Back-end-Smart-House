package com.cg.smart_house.controller;

import com.cg.smart_house.model.Address;
import com.cg.smart_house.service.AddressService;
import com.cg.smart_house.service.ServiceResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class AddressController {

    @Autowired
    AddressService addressService;

    @PostMapping("/createAddress")
    public ResponseEntity<ServiceResult> createAddress(@RequestBody Address address){
        return new ResponseEntity<>(addressService.createAddress(address), HttpStatus.OK);
    }

    @GetMapping("/listAddress")
    public ResponseEntity<ServiceResult> listAddress(){
        return new ResponseEntity<>(addressService.findAll(),HttpStatus.OK);
    }
}
