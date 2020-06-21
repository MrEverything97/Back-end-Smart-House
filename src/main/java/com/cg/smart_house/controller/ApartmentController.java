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
import java.util.Date;

@RestController
@RequestMapping("/api")
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
    @PutMapping("/updateApartment/{id}")
    public ResponseEntity<ServiceResult> updateApartment(@PathVariable Long id,@RequestBody Apartment apartment){
        return new ResponseEntity<>(apartmentService.updateApartment(id,apartment),HttpStatus.OK);
    }
    /* ---------------- SEARCH Apartment ------------------------ */
    @GetMapping("/searchApartment")
    public ResponseEntity<ServiceResult> searchApartment(@RequestParam("price") int price){
        return new ResponseEntity<>(apartmentService.findTopByPriceByDate(price),HttpStatus.OK);
    }

    /* ---------------- SEARCH BetweenByPriceDate------------------------ */
    @GetMapping("/searchPrice")
    public ResponseEntity<ServiceResult> searchByDate(@RequestParam("min") int minPrice, @RequestParam("max") int maxPrice){
        return new ResponseEntity<>(apartmentService.findAllByPriceByDate(minPrice, maxPrice),HttpStatus.OK);
    }

    /* ---------------- SEARCH Apartment By PriceAndName ------------------------ */
    @GetMapping("/search")
    public ResponseEntity<ServiceResult> searchApartmentByPriceAndName(@RequestParam("price") int price, @RequestParam("name") String name){
        return new ResponseEntity<>(apartmentService.findTop5ByPriceByDateAndNameContains(price, name),HttpStatus.OK);
    }

    /* ---------------- SEARCH AllByApartment ------------------------ */
    @GetMapping("/searchAll")
    public ResponseEntity<ServiceResult> searchAllByApartment(@RequestParam("name") String name,
                                                              @RequestParam("bedroom") int bedroom,
                                                              @RequestParam("bathroom") int bathroom,
                                                              @RequestParam("price") int price,
                                                              @RequestParam("address") String address,
                                                              @RequestParam("start") Date startTime,
                                                              @RequestParam("end") Date endTime){
        return new ResponseEntity<>(apartmentService.searchAllByApartment(name, bedroom, bathroom, price, address, startTime, endTime),HttpStatus.OK);
    }

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

    @GetMapping("/listApartmentRanting")
    public ResponseEntity<ServiceResult> listApartmentRanting() {
        return new ResponseEntity<>(ordersService.findAllApartmentRanting(),HttpStatus.OK);
    }

}
