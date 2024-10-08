package com.doping.demo.controller;

import com.doping.demo.dto.QuestionResponse;
import com.doping.demo.entity.Question;
import com.doping.demo.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/question")
public class QuestionController {

    private final QuestionService questionService;

    @Autowired
    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @PostMapping
    private QuestionResponse addQuestion(@RequestBody Question question) {
        return questionService.save(question);
    }

    @GetMapping
    private List<QuestionResponse> getAllQuestions() {
        return questionService.findAll();
    }

    @GetMapping("{id}")
    private QuestionResponse getQuestionById(@PathVariable Long id) {
        return questionService.findById(id);
    }

    @DeleteMapping("{id}")
    private void deleteQuestionById(@PathVariable Long id) {
        questionService.deleteById(id);
    }
}
