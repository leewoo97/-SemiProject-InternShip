package com.dldnwls.internship.global.exception.student;

public class StudentNotFoundException extends RuntimeException{
    public StudentNotFoundException(String studentId) {
        super("Student not found with id: " + studentId);
    }
}
