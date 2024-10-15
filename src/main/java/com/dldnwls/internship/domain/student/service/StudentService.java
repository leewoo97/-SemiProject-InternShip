package com.dldnwls.internship.domain.student.service;

import com.dldnwls.internship.domain.student.dto.request.CreateStudentDTO;
import com.dldnwls.internship.domain.student.dto.request.UpdateStudentDTO;
import com.dldnwls.internship.domain.student.dto.response.StudentDTO;

import java.util.List;
import java.util.Set;

public interface StudentService {
    public StudentDTO createStudent(CreateStudentDTO createStudentDTO);

    public StudentDTO getStudentById(Long studentId);

    public List<StudentDTO> getStudents(String name, String major, Set<String > techStackNames);

    public StudentDTO updateStudent(Long studentId, UpdateStudentDTO updateStudentDTO);
}
