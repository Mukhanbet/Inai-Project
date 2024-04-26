package com.example.InaiProject.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {
    @GetMapping("/vacancies/version/two")
    public String vacancies() {
        return "vacancies";
    }
}

