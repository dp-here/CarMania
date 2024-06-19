package com.example.SpringMongoProject.Controller;

import java.util.Collections;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.SpringMongoProject.CustomUserDetails;
import com.example.SpringMongoProject.Entity.UserEntity;
import com.example.SpringMongoProject.Service.AuthenticationService;
import com.example.SpringMongoProject.Service.JwtService;
import com.example.SpringMongoProject.dtos.LoginUserDto;
import com.example.SpringMongoProject.dtos.RegisterUserDto;

import payload.response.LoginResponse;

@RequestMapping("/auth")
@RestController
@CrossOrigin(origins ="*")
public class AuthController {
    private final JwtService jwtService;
    private final AuthenticationService authenticationService;

    public AuthController(JwtService jwtService, AuthenticationService authenticationService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/signup")
    public ResponseEntity<UserEntity> register(@RequestBody RegisterUserDto registerUserDto) {
        UserEntity registeredUser = authenticationService.signup(registerUserDto);
        System.out.print("Signup is called");
        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginUserDto loginUserDto) {
        UserEntity authenticatedUser = authenticationService.authenticate(loginUserDto);

        // Convert UserEntity to UserDetails
        CustomUserDetails userDetails = new CustomUserDetails(
                authenticatedUser,
                Collections.emptyList() // Or provide the appropriate authorities if needed
        );
        String jwtToken = jwtService.generateToken(userDetails);
        // Your code to generate the response
        LoginResponse response = new LoginResponse().setToken(jwtToken).setExpiresIn(jwtService.getExpirationTime());
        // Populate the response object as needed

        return ResponseEntity.ok(response);
    }
}
