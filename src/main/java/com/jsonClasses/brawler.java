package com.jsonClasses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class brawler {
    private String name;
    private int trophies;

    public String getName() {
        return name;
    }

    public int getTrophies() {
        return trophies;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTrophies(int trophies) {
        this.trophies = trophies;
    }
}
