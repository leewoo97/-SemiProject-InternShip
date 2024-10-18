package com.dldnwls.internship.domain.company.controller;

import com.dldnwls.internship.domain.company.dto.request.CreateCompanyRequest;
import com.dldnwls.internship.domain.company.dto.request.UpdateCompanyRequest;
import com.dldnwls.internship.domain.company.dto.response.CreateCompanyResponse;
import com.dldnwls.internship.domain.company.dto.response.GetCompanyResponse;
import com.dldnwls.internship.domain.company.dto.response.UpdateCompanyResponse;
import com.dldnwls.internship.domain.company.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/companies")
@RestController
public class CompanyController {

    private final CompanyService companyService;

    @PostMapping
    ResponseEntity<CreateCompanyResponse> createCompany(@RequestBody CreateCompanyRequest createCompanyRequest){
        CreateCompanyResponse createCompanyResponse = companyService.createCompany(createCompanyRequest);
        return ResponseEntity.ok(createCompanyResponse);
    }

    @GetMapping("/{companyId}")
    ResponseEntity<GetCompanyResponse> getCompany(@PathVariable Long companyId){
        GetCompanyResponse getCompanyResponse = companyService.getCompany(companyId);
        return ResponseEntity.ok(getCompanyResponse);
    }

    @GetMapping
    ResponseEntity<List<GetCompanyResponse>> getCompaniesByFilter(@RequestParam(required = false) String name, @RequestParam(required = false) String location, @RequestParam(required = false) String industry){
        List<GetCompanyResponse> companies = companyService.getCompaniesByFilter(name, location, industry);
        return ResponseEntity.ok(companies);
    }

    @PutMapping("/{companyId}")
    ResponseEntity<UpdateCompanyResponse> updateCompany(@PathVariable Long companyId, @RequestParam UpdateCompanyRequest updateCompanyRequest){
         UpdateCompanyResponse updateCompanyResponse = companyService.updateCompany(companyId, updateCompanyRequest);
         return ResponseEntity.ok(updateCompanyResponse);
    }

    @DeleteMapping("/{companyId}")
    ResponseEntity<Void> deleteCompany(@PathVariable Long companyId){
        companyService.deleteCompany(companyId);
        return ResponseEntity.noContent().build();
    }

}
