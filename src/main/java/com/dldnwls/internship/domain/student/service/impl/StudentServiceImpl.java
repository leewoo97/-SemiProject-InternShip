package com.dldnwls.internship.domain.student.service.impl;

import com.dldnwls.internship.domain.student.Student;
import com.dldnwls.internship.domain.student.dto.request.student.CreateStudentRequest;
import com.dldnwls.internship.domain.student.dto.request.student.UpdateStudentRequest;
import com.dldnwls.internship.domain.student.dto.response.student.*;
import com.dldnwls.internship.domain.student.repository.StudentRepository;
import com.dldnwls.internship.domain.student.service.StudentService;
import com.dldnwls.internship.domain.techstack.Techstack;
import com.dldnwls.internship.domain.techstack.dto.TechStackDTO;
import com.dldnwls.internship.domain.techstack.repository.TechstackRepository;
import com.dldnwls.internship.global.exception.student.StudentNotFoundException;
import com.dldnwls.internship.global.exception.techstack.TechstackNotFoundException;
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
    public CreateStudentResponse createStudent(CreateStudentRequest createStudentRequest) {

        Student student = Student.builder()
                .name(createStudentRequest.getName())
                .email(createStudentRequest.getEmail())
                .phone(createStudentRequest.getPhone())
                .university(createStudentRequest.getUniversity())
                .major(createStudentRequest.getMajor())
                .resume(createStudentRequest.getResume())
                .techStacks(processTechStacks(createStudentRequest.getTechStackNames()))
                .build();

        Student savedStudent = studentRepository.save(student);

        return convertToCreateStudentResponse(savedStudent);
    }

    @Override
    public StudentDTO getStudentById(Long studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new StudentNotFoundException("[Get] Student not found with id: " + studentId));
        return convertToStudentDTO(student);
    }

    @Override
    public List<StudentDTO> getStudentsByFilter(String name, String major, Set<String> techStackNames) {
        List<Student> students = studentRepository.findStudentsByFilter(name, major, techStackNames);

        return students.stream()
                .map(this::convertToStudentDTO)
                .toList();
    }

    @Override
    public UpdateStudentResponse updateStudent(Long studentId, UpdateStudentRequest updateStudentRequest) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(()->new StudentNotFoundException("[Update] Student not found with id: " + studentId));

        student.updateName(updateStudentRequest.getName())
               .updateEmail(updateStudentRequest.getEmail())
               .updatePhone(updateStudentRequest.getPhone())
               .updateMajor(updateStudentRequest.getMajor())
               .updateUniversity(updateStudentRequest.getUniversity())
               .updateResume(updateStudentRequest.getResume())
               .updateTechStacks(processTechStacks(updateStudentRequest.getTechStackNames()));

        return convertToUpdateStudentResponse(student);
    }

    @Override
    public DeleteStudentResponse deleteStudent(Long studentId) {
        Student student = studentRepository.findById(studentId).orElseThrow(()-> new StudentNotFoundException("[Delete] Student not found with id : " + studentId));
        studentRepository.delete(student);
        return convertToDeleteStudentResponse(student);
    }

    @Override
    public AddTechstacksResponse addTechstack(Long studentId, Set<String> techStackNames) {
        Student student = studentRepository.findById(studentId).orElseThrow(() -> new StudentNotFoundException("[AddTechstacks] Student not found with id : " + studentId));

        Set<Techstack> newTechStacks = processTechStacks(techStackNames);
        student.getTechStacks().addAll(newTechStacks);

        return convertToAddTechstacksResponse(student);
    }

    @Override
    public DeleteTechstackResponse deleteTechstack(Long studentId, Long techstackId) {
        Student student = studentRepository.findById(studentId).orElseThrow(() -> new StudentNotFoundException("[DeleteTechstack] Student not found with id : " + studentId));

        Techstack techstack = student.getTechStacks().stream()
                .filter(ts -> ts.getId().equals(techstackId))
                .findFirst()
                .orElseThrow(() -> new TechstackNotFoundException("[DeleteTechstack] Techstack not found with id: " + techstackId));

        student.removeTechStack(techstack);
        return convertToDeleteTechstackResponse(student);
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

    private CreateStudentResponse convertToCreateStudentResponse(Student student){
        return CreateStudentResponse.builder()
                .name(student.getName())
                .email(student.getEmail())
                .phone(student.getPhone())
                .major(student.getMajor())
                .phone(student.getPhone())
                .resume(student.getResume())
                .university(student.getUniversity())
                .techStacks(student.getTechStacks().stream()
                        .map(this::convertToTechStackDTO)
                        .collect(Collectors.toSet()))
                .build();
    }

    private UpdateStudentResponse convertToUpdateStudentResponse(Student student){
        return UpdateStudentResponse.builder()
                .name(student.getName())
                .email(student.getEmail())
                .phone(student.getPhone())
                .major(student.getMajor())
                .phone(student.getPhone())
                .resume(student.getResume())
                .university(student.getUniversity())
                .techStacks(student.getTechStacks().stream()
                        .map(this::convertToTechStackDTO)
                        .collect(Collectors.toSet()))
                .build();
    }

    private DeleteStudentResponse convertToDeleteStudentResponse(Student student){
        return DeleteStudentResponse.builder()
                .name(student.getName())
                .phone(student.getPhone())
                .email(student.getEmail())
                .major(student.getMajor())
                .university(student.getUniversity())
                .resume(student.getResume())
                .createdAt(student.getCreatedAt())
                .build();
    }

    private AddTechstacksResponse convertToAddTechstacksResponse(Student student){
        return AddTechstacksResponse.builder()
                .name(student.getName())
                .phone(student.getPhone())
                .email(student.getEmail())
                .major(student.getMajor())
                .university(student.getUniversity())
                .resume(student.getResume())
                .createdAt(student.getCreatedAt())
                .techStacks(student.getTechStacks().stream()
                        .map(this::convertToTechStackDTO)
                        .collect(Collectors.toSet()))
                .build();
    }

    private DeleteTechstackResponse convertToDeleteTechstackResponse(Student student){
        return DeleteTechstackResponse.builder()
                .name(student.getName())
                .phone(student.getPhone())
                .email(student.getEmail())
                .major(student.getMajor())
                .university(student.getUniversity())
                .resume(student.getResume())
                .createdAt(student.getCreatedAt())
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
