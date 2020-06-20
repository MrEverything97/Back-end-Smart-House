package com.cg.smart_house.controller;

import com.cg.smart_house.model.Apartment;
import com.cg.smart_house.service.ApartmentService;
import com.cg.smart_house.service.ServiceResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class ApartmentController {
    @Autowired
    private ApartmentService apartmentService;

    /* ---------------- CREATE Apartment ------------------------ */
    @PostMapping("/createApartment")
    public ResponseEntity<ServiceResult> createApartment(@Valid @RequestBody Apartment apartment){
        return new ResponseEntity<>(apartmentService.createApartment(apartment), HttpStatus.OK);
    }

    /* ---------------- UPDATE Apartment ------------------------ */
    @PutMapping("/updateApartment/{id}")
    public ResponseEntity<ServiceResult> updateApartment(@PathVariable Long id,@RequestBody Apartment apartment){
        return new ResponseEntity<>(apartmentService.updateApartment(id,apartment),HttpStatus.OK);
    }
    /* ---------------- SEARCH Apartment ------------------------ */
    @GetMapping("/searchApartment")
    public ResponseEntity<ServiceResult> searchApartment(@RequestParam("price") int price){
        return new ResponseEntity<>(apartmentService.findTopByPriceByDate(price),HttpStatus.OK);
    }

    /* ---------------- DELETE Apartment ------------------------ */
    @DeleteMapping("/deleteApartment/{id}")
    public ResponseEntity<ServiceResult> deleteApartment(@PathVariable Long id){
        return new ResponseEntity<>(apartmentService.deleteApartment(id), HttpStatus.OK);
    }

    /* ---------------- VIEWS Apartment ------------------------ */
    @GetMapping("/listApartment/{id}")
    public ResponseEntity<ServiceResult> getApartmentById(@PathVariable Long id){
        return new ResponseEntity<>(apartmentService.findById(id), HttpStatus.OK);
    }

    /* ---------------- LIST Apartment ------------------------ */
    @GetMapping("/listApartment")
    public ResponseEntity<ServiceResult> listApartment(){
        return new ResponseEntity<>(apartmentService.findAll(),HttpStatus.OK);
    }

}
