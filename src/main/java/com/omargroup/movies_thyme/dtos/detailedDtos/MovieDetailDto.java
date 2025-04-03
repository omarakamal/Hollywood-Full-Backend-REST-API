package com.omargroup.movies_thyme.dtos.detailedDtos;

import com.omargroup.movies_thyme.dtos.summaryDtos.CastMemberDto;
import com.omargroup.movies_thyme.dtos.summaryDtos.GenreSimpleDto;
import com.omargroup.movies_thyme.model.Movie;

import java.time.LocalDateTime;
import java.util.List;

public record MovieDetailDto(
    Long movieId,
    String title,
    String description,
    int releaseYear,
    double rating,
    boolean hasOscars,
    int duration,
    List<GenreSimpleDto> genres,
    List<CastMemberDto> cast

    ) {
    public MovieDetailDto(Movie movie) {
        this(
                movie.getMovieId(),
                movie.getTitle(),
                movie.getDescription(),
                movie.getReleaseYear(),
                movie.getRating(),
                movie.isHasOscars(),
                movie.getDuration(),
                movie.getGenres().stream().map(GenreSimpleDto::new).toList(),
                movie.getMovieCast().stream()
                        .map(CastMemberDto::new)
                        .toList()
        );
    }
}
