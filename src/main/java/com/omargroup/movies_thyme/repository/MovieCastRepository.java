package com.omargroup.movies_thyme.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.omargroup.movies_thyme.model.MovieCast;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieCastRepository extends JpaRepository<MovieCast, Long> {

    List<MovieCast> findByMovieMovieId(Long movieId);

//    List<MovieCast> findByPersonPersonId(Long actorId);


    //    finds all movies a person has worked on based on their personType
//    Page<MovieCast> findByPersonIdAndPersonType(
//            Long personId,
//            String personType,
//            Pageable pageable
//    );
    @Query("SELECT mc FROM MovieCast mc WHERE mc.person.personId = :personId AND mc.personType = :personType")
    Page<MovieCast> findByPersonIdAndPersonType(
            @Param("personId") Long personId,
            @Param("personType") String personType,
            Pageable pageable
    );

//    finds all movies a person has worked on
    Page<MovieCast> findByPersonPersonId(
            Long personId,
            Pageable pageable
    );

}
