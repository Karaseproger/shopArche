package com.shopArche.shopArche.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class controller {
    @GetMapping("/")
    public String home(Model model){
        return"home";
    }

    @GetMapping("/login")
    public String login(Model model){
        return "login";

    }


}
