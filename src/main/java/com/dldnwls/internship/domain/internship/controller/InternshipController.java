package com.dldnwls.internship.domain.internship.controller;

import com.dldnwls.internship.domain.internship.dto.request.CreateInternshipRequest;
import com.dldnwls.internship.domain.internship.dto.request.UpdateInternshipRequest;
import com.dldnwls.internship.domain.internship.dto.response.AddTechstacksByInternshipResponse;
import com.dldnwls.internship.domain.internship.dto.response.CreateInternshipResponse;
import com.dldnwls.internship.domain.internship.dto.response.GetInternshipResponse;
import com.dldnwls.internship.domain.internship.dto.response.UpdateInternshipResponse;
import com.dldnwls.internship.domain.internship.service.InternshipService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@RequestMapping("/internships")
@RequiredArgsConstructor
@RestController
public class InternshipController {

    private final InternshipService internshipService;

    //인턴십 등록 API
    @PostMapping
    ResponseEntity<CreateInternshipResponse> createInternship(@RequestBody CreateInternshipRequest createInternshipRequest){
        CreateInternshipResponse createInternshipResponse = internshipService.createInternship(createInternshipRequest);
        return ResponseEntity.ok(createInternshipResponse);
    }

    //인턴십 정보 조회 API
    @GetMapping("/{internshipId}")
    ResponseEntity<GetInternshipResponse> getInternship(@PathVariable Long internshipId){
        GetInternshipResponse getInternshipResponse = internshipService.getInternship(internshipId);
        return ResponseEntity.ok(getInternshipResponse);
    }

    //진행중인 인턴쉽만 확인할수있도록 변경해야함
    @GetMapping
    ResponseEntity<List<GetInternshipResponse>> getInternshipsByFilter(@RequestParam(required = false) String companyName, @RequestParam(required = false) Set<String> techstackNames, @RequestParam(required = false) LocalDate startDate, @RequestParam(required = false) LocalDate endDate){
        List<GetInternshipResponse> internshipList = internshipService.getInternshipsByFilter(companyName, techstackNames, startDate, endDate);
        return ResponseEntity.ok(internshipList);
    }

    //인턴쉽 수정 API
    @PutMapping("/{internshipId}")
    ResponseEntity<UpdateInternshipResponse> updateInternship(@PathVariable Long internshipId, @RequestBody UpdateInternshipRequest updateInternshipRequest){
        UpdateInternshipResponse updateInternshipResponse = internshipService.updateInternship(internshipId, updateInternshipRequest);
        return ResponseEntity.ok(updateInternshipResponse);
    }

    //인턴쉽 삭제 API
    @DeleteMapping("/{internshipId}")
    ResponseEntity<Void> deleteInternship(@PathVariable Long internshipId){
        boolean isDeleted = internshipService.deleteInternship(internshipId);

        if(isDeleted){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //인턴십 기술 스택 추가 API
    @PostMapping("/{internshipId}/techstacks")
    ResponseEntity<AddTechstacksByInternshipResponse> addTechstacks(@PathVariable Long internshipId, @RequestBody Set<String> techstackNames){
        AddTechstacksByInternshipResponse addTechstacksByInternshipResponse = internshipService.addTechstacksByInternship(internshipId, techstackNames);
        return ResponseEntity.ok(addTechstacksByInternshipResponse);
    }

    //인턴십 기술 스택 삭제 API
    @DeleteMapping("{internshipId}/techstacks/{techStackId}")
    ResponseEntity<Void> deleteTechstackByInternship(@PathVariable Long internshipId, @PathVariable Long techStackId){
        boolean isDeleted = internshipService.deleteTechstackByInternship(internshipId, techStackId);

        if(isDeleted){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}
