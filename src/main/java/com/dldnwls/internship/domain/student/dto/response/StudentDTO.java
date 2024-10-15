package com.dldnwls.internship.domain.student.dto.response;

import com.dldnwls.internship.domain.student.Student;
import com.dldnwls.internship.domain.techstack.Techstack;
import com.dldnwls.internship.domain.techstack.dto.TechStackDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@Builder
@AllArgsConstructor
public class StudentDTO {
    private Long id; //PK,학생 고유 ID

    private String name; //학생 이름

    private String email; //이메일

    private String phone; //전화번호

    private String university; //재학 중인 대학교

    private String major; //전공

    private String resume; //이력서 파일 경로

    private LocalDateTime createdAt; //가입날짜

    private Set<TechStackDTO> techStacks; // DTO로 변경

//    public StudentDTO(Student student) {
//        this.id = student.getId();
//        this.name = student.getName();
//        this.email = student.getEmail();
//        this.phone = student.getPhone();
//        this.university = student.getUniversity();
//        this.major = student.getMajor();
//        this.resume = student.getResume();
//        this.createdAt = student.getCreatedAt();
//        this.techStacks = convertToDTOSet(student.getTechStacks());
//    }
//
//    private Set<TechStackDTO> convertToDTOSet(Set<Techstack> techstackSet) {
//        Set<TechStackDTO> dtoSet = new HashSet<>();
//        for (Techstack techstack : techstackSet) {
//            dtoSet.add(new TechStackDTO(techstack));
//        }
//        return dtoSet;
//    }
}
