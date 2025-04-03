package com.omargroup.movies_thyme.service;

import com.omargroup.movies_thyme.dtos.createDtos.UserrCreateDto;
import com.omargroup.movies_thyme.dtos.summaryDtos.UserDto;
import com.omargroup.movies_thyme.exceptions.ResourceNotFoundException;
import com.omargroup.movies_thyme.model.User;
import com.omargroup.movies_thyme.repository.UserRepository;
import com.omargroup.movies_thyme.security.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    public AuthenticationService(UserRepository repository, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }


    public UserDto register(UserrCreateDto request){
        var user = new User();
        user.setUserName(request.userName());
        user.setPassword(passwordEncoder.encode(request.password()));
        user.setEmail(request.email());
        user.setActive(true);


        return new UserDto(repository.save(user));

    }

    public String authenticate(UserrCreateDto request){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.userName(),
                        request.password()
                )
        );
        var user = repository.findByUserName(request.userName()).orElseThrow(()-> new ResourceNotFoundException("Sign up first"));
        var jwtToken = jwtService.generateToken(user);
        return String.format("Bearer %s",jwtToken);
    }




}
