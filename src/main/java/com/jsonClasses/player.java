package com.jsonClasses;

import com.fasterxml.jackson.annotation.JsonProperty;

public class player {

    @JsonProperty("tag")
    private String tag;

    private gameLog gameLog;

    public String getTag() {
        return tag;
    }

    public gameLog getGameLog() {
        return gameLog;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public void setGames(gameLog gameLog) {
        this.gameLog = gameLog;
    }
}
