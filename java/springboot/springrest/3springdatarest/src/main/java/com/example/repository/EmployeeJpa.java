package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.example.entities.Employee;

/**
 * EmployeeJpa
 */
@RepositoryRestResource(path="employees")
public interface EmployeeJpa extends JpaRepository<Employee, Integer> {

}
