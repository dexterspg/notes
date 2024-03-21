package com.example.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * InstructorDetail
 */
@Data
@NoArgsConstructor
@Entity
public class Course {

    @Id
    @GeneratedValue
    private int id;

    @Column
    private String title;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE,
                          CascadeType.DETACH, CascadeType.REFRESH}) // default FetchType is Eager
    @JoinColumn(name ="fk_instructorId")
    private Instructor instructor;

    public Course(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Course [title=" + title + ", instructor=" + instructor.getId() + "]";
    }


}
