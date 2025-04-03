package com.omargroup.movies_thyme.controller;

import com.omargroup.movies_thyme.dtos.createDtos.PersonCreateDto;
import com.omargroup.movies_thyme.dtos.summaryDtos.MovieSummaryDto;
import com.omargroup.movies_thyme.dtos.summaryDtos.PersonSummaryDto;
import com.omargroup.movies_thyme.model.Person;
import com.omargroup.movies_thyme.service.PersonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.apache.coyote.Response;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/person")
public class PersonController {
    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/{personId}/movies")
    public ResponseEntity<Page<MovieSummaryDto>> getMoviesForActorById(
            @PathVariable Long personId,
            @RequestParam (defaultValue = "0") int page,
            @RequestParam (defaultValue = "10") int size,
            @RequestParam (defaultValue = "title") String sortBy){
        Pageable pageable = PageRequest.of(page,size, Sort.by(sortBy));
        return new ResponseEntity<>(personService.getMoviesByPersonId(personId,pageable), HttpStatus.OK) ;

    }

    @PostMapping
    public ResponseEntity<PersonSummaryDto> createPerson(@RequestBody PersonCreateDto personCreateDto){

        System.out.println(personCreateDto);
        return new ResponseEntity<>(personService.createPerson(personCreateDto),HttpStatus.CREATED);
    }

    @Operation(summary = "Search persons dynamically", description = "Search persons based on optional fields")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully retrieved results"),
            @ApiResponse(responseCode = "400", description = "Invalid request parameters")
    })
    @GetMapping("/search")
    public List<Person> searchPersons(
            @Parameter(description = "First name of the person") @RequestParam(required = false) String firstName,
            @Parameter(description = "Last name of the person") @RequestParam(required = false) String lastName,
            @Parameter(description = "Birth date (YYYY-MM-DD)") @RequestParam(required = false) LocalDate birthDate,
            @Parameter(description = "Nationality of the person") @RequestParam(required = false) String nationality) {

        return personService.searchPersons(firstName, lastName, birthDate, nationality);
    }





}
