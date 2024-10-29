package com.dldnwls.internship.domain.internship;

import com.dldnwls.internship.domain.company.Company;
import com.dldnwls.internship.domain.techstack.Techstack;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@AllArgsConstructor
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Internship {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id; //PK, 인턴십 고유ID

    @Column(name = "title",nullable = false)
    private String title; //인턴십 제목

    @Column(name = "description",nullable = false)
    private String description; //인턴십 설명

    @Column(name = "location",nullable = false)
    private String location; //인턴십 위치

    @Column(name = "salary")
    private BigDecimal salary; //급여(옵션)

    @Column(name = "start_date",nullable = false)
    private LocalDateTime startDate; //인턴십 시작일

    @Column(name = "end_date",nullable = false)
    private LocalDateTime endDate; //인턴십 종료일

    @Column(name = "created_at",nullable = false)
    private LocalDateTime createdAt; //등록 날짜

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="company_id", nullable = false)
    private Company company; //회사, (FK,ManyToOne)관계

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name="internship_techstack",
            joinColumns = @JoinColumn(name = "internship_id"),
            inverseJoinColumns = @JoinColumn(name = "techstack_id")
    )
    private Set<Techstack> requiredSkills = new HashSet<>(); //필수 기술 스택(FK, TechStack)

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now(); // 엔티티 생성 시 자동으로 등록 날짜 설정
    }

    public Internship updateTitle(String title) {
        this.title = title;
        return this;
    }

    public Internship updateDescription(String description) {
        this.description = description;
        return this;
    }

    public Internship updateSalary(BigDecimal salary) {
        this.salary = salary;
        return this;
    }

    public Internship updateStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
        return this;
    }

    public Internship updateEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
        return this;
    }

    public Internship updateCompany(Company company) {
        this.company = company;
        return this;
    }

    public Internship updateRequiredSkills(Set<Techstack> requiredSkills) {
        this.requiredSkills = requiredSkills;
        return this;
    }

    public Internship removeTechStack(Techstack techstack) {
        this.requiredSkills.remove(techstack);
        return this;
    }
}
