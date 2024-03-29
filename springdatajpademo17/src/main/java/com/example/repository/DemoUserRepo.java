package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entities.DemoUser;

/**
 * DemoUserRepo
 */
@Repository
public interface DemoUserRepo extends JpaRepository<DemoUser, Long> {

    
}
