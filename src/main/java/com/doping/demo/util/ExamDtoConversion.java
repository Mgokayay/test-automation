package com.doping.demo.util;

import com.doping.demo.dto.ExamResponse;
import com.doping.demo.entity.Exam;

import java.util.List;
import java.util.stream.Collectors;

public class ExamDtoConversion {


    public static List<ExamResponse> examConvertList(List<Exam> examList) {

        return examList.stream().map(exam -> new ExamResponse(
                exam.getId(),
                exam.getTestName(),
                exam.getExamQuestions()
        )).collect(Collectors.toList());
    }

    public static ExamResponse examConvert(Exam exam) {
        return new ExamResponse(
                exam.getId(),
                exam.getTestName(),
                exam.getExamQuestions()
        );
    }
}
