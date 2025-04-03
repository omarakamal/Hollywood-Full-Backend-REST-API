package com.omargroup.movies_thyme.repository;

import com.omargroup.movies_thyme.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie,Long> {


//    List<Movie> findByGenreGenreId(Long genreId);

    Movie findByTitle(String title);





}
