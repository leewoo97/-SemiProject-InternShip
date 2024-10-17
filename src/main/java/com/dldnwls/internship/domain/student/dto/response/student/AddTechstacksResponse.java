package com.dldnwls.internship.domain.student.dto.response.student;

import com.dldnwls.internship.domain.techstack.dto.TechStackDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class AddTechstacksResponse {
    private String name; //학생 이름

    private String email; //이메일

    private String phone; //전화번호

    private String university; //재학 중인 대학교

    private String major; //전공

    private String resume; //이력서 파일 경로

    private LocalDateTime createdAt; //가입날짜

    private Set<TechStackDTO> techStacks; // DTO로 변경
}
