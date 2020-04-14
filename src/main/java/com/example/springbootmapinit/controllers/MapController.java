package com.example.springbootmapinit.controllers;

import com.example.springbootmapinit.domain.Point;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by ch on 2020-04-14
 */
@Controller
public class MapController {

    @GetMapping
    public String getMap(Model model){
        model.addAttribute("point" , new Point(52.237049,21.017532,"hehe"));
        return "map.html";
    }
}
