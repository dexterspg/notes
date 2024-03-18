package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entities.Employee;

/**
 * EmployeeJpa
 */
public interface EmployeeJpa extends JpaRepository<Employee, String> {

}
