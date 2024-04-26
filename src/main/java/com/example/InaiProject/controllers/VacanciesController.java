package com.example.InaiProject.controllers;
import com.example.InaiProject.model.SearchParametersVacancy;
import com.example.InaiProject.services.VacanciesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/vacancies")
public class VacanciesController {
    private final VacanciesService vacanciesService;


    @GetMapping
    public ResponseEntity<?> getAllVacancies() {
        return ResponseEntity.ok(vacanciesService.getAllVacancies().getItems());
    }

    @PostMapping
    public ResponseEntity<?> getAllVacancies(@RequestBody SearchParametersVacancy params) {
        return ResponseEntity.ok(vacanciesService.getAllVacancies(params).getItems());
    }

    @GetMapping("/{vacId}")
    public ResponseEntity<?> getVacancyById(@PathVariable String vacId) {
        return ResponseEntity.ok(vacanciesService.getVacancyById(vacId));
    }
}
