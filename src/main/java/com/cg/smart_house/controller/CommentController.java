package com.cg.smart_house.controller;

import com.cg.smart_house.model.Comment;
import com.cg.smart_house.service.CommentService;
import com.cg.smart_house.service.ServiceResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class CommentController {
    @Autowired
    private CommentService commentService;
    @GetMapping("/getListComment/{id}")
    public ResponseEntity<ServiceResult> getListCommentById(@PathVariable Long id){
        return new ResponseEntity<>(commentService.findAllCommentById(id), HttpStatus.OK);
    }
    @PutMapping("/createComment")
    public ResponseEntity<ServiceResult> createComment(@RequestBody Comment comment){
        return new ResponseEntity<>(commentService.saveComment(comment),HttpStatus.OK);

    }
}
