package com.dldnwls.internship.domain.company.repository;

import com.dldnwls.internship.domain.company.Company;
import com.dldnwls.internship.domain.company.repository.custom.CompanyRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long>, CompanyRepositoryCustom {
}
