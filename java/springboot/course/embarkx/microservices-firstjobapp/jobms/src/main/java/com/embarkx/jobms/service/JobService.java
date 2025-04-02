package com.embarkx.jobms.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.embarkx.jobms.clients.CompanyClient;
import com.embarkx.jobms.clients.ReviewClient;
import com.embarkx.jobms.dto.JobDTO;
import com.embarkx.jobms.external.Company;
import com.embarkx.jobms.external.Review;
import com.embarkx.jobms.mapper.JobMapper;
import com.embarkx.jobms.model.Job;
import com.embarkx.jobms.repository.JobRepository;

@Service
public class JobService {

    private final JobRepository jobRepository;
    private final CompanyClient companyClient;
    private final ReviewClient reviewClient;

    @Autowired
    public JobService(JobRepository jobRepository, CompanyClient companyClient, ReviewClient reviewClient) {
        this.jobRepository = jobRepository;
        this.companyClient = companyClient;
        this.reviewClient = reviewClient;
    }

    public List<JobDTO> findAll() {
        List<Job> jobs = jobRepository.findAll();

        return jobs.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private JobDTO convertToDTO(Job job) {

        Company company = companyClient.getCompany(job.getCompanyId());

        List<Review> reviews = reviewClient.getAllReviews(job.getCompanyId());
        
        return JobMapper.mapToJobDTO(job, company ,reviews);
    }

    public Job createJob(Job job) {
        return jobRepository.save(job);
    }

    public Optional<JobDTO> findJobById(long id) {
        return jobRepository.findById(id)
                .map(this::convertToDTO);
    }

    public Optional<Job> updateJob(long id, Job updatedJob) {
        return jobRepository.findById(id).map(existingJob -> {
            existingJob.setTitle(updatedJob.getTitle());
            existingJob.setDescription(updatedJob.getDescription());
            existingJob.setMinSalary(updatedJob.getMinSalary());
            existingJob.setMaxSalary(updatedJob.getMaxSalary());
            existingJob.setLocation(updatedJob.getLocation());
            return jobRepository.save(existingJob);
        });
    }

    public boolean deleteJob(long id) {
        return jobRepository.findById(id)
                .map(job -> {
                    jobRepository.delete(job);
                    return true;
                }).orElse(false);
    }
}
