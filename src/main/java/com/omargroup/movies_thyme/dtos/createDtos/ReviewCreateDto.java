package com.omargroup.movies_thyme.dtos.createDtos;

public record ReviewCreateDto(
        int ratingStars,
        String ratingBody
) {
}
