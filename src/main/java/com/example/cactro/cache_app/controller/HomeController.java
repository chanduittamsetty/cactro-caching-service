package com.example.cactro.cache_app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "cache";  // This maps to cache.html in the templates folder
    }
}
