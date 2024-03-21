package com.example.repository;

import com.example.entities.Instructor;

public interface AppDAO {

    void save(Instructor instructor);
    Instructor findInstructorById(int id);
    void deleteInstructorById(int id);   
}