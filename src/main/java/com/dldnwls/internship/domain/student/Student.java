package com.dldnwls.internship.domain.student;

import com.dldnwls.internship.domain.techstack.Techstack;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Student {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id; //PK,학생 고유 ID

    @Column(name = "name", nullable = false, length = 50)
    private String name; //학생 이름

    @Column(name = "email", nullable = false, unique = true)
    private String email; //이메일

    @Column(name = "phone", nullable = false, unique = true)
    private String phone; //전화번호

    @Column(name = "university", nullable = false)
    private String university; //재학 중인 대학교

    @Column(name = "major", nullable = false)
    private String major; //전공

    @Column(name = "resume", nullable = false)
    private String resume; //이력서 파일 경로

    @Column(name = "createdAt", nullable = false, updatable = false)
    private LocalDateTime createdAt; //가입날짜

    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinTable(
            name = "student_techstack",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "techstack_id")
    )
    private Set<Techstack> techStacks = new HashSet<>(); // 다대다 관계 설정

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }

    public Student updateName(String name){
        this.name = name;
        return this;
    }

    public Student updateEmail(String email){
        this.email = email;
        return this;
    }

    public Student updatePhone(String phone){
        this.phone = phone;
        return this;
    }

    public Student updateUniversity(String university){
        this.university = university;
        return this;
    }

    public Student updateMajor(String major){
        this.major = major;
        return this;
    }

    public Student updateResume(String resume){
        this.resume = resume;
        return this;
    }

    public Student updateTechStacks(Set<Techstack> techStacks){
        this.techStacks = techStacks;
        return this;
    }
//    @Builder
//    public Student(String name, String email, String phone, String university, String major, String resume, Set<Techstack> techStacks) {
//        this.name = name;
//        this.email = email;
//        this.phone = phone;
//        this.university = university;
//        this.major = major;
//        this.resume = resume;
//        this.techStacks = techStacks;
//    }
}
