package com.jsonClasses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class teamMate {

    private String name;
    private brawler brawler;

    public String getName() {
        return name;
    }

    public brawler getBrawler() {
        return brawler;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBrawler(brawler brawler) {
        this.brawler = brawler;

    }
}
