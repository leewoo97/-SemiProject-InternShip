package com.dldnwls.internship.domain.company.service;

import com.dldnwls.internship.domain.company.dto.request.CreateCompanyRequest;
import com.dldnwls.internship.domain.company.dto.response.CreateCompanyResponse;

public interface CompanyService {

    public CreateCompanyResponse createCompany(CreateCompanyRequest createCompanyRequest);
}
