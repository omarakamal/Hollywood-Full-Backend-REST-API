package com.omargroup.movies_thyme.dtos.createDtos;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record PersonCreateDto(@NotBlank
                              String firstName,
                              @NotBlank String lastName,
                              LocalDate birthDate,
                              String nationality,
                              String bio,
                              String photoUrl,
                              Boolean wonOscar
) {


}

