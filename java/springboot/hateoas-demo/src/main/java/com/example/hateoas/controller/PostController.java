package com.example.hateoas.controller;

import com.example.hateoas.entity.Post;
import com.example.hateoas.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

/**
 * REST Controller for Post entity demonstrating HATEOAS with @ManyToOne relationship
 */
@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private PostRepository postRepository;

    /**
     * Get all posts with HATEOAS links
     */
    @GetMapping
    public CollectionModel<EntityModel<Post>> getAllPosts() {
        List<Post> posts = postRepository.findAllByOrderByCreatedAtDesc();
        
        List<EntityModel<Post>> postModels = posts.stream()
            .map(post -> EntityModel.of(post)
                // Self link
                .add(linkTo(methodOn(PostController.class).getPostById(post.getId())).withSelfRel())
                // Link to the author (demonstrating @ManyToOne relationship)
                .add(linkTo(methodOn(UserController.class).getUserById(post.getAuthor().getId())).withRel("author"))
                // Link to update post
                .add(linkTo(methodOn(PostController.class).updatePost(post.getId(), null)).withRel("update"))
                // Link to delete post
                .add(linkTo(methodOn(PostController.class).deletePost(post.getId())).withRel("delete"))
            )
            .collect(Collectors.toList());
        
        return CollectionModel.of(postModels)
            .add(linkTo(methodOn(PostController.class).getAllPosts()).withSelfRel())
            .add(linkTo(methodOn(PostController.class).createPost(null)).withRel("create"));
    }

    /**
     * Get post by ID with HATEOAS links
     */
    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Post>> getPostById(@PathVariable Long id) {
        return postRepository.findById(id)
            .map(post -> EntityModel.of(post)
                .add(linkTo(methodOn(PostController.class).getPostById(id)).withSelfRel())
                .add(linkTo(methodOn(PostController.class).getAllPosts()).withRel("posts"))
                .add(linkTo(methodOn(UserController.class).getUserById(post.getAuthor().getId())).withRel("author"))
                .add(linkTo(methodOn(PostController.class).updatePost(id, null)).withRel("update"))
                .add(linkTo(methodOn(PostController.class).deletePost(id)).withRel("delete"))
            )
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Get posts by user ID (demonstrating @OneToMany relationship from User perspective)
     */
    @GetMapping("/user/{userId}")
    public CollectionModel<EntityModel<Post>> getPostsByUserId(@PathVariable Long userId) {
        List<Post> posts = postRepository.findByAuthorId(userId);
        
        List<EntityModel<Post>> postModels = posts.stream()
            .map(post -> EntityModel.of(post)
                .add(linkTo(methodOn(PostController.class).getPostById(post.getId())).withSelfRel())
                .add(linkTo(methodOn(UserController.class).getUserById(userId)).withRel("author"))
            )
            .collect(Collectors.toList());
        
        return CollectionModel.of(postModels)
            .add(linkTo(methodOn(PostController.class).getPostsByUserId(userId)).withSelfRel())
            .add(linkTo(methodOn(UserController.class).getUserById(userId)).withRel("author"))
            .add(linkTo(methodOn(PostController.class).getAllPosts()).withRel("all-posts"));
    }

    /**
     * Create new post
     */
    @PostMapping
    public ResponseEntity<EntityModel<Post>> createPost(@RequestBody Post post) {
        Post savedPost = postRepository.save(post);
        
        EntityModel<Post> postModel = EntityModel.of(savedPost)
            .add(linkTo(methodOn(PostController.class).getPostById(savedPost.getId())).withSelfRel())
            .add(linkTo(methodOn(PostController.class).getAllPosts()).withRel("posts"))
            .add(linkTo(methodOn(UserController.class).getUserById(savedPost.getAuthor().getId())).withRel("author"));
        
        return ResponseEntity
            .created(linkTo(methodOn(PostController.class).getPostById(savedPost.getId())).toUri())
            .body(postModel);
    }

    /**
     * Update existing post
     */
    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<Post>> updatePost(@PathVariable Long id, @RequestBody Post postDetails) {
        return postRepository.findById(id)
            .map(post -> {
                post.setTitle(postDetails.getTitle());
                post.setContent(postDetails.getContent());
                Post updatedPost = postRepository.save(post);
                
                return EntityModel.of(updatedPost)
                    .add(linkTo(methodOn(PostController.class).getPostById(id)).withSelfRel())
                    .add(linkTo(methodOn(PostController.class).getAllPosts()).withRel("posts"))
                    .add(linkTo(methodOn(UserController.class).getUserById(updatedPost.getAuthor().getId())).withRel("author"));
            })
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Delete post
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePost(@PathVariable Long id) {
        return postRepository.findById(id)
            .map(post -> {
                postRepository.delete(post);
                return ResponseEntity.noContent().build();
            })
            .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Search posts by title
     */
    @GetMapping("/search")
    public CollectionModel<EntityModel<Post>> searchPostsByTitle(@RequestParam String title) {
        List<Post> posts = postRepository.findByTitleContainingIgnoreCase(title);
        
        List<EntityModel<Post>> postModels = posts.stream()
            .map(post -> EntityModel.of(post)
                .add(linkTo(methodOn(PostController.class).getPostById(post.getId())).withSelfRel())
                .add(linkTo(methodOn(UserController.class).getUserById(post.getAuthor().getId())).withRel("author"))
            )
            .collect(Collectors.toList());
        
        return CollectionModel.of(postModels)
            .add(linkTo(methodOn(PostController.class).searchPostsByTitle(title)).withSelfRel())
            .add(linkTo(methodOn(PostController.class).getAllPosts()).withRel("all-posts"));
    }
}