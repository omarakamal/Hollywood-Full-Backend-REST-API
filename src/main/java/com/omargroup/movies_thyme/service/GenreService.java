package com.omargroup.movies_thyme.service;

import com.omargroup.movies_thyme.dtos.createDtos.GenreCreateDto;
import com.omargroup.movies_thyme.dtos.summaryDtos.GenreSimpleDto;
import com.omargroup.movies_thyme.exceptions.ResourceExistsException;
import com.omargroup.movies_thyme.exceptions.ResourceNotFoundException;
import com.omargroup.movies_thyme.model.Genre;
import com.omargroup.movies_thyme.repository.GenreRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreService {
    private final GenreRepository genreRepository;

    public GenreService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }


//    public List<GenreSimpleDto> getAllGenres(){
//        List<Genre> allGenres = genreRepository.findAll();
//        return allGenres.stream().map(genre->new GenreSimpleDto(genre)).toList();
//    }

    public Page<GenreSimpleDto> getAllGenresPaginated(Pageable pageable){
        return genreRepository.findAll(pageable)
                .map(GenreSimpleDto::new);
    }


    public GenreSimpleDto getGenreById(Long genreId){
        Genre foundGenre = genreRepository.findById(genreId).orElseThrow(()->new ResourceNotFoundException("Genre doesnt exist with this id " + genreId));

        return new GenreSimpleDto(foundGenre) ;
    }

    public GenreSimpleDto createGenre(GenreCreateDto genreCreateDto){
        Genre foundGenre = genreRepository.findByName(genreCreateDto.name());

        if(foundGenre != null){
            throw new ResourceExistsException("Genre with name " + genreCreateDto.name() + " Already exists");
        }
        else{
            Genre createdGenre = new Genre();
            createdGenre.setName(genreCreateDto.name());
            Genre savedGenre = genreRepository.save(createdGenre);

            return new GenreSimpleDto(savedGenre);
        }
    }



}

