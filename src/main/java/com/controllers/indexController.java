package com.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import org.springframework.ui.Model;
import com.Classes.*;

@RestController
// main page controller
public class indexController {

    private final mapList maps;

    public indexController(mapList m) {// dependency injection of cached data
        this.maps = m;
    }

    @GetMapping("/")
    public String indexPath(Model model) {
        return "index";
    }

    // returns the page using the info for the map that user selected
    @GetMapping("/mapPageInfo/{map}")
    public String returnMapPage(Model model, @PathVariable String map) {
        rankedMap currentMap = maps.getMapUsingString(map);
        model.addAttribute("currentMap", currentMap.getName());
        return "mapInfo";
    }

    // url to call to get the hashmap for a specific map using the maps name as a
    // string
    @GetMapping("/getMapHash/{map}")
    public Map<String, Integer> getMapHash(@PathVariable String map) {
        return maps.getMapUsingString(map).getHash();
    }

}
