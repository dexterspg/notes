package com.example.hateoas.repository;

import com.example.hateoas.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for Post entity
 */
@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    /**
     * Find all posts by a specific author
     * Spring Data JPA automatically implements this method
     */
    List<Post> findByAuthorId(Long authorId);

    /**
     * Find posts by title containing specific text (case-insensitive)
     */
    List<Post> findByTitleContainingIgnoreCase(String title);

    /**
     * Custom query to find posts with their authors
     */
    @Query("SELECT p FROM Post p LEFT JOIN FETCH p.author WHERE p.id = :id")
    Post findByIdWithAuthor(@Param("id") Long id);

    /**
     * Find posts ordered by creation date (newest first)
     */
    List<Post> findAllByOrderByCreatedAtDesc();
}