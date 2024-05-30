package com.darkwiki.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebNavigationController {

    @GetMapping("/")
    public String index() {
        return "redirect:/main/index.html";
    }
}
