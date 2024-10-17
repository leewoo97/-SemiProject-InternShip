package com.dldnwls.internship.domain.company.service.impl;

import com.dldnwls.internship.domain.company.Company;
import com.dldnwls.internship.domain.company.dto.request.CreateCompanyRequest;
import com.dldnwls.internship.domain.company.dto.response.CreateCompanyResponse;
import com.dldnwls.internship.domain.company.repository.CompanyRepository;
import com.dldnwls.internship.domain.company.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;

    @Override
    public CreateCompanyResponse createCompany(CreateCompanyRequest createCompanyRequest) {
        Company company = Company.builder()
                .name(createCompanyRequest.getName())
                .description(createCompanyRequest.getDescription())
                .industry(createCompanyRequest.getIndustry())
                .location(createCompanyRequest.getLocation())
                .website(createCompanyRequest.getWebsite())
                .createdAt(createCompanyRequest.getCreatedAt())
                .build();

        companyRepository.save(company);
        return convertToCreateCompanyResponse(company);
    }

//----------------------------------------------------------------------------------------------------------------------
    public CreateCompanyResponse convertToCreateCompanyResponse(Company company){
        return CreateCompanyResponse.builder()
                .name(company.getName())
                .description(company.getDescription())
                .industry(company.getIndustry())
                .location(company.getLocation())
                .website(company.getWebsite())
                .createdAt(company.getCreatedAt())
                .build();
    }
}
