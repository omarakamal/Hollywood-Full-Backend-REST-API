package com.omargroup.movies_thyme.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "person_id")
    private Long personId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(name = "nationality")
    private String nationality;

    @Column(name = "bio")
    private String bio;

    @Column(name = "photo_url")
    private String photoUrl;

    @Column(name = "won_oscar")
    private Boolean wonOscar;



    @OneToMany(mappedBy = "person")
    @JsonIgnore
    Set<MovieCast> moviesCast;


    public Person(Long personId, String firstName, String lastName, LocalDate birthDate, String nationality, String bio, String photoUrl, Boolean wonOscar, Set<MovieCast> moviesCast) {
        this.personId = personId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.nationality = nationality;
        this.bio = bio;
        this.photoUrl = photoUrl;
        this.wonOscar = wonOscar;
        this.moviesCast = moviesCast;
    }

    public Person() {
    }

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public Boolean getWonOscar() {
        return wonOscar;
    }

    public void setWonOscar(Boolean wonOscar) {
        this.wonOscar = wonOscar;
    }

    public Set<MovieCast> getMoviesCast() {
        return moviesCast;
    }

    public void setMoviesCast(Set<MovieCast> moviesCast) {
        this.moviesCast = moviesCast;
    }


    @Override
    public String toString() {
        return "Person{" +
                "personId=" + personId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDate=" + birthDate +
                ", nationality='" + nationality + '\'' +
                ", bio='" + bio + '\'' +
                ", photoUrl='" + photoUrl + '\'' +
                ", wonOscar=" + wonOscar +
                ", moviesCast=" + moviesCast +
                '}';
    }
}