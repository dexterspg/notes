package com.example.service;

import java.util.List;
import java.util.Optional;

import com.example.entities.Employee;

/**
 * EmployeeService
 */
public interface EmployeeService {

    Employee save(Employee employee);
    List<Employee> findAll();
    Optional<Employee> findById(String id);
    Employee deleteById(String id);

}
