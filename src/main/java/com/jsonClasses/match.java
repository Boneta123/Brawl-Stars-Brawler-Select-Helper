package com.jsonClasses;
//Each class in this represents an object or list in the json, this one is a battle

//The jackson maps any differently named things to my own
//ignores unknown properties
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class match {
    private event event;

    private battle battle;

    public event getEvent() {
        return event;
    }

    public void setEvent(event event) {
        this.event = event;

    }

    public battle getBattle() {
        return battle;
    }

    public void setBattle(battle battle) {
        this.battle = battle;

    }
}
