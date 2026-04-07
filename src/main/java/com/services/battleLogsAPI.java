package com.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.jsonClasses.*;

//Takes the list of players ids and gets the battleLog for each player
//Makes a seperate list for each player
@Service
public class battleLogsAPI {

    private final RestTemplate restTemplate;
    top200 topPlayersList;// creating the list of players

    @Value("${brawl.api.key}") // my api key encoded
    private String apiKey;

    private final topPlayerAPI apiTopPlayers;// dependency injection of previous

    public battleLogsAPI(topPlayerAPI apiTopPlayers) {// injection
        this.apiTopPlayers = apiTopPlayers;
        this.restTemplate = new RestTemplate();
    }

    // call the api for the top players and for each player, get the gameList with
    // this api and set it to its object for
    // gameList
    public List<player> getBattleHistories() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + apiKey);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        List<player> topPlayers = apiTopPlayers.topPlayersGet().getPlayers();

        for (player p : topPlayers) {// runs the api for each player
            p.setGames(restTemplate
                    .exchange(
                            "https://api.brawlstars.com/v1/players/{tag}/battlelog",
                            HttpMethod.GET,
                            entity,
                            gameLog.class,
                            p.getTag())// this line is what goes it {tag}
                    .getBody());

        }
        return topPlayers;

    }
}
