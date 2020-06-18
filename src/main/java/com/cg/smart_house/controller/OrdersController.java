package com.cg.smart_house.controller;

import com.cg.smart_house.models.Apartment;
import com.cg.smart_house.models.Orders;
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
    public ResponseEntity<ServiceResult> createOrders(@RequestBody Orders orders){
        return new ResponseEntity<>(ordersService.createOrders(orders), HttpStatus.OK);
    }
}
