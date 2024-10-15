package com.dldnwls.internship.domain.techstack;

import com.dldnwls.internship.domain.internship.Internship;
import com.dldnwls.internship.domain.student.Student;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Getter
@NoArgsConstructor
@Entity
public class Techstack {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id; //기술 스택 고유ID(PK)

    @Column(name = "name", nullable = false)
    private String name; //기술 이름(예:'Java', 'Python', 'React')

    @ManyToMany(mappedBy = "techStacks")
    private Set<Student> students = new HashSet<>(); // 역방향 다대다 관계 설정

    @ManyToMany(mappedBy = "requiredSkills")
    private Set<Internship> internships = new HashSet<>(); // 인턴십과의 역방향 다대다 관계

    @Builder
    public Techstack(String name) {
        this.name = name;
    }
}
