package com.profHacks.AngularChartJS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DataServices {
    @Autowired
    DataRepository dataRepository;
    public List<Data> getAllData(){
        return dataRepository.findAll();
    }

    public List<CountryAndCount> getAllDataByAttr(String attr){
        List<Data> all = getAllData();
        return getData(all, attr);
    }

    private List<CountryAndCount> getData(List<Data> data, String attr) {
        List<CountryAndCount> listOfCounts = new ArrayList<>();
        switch (attr) {
            case "total_cases":
                for (Data aData : data) {
                    CountryAndCount aCount = new CountryAndCount();
                    aCount.setCount(aData.getTotal_cases());
                    aCount.setCountry(aData.getCountry());
                    listOfCounts.add(aCount);
                }
            case "new_cases":
                for (Data aData : data) {
                    CountryAndCount aCount = new CountryAndCount();
                    aCount.setCount(aData.getNew_cases());
                    aCount.setCountry(aData.getCountry());
                    listOfCounts.add(aCount);
                }
            case "total_deaths":
                for (Data aData : data) {
                    CountryAndCount aCount = new CountryAndCount();
                    aCount.setCount(aData.getTotal_cases());
                    aCount.setCountry(aData.getCountry());
                    listOfCounts.add(aCount);
                }
            case "new_deaths":
                for (Data aData : data) {
                    CountryAndCount aCount = new CountryAndCount();
                    aCount.setCount(aData.getNew_deaths());
                    aCount.setCountry(aData.getCountry());
                    listOfCounts.add(aCount);
                }
            case "active_cases":
                for (Data aData : data) {
                    CountryAndCount aCount = new CountryAndCount();
                    aCount.setCount(aData.getActive_cases());
                    aCount.setCountry(aData.getCountry());
                    listOfCounts.add(aCount);
                }
            case "total_recovered":
                for (Data aData : data) {
                    CountryAndCount aCount = new CountryAndCount();
                    aCount.setCount(aData.getTotal_recovered());
                    aCount.setCountry(aData.getCountry());
                    listOfCounts.add(aCount);
                }
            case "serious_critical":
                for (Data aData : data) {
                    CountryAndCount aCount = new CountryAndCount();
                    aCount.setCount(aData.getSerious_critical());
                    aCount.setCountry(aData.getCountry());
                    listOfCounts.add(aCount);
                }
            default:
                break;
        }
        return listOfCounts;
    }

    public List<CountryAndCount> getAllDataByCountry(String country) {
       return dataRepository.getAllByCountry(country);
    }

    public List<String> getAllCountries() {
        return dataRepository.getAllCountries();

    }
}

