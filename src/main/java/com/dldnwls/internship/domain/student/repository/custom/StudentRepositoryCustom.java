package com.dldnwls.internship.domain.student.repository.custom;

import com.dldnwls.internship.domain.student.Student;

import java.util.List;
import java.util.Set;

public interface StudentRepositoryCustom {
    List<Student> findStudentsByFilter(String name, String major, Set<String> techStackNames);
}
