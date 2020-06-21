package com.cg.smart_house.controller;

import com.cg.smart_house.model.Address;
import com.cg.smart_house.model.Apartment;
import com.cg.smart_house.model.Picture;
import com.cg.smart_house.service.AddressService;
import com.cg.smart_house.service.ApartmentService;
import com.cg.smart_house.service.PictureService;
import com.cg.smart_house.service.ServiceResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class ApartmentController {
    @Autowired
    private ApartmentService apartmentService;

    /* ---------------- CREATE Apartment ------------------------ */
    @PostMapping("/createApartment")
    public ResponseEntity<ServiceResult> createApartment( @RequestBody Apartment apartment){
        return new ResponseEntity<>(apartmentService.createApartment(apartment), HttpStatus.OK);
    }

    /* ---------------- UPDATE Apartment ------------------------ */
    @PutMapping("/updateApartment/{id}")
    public ResponseEntity<ServiceResult> updateApartment(@PathVariable Long id,@RequestBody Apartment apartment){
        return new ResponseEntity<>(apartmentService.updateApartment(id,apartment),HttpStatus.OK);
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

    // Update pictures only
    @PutMapping("/update-apartment-pictures/{id}")
    public ResponseEntity<ServiceResult> updateApartmentPictures(@PathVariable Long id,@RequestBody List<Picture> pictureList){
        return new ResponseEntity<>(apartmentService.updateApartmentPicture(id,pictureList),HttpStatus.OK);
    }

    @GetMapping("/search-apartment")
    public ResponseEntity<ServiceResult> searchApartment(@RequestParam int bedroom, int bathroom, Long province_id, int startPrice, int endPrice, Date startTime, Date endTime){
        return new ResponseEntity<>(apartmentService.searchApartment(bedroom,bathroom,province_id,startPrice,endPrice,startTime,endTime),HttpStatus.OK);
    }
}
