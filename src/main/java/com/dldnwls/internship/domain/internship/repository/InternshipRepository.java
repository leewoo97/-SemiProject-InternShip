package com.dldnwls.internship.domain.internship.repository;

import com.dldnwls.internship.domain.internship.Internship;
import com.dldnwls.internship.domain.internship.repository.custom.InternshipRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InternshipRepository extends JpaRepository<Internship, Long>, InternshipRepositoryCustom {

}
