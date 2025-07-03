package com.example.hateoas.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.springframework.hateoas.RepresentationModel;

import java.util.HashSet;
import java.util.Set;

/**
 * Role entity demonstrating @ManyToMany relationship with User
 * Multiple users can have multiple roles
 */
@Entity
@Table(name = "roles")
public class Role extends RepresentationModel<Role> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(length = 500)
    private String description;

    /**
     * @ManyToMany relationship with User
     * mappedBy = "roles": This is the non-owning side of the relationship
     * The User entity owns the relationship and defines the join table
     */
    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    @JsonIgnore // Prevents infinite recursion in JSON serialization
    private Set<User> users = new HashSet<>();

    // Constructors
    public Role() {}

    public Role(String name, String description) {
        this.name = name;
        this.description = description;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    // Utility methods
    public void addUser(User user) {
        users.add(user);
        user.getRoles().add(this);
    }

    public void removeUser(User user) {
        users.remove(user);
        user.getRoles().remove(this);
    }
}