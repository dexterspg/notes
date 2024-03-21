package com.example.repository;

import com.example.entities.Instructor;
import com.example.entities.InstructorDetail;

public interface AppDAO {

    void save(Instructor instructor);
    Instructor findInstructorById(int id);
    void deleteInstructorById(int id);
    void deleteInstructorDetailById(int theId);
    InstructorDetail findInstructorDetailById(int theId);   
}
