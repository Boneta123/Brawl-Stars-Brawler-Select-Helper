package com.controllers;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
// player info controller
public class playerInfoController {

    @GetMapping("/playerInfo")
    public String getPlayerInfoPage(Model model) {
        return "players";
    }

    // for later, get class to return each player used and their trophies and name

}
