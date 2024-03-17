package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entities.DemoUser;
import com.example.repository.DemoUserRepo;

/**
 * DemoController
 */
@RestController
@RequestMapping("/api")
public class DemoController {

    @Autowired
    private DemoUserRepo repo;

    @GetMapping
    public String getString() {
        DemoUser user = new DemoUser();
        user.setName("Control");
        repo.save(user);
        return "Hello, " + user.getName();
    }
}
