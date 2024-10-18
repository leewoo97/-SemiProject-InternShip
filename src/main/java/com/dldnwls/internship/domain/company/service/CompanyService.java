package com.dldnwls.internship.domain.company.service;

import com.dldnwls.internship.domain.company.dto.request.CreateCompanyRequest;
import com.dldnwls.internship.domain.company.dto.request.UpdateCompanyRequest;
import com.dldnwls.internship.domain.company.dto.response.CreateCompanyResponse;
import com.dldnwls.internship.domain.company.dto.response.GetCompanyResponse;
import com.dldnwls.internship.domain.company.dto.response.UpdateCompanyResponse;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface CompanyService {

    public CreateCompanyResponse createCompany(CreateCompanyRequest createCompanyRequest);

    public GetCompanyResponse getCompany(Long companyId);

    public List<GetCompanyResponse> getCompaniesByFilter(String name, String location, String industry);

    public UpdateCompanyResponse updateCompany(Long companyId, UpdateCompanyRequest updateCompanyRequest);

    public void deleteCompany(Long companyId);
}
