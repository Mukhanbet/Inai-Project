package com.example.InaiProject.controllers;

import com.example.InaiProject.model.Question;
import com.example.InaiProject.services.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class QuizController {
    private final QuestionService questionService;

    @PostMapping("/submit-test")
    public String submitTest(@RequestParam Map<String, String> responses, RedirectAttributes redirectAttributes) {
        int totalScore = responses.values().stream()
                .mapToInt(Integer::parseInt)
                .sum();

        // Добавляем сумму баллов в RedirectAttributes
        redirectAttributes.addFlashAttribute("score", totalScore);
        return "redirect:/test-result";
    }

    @GetMapping("/test-question")
    public String test(Model model){
        List<Question> questions = questionService.get10Question();
        model.addAttribute("question", questions);
        return "test";
    }



    @GetMapping("/test-result")
    public String showTestResult(Model model) {
        Integer score = (Integer) model.getAttribute("score");
        if(score > 15){
            model.addAttribute("profession", "Вам могут подойти профессии, связанные с общением и взаимодействием с людьми, такие как учитель, психолог, менеджер по работе с клиентами.");
        }
        if(score > 10 && score < 16){
            model.addAttribute("profession", "Вам могут подойти профессии, требующие аналитического мышления и решения проблем, такие как инженер, программист, детектив.");
        }
        if(score > 5 && score < 11){
            model.addAttribute("profession", "Вам могут подойти профессии, требующие практических навыков и работы руками, такие как столяр, механик, повар.");
        } else{
            model.addAttribute("profession", "Вам могут подойти профессии, в которых постоянно нужно учиться новому, такие как исследователь, врач, журналист.");
        }
        return "test-result";
    }
}
