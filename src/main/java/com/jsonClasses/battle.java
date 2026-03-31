package com.jsonClasses;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true) // ignores un needed json
public class battle {
    String type;

    List<List<teamMate>> teams;

    public String getType() {
        return type;
    }

    public List<List<teamMate>> getTeams() {
        return teams;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setTeams(List<List<teamMate>> teams) {
        this.teams = teams;

    }
}
