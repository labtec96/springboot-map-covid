package com.example.springbootmapinit.services;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Created by ch on 2020-04-14
 */
@Service
public class Covid19Parser {

    private static final String url = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";

    public String getCovidData(){

        RestTemplate restTemplate = new RestTemplate();
        String values = restTemplate.getForObject(url, String.class);

        return null;
    }
}
