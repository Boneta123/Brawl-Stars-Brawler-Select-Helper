package com.Classes;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

//this class is the list of current ranked maps
//component annotation allows spring to make beans with this
@Component
public class mapList {

    // initialize the list of ranked maps
    private List<rankedMap> rankedMapList = new ArrayList<>();

    public void addMap(String newMap, String mapMode) {
        // adds the map if there are no maps in the list always
        if (rankedMapList.size() == 0) {
            rankedMap s = new rankedMap(newMap, mapMode);
            rankedMapList.add(s);
        }
        boolean addMap = true;
        for (rankedMap m : rankedMapList) {
            if (m.getName().equals(newMap)) {// if the map is in the list the map will not be added again
                addMap = false;
            }
        }
        if (addMap) {// after the loop if the map is not in the list it is added as an object
            rankedMap s = new rankedMap(newMap, mapMode);
            rankedMapList.add(s);
        }
    }

    // takes a map, then if the map exists that brawler is added to the maps hash
    // if the brawler already exists then its value is updated, increased by one
    public void updateMapHash(String map, String brawler) {
        for (rankedMap m : rankedMapList) {
            if (m.getName().equals(map)) {
                if (m.getHash().containsKey(brawler)) {
                    // If key exists, get the current value, else default to 0
                    int count = m.getHash().getOrDefault(brawler, 0);
                    m.getHash().put(brawler, count + 1);
                } else {
                    m.getHash().put(brawler, 1);
                }
            }
        }
    }

    public List<rankedMap> getRankedMapsList() {
        return rankedMapList;
    }

    // returns a map object in the mapList based on a string
    public rankedMap getMapUsingString(String map) {
        for (rankedMap m : rankedMapList) {
            if (m.getName().equals(map)) {
                return m;
            }
        }
        return null;
    }
}
