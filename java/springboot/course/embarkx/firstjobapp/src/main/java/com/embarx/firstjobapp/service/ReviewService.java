package com.embarx.firstjobapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.embarx.firstjobapp.model.Review;
import com.embarx.firstjobapp.repository.ReviewRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository; 
    private final CompanyService companyService;

    public List<Review> getAllReviews(Long id){
        return reviewRepository.findByCompanyId(id);
    }

    public Optional<Review> createReviewByCompanyId(Long companyId, Review review){
        return companyService.getCompanyById(companyId).map(company ->{
            review.setCompany(company);
            return reviewRepository.save(review);
        });
    }

    public Optional<Review> getReviewById(Long id){
        return reviewRepository.findById(id);
    }

    public Optional<Review> getReviewByCompanyId(Long reviewId, Long companyId){
        return companyService.getCompanyById(companyId)
        .flatMap(company -> {
            return reviewRepository.findById(reviewId);
        });

    }

    public Optional<Review> updateReview(Long id, Review reviewDetails){
        return reviewRepository.findById(id).map(existingReview ->{
            existingReview.setTitle(reviewDetails.getTitle());
            existingReview.setDescription(reviewDetails.getDescription());
            existingReview.setRating(reviewDetails.getRating());
            return reviewRepository.save(existingReview);
        });
    }

    public Optional<Review> updateReviewForNewCompanyId(Long companyId, Long reviewId, Review reviewDetails){
        return companyService.getCompanyById(companyId)
        .flatMap(company -> {
            return reviewRepository.findById(reviewId).map(existingReview ->{
                existingReview.setTitle(reviewDetails.getTitle());
                existingReview.setDescription(reviewDetails.getDescription());
                existingReview.setRating(reviewDetails.getRating());
                existingReview.setCompany(company);
                return reviewRepository.save(existingReview);
            });
        });
    }

    public boolean deleteReview(Long id){
        return reviewRepository.findById(id).map(review -> {
            reviewRepository.delete(review);
            return true;
        }).orElse(false);
    }

    public boolean deleteReview(Long companyId, Long reviewId){
        return companyService.getCompanyById(companyId)
        .flatMap(company ->  
            reviewRepository.findById(reviewId)
            .map(review -> {
                company.getReviews().remove(review);
                reviewRepository.delete(review);
                return true;
            })
        ).orElse(false);
    }
}
