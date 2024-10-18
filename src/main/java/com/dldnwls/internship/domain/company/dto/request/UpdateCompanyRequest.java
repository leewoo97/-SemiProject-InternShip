package com.dldnwls.internship.domain.company.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class UpdateCompanyRequest {
    String name; //회사 이름

    String industry; //산업 분야

    String location; //위치

    String website; //회사 웹사이트 URL

    String description; //회사 설명
}
