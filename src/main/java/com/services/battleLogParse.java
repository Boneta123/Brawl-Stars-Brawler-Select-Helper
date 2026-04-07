package com.services;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.jsonClasses.*;

import jakarta.annotation.PostConstruct;

import com.Classes.*;

import java.util.*;

//Takes Each map object and fills its hashmap with brawlers and amount of times played
@Service
public class battleLogParse {

    private final battleLogsAPI topPlayersBattles;
    private List<player> topPlayers;
    private final mapList mapInfo;
    private final enhanceTeamMateInfoAPI playerInfo;

    public battleLogParse(battleLogsAPI topPlayersBattles, mapList mapInfo, enhanceTeamMateInfoAPI playerInfo) {// dependency
        // injection/initialization
        this.topPlayersBattles = topPlayersBattles;
        this.mapInfo = mapInfo;
        this.playerInfo = playerInfo;
    }

    // Initialize data
    @PostConstruct
    public void initializeData() {
        System.out.println("=== initializeData START === " + System.currentTimeMillis());// console log start
        topPlayers = topPlayersBattles.getBattleHistories();// Gets new battles or players
        getRankedMaps();// Initializes current maps
        createHashMaps();// Creates the hashmaps for each map
        System.out.println("=== initializeData HashMaps Done === " + System.currentTimeMillis());// console log HashMaps
                                                                                                 // Created
        playerInfo.enhanceInfo();// Enhances each team mate info
        System.out.println("=== initializeData END === " + System.currentTimeMillis()); // console log end
    }

    // Reset the cached data
    @Scheduled(cron = "0 0 * * * *") // Every morining at 12am, cron rate
    public mapList resetCachedData() {
        teamMate.clearAllTrackedPlayers(); // cleares the current teamate list
        topPlayers = topPlayersBattles.getBattleHistories();// Gets new battles or players
        getRankedMaps();// updates the current maps
        createHashMaps();// updates the hashmaps for each map
        playerInfo.enhanceInfo();// Enhances each team mate info
        return mapInfo;
    }

    // MapList and gives it, it's own hashmap
    public mapList getRankedMaps() {
        for (player p : topPlayers) {//
            gameLog g = p.getGameLog();
            for (match m : g.getMatches()) {
                battle b = m.getBattle();
                event e = m.getEvent();
                // filtering of what maps will appear, no 5V5, no null or unknown modes either,
                // no null maps, no showdown either, no duels
                if (b.getType() != null && b.getType().equals("ranked") && e.getMap() != null && e.getMode() != null
                        && !e.getMode().equals("unknown") && !e.getMode().contains("5V5") &&
                        !e.getMode().contains("Showdown") && !e.getMode().contains("duels")) {
                    mapInfo.addMap(e.getMap(), e.getMode());
                }
            }
        }
        return mapInfo;
    }

    // This takes the players battle logs and updates the hashmaps for each
    // trophy/ranked
    // map
    // to determine how many times each brawler was played on this map
    // Checks each brawler for each teamate
    // Also adds eached player as a tracked player
    public mapList createHashMaps() {
        for (player p : topPlayers) {//
            gameLog g = p.getGameLog();
            for (match m : g.getMatches()) {
                event e = m.getEvent();
                for (rankedMap rm : mapInfo.getRankedMapsList()) {
                    if (rm.getName().equals(e.getMap())) {
                        List<List<teamMate>> gameTeams = m.getBattle().getTeams();
                        if (gameTeams != null) {
                            for (List<teamMate> t : gameTeams) {
                                for (teamMate tm : t) {
                                    // tracks teamMate if not already tracked
                                    if (!(teamMate.containsName(tm.getTag()))) {
                                        teamMate.getAllTrackedPlayers().add(tm);
                                    }
                                    mapInfo.updateMapHash(e.getMap(), tm.getBrawler().getName());
                                }
                            }
                        }
                    }
                }

            }

        }
        return mapInfo;
    }

    // getters for the map brawler info and all the top players
    public mapList getMapInfo() {
        return mapInfo;
    }

    public List<player> getTopPlayers() {
        return topPlayers;
    }

}
