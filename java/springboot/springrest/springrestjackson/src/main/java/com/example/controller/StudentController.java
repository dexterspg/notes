package com.example.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entities.Student;
import com.example.exception.StudentNotFoundException;

import jakarta.annotation.PostConstruct;

/**
 * DemoController
 */
@RestController
@RequestMapping("/api/students")
public class StudentController {

    private List<Student> students; 

    @PostConstruct
    public void initStudents(){
        students = new ArrayList<>();
        students.add(new Student("Bob", "Andrew"));
        students.add(new Student("Jane", "Doe"));
        students.add(new Student("Gideon", "Knight"));
    }

    @GetMapping
    public List<Student> getStudents() {
        return students;
    }

    @GetMapping("/{id}")
    public Student getStudent(@PathVariable("id") int id){
        
        if(students.size() < id){
            throw new StudentNotFoundException("Student id not found --- " + id);
        }
        return students.get(id);
    }

    // @ExceptionHandler
    // public ResponseEntity<StudentErrorResponse> handleException(StudentNotFoundException exc){
    //     StudentErrorResponse error =  new StudentErrorResponse(HttpStatus.NOT_FOUND.value(), exc.getMessage(), System.currentTimeMillis() );
    //     return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    // }
}
