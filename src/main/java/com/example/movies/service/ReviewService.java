package com.example.movies.service;

import com.example.movies.model.Movie;
import com.example.movies.model.Review;
import com.example.movies.repository.ReviewRepository;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final MongoTemplate mongoTemplate;

    public ReviewService(ReviewRepository reviewRepository, MongoTemplate mongoTemplate) {
        this.reviewRepository = reviewRepository;
        this.mongoTemplate = mongoTemplate;
    }

    public Review createReview(String reviewBody, String imdbId) {
        Review review = reviewRepository.insert(new Review(reviewBody));

        mongoTemplate.update(Movie.class)
                .matching(Criteria.where("imdbId").is(imdbId))
                .apply(new Update().push("reviewIds").value(review))
                .first();

        return review;
    }

}
