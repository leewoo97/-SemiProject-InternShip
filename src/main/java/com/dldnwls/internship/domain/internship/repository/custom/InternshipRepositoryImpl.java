package com.dldnwls.internship.domain.internship.repository.custom;

import com.dldnwls.internship.domain.company.QCompany;
import com.dldnwls.internship.domain.internship.Internship;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import com.dldnwls.internship.domain.internship.QInternship;
import com.dldnwls.internship.domain.techstack.QTechstack;


@RequiredArgsConstructor
public class InternshipRepositoryImpl implements InternshipRepositoryCustom{

    private final JPAQueryFactory queryFactory;


    @Override
    public List<Internship> findByInternshipsByFilter(String companyName, Set<String> techstackNames, LocalDate startDate, LocalDate endDate) {
        QInternship internship = QInternship.internship;
        QCompany company = QCompany.company;
        QTechstack techstack = QTechstack.techstack;

        return queryFactory.selectFrom(internship)
                .leftJoin(internship.company, company) // 회사와의 조인 추가
                .leftJoin(internship.requiredSkills, techstack)
                .where(nameContain(companyName),
                        hasTechstacks(techstackNames))
                .fetch();
    }

    private BooleanExpression nameContain(String name){
        QCompany company = QCompany.company;
        return name != null && !name.isEmpty() ? company.name.containsIgnoreCase(name) : null;
    }

    private BooleanExpression hasTechstacks(Set<String> techstackNames){
        QInternship internship = QInternship.internship;
        return techstackNames != null && !techstackNames.isEmpty() ? internship.requiredSkills.any().name.in(techstackNames) : null;
    }

}
