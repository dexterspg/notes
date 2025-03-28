package com.embarkx.jobms.dto;

import java.util.List;

import com.embarkx.jobms.external.Company;
import com.embarkx.jobms.external.Review;

import lombok.Data;

@Data
public class JobDTO {
    private Long id;
    private String title;
    private String description;
    private String minSalary;
    private String maxSalary;
    private String location;

    private Company company;
    private List<Review> reviews;
}
