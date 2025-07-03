package com.example.hateoas.controller;

import com.example.hateoas.entity.UserProfile;
import com.example.hateoas.repository.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

/**
 * REST Controller for UserProfile entity demonstrating HATEOAS with @OneToOne relationship
 * 
 * This controller shows how to handle the owning side of a @OneToOne relationship
 */
@RestController
@RequestMapping("/api/profiles")
public class UserProfileController {

    @Autowired
    private UserProfileRepository userProfileRepository;

    /**
     * Get profile by ID with HATEOAS links
     */
    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<UserProfile>> getProfileById(@PathVariable Long id) {
        return userProfileRepository.findById(id)
            .map(profile -> EntityModel.of(profile)
                .add(linkTo(methodOn(UserProfileController.class).getProfileById(id)).withSelfRel())
                .add(linkTo(methodOn(UserController.class).getUserById(profile.getUser().getId())).withRel("user"))
                .add(linkTo(methodOn(UserProfileController.class).updateProfile(id, null)).withRel("update"))
                .add(linkTo(methodOn(UserProfileController.class).deleteProfile(id)).withRel("delete"))
            )
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Get profile by user ID (demonstrating @OneToOne relationship navigation)
     * 
     * This method shows how to navigate from User to UserProfile
     */
    @GetMapping("/user/{userId}")
    public ResponseEntity<EntityModel<UserProfile>> getProfileByUserId(@PathVariable Long userId) {
        return userProfileRepository.findByUserId(userId)
            .map(profile -> EntityModel.of(profile)
                .add(linkTo(methodOn(UserProfileController.class).getProfileById(profile.getId())).withSelfRel())
                .add(linkTo(methodOn(UserController.class).getUserById(userId)).withRel("user"))
                .add(linkTo(methodOn(UserProfileController.class).getProfileByUserId(userId)).withRel("profile-by-user"))
                .add(linkTo(methodOn(UserProfileController.class).updateProfile(profile.getId(), null)).withRel("update"))
                .add(linkTo(methodOn(UserProfileController.class).deleteProfile(profile.getId())).withRel("delete"))
            )
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Create new profile
     */
    @PostMapping
    public ResponseEntity<EntityModel<UserProfile>> createProfile(@RequestBody UserProfile profile) {
        UserProfile savedProfile = userProfileRepository.save(profile);
        
        EntityModel<UserProfile> profileModel = EntityModel.of(savedProfile)
            .add(linkTo(methodOn(UserProfileController.class).getProfileById(savedProfile.getId())).withSelfRel())
            .add(linkTo(methodOn(UserController.class).getUserById(savedProfile.getUser().getId())).withRel("user"));
        
        return ResponseEntity
            .created(linkTo(methodOn(UserProfileController.class).getProfileById(savedProfile.getId())).toUri())
            .body(profileModel);
    }

    /**
     * Update existing profile
     */
    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<UserProfile>> updateProfile(@PathVariable Long id, @RequestBody UserProfile profileDetails) {
        return userProfileRepository.findById(id)
            .map(profile -> {
                profile.setFirstName(profileDetails.getFirstName());
                profile.setLastName(profileDetails.getLastName());
                profile.setBio(profileDetails.getBio());
                profile.setPhoneNumber(profileDetails.getPhoneNumber());
                profile.setBirthDate(profileDetails.getBirthDate());
                UserProfile updatedProfile = userProfileRepository.save(profile);
                
                return EntityModel.of(updatedProfile)
                    .add(linkTo(methodOn(UserProfileController.class).getProfileById(id)).withSelfRel())
                    .add(linkTo(methodOn(UserController.class).getUserById(updatedProfile.getUser().getId())).withRel("user"));
            })
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Delete profile
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProfile(@PathVariable Long id) {
        return userProfileRepository.findById(id)
            .map(profile -> {
                userProfileRepository.delete(profile);
                return ResponseEntity.noContent().build();
            })
            .orElse(ResponseEntity.notFound().build());
    }
}