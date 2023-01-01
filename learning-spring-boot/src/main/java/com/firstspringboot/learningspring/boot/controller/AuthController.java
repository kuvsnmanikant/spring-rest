package com.firstspringboot.learningspring.boot.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.firstspringboot.learningspring.boot.dto.JwtAuthResponse;
import com.firstspringboot.learningspring.boot.dto.LoginDto;
import com.firstspringboot.learningspring.boot.dto.RegisterUserDto;
import com.firstspringboot.learningspring.boot.services.AuthServices;


@RestController
@CrossOrigin("*")
@RequestMapping("/api/auth")
public class AuthController {

    private AuthServices authServices;

    public AuthController(AuthServices authServices) {
        this.authServices = authServices;
    }
    
    // @PostMapping(value = {"/login", "/signin"}) // this is for basic auth
    // public ResponseEntity<String> login(@RequestBody LoginDto loginDto){
    //     return ResponseEntity.ok(authServices.login(loginDto));
    // }

    @PostMapping(value = {"/login", "/signin"}) // this is for jwt token
    public ResponseEntity<JwtAuthResponse> login(@RequestBody LoginDto loginDto){
        JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();
        jwtAuthResponse.setAccessToken(authServices.login(loginDto));
        return ResponseEntity.ok(jwtAuthResponse);
    }

    @PostMapping(value = {"/signup", "/register"})
    public ResponseEntity<String> registerUser(@RequestBody RegisterUserDto registerUserDto){
        return new ResponseEntity<>(authServices.registerUser(registerUserDto), HttpStatus.CREATED);
    }
}
