package com.dldnwls.internship.global.exception.company;

public class CompanyNotFoundException extends RuntimeException{

    public CompanyNotFoundException(String message) {
        super(message);
    }
}
