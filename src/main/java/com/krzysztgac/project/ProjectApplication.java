package com.krzysztgac.project;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.krzysztgac.project.entities.CountryData;
import com.krzysztgac.project.entities.CovidData;
import com.krzysztgac.project.repository.CovidDataRepo;
import com.krzysztgac.project.service.CountryDataService;
import com.krzysztgac.project.service.CovidDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@SpringBootApplication
@EnableJpaRepositories
public class ProjectApplication extends IOException{

    public static final Logger log = LoggerFactory.getLogger(ProjectApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(ProjectApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Bean
    public CommandLineRunner run(RestTemplate restTemplate, CovidDataService covidDataService) throws Exception {
        return args -> {

            List<CovidData> covidDataList = new ArrayList<>();


            // Data from whole World

            try {
                ResponseEntity<List<CovidData>> rateResponse =
                        restTemplate.exchange("https://coronavirus-19-api.herokuapp.com/countries",
                                HttpMethod.GET, null, new ParameterizedTypeReference<>() {
                                });
                log.info("Connection with API: 200 OK.");
                 covidDataList = rateResponse.getBody();
            } catch (Exception e)
            {
                log.error("Oops! Connection with API: failed" + e);
            }

            try {
                for (CovidData covidData : Objects.requireNonNull(covidDataList)) {
                    log.info(covidData.getCountry());
                    covidDataService.save(covidData);
                }
                log.info("Success! Data saved into DB.");
            } catch (Exception e) {
                log.error("Oops! Saving data to DB failed!" + e);
            }

            // Data from selected countries
            //String[] countries = {"Poland", "United Kingdom", "Germany", "Italy", "France", "Ukraine", "Spain", "Sweden", "Israel",
            //        "China", "Brazil", "India", "Australia", "Russia", "United States of America", "South Africa" };

        };
    }
}