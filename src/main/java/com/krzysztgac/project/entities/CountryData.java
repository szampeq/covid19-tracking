package com.krzysztgac.project.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.context.annotation.Primary;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Data
@AllArgsConstructor
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)

public class CountryData {

    @Id
    @JsonIgnore
    @GeneratedValue( strategy = GenerationType.AUTO )
    private Long CountryId;
    private String Country;
    private Integer Confirmed;
    private Integer Deaths;
    private Integer Recovered;
    private Integer Active;
    private Date Date;
    private Integer newCases;
    private Integer newDeaths;

    public CountryData() {
    }
}
