package com.doping.demo.service;

import com.doping.demo.dto.ExamResponse;
import com.doping.demo.entity.Exam;

import java.util.List;

public interface ExamService {

    ExamResponse save(Exam exam);

    List<ExamResponse> findAll();

    ExamResponse findById(Long id);

    void deleteById(Long id);


}
