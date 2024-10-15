package com.dldnwls.internship.domain.techstack.repository;

import com.dldnwls.internship.domain.techstack.Techstack;
import com.dldnwls.internship.domain.techstack.repository.custom.TechstackRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface TechstackRepository extends JpaRepository<Techstack,Long>, TechstackRepositoryCustom {
    Optional<Techstack> findByName(String name);

    List<Techstack> findByNameIn(Set<String> names);
}
