package com.dldnwls.internship.domain.company.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class CreateCompanyResponse {
    String name; //회사 이름

    String industry; //산업 분야

    String location; //위치

    String website; //회사 웹사이트 URL

    String description; //회사 설명

    LocalDateTime createdAt; //가입 날짜
}
