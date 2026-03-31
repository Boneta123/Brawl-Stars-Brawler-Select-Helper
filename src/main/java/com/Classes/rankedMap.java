package com.Classes;

import java.util.HashMap;
import java.util.Map;

public class rankedMap {
    // name of map
    private String name;
    private String gameMode;
    // hashmap for number of times a brawler was played
    // String is the brawler and Integer is the times they were played
    private Map<String, Integer> numBrawlersPlayed = new HashMap<>();

    // creates a map object
    public rankedMap(String name, String gameMode) {
        this.name = name;
        this.gameMode = gameMode;
    }

    public String getName() {
        return name;
    }

    public String getGameMode() {
        return gameMode;
    }

    public Map<String, Integer> getHash() {
        return numBrawlersPlayed;
    }

}
