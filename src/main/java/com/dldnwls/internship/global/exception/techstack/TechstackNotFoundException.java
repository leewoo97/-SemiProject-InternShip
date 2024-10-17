package com.dldnwls.internship.global.exception.techstack;

public class TechstackNotFoundException extends RuntimeException{
    public TechstackNotFoundException(String techstackId) {
        super(techstackId);
    }
}
