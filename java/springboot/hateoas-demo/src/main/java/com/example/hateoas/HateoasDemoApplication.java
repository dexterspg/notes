package com.example.hateoas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main application class for HATEOAS Demo
 * 
 * This Spring Boot application demonstrates:
 * - HATEOAS (Hypermedia as the Engine of Application State)
 * - JPA relationships (@OneToOne, @OneToMany, @ManyToMany)
 * - REST API design with hypermedia links
 * - linkTo() and methodOn() for creating links
 * - EntityModel and CollectionModel for wrapping responses
 * 
 * Key Learning Points:
 * 1. HATEOAS makes REST APIs more discoverable and self-documenting
 * 2. Clients can navigate the API by following links rather than constructing URLs
 * 3. linkTo() and methodOn() provide type-safe link creation
 * 4. EntityModel wraps single resources with links
 * 5. CollectionModel wraps collections with links
 * 6. @Relation annotation can be used to customize link relation names
 * 
 * Database Configuration:
 * - Uses H2 in-memory database for easy testing
 * - H2 console available at: http://localhost:8080/h2-console
 * - JDBC URL: jdbc:h2:mem:testdb
 * - Username: sa, Password: (empty)
 * 
 * API Endpoints:
 * - GET /api/users - Get all users
 * - GET /api/users/{id} - Get user by ID
 * - GET /api/posts - Get all posts
 * - GET /api/posts/{id} - Get post by ID
 * - GET /api/roles - Get all roles
 * - GET /api/roles/{id} - Get role by ID
 * - GET /api/profiles/{id} - Get profile by ID
 * 
 * Each endpoint returns hypermedia links to related resources
 */
@SpringBootApplication
public class HateoasDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(HateoasDemoApplication.class, args);
        
        System.out.println("\n" + "=".repeat(70));
        System.out.println("🚀 HATEOAS Demo Application Started Successfully!");
        System.out.println("=".repeat(70));
        System.out.println("📊 H2 Database Console: http://localhost:8080/h2-console");
        System.out.println("   JDBC URL: jdbc:h2:mem:testdb");
        System.out.println("   Username: sa");
        System.out.println("   Password: (empty)");
        System.out.println();
        System.out.println("🌐 API Endpoints:");
        System.out.println("   GET  /api/users           - Get all users");
        System.out.println("   GET  /api/users/{id}      - Get user by ID");
        System.out.println("   GET  /api/posts           - Get all posts");
        System.out.println("   GET  /api/posts/{id}      - Get post by ID");
        System.out.println("   GET  /api/roles           - Get all roles");
        System.out.println("   GET  /api/roles/{id}      - Get role by ID");
        System.out.println("   GET  /api/profiles/{id}   - Get profile by ID");
        System.out.println();
        System.out.println("🔗 HATEOAS Features Demonstrated:");
        System.out.println("   • linkTo() and methodOn() for type-safe link creation");
        System.out.println("   • EntityModel for wrapping single resources");
        System.out.println("   • CollectionModel for wrapping collections");
        System.out.println("   • Self-discoverable API with hypermedia links");
        System.out.println("   • JPA relationships (@OneToOne, @OneToMany, @ManyToMany)");
        System.out.println("=".repeat(70));
    }
}