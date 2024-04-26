package com.example.InaiProject.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/process-score")
public class ScoreProcessingController {
    @PostMapping
    public String processScore(@RequestBody int score) {
        // Добавьте здесь логику обработки суммы баллов
        return "Финальный результат: " + score;
    }
}