package com.dldnwls.internship.domain.company.repository.custom;

import com.dldnwls.internship.domain.company.Company;
import com.dldnwls.internship.domain.company.dto.response.GetCompanyResponse;
import com.dldnwls.internship.domain.techstack.Techstack;

import java.util.List;
import java.util.Optional;

public interface CompanyRepositoryCustom {
    List<Company> findCompaniesByFilter(String name, String location, String industry);
}
