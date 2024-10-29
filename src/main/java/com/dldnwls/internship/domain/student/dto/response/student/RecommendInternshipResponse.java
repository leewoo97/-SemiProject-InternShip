package com.dldnwls.internship.domain.student.dto.response.student;

import com.dldnwls.internship.domain.company.dto.response.CompanyDTO;
import com.dldnwls.internship.domain.techstack.dto.TechstackDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class RecommendInternshipResponse {
    private String title; //인턴십 제목

    private String description; //인턴십 설명

    private String location; //인턴십 위치

    private BigDecimal salary; //급여(옵션)

    private LocalDateTime startDate; //인턴십 시작일

    private LocalDateTime endDate; //인턴십 종료일

    private CompanyDTO company; //회사, (FK,ManyToOne)관계

    private Set<TechstackDTO> requiredSkills; //필수 기술 스택(FK, TechStack)
}
