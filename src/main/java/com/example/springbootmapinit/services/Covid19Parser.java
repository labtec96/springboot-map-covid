package com.example.springbootmapinit.services;

import com.example.springbootmapinit.domain.Point;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ch on 2020-04-14
 */
@Service
public class Covid19Parser {

    private static final String url = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";
    private static final String urlCoronaLmaoNinjaToday = "https://corona.lmao.ninja/v2/countries";
    private static final String getUrlCoronaLmaoNinjaYesterday = "https://corona.lmao.ninja/v2/countries?yesterday=true";
    public List<Point> getCovidData(String date) throws IOException {

        String url = null;
        if(date.equals("today"))
            url = urlCoronaLmaoNinjaToday;
        else if(date.equals("yesterday"))
            url = getUrlCoronaLmaoNinjaYesterday;


        List<Point> points = new ArrayList<>();
        JSONParser parser = new JSONParser();
        try {
            URL data = new URL(url); // URL to Parse
            URLConnection yc = data.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));

            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                JSONArray array = (JSONArray) parser.parse(inputLine);

                // Loop through each item
                for (Object country : array) {
                    JSONObject jsonCountry = (JSONObject) country;

                    //Long id = (Long) jsonCountry.get("updated");
                    JSONObject jsonCountryInfo = (JSONObject)jsonCountry.get("countryInfo");
                    Double lat = Double.parseDouble(jsonCountryInfo.get("lat").toString());
                    Double lon = Double.parseDouble(jsonCountryInfo.get("long").toString());

                    String countryName = (String) jsonCountry.get("country");
                    Integer cases = Integer.parseInt(jsonCountry.get("cases").toString());
                    Double casesPerOneMillion = Double.parseDouble(jsonCountry.get("casesPerOneMillion").toString());

                    points.add(new Point(lat,lon,cases,countryName,casesPerOneMillion));


                }
            }
            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return points;
    }


}
