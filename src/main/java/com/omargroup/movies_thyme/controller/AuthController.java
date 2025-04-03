package com.omargroup.movies_thyme.controller;

import com.omargroup.movies_thyme.dtos.createDtos.UserrCreateDto;
import com.omargroup.movies_thyme.dtos.summaryDtos.UserDto;
import com.omargroup.movies_thyme.service.AuthenticationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final AuthenticationService service;

    public AuthController(AuthenticationService service) {
        this.service = service;
    }

    @PostMapping("/register")
    public ResponseEntity<UserDto> register(
            @RequestBody UserrCreateDto request
            ){
        return new ResponseEntity<>(service.register(request), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(
            @RequestBody UserrCreateDto request
    ){
        return new ResponseEntity<>(service.authenticate(request), HttpStatus.OK);
    }
}
