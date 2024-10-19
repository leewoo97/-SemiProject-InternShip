package com.dldnwls.internship.performance.load;

import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.CountDownLatch;

public class LoadTest {
    private static final int TOTAL_REQUESTS = 10000;

    public static void main(String[] args) throws IOException, InterruptedException {
        WebClient webClient = WebClient.create("http://localhost:8080");


        // 비동기 요청 완료를 위한 CountDownLatch
        CountDownLatch latch = new CountDownLatch(TOTAL_REQUESTS);

        // 시작 시간 측정
        long startTime = System.currentTimeMillis();

        for (int i = 0; i < TOTAL_REQUESTS; i++) {
            // studentId를 동적으로 생성
            int studentId = i + 1; // 예시로 1부터 시작하는 studentId

            // 더미 파일 생성
            File dummyFile = createDummyFile(studentId);

            // Multipart 데이터를 생성
            MultipartBodyBuilder builder = new MultipartBodyBuilder();
            builder.part("file", new FileSystemResource(dummyFile)); // 'file' 필드명 변경 필요시 수정
            builder.part("studentId", String.valueOf(studentId)); // studentId를 파라미터로 추가
            MultiValueMap<String, HttpEntity<?>> body = builder.build();

            webClient.post()
                    .uri(uriBuilder -> uriBuilder
                            .path("/students/uploadResume/{studentId}")
                            .build(studentId)) // 실제 파일 업로드 엔드포인트로 수정
                    .bodyValue(body)
                    .retrieve()
                    .bodyToMono(String.class)
                    .doOnError(error -> {
                        // 에러 처리 (필요시)
                        System.err.println("Error occurred: " + error.getMessage());
                        latch.countDown();
                    })
                    .doOnSuccess(response -> {
                        // 응답 처리 (필요시)
                        latch.countDown();
                    })
                    .subscribe();
        }

        // 모든 요청이 완료될 때까지 대기
        latch.await();

        // 종료 시간 측정
        long endTime = System.currentTimeMillis();

        // 소요 시간 계산
        long duration = endTime - startTime;
        System.out.println("Total time required : " + duration + " milliseconds");
    }

    // 더미 파일 생성 함수
    private static File createDummyFile(int studentId) throws IOException {
        File file = new File("dummyfileBy" + studentId + ".txt");
        try (FileWriter writer = new FileWriter(file)) {
            writer.write("이것은 더미 파일입니다. 부하 테스트를 위한 파일입니다.");
        }
        return file;
    }
}
