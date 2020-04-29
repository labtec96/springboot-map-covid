package com.example.springbootmapinit.controllers;

import com.example.springbootmapinit.services.Covid19Parser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by ch on 2020-04-14
 */
@Slf4j
@Controller
public class MapController {

    private Covid19Parser covid19Parser;

    public MapController(Covid19Parser covid19Parser) {
        this.covid19Parser = covid19Parser;
    }

    @GetMapping
    public String getMap(Model model){
        log.info("Get Mapping");
        System.out.println("Get Mapping");
        covid19Parser.getCovidData();
        model.addAttribute("countries" , covid19Parser.getCovidData());
        model.addAttribute("countriesHighestIncrease", covid19Parser.getHighestIncrease());
        return "map.html";
    }
}
