package com.dldnwls.internship.domain.company.controller;

import com.dldnwls.internship.domain.company.Company;
import com.dldnwls.internship.domain.company.dto.request.CreateCompanyRequest;
import com.dldnwls.internship.domain.company.dto.response.CreateCompanyResponse;
import com.dldnwls.internship.domain.company.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/companies")
@RestController
public class CompanyController {

    private final CompanyService companyService;

    @PostMapping
    ResponseEntity<?> createCompany(@RequestBody CreateCompanyRequest createCompanyRequest){
        CreateCompanyResponse createCompanyResponse = companyService.createCompany(createCompanyRequest);
        return ResponseEntity.ok(createCompanyResponse);
    }
}
