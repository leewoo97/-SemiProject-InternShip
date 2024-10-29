package com.dldnwls.internship.domain.internship.service;

import com.dldnwls.internship.domain.internship.dto.request.CreateInternshipRequest;
import com.dldnwls.internship.domain.internship.dto.request.UpdateInternshipRequest;
import com.dldnwls.internship.domain.internship.dto.response.AddTechstacksByInternshipResponse;
import com.dldnwls.internship.domain.internship.dto.response.CreateInternshipResponse;
import com.dldnwls.internship.domain.internship.dto.response.GetInternshipResponse;
import com.dldnwls.internship.domain.internship.dto.response.UpdateInternshipResponse;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface InternshipService {
    public AddTechstacksByInternshipResponse addTechstacksByInternship(Long internshipId, Set<String> techstackNames);

    public CreateInternshipResponse createInternship(CreateInternshipRequest createInternshipRequest);

    public GetInternshipResponse getInternship(Long internshipId);

    public List<GetInternshipResponse> getInternshipsByFilter(String companyName, Set<String> techstackNames, LocalDate startDate, LocalDate endDate);

    public UpdateInternshipResponse updateInternship(Long internshipId, UpdateInternshipRequest updateInternshipRequest);

    public boolean deleteInternship(Long internshipId);

    public boolean deleteTechstackByInternship(Long internshipId, Long techStackId);
}
