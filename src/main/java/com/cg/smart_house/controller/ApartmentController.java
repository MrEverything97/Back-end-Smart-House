package com.cg.smart_house.controller;

import com.cg.smart_house.model.Apartment;
import com.cg.smart_house.service.ApartmentService;
import com.cg.smart_house.service.OrdersService;
import com.cg.smart_house.service.ServiceResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class ApartmentController {
    @Autowired
    private ApartmentService apartmentService;

    @Autowired
    private OrdersService ordersService;

    /* ---------------- CREATE Apartment ------------------------ */
    @PostMapping("/createApartment")
    public ResponseEntity<ServiceResult> createApartment(@Valid @RequestBody Apartment apartment){
        return new ResponseEntity<>(apartmentService.createApartment(apartment), HttpStatus.OK);
    }

    /* ---------------- UPDATE Apartment ------------------------ */
//    @PutMapping("/updateApartment/{id}")
//    public ResponseEntity<ServiceResult> updateApartment(@RequestBody Apartment apartment){
//        return new ResponseEntity<>(apartmentService.updateApartment(apartment),HttpStatus.OK);
//    }

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
    @GetMapping("/listApartmentAll")
    public ResponseEntity<ServiceResult> listApartment(){
        return new ResponseEntity<>(apartmentService.findAll(),HttpStatus.OK);
    }

    @GetMapping("/listApartmentRanting")
    public ResponseEntity<ServiceResult> listApartmentRanting() {
        return new ResponseEntity<>(ordersService.findAllApartmentRanTing(),HttpStatus.OK);
    }

    @GetMapping("/listApartment")
    public ResponseEntity<ServiceResult> listApartmentByDate(@RequestParam("minTime") String minTime,
                                                             @RequestParam("maxTime") String maxTime,
                                                             @RequestParam("idProvince") Long idProvince) throws ParseException {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date dfMinTime = df.parse(minTime);
        Date dfMaxTime = df.parse(maxTime);
        return new ResponseEntity<>(apartmentService.findAllByAddressAndOrderStartTimeAndEndTime(idProvince, dfMinTime,dfMaxTime),HttpStatus.OK);
    }


    @GetMapping("/listApartment")
    public ResponseEntity<ServiceResult> listApartmentByDate(@RequestParam("minTime") String minTime,
                                                             @RequestParam("maxTime") String maxTime,
                                                             @RequestParam("idProvince") Long idProvince,
                                                             @RequestParam("bathroom") int bathroom,
                                                             @RequestParam("bedroom") int bedroom,
                                                             @RequestParam("priceByDate") int priceByDate) throws ParseException {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date dfMinTime = df.parse(minTime);
        Date dfMaxTime = df.parse(maxTime);
        return new ResponseEntity<>(apartmentService.findAllCriteria(bathroom,bedroom,priceByDate, idProvince, dfMinTime,dfMaxTime),HttpStatus.OK);
    }


//
//    @GetMapping("/listApartmentRanting")
//    public ResponseEntity<ServiceResult> listApartmentRanting() {
//        return new ResponseEntity<>(ordersService.findAllApartmentRanting(),HttpStatus.OK);
//    }

}
