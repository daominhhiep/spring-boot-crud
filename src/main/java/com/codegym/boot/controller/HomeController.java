package com.codegym.boot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/")
    public String getHomePage() {
        return "index";
    }
}
