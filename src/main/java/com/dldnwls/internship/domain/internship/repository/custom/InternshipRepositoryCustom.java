package com.dldnwls.internship.domain.internship.repository.custom;

import com.dldnwls.internship.domain.internship.Internship;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface InternshipRepositoryCustom {

    public List<Internship> findByInternshipsByFilter(String companyName, Set<String> techstackNames, LocalDate startDate, LocalDate endDate);

}
