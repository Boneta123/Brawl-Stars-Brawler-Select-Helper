package com.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
// map info controller
public class mapInfoController {

    @GetMapping("/info/{map}")
    public String getMapPage(Model model, @PathVariable String map) {
        model.addAttribute("currentMap", map);
        return "mapInfo";
    }

}