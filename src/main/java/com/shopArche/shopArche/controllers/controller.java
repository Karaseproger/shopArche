package com.shopArche.shopArche.controllers;


import com.shopArche.shopArche.Repository.TovarRep;
import com.shopArche.shopArche.model.TovarPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class controller {
    @Autowired
    private TovarRep tovarRep;
    @GetMapping("/")
    public String home(Model model){
        List<TovarPost> tovars = tovarRep.findAll();
        model.addAttribute("tovars", tovars);
        return"home";
    }

    @GetMapping("/login")
    public String login(Model model){
        return "login";

    }


}
