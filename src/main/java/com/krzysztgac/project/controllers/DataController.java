package com.krzysztgac.project.controllers;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.krzysztgac.project.ProjectApplication;
import com.krzysztgac.project.entities.CountryData;
import com.krzysztgac.project.entities.CountryInfo;
import com.krzysztgac.project.entities.CovidData;
import com.krzysztgac.project.service.CountryDataService;
import com.krzysztgac.project.service.CovidDataService;
import org.apache.logging.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

@Controller
@RequestMapping("/")
public class DataController {

    private final CovidDataService covidDataService;
    private final CountryDataService countryDataService;
    private final String ApiURL = "https://api.covid19api.com/country/";

    public DataController(CovidDataService covidDataService, CountryDataService countryDataService) {
        this.covidDataService = covidDataService;
        this.countryDataService = countryDataService;
    }

    public Iterable<CovidData> covidDataList() {
        return covidDataService.list();
    }

    public Iterable<CountryData> countryDataList() {
        return countryDataService.list();
    }

   // @RequestMapping
   @GetMapping
   public String index(Model model) {
            Optional<CovidData> world = covidDataService.findByCountry("World");
            CovidData worldData = world.get();
            model.addAttribute("worldData", worldData);
            return "home.html";
   }

    @GetMapping("/list")
    public String list(Model model) {
        Iterable<CovidData> covidDataIt = covidDataService.list();
        List<CovidData> covidData = new ArrayList<>();
        covidDataIt.forEach(covidData::add);
        model.addAttribute("covidData", covidData);
        return "list.html";
    }

    @GetMapping("/country")
    public String countries(Model model, @RequestParam String country) {
        Iterable<CovidData> covidDataIt = covidDataService.list();
        List<CovidData> covidData = new ArrayList<>();
        covidDataIt.forEach(covidData::add);
        model.addAttribute("covidData", covidData);

        Optional<CovidData> countryData = covidDataService.findByCountry(country);
        CovidData countryObj = countryData.get();
        model.addAttribute("countryData", countryObj);
        return "country.html";
    }

    @GetMapping("/rank")
    public String rank(Model model, @RequestParam String sortBy) {
        List<Object[]> covid;
        String parameter = "";
        switch(sortBy){
            case "cases":
                covid = covidDataService.findByCases();
                parameter = "Total Cases";
                break;
            case "todaycases":
                covid = covidDataService.findByCasesToday();
                parameter = "Cases Today";
                break;
            case "casespmc":
                covid = covidDataService.findByCasesPMC();
                parameter = "Cases Per Million Citizens";
                break;
            case "active":
                covid = covidDataService.findByActiveCases();
                parameter = "Active Cases";
                break;
            case "critical":
                covid = covidDataService.findByCriticalCases();
                parameter = "Critical Cases";
                break;
            case "recovered":
                covid = covidDataService.findByRecoveredCases();
                parameter = "Recovered Cases";
                break;
            case "deaths":
                covid = covidDataService.findByDeaths();
                parameter = "Total Deaths";
                break;
            case "deathstoday":
                covid = covidDataService.findByDeathsToday();
                parameter = "Deaths Today";
                break;
            case "deathspmc":
                covid = covidDataService.findByDeathsPMC();
                parameter = "Deaths Per Million Citizens";
                break;
            case "tests":
                covid = covidDataService.findByTests();
                parameter = "Total Tests";
                break;
            case "testspmc":
                covid = covidDataService.findByTestsPMC();
                parameter = "Tests Per Million Citizens";
                break;
            default:
                parameter = "Total Cases";
                covid = covidDataService.findByCases();
        }

        List<String[]> covidStrings = new ArrayList<>();
        for (Object[] o : covid) {
            String country = o[0].toString();
            String value = o[1].toString();
            String[] s = new String[]{country, value};
            covidStrings.add(s);
        }

        model.addAttribute("parameter", parameter);
        model.addAttribute("covid", covidStrings);
        return "rank.html";
    }

    @GetMapping("/daybyday")
    public String daybyday(Model model, @RequestParam String country) throws IOException {

        Gson gson = new Gson();

        /* Country List */
        List<CountryInfo> countryInfoList;
        URL info = new URL("https://api.covid19api.com/countries");

        InputStreamReader reader1 = new InputStreamReader(info.openStream());
        Type listType1 = new TypeToken<ArrayList<CountryInfo>>(){}.getType();
        countryInfoList = gson.fromJson(reader1, listType1);

        countryInfoList.sort(Comparator.comparing(CountryInfo::getCountry));


        /* Country Data List */
        List<CountryData> countryDataList;
        String ApiURL = "https://api.covid19api.com/dayone/country/";

        URL url = new URL(ApiURL + country);
        InputStreamReader reader2 = new InputStreamReader(url.openStream());
        Type listType2 = new TypeToken<ArrayList<CountryData>>(){}.getType();
        countryDataList = gson.fromJson(reader2, listType2);

        for (int i = 1; i < countryDataList.size(); i++) {
            Integer myCases = countryDataList.get(i).getConfirmed();
            Integer myDeaths = countryDataList.get(i).getDeaths();
            Integer prevCases = countryDataList.get(i-1).getConfirmed();
            Integer prevDeaths = countryDataList.get(i-1).getDeaths();
            countryDataList.get(i).setNewCases(myCases - prevCases);
            countryDataList.get(i).setNewDeaths(myDeaths - prevDeaths);
        }

        model.addAttribute("CountryInfo", countryInfoList);
        model.addAttribute("CountryData", countryDataList);

        return "daybyday.html";
    }

}

