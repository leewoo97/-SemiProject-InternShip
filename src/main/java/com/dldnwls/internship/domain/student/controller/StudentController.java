package com.dldnwls.internship.domain.student.controller;

import com.dldnwls.internship.domain.student.dto.request.student.CreateStudentRequest;
import com.dldnwls.internship.domain.student.dto.request.student.UpdateStudentRequest;
import com.dldnwls.internship.domain.student.dto.response.student.*;
import com.dldnwls.internship.domain.student.service.StudentService;
import com.dldnwls.internship.storage.file.service.FileUploadService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Slf4j
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


    @Operation(summary = "이력서 저장(비동기 방식)", description = "ThreadPool을 활용해 비동기 방식으로 이력서를 저장합니다.")
    @PostMapping(value = "/uploadResume/async/{studentId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ResponseEntity<String> uploadResumeByAsync(@PathVariable Long studentId , @RequestParam("file") MultipartFile file) throws IOException {
        try{
            fileUploadService.uploadFileByAsync(studentId,file.getBytes(),file.getOriginalFilename());  // 비동기로 파일 저장
            return ResponseEntity.ok("File upload in progress.");
        }catch(Exception e){
            return ResponseEntity.status(500).body("File upload failed: " + e.getMessage());
        }
    }

    @Operation(summary = "이력서 저장(동기 방식)", description = "동기 방식으로 이력서를 저장합니다.")
    @PostMapping(value = "/uploadResume/synch/{studentId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ResponseEntity<String> uploadResumeBySynch(@PathVariable Long studentId, @RequestParam("file") MultipartFile file){
        try{
            fileUploadService.uploadFileBySynch(studentId,file);
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
