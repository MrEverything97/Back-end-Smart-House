package com.cg.smart_house.controller;

import com.cg.smart_house.models.Province;
import com.cg.smart_house.service.ProvinceService;
import com.cg.smart_house.service.ServiceResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ProvinceController {
    @Autowired
    private ProvinceService provinceService;

    @GetMapping("/province")
    public ResponseEntity<ServiceResult> listProvince() {
        return new ResponseEntity<>(provinceService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/province/{id}")
    public ResponseEntity<ServiceResult> getProvinceById(@PathVariable Long id){
        return new ResponseEntity<>(provinceService.findById(id), HttpStatus.OK);
    }

    @PostMapping("/createProvince")
    public ResponseEntity<ServiceResult> createProvince(@RequestBody Province province){
        return new ResponseEntity<>(provinceService.createProvince(province), HttpStatus.OK);
    }

    @PostMapping("/deleteProvince/{id}")
    public ResponseEntity<ServiceResult> deleteProvince(@PathVariable Long id){
        return new ResponseEntity<>(provinceService.deleteProvince(id), HttpStatus.OK);
    }

    @PutMapping("/updateProvince/{id}")
    public ResponseEntity<ServiceResult> updateProvince(@RequestBody Province province){
        return new ResponseEntity<>(provinceService.updateProvince(province),HttpStatus.OK);
    }
}
