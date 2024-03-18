package com.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.entities.Employee;
import com.example.repository.EmployeeJpa;

/**
 * EmployeeService
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeJpa employeeRepo;

    public EmployeeServiceImpl(EmployeeJpa employeeRepo) {
        this.employeeRepo = employeeRepo;
    }

    @Override
    public List<Employee> findAll(){
        return employeeRepo.findAll();
    }

    @Override
    public Optional<Employee> findById(String id){
        Optional<Employee> optEmp = employeeRepo.findById(id);
        return optEmp;
    }

    @Override
    public Employee save(Employee employee){
        return employeeRepo.save(employee);
    }

    @Override
    public Employee deleteById(String id) {
        Optional<Employee> deleteEmployee = findById(id); 
        if (deleteEmployee.isPresent()) {
            employeeRepo.delete(deleteEmployee.get());
            return deleteEmployee.get();
        }

        return null;
    }

}
