package com.embarx.firstjobapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.embarx.firstjobapp.model.Job;

@Repository
public interface JobRepository extends JpaRepository<Job, Long>{
   
}
