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


@Data
@AllArgsConstructor
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class CountryInfo {
    @Id
    @JsonIgnore
    @GeneratedValue( strategy = GenerationType.AUTO )
    private Long Id;
    public String Country;
    private String Slug;
    private String ISO2;

    public CountryInfo() {
    }
}
