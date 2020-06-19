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

import java.util.List;


@RestController
@RequestMapping("/api/apartment")
public class ApartmentController {
    @Autowired
    private ApartmentService apartmentService;

    @Autowired
    private PictureService pictureService;

    @Autowired
    private AddressService addressService;

    @GetMapping
    public ResponseEntity<ServiceResult> listApartment() {
        return new ResponseEntity<>(apartmentService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServiceResult> getApartmentById(@PathVariable Long id){
        return new ResponseEntity<>(apartmentService.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ServiceResult> createApartment(@RequestBody Apartment apartment){
        ServiceResult result = new ServiceResult();
//        Address address = apartment.getAddress();
//        addressService.createAddress(address);
//        List<Picture> pictureList = apartment.getPictures();
//        pictureList.forEach(picture -> {
//            pictureService.createPicture(picture);
//        });
        result.setData(apartmentService.createApartment(apartment));
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ServiceResult> deleteApartment(@PathVariable Long id){
        return new ResponseEntity<>(apartmentService.deleteApartment(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServiceResult> updateApartment(@RequestBody Apartment apartment){
        ServiceResult result = new ServiceResult();
        result.setData(apartmentService.updateApartment(apartment));
        return ResponseEntity.ok(result);
    }
}
