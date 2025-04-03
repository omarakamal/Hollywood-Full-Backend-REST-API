package com.omargroup.movies_thyme.dtos.summaryDtos;

import com.omargroup.movies_thyme.model.MovieCast;

public record CastMemberDto (
        Long personId,
        String firstName,
        String photoUrl,
        String personType,
        String characterName
){
    public CastMemberDto(MovieCast movieCast) {
        this(
                movieCast.getPerson().getPersonId(),
                movieCast.getPerson().getFirstName() + " " + movieCast.getPerson().getLastName(),
                movieCast.getPerson().getPhotoUrl(),
                movieCast.getPersonType(),
                movieCast.getRole() // Will be null for directors/writers
        );
    }
}
