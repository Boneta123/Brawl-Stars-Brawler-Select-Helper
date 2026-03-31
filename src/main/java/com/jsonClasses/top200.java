package com.jsonClasses;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public class top200 {

    @JsonProperty("items")
    private List<player> players;

    public List<player> getPlayers() {
        return players;
    }

    public void setPlayers(List<player> players) {
        this.players = players;

    }
}
