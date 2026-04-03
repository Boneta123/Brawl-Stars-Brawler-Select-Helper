package com.services;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.jsonClasses.*;

import com.Classes.*;

import java.util.*;

//Takes Each map object and fills its hashmap with brawlers and amount of times played
@Service
public class battleLogParse {

    private final battleLogsAPI topPlayersBattles;
    private List<player> topPlayers;
    private final mapList mapInfo;

    public battleLogParse(battleLogsAPI topPlayersBattles, mapList mapInfo) {// dependency injection/initialization
        this.topPlayersBattles = topPlayersBattles;
        this.mapInfo = mapInfo;
    }

    // Reset the cached data
    @Scheduled(cron = "0 0 * * *") // Every morining at 12am, cron rate
    public mapList resetCachedData() {
        topPlayers = topPlayersBattles.getBattleHistories();// Gets new battles or players
        getRankedMaps();// updates the current maps
        createHashMaps();// updates the hashmaps for each map
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
