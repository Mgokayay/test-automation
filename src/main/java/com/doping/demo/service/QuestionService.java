package com.doping.demo.service;

import com.doping.demo.dto.QuestionResponse;
import com.doping.demo.entity.Question;

import java.util.List;

public interface QuestionService {

    QuestionResponse save(Question question);

    List<QuestionResponse> findAll();

    QuestionResponse findById(Long id);

    void deleteById(Long id);
}
