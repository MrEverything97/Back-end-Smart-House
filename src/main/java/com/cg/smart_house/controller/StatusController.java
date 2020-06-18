package com.cg.smart_house.controller;

import com.cg.smart_house.models.Status;
import com.cg.smart_house.service.ServiceResult;
import com.cg.smart_house.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RestController
@RequestMapping("/api")
public class StatusController {

    @Autowired
    private StatusService statusService;

    @GetMapping("/status")
    public ResponseEntity<ServiceResult> listStatus() {
        return new ResponseEntity<>(statusService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/status/{id}")
    public ResponseEntity<ServiceResult> getStatusById(@PathVariable Long id){
        return new ResponseEntity<>(statusService.findById(id), HttpStatus.OK);
    }

    @PostMapping("/createStatus")
    public ResponseEntity<ServiceResult> createStatus(@RequestBody Status status){
        return new ResponseEntity<>(statusService.createStatus(status), HttpStatus.OK);
    }

    @PostMapping("/deleteStatus/{id}")
    public ResponseEntity<ServiceResult> deleteStatus(@PathVariable Long id){
        return new ResponseEntity<>(statusService.deleteStatus(id), HttpStatus.OK);
    }

    @PutMapping("/updateStatus/{id}")
    public ResponseEntity<ServiceResult> updateStatus(@RequestBody Status status){
        return new ResponseEntity<>(statusService.updateStatus(status),HttpStatus.OK);
    }

    @GetMapping("/listStatus/{id}")
    public ResponseEntity<ServiceResult> listAllStatusByApartment(@PathVariable Long id){
        return new ResponseEntity<>(statusService.findAllStatusByApartment(id),HttpStatus.OK);
    }
}
