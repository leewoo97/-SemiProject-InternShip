package com.dldnwls.internship.domain.company.repository.custom;

import com.dldnwls.internship.domain.company.Company;

import java.util.List;

import com.dldnwls.internship.domain.company.QCompany;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CompanyRepositoryImpl implements CompanyRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Company> findCompaniesByFilter(String name, String location, String industry) {
        QCompany company = QCompany.company;

        return queryFactory.selectFrom(company)
                .where(nameContains(name)
                ,locationContains(location)
                ,industryContains(industry))
                .fetch();
    }

//----------------------------------------------------------------------------------------------------------------------
    public BooleanExpression nameContains(String name){
        QCompany company = QCompany.company;
        return name!=null ? company.name.containsIgnoreCase(name) : null;
    }

    public BooleanExpression locationContains(String location){
        QCompany company = QCompany.company;
        return location != null ? company.location.containsIgnoreCase(location) : null;
    }

    public BooleanExpression industryContains(String industry){
        QCompany company = QCompany.company;
        return industry != null ? company.location.containsIgnoreCase(industry) : null;
    }
}
