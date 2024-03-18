package com.example.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entities.Employee;
import com.example.exceptionhandling.EmployeeNotFoundException;
import com.example.service.EmployeeServiceImpl;

/**
 * DemoController
 */
@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private EmployeeServiceImpl employeeService;

    @Autowired
    public EmployeeController(EmployeeServiceImpl employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public List<Employee> findAllEmployees() {
        List<Employee> employees = employeeService.findAll();
        return employees;
    }

    @GetMapping("/{id}")
    public Employee findById(@PathVariable String id) {
        Optional<Employee> employeeOpt = employeeService.findById(id);
        if(!employeeOpt.isPresent()){
            throw new EmployeeNotFoundException("Employee Id is not found -- " + id); 
        }
        return employeeOpt.get();
    }

    @PostMapping
    public Employee addEmployee(@RequestBody Employee employee) {
        Employee addedEmployee = employeeService.save(employee);
        return addedEmployee;

    }

    @PutMapping
    public Employee updateEmployee(@RequestBody Employee employee){
        Employee addedEmployee = employeeService.save(employee);
        return addedEmployee;
    }

    @DeleteMapping("/{id}")
    public Employee deleteEmployeeById(@PathVariable String id) {
        Employee deletedEmployee = employeeService.deleteById(id);
        return deletedEmployee;
    }

}
