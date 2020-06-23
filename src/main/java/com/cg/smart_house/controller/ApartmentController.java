package com.cg.smart_house.controller;

import com.cg.smart_house.model.Address;
import com.cg.smart_house.model.Apartment;
import com.cg.smart_house.model.Picture;

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
import java.util.List;

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
    public ResponseEntity<ServiceResult> createApartment( @RequestBody Apartment apartment){
        return new ResponseEntity<>(apartmentService.createApartment(apartment), HttpStatus.OK);
    }

    /* ---------------- UPDATE Apartment ------------------------ */
    @PutMapping("/updateApartment/{id}")
    public ResponseEntity<ServiceResult> updateApartment(@PathVariable Long id,@RequestBody Apartment apartment){
        return new ResponseEntity<>(apartmentService.updateApartment(id,apartment),HttpStatus.OK);
    }
    /* ---------------- SEARCH Apartment ------------------------ */
//    @GetMapping("/searchApartment")
//    public ResponseEntity<ServiceResult> searchApartment(@RequestParam("price") int price){
//        return new ResponseEntity<>(apartmentService.findTopByPriceByDate(price),HttpStatus.OK);
//    }
//
//    /* ---------------- SEARCH BetweenByPriceDate------------------------ */
//    @GetMapping("/searchPrice")
//    public ResponseEntity<ServiceResult> searchByDate(@RequestParam("min") int minPrice, @RequestParam("max") int maxPrice){
//        return new ResponseEntity<>(apartmentService.findAllByPriceByDate(minPrice, maxPrice),HttpStatus.OK);
//    }
//
//    /* ---------------- SEARCH Apartment By PriceAndName ------------------------ */
//    @GetMapping("/search")
//    public ResponseEntity<ServiceResult> searchApartmentByPriceAndName(@RequestParam("price") int price, @RequestParam("name") String name){
//        return new ResponseEntity<>(apartmentService.findTop5ByPriceByDateAndNameContains(price, name),HttpStatus.OK);
//    }


    /* ---------------- SEARCH AllByApartment ------------------------ */
//    @GetMapping("/searchAll")
//    public ResponseEntity<ServiceResult> searchAllByApartment(
//            @RequestParam("bedroom") int bedroom,
//            @RequestParam("bathroom") int bathroom,
//            @RequestParam("min") int min,
//            @RequestParam("max") int max,
//            @RequestParam("address") String address,
//            @RequestParam("start") Date startTime,
//            @RequestParam("end") Date endTime) {
//        return new ResponseEntity<>(apartmentService.searchAllByApartment(bedroom, bathroom, min, max, address, startTime, endTime),HttpStatus.OK);
//    }


    /* ---------------- SEARCH Apartment By StartTimeAndEndTime ------------------------ */
//    @GetMapping("/searchTime")
//    public ResponseEntity<ServiceResult> searchApartmentByStartTimeAndEndTime(@RequestParam("start") Date startTime, @RequestParam("end") Date endTime){
//        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//        Date dfMinTime = df.parse(startTime);
//        Date dfMaxTime = df.parse(endTime);
//        return new ResponseEntity<>(apartmentService.findAllOrderByStartTimeAndEndTime(startTime, endTime),HttpStatus.OK);
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
    public ResponseEntity<ServiceResult> searchApartment(@RequestParam int bedroom, int bathroom, Long province_id, int startPrice, int endPrice, String startTime, String endTime) throws ParseException {
         System.out.println(startTime);
        Date startTimeDate = new SimpleDateFormat("yyyy-MM-dd").parse(startTime);
        Date endTimeDate = new SimpleDateFormat("yyyy-MM-dd").parse(endTime);
        return new ResponseEntity<>(apartmentService.searchApartment(bedroom,bathroom,province_id,startPrice,endPrice,startTimeDate,endTimeDate),HttpStatus.OK);
    }
//    @GetMapping("/listApartmentRanting")
//    public ResponseEntity<ServiceResult> listApartmentRanting() {
//        return new ResponseEntity<>(ordersService.findAllApartmentRanTing(),HttpStatus.OK);
//    }
//
//    @GetMapping("/listApartment")
//    public ResponseEntity<ServiceResult> listApartmentByDate(@RequestParam("minTime") String minTime,
//                                                             @RequestParam("maxTime") String maxTime,
//                                                             @RequestParam("idProvince") Long idProvince) throws ParseException {
//        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//        Date dfMinTime = df.parse(minTime);
//        Date dfMaxTime = df.parse(maxTime);
//        return new ResponseEntity<>(apartmentService.findAllByAddressAndOrderStartTimeAndEndTime(idProvince, dfMinTime,dfMaxTime),HttpStatus.OK);
//    }
//
//
//    @GetMapping("/listApartment")
//    public ResponseEntity<ServiceResult> listApartmentByDate(@RequestParam("minTime") String minTime,
//                                                             @RequestParam("maxTime") String maxTime,
//                                                             @RequestParam("idProvince") Long idProvince,
//                                                             @RequestParam("bathroom") int bathroom,
//                                                             @RequestParam("bedroom") int bedroom,
//                                                             @RequestParam("priceByDate") int priceByDate) throws ParseException {
//        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//        Date dfMinTime = df.parse(minTime);
//        Date dfMaxTime = df.parse(maxTime);
//        return new ResponseEntity<>(apartmentService.findAllCriteria(bathroom,bedroom,priceByDate, idProvince, dfMinTime,dfMaxTime),HttpStatus.OK);
//    }


//
//    @GetMapping("/listApartmentRanting")
//    public ResponseEntity<ServiceResult> listApartmentRanting() {
//        return new ResponseEntity<>(ordersService.findAllApartmentRanting(),HttpStatus.OK);
//    }

}
