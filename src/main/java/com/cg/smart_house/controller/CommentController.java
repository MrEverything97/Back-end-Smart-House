package com.cg.smart_house.controller;

import com.cg.smart_house.model.Apartment;
import com.cg.smart_house.model.Comment;
import com.cg.smart_house.model.User;
import com.cg.smart_house.repository.ApartmentRepository;
import com.cg.smart_house.repository.CommentRepository;
import com.cg.smart_house.repository.UserRepository;
import com.cg.smart_house.service.CommentService;
import com.cg.smart_house.service.ServiceResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ApartmentRepository apartmentRepository;

    @PostMapping("/createComment/{idOrder}")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<ServiceResult> createComment(@RequestParam String comment, @PathVariable Long idOrder, Principal principal) {
        String username = principal.getName();
        Optional<User> userOptional = userRepository.findByUsername(username);
        if (!userOptional.isPresent()) {
            throw new RuntimeException("Not found");
        }
        User user = userOptional.get();
        return new ResponseEntity<>(commentService.createComment(comment, idOrder, user.getId()), HttpStatus.OK);
    }

    @GetMapping("/listComment/{idApartment}")
    public ResponseEntity<ServiceResult> listComment(@PathVariable Long idApartment){
        return new ResponseEntity<>(commentService.findAllCommentByApartment(idApartment),HttpStatus.OK);
    }

    @PostMapping("/addComment/{apartmentId}")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<ServiceResult> addComment(@RequestParam String comment,@PathVariable Long apartmentId,Principal principal){
        String username = principal.getName();
        return new ResponseEntity<>(commentService.addComment(comment,apartmentId,username),HttpStatus.OK);
    }
}
