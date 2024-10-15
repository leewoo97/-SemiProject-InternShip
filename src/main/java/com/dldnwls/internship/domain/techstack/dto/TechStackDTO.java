package com.dldnwls.internship.domain.techstack.dto;

import com.dldnwls.internship.domain.techstack.Techstack;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class TechStackDTO {
    private Long id; // Techstack 고유 ID
    private String name; // 기술 스택 이름

}
