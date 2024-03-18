package com.example.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.example.entities.Employee;
import com.example.service.EmployeeServiceImpl;

@SpringBootApplication
@ComponentScan(basePackages = "com.example")
@EnableJpaRepositories("com.example.repository")
@EntityScan("com.example.entities")
public class DemoApplication implements CommandLineRunner {

    @Autowired
    EmployeeServiceImpl employeeService;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        employeeService.save(new Employee("Emma", "Baumgarten", "emma@luv2code.com"));
        employeeService.save(new Employee("Leslie", "Andrews", "leslie@luv2code.com"));
        employeeService.save(new Employee("Avani", "Gupta", "avani@luv2code.com"));
        employeeService.save(new Employee("Yuri", "Petrov", "yuri@luv2code.com"));

        employeeService.save(new Employee("Juan", "Vega", "juan@luv2code.com"));

        int size = employeeService.findAll().size();
        System.out.println(size);
    }

}
