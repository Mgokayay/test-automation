package com.doping.demo.dto;

import com.doping.demo.entity.exam_question.ExamQuestion;

import java.util.List;

public record ExamResponse(Long id, String testName, List<ExamQuestion> examQuestions) {
}
