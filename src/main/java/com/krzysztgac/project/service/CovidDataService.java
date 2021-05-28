package com.krzysztgac.project.service;

import com.krzysztgac.project.entities.CovidData;
import com.krzysztgac.project.repository.CovidDataRepo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class CovidDataService {

    private final CovidDataRepo covidDataRepo;

    public CovidDataService(CovidDataRepo covidDataRepo) {
        this.covidDataRepo = covidDataRepo;
    }

    public Optional<CovidData> findById(Long id) {
        return covidDataRepo.findById(id);
    }

    public Optional<CovidData> findByCountry(String Country) {
        return covidDataRepo.findByCountry(Country);
    }

    public Iterable<CovidData> list() {
        return covidDataRepo.findAll();
    }

    public void save(CovidData covidData) {
        covidDataRepo.save(covidData);
    }

    public void save(Iterable<CovidData> covidData) {
        covidDataRepo.saveAll(covidData);
    }

    public List<Object[]> findByCases(){
        return covidDataRepo.findByCases();
    }
    public List<Object[]> findByCasesToday(){
        return covidDataRepo.findByCasesToday();
    }
    public List<Object[]> findByCasesPMC(){
        return covidDataRepo.findByCasesPMC();
    }
    public List<Object[]> findByActiveCases(){
        return covidDataRepo.findByActiveCases();
    }
    public List<Object[]> findByCriticalCases(){
        return covidDataRepo.findByCriticalCases();
    }
    public List<Object[]> findByRecoveredCases(){
        return covidDataRepo.findByRecoveredCases();
    }
    public List<Object[]> findByDeaths(){
        return covidDataRepo.findByDeaths();
    }
    public List<Object[]> findByDeathsToday(){
        return covidDataRepo.findByDeathsToday();
    }
    public List<Object[]> findByDeathsPMC(){
        return covidDataRepo.findByDeathsPMC();
    }
    public List<Object[]> findByTests(){
        return covidDataRepo.findByTests();
    }
    public List<Object[]> findByTestsPMC(){
        return covidDataRepo.findByTestsPMC();
    }

}