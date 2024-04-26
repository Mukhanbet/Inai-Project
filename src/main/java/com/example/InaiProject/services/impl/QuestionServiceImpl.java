package com.example.InaiProject.services.impl;

import com.example.InaiProject.model.Question;
import com.example.InaiProject.repository.QuestionRepository;
import com.example.InaiProject.services.QuestionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class QuestionServiceImpl implements QuestionService {
    private final QuestionRepository questionRepository;

    @Override
    public List<Question> get10Question() {
        List<Question> questions = questionRepository.findAll();
        return new Random().ints(0, questions.size())
                .distinct()
                .limit(10)
                .mapToObj(questions::get)
                .collect(Collectors.toList());
    }
}
