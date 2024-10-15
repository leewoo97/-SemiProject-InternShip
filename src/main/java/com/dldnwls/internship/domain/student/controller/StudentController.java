package com.dldnwls.internship.domain.student.controller;

import com.dldnwls.internship.domain.student.dto.request.CreateStudentDTO;
import com.dldnwls.internship.domain.student.dto.request.UpdateStudentDTO;
import com.dldnwls.internship.domain.student.dto.response.StudentDTO;
import com.dldnwls.internship.domain.student.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    @PostMapping
    public ResponseEntity<StudentDTO> createStudent(@RequestBody CreateStudentDTO createStudentDTO){
        StudentDTO createdStudent = studentService.createStudent(createStudentDTO);
        return ResponseEntity.ok(createdStudent);
    }

    @GetMapping("/{studentId}")
    public ResponseEntity<StudentDTO> getStudentById(@PathVariable Long studentId){
        StudentDTO studentDTO = studentService.getStudentById(studentId);
        return ResponseEntity.ok(studentDTO);
    }

    @GetMapping
    public ResponseEntity<List<StudentDTO>> getStudents(@RequestParam(required = false) String name, @RequestParam(required = false) String major, @RequestParam(required = false)Set<String> techStackNames){
        List<StudentDTO> students = studentService.getStudents(name, major, techStackNames);
        return ResponseEntity.ok(students);
    }

    @PutMapping("/{studentId}")
    public ResponseEntity<StudentDTO> updateStudent(@PathVariable Long studentId, @RequestBody UpdateStudentDTO updateStudentDTO){
        StudentDTO studentDTO = studentService.updateStudent(studentId, updateStudentDTO);
        return ResponseEntity.ok(studentDTO);
    }


}
