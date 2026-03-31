package com.jsonClasses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class event {
    private String mode;
    private String map;

    public String getMode() {
        return mode;
    }

    public String getMap() {
        return map;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public void setMap(String map) {
        this.map = map;
    }
}
