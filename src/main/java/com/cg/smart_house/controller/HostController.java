package com.cg.smart_house.controller;

import com.cg.smart_house.model.Host;
import com.cg.smart_house.service.ApartmentService;
import com.cg.smart_house.service.ServiceResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class HostController {
    @Autowired
    private ApartmentService apartmentService;

    @GetMapping("/host/apartment")
    public ResponseEntity<ServiceResult> listApartment(@RequestParam(defaultValue = "1",name = "id") Long id){
        return new ResponseEntity<>(apartmentService.findAllByHostId(id), HttpStatus.OK);
    }

}
