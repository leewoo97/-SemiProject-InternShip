package com.dldnwls.internship.domain.student.controller;

import com.dldnwls.internship.domain.student.dto.request.student.CreateStudentRequest;
import com.dldnwls.internship.domain.student.dto.request.student.UpdateStudentRequest;
import com.dldnwls.internship.domain.student.dto.response.student.*;
import com.dldnwls.internship.domain.student.service.StudentService;
import com.dldnwls.internship.storage.file.service.FileUploadService;
import lombok.RequiredArgsConstructor;
import org.hibernate.sql.Delete;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

@RequiredArgsConstructor
@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;
    private final FileUploadService fileUploadService;

    @PostMapping
    public ResponseEntity<CreateStudentResponse> createStudent(@RequestBody CreateStudentRequest createStudentRequest){
        CreateStudentResponse createdStudent = studentService.createStudent(createStudentRequest);
        return ResponseEntity.ok(createdStudent);
    }

    @PostMapping(value = "/uploadResume", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ResponseEntity<?> uploadResume(@RequestParam("file") MultipartFile file) {
        try {
            CompletableFuture<String> uploadFuture = fileUploadService.uploadFile(file);
            return ResponseEntity.ok("File upload in progress.");
        }catch(Exception e){
            return ResponseEntity.status(500).body("File upload failed: " + e.getMessage());
        }
    }

    @GetMapping("/{studentId}")
    public ResponseEntity<StudentDTO> getStudentById(@PathVariable Long studentId){
        StudentDTO studentDTO = studentService.getStudentById(studentId);
        return ResponseEntity.ok(studentDTO);
    }

    @GetMapping
    public ResponseEntity<List<StudentDTO>> getStudentsByFilter(@RequestParam(required = false) String name, @RequestParam(required = false) String major, @RequestParam(required = false)Set<String> techStackNames){
        List<StudentDTO> students = studentService.getStudentsByFilter(name, major, techStackNames);
        return ResponseEntity.ok(students);
    }

    @PutMapping("/{studentId}")
    public ResponseEntity<UpdateStudentResponse> updateStudent(@PathVariable Long studentId, @RequestBody UpdateStudentRequest updateStudentRequest){
        UpdateStudentResponse updateStudentResponse = studentService.updateStudent(studentId, updateStudentRequest);
        return ResponseEntity.ok(updateStudentResponse);
    }

    @DeleteMapping("/{studentId}")
    public ResponseEntity<DeleteStudentResponse> deleteStudent(@PathVariable Long studentId){
        DeleteStudentResponse deleteStudentResponse = studentService.deleteStudent(studentId);
        return ResponseEntity.ok(deleteStudentResponse);
    }

    @PostMapping("/{studentId}/techstacks")
    public ResponseEntity<AddTechstacksResponse> addTechstacks(@PathVariable Long studentId, @RequestParam Set<String> techStackNames){
        AddTechstacksResponse addTechstacksResponse = studentService.addTechstack(studentId, techStackNames);
        return ResponseEntity.ok(addTechstacksResponse);
    }

    @DeleteMapping("/{studentId}/techstacks/{techstackId}")
    public ResponseEntity<DeleteTechstackResponse> deleteTechstack(@PathVariable Long studentId, @PathVariable Long techstackId){
        DeleteTechstackResponse deleteTechstackResponse = studentService.deleteTechstack(studentId, techstackId);
        return ResponseEntity.ok(deleteTechstackResponse);
    }


}
