package com.embarkx.jobms.mapper;

import java.util.List;

import com.embarkx.jobms.dto.JobDTO;
import com.embarkx.jobms.external.Company;
import com.embarkx.jobms.external.Review;
import com.embarkx.jobms.model.Job;

public class JobMapper {

    public static JobDTO mapToJobDTO(Job job, Company company, List<Review> reviews){
        JobDTO jobDTO = new JobDTO();
        jobDTO.setId(job.getId());
        jobDTO.setTitle(job.getTitle());
        jobDTO.setDescription(job.getDescription());
        jobDTO.setMinSalary(job.getMinSalary());
        jobDTO.setMaxSalary(job.getMaxSalary());
        jobDTO.setLocation(job.getLocation());
        jobDTO.setCompany(company);
        jobDTO.setReviews(reviews);

        return jobDTO;

    }

    
}
