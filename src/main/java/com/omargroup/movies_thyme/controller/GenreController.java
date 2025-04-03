package com.omargroup.movies_thyme.controller;

import com.omargroup.movies_thyme.dtos.createDtos.GenreCreateDto;
import com.omargroup.movies_thyme.dtos.summaryDtos.GenreSimpleDto;
import com.omargroup.movies_thyme.dtos.summaryDtos.UserDto;
import com.omargroup.movies_thyme.model.User;
import com.omargroup.movies_thyme.service.GenreService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.nio.file.attribute.UserPrincipal;
import java.security.Principal;
import java.util.List;
@Tag(name = "Genre API", description = "Endpoints for managing Genres in DB")
@RestController
@RequestMapping("/api/genres")
public class GenreController {

    private final GenreService genreService;


    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully retrieved movie"),
            @ApiResponse(responseCode = "404", description = "Movie not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @Operation(summary = "Create a new Genre", description = "Will take in a GenreCreateDTO and create a new movie")

    @PostMapping
    public ResponseEntity<GenreSimpleDto> createGenre(@RequestBody GenreCreateDto request)
    {
        GenreSimpleDto response = genreService.createGenre(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    @GetMapping
    public ResponseEntity<Page<GenreSimpleDto>> getAllGenres(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "name") String sortBy
    ){
        Pageable pageable = PageRequest.of(page,size, Sort.by(sortBy));
        return new ResponseEntity<>(genreService.getAllGenresPaginated(pageable),HttpStatus.OK);
    }

    @GetMapping("/{genreId}")
    public ResponseEntity<GenreSimpleDto> getGenreById(@Parameter(description = "ID of the desired genre") @PathVariable Long genreId){
        return new ResponseEntity<>(genreService.getGenreById(genreId),HttpStatus.OK);
    }

//    @GetMapping("/bigOmar")
//    public UserDto bigOmar(){
//        Object authentication = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//
//        return new UserDto((User) authentication);
//    }




}
