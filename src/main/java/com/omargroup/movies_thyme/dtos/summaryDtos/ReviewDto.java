package com.omargroup.movies_thyme.dtos.summaryDtos;

import com.omargroup.movies_thyme.model.Review;
import com.omargroup.movies_thyme.model.User;

public record ReviewDto (
        Long reviewId,
        int ratingStars,
        String ratingBody,
        UserDto userDto,
        MovieSummaryDto movieSummaryDto

        //reviewId, ratingStars, ratingBody, user, movie

){
    public ReviewDto(Review review){
        this(
                review.getReviewId(),
                review.getRatingStars(),
                review.getRatingBody(),
                new UserDto(review.getUser()),
                new MovieSummaryDto(review.getMovie())
        );
    }
}
