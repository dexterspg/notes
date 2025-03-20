package com.embarkx.jobms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.embarkx.jobms.model.Job;

@Repository
public interface JobRepository extends JpaRepository<Job, Long>{
   
}
