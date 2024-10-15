package com.dldnwls.internship.domain.student.repository;

import com.dldnwls.internship.domain.student.Student;
import com.dldnwls.internship.domain.student.repository.custom.StudentRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,Long>, StudentRepositoryCustom {
}
