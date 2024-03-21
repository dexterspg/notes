package com.example.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Course
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

    @OneToMany(cascade =  CascadeType.ALL)
    @JoinColumn(name ="fk_courseId")
    private List<Review> reviews;


    @ManyToMany(fetch = FetchType.LAZY,
        cascade =  {CascadeType.DETACH, CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE})
    @JoinTable(
        name = "course_student",
        joinColumns = @JoinColumn(name="fk_courseId"),
        inverseJoinColumns = @JoinColumn(name="fk_studentId"))
    private List<Student> students;

    public Course(String title) {
        this.title = title;
    }

    public void addReview(Review review){
        if(reviews == null){
            reviews = new ArrayList<>();
        }
        reviews.add(review);
    }

    public void addStudent(Student student){
        if(students == null){
            students = new ArrayList<>();
        }
        students.add(student);
    }

    @Override
    public String toString() {
        return "Course [id=" + id + ", title=" + title + "]";
    }

    

    


}