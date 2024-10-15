package com.dldnwls.internship.domain.application;

import com.dldnwls.internship.domain.internship.Internship;
import com.dldnwls.internship.domain.student.Student;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Entity
public class Application {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id; //PK,지원 고유ID

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student; //FK, 학생ID

    @ManyToOne
    @JoinColumn(name = "internship_id")
    private Internship internship; //FK, 인턴쉽ID

    private String status; //지원상태(예:'지원중','합격','불합격)
    private LocalDateTime applied_at; //지원날짜
    private String resume; //지원 시 제출한 이력서 파일 경로
}
