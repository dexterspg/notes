package com.embarx.firstjobapp.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.embarx.firstjobapp.model.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
}
