package com.dldnwls.internship.storage.file.service;

import com.dldnwls.internship.storage.file.vo.FileUploadRequestVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
@RequiredArgsConstructor
public class FileUploadService {

    private final String uploadDirectory = "uploads";

    // 비동기 파일 생성 로직
    @Async
    public void uploadFileByAsync(Long studentId, byte[] fileBytes, String originalFileName) throws IOException {
        try {
            // 업로드 디렉토리 생성
            Path directoryPath = Paths.get(uploadDirectory);
            if (Files.notExists(directoryPath)) {
                Files.createDirectories(directoryPath);
                log.info("Upload directory created: {}", directoryPath);
            }

            // 학생 별 디렉토리 생성
            Path studentPath = directoryPath.resolve(String.valueOf(studentId));
            if (Files.notExists(studentPath)) {
                Files.createDirectories(studentPath);
                log.info("Student directory created: {}", studentPath);
            }

            // 파일 이름에 UUID 추가하여 저장
            String fileExtension = getFileExtension(originalFileName);
            String uniqueFileName = UUID.randomUUID() + fileExtension;
            Path filePath = studentPath.resolve(uniqueFileName);

            // 파일 저장
            try (FileOutputStream fos = new FileOutputStream(filePath.toFile())) {
                fos.write(fileBytes);  // byte[] 데이터를 파일에 씀
            }

            log.info("File saved successfully: {}", filePath);

        } catch (IOException e) {
            log.error("Error occurred while uploading file: {}", e.getMessage());
        }
    }



    public void uploadFileBySynch(Long studentId, MultipartFile file){
        try {
            // 업로드 디렉토리 생성
            Path directoryPath = Paths.get(uploadDirectory);
            if (Files.notExists(directoryPath)) {
                Files.createDirectories(directoryPath);
                log.info("Upload directory created: {}", directoryPath);
            }

            // 학생 별 디렉토리 생성
            Path studentPath = directoryPath.resolve(String.valueOf(studentId));
            if (Files.notExists(studentPath)) {
                Files.createDirectories(studentPath);
                log.info("Student directory created: {}", studentPath);
            }

            // 파일 이름에 UUID 추가하여 저장
            String originalFileName = file.getOriginalFilename();
            String fileExtension = getFileExtension(originalFileName);
            String uniqueFileName = UUID.randomUUID() + fileExtension;
            Path filePath = studentPath.resolve(uniqueFileName);

            // 파일 저장
            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
            }

            log.info("File saved successfully: {}", filePath);

        } catch (IOException e) {
            log.error("Error occurred while uploading file: {}", e.getMessage());
        }
    }


//----------------------------------------------------------------------------------------------------------------------
    // 파일 확장자 추출 메서드
    private String getFileExtension(String fileName) {
        if (fileName != null && fileName.contains(".")) {
            return fileName.substring(fileName.lastIndexOf("."));
        }
        return "";  // 확장자가 없는 경우 빈 문자열 반환
    }
}
