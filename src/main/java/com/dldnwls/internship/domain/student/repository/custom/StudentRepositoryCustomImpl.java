package com.dldnwls.internship.domain.student.repository.custom;

import com.dldnwls.internship.domain.student.QStudent;
import com.dldnwls.internship.domain.student.Student;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Set;

import com.dldnwls.internship.domain.techstack.QTechstack;

@RequiredArgsConstructor
public class StudentRepositoryCustomImpl implements StudentRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Student> findStudentsByFilter(String name, String major, Set<String> techStackNames) {
        QStudent student = QStudent.student;
        QTechstack techstack = QTechstack.techstack;

        return queryFactory.selectFrom(student)
                .leftJoin(student.techStacks, techstack)
                .where(nameContains(name),
                        majorContains(major),
                        hasTechStacks(techStackNames))
                .fetch();
    }

//----------------------------------------------------------------------------------------------------------------------
    private BooleanExpression nameContains(String name) {
        QStudent student = QStudent.student;
        return name != null && !name.isEmpty() ? student.name.containsIgnoreCase(name) : null;
    }

    private BooleanExpression majorContains(String major) {
        QStudent student = QStudent.student;
        return major != null && !major.isEmpty() ? student.major.containsIgnoreCase(major) : null;
    }

    private BooleanExpression hasTechStacks(Set<String> techStacks) {
        QStudent student = QStudent.student;
        return techStacks != null && !techStacks.isEmpty() ? student.techStacks.any().name.in(techStacks) : null;
    }
}
