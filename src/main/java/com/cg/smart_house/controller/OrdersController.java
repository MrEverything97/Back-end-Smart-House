package com.cg.smart_house.controller;

import com.cg.smart_house.model.Order;
import com.cg.smart_house.service.OrdersService;
import com.cg.smart_house.service.ServiceResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class OrdersController {
    @Autowired
    private OrdersService ordersService;

    @GetMapping("/listOrders")
    public ResponseEntity<ServiceResult> listOrders(){
        return new ResponseEntity<>(ordersService.findALl(),HttpStatus.OK);
    }

    @PostMapping("/createOrders")
    public ResponseEntity<ServiceResult> createOrders(@RequestBody Order orders){
        return new ResponseEntity<>(ordersService.createOrders(orders), HttpStatus.OK);
    }

    @PutMapping("/updateStatusOrders")
    public ResponseEntity<ServiceResult> updateStatusOrders(@RequestBody Order orders){
        return new ResponseEntity<>(ordersService.updateStatusOrders(orders),HttpStatus.OK);
    }

//    @SneakyThrows
//    @GetMapping("/listApartmentByDate")
//    public ResponseEntity<ServiceResult> listApartmentByDate(@RequestParam("minTime") String minTime,
//                                                             @RequestParam("maxTime") String maxTime){
//        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//        Date dfMinTime = df.parse(minTime);
//        Date dfMaxTime = df.parse(maxTime);
//        return new ResponseEntity<>(ordersService.findAllOrderByStartTimeAndEndTime(dfMinTime,dfMaxTime),HttpStatus.OK);
//    }
}
