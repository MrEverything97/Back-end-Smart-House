package com.cg.smart_house.controller;

import com.cg.smart_house.config.Jwt.JwtProvider;
import com.cg.smart_house.enumm.RoleName;
import com.cg.smart_house.message.requset.LoginForm;
import com.cg.smart_house.message.requset.SignUpForm;
import com.cg.smart_house.message.responce.JwtResponse;
import com.cg.smart_house.model.Role;
import com.cg.smart_house.model.User;
import com.cg.smart_house.repository.RoleRepository;
import com.cg.smart_house.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthRestAPIs {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtProvider jwtProvider;

    @PostMapping("/signIn")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginForm loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.generateJwtToken(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return ResponseEntity.ok(new JwtResponse(jwt,userDetails.getUsername(), (Collection<GrantedAuthority>) userDetails.getAuthorities()));
    }

    @PostMapping("/signUp")
    public ResponseEntity<Void> registerUser( @RequestBody SignUpForm signUpRequest) {
        if(userRepository.existsByUsername(signUpRequest.getUsername())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if(userRepository.existsByEmail(signUpRequest.getEmail())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        // Creating user's account
        User user = new User(signUpRequest.getName(), signUpRequest.getUsername(),
                signUpRequest.getEmail(),signUpRequest.getPhone(), encoder.encode(signUpRequest.getPassword()));

        String roleString = signUpRequest.getRole();
        if (roleString == null) roleString = "";

        Role role ;
            switch(roleString) {
                case "host":
                    Role adminRole = roleRepository.findByName(RoleName.ROLE_HOST)
                            .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
                    role = adminRole;
                    break;
                case "customer":
                    Role pmRole = roleRepository.findByName(RoleName.ROLE_CUSTOMER)
                            .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
                    role = pmRole;
                    break;
                default:
                    Role defaultRole = roleRepository.findByName(RoleName.ROLE_CUSTOMER)
                            .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
                    role = defaultRole;
            }
        user.setRole(role);
        userRepository.save(user);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
