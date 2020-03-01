package com.profHacks.AngularChartJS;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DataController {
    Logger logger = LoggerFactory.getLogger(DataController.class);

    @Autowired
    DataServices dataServices;

    @GetMapping("/data")
    public List<Data> getAllData(){
        return dataServices.getAllData();
    }
    @GetMapping("/data/attribute/{attr}")
    public List<CountryAndCount> getAllDataByAttr(@PathVariable("attr") String attr){
        return dataServices.getAllDataByAttr(attr);
    }
    @GetMapping("/data/countries")
    public List<String> getAllCountries(){
        return dataServices.getAllCountries();
    }
    @GetMapping("/data/country/{country}")
    public List<CountryAndCount> getAllDataByCountry(@PathVariable("country") String country){
        return dataServices.getAllDataByCountry(country);
    }


}
