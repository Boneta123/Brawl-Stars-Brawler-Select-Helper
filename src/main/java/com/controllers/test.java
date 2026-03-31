package com.controllers;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Classes.mapList;
import com.jsonClasses.*;
import com.services.*;

@RestController
public class test {// tests the api is working
    private final battleLogParse parseBattle;
    private final topPlayerAPI apiTopPlayers;

    public test(battleLogParse parseBattle, topPlayerAPI apiTopPlayers) {// dependency injection of battleLogs
        this.parseBattle = parseBattle;
        this.apiTopPlayers = apiTopPlayers;
    }

    // tests api to get top players
    @GetMapping("/test1")
    public top200 testAPI1() {
        return apiTopPlayers.topPlayersGet();

    }

    // tests api to get all the battle logs
    @GetMapping("/test2")
    public List<player> testAPI2() {
        return parseBattle.resetCachedData();

    }

    // tests the ranked map generation works, run test 2 first
    @GetMapping("/test3")
    public mapList testMapListGeneration() {
        return parseBattle.getRankedMaps();

    }

    // tests if all the hashmaps are created for each map correctly
    @GetMapping("/test4")
    public mapList testMapHashGeneration() {
        return parseBattle.createHashMaps();

    }

    // runs all the previous tests in one route
    @GetMapping("/fullHashTest")
    public mapList fullTest() {
        parseBattle.resetCachedData();
        parseBattle.getRankedMaps();
        return parseBattle.createHashMaps();
    }

}
