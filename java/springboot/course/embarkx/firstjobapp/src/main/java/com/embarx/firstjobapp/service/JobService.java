package com.embarx.firstjobapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.embarx.firstjobapp.model.Job;
import com.embarx.firstjobapp.repository.JobRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JobService {

    private final JobRepository jobRepository;

    public List<Job> getAllJobs(){
        return jobRepository.findAll();
    } 

    public Job createJob(Job job){
        return jobRepository.save(job);
    }

    public Optional<Job> findJobById(long id){
        return jobRepository.findById(id);
    }

    public Optional<Job>  updateJob(long id, Job updatedJob){
        return jobRepository.findById(id).map( existingJob ->{
            existingJob.setTitle(updatedJob.getTitle());
            existingJob.setDescription(updatedJob.getDescription());
            existingJob.setMinSalary(updatedJob.getMinSalary());
            existingJob.setMaxSalary(updatedJob.getMaxSalary());
            existingJob.setLocation(updatedJob.getLocation());
            return jobRepository.save(existingJob);
        }
        );
    }
    public boolean deleteJob(long id){
        return jobRepository.findById(id)
        .map(job ->{
            jobRepository.delete(job);
            return true;
        }).orElse(false);
    }
}
