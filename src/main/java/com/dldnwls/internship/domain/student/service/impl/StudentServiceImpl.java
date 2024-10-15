package com.dldnwls.internship.domain.student.service.impl;

import com.dldnwls.internship.domain.student.Student;
import com.dldnwls.internship.domain.student.dto.request.CreateStudentDTO;
import com.dldnwls.internship.domain.student.dto.request.UpdateStudentDTO;
import com.dldnwls.internship.domain.student.dto.response.StudentDTO;
import com.dldnwls.internship.domain.student.repository.StudentRepository;
import com.dldnwls.internship.domain.student.service.StudentService;
import com.dldnwls.internship.domain.techstack.Techstack;
import com.dldnwls.internship.domain.techstack.dto.TechStackDTO;
import com.dldnwls.internship.domain.techstack.repository.TechstackRepository;
import com.dldnwls.internship.global.exception.student.StudentNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional
@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final TechstackRepository techstackRepository;

    @Override
    public StudentDTO createStudent(CreateStudentDTO createStudentDTO) {

        Student student = Student.builder()
                .name(createStudentDTO.getName())
                .email(createStudentDTO.getEmail())
                .phone(createStudentDTO.getPhone())
                .university(createStudentDTO.getUniversity())
                .major(createStudentDTO.getMajor())
                .resume(createStudentDTO.getResume())
                .techStacks(processTechStacks(createStudentDTO.getTechStackNames()))
                .build();

        Student savedStudent = studentRepository.save(student);

        return convertToStudentDTO(savedStudent);
    }

    @Override
    public StudentDTO getStudentById(Long studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new StudentNotFoundException("[Get] Student not found with id: " + studentId));
        return convertToStudentDTO(student);
    }

    @Override
    public List<StudentDTO> getStudents(String name, String major, Set<String> techStackNames) {
        List<Student> students = studentRepository.findStudentsByFilter(name, major, techStackNames);

        return students.stream()
                .map(this::convertToStudentDTO)
                .toList();
    }

    @Override
    public StudentDTO updateStudent(Long studentId, UpdateStudentDTO updateStudentDTO) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(()->new StudentNotFoundException("[Update] Student not found with id: " + studentId));

        student.updateName(updateStudentDTO.getName())
               .updateEmail(updateStudentDTO.getEmail())
               .updatePhone(updateStudentDTO.getPhone())
               .updateMajor(updateStudentDTO.getMajor())
               .updateUniversity(updateStudentDTO.getUniversity())
               .updateResume(updateStudentDTO.getResume())
               .updateTechStacks(processTechStacks(updateStudentDTO.getTechStackNames()));

        studentRepository.save(student);
        return convertToStudentDTO(student);
    }

    //---------------------------------------------------------------------------------------------------------------------------
// TechStack 처리 메서드 (이전과 동일)
private Set<Techstack> processTechStacks(Set<String> techStackNames) {
    Set<Techstack> existingTechStacks = new HashSet<>(techstackRepository.findByNameIn(techStackNames));

    Set<String> existingNames = existingTechStacks.stream()
            .map(Techstack::getName)
            .collect(Collectors.toSet());

    Set<Techstack> newTechStacks = techStackNames.stream()
            .filter(name -> !existingNames.contains(name))
            .map(name -> Techstack.builder().name(name).build())
            .collect(Collectors.toSet());

    if (!newTechStacks.isEmpty()) {
        existingTechStacks.addAll(techstackRepository.saveAll(newTechStacks));
    }

    return existingTechStacks;
}

    private StudentDTO convertToStudentDTO(Student student) {
        return StudentDTO.builder()
                .id(student.getId())
                .name(student.getName())
                .email(student.getEmail())
                .major(student.getMajor())
                .phone(student.getPhone())
                .resume(student.getResume())
                .university(student.getUniversity())
                .techStacks(student.getTechStacks().stream()
                        .map(this::convertToTechStackDTO)
                        .collect(Collectors.toSet()))
                .build();
    }

    private TechStackDTO convertToTechStackDTO(Techstack techstack) {
        return TechStackDTO.builder()
                .id(techstack.getId())
                .name(techstack.getName())
                .build();
    }

}
