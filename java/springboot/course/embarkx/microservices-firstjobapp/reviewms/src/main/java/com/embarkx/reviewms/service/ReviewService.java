package com.embarkx.reviewms.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.embarkx.reviewms.model.Review;
import com.embarkx.reviewms.repository.ReviewRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository; 

    public List<Review> getAllReviews(Long id){
        return reviewRepository.findByCompanyId(id);
    }

    public Optional<Review> createReviewByCompanyId(Long companyId, Review review){
        if(companyId != null && review !=null){
            review.setCompanyId(companyId);
            return Optional.of(reviewRepository.save(review));
        }
        return Optional.empty();
    }

    public Optional<Review> getReviewById(Long id){
        return reviewRepository.findById(id);
    }


    public Optional<Review> updateReview(Long reviewId, Review reviewDetails){
        return reviewRepository.findById(reviewId)
           .map(existingReview ->{
                existingReview.setTitle(reviewDetails.getTitle());
                existingReview.setDescription(reviewDetails.getDescription());
                existingReview.setRating(reviewDetails.getRating());
                existingReview.setCompanyId(reviewDetails.getCompanyId());
                return reviewRepository.save(existingReview); 
        });
    }

    public boolean deleteReview(Long id){
        return reviewRepository.findById(id).map(review -> {
            reviewRepository.delete(review);
            return true;
        }).orElse(false);
    }
}

