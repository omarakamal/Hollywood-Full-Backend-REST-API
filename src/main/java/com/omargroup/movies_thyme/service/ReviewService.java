package com.omargroup.movies_thyme.service;

import com.omargroup.movies_thyme.dtos.createDtos.ReviewCreateDto;
import com.omargroup.movies_thyme.dtos.summaryDtos.MovieSummaryDto;
import com.omargroup.movies_thyme.dtos.summaryDtos.ReviewDto;
import com.omargroup.movies_thyme.dtos.summaryDtos.UserDto;
import com.omargroup.movies_thyme.exceptions.ResourceNotFoundException;
import com.omargroup.movies_thyme.model.Movie;
import com.omargroup.movies_thyme.model.Review;
import com.omargroup.movies_thyme.model.User;
import com.omargroup.movies_thyme.repository.MovieRepository;
import com.omargroup.movies_thyme.repository.ReviewRepository;
import com.omargroup.movies_thyme.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final MovieRepository movieRepository;
    private final UserRepository userRepository;

    public ReviewService(ReviewRepository reviewRepository, MovieRepository movieRepository, UserRepository userRepository) {
        this.reviewRepository = reviewRepository;
        this.movieRepository = movieRepository;
        this.userRepository = userRepository;

    }

    public List<ReviewDto> getReviewByForMovie(Long movieId){

        return reviewRepository.findByMovieMovieId(movieId).stream().map(ReviewDto::new).toList();
    }

    public List<ReviewDto> getReviewByForUser(Long userId){
        return reviewRepository.findByUserUserId(userId).stream().map(ReviewDto::new).toList();
    }

    public ReviewDto createReview(ReviewCreateDto createDto,Long movieId){
        Movie foundMovie = movieRepository.findById(movieId)
                .orElseThrow(()-> new ResourceNotFoundException("Movie with id not found " + movieId));
        Object authentication = SecurityContextHolder.getContext().getAuthentication().getPrincipal();



        Review review = new Review();
        review.setRatingBody(createDto.ratingBody());
        review.setRatingStars(createDto.ratingStars());
        review.setUser((User) authentication);
        review.setMovie(foundMovie);


        return new ReviewDto(reviewRepository.save(review));
    }

}
