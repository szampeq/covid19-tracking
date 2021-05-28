package com.krzysztgac.project.repository;

import com.krzysztgac.project.entities.CountryData;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CountryDataRepo extends CrudRepository<CountryData, Long> {
    Optional<CountryData> findById(Long id);

}
