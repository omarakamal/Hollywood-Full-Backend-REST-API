package com.omargroup.movies_thyme.repository;

import com.omargroup.movies_thyme.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository  extends JpaRepository<Review,Long> {
    List<Review> findByMovieMovieId(Long movieId);
    List<Review> findByUserUserId(Long userId);
}
