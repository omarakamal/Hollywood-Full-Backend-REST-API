package com.omargroup.movies_thyme.dtos.summaryDtos;

import com.omargroup.movies_thyme.model.Person;

public record PersonSummaryDto (
        Long personId,
        String firstName,
        String lastName,
        String photoUrl
){
    public PersonSummaryDto(Person person){
        this(person.getPersonId(), person.getFirstName(), person.getLastName(),person.getPhotoUrl());
    }
}
