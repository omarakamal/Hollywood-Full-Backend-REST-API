package com.omargroup.movies_thyme.service;

import com.omargroup.movies_thyme.dtos.createDtos.PersonCreateDto;
import com.omargroup.movies_thyme.dtos.summaryDtos.GenreSimpleDto;
import com.omargroup.movies_thyme.dtos.summaryDtos.MovieSummaryDto;
import com.omargroup.movies_thyme.dtos.summaryDtos.PersonSummaryDto;
import com.omargroup.movies_thyme.exceptions.ResourceExistsException;
import com.omargroup.movies_thyme.exceptions.ResourceNotFoundException;
import com.omargroup.movies_thyme.model.Genre;
import com.omargroup.movies_thyme.model.Person;
import com.omargroup.movies_thyme.repository.MovieCastRepository;
import com.omargroup.movies_thyme.repository.PersonRepository;
import com.omargroup.movies_thyme.specification.PersonSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PersonService {
    private final PersonRepository personRepository;
    private final MovieCastRepository movieCastRepository;


    public PersonService(PersonRepository personRepository, MovieCastRepository movieCastRepository) {
        this.personRepository = personRepository;
        this.movieCastRepository = movieCastRepository;
    }

//    public List<PersonSummaryDto> getAllPeople(){
//        personRepository.findAll();
//    }

    public Page<MovieSummaryDto> getMoviesByPersonId(Long personId, Pageable pageable) {
        return movieCastRepository.findByPersonPersonId(
                        personId,
                        pageable
                )
                .map(mc -> new MovieSummaryDto(mc.getMovie()));
    }

    public PersonSummaryDto getPersonById(Long personId) {
        Person foundPerson = personRepository.findById(personId).orElseThrow(() -> new ResourceNotFoundException("Person doesnt exist with this id " + personId));

        return new PersonSummaryDto(foundPerson);
    }

    public PersonSummaryDto createPerson(PersonCreateDto personCreateDto) {


        Person createdPerson = new Person();
        createdPerson.setFirstName(personCreateDto.firstName());
        createdPerson.setLastName(personCreateDto.lastName());
        createdPerson.setBio(personCreateDto.bio());
        createdPerson.setNationality(personCreateDto.nationality());
        createdPerson.setBirthDate(personCreateDto.birthDate());
        createdPerson.setPhotoUrl(personCreateDto.photoUrl());
        createdPerson.setWonOscar(personCreateDto.wonOscar());

        Person savedPerson = personRepository.save(createdPerson);

        return new PersonSummaryDto(savedPerson);
    }


    public List<Person> searchPersons(String firstName, String lastName, LocalDate birthDate, String nationality){
        Specification<Person> spec = PersonSpecification.filterBy(firstName, lastName, birthDate, nationality);
        return personRepository.findAll(spec);

    }
}