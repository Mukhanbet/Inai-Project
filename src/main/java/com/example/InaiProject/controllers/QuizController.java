package com.example.InaiProject.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Map;

@Controller
@RequestMapping("/")
public class QuizController {
    @PostMapping("/submit-test")
    public String submitTest(@RequestParam Map<String, String> responses, RedirectAttributes redirectAttributes) {
        int totalScore = responses.values().stream()
                .mapToInt(Integer::parseInt)
                .sum();

        // Добавляем сумму баллов в RedirectAttributes
        redirectAttributes.addFlashAttribute("score", totalScore);
        return "redirect:/test-result";
    }

    @GetMapping("/submit-test")
    public String test(){
        return "test";
    }

    @GetMapping("/test-result")
    public String showTestResult(Model model) {
        // Параметр "score" автоматически добавлен в модель благодаря RedirectAttributes
        return "test-result";
    }
}
