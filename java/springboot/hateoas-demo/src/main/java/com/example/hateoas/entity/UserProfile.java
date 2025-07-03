package com.example.hateoas.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDate;

/**
 * UserProfile entity demonstrating @OneToOne relationship with User
 * This is the owning side of the relationship (contains the foreign key)
 */
@Entity
@Table(name = "user_profiles")
public class UserProfile extends RepresentationModel<UserProfile> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(length = 500)
    private String bio;

    @Column(name = "phone_number")
    private String phoneNumber;

    /**
     * @OneToOne relationship with User
     * @JoinColumn specifies the foreign key column name
     * This is the owning side of the relationship
     */
    @OneToOne
    @JoinColumn(name = "user_id", unique = true)
    @JsonIgnore // Prevents infinite recursion in JSON serialization
    private User user;

    // Constructors
    public UserProfile() {}

    public UserProfile(String firstName, String lastName, LocalDate birthDate, String bio) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.bio = bio;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    // Utility method to get full name
    public String getFullName() {
        return firstName + " " + lastName;
    }
}