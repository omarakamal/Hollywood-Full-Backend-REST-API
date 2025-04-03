package com.omargroup.movies_thyme.dtos.summaryDtos;

import com.omargroup.movies_thyme.model.User;
import org.springframework.security.core.userdetails.UserDetails;

public record UserDto(
        String username,
        Long userId
) {

    public UserDto(User user){
        this(
                user.getUsername(),
                user.getUserId()
        );
    }
    public UserDto(UserDetails userDetails){
        this(
                userDetails.getUsername(),
                ((User) userDetails).getUserId()
        );
    }
}
