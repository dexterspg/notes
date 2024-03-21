package com.example.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Review
 */
@Data
@NoArgsConstructor
@Entity
public class Review {

    @Id
    @GeneratedValue
    private int id;

    @Column
    private String comment;

    public Review(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "Review [id=" + id + ", comment=" + comment + "]";
    }

}
