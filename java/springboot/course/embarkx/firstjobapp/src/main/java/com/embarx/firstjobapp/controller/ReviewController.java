package com.embarx.firstjobapp.controller;

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

import com.embarx.firstjobapp.model.Review;
import com.embarx.firstjobapp.service.ReviewService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/companies/{companyId}")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping("/reviews")
    public ResponseEntity<List<Review>> getAllReviews(@PathVariable Long companyId) {
        return ResponseEntity.ok(reviewService.getAllReviews(companyId));
    }

    @GetMapping("/reviews/{reviewId}")
    public ResponseEntity<Review> getReviewById(@PathVariable Long companyId, @PathVariable Long reviewId) {
        return reviewService.getReviewByCompanyId(companyId, reviewId)
                             .map(ResponseEntity::ok)
                             .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/reviews")
    public ResponseEntity<String> createReview(@PathVariable Long companyId, @RequestBody Review review) {
        return reviewService.createReviewByCompanyId(companyId ,review)
        .map(savedReview -> ResponseEntity.ok().body("Created Review with id: " + savedReview.getId()))
        .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body("Company Id" + companyId + " not found"));
    }


    @PutMapping("/reviews/{reviewId}")
    public ResponseEntity<Review> updateReview(@PathVariable Long companyId, @PathVariable Long reviewId,  @RequestBody Review review) {
        return reviewService.updateReviewForNewCompanyId(companyId, reviewId, review)
                             .map(ResponseEntity::ok)
                             .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/reviews/{reviewId}")
    public ResponseEntity<Void> deleteReview(@PathVariable Long companyId, @PathVariable Long reviewId) {
        return reviewService.deleteReview(companyId, reviewId) ? ResponseEntity.noContent().build() :
        ResponseEntity.notFound().build();
                
    }
    
}
