package com.embarkx.reviewms.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.embarkx.reviewms.model.Review;
import com.embarkx.reviewms.service.ReviewService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping
    public ResponseEntity<List<Review>> getAllReviews(@RequestParam Long companyId) {
        return ResponseEntity.ok(reviewService.getAllReviews(companyId));
    }

    @GetMapping("/{reviewId}")
    public ResponseEntity<Review> getReviewById(@PathVariable Long reviewId) {
        return reviewService.getReviewById(reviewId)
                             .map(ResponseEntity::ok)
                             .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<String> createReview(@RequestParam Long companyId, @RequestBody Review review) {
        return reviewService.createReviewByCompanyId(companyId ,review)
        .map(savedReview -> ResponseEntity.ok().body("Created Review with id: " + savedReview.getId()))
        .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body("Company Id" + companyId + " not found"));
    }


    @PutMapping("/{reviewId}")
    public ResponseEntity<Review> updateReview(@PathVariable Long reviewId,  @RequestBody Review review) {
        return reviewService.updateReview(reviewId, review)
                             .map(ResponseEntity::ok)
                             .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<Void> deleteReview(@PathVariable Long reviewId) {
        return reviewService.deleteReview(reviewId) ? ResponseEntity.noContent().build() :
        ResponseEntity.notFound().build();
    }
}
