package com.omargroup.movies_thyme.service;

import com.omargroup.movies_thyme.dtos.createDtos.UserrCreateDto;
import com.omargroup.movies_thyme.exceptions.ResourceExistsException;
import com.omargroup.movies_thyme.exceptions.ResourceNotFoundException;
import com.omargroup.movies_thyme.model.User;
import com.omargroup.movies_thyme.repository.UserRepository;
import org.springframework.stereotype.Service;

//@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found!"));
    }

//    public User createUser(UserrCreateDto request) {
//
//        if (userRepository.existsByUsername(request.email())) throw new ResourceExistsException("username already taken");
//
//        User user = new User();
//        user.setUserName(request.userName());
//        user.setEmail(request.email());
//        user.setPassword(request.password());
//        return userRepository.save(user);
//    }

    public User updateUser(UserrCreateDto request, Long userId) {
        return  userRepository.findById(userId).map(existingUser ->{
            existingUser.setUserName(request.userName());
            existingUser.setEmail(request.email());
            existingUser.setPassword(request.password());
            return userRepository.save(existingUser);
        }).orElseThrow(() -> new ResourceNotFoundException("User not found!"));

    }

    public void deleteUser(Long userId) {
        userRepository.findById(userId).ifPresentOrElse(userRepository :: delete, () ->{
            throw new ResourceNotFoundException("User not found!");
        });
    }





}
