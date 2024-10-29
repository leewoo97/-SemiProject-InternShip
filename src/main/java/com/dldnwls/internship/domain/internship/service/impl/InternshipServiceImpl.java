package com.dldnwls.internship.domain.internship.service.impl;

import com.dldnwls.internship.domain.company.Company;
import com.dldnwls.internship.domain.company.dto.response.CompanyDTO;
import com.dldnwls.internship.domain.company.repository.CompanyRepository;
import com.dldnwls.internship.domain.internship.Internship;
import com.dldnwls.internship.domain.internship.dto.request.CreateInternshipRequest;
import com.dldnwls.internship.domain.internship.dto.request.UpdateInternshipRequest;
import com.dldnwls.internship.domain.internship.dto.response.AddTechstacksByInternshipResponse;
import com.dldnwls.internship.domain.internship.dto.response.CreateInternshipResponse;
import com.dldnwls.internship.domain.internship.dto.response.GetInternshipResponse;
import com.dldnwls.internship.domain.internship.dto.response.UpdateInternshipResponse;
import com.dldnwls.internship.domain.internship.repository.InternshipRepository;
import com.dldnwls.internship.domain.internship.service.InternshipService;
import com.dldnwls.internship.domain.techstack.Techstack;
import com.dldnwls.internship.domain.techstack.dto.TechstackDTO;
import com.dldnwls.internship.domain.techstack.repository.TechstackRepository;
import com.dldnwls.internship.global.exception.company.CompanyNotFoundException;
import com.dldnwls.internship.global.exception.internship.InternshipNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Transactional
@RequiredArgsConstructor
@Service
public class InternshipServiceImpl implements InternshipService {

    private final InternshipRepository internshipRepository;
    private final CompanyRepository companyRepository;
    private final TechstackRepository techstackRepository;

    @Override
    public AddTechstacksByInternshipResponse addTechstacksByInternship(Long internshipId, Set<String> techstackNames) {
        Internship internship = internshipRepository.findById(internshipId).orElseThrow(() -> new InternshipNotFoundException("[AddTechstacks] Internship not found with id" + internshipId));

        Set<Techstack> newTechStacks = processTechstacks(techstackNames);
        internship.getRequiredSkills().addAll(newTechStacks);

        return convertToAddTechstacksByInternshipResponse(internship);
    }

    @Override
    public CreateInternshipResponse createInternship(CreateInternshipRequest createInternshipRequest) {

        Company company = companyRepository.findByName(createInternshipRequest.getCompanyName()).orElseThrow(() -> new CompanyNotFoundException("[Create] Company not found with name" + createInternshipRequest.getCompanyName()));
        Set<Techstack> techstacks = processTechstacks(createInternshipRequest.getRequiredSkillNames());

        Internship internship = Internship.builder()
                .title(createInternshipRequest.getTitle())
                .startDate(createInternshipRequest.getStartDate())
                .endDate(createInternshipRequest.getEndDate())
                .description(createInternshipRequest.getDescription())
                .location(createInternshipRequest.getLocation())
                .salary(createInternshipRequest.getSalary())
                .company(company)
                .requiredSkills(techstacks)
                .build();

        internshipRepository.save(internship);
        return convertToCreateInternshipResponse(internship);
    }

    @Override
    public GetInternshipResponse getInternship(Long internshipId) {
        Internship internship = internshipRepository.findById(internshipId).orElseThrow(()-> new InternshipNotFoundException("[Get] Internship not found with id " + internshipId));
        return convertToGetInternshipResponse(internship);
    }

    @Override
    public List<GetInternshipResponse> getInternshipsByFilter(String companyName, Set<String> techstackNames, LocalDate startDate, LocalDate endDate) {
        List<Internship> internshipList = internshipRepository.findByInternshipsByFilter(companyName, techstackNames, startDate, endDate);
        return internshipList.stream()
                .map(this::convertToGetInternshipResponse)
                .toList();
    }

    @Override
    public UpdateInternshipResponse updateInternship(Long internshipId, UpdateInternshipRequest updateInternshipRequest) {
        Internship internship = internshipRepository.findById(internshipId).orElseThrow(() -> new InternshipNotFoundException("[update] Internship not found with id " + internshipId));

        Company updateCompany = companyRepository.findByName(updateInternshipRequest.getCompanyName()).orElseThrow(() -> new CompanyNotFoundException("[update] Company not found with name " + updateInternshipRequest.getCompanyName()));
        Set<Techstack> updateRequiredSkills = processTechstacks(updateInternshipRequest.getRequiredSkillNames());

        internship.updateTitle(updateInternshipRequest.getTitle())
                .updateDescription(updateInternshipRequest.getDescription())
                .updateSalary(updateInternshipRequest.getSalary())
                .updateStartDate(updateInternshipRequest.getStartDate())
                .updateEndDate(updateInternshipRequest.getEndDate())
                .updateCompany(updateCompany)
                .updateRequiredSkills(updateRequiredSkills);

        return convertToUpdateInternshipResponse(internship);
    }

    @Override
    public boolean deleteInternship(Long internshipId) {
        Optional<Internship> internship = internshipRepository.findById(internshipId);

        if(internship.isPresent()){
            internshipRepository.deleteById(internshipId);
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean deleteTechstackByInternship(Long internshipId, Long techStackId) {
        Optional<Internship> optionalInternship = internshipRepository.findById(internshipId);
        Optional<Techstack> optionalTechstack = techstackRepository.findById(techStackId);

        if(optionalInternship.isPresent()&&optionalTechstack.isPresent()){
            Internship internship = optionalInternship.get();
            Techstack techstack = optionalTechstack.get();

            internship.removeTechStack(techstack);
            return true;
        }else{
            return false;
        }
    }


    //--------------------------------------------------------------------------------------------------------
    public Set<Techstack> processTechstacks(Set<String> techstackNames){
        Set<Techstack> existingTechstack = new HashSet<>(techstackRepository.findByNameIn(techstackNames));

        Set<String> existingNames = existingTechstack.stream()
                .map(Techstack::getName)
                .collect(Collectors.toSet());

        Set<Techstack> newTechstack = techstackNames.stream()
                .filter(name -> !existingNames.contains(name))
                .map(name -> Techstack.builder().name(name).build())
                .collect(Collectors.toSet());

        existingTechstack.addAll(techstackRepository.saveAll(newTechstack));
        return existingTechstack;
    }



    public AddTechstacksByInternshipResponse convertToAddTechstacksByInternshipResponse(Internship internship){
        return AddTechstacksByInternshipResponse.builder()
                .startDate(internship.getStartDate())
                .endDate(internship.getEndDate())
                .description(internship.getDescription())
                .location(internship.getLocation())
                .company(internship.getCompany())
                .requiredSkills(internship.getRequiredSkills().stream().map(this::convertToTechstackDTO).collect(Collectors.toSet()))
                .build();
    }

    public TechstackDTO convertToTechstackDTO(Techstack techstack){
        return TechstackDTO.builder()
                .name(techstack.getName())
                .build();
    }

    public CompanyDTO convertToCompanyDTO(Company company){
        return CompanyDTO.builder()
                .name(company.getName())
                .location(company.getLocation())
                .industry(company.getIndustry())
                .description(company.getDescription())
                .website(company.getWebsite())
                .build();
    }

    public CreateInternshipResponse convertToCreateInternshipResponse(Internship internship){
        return CreateInternshipResponse.builder()
                .startDate(internship.getStartDate())
                .endDate(internship.getEndDate())
                .description(internship.getDescription())
                .location(internship.getLocation())
                .salary(internship.getSalary())
                .company(convertToCompanyDTO(internship.getCompany()))
                .requiredSkills(internship.getRequiredSkills().stream()
                        .map(this::convertToTechstackDTO)
                        .collect(Collectors.toSet()))
                .build();
    }

    public GetInternshipResponse convertToGetInternshipResponse(Internship internship){
        return GetInternshipResponse.builder()
                .title(internship.getTitle())
                .startDate(internship.getStartDate())
                .endDate(internship.getEndDate())
                .description(internship.getDescription())
                .location(internship.getLocation())
                .salary(internship.getSalary())
                .company(convertToCompanyDTO(internship.getCompany()))
                .requiredSkills(internship.getRequiredSkills().stream()
                        .map(this::convertToTechstackDTO)
                        .collect(Collectors.toSet()))
                .build();
    }

    public UpdateInternshipResponse convertToUpdateInternshipResponse(Internship internship){
        return UpdateInternshipResponse.builder()
                .startDate(internship.getStartDate())
                .endDate(internship.getEndDate())
                .description(internship.getDescription())
                .location(internship.getLocation())
                .salary(internship.getSalary())
                .company(convertToCompanyDTO(internship.getCompany()))
                .requiredSkills(internship.getRequiredSkills().stream().map(this::convertToTechstackDTO).collect(Collectors.toSet()))
                .build();
    }

}
