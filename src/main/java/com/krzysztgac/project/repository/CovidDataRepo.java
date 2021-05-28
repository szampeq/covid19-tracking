package com.krzysztgac.project.repository;

import com.krzysztgac.project.entities.CovidData;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CovidDataRepo extends CrudRepository<CovidData, Long> {
    Optional<CovidData> findById(Long id);
    Optional<CovidData> findByCountry(String Country);

    @Query(value = "SELECT country, cases FROM COVID_DATA WHERE country!='World' ORDER BY cases DESC", nativeQuery=true)
    List<Object[]> findByCases();
    @Query(value = "SELECT country, today_cases FROM COVID_DATA WHERE country!='World' ORDER BY today_cases DESC", nativeQuery=true)
    List<Object[]> findByCasesToday();
    @Query(value = "SELECT country, cases_per_one_million FROM COVID_DATA WHERE country!='World' ORDER BY cases_per_one_million DESC", nativeQuery=true)
    List<Object[]> findByCasesPMC();
    @Query(value = "SELECT country, active FROM COVID_DATA WHERE country!='World' ORDER BY active DESC", nativeQuery=true)
    List<Object[]> findByActiveCases();
    @Query(value = "SELECT country, critical FROM COVID_DATA WHERE country!='World' ORDER BY critical DESC", nativeQuery=true)
    List<Object[]> findByCriticalCases();
    @Query(value = "SELECT country, recovered FROM COVID_DATA WHERE country!='World' ORDER BY recovered DESC", nativeQuery=true)
    List<Object[]> findByRecoveredCases();
    @Query(value = "SELECT country, deaths FROM COVID_DATA WHERE country!='World' ORDER BY deaths DESC", nativeQuery=true)
    List<Object[]> findByDeaths();
    @Query(value = "SELECT country, today_deaths FROM COVID_DATA WHERE country!='World' ORDER BY today_deaths DESC", nativeQuery=true)
    List<Object[]> findByDeathsToday();
    @Query(value = "SELECT country, deaths_per_one_million FROM COVID_DATA WHERE country!='World' ORDER BY deaths_per_one_million DESC", nativeQuery=true)
    List<Object[]> findByDeathsPMC();
    @Query(value = "SELECT country, total_tests FROM COVID_DATA WHERE country!='World' ORDER BY total_tests DESC", nativeQuery=true)
    List<Object[]> findByTests();
    @Query(value = "SELECT country, tests_per_one_million FROM COVID_DATA WHERE country!='World' ORDER BY tests_per_one_million DESC", nativeQuery=true)
    List<Object[]> findByTestsPMC();
}
