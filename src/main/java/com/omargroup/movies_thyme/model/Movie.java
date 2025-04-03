package com.omargroup.movies_thyme.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movie_id")
    private Long movieId;

    @NotBlank(message = "Title is required")
    @Column(name = "title", nullable= false)
    private String title;


    @Min(value = 1888, message = "Release year must be valid")
    @Max(value = 2100, message = "Release year must be valid")
    @Column(name = "release_year", nullable = false)
    private int releaseYear;


    @DecimalMin(value = "0.0", message = "Rating must be at least 0.0")
    @DecimalMax(value = "10.0", message = "Rating cannot exceed 10.0")
    @Column(name = "rating")
    private double rating;

    @Column(name = "has_oscars")
    private boolean hasOscars;

    @Column(name = "description")
    private String description;


    @Column(name = "duration")
    private int duration;


    @CreatedDate
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;


    @OneToMany(mappedBy = "movie")
    @JsonIgnore
    private Set<MovieCast> movieCast;


    @ManyToMany
    @JoinTable(
            name = "movie_genre",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    private Set<Genre> genres = new HashSet<>();

    public Movie(Long movieId, String title, int releaseYear, double rating, boolean hasOscars, String description, int duration, LocalDateTime createdAt, LocalDateTime updatedAt, Set<MovieCast> movieCast, Set<Genre> genres) {
        this.movieId = movieId;
        this.title = title;
        this.releaseYear = releaseYear;
        this.rating = rating;
        this.hasOscars = hasOscars;
        this.description = description;
        this.duration = duration;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.movieCast = movieCast;
        this.genres = genres;
    }

    public Movie() {
    }

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    public @NotBlank(message = "Title is required") String getTitle() {
        return title;
    }

    public void setTitle(@NotBlank(message = "Title is required") String title) {
        this.title = title;
    }

    @Min(value = 1888, message = "Release year must be valid")
    @Max(value = 2100, message = "Release year must be valid")
    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(@Min(value = 1888, message = "Release year must be valid") @Max(value = 2100, message = "Release year must be valid") int releaseYear) {
        this.releaseYear = releaseYear;
    }

    @DecimalMin(value = "0.0", message = "Rating must be at least 0.0")
    @DecimalMax(value = "10.0", message = "Rating cannot exceed 10.0")
    public double getRating() {
        return rating;
    }

    public void setRating(@DecimalMin(value = "0.0", message = "Rating must be at least 0.0") @DecimalMax(value = "10.0", message = "Rating cannot exceed 10.0") double rating) {
        this.rating = rating;
    }

    public boolean isHasOscars() {
        return hasOscars;
    }

    public void setHasOscars(boolean hasOscars) {
        this.hasOscars = hasOscars;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }



    public Set<MovieCast> getMovieCast() {
        return movieCast;
    }

    public void setMovieCast(Set<MovieCast> movieCast) {
        this.movieCast = movieCast;
    }

    public Set<Genre> getGenres() {
        return genres;
    }

    public void setGenres(Set<Genre> genres) {
        this.genres = genres;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "movieId=" + movieId +
                ", title='" + title + '\'' +
                ", releaseYear=" + releaseYear +
                ", rating=" + rating +
                ", hasOscars=" + hasOscars +
                ", description='" + description + '\'' +
                ", duration=" + duration +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", movieCast=" + movieCast +
                ", genres=" + genres +
                '}';
    }
}