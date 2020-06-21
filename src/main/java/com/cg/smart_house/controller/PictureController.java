package com.cg.smart_house.controller;

import com.cg.smart_house.model.Picture;
import com.cg.smart_house.service.PictureService;
import com.cg.smart_house.service.ServiceResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/picture")
@CrossOrigin("*")
public class PictureController {
    @Autowired
    private PictureService pictureService;

    @GetMapping
    public ResponseEntity<ServiceResult> listPicture() {
        return new ResponseEntity<>(pictureService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServiceResult> getPictureById(@PathVariable Long id){
        return new ResponseEntity<>(pictureService.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ServiceResult> createPicture(@RequestBody Picture picture){
        return new ResponseEntity<>(pictureService.createPicture(picture), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ServiceResult> deletePicture(@PathVariable Long id){
        return new ResponseEntity<>(pictureService.deletePicture(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServiceResult> updatePicture(@RequestBody Picture picture){
        return new ResponseEntity<>(pictureService.updatePicture(picture),HttpStatus.OK);
    }
}
