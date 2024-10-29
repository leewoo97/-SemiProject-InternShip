package com.dldnwls.internship.domain.company.repository;

import com.dldnwls.internship.domain.company.Company;
import com.dldnwls.internship.domain.company.repository.custom.CompanyRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company, Long>, CompanyRepositoryCustom {
    Optional<Company> findByName(String name);
}
