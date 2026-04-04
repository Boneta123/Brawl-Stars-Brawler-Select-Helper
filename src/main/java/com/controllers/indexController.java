package com.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import com.Classes.*;

@Controller
// main page controller
public class indexController {

    private final mapList maps;

    public indexController(mapList m) {// dependency injection of cached data
        this.maps = m;
    }

    // adds the mapList to the index page
    @GetMapping("/")
    public String indexPath(Model model) {
        model.addAttribute("maps", maps.getRankedMapsList());
        return "index";
    }
}
