package com.bti.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebNavigationController {

    @GetMapping("/")
    public String redirectToMain() {
//        return "redirect:/praetorius-docs.xhtml";
        return "redirect:/main.xhtml";
    }
}
