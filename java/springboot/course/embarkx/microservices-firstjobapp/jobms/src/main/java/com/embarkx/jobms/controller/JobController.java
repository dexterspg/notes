package com.embarkx.jobms.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.embarkx.jobms.dto.JobDTO;
import com.embarkx.jobms.model.Job;
import com.embarkx.jobms.service.JobService;

@RestController
@RequestMapping("/jobs")
public class JobController {

    @Autowired
    private JobService jobService;

    // Get all jobs
    @GetMapping
    public ResponseEntity<List<JobDTO>> getAllJobs() {
        List<JobDTO> jobs = jobService.findAll();
        return new ResponseEntity<>(jobs, HttpStatus.OK);
    }

    // Get a specific job by ID
    @GetMapping("/{id}")
    public ResponseEntity<JobDTO> findJobById(@PathVariable long id) {
        Optional<JobDTO> job = jobService.findJobById(id);
        if (job.isPresent()) {
            return new ResponseEntity<>(job.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Add a new job
    @PostMapping
    public ResponseEntity<String> addJob(@RequestBody Job job) {
        jobService.createJob(job);
        return new ResponseEntity<>("Job added successfully!", HttpStatus.CREATED);
    }

    // Update an existing job
    @PutMapping("/{id}")
    public ResponseEntity<String> updateJob(@PathVariable long id, @RequestBody Job job) {
        try {
            jobService.updateJob(id, job);
            return new ResponseEntity<>("Job updated successfully!", HttpStatus.OK);
        } catch (IndexOutOfBoundsException e) {
            return new ResponseEntity<>("Job ID not found.", HttpStatus.NOT_FOUND);
        }
    }

    // Delete a job by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteJob(@PathVariable long id) {
        try {
            boolean isRemoved = jobService.deleteJob(id);
            if (isRemoved) {
                return new ResponseEntity<>("Job deleted successfully!", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Job ID not found.", HttpStatus.NOT_FOUND);
            }
        } catch (IndexOutOfBoundsException e) {
            return new ResponseEntity<>("Job ID not found.", HttpStatus.NOT_FOUND);
        }
    }
}

