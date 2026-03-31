package com.jsonClasses;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class gameLog {

    @JsonProperty("items")
    private List<match> matches;

    public List<match> getMatches() {
        return matches;
    }

    public void setMatches(List<match> matches) {
        this.matches = matches;
    }

}
