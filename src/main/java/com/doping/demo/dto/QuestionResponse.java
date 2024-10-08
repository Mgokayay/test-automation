package com.doping.demo.dto;

import java.util.List;

public record QuestionResponse(Long id, String questionText, List<String> options, Integer correctAnswerIndex) {
}
