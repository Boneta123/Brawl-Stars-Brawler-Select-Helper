package com.jsonClasses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class teamMate {

    // Static list to track all teamMate instances
    private static List<teamMate> allTeamMates = new ArrayList<>();

    // Player basic info
    private String name;
    private String tag;
    private brawler brawler;
    private int trophies;

    // Player stats
    private int totalPrestigeLevel;
    private int expLevel;
    private int expPoints;

    @JsonProperty("3vs3Victories")
    private int vs3Victories;

    // Constructor auto-registers instance
    public static boolean autoRegister = true; // determines wether teamMate Should be Added

    public teamMate() {
    }

    // --- Static methods ---
    public static List<teamMate> getAllTrackedPlayers() {
        return allTeamMates;
    }

    public static void clearAllTrackedPlayers() {
        allTeamMates.clear();
    }

    // makes sure duplicate teamMates are not added to the trackedPlayers
    public static boolean containsName(String tag) {
        for (teamMate t : allTeamMates) {
            if (t.getTag().equals(tag)) {
                return true; // Found a teammate with this tag
            }
        }
        return false; // Not found
    }

    // --- Getters and setters ---

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public brawler getBrawler() {
        return brawler;
    }

    public void setBrawler(brawler brawler) {
        this.brawler = brawler;
    }

    public int getTrophies() {
        return trophies;
    }

    public void setTrophies(int trophies) {
        this.trophies = trophies;
    }

    public int getTotalPrestigeLevel() {
        return totalPrestigeLevel;
    }

    public void setTotalPrestigeLevel(int totalPrestigeLevel) {
        this.totalPrestigeLevel = totalPrestigeLevel;
    }

    public int getExpLevel() {
        return expLevel;
    }

    public void setExpLevel(int expLevel) {
        this.expLevel = expLevel;
    }

    public int getExpPoints() {
        return expPoints;
    }

    public void setExpPoints(int expPoints) {
        this.expPoints = expPoints;
    }

    public int getVs3Victories() {
        return vs3Victories;
    }

    public void setVs3Victories(int vs3Victories) {
        this.vs3Victories = vs3Victories;
    }
}