package com.dldnwls.internship.domain.company.service.impl;

import com.dldnwls.internship.domain.company.Company;
import com.dldnwls.internship.domain.company.dto.request.CreateCompanyRequest;
import com.dldnwls.internship.domain.company.dto.request.UpdateCompanyRequest;
import com.dldnwls.internship.domain.company.dto.response.CreateCompanyResponse;
import com.dldnwls.internship.domain.company.dto.response.GetCompanyResponse;
import com.dldnwls.internship.domain.company.dto.response.UpdateCompanyResponse;
import com.dldnwls.internship.domain.company.repository.CompanyRepository;
import com.dldnwls.internship.domain.company.service.CompanyService;
import com.dldnwls.internship.global.exception.company.CompanyNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Transactional
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

    @Override
    public GetCompanyResponse getCompany(Long companyId) {
        Company company = companyRepository.findById(companyId).orElseThrow(() -> new CompanyNotFoundException("[GetCompany] CompanyNotFoundException : " + companyId));
        return convertToGetCompanyResponse(company);
    }

    @Override
    public List<GetCompanyResponse> getCompaniesByFilter(String name, String location, String industry) {
        List<Company> companies = companyRepository.findCompaniesByFilter(name, location, industry);
        return companies.stream()
                .map(this::convertToGetCompanyResponse)
                .toList();
    }

    @Override
    public UpdateCompanyResponse updateCompany(Long companyId, UpdateCompanyRequest updateCompanyRequest) {
        Company company = companyRepository.findById(companyId).orElseThrow(() -> new CompanyNotFoundException("[UpdateCompany] CompanyNotFoundException : " + companyId));

        company.updateName(updateCompanyRequest.getName())
                .updateLocation(updateCompanyRequest.getLocation())
                .updateWebsite(updateCompanyRequest.getWebsite())
                .updateDescription(updateCompanyRequest.getDescription())
                .updateIndustry(updateCompanyRequest.getIndustry());

        return convertToUpdateCompanyResponse(company);
    }

    @Override
    public void deleteCompany(Long companyId) {
        Company company = companyRepository.findById(companyId).orElseThrow(() -> new CompanyNotFoundException("[DeleteCompany] CompanyNotFoundException " + companyId));
        companyRepository.delete(company);
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

    public GetCompanyResponse convertToGetCompanyResponse(Company company){
        return GetCompanyResponse.builder()
                .name(company.getName())
                .description(company.getDescription())
                .industry(company.getIndustry())
                .location(company.getLocation())
                .website(company.getWebsite())
                .createdAt(company.getCreatedAt())
                .build();
    }

    public UpdateCompanyResponse convertToUpdateCompanyResponse(Company company){
        return UpdateCompanyResponse.builder()
                .name(company.getName())
                .description(company.getDescription())
                .industry(company.getIndustry())
                .location(company.getLocation())
                .website(company.getWebsite())
                .build();
    }
}
