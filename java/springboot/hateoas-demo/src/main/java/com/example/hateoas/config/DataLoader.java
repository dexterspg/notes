package com.example.hateoas.config;

import com.example.hateoas.entity.Post;
import com.example.hateoas.entity.Role;
import com.example.hateoas.entity.User;
import com.example.hateoas.entity.UserProfile;
import com.example.hateoas.repository.PostRepository;
import com.example.hateoas.repository.RoleRepository;
import com.example.hateoas.repository.UserRepository;
import com.example.hateoas.repository.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

/**
 * Data loader to populate the database with sample data for testing
 * 
 * This class implements CommandLineRunner to run after the application starts
 * and demonstrates all JPA relationships:
 * - @OneToOne: User ↔ UserProfile
 * - @OneToMany: User → Posts
 * - @ManyToMany: User ↔ Roles
 */
@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserProfileRepository userProfileRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void run(String... args) throws Exception {
        loadSampleData();
    }

    private void loadSampleData() {
        System.out.println("Loading sample data...");

        // Create Roles first (for @ManyToMany relationship)
        Role adminRole = new Role("ADMIN", "Administrator with full access");
        Role userRole = new Role("USER", "Regular user with limited access");
        Role moderatorRole = new Role("MODERATOR", "Moderator with content management access");

        adminRole = roleRepository.save(adminRole);
        userRole = roleRepository.save(userRole);
        moderatorRole = roleRepository.save(moderatorRole);

        // Create Users
        User johnDoe = new User("johndoe", "john.doe@example.com");
        User janeSmith = new User("janesmith", "jane.smith@example.com");
        User bobWilson = new User("bobwilson", "bob.wilson@example.com");

        // Assign roles to users (demonstrating @ManyToMany relationship)
        // Use the managed (saved) role entities
        johnDoe.getRoles().add(adminRole);
        johnDoe.getRoles().add(userRole);
        
        janeSmith.getRoles().add(moderatorRole);
        janeSmith.getRoles().add(userRole);
        
        bobWilson.getRoles().add(userRole);

        // Save users (this will also save the many-to-many relationships)
        johnDoe = userRepository.save(johnDoe);
        janeSmith = userRepository.save(janeSmith);
        bobWilson = userRepository.save(bobWilson);

        // Create User Profiles (demonstrating @OneToOne relationship)
        UserProfile johnProfile = new UserProfile(
            "John", 
            "Doe", 
            LocalDate.of(1990, 5, 15),
            "Experienced software developer with expertise in Spring Boot and HATEOAS"
        );
        johnProfile.setPhoneNumber("+1-555-0101");
        johnProfile.setUser(johnDoe); // Set the @OneToOne relationship
        userProfileRepository.save(johnProfile);

        UserProfile janeProfile = new UserProfile(
            "Jane", 
            "Smith", 
            LocalDate.of(1988, 8, 22),
            "Content moderator and technical writer with passion for clean APIs"
        );
        janeProfile.setPhoneNumber("+1-555-0102");
        janeProfile.setUser(janeSmith);
        userProfileRepository.save(janeProfile);

        UserProfile bobProfile = new UserProfile(
            "Bob", 
            "Wilson", 
            LocalDate.of(1992, 12, 3),
            "Junior developer learning Spring Boot and REST API best practices"
        );
        bobProfile.setPhoneNumber("+1-555-0103");
        bobProfile.setUser(bobWilson);
        userProfileRepository.save(bobProfile);

        // Create Posts (demonstrating @ManyToOne / @OneToMany relationship)
        
        // John's posts
        Post post1 = new Post(
            "Getting Started with HATEOAS", 
            "HATEOAS (Hypermedia as the Engine of Application State) is a key principle of REST that makes APIs self-discoverable. In this post, we'll explore how to implement HATEOAS in Spring Boot..."
        );
        post1.setAuthor(johnDoe);
        postRepository.save(post1);

        Post post2 = new Post(
            "Understanding JPA Relationships", 
            "JPA provides several types of relationships between entities: @OneToOne, @OneToMany, @ManyToOne, and @ManyToMany. Each has its own use cases and configuration options..."
        );
        post2.setAuthor(johnDoe);
        postRepository.save(post2);

        Post post3 = new Post(
            "Spring Boot Best Practices", 
            "When building Spring Boot applications, following best practices can save you time and prevent common pitfalls. Here are some key recommendations..."
        );
        post3.setAuthor(johnDoe);
        postRepository.save(post3);

        // Jane's posts
        Post post4 = new Post(
            "API Documentation with HATEOAS", 
            "Self-documenting APIs are crucial for developer experience. HATEOAS helps by providing links that guide clients through available actions..."
        );
        post4.setAuthor(janeSmith);
        postRepository.save(post4);

        Post post5 = new Post(
            "RESTful API Design Principles", 
            "Designing RESTful APIs requires understanding of HTTP methods, status codes, and resource modeling. Let's explore these concepts..."
        );
        post5.setAuthor(janeSmith);
        postRepository.save(post5);

        // Bob's posts
        Post post6 = new Post(
            "My Journey Learning Spring Boot", 
            "As a junior developer, learning Spring Boot has been both challenging and rewarding. Here's what I've learned so far..."
        );
        post6.setAuthor(bobWilson);
        postRepository.save(post6);

        System.out.println("✅ Sample data loaded successfully!");
        System.out.println("📊 Created:");
        System.out.println("   • 3 Users (johndoe, janesmith, bobwilson)");
        System.out.println("   • 3 User Profiles (@OneToOne with Users)");
        System.out.println("   • 3 Roles (ADMIN, USER, MODERATOR)");
        System.out.println("   • 6 Posts (@ManyToOne with Users)");
        System.out.println("   • User-Role assignments (@ManyToMany relationships)");
        System.out.println();
        System.out.println("🔗 Try these HATEOAS endpoints:");
        System.out.println("   GET /api/users/1 - John Doe with hypermedia links");
        System.out.println("   GET /api/posts/1 - Post with link to author");
        System.out.println("   GET /api/roles/1 - Role with links to users");
    }
}