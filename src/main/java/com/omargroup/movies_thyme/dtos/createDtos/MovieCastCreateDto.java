package com.omargroup.movies_thyme.dtos.createDtos;

import jakarta.validation.constraints.NotNull;

public record MovieCastCreateDto(
        @NotNull Long movieId,
        @NotNull Long personId,
        @NotNull String personType,
        String role // Required for actors
) {

}
