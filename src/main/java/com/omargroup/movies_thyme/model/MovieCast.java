package com.omargroup.movies_thyme.model;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "movie_cast")
public class MovieCast {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movie_cast_id")
    private Long movieCastId;

    @ManyToOne
    @JoinColumn(name = "movie_id", nullable = false)
    private Movie movie;

    @ManyToOne
    @JoinColumn(name = "person_id", nullable = false)
    private Person person;

    @Column(name = "role")
    private String role;

    @Column(name = "person_type")
    private String personType;


    public MovieCast(Long movieCastId, Movie movie, Person person, String role, String personType) {
        this.movieCastId = movieCastId;
        this.movie = movie;
        this.person = person;
        this.role = role;
        this.personType = personType;
    }

    public MovieCast() {
    }

    public Long getMovieCastId() {
        return movieCastId;
    }

    public void setMovieCastId(Long movieCastId) {
        this.movieCastId = movieCastId;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPersonType() {
        return personType;
    }

    public void setPersonType(String personType) {
        this.personType = personType;
    }

    @Override
    public String toString() {
        return "MovieCast{" +
                "movieCastId=" + movieCastId +
                ", movie=" + movie +
                ", person=" + person +
                ", role='" + role + '\'' +
                ", personType='" + personType + '\'' +
                '}';
    }
}