package com.example.springbootmapinit.controllers;

import com.example.springbootmapinit.services.Covid19Parser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;

/**
 * Created by ch on 2020-04-14
 */
@Controller
public class MapController {

    private Covid19Parser covid19Parser;

    public MapController(Covid19Parser covid19Parser) {
        this.covid19Parser = covid19Parser;
    }

    @GetMapping
    public String getMap(Model model) throws IOException {
        model.addAttribute("points" , covid19Parser.getCovidData("today"));
        model.addAttribute("pointsYesterday", covid19Parser.getCovidData("yesterday"));
        return "map.html";
    }
}
