package com.example.hateoas.controller;

import com.example.hateoas.entity.User;
import com.example.hateoas.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

/**
 * REST Controller for User entity demonstrating HATEOAS concepts
 * 
 * Key HATEOAS concepts demonstrated:
 * - linkTo(): Creates links to controller methods
 * - methodOn(): Type-safe way to reference controller methods
 * - EntityModel: Wraps entity with links
 * - CollectionModel: Wraps collection of entities with links
 */
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    /**
     * Get all users with HATEOAS links
     * 
     * This method demonstrates:
     * - CollectionModel for wrapping collections
     * - linkTo() and methodOn() for creating links
     * - Adding self links to each user
     * - Adding collection-level links
     */
    @GetMapping
    public CollectionModel<EntityModel<User>> getAllUsers() {
        List<User> users = userRepository.findAll();
        
        // Convert each user to EntityModel with links
        List<EntityModel<User>> userModels = users.stream()
            .map(user -> EntityModel.of(user)
                // Add self link to individual user
                .add(linkTo(methodOn(UserController.class).getUserById(user.getId())).withSelfRel())
                // Add link to user's posts
                .add(linkTo(methodOn(PostController.class).getPostsByUserId(user.getId())).withRel("posts"))
                // Add link to user's profile
                .add(linkTo(methodOn(UserProfileController.class).getProfileByUserId(user.getId())).withRel("profile"))
            )
            .collect(Collectors.toList());
        
        // Create CollectionModel with collection-level links
        return CollectionModel.of(userModels)
            // Add self link to the collection
            .add(linkTo(methodOn(UserController.class).getAllUsers()).withSelfRel())
            // Add link to create new user
            .add(linkTo(methodOn(UserController.class).createUser(null)).withRel("create"));
    }

    /**
     * Get user by ID with HATEOAS links
     * 
     * Demonstrates:
     * - EntityModel for wrapping single entity
     * - Multiple relationship links
     * - Conditional links based on data availability
     */
    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<User>> getUserById(@PathVariable Long id) {
        return userRepository.findById(id)
            .map(user -> EntityModel.of(user)
                // Self link
                .add(linkTo(methodOn(UserController.class).getUserById(id)).withSelfRel())
                // Link to all users
                .add(linkTo(methodOn(UserController.class).getAllUsers()).withRel("users"))
                // Link to user's posts
                .add(linkTo(methodOn(PostController.class).getPostsByUserId(id)).withRel("posts"))
                // Link to user's profile
                .add(linkTo(methodOn(UserProfileController.class).getProfileByUserId(id)).withRel("profile"))
                // Link to update user
                .add(linkTo(methodOn(UserController.class).updateUser(id, null)).withRel("update"))
                // Link to delete user
                .add(linkTo(methodOn(UserController.class).deleteUser(id)).withRel("delete"))
            )
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Create new user
     * 
     * Demonstrates:
     * - POST operation with HATEOAS
     * - Location header with self link
     * - Links to related resources
     */
    @PostMapping
    public ResponseEntity<EntityModel<User>> createUser(@RequestBody User user) {
        User savedUser = userRepository.save(user);
        
        EntityModel<User> userModel = EntityModel.of(savedUser)
            .add(linkTo(methodOn(UserController.class).getUserById(savedUser.getId())).withSelfRel())
            .add(linkTo(methodOn(UserController.class).getAllUsers()).withRel("users"))
            .add(linkTo(methodOn(PostController.class).getPostsByUserId(savedUser.getId())).withRel("posts"))
            .add(linkTo(methodOn(UserProfileController.class).getProfileByUserId(savedUser.getId())).withRel("profile"));
        
        return ResponseEntity
            .created(linkTo(methodOn(UserController.class).getUserById(savedUser.getId())).toUri())
            .body(userModel);
    }

    /**
     * Update existing user
     * 
     * Demonstrates:
     * - PUT operation with HATEOAS
     * - Links after successful update
     */
    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<User>> updateUser(@PathVariable Long id, @RequestBody User userDetails) {
        return userRepository.findById(id)
            .map(user -> {
                user.setUsername(userDetails.getUsername());
                user.setEmail(userDetails.getEmail());
                User updatedUser = userRepository.save(user);
                
                return EntityModel.of(updatedUser)
                    .add(linkTo(methodOn(UserController.class).getUserById(id)).withSelfRel())
                    .add(linkTo(methodOn(UserController.class).getAllUsers()).withRel("users"))
                    .add(linkTo(methodOn(PostController.class).getPostsByUserId(id)).withRel("posts"))
                    .add(linkTo(methodOn(UserProfileController.class).getProfileByUserId(id)).withRel("profile"));
            })
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Delete user
     * 
     * Demonstrates:
     * - DELETE operation
     * - No content response with location to collection
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        return userRepository.findById(id)
            .map(user -> {
                userRepository.delete(user);
                return ResponseEntity.noContent().build();
            })
            .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Get user with profile (demonstrating @OneToOne relationship)
     */
    @GetMapping("/{id}/with-profile")
    public ResponseEntity<EntityModel<User>> getUserWithProfile(@PathVariable Long id) {
        return userRepository.findByIdWithProfile(id)
            .map(user -> EntityModel.of(user)
                .add(linkTo(methodOn(UserController.class).getUserById(id)).withSelfRel())
                .add(linkTo(methodOn(UserController.class).getAllUsers()).withRel("users"))
                .add(linkTo(methodOn(UserProfileController.class).getProfileByUserId(id)).withRel("profile"))
            )
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Get user with posts (demonstrating @OneToMany relationship)
     */
    @GetMapping("/{id}/with-posts")
    public ResponseEntity<EntityModel<User>> getUserWithPosts(@PathVariable Long id) {
        return userRepository.findByIdWithPosts(id)
            .map(user -> EntityModel.of(user)
                .add(linkTo(methodOn(UserController.class).getUserById(id)).withSelfRel())
                .add(linkTo(methodOn(UserController.class).getAllUsers()).withRel("users"))
                .add(linkTo(methodOn(PostController.class).getPostsByUserId(id)).withRel("posts"))
            )
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Get user with roles (demonstrating @ManyToMany relationship)
     */
    @GetMapping("/{id}/with-roles")
    public ResponseEntity<EntityModel<User>> getUserWithRoles(@PathVariable Long id) {
        return userRepository.findByIdWithRoles(id)
            .map(user -> EntityModel.of(user)
                .add(linkTo(methodOn(UserController.class).getUserById(id)).withSelfRel())
                .add(linkTo(methodOn(UserController.class).getAllUsers()).withRel("users"))
                .add(linkTo(methodOn(RoleController.class).getAllRoles()).withRel("roles"))
            )
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }
}