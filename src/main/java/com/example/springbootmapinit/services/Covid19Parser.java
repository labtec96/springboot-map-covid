package com.example.springbootmapinit.services;

import com.example.springbootmapinit.domain.Country;
import com.example.springbootmapinit.domain.Point;
import com.example.springbootmapinit.util.Utils;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ch on 2020-04-14
 */

@Slf4j
@Service
public class Covid19Parser {

    //private static final String url = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";
    private static final String urlCoronaLmaoNinjaToday = "https://corona.lmao.ninja/v2/countries";
    private static final String getUrlCoronaLmaoNinjaYesterday = "https://corona.lmao.ninja/v2/countries?yesterday=true";
    private static final String urlCoronaLmaoNinjaTodayHighestIncrease = urlCoronaLmaoNinjaToday + "?sort=todayCases";

    private static Map<String, Country> countries = new HashMap<>();

    public ArrayList<Country> getCovidData() {
        getCovidData(urlCoronaLmaoNinjaToday, true);
        getCovidData(getUrlCoronaLmaoNinjaYesterday, false);
        return new ArrayList<>(countries.values());
    }


    private void getCovidData(String url, boolean getCountryInfo) {
        log.info("Get Covid Data");
        JSONParser parser = new JSONParser();
        try {
            BufferedReader readerFromUrl = Utils.getBufferReaderFromUrl(url);
            String inputLine;
            while ((inputLine = readerFromUrl.readLine()) != null) {
                JSONArray array = (JSONArray) parser.parse(inputLine);

                // Loop through each item
                Country country;
                String countryName;
                for (Object countryObject : array) {
                    JSONObject jsonCountry = (JSONObject) countryObject;
                    if(getCountryInfo) {

                        //Long id = (Long) jsonCountry.get("updated");
                        JSONObject jsonCountryInfo = (JSONObject) jsonCountry.get("countryInfo");
                        double lat = Double.parseDouble(jsonCountryInfo.get("lat").toString());
                        double lon = Double.parseDouble(jsonCountryInfo.get("long").toString());

                        countryName = (String) jsonCountry.get("country");
                        Integer cases = Integer.parseInt(jsonCountry.get("cases").toString());
                        double casesPerOneMillion = Double.parseDouble(jsonCountry.get("casesPerOneMillion").toString());

                        country = new Country(cases, countryName, casesPerOneMillion, new Point(lat, lon));
                        countries.put(countryName,country);
                    }
                    else {
                        countryName = (String) jsonCountry.get("country");
                        Integer cases = Integer.parseInt(jsonCountry.get("cases").toString());
                        double casesPerOneMillion = Double.parseDouble(jsonCountry.get("casesPerOneMillion").toString());

                        country = countries.get(countryName);

                        country.setCasesPerOneMillionYesterday(casesPerOneMillion);
                        country.setAllCasesYesterday(cases);
                    }
                }
            }
            readerFromUrl.close();
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    public List<Country> getHighestIncrease() {
        log.info("Get Highest Increase");
        JSONParser parser = new JSONParser();
        List<Country> countriesHighestIncrease = new ArrayList<>();

        try {
            BufferedReader readerFromUrl = Utils.getBufferReaderFromUrl(urlCoronaLmaoNinjaTodayHighestIncrease);
            String inputLine;
            while ((inputLine = readerFromUrl.readLine()) != null) {
                JSONArray array = (JSONArray) parser.parse(inputLine);

                for (int i = 0; i < 5; i++) {
                    JSONObject jsonCountry = (JSONObject) array.get(i);

                    String countryName = (String) jsonCountry.get("country");
                    Integer todayNewCases = Integer.parseInt(jsonCountry.get("todayCases").toString());

                    Country country = countries.get(countryName);
                    country.setTodayNewCases(todayNewCases);

                    countriesHighestIncrease.add(country);
                }
            }
            readerFromUrl.close();
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        return countriesHighestIncrease;
    }
}
