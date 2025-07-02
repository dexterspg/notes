package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main application class for Docker Spring Boot Demo
 * 
 * @SpringBootApplication is a convenience annotation that combines:
 * - @Configuration: Indicates this class contains Spring configuration
 * - @EnableAutoConfiguration: Enables Spring Boot's auto-configuration mechanism
 * - @ComponentScan: Enables component scanning for this package and sub-packages
 * 
 * This annotation tells Spring Boot to:
 * 1. Look for configuration classes
 * 2. Automatically configure beans based on classpath dependencies
 * 3. Scan for Spring components (Controllers, Services, etc.)
 */
@SpringBootApplication
public class DockerSpringBootDemoApplication {

    /**
     * Main method - entry point of the Spring Boot application
     * 
     * When running in Docker:
     * - This method will be called when the container starts
     * - The embedded Tomcat server will start on port 8080 (default)
     * - The application will remain running until the container is stopped
     * 
     * @param args Command line arguments (can be used to override configuration)
     */
    public static void main(String[] args) {
        /*
         * SpringApplication.run() method:
         * 1. Creates an ApplicationContext
         * 2. Registers the main configuration class
         * 3. Starts the embedded web server (Tomcat)
         * 4. Performs auto-configuration based on dependencies
         * 5. Scans for and registers Spring components
         */
        SpringApplication.run(DockerSpringBootDemoApplication.class, args);
        
        // Log message to indicate successful startup (visible in Docker logs)
        System.out.println("🚀 Docker Spring Boot Demo Application Started Successfully!");
        System.out.println("📊 Health check available at: http://localhost:8080/actuator/health");
        System.out.println("🔍 Application info at: http://localhost:8080/actuator/info");
    }
}