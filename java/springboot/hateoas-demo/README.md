# Spring Boot HATEOAS Demo

A comprehensive demonstration of HATEOAS (Hypermedia as the Engine of Application State) concepts in Spring Boot with JPA relationships.

## 🎯 Learning Objectives

This project demonstrates:
- **HATEOAS concepts**: `linkTo()`, `methodOn()`, EntityModel, CollectionModel
- **JPA relationships**: `@OneToOne`, `@OneToMany`, `@ManyToMany`
- **REST API design** with hypermedia links
- **Self-discoverable APIs** that guide clients through available actions

## 🏗️ Project Structure

```
src/main/java/com/example/hateoas/
├── entity/
│   ├── User.java           # @OneToOne with UserProfile, @OneToMany with Post, @ManyToMany with Role
│   ├── UserProfile.java    # @OneToOne with User (owning side)
│   ├── Post.java           # @ManyToOne with User
│   └── Role.java           # @ManyToMany with User (non-owning side)
├── repository/
│   ├── UserRepository.java
│   ├── UserProfileRepository.java
│   ├── PostRepository.java
│   └── RoleRepository.java
├── controller/
│   ├── UserController.java        # Demonstrates linkTo(), methodOn(), EntityModel
│   ├── PostController.java        # Shows @ManyToOne relationship links
│   ├── RoleController.java        # Shows @ManyToMany relationship links
│   └── UserProfileController.java # Shows @OneToOne relationship links
├── config/
│   └── DataLoader.java     # Sample data for testing
└── HateoasDemoApplication.java
```

## 🔗 HATEOAS Key Concepts Demonstrated

### 1. linkTo() and methodOn()
```java
// Type-safe link creation
.add(linkTo(methodOn(UserController.class).getUserById(user.getId())).withSelfRel())
.add(linkTo(methodOn(PostController.class).getPostsByUserId(user.getId())).withRel("posts"))
```

### 2. EntityModel
```java
// Wrapping single entity with links
EntityModel<User> userModel = EntityModel.of(user)
    .add(linkTo(methodOn(UserController.class).getUserById(id)).withSelfRel())
    .add(linkTo(methodOn(PostController.class).getPostsByUserId(id)).withRel("posts"));
```

### 3. CollectionModel
```java
// Wrapping collection with links
CollectionModel<EntityModel<User>> userCollection = CollectionModel.of(userModels)
    .add(linkTo(methodOn(UserController.class).getAllUsers()).withSelfRel());
```

## 🗄️ Database Relationships

### @OneToOne: User ↔ UserProfile
- **User** has one **UserProfile**
- **UserProfile** belongs to one **User**
- UserProfile is the owning side (contains foreign key)

### @OneToMany: User → Posts
- One **User** can have many **Posts**
- **Post** belongs to one **User** (author)
- Post is the owning side (@ManyToOne)

### @ManyToMany: User ↔ Roles
- Many **Users** can have many **Roles**
- Many **Roles** can belong to many **Users**
- User is the owning side (defines join table)

## 🚀 Running the Application

1. **Build and run:**
   ```bash
   cd java/springboot/hateoas-demo
   mvn spring-boot:run
   ```

2. **Access H2 Console:**
   - URL: http://localhost:8080/h2-console
   - JDBC URL: `jdbc:h2:mem:testdb`
   - Username: `sa`
   - Password: (empty)

## 🌐 API Endpoints

### Users
- `GET /api/users` - Get all users with HATEOAS links
- `GET /api/users/{id}` - Get user by ID
- `GET /api/users/{id}/with-profile` - Get user with profile (@OneToOne)
- `GET /api/users/{id}/with-posts` - Get user with posts (@OneToMany)
- `GET /api/users/{id}/with-roles` - Get user with roles (@ManyToMany)
- `POST /api/users` - Create new user
- `PUT /api/users/{id}` - Update user
- `DELETE /api/users/{id}` - Delete user

### Posts
- `GET /api/posts` - Get all posts
- `GET /api/posts/{id}` - Get post by ID
- `GET /api/posts/user/{userId}` - Get posts by user (@OneToMany from User perspective)
- `GET /api/posts/search?title=keyword` - Search posts by title

### Roles
- `GET /api/roles` - Get all roles
- `GET /api/roles/{id}` - Get role by ID
- `GET /api/roles/{roleId}/users` - Get users with specific role (@ManyToMany)
- `GET /api/roles/name/{name}` - Get role by name

### User Profiles
- `GET /api/profiles/{id}` - Get profile by ID
- `GET /api/profiles/user/{userId}` - Get profile by user ID (@OneToOne)

## 📋 Sample Data

The application loads sample data including:
- **3 Users**: johndoe, janesmith, bobwilson
- **3 User Profiles**: One for each user (@OneToOne)
- **3 Roles**: ADMIN, USER, MODERATOR
- **6 Posts**: Multiple posts per user (@OneToMany)
- **User-Role assignments**: Demonstrating @ManyToMany

## 🧪 Testing HATEOAS

Try these example requests:

```bash
# Get user with hypermedia links
curl http://localhost:8080/api/users/1

# Response includes links like:
{
  "id": 1,
  "username": "johndoe",
  "email": "john.doe@example.com",
  "_links": {
    "self": {"href": "http://localhost:8080/api/users/1"},
    "posts": {"href": "http://localhost:8080/api/posts/user/1"},
    "profile": {"href": "http://localhost:8080/api/profiles/user/1"}
  }
}
```

## 🔍 Key Learning Points

1. **HATEOAS makes APIs self-discoverable** - clients follow links instead of constructing URLs
2. **linkTo() and methodOn()** provide type-safe, refactor-friendly link creation
3. **EntityModel** wraps single resources with hypermedia links
4. **CollectionModel** wraps collections with hypermedia links
5. **JPA relationships** are properly represented in the API structure
6. **Relationship navigation** through hypermedia links enhances API usability

## 📚 Technologies Used

- Spring Boot 3.2.0
- Spring Data JPA
- Spring HATEOAS
- H2 Database
- Maven
- Java 17

## 🎓 Next Steps

To extend your learning:
1. Add `@Relation` annotations to customize link relation names
2. Implement custom RepresentationModel assemblers
3. Add pagination support with HATEOAS
4. Explore Spring Data REST for automatic HATEOAS endpoints
5. Add API versioning with HATEOAS links