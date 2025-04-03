package com.omargroup.movies_thyme.dtos.createDtos;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;

public record MovieCreateDto(
        @NotBlank String title,
        @Min(1888) @Max(2100) int releaseYear,
        String description,
        double rating,
        boolean hasOscars,
        int duration,
        List<Long> castIds,
        List<Long> genreIds
        ) {

}
