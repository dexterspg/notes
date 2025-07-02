# Dockerfile for Spring Boot Application
# 
# A Dockerfile is a text file that contains instructions to build a Docker image.
# Each instruction creates a new layer in the image.
# 
# Best practices demonstrated in this Dockerfile:
# 1. Multi-stage build to reduce final image size
# 2. Use official base images
# 3. Minimize layers
# 4. Use specific versions (not 'latest')
# 5. Create non-root user for security
# 6. Proper handling of application files

# =============================================================================
# STAGE 1: BUILD STAGE
# =============================================================================
# Use official Maven image with OpenJDK 17 for building the application
# This stage is called "build" and will be discarded in the final image
FROM maven:3.9.6-eclipse-temurin-17 AS build

# Set working directory inside the container
# All subsequent commands will be executed from this directory
WORKDIR /app

# Copy Maven configuration files first
# This is done separately to leverage Docker layer caching
# If pom.xml doesn't change, Maven dependencies won't be re-downloaded
COPY pom.xml .

# Download dependencies
# This layer will be cached unless pom.xml changes
RUN mvn dependency:go-offline -B

# Copy source code
# This is done after dependency download to optimize cache usage
COPY src ./src

# Build the application
# -B: Batch mode (non-interactive)
# -DskipTests: Skip running tests during build (you can remove this if you want to run tests)
RUN mvn clean package -B -DskipTests

# =============================================================================
# STAGE 2: RUNTIME STAGE
# =============================================================================
# Use a smaller base image for the runtime stage
# eclipse-temurin is the official OpenJDK distribution
FROM eclipse-temurin:17-jre-alpine

# Install additional packages if needed (example: curl for health checks)
# Alpine uses apk package manager
RUN apk add --no-cache curl

# Create a non-root user for security best practices
# Running applications as root in containers is a security risk
RUN addgroup -g 1001 -S appgroup && \
    adduser -u 1001 -S appuser -G appgroup

# Set working directory
WORKDIR /app

# Copy the built JAR file from the build stage
# The JAR file name matches what we defined in pom.xml <finalName>
COPY --from=build /app/target/docker-spring-boot-demo-1.0.0.jar app.jar

# Change ownership of the application files to the non-root user
RUN chown -R appuser:appgroup /app

# Switch to non-root user
USER appuser

# =============================================================================
# CONTAINER CONFIGURATION
# =============================================================================

# Expose port 8080
# This doesn't actually publish the port, it's just documentation
# The actual port mapping is done when running the container
EXPOSE 8080

# Add health check
# Docker will periodically run this command to check if the container is healthy
# --interval: How often to run the check
# --timeout: How long to wait for the check to complete
# --start-period: Grace period before starting health checks
# --retries: Number of consecutive failures before marking as unhealthy
HEALTHCHECK --interval=30s --timeout=10s --start-period=60s --retries=3 \
    CMD curl -f http://localhost:8080/actuator/health || exit 1

# Set JVM options for containerized environments
# These options are optimized for running Java applications in containers
ENV JAVA_OPTS="-Xms128m -Xmx512m -XX:+UseContainerSupport -XX:MaxRAMPercentage=75.0"

# Define the command to run the application
# ENTRYPOINT vs CMD:
# - ENTRYPOINT: Cannot be overridden easily, defines the main command
# - CMD: Can be overridden, provides default arguments
# 
# Using ENTRYPOINT ensures our application always starts, even if the user
# provides additional arguments when running the container
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]

# Alternative simple command (if you don't need the flexibility above):
# CMD ["java", "-jar", "app.jar"]

# =============================================================================
# DOCKER BUILD CONTEXT OPTIMIZATION
# =============================================================================
# Create a .dockerignore file to exclude unnecessary files from build context:
# 
# .dockerignore contents should include:
# target/
# .git/
# .gitignore
# *.md
# .mvn/
# mvnw
# mvnw.cmd
# Dockerfile
# docker-compose.yml

# =============================================================================
# USAGE EXAMPLES
# =============================================================================
# 
# Build the image:
# docker build -t spring-boot-docker-demo .
# 
# Run the container:
# docker run -p 8080:8080 spring-boot-docker-demo
# 
# Run with environment variables:
# docker run -p 8080:8080 -e "app.name=My Custom App" spring-boot-docker-demo
# 
# Run in background (detached mode):
# docker run -d -p 8080:8080 --name my-spring-app spring-boot-docker-demo
# 
# View logs:
# docker logs my-spring-app
# 
# Access container shell:
# docker exec -it my-spring-app sh