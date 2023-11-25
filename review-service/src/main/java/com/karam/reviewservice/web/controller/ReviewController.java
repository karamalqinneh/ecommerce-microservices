package com.karam.reviewservice.web.controller;

import com.karam.reviewservice.domain.Review;
import com.karam.reviewservice.service.ReviewService;
import com.karam.reviewservice.web.dto.ReviewDto;
import com.karam.reviewservice.web.util.PageResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/review")
//@AllArgsConstructor
public class ReviewController {

    @Value("${app.secret}")
    private String appSecret;

    @Autowired
    private ReviewService reviewService;

    @GetMapping("/secret")
    public String getAppSecret() {
        return appSecret;
    }

    @GetMapping("/{productId}")
    public ResponseEntity<?> getProductReviews(@PathVariable String productId,
                                               @RequestParam(defaultValue = "0") int page,
                                               @RequestParam(defaultValue = "10") int size) {
        System.out.println("############################################### CALLED @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        Pageable paging = PageRequest.of(page, size);
        Page<Review> query = reviewService.getAllReviews(paging, productId);

        Map<String, Object> response = PageResponse.getPagedResponse(query);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }



    @PostMapping("/{productId}")
    public ResponseEntity<?> addProductReviews(@PathVariable String productId, @RequestBody ReviewDto reviewDto) {
        reviewService.addNewReview(productId, reviewDto);
        return new ResponseEntity<>(reviewDto, HttpStatus.CREATED);
    }
}
