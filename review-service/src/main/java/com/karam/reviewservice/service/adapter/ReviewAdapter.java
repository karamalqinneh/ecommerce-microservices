package com.karam.reviewservice.service.adapter;


import com.karam.reviewservice.domain.Review;
import com.karam.reviewservice.web.dto.ReviewDto;

public class ReviewAdapter {

    public static ReviewDto getDto(Review review) {
        return new ReviewDto(
                review.getReviewId(),
                review.getProductNumber(),
                review.getUserId(),
                review.getDescription()
        );
    }

    public static Review getReview(ReviewDto review) {
        return new Review(
                review.getReviewId(),
                review.getProductNumber(),
                review.getUserId(),
                review.getDescription()
        );
    }
}
