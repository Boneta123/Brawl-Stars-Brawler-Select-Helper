package com.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.jsonClasses.*;

import org.springframework.http.HttpHeaders;

//Gets the info for a specific player
//Used every time a player is clicked on to access more info
@Service
public class enhanceTeamMateInfoAPI {

    private final RestTemplate restTemplate;

    @Value("${brawl.api.key}") // api key encoded
    private String apiKey;

    public enhanceTeamMateInfoAPI() {// dependency injection
        this.restTemplate = new RestTemplate();
    }

    // api call to get the full info needed for each teamMate
    @Async // asynchronous b/c there are many team mates, loads html pages whilst running
           // on another thread
    public void enhanceInfo() {
        // Set up headers with API key
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + apiKey);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        // Make the GET request
        for (teamMate tmate : teamMate.getAllTrackedPlayers()) {// runs the api for each tracked player
            try {// try catch to see if any tags come back invalid
                teamMate updated = restTemplate// temporary updated object
                        .exchange(
                                "https://api.brawlstars.com/v1/players/{tag}",
                                HttpMethod.GET,
                                entity,
                                teamMate.class,
                                tmate.getTag())// this line is what goes it {tag}
                        .getBody();
                // Only runs if there is an update
                if (updated != null) {
                    // only uses fields not already set by previous api call for battlelogs
                    tmate.setTrophies(updated.getTrophies());
                    tmate.setExpLevel(updated.getExpLevel());
                    tmate.setExpPoints(updated.getExpPoints());
                    tmate.setTotalPrestigeLevel(updated.getTotalPrestigeLevel());
                    tmate.setVs3Victories(updated.getVs3Victories());
                }
            } catch (Exception e) {// if invalid return the tag and skip it
                System.out.println("Invalid tag skipped: " + tmate.getTag());
            }
        }

    }

}
