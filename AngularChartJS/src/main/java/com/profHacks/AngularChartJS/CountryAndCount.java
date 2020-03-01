package com.profHacks.AngularChartJS;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class CountryAndCount {
    private String country;
    private int count;
    public CountryAndCount(){}
    public CountryAndCount(String country, int count) {
        this.country = country;
        this.count = count;
    }
}
