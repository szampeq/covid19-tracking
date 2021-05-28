package com.krzysztgac.project.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class CovidData {

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    private Long Id;
    public String country;
    private Integer cases;
    private Integer todayCases;
    private Integer deaths;
    private Integer todayDeaths;
    private Integer recovered;
    private Integer active;
    private Integer critical;
    private Integer casesPerOneMillion;
    private Integer deathsPerOneMillion;
    private Integer totalTests;
    private Integer testsPerOneMillion;

    public CovidData() {
    }

}