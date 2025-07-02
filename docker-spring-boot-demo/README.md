# Docker Spring Boot Demo 🐳

A comprehensive example of containerizing a Spring Boot application using Docker, designed for beginners learning Docker concepts.

## 📋 Table of Contents

- [Project Overview](#project-overview)
- [Prerequisites](#prerequisites)
- [Project Structure](#project-structure)
- [Quick Start](#quick-start)
- [Docker Commands Explained](#docker-commands-explained)
- [Testing the Application](#testing-the-application)
- [Docker Concepts Demonstrated](#docker-concepts-demonstrated)
- [Troubleshooting](#troubleshooting)
- [Learning Resources](#learning-resources)

## 🎯 Project Overview

This project demonstrates:
- ✅ **Multi-stage Docker builds** for optimized images
- ✅ **Spring Boot REST API** with multiple endpoints
- ✅ **Docker Compose** for simplified container management
- ✅ **Health checks** and monitoring
- ✅ **Security best practices** (non-root user)
- ✅ **Comprehensive documentation** and comments

### Features
- Simple REST API with greeting endpoints
- Application status and Docker information endpoints
- Health checks via Spring Boot Actuator
- Detailed logging for container debugging
- Production-ready configurations

## 🛠 Prerequisites

Before you begin, ensure you have the following installed:

```bash
# Check if Docker is installed
docker --version

# Check if Docker Compose is installed
docker-compose --version
```

**Required:**
- Docker Desktop (Windows/Mac) or Docker Engine (Linux)
- Docker Compose (usually included with Docker Desktop)

**Optional but recommended:**
- Java 17+ (for local development)
- Maven 3.6+ (for local development)

## 📁 Project Structure

```
docker-spring-boot-demo/
├── src/
│   └── main/
│       ├── java/com/example/demo/
│       │   ├── DockerSpringBootDemoApplication.java  # Main application class
│       │   └── HelloController.java                  # REST controller
│       └── resources/
│           └── application.properties                # App configuration
├── Dockerfile                                        # Docker image definition
├── docker-compose.yml                               # Multi-container setup
├── .dockerignore                                     # Files to exclude from build
├── pom.xml                                          # Maven dependencies
├── logs/                                            # Log files directory
└── README.md                                        # This file
```

## 🚀 Quick Start

### Option 1: Using Docker Compose (Recommended for beginners)

```bash
# 1. Navigate to the project directory
cd docker-spring-boot-demo

# 2. Build and start the application
docker-compose up --build

# 3. Access the application
# Open your browser and go to: http://localhost:8080/api/hello
```

### Option 2: Using Docker Commands

```bash
# 1. Build the Docker image
docker build -t spring-boot-docker-demo .

# 2. Run the container
docker run -p 8080:8080 spring-boot-docker-demo

# 3. Access the application
# Open your browser and go to: http://localhost:8080/api/hello
```

## 🐳 Docker Commands Explained

### Building the Image

```bash
# Basic build command
docker build -t spring-boot-docker-demo .

# Build with custom tag
docker build -t spring-boot-docker-demo:v1.0 .

# Build without using cache (for debugging)
docker build --no-cache -t spring-boot-docker-demo .
```

**What happens during build:**
1. Downloads base images (Maven + OpenJDK)
2. Copies project files into the container
3. Downloads Maven dependencies
4. Compiles and packages the Java application
5. Creates final runtime image with only the JAR file

### Running Containers

```bash
# Run in foreground (you'll see logs)
docker run -p 8080:8080 spring-boot-docker-demo

# Run in background (detached mode)
docker run -d -p 8080:8080 --name my-spring-app spring-boot-docker-demo

# Run with environment variables
docker run -p 8080:8080 -e "app.name=My Custom App" spring-boot-docker-demo

# Run with volume mounting (for logs)
docker run -p 8080:8080 -v $(pwd)/logs:/app/logs spring-boot-docker-demo
```

### Container Management

```bash
# List running containers
docker ps

# List all containers (including stopped)
docker ps -a

# Stop a container
docker stop my-spring-app

# Start a stopped container
docker start my-spring-app

# Remove a container
docker rm my-spring-app

# View container logs
docker logs my-spring-app

# Follow logs in real-time
docker logs -f my-spring-app

# Execute commands inside running container
docker exec -it my-spring-app sh
```

### Image Management

```bash
# List Docker images
docker images

# Remove an image
docker rmi spring-boot-docker-demo

# Remove unused images
docker image prune

# View image details
docker inspect spring-boot-docker-demo
```

## 🧪 Testing the Application

Once the application is running, you can test these endpoints:

### 1. Basic Hello Endpoint
```bash
curl http://localhost:8080/api/hello
```
**Expected response:**
```json
{
  "message": "Hello from Spring Boot running in Docker! 🐳",
  "timestamp": "2024-01-15T10:30:00",
  "application": "Docker Spring Boot Demo",
  "version": "1.0.0"
}
```

### 2. Personalized Greeting
```bash
curl http://localhost:8080/api/hello/Docker
```

### 3. Application Status
```bash
curl http://localhost:8080/api/status
```

### 4. Docker Information
```bash
curl http://localhost:8080/api/docker-info
```

### 5. Health Check (Spring Boot Actuator)
```bash
curl http://localhost:8080/actuator/health
```

### 6. Application Information
```bash
curl http://localhost:8080/actuator/info
```

## 📚 Docker Concepts Demonstrated

### 1. **Multi-stage Builds**
- **Build stage**: Uses Maven image to compile and package the application
- **Runtime stage**: Uses smaller JRE image for running the application
- **Benefit**: Significantly smaller final image size

### 2. **Layer Caching**
- Dependencies are downloaded in a separate layer
- Source code is copied in a different layer
- **Benefit**: Faster rebuilds when only code changes

### 3. **Security Best Practices**
- Uses non-root user (`appuser`)
- Specific base image versions (not `latest`)
- Minimal runtime image (Alpine Linux)

### 4. **Health Checks**
- Docker-native health checks
- Spring Boot Actuator endpoints
- **Benefit**: Container orchestrators can detect unhealthy containers

### 5. **Environment Configuration**
- External configuration via environment variables
- Docker Compose environment files
- **Benefit**: Same image works in different environments

### 6. **Networking**
- Port exposure and mapping
- Container-to-container communication
- **Benefit**: Flexible deployment options

## 🔧 Troubleshooting

### Common Issues and Solutions

#### 1. Port Already in Use
```bash
# Error: Port 8080 is already in use
# Solution: Use a different port
docker run -p 8081:8080 spring-boot-docker-demo
```

#### 2. Container Won't Start
```bash
# Check container logs
docker logs <container-name>

# Run container interactively for debugging
docker run -it spring-boot-docker-demo sh
```

#### 3. Build Fails
```bash
# Clean build without cache
docker build --no-cache -t spring-boot-docker-demo .

# Check Docker build context
docker build --progress=plain -t spring-boot-docker-demo .
```

#### 4. Application Not Responding
```bash
# Check if container is running
docker ps

# Check health status
docker inspect <container-name> | grep Health

# Access container shell
docker exec -it <container-name> sh
```

### Useful Debugging Commands

```bash
# View detailed container information
docker inspect <container-name>

# Monitor container resource usage
docker stats <container-name>

# Check container processes
docker exec <container-name> ps aux

# View container filesystem
docker exec <container-name> ls -la /app
```

## 📖 Learning Resources

### Docker Documentation
- [Docker Official Tutorial](https://docs.docker.com/get-started/)
- [Dockerfile Best Practices](https://docs.docker.com/develop/dev-best-practices/)
- [Docker Compose Documentation](https://docs.docker.com/compose/)

### Spring Boot with Docker
- [Spring Boot Docker Guide](https://spring.io/guides/gs/spring-boot-docker/)
- [Spring Boot Actuator](https://docs.spring.io/spring-boot/docs/current/reference/html/actuator.html)

### Next Steps
1. **Add a Database**: Extend the docker-compose.yml to include PostgreSQL
2. **Multi-container Apps**: Create separate services for frontend/backend
3. **Production Deployment**: Learn about Docker Swarm or Kubernetes
4. **CI/CD Integration**: Automate building and pushing images
5. **Monitoring**: Add logging and monitoring solutions

## 🏷 Docker Commands Cheat Sheet

| Command | Description |
|---------|-------------|
| `docker build -t name .` | Build image from Dockerfile |
| `docker run -p 8080:8080 name` | Run container with port mapping |
| `docker ps` | List running containers |
| `docker stop <id>` | Stop container |
| `docker logs <id>` | View container logs |
| `docker exec -it <id> sh` | Access container shell |
| `docker-compose up` | Start services |
| `docker-compose down` | Stop services |
| `docker-compose logs -f` | Follow logs |

---

## 🎉 Congratulations!

You've successfully created and containerized a Spring Boot application! This example covers the fundamental concepts you need to understand Docker and how it works with Java applications.

**What you've learned:**
- How to write a Dockerfile for Java applications
- Multi-stage builds for optimization
- Docker Compose for multi-container applications
- Container networking and port mapping
- Health checks and monitoring
- Security best practices

**Ready for more?** Try modifying the application, adding new endpoints, or integrating with a database using Docker Compose!