package com.dldnwls.internship.domain.student.service;

import com.dldnwls.internship.domain.student.dto.request.student.CreateStudentRequest;
import com.dldnwls.internship.domain.student.dto.request.student.UpdateStudentRequest;
import com.dldnwls.internship.domain.student.dto.response.student.*;

import java.util.List;
import java.util.Set;

public interface StudentService {
    public CreateStudentResponse createStudent(CreateStudentRequest createStudentRequest);

    public StudentDTO getStudentById(Long studentId);

    public List<StudentDTO> getStudentsByFilter(String name, String major, Set<String > techStackNames);

    public UpdateStudentResponse updateStudent(Long studentId, UpdateStudentRequest updateStudentRequest);

    public DeleteStudentResponse deleteStudent(Long studentId);

    public AddTechstacksResponse addTechstack(Long studentId, Set<String> techStackNames);

    public DeleteTechstackResponse deleteTechstack(Long studentId, Long techstackId);

    public List<RecommendInternshipResponse> recommendInternships(Long studentId);
}
