package com.krzysztgac.project.service;

import com.krzysztgac.project.entities.CountryData;
import com.krzysztgac.project.repository.CountryDataRepo;

import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class CountryDataService {

    private final CountryDataRepo countryDataRepo;

    public CountryDataService(CountryDataRepo countryDataRepo) {
        this.countryDataRepo = countryDataRepo;
    }

    public Optional<CountryData> findById(Long id) {
        return countryDataRepo.findById(id);
    }

    public Iterable<CountryData> list() {
        return countryDataRepo.findAll();
    }

    public void save(CountryData countryData) {
        countryDataRepo.save(countryData);
    }

    public void save(Iterable<CountryData> countryData) {
        countryDataRepo.saveAll(countryData);
    }


}