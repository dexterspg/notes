package com.embarkx.companyms.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.embarkx.companyms.model.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
}
