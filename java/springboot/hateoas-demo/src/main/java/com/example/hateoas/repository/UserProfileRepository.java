package com.example.hateoas.repository;

import com.example.hateoas.entity.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository interface for UserProfile entity
 */
@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {

    /**
     * Find user profile by user ID
     */
    Optional<UserProfile> findByUserId(Long userId);

    /**
     * Find user profile by user username
     */
    Optional<UserProfile> findByUserUsername(String username);
}