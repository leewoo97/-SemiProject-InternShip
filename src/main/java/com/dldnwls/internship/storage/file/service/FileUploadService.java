package com.dldnwls.internship.storage.file.service;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.CompletableFuture;

@Service
public class FileUploadService {

    //기준 경로 설정
    private final Path uploadDirectory = Paths.get("uploads");

    public FileUploadService() throws IOException {
        Files.createDirectories(uploadDirectory);
    }

    @Async("fileUploadTaskExecutor")
    public CompletableFuture<String> uploadFile(Long studentId, MultipartFile file) throws Exception {
        // studentId로 폴더 생성
        Path studentFolder = uploadDirectory.resolve(String.valueOf(studentId));
        Files.createDirectories(studentFolder);

        //파일 저장 경로 설정
        Path filePath = studentFolder.resolve(file.getOriginalFilename());

        //파일 저장
        Files.copy(file.getInputStream(), filePath);

        //성공메세지 반환
        return CompletableFuture.completedFuture("File uploaded : " + file.getOriginalFilename());
    }
}
