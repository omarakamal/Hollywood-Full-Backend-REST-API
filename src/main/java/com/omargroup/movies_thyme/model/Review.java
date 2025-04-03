package com.omargroup.movies_thyme.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

@Entity
@Table(name = "review")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Long reviewId;
    @Column(name = "rating_stars")
    @Min(1)
    @Max(5)
    private int ratingStars;

    @Column(name = "rating_body")
    private String ratingBody;


    @ManyToOne
    @JoinColumn(name = "creator_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "movie_id",nullable = false)
    private Movie movie;


    public Review(Long reviewId, int ratingStars, String ratingBody, User user, Movie movie) {
        this.reviewId = reviewId;
        this.ratingStars = ratingStars;
        this.ratingBody = ratingBody;
        this.user = user;
        this.movie = movie;
    }

    public Review() {
    }

    public Long getReviewId() {
        return reviewId;
    }

    public void setReviewId(Long reviewId) {
        this.reviewId = reviewId;
    }

    @Min(1)
    @Max(5)
    public int getRatingStars() {
        return ratingStars;
    }

    public void setRatingStars(@Min(1) @Max(5) int ratingStars) {
        this.ratingStars = ratingStars;
    }

    public String getRatingBody() {
        return ratingBody;
    }

    public void setRatingBody(String ratingBody) {
        this.ratingBody = ratingBody;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    @Override
    public String toString() {
        return "Review{" +
                "reviewId=" + reviewId +
                ", ratingStars=" + ratingStars +
                ", ratingBody='" + ratingBody + '\'' +
                ", user=" + user +
                ", movie=" + movie +
                '}';
    }
}
