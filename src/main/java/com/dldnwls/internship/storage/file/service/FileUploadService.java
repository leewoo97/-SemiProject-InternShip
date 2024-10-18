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

    private final Path uploadDirectory = Paths.get("uploads");

    public FileUploadService() throws IOException {
        Files.createDirectories(uploadDirectory);
    }

    @Async("fileUploadTaskExecutor")
    public CompletableFuture<String> uploadFile(MultipartFile file) throws Exception {
        //파일 저장 경로 설정
        Path filePath = uploadDirectory.resolve(file.getOriginalFilename());

        //파일 저장
        Files.copy(file.getInputStream(), filePath);

        //성공메세지 반환
        return CompletableFuture.completedFuture("File uploaded : " + file.getOriginalFilename());
    }
}
