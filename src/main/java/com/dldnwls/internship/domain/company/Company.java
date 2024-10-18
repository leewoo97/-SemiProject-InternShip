package com.dldnwls.internship.domain.company;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Company {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    Long id; //PK,회사 고유ID

    @Column(name = "name",nullable = false)
    String name; //회사 이름

    @Column(name = "industry",nullable = false)
    String industry; //산업 분야

    @Column(name = "location",nullable = false)
    String location; //위치

    @Column(name = "website")
    String website; //회사 웹사이트 URL

    @Column(name = "description",nullable = false)
    String description; //회사 설명

    @Column(name = "created_at",nullable = false)
    LocalDateTime createdAt; //가입 날짜

    public Company updateName(String name) {
        this.name = name;
        return this;
    }

    public Company updateIndustry(String industry) {
        this.industry = industry;
        return this;
    }

    public Company updateLocation(String location) {
        this.location = location;
        return this;
    }

    public Company updateWebsite(String website) {
        this.website = website;
        return this;
    }

    public Company updateDescription(String description) {
        this.description = description;
        return this;
    }
}
