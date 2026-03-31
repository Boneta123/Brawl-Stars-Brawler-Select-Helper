package com.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.jsonClasses.*;

import org.springframework.http.HttpHeaders;

//Gets the top players and creates a list 
@Service
public class topPlayerAPI {

    private final RestTemplate restTemplate;

    @Value("${brawl.api.key}") // api key encoded
    private String apiKey;

    public topPlayerAPI() {
        this.restTemplate = new RestTemplate();
    }

    // api call to get the top200 players list in the US and maps it to the class
    public top200 topPlayersGet() {
        // Set up headers with API key
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + apiKey);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        // Make the GET request
        top200 topPlayers = restTemplate
                .exchange(
                        "https://api.brawlstars.com/v1/rankings/US/players",
                        HttpMethod.GET,
                        entity,
                        top200.class)
                .getBody();

        return topPlayers;
    }

}
