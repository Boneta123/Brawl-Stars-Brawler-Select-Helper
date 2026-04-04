package com.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

import com.Classes.*;

@RestController
// data controller
public class dataController {

    private final mapList maps;

    public dataController(mapList m) {// dependency injection of cached data
        this.maps = m;
    }

    // url to call to get the hashmap for a specific map using the maps name as a
    // string. Used for chart.js for each map
    @GetMapping("/getMapHash/{map}")
    public Map<String, Integer> getMapHash(@PathVariable String map) {
        return maps.getMapUsingString(map).getHash();
    }

}
