package com.dldnwls.internship.domain.internship.dto.response;

import com.dldnwls.internship.domain.company.Company;
import com.dldnwls.internship.domain.techstack.dto.TechstackDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class AddTechstacksByInternshipResponse {
    private Long id; //PK, 인턴십 고유ID

    private String title; //인턴십 제목

    private String description; //인턴십 설명

    private String location; //인턴십 위치

    private BigDecimal salary; //급여(옵션)

    private LocalDateTime startDate; //인턴십 시작일

    private LocalDateTime endDate; //인턴십 종료일

    private LocalDateTime createdAt; //등록 날짜

    private Company company; //회사, (FK,ManyToOne)관계

    private Set<TechstackDTO> requiredSkills=new HashSet<>(); //필수 기술 스택(FK, TechStack)
}
