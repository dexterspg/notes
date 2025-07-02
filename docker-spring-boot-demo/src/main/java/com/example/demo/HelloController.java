package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

/**
 * REST Controller demonstrating various endpoints for Docker learning
 * 
 * @RestController combines @Controller and @ResponseBody
 * - Indicates this class handles HTTP requests
 * - Automatically serializes return values to JSON/XML
 * 
 * @RequestMapping sets the base path for all endpoints in this controller
 */
@RestController
@RequestMapping("/api")
public class HelloController {

    /*
     * @Value annotation injects application properties
     * This demonstrates how to use configuration in containerized apps
     * Default value "Docker Spring Boot Demo" is used if property is not set
     */
    @Value("${app.name:Docker Spring Boot Demo}")
    private String appName;

    @Value("${app.version:1.0.0}")
    private String appVersion;

    /**
     * Simple hello endpoint
     * 
     * URL: GET /api/hello
     * 
     * This endpoint is useful for:
     * - Docker health checks
     * - Testing if the container is responding
     * - Load balancer health probes
     */
    @GetMapping("/hello")
    public Map<String, Object> hello() {
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Hello from Spring Boot running in Docker! 🐳");
        response.put("timestamp", LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        response.put("application", appName);
        response.put("version", appVersion);
        
        // This will be visible in Docker logs when the endpoint is called
        System.out.println("📞 /api/hello endpoint called at " + LocalDateTime.now());
        
        return response;
    }

    /**
     * Personalized greeting with path variable
     * 
     * URL: GET /api/hello/{name}
     * Example: GET /api/hello/Docker
     * 
     * Demonstrates:
     * - Path variables in REST APIs
     * - Dynamic response generation
     * - Logging for debugging in containers
     */
    @GetMapping("/hello/{name}")
    public Map<String, Object> helloName(@PathVariable String name) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", String.format("Hello %s! Welcome to Spring Boot in Docker! 🚀", name));
        response.put("timestamp", LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        response.put("personalizedFor", name);
        response.put("containerInfo", "Running inside Docker container");
        
        // Log the personalized request
        System.out.println(String.format("📞 /api/hello/%s endpoint called", name));
        
        return response;
    }

    /**
     * Application status endpoint
     * 
     * URL: GET /api/status
     * 
     * Useful for:
     * - Container health monitoring
     * - Application diagnostics
     * - Docker Compose health checks
     */
    @GetMapping("/status")
    public Map<String, Object> status() {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "UP");
        response.put("application", appName);
        response.put("version", appVersion);
        response.put("timestamp", LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        
        // Include some system information (useful for debugging containers)
        response.put("javaVersion", System.getProperty("java.version"));
        response.put("os", System.getProperty("os.name"));
        response.put("availableProcessors", Runtime.getRuntime().availableProcessors());
        
        // Memory information (useful for container resource monitoring)
        Runtime runtime = Runtime.getRuntime();
        long totalMemory = runtime.totalMemory();
        long freeMemory = runtime.freeMemory();
        long usedMemory = totalMemory - freeMemory;
        
        Map<String, Object> memory = new HashMap<>();
        memory.put("total", totalMemory / 1024 / 1024 + " MB");
        memory.put("used", usedMemory / 1024 / 1024 + " MB");
        memory.put("free", freeMemory / 1024 / 1024 + " MB");
        response.put("memory", memory);
        
        System.out.println("📊 /api/status endpoint called - Application is healthy");
        
        return response;
    }

    /**
     * Docker-specific information endpoint
     * 
     * URL: GET /api/docker-info
     * 
     * Demonstrates how to detect if running in Docker and provide container-specific info
     */
    @GetMapping("/docker-info")
    public Map<String, Object> dockerInfo() {
        Map<String, Object> response = new HashMap<>();
        
        // Check if running in Docker (simple detection method)
        boolean isInDocker = System.getenv("DOCKER_CONTAINER") != null || 
                           System.getProperty("java.class.path").contains("app.jar");
        
        response.put("runningInDocker", isInDocker);
        response.put("containerTips", Map.of(
            "logs", "Use 'docker logs <container-name>' to view application logs",
            "shell", "Use 'docker exec -it <container-name> /bin/bash' to access container shell",
            "stop", "Use 'docker stop <container-name>' to gracefully stop the container"
        ));
        
        // Environment variables that might be set in Docker
        Map<String, String> dockerEnv = new HashMap<>();
        if (System.getenv("HOSTNAME") != null) {
            dockerEnv.put("hostname", System.getenv("HOSTNAME"));
        }
        if (System.getenv("PATH") != null) {
            dockerEnv.put("path", System.getenv("PATH"));
        }
        response.put("environment", dockerEnv);
        
        response.put("timestamp", LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        
        System.out.println("🐳 /api/docker-info endpoint called");
        
        return response;
    }
}