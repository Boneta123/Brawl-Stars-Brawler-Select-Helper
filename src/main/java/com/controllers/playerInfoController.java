package com.controllers;

import org.springframework.web.bind.annotation.GetMapping;

import com.jsonClasses.teamMate;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
// player info controller
public class playerInfoController {

    @GetMapping("/playerInfo")
    public String getPlayerInfoPage(Model model) {
        model.addAttribute("playerList", teamMate.getAllTrackedPlayers());
        return "players";
    }

}
