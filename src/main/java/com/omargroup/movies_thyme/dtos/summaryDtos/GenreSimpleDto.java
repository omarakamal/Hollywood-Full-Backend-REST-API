package com.omargroup.movies_thyme.dtos.summaryDtos;

import com.omargroup.movies_thyme.model.Genre;

public record GenreSimpleDto(
        Long genreId,
        String name
) {
    public GenreSimpleDto(Genre genre){
        this(genre.getGenreId(), genre.getName());
    }
}
