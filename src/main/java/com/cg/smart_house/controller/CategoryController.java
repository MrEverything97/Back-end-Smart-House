package com.cg.smart_house.controller;

import com.cg.smart_house.models.Category;
import com.cg.smart_house.service.CategoryService;
import com.cg.smart_house.service.ServiceResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/category")
    public ResponseEntity<ServiceResult> listCategory() {
        return new ResponseEntity<>(categoryService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<ServiceResult> getCategoryById(@PathVariable Long id){
        return new ResponseEntity<>(categoryService.findById(id), HttpStatus.OK);
    }

    @PostMapping("/createCategory")
    public ResponseEntity<ServiceResult> createCategory(@RequestBody Category category){
        return new ResponseEntity<>(categoryService.createCategory(category), HttpStatus.OK);
    }

    @PostMapping("/deleteCategory/{id}")
    public ResponseEntity<ServiceResult> deleteCategory(@PathVariable Long id){
        return new ResponseEntity<>(categoryService.deleteCategory(id), HttpStatus.OK);
    }

    @PutMapping("/updateCategory/{id}")
    public ResponseEntity<ServiceResult> updateCategory(@RequestBody Category category){
        return new ResponseEntity<>(categoryService.updateCategory(category),HttpStatus.OK);
    }
}
