package com.dldnwls.internship.domain.student.dto.request.student;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class CreateStudentRequest {

    private String name; //학생 이름

    private String email; //이메일

    private String phone; //전화번호

    private String university; //재학 중인 대학교

    private String major; //전공

    private String resume; //이력서 파일 경로

    //Teckstack의 이름목록을 받는 방식으로 수정(다대다 관계의 직접 참조는 엔티티에서 처리)
    private Set<String> techStackNames;
}
