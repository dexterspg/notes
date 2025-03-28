package com.embarkx.jobms.external;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Review {

    private Long id;
    private String title;
    private String description;
    private double rating;

    // private Long companyId;


}
