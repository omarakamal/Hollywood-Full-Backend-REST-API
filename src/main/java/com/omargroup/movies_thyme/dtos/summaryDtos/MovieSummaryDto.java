package com.omargroup.movies_thyme.dtos.summaryDtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.omargroup.movies_thyme.model.Genre;
import com.omargroup.movies_thyme.model.Movie;
import com.omargroup.movies_thyme.model.MovieCast;
import com.omargroup.movies_thyme.model.Person;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public record MovieSummaryDto(
        Long movieId,
        String title,
        int releaseYear,
        double rating,
        int duration,
        String directorName

) {
    public MovieSummaryDto(Movie movie) {
        this(
                movie.getMovieId(),
                movie.getTitle(),
                movie.getReleaseYear(),
                movie.getRating(),
                movie.getDuration(),
                movie.getMovieCast().stream()
                        .filter(mc->mc.getRole().equalsIgnoreCase("Director"))
                        .findFirst()
                        .map(mc->mc.getPerson().getFirstName() + " " + mc.getPerson().getLastName()).orElse("Unknown")
        );
    }


}

