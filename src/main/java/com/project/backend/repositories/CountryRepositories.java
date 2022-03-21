package com.project.backend.repositories;

import com.project.backend.models.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepositories extends JpaRepository<Country,String> {
}
