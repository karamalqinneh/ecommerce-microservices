package com.karam.reviewservice.service;

import com.karam.reviewservice.data.ReviewRepository;
import com.karam.reviewservice.domain.Review;
import com.karam.reviewservice.service.adapter.ReviewAdapter;
import com.karam.reviewservice.web.dto.ProductDto;
import com.karam.reviewservice.web.dto.ReviewDto;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@AllArgsConstructor
public class ReviewService {

    private WebClient.Builder webClient;
    private ReviewRepository reviewRepository;

    public Page<Review> getAllReviews(Pageable paging, String productId) {
        return reviewRepository.findAllByProductNumber(paging, productId);
    }

    public void addNewReview(String productId, ReviewDto reviewDto) {
        ProductDto product = webClient.build().get().uri("http://store-service/product/" + productId).retrieve().bodyToMono(ProductDto.class).block();
        if(product == null) throw new RuntimeException("Product doesn't exist");
        Review review = ReviewAdapter.getReview(reviewDto);

        reviewRepository.save(review);
    }
}
