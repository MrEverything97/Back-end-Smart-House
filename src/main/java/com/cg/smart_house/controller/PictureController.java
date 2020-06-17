package com.cg.smart_house.controller;

import com.cg.smart_house.models.Picture;
import com.cg.smart_house.models.Province;
import com.cg.smart_house.service.PictureService;
import com.cg.smart_house.service.ServiceResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class PictureController {
    @Autowired
    private PictureService pictureService;

    @GetMapping("/picture")
    public ResponseEntity<ServiceResult> listPicture() {
        return new ResponseEntity<>(pictureService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/picture/{id}")
    public ResponseEntity<ServiceResult> getPictureById(@PathVariable Long id){
        return new ResponseEntity<>(pictureService.findById(id), HttpStatus.OK);
    }

    @PostMapping("/createPicture")
    public ResponseEntity<ServiceResult> createPicture(@RequestBody Picture picture){
        return new ResponseEntity<>(pictureService.createPicture(picture), HttpStatus.OK);
    }

    @PostMapping("/deletePicture/{id}")
    public ResponseEntity<ServiceResult> deletePicture(@PathVariable Long id){
        return new ResponseEntity<>(pictureService.deletePicture(id), HttpStatus.OK);
    }

    @PutMapping("/updatePicture/{id}")
    public ResponseEntity<ServiceResult> updatePicture(@RequestBody Picture picture){
        return new ResponseEntity<>(pictureService.updatePicture(picture),HttpStatus.OK);
    }
}
