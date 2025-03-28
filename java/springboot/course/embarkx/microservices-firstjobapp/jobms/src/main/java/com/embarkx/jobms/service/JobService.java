package com.embarkx.jobms.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.embarkx.jobms.dto.JobDTO;
import com.embarkx.jobms.external.Company;
import com.embarkx.jobms.external.Review;
import com.embarkx.jobms.mapper.JobMapper;
import com.embarkx.jobms.model.Job;
import com.embarkx.jobms.repository.JobRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JobService {

    private final JobRepository jobRepository;

    public List<JobDTO> findAll() {
        List<Job> jobs = jobRepository.findAll();

        return jobs.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private JobDTO convertToDTO(Job job) {
        RestTemplate restTemplate = new RestTemplate();
        Company company = restTemplate.getForObject("http://localhost:8081/companies/" + job.getCompanyId(),
                Company.class);
        ResponseEntity<List<Review>> reviewResponse = restTemplate.exchange("http://localhost:8083/reviews?companyId=" + job.getCompanyId(), 
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<List<Review>>(){});
        List<Review> reviews = reviewResponse.getBody();
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
