package com.controllers;

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

    // tests api to get all the battle logs and parse them
    @GetMapping("/test2")
    public mapList testAPI2() {
        return parseBattle.resetCachedData();

    }
}
