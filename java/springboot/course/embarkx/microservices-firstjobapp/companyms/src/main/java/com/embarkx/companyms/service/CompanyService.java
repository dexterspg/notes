package com.embarkx.companyms.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.embarkx.companyms.model.Company;
import com.embarkx.companyms.repository.CompanyRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CompanyService {
    private final CompanyRepository companyRepository;

    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    public Optional<Company> getCompanyById(Long id) {
        return companyRepository.findById(id);
    }

    public Company createCompany(Company company) {
        return companyRepository.save(company);
    }

    public Optional<Company> updateCompany(Long id, Company companyDetails) {
        return companyRepository.findById(id).map(existingCompany -> {
            existingCompany.setName(companyDetails.getName());
            existingCompany.setDescription(companyDetails.getDescription());
            return companyRepository.save(existingCompany);
        });
    }

    public boolean deleteCompany(Long id) {
        return companyRepository.findById(id).map(company -> {
            companyRepository.delete(company);
            return true;
        }).orElse(false);
    }
}
