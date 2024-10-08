package com.doping.demo.controller;


import com.doping.demo.dto.ExamResponse;
import com.doping.demo.entity.Exam;
import com.doping.demo.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/exam")
public class ExamController {

    private final ExamService examService;

    @Autowired
    public ExamController(ExamService examService) {
        this.examService = examService;
    }

    @PostMapping
    public ExamResponse addExam(@RequestBody Exam exam) {
        return examService.save(exam);
    }

    @GetMapping
    public List<ExamResponse> getAllExams() {
        return examService.findAll();
    }

    @GetMapping("{id}")
    public ExamResponse getExamById(@PathVariable Long id) {
        return examService.findById(id);
    }

    @DeleteMapping("{id}")
    public void deleteExamById(@PathVariable Long id) {
        examService.deleteById(id);
    }
}
