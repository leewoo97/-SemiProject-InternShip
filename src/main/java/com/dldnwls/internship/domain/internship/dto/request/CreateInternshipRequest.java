package com.dldnwls.internship.domain.internship.dto.request;

import com.dldnwls.internship.domain.company.Company;
import com.dldnwls.internship.domain.techstack.Techstack;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class CreateInternshipRequest {
    private String title; //인턴십 제목

    private String description; //인턴십 설명

    private String location; //인턴십 위치

    private BigDecimal salary; //급여(옵션)

    private LocalDateTime startDate; //인턴십 시작일

    private LocalDateTime endDate; //인턴십 종료일

    private String companyName; //회사 이름, (FK,ManyToOne)관계

    private Set<String> requiredSkillNames; //필수 기술 스택 이름(FK, TechStack)

}
