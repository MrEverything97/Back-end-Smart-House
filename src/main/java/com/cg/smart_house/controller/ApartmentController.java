package com.cg.smart_house.controller;

import com.cg.smart_house.models.Apartment;
import com.cg.smart_house.service.ApartmentService;
import com.cg.smart_house.service.ServiceResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ApartmentController {
    @Autowired
    private ApartmentService apartmentService;

    @GetMapping("/listApartment")
    public ResponseEntity<ServiceResult> listApartment() {
        return new ResponseEntity<>(apartmentService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/listApartment/{id}")
    public ResponseEntity<ServiceResult> getApartmentById(@PathVariable Long id){
        return new ResponseEntity<>(apartmentService.findById(id), HttpStatus.OK);
    }

    @PostMapping("/createApartment")
    public ResponseEntity<ServiceResult> createApartment(@RequestBody Apartment apartment){
        return new ResponseEntity<>(apartmentService.createApartment(apartment), HttpStatus.OK);
    }

    @PostMapping("/deleteApartment/{id}")
    public ResponseEntity<ServiceResult> deleteApartment(@PathVariable Long id){
        return new ResponseEntity<>(apartmentService.deleteApartment(id), HttpStatus.OK);
    }

    @PutMapping("/updateApartment/{id}")
    public ResponseEntity<ServiceResult> updateApartment(@RequestBody Apartment apartment){
        return new ResponseEntity<>(apartmentService.updateApartment(apartment),HttpStatus.OK);
    }


}
